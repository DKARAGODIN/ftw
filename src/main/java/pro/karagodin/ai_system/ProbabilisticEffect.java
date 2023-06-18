package pro.karagodin.ai_system;

import java.util.Random;

import pro.karagodin.models.Mob;

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
    public void doEffect(Mob defending, Mob attacker, Direction directionOfImpact) {
        if (randomGenerator.nextDouble() < probability) {
            subEffect.doEffect(defending, attacker, directionOfImpact);
        }
    }
}
