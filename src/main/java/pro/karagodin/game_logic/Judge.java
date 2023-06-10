package pro.karagodin.game_logic;

import org.jetbrains.annotations.Nullable;

import pro.karagodin.ai_system.Action;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Cell;
import pro.karagodin.models.Hole;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Player;
import pro.karagodin.models.SmallThing;

/**
 * Game logic decision maker
 */
public class Judge {

    private final Player player;
    private final CombatSystem combatSystem;
    private final DeathSystem deathSystem;
    private Map tempMap = null;
    private Coordinate tempCoordinate = null;
    private boolean stageCompleted;

    public Judge(Player player) {
        this.player = player;
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
            case MoveLeft:
            case MoveRight:
            case MoveDown:
            case MoveUp:
                Coordinate newMobCoord = switch (action) {
                    case MoveLeft -> map.getLefterCoordinate(mobAndCoord.getPosition());
                    case MoveRight -> map.getRighterCoordinate(mobAndCoord.getPosition());
                    case MoveDown -> map.getLowerCoordinate(mobAndCoord.getPosition());
                    case MoveUp -> map.getHigherCoordinate(mobAndCoord.getPosition());
                    default -> null;
                };
                if (newMobCoord != null) {
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    } else if (isAttack(map, newMobCoord)) {
                        return doAttack(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                return new GameDiff(mobAndCoord);
            case InteractWithObjectOnFloor:
                return useItem(mobAndCoord, map);
            default:
                return null;
        }
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

    public GameDiff useSmallThing(SmallThing smallThing) {
        return pickSmallThing(smallThing, new MobWithPosition(tempMap, tempCoordinate), tempMap.getCell(tempCoordinate));
    }

    public GameDiff useHole(Hole hole) {
        this.stageCompleted = true;
        return null;
    }

    private GameDiff pickSmallThing(SmallThing smallThing, MobWithPosition mobAndCoord, Cell cell) {
        var player = (Player) mobAndCoord.getMob();
        var inv = player.getInventory();
        cell.setItem(null);
        inv.addSmallThingToStash(smallThing);
        return new GameDiff(new MapDiff(mobAndCoord.getPosition()), mobAndCoord, true);
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
