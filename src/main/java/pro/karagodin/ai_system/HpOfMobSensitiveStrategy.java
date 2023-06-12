package pro.karagodin.ai_system;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class HpOfMobSensitiveStrategy extends SensitiveStrategy {

    private final Strategy healthyStrategy;
    private final Strategy illStrategy;
    private final double criticalHpFraction;
    private int currentHp;
    private int maxHp;

    public HpOfMobSensitiveStrategy(Strategy healthyStrategy, Strategy illStrategy, double criticalHpFraction) {
        this.healthyStrategy = healthyStrategy;
        this.illStrategy = illStrategy;
        this.criticalHpFraction = criticalHpFraction;
        this.currentHp = 2147483647;
    }

    @Override
    protected Strategy getCurrentStrategy() {
        return currentHp < criticalHpFraction * maxHp ? illStrategy : healthyStrategy;
    }

    @Override
    protected void updateState(MobWithPosition mobAndCoord, Map map) {
        currentHp = mobAndCoord.getMob().getHp();
    }

    @Override
    public Strategy cloneStrategy() {
        return new HpOfMobSensitiveStrategy(healthyStrategy.cloneStrategy(), illStrategy.cloneStrategy(), criticalHpFraction);
    }
}
