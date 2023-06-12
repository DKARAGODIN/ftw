package pro.karagodin.generators;


import pro.karagodin.ai_system.DistanceToPlayerSensitiveStrategy;
import pro.karagodin.ai_system.HpOfMobSensitiveStrategy;
import pro.karagodin.ai_system.InvisibleStrategy;
import pro.karagodin.ai_system.PursuitStrategy;
import pro.karagodin.ai_system.RoamStrategy;
import pro.karagodin.ai_system.SmartAttackStrategy;
import pro.karagodin.ai_system.Strategy;

public class ComplexStrategyFactory implements StrategyFactory {
    @Override
    public Strategy createAggressiveStrategy() {
        return new SmartAttackStrategy(new DistanceToPlayerSensitiveStrategy(
                new PursuitStrategy(0.95),
                new InvisibleStrategy(new PursuitStrategy(0.75)),
                6
        ));
    }

    @Override
    public Strategy createPassiveAggressiveStrategy() {
        return new SmartAttackStrategy(new HpOfMobSensitiveStrategy(
                new RoamStrategy(),
                new PursuitStrategy(0.6),
                0.8
        ));
    }

    @Override
    public Strategy createPassiveStrategy() {
        return new SmartAttackStrategy(new RoamStrategy());
    }

    @Override
    public Strategy createCowardStrategy() {
        return new SmartAttackStrategy(new DistanceToPlayerSensitiveStrategy(
                new PursuitStrategy(0.9),
                new InvisibleStrategy(new PursuitStrategy(-0.6)),
                3
        ));
    }
}
