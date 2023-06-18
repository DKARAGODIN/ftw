package pro.karagodin.ai_system;

import pro.karagodin.models.Mob;

public class PushEffect implements Effect {

    private final int durationInMoveNumber;

    public PushEffect(int durationInMoveNumber) {
        this.durationInMoveNumber = durationInMoveNumber;
    }

    @Override
    public void doEffect(Mob defending, Mob attacker, Direction directionOfImpact) {
        defending.setStrategy(new PushStrategy(defending.getStrategy(), directionOfImpact, durationInMoveNumber));
    }
}
