package pro.karagodin.ai_system;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.time.TimeMoment;

/**
 * Strategy implements confusion effect
 */
public class ConfusedStrategy extends EffectiveStrategy {

    private final TimeMoment endTime;

    public ConfusedStrategy(Strategy subStrategy, TimeMoment duration) {
        this.subStrategy = subStrategy;
        this.effectStrategy = new RoamStrategy();
        this.endTime = new TimeMoment().after(duration);
    }

    @Override
    protected void updateState(MobWithPosition mobAndCoord, Map map) {
    }

    @Override
    protected boolean isEndOfEffect() {
        return endTime.deltaWithCurrentTime() < 0;
    }
}
