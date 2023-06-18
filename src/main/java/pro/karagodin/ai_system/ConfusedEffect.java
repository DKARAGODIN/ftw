package pro.karagodin.ai_system;

import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

public class ConfusedEffect implements Effect {

    private final TimeMoment duration;

    public ConfusedEffect(TimeMoment duration) {
        this.duration = duration;
    }

    @Override
    public void doEffect(Mob defending, Direction directionOfImpact) {
        defending.setStrategy(new ConfusedStrategy(defending.getStrategy(), duration));
    }
}
