package pro.karagodin.ai_system;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class DistanceToPlayerSensitiveStrategy extends SensitiveStrategy {

    private final Strategy shortRangeStrategy;
    private final Strategy longRangeStrategy;
    private final int criticalDistance;
    private int distanceBetweenMobAndPlayer;

    public DistanceToPlayerSensitiveStrategy(Strategy shortRangeStrategy, Strategy longRangeStrategy, int criticalDistance) {
        this.shortRangeStrategy = shortRangeStrategy;
        this.longRangeStrategy = longRangeStrategy;
        this.criticalDistance = criticalDistance;
        this.distanceBetweenMobAndPlayer = 2147483647;
    }

    @Override
    protected Strategy getCurrentStrategy() {
        return distanceBetweenMobAndPlayer <= criticalDistance ? shortRangeStrategy : longRangeStrategy;
    }

    @Override
    protected void updateState(MobWithPosition mobAndCoord, Map map) {
        distanceBetweenMobAndPlayer = map.findPlayerCoordinate().getDistanceBetween(mobAndCoord.getPosition());
    }

    @Override
    public Strategy cloneStrategy() {
        return new DistanceToPlayerSensitiveStrategy(shortRangeStrategy.cloneStrategy(), longRangeStrategy.cloneStrategy(), criticalDistance);
    }
}
