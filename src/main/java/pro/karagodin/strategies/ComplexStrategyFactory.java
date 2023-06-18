package pro.karagodin.strategies;

/**
 * Generates complex mobs with linear stats growth and sum random addition and effects on attack
 * Used on difficulty set to 5 - 9
 */
public class ComplexStrategyFactory implements StrategyFactory {
    @Override
    public Strategy createAggressiveStrategy() {
        return new BifurcateStrategy(new AttackOnlyPlayerStrategy(new DistanceToPlayerSensitiveStrategy(
                new PursuitStrategy(0.95),
                new InvisibleStrategy(new PursuitStrategy(0.9)),
                6
        )), 25);
    }

    @Override
    public Strategy createPassiveAggressiveStrategy() {
        return new HpOfMobSensitiveStrategy(
                new NoAttackStrategy(new RoamStrategy()),
                new AttackOnlyPlayerStrategy(new PursuitStrategy(0.6)),
                0.8
        );
    }

    @Override
    public Strategy createPassiveStrategy() {
        return new NoAttackStrategy(new RoamStrategy());
    }

    @Override
    public Strategy createCowardStrategy() {
        return new AttackOnlyPlayerStrategy(new DistanceToPlayerSensitiveStrategy(
                new PursuitStrategy(0.9),
                new InvisibleStrategy(new PursuitStrategy(-0.6)),
                15
        ));
    }
}
