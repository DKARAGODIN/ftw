package pro.karagodin.generators;

import pro.karagodin.ai_system.AttackOnlyPlayerStrategy;
import pro.karagodin.ai_system.BifurcateStrategy;
import pro.karagodin.ai_system.DistanceToPlayerSensitiveStrategy;
import pro.karagodin.ai_system.HpOfMobSensitiveStrategy;
import pro.karagodin.ai_system.InvisibleStrategy;
import pro.karagodin.ai_system.NoAttackStrategy;
import pro.karagodin.ai_system.PursuitStrategy;
import pro.karagodin.ai_system.RoamStrategy;
import pro.karagodin.ai_system.Strategy;

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
                3
        ));
    }
}
