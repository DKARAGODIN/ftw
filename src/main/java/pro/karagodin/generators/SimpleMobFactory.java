package pro.karagodin.generators;

import java.util.List;

import pro.karagodin.models.Mob;
import pro.karagodin.strategies.SimpleStrategyFactory;
import pro.karagodin.time.TimeMoment;

/**
 * Generate mob with simple stat growth
 */
public class SimpleMobFactory implements MobFactory {

    private final SimpleStrategyFactory strategyFactory;
    private final int stage;
    private final int difficulty; // 0 - 4

    public SimpleMobFactory(int stage, int difficulty) {
        this.strategyFactory = new SimpleStrategyFactory();
        this.stage = stage;
        this.difficulty = difficulty;
    }

    @Override
    public Mob createAggressiveMob() {
        return new Mob(
                25 + stage * 25 / (5 - difficulty),
                stage * 3,
                stage * 2,
                stage * 10,
                stage * 17,
                new TimeMoment(stage > 30 ? 25 : 925 - stage * 30),
                strategyFactory.createAggressiveStrategy(),
                'a',
                List.of());
    }

    @Override
    public Mob createPassiveAggressiveMob() {
        return new Mob(
                40 + stage * 30 / (5 - difficulty),
                stage,
                stage,
                stage * 10,
                stage * 25,
                new TimeMoment(stage > 30 ? 40 : 940 - stage * 30),
                strategyFactory.createPassiveAggressiveStrategy(),
                'r',
                List.of()
        );
    }

    @Override
    public Mob createPassiveMob() {
        return new Mob(
                40 + stage * 25 / (difficulty + 1),
                stage,
                stage * 5,
                stage * 5,
                stage * 10,
                new TimeMoment(stage > 10 ? 50 : 1050 - stage * 100),
                strategyFactory.createPassiveStrategy(),
                'p',
                List.of()
        );
    }

    @Override
    public Mob createCowardMob() {
        return new Mob(
                20 + stage * 20  / (5 - difficulty),
                stage * 3,
                stage * 3,
                stage * 5,
                stage * 15,
                new TimeMoment(stage > 10 ? 10 : 1010 - stage * 100),
                strategyFactory.createCowardStrategy(),
                'c',
                List.of()
        );
    }
}
