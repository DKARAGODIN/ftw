package pro.karagodin.ai_system;

import pro.karagodin.time.TimeMoment;

/**
 * Strategy implements confusion effect
 */
public class ConfusedStrategy extends RoamStrategy {

    private final Strategy originStrategy;
    private final TimeMoment endTime;

    public ConfusedStrategy(Strategy originStrategy, TimeMoment duration) {
        this.originStrategy = originStrategy;
        this.endTime = new TimeMoment().after(duration);
    }

    @Override
    public Strategy nextStrategy() {
        return endTime.deltaWithCurrentTime() > 0 ? this : originStrategy;
    }
}
