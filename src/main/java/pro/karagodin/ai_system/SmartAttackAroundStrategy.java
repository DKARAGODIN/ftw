package pro.karagodin.ai_system;

import java.io.IOException;
import java.util.function.Predicate;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;

public abstract class SmartAttackAroundStrategy extends DecoratingStrategy {

    protected Predicate<Mob> attackMobPredicate;

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        Predicate<Coordinate> thereIsMob =
                coord -> coord != null && map.getCell(coord).getUnit() != null;
        Predicate<Coordinate> thereIsEnemy =
                coord -> thereIsMob.test(coord) && attackMobPredicate.test(map.getCell(coord).getUnit());
        return map
                .getAllDirections()
                .parallelStream()
                .filter(direction -> thereIsEnemy.test(direction.getOperator().apply(mobAndCoord.getPosition())))
                .findAny()
                .map(Map.MapDirection::getAction)
                .orElseGet(() -> {
                    Action action;
                    try {
                        action = subStrategy.getNextAction(mobAndCoord, map);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (action instanceof MoveAction) {
                        Coordinate newCoord = map.getCoordinateByDirection(mobAndCoord.getPosition(), ((MoveAction) action).getDirection());
                        return thereIsMob.test(newCoord) ? new DoNothingAction() : action;
                    } else {
                        return action;
                    }
                });
    }
}
