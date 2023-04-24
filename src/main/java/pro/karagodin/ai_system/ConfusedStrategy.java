package pro.karagodin.ai_system;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.time.TimeInterval;
import pro.karagodin.time.TimeMoment;

import java.io.IOException;

public class ConfusedStrategy extends RoamStrategy {

    private final Strategy originStrategy;
    private final TimeMoment endTime;

    public ConfusedStrategy(Strategy originStrategy, TimeInterval duration) {
        this.originStrategy = originStrategy;
        this.endTime = new TimeMoment().after(duration);
    }

    @Override
    public Strategy nextStrategy() {
        return endTime.deltaWithCurrentTime() > 0 ? this : originStrategy;
    }
}
