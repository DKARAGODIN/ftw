package pro.karagodin.generators;

import pro.karagodin.ai_system.Strategy;

public interface StrategyFactory {

    Strategy createAggressiveStrategy();

    Strategy createPassiveAggressiveStrategy();

    Strategy createPassiveStrategy();

    Strategy createCowardStrategy();
}
