package pro.karagodin.game_logic;

import org.jetbrains.annotations.Nullable;

import pro.karagodin.actions.Action;
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
        return action.doAction(this, mobAndCoord, map);
    }

    /**
     *
     * @param mobAndCoord
     * @param map
     * @return
     */
    public GameDiff useItem(MobWithPosition mobAndCoord, Map map) {
        var cell = map.getCell(mobAndCoord.getPosition());
        if (cell.getItem() != null) {
            this.tempMap = map;
            this.tempCoordinate = mobAndCoord.getPosition();
            return cell.getItem().use(this);
        }
        return null;
    }

    /**
     * Pick lootItem from the floor and put it to stash
     * @param lootItem
     * @return
     */
    public GameDiff useLootItem(LootItem lootItem) {
        var playerWithCoord = new MobWithPosition(tempMap, tempCoordinate);
        var player = (Player) playerWithCoord.getMob();
        var inv = player.getInventory();
        inv.addLootToStash(lootItem);
        tempMap.getCell(tempCoordinate).setItem(null);
        return new GameDiff(new MapDiff(tempCoordinate), playerWithCoord, true);
    }

    /**
     * Pick consumableItem from the floor and immediately use it
     * @param consumableItem
     * @return
     */
    public GameDiff useConsumableItem(ConsumableItem consumableItem) {
        var playerWithCoord = new MobWithPosition(tempMap, tempCoordinate);
        var player = (Player) playerWithCoord.getMob();
        int hp = player.getHp() + consumableItem.getHpBoost();
        player.setHp(Math.min(player.getMaxHp(), hp));
        tempMap.getCell(tempCoordinate).setItem(null);
        return new GameDiff(new MapDiff(tempCoordinate), playerWithCoord, false, true);
    }

    /**
     * Stop current stage and move to the next one
     * @param hole
     * @return
     */
    public GameDiff useHole(Hole hole) {
        this.stageCompleted = true;
        return null;
    }

    /**
     * @return true if stage is over for whatever reason
     */
    public boolean isStageOver() {
        return !player.isWantsToContinuePlaying() || player.isKilled() || stageCompleted;
    }

    /**
     * @return true if game is over for whatever reason
     */
    public boolean isGameOver() {
        return !player.isWantsToContinuePlaying() || player.isKilled();
    }

    /**
     * @param map
     * @param position
     * @return true if mob can be moved to position
     */
    public boolean canDoMovement(Map map, Coordinate position) {
        Cell nextMobCell = map.getCell(position);
        return nextMobCell.getWall() == null && nextMobCell.getUnit() == null;
    }

    /**
     *
     * @param map
     * @param position
     * @return true if mob on position can be attacked
     */
    public boolean isAttack(Map map, Coordinate position) {
        Cell nextMobCell = map.getCell(position);
        return nextMobCell.getUnit() != null;
    }

    /**
     * Apply attack action
     * @param map
     * @param attackerPosition
     * @param defenderPosition
     * @return
     */
    public GameDiff doAttack(Map map, Coordinate attackerPosition, Coordinate defenderPosition) {
        Mob attacker = map.getCell(attackerPosition).getUnit();
        Mob defender = map.getCell(defenderPosition).getUnit();
        int heroHealthBefore = attacker instanceof Player ? attacker.getHp() : defender.getHp();
        combatSystem.attack(attacker, defender, attackerPosition.getDirection(defenderPosition));
        int heroHealthAfter = attacker instanceof Player ? attacker.getHp() : defender.getHp();

        GameDiff gameDiff = new GameDiff();
        MapDiff mapDiff = new MapDiff();
        mapDiff.addNewCoordinate(attackerPosition);
        mapDiff.addNewCoordinate(defenderPosition);
        gameDiff.setMapDiff(mapDiff);
        gameDiff.setPlayerStatsChanged(heroHealthBefore - heroHealthAfter != 0);

        mapDiff.addNewCoordinate(attackerPosition);
        mapDiff.addNewCoordinate(defenderPosition);
        if (defender.isKilled()) {
            deathSystem.killMob(defenderPosition, map);
            if (attacker instanceof Player) {
                player.increaseXP(defender.getMaxHp());
                gameDiff.setPlayerStatsChanged(true);
            }
        }
        MobWithPosition newAttackerWithCoord = new MobWithPosition(map, attackerPosition);
        if (!attacker.isKilled() && defender.isKilled()) { // optional if-block
            newAttackerWithCoord = doMovement(map, attackerPosition, defenderPosition).getNewMobPosition();
        }
        gameDiff.setNewMobPosition(newAttackerWithCoord);
        return gameDiff;
    }

    /**
     * Apply movement of Mob
     * @param map
     * @param beginPosition
     * @param endPosition
     * @return
     */
    public GameDiff doMovement(Map map, Coordinate beginPosition, Coordinate endPosition) {
        Mob mob = map.getCell(beginPosition).getUnit();
        map.getCell(beginPosition).setUnit(null);
        map.getCell(endPosition).setUnit(mob);
        MapDiff diff = new MapDiff();
        diff.addNewCoordinate(beginPosition);
        diff.addNewCoordinate(endPosition);
        return new GameDiff(diff, new MobWithPosition(map, endPosition));
    }

    /**
     * Puts mob to timeline
     * @param mobAndCoord
     */
    public void addNewMobAtTimeline(MobWithPosition mobAndCoord) {
        timeline.addNewMob(mobAndCoord);
    }
}
