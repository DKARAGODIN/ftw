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
    private final Random randomGenerator;

    public ComplexMobFactory(int stage) {
        this.strategyFactory = new ComplexStrategyFactory();
        this.stage = stage;
        this.randomGenerator = new Random();
    }

    private int nextInt(int bound) {
        return randomGenerator.nextInt(Math.max(bound, 0) + 1);
    }

    @Override
    public Mob createAggressiveMob() {
        return new Mob(
                50 + stage * 15 + nextInt(stage * 10),
                stage + nextInt(stage),
                stage / 3 + nextInt(stage  / 3),
                stage * 10 + nextInt(stage * 5),
                stage * 20 + nextInt(stage * 5),
                new TimeMoment((stage > 30 ? 20 : 920 - stage * 30) + nextInt(50)),
                strategyFactory.createAggressiveStrategy(),
                'a',
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
                40 + stage * 15 + nextInt(stage * 10),
                stage  / 4 + nextInt(stage / 2),
                stage / 4 + nextInt(stage / 2),
                stage * 10 + nextInt(stage * 5),
                stage * 20 + nextInt(stage * 5),
                new TimeMoment((stage > 30 ? 40 : 940 - stage * 30) + nextInt(50)),
                strategyFactory.createPassiveAggressiveStrategy(),
                'r',
                List.of()
        );
    }

    @Override
    public Mob createPassiveMob() {
        return new Mob(
                40 + stage * 30 + nextInt(stage * 10),
                stage * 3 / 4 + nextInt(stage / 2),
                stage / 4 + nextInt(stage / 2),
                stage * 10 + nextInt(stage * 10),
                stage * 20 + nextInt(stage * 10),
                new TimeMoment((stage > 30 ? 40 : 940 - stage * 30) + nextInt(50)),
                strategyFactory.createPassiveStrategy(),
                'p',
                List.of()
        );
    }

    @Override
    public Mob createCowardMob() {
        return new Mob(
                20 + stage * 15 + nextInt(stage * 10),
                stage * 3 / 4 + nextInt(stage / 2),
                stage + nextInt(stage * 2),
                nextInt(stage * 5),
                stage * 5 + nextInt(stage * 10),
                new TimeMoment((stage > 30 ? 10 : 910 - stage * 30) + nextInt(50)),
                strategyFactory.createCowardStrategy(),
                'c',
                List.of()
        );
    }
}
