package pro.karagodin.strategies;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class DistanceToPlayerSensitiveStrategy extends SensitiveStrategy {

    private final int criticalDistance;
    private int distanceBetweenMobAndPlayer;

    public DistanceToPlayerSensitiveStrategy(Strategy shortRangeStrategy, Strategy longRangeStrategy, int criticalDistance) {
        this.trueStrategy = shortRangeStrategy;
        this.falseStrategy = longRangeStrategy;
        this.criticalDistance = criticalDistance;
        this.distanceBetweenMobAndPlayer = 2147483647;
    }

    @Override
    protected boolean isPredicateExecuted() {
        return distanceBetweenMobAndPlayer <= criticalDistance;
    }

    @Override
    protected void updateState(MobWithPosition mobAndCoord, Map map) {
        distanceBetweenMobAndPlayer = map.findPlayerCoordinate().getDistanceBetween(mobAndCoord.getPosition());
    }

    @Override
    protected Strategy constructor(Strategy trueStrategy, Strategy falseStrategy) {
        return new DistanceToPlayerSensitiveStrategy(trueStrategy, falseStrategy, criticalDistance);
    }
}
