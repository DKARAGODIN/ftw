package pro.karagodin.game_logic;

import org.jetbrains.annotations.Nullable;

import pro.karagodin.ai_system.Action;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.game_engine.Timeline;
import pro.karagodin.models.Cell;
import pro.karagodin.models.ConsumableItem;
import pro.karagodin.models.Hole;
import pro.karagodin.models.LootItem;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Player;

/**
 * Game logic decision maker
 */
public class Judge {

    private final Player player;
    private final Timeline timeline;
    private final CombatSystem combatSystem;
    private final DeathSystem deathSystem;
    private Map tempMap = null;
    private Coordinate tempCoordinate = null;
    private boolean stageCompleted;

    public Judge(Player player, Timeline timeline) {
        this.player = player;
        this.timeline = timeline;
        this.combatSystem = new CombatSystem();
        this.deathSystem = new DeathSystem();
        this.stageCompleted = false;
    }

    /**
     * Calculates what happened in the game and it's results
     * @param action
     * @param mobAndCoord
     * @param map
     * @return
     */
    @Nullable
    public GameDiff doAction(Action action, MobWithPosition mobAndCoord, Map map) {
        switch (action) {
            case MoveLeft, MoveRight, MoveDown, MoveUp -> {
                Coordinate initialPosition = mobAndCoord.getPosition();
                Coordinate newMobCoord = getCoordinateByAction(map, initialPosition, action);
                if (newMobCoord != null) {
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, initialPosition, newMobCoord);
                    } else if (isAttack(map, newMobCoord)) {
                        return doAttack(map, initialPosition, newMobCoord);
                    }
                }
                return new GameDiff(mobAndCoord);
            }
            case InteractWithObjectOnFloor -> {
                return useItem(mobAndCoord, map);
            }
            case BifurcateLeft, BifurcateRight, BifurcateDown, BifurcateUp -> {
                Coordinate initialPosition = mobAndCoord.getPosition();
                Coordinate newMobCoord = getCoordinateByAction(map, initialPosition, action);
                if (newMobCoord != null && canDoMovement(map, newMobCoord)) {
                    GameDiff result = doMovement(map, initialPosition, newMobCoord);
                    map.getCell(initialPosition).setUnit(mobAndCoord.getMob().cloneMob());
                    timeline.addNewMob(new MobWithPosition(map, initialPosition));
                    return result;
                }
                return new GameDiff(mobAndCoord);
            }
            default -> {
                return null;
            }
        }
    }

    private Coordinate getCoordinateByAction(Map map, Coordinate coordinate, Action action) {
        return switch (action) {
            case MoveLeft, BifurcateLeft -> map.getLefterCoordinate(coordinate);
            case MoveRight, BifurcateRight -> map.getRighterCoordinate(coordinate);
            case MoveDown, BifurcateDown -> map.getLowerCoordinate(coordinate);
            case MoveUp, BifurcateUp -> map.getHigherCoordinate(coordinate);
            default -> null;
        };
    }

    private GameDiff useItem(MobWithPosition mobAndCoord, Map map) {
        var cell = map.getCell(mobAndCoord.getPosition());
        if (cell.getItem() != null) {
            this.tempMap = map;
            this.tempCoordinate = mobAndCoord.getPosition();
            return cell.getItem().use(this);
        }
        return null;
    }

    public GameDiff useLootItem(LootItem lootItem) {
        var playerWithCoord = new MobWithPosition(tempMap, tempCoordinate);
        var player = (Player) playerWithCoord.getMob();
        var inv = player.getInventory();
        inv.addLootToStash(lootItem);
        tempMap.getCell(tempCoordinate).setItem(null);
        return new GameDiff(new MapDiff(tempCoordinate), playerWithCoord, true);
    }

    public GameDiff useConsumableItem(ConsumableItem consumableItem) {
        var playerWithCoord = new MobWithPosition(tempMap, tempCoordinate);
        var player = (Player) playerWithCoord.getMob();
        int hp = player.getHp() + consumableItem.getHpBoost();
        player.setHp(Math.min(player.getMaxHp(), hp));
        tempMap.getCell(tempCoordinate).setItem(null);
        return new GameDiff(new MapDiff(tempCoordinate), playerWithCoord, false, true);
    }

    public GameDiff useHole(Hole hole) {
        this.stageCompleted = true;
        return null;
    }

    public boolean isStageOver() {
        return !player.isWantsToContinuePlaying() || player.isKilled() || stageCompleted;
    }

    public boolean isGameOver() {
        return !player.isWantsToContinuePlaying() || player.isKilled();
    }

    private boolean canDoMovement(Map map, Coordinate position) {
        Cell nextMobCell = map.getCell(position);
        return nextMobCell.getWall() == null && nextMobCell.getUnit() == null;
    }

    private boolean isAttack(Map map, Coordinate position) {
        Cell nextMobCell = map.getCell(position);
        return nextMobCell.getUnit() != null;
    }

    private GameDiff doAttack(Map map, Coordinate attackerPosition, Coordinate defenderPosition) {
        Mob attacker = map.getCell(attackerPosition).getUnit();
        Mob defender = map.getCell(defenderPosition).getUnit();
        int heroHealthBefore = attacker instanceof Player ? attacker.getHp() : defender.getHp();
        combatSystem.attack(attacker, defender);
        int heroHealthAfter = attacker instanceof Player ? attacker.getHp() : defender.getHp();

        GameDiff gd = new GameDiff();
        MapDiff mapDiff = new MapDiff();
        gd.setMapDiff(mapDiff);
        gd.setPlayerStatsChanged(heroHealthBefore - heroHealthAfter != 0);

        mapDiff.addNewCoordinate(attackerPosition);
        mapDiff.addNewCoordinate(defenderPosition);
        if (defender.isKilled()) {
            deathSystem.killMob(defenderPosition, map);
            if (attacker instanceof Player) {
                Player player = (Player) attacker;
                player.increaseXP(defender.getMaxHp());
                gd.setPlayerStatsChanged(true);
            }
        }
        MobWithPosition newAttackerWithCoord = new MobWithPosition(map, attackerPosition);
        if (!attacker.isKilled() && defender.isKilled()) { // optional if-block
            newAttackerWithCoord = doMovement(map, attackerPosition, defenderPosition).getNewMobPosition();
        }
        gd.setNewMobPosition(newAttackerWithCoord);
        return gd;
    }

    private GameDiff doMovement(Map map, Coordinate beginPosition, Coordinate endPosition) {
        Mob mob = map.getCell(beginPosition).getUnit();
        map.getCell(beginPosition).setUnit(null);
        map.getCell(endPosition).setUnit(mob);
        MapDiff diff = new MapDiff();
        diff.addNewCoordinate(beginPosition);
        diff.addNewCoordinate(endPosition);
        return new GameDiff(diff, new MobWithPosition(map, endPosition));
    }
}
