package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

import java.io.IOException;

public abstract class EffectiveStrategy implements Strategy {

    protected Strategy subStrategy;
    protected Strategy effectStrategy;

    protected abstract void updateState(MobWithPosition mobAndCoord, Map map);

    protected abstract boolean isEndOfEffect();

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        updateState(mobAndCoord, map);
        return effectStrategy.getNextAction(mobAndCoord, map);
    }

    @Override
    public Strategy nextStrategy() {
        return isEndOfEffect() ? subStrategy : this;
    }

    @Override
    public char getView() {
        return subStrategy.getView();
    }

    @Override
    public TextColor getForeground() {
        return effectStrategy.getForeground();
    }

    @Override
    public TextColor getBackground() {
        return subStrategy.getBackground();
    }

    @Override
    public Strategy cloneStrategy() {
        return subStrategy.cloneStrategy();
    }
}
