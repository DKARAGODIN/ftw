package pro.karagodin.generators;

import java.util.List;

import pro.karagodin.models.Mob;
import pro.karagodin.time.TimeMoment;

public class SimpleMobFactory implements MobFactory {

    private final SimpleStrategyFactory strategyFactory;
    private final int stage;

    public SimpleMobFactory(int stage) {
        this.strategyFactory = new SimpleStrategyFactory();
        this.stage = stage;
    }

    @Override
    public Mob createAggressiveMob() {
        return new Mob(
                60 + stage * 40,
                stage * 2,
                stage,
                stage * 20,
                stage * 30,
                new TimeMoment(stage > 10 ? 10 : 1010 - stage * 100),
                strategyFactory.createAggressiveStrategy(),
                'A',
                List.of());
    }

    @Override
    public Mob createPassiveAggressiveMob() {
        return new Mob(
                40 + stage * 30,
                stage,
                stage / 2,
                stage * 15,
                stage * 25,
                new TimeMoment(stage > 10 ? 50 : 1050 - stage * 100),
                strategyFactory.createPassiveAggressiveStrategy(),
                'R',
                List.of()
        );
    }

    @Override
    public Mob createPassiveMob() {
        return new Mob(
                40 + stage * 30,
                stage,
                stage / 2,
                stage * 15,
                stage * 25,
                new TimeMoment(stage > 10 ? 50 : 1050 - stage * 100),
                strategyFactory.createPassiveStrategy(),
                'P',
                List.of()
        );
    }

    @Override
    public Mob createCowardMob() {
        return new Mob(
                20 + stage * 20,
                stage / 2,
                stage * 2,
                stage * 5,
                stage * 10,
                new TimeMoment(stage > 10 ? 5 : 1005 - stage * 100),
                strategyFactory.createCowardStrategy(),
                'C',
                List.of()
        );
    }
}
