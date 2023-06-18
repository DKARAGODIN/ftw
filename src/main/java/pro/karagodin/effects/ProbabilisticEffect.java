package pro.karagodin.effects;

import java.util.Random;

import pro.karagodin.actions.Direction;
import pro.karagodin.models.Mob;

/**
 * Effect of possible application of given effect
 */
public class ProbabilisticEffect implements Effect {

    private final Effect subEffect;
    private final double probability;
    private final Random randomGenerator;

    public ProbabilisticEffect(Effect subEffect, double probability) {
        this.subEffect = subEffect;
        this.probability = probability;
        this.randomGenerator = new Random();
    }

    @Override
    public void doEffect(Mob defending, Direction directionOfImpact) {
        if (randomGenerator.nextDouble() < probability) {
            subEffect.doEffect(defending, directionOfImpact);
        }
    }
}
