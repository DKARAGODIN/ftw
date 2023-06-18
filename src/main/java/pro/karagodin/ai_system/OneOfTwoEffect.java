package pro.karagodin.ai_system;

import java.util.Random;

import pro.karagodin.models.Mob;

public class OneOfTwoEffect implements Effect {

    private final Effect firstEffect;
    private final Effect secondEffect;
    private final Random randomGenerator;

    public OneOfTwoEffect(Effect firstEffect, Effect secondEffect) {
        this.firstEffect = firstEffect;
        this.secondEffect = secondEffect;
        this.randomGenerator = new Random();
    }

    @Override
    public void doEffect(Mob defending, Mob attacker, Direction directionOfImpact) {
        if (randomGenerator.nextBoolean()) {
            firstEffect.doEffect(defending, attacker, directionOfImpact);
        } else {
            secondEffect.doEffect(defending, attacker, directionOfImpact);
        }
    }
}
