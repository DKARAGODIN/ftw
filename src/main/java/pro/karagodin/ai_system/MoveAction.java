package pro.karagodin.ai_system;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.models.Map;

public class MoveAction implements Action {

    private final Direction direction;

    public MoveAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public GameDiff doAction(Judge judge, MobWithPosition mobAndCoord, Map map) {
        Coordinate initialPosition = mobAndCoord.getPosition();
        Coordinate newMobCoord = map.getCoordinateByDirection(initialPosition, direction);
        if (newMobCoord != null) {
            if (judge.canDoMovement(map, newMobCoord)) {
                return judge.doMovement(map, initialPosition, newMobCoord);
            } else if (judge.isAttack(map, newMobCoord)) {
                return judge.doAttack(map, initialPosition, newMobCoord);
            }
        }
        return new GameDiff(mobAndCoord);
    }

    public Direction getDirection() {
        return direction;
    }
}
