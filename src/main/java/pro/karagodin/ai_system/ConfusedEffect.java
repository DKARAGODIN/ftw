package pro.karagodin.ai_system;

import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

public class ConfusedEffect implements Effect {

    private final TimeMoment duration;

    public ConfusedEffect(TimeMoment duration) {
        this.duration = duration;
    }

    @Override
    public void doEffect(Mob mob) {
        mob.setStrategy(new ConfusedStrategy(mob.getStrategy(), duration));
    }
}
