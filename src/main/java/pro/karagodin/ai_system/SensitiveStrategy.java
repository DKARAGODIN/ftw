package pro.karagodin.ai_system;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public abstract class SensitiveStrategy implements Strategy {

    protected abstract Strategy getCurrentStrategy();

    protected abstract void updateState(MobWithPosition mobAndCoord, Map map);

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        updateState(mobAndCoord, map);
        return getCurrentStrategy().getNextAction(mobAndCoord, map);
    }

    @Override
    public Strategy nextStrategy() {
        return getCurrentStrategy().nextStrategy();
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
}
