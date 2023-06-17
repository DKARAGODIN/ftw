package pro.karagodin.generators;

import java.util.List;
import java.util.Random;

import pro.karagodin.ai_system.ConfusedEffect;
import pro.karagodin.ai_system.OneOfTwoEffect;
import pro.karagodin.ai_system.ProbabilisticEffect;
import pro.karagodin.ai_system.PushEffect;
import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

public class ComplexMobFactory implements MobFactory {

    private final ComplexStrategyFactory strategyFactory;
    private final int stage;
    private final int difficulty; // 0 - 4;
    private final Random randomGenerator;

    public ComplexMobFactory(int stage, /*5-9*/ int difficulty) {
        this.strategyFactory = new ComplexStrategyFactory();
        this.stage = stage;
        this.difficulty = difficulty - 5;
        this.randomGenerator = new Random();
    }

    private int nextInt(int bound) {
        return randomGenerator.nextInt(Math.max(bound, 0) + 1);
    }

    @Override
    public Mob createAggressiveMob() {
        return new Mob(
                25 + stage * 25 / (5 - difficulty) + nextInt(stage * 10),
                stage * 3,
                stage * 2,
                stage * 10 + nextInt(stage * 5),
                stage * 17 + nextInt(stage * 5),
                new TimeMoment(stage > 30 ? 25 : 925 - stage * 30),
                strategyFactory.createAggressiveStrategy(),
                'A',
                List.of(
                        new ProbabilisticEffect(new OneOfTwoEffect(
                                new ConfusedEffect(new TimeMoment(1500)),
                                new PushEffect(5)
                        ), 0.2)
                )
        );
    }

    @Override
    public Mob createPassiveAggressiveMob() {
        return new Mob(
                40 + stage * 30 / (5 - difficulty) + nextInt(stage * 10),
                stage,
                stage,
                stage * 10 + nextInt(stage * 5),
                stage * 25 + nextInt(stage * 5),
                new TimeMoment(stage > 30 ? 40 : 940 - stage * 30),
                strategyFactory.createPassiveAggressiveStrategy(),
                'R',
                List.of(
                        new ProbabilisticEffect(new OneOfTwoEffect(
                                new ConfusedEffect(new TimeMoment(1500)),
                                new PushEffect(5)
                        ), 0.2)
                )
        );
    }

    @Override
    public Mob createPassiveMob() {
        return new Mob(
                40 + stage * 25 / (difficulty + 1),
                stage,
                stage * 5,
                stage * 15 + nextInt(stage * 5),
                stage * 25 + nextInt(stage * 5),
                new TimeMoment(stage > 10 ? 50 : 1050 - stage * 100),
                strategyFactory.createPassiveStrategy(),
                'P',
                List.of()
        );
    }

    @Override
    public Mob createCowardMob() {
        return new Mob(
                20 + stage * 20 / (5 - difficulty) + nextInt(stage * 10),
                stage * 3,
                stage * 3,
                stage * 5 + nextInt(stage * 5),
                stage * 15 + nextInt(stage * 5),
                new TimeMoment(stage > 10 ? 10 : 1010 - stage * 100),
                strategyFactory.createCowardStrategy(),
                'C',
                List.of(
                        new ProbabilisticEffect(new OneOfTwoEffect(
                                new ConfusedEffect(new TimeMoment(1500)),
                                new PushEffect(5)
                        ), 0.2)
                )
        );
    }
}
