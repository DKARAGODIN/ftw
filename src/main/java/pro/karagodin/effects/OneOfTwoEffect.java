package pro.karagodin.effects;

import java.util.Random;

import pro.karagodin.actions.Direction;
import pro.karagodin.models.Mob;

/**
 * Effect selected equally probable from two given
 */
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
    public void doEffect(Mob defending, Direction directionOfImpact) {
        if (randomGenerator.nextBoolean()) {
            firstEffect.doEffect(defending, directionOfImpact);
        } else {
            secondEffect.doEffect(defending, directionOfImpact);
        }
    }
}
