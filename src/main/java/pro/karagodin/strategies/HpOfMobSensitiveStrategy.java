package pro.karagodin.strategies;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

/**
 * Applies panic effect in case mobs hp drops lower than criticalHpFraction
 */
public class HpOfMobSensitiveStrategy extends SensitiveStrategy {

    private final double criticalHpFraction;
    private int currentHp;
    private int maxHp;

    public HpOfMobSensitiveStrategy(Strategy healthyStrategy, Strategy illStrategy, double criticalHpFraction) {
        this.trueStrategy = healthyStrategy;
        this.falseStrategy = illStrategy;
        this.criticalHpFraction = criticalHpFraction;
        this.currentHp = 2147483647;
        this.maxHp = 2147483647;
    }

    @Override
    protected boolean isPredicateExecuted() {
        return currentHp > criticalHpFraction * maxHp;
    }

    @Override
    protected void updateState(MobWithPosition mobAndCoord, Map map) {
        currentHp = mobAndCoord.getMob().getHp();
        maxHp = mobAndCoord.getMob().getMaxHp();
    }

    @Override
    protected Strategy constructor(Strategy trueStrategy, Strategy falseStrategy) {
        return new HpOfMobSensitiveStrategy(trueStrategy, falseStrategy, criticalHpFraction);
    }
}
