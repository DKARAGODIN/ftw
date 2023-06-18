package pro.karagodin.strategies;

/**
 * Class from pattern "Abstract factory", create strategies of mobs
 */
public interface StrategyFactory {

    Strategy createAggressiveStrategy();

    Strategy createPassiveAggressiveStrategy();

    Strategy createPassiveStrategy();

    Strategy createCowardStrategy();
}
