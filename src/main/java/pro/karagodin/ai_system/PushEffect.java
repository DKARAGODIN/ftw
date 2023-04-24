package pro.karagodin.ai_system;

import pro.karagodin.models.Mob;

public class PushEffect implements Effect {

    private Action action;
    private int durationInMoveNumber;

    public PushEffect(Action action, int durationInMoveNumber) {
        this.action = action;
        this.durationInMoveNumber = durationInMoveNumber;
    }

    @Override
    public void doEffect(Mob mob) {
        mob.setStrategy(new PushStrategy(mob.getStrategy(), action, durationInMoveNumber));
    }
}
