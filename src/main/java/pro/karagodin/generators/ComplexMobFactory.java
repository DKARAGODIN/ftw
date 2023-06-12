package pro.karagodin.generators;

import java.util.List;
import java.util.Random;

import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

public class ComplexMobFactory implements MobFactory {

    private final ComplexStrategyFactory strategyFactory;
    private final int stage;
    private final Random randomGenerator;

    public ComplexMobFactory(int stage) {
        this.strategyFactory = new ComplexStrategyFactory();
        this.stage = stage;
        this.randomGenerator = new Random();
    }

    @Override
    public Mob createAggressiveMob() {
        return new Mob(
                60 + stage * 40 + randomGenerator.nextInt(stage * 10),
                stage + randomGenerator.nextInt(stage),
                stage * 2 / 3 + randomGenerator.nextInt(stage * 2 / 3),
                stage * 15 + randomGenerator.nextInt(stage * 10),
                stage * 25 + randomGenerator.nextInt(stage * 10),
                new TimeMoment(stage > 10 ? 10 : 910 - stage * 100 + randomGenerator.nextInt(200)),
                strategyFactory.createAggressiveStrategy(),
                'a',
                List.of());
    }

    @Override
    public Mob createPassiveAggressiveMob() {
        return new Mob(
                40 + stage * 30 + randomGenerator.nextInt(stage * 10),
                stage * 3 / 4 + randomGenerator.nextInt(stage / 2),
                stage / 4 + randomGenerator.nextInt(stage / 2),
                stage * 10 + randomGenerator.nextInt(stage * 10),
                stage * 20 + randomGenerator.nextInt(stage * 10),
                new TimeMoment(stage > 10 ? 50 : 950 - stage * 100 + randomGenerator.nextInt(200)),
                strategyFactory.createPassiveAggressiveStrategy(),
                'r',
                List.of()
        );
    }

    @Override
    public Mob createPassiveMob() {
        return new Mob(
                40 + stage * 30 + randomGenerator.nextInt(stage * 10),
                stage * 3 / 4 + randomGenerator.nextInt(stage / 2),
                stage / 4 + randomGenerator.nextInt(stage / 2),
                stage * 10 + randomGenerator.nextInt(stage * 10),
                stage * 20 + randomGenerator.nextInt(stage * 10),
                new TimeMoment(stage > 10 ? 50 : 950 - stage * 100 + randomGenerator.nextInt(200)),
                strategyFactory.createPassiveStrategy(),
                'p',
                List.of()
        );
    }

    @Override
    public Mob createCowardMob() {
        return new Mob(
                20 + stage * 15 + randomGenerator.nextInt(stage * 10),
                stage * 3 / 4 + randomGenerator.nextInt(stage / 2),
                stage + randomGenerator.nextInt(stage * 2),
                randomGenerator.nextInt(stage * 5),
                stage * 5 + randomGenerator.nextInt(stage * 10),
                new TimeMoment(stage > 10 ? 5 : 905 - stage * 100 + randomGenerator.nextInt(200)),
                strategyFactory.createCowardStrategy(),
                'c',
                List.of()
        );
    }
}
