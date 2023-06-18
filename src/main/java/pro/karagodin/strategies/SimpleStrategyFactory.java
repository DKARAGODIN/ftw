package pro.karagodin.strategies;

/**
 * Generates easy mobs with linear stats growth and no effects on attacks
 * Used on difficulty set to 0 - 4
 */
public class SimpleStrategyFactory implements StrategyFactory {
    @Override
    public Strategy createAggressiveStrategy() {
        return new AttackOnlyPlayerStrategy(new PursuitStrategy(0.8));
    }

    @Override
    public Strategy createPassiveAggressiveStrategy() {
        return new AttackOnlyPlayerStrategy(new PursuitStrategy(0.4));
    }

    @Override
    public Strategy createPassiveStrategy() {
        return new AttackOnlyPlayerStrategy(new PursuitStrategy(0));
    }

    @Override
    public Strategy createCowardStrategy() {
        return new AttackOnlyPlayerStrategy(new PursuitStrategy(-0.4));
    }
}
