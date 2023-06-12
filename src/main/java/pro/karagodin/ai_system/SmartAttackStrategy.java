package pro.karagodin.ai_system;

import java.io.IOException;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class SmartAttackStrategy extends AttackAroundStrategy {

    public SmartAttackStrategy(Strategy subStrategy) {
        super(subStrategy);
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        Action action = super.getNextAction(mobAndCoord, map);
        Coordinate newCoordinate = map.getCoordinateByAction(mobAndCoord.getPosition(), action);
        if (newCoordinate != null && map.getCell(newCoordinate).getUnit() != null && !(map.getCell(newCoordinate).getUnit() instanceof Player)) {
            return Action.DoNothing;
        } else {
            return action;
        }
    }

    @Override
    public Strategy cloneStrategy() {
        return new SmartAttackStrategy(subStrategy.cloneStrategy());
    }
}
