package pro.karagodin.ai_system;

import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeInterval;

public class ConfusedEffect implements Effect {

    private final TimeInterval duration;

    public ConfusedEffect(TimeInterval duration) {
        this.duration = duration;
    }

    @Override
    public void doEffect(Mob mob) {
        mob.setStrategy(new ConfusedStrategy(mob.getStrategy(), duration));
    }
}
