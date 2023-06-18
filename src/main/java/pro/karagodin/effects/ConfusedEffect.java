package pro.karagodin.effects;

import pro.karagodin.actions.Direction;
import pro.karagodin.models.Mob;
import pro.karagodin.strategies.ConfusedStrategy;
import pro.karagodin.time.TimeMoment;

/**
 * Effect of confusing, mob start move absolute randomly
 */
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
