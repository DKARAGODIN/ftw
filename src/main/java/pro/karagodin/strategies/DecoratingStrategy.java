package pro.karagodin.strategies;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.actions.Action;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

/**
 * Class of strategy from pattern "Decorator", complements the existing strategy with new logic
 */
public abstract class DecoratingStrategy implements Strategy {

    protected Strategy subStrategy;

    protected abstract Strategy constructor(Strategy subStrategy);

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        return subStrategy.getNextAction(mobAndCoord, map);
    }

    @Override
    public Strategy nextStrategy() {
        subStrategy = subStrategy.nextStrategy();
        return this;
    }

    @Override
    public char getView() {
        return subStrategy.getView();
    }

    @Override
    public TextColor getBackground() {
        return subStrategy.getBackground();
    }

    @Override
    public TextColor getForeground() {
        return subStrategy.getForeground();
    }

    @Override
    public Strategy cloneStrategy() {
        return constructor(subStrategy.cloneStrategy());
    }
}
