package pro.karagodin.strategies;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.actions.Action;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

/**
 * Class of strategy which depends on execution of fixed predicate, the choice is made from two other strategies
 */
public abstract class SensitiveStrategy implements Strategy {

    protected Strategy trueStrategy;
    protected Strategy falseStrategy;

    protected abstract boolean isPredicateExecuted();

    protected abstract void updateState(MobWithPosition mobAndCoord, Map map);

    protected abstract Strategy constructor(Strategy trueStrategy, Strategy falseStrategy);

    protected Strategy getCurrentStrategy() {
        return isPredicateExecuted() ? trueStrategy : falseStrategy;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        updateState(mobAndCoord, map);
        return getCurrentStrategy().getNextAction(mobAndCoord, map);
    }

    @Override
    public Strategy nextStrategy() {
        if (isPredicateExecuted()) {
            trueStrategy = trueStrategy.nextStrategy();
        } else {
            falseStrategy = falseStrategy.nextStrategy();
        }
        return this;
    }

    @Override
    public char getView() {
        return getCurrentStrategy().getView();
    }

    @Override
    public TextColor getForeground() {
        return getCurrentStrategy().getForeground();
    }

    @Override
    public TextColor getBackground() {
        return getCurrentStrategy().getBackground();
    }

    @Override
    public Strategy cloneStrategy() {
        return constructor(trueStrategy.cloneStrategy(), falseStrategy.cloneStrategy());
    }
}
