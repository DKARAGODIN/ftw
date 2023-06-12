package pro.karagodin.ai_system;

import pro.karagodin.time.TimeMoment;

/**
 * Strategy implements confusion effect
 */
public class ConfusedStrategy extends RoamStrategy {

    private final Strategy subStrategy;
    private final TimeMoment endTime;

    public ConfusedStrategy(Strategy subStrategy, TimeMoment duration) {
        this.subStrategy = subStrategy;
        this.endTime = new TimeMoment().after(duration);
    }

    @Override
    public Strategy nextStrategy() {
        return endTime.deltaWithCurrentTime() > 0 ? this : subStrategy;
    }

    @Override
    public Strategy cloneStrategy() {
        return subStrategy.cloneStrategy();
    }
}
