package pro.karagodin.game_logic;

import pro.karagodin.ai_system.Action;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Cell;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Player;

public class Judge {

    private Player player;

    public Judge(Player player) {
        this.player = player;
    }

    public GameDiff doAction(Action action, MobWithPosition mobAndCoord, Map map) {
        switch (action) {
            case MoveLeft:
                if (mobAndCoord.getPosition().getX() > 0) {
                    Coordinate newMobCoord = new Coordinate(mobAndCoord.getPosition().getX() - 1, mobAndCoord.getPosition().getY());
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            case MoveRight:
                if (mobAndCoord.getPosition().getX() < map.getWidth() - 1) {
                    Coordinate newMobCoord = new Coordinate(mobAndCoord.getPosition().getX() + 1, mobAndCoord.getPosition().getY());
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            case MoveDown:
                if (mobAndCoord.getPosition().getY() < map.getHeight() - 1) {
                    Coordinate newMobCoord = new Coordinate(mobAndCoord.getPosition().getX(), mobAndCoord.getPosition().getY() + 1);
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            case MoveUp:
                if (mobAndCoord.getPosition().getY() > 0) {
                    Coordinate newMobCoord = new Coordinate(mobAndCoord.getPosition().getX(), mobAndCoord.getPosition().getY() - 1);
                    if (canDoMovement(map, newMobCoord)) {
                        return doMovement(map, mobAndCoord.getPosition(), newMobCoord);
                    }
                }
                break;
            default:
                break;
        }
        return new GameDiff(mobAndCoord);
    }

    public boolean isStageOver() {
        return !player.doesPlayerWantToPlay();
    }

    public boolean isGameOver() {
        return !player.doesPlayerWantToPlay();
    }

    private boolean canDoMovement(Map map, Coordinate position) {
        Cell nextMobCell = map.getCell(position);
        return nextMobCell.getWall() == null && nextMobCell.getUnit() == null;
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
