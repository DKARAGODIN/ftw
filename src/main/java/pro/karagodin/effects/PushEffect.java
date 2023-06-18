package pro.karagodin.effects;

import pro.karagodin.actions.Direction;
import pro.karagodin.models.Mob;
import pro.karagodin.strategies.PushStrategy;

/**
 * Effect of throwing mob aside
 */
public class PushEffect implements Effect {

    private final int durationInMoveNumber;

    public PushEffect(int durationInMoveNumber) {
        this.durationInMoveNumber = durationInMoveNumber;
    }

    @Override
    public void doEffect(Mob defending, Direction directionOfImpact) {
        defending.setStrategy(new PushStrategy(defending.getStrategy(), directionOfImpact, durationInMoveNumber));
    }
}
