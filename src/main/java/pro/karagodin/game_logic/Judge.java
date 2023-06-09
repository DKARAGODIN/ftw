package pro.karagodin.game_logic;

import org.jetbrains.annotations.Nullable;

import pro.karagodin.ai_system.Action;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Cell;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Player;

/**
 * Game logic decision maker
 */
public class Judge {

    private final Player player;
    private final CombatSystem combatSystem;

    public Judge(Player player) {
        this.player = player;
        this.combatSystem = new CombatSystem();
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
                if (mobAndCoord.getPosition().getX() > 0) {
                    Coordinate newMobCoord = mobAndCoord.getPosition().withX(x -> x - 1);
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    } else if (isAttack(map, newMobCoord)) {
                        return doAttack(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            case MoveRight:
                if (mobAndCoord.getPosition().getX() < map.getWidth() - 1) {
                    Coordinate newMobCoord = mobAndCoord.getPosition().withX(x -> x + 1);
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    } else if (isAttack(map, newMobCoord)) {
                        return doAttack(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            case MoveDown:
                if (mobAndCoord.getPosition().getY() < map.getHeight() - 1) {
                    Coordinate newMobCoord = mobAndCoord.getPosition().withY(y -> y + 1);
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    } else if (isAttack(map, newMobCoord)) {
                        return doAttack(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            case MoveUp:
                if (mobAndCoord.getPosition().getY() > 0) {
                    Coordinate newMobCoord = mobAndCoord.getPosition().withY(y -> y - 1);
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    } else if (isAttack(map, newMobCoord)) {
                        return doAttack(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            case InteractWithObjectOnFloor:
                return tryPickItem(mobAndCoord, map);
            default:
                break;
        }
        return new GameDiff(mobAndCoord);
    }

    private GameDiff tryPickItem(MobWithPosition mobAndCoord, Map map) {
        var cell = map.getCell(mobAndCoord.getPosition());
        if (cell.getFloor().hasItem())
            return pickItem(mobAndCoord, map, cell);
        return null;
    }

    private GameDiff pickItem(MobWithPosition mobAndCoord, Map map, Cell cell) {
        var player = (Player) mobAndCoord.getMob();
        var inv = player.getInventory();
        var item = cell.getFloor().pickItem();
        inv.addItemToStash(item);

        var mapDiff = new MapDiff();
        mapDiff.addNewCoordinate(mobAndCoord.getPosition());
        return new GameDiff(mapDiff, mobAndCoord, true);
    }

    public boolean isStageOver() {
        return !player.isWantsToContinuePlaying();
    }

    public boolean isGameOver() {
        return !player.isWantsToContinuePlaying();
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
        combatSystem.attack(attacker, defender);

        if (defender.getHp() <= 0) {
            if (defender instanceof Player) {
                //Game over
                System.exit(1);
            } else {
                GameDiff result = new GameDiff();
                MapDiff mapDiff = new MapDiff(defenderPosition);
                map.getCell(defenderPosition).setUnit(null);
                result.setMapDiff(mapDiff);
                return result;
            }
        } else if (attacker.getHp() <= 0) {
            if (attacker instanceof Player) {
                //Game over
                System.exit(1);
            } else {
                GameDiff result = new GameDiff();
                MapDiff mapDiff = new MapDiff(attackerPosition);
                map.getCell(attackerPosition).setUnit(null);
                result.setMapDiff(mapDiff);
                return result;
            }
        }
        return null;
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
