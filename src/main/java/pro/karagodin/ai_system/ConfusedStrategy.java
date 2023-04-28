package pro.karagodin.ai_system;

import pro.karagodin.output.ConsoleCharacter;
import pro.karagodin.time.TimeInterval;
import pro.karagodin.time.TimeMoment;

public class ConfusedStrategy extends RoamStrategy {

    private final Strategy originStrategy;
    private final TimeMoment endTime;

    public ConfusedStrategy(Strategy originStrategy, TimeInterval duration) {
        this.originStrategy = originStrategy;
        this.endTime = new TimeMoment().after(duration);
    }

    @Override
    public Strategy getNextStrategy() {
        return endTime.deltaWithCurrentTime() > 0 ? this : originStrategy;
    }

    @Override
    public ConsoleCharacter modifyMobCharacter(ConsoleCharacter character) {
        return originStrategy.modifyMobCharacter(character).overwrite(new ConsoleCharacter(null, null, null, null, true, null));
    }
}
