package pro.karagodin.ai_system;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public class InvisibleStrategy implements Strategy {

    private final Strategy subStrategy;

    public InvisibleStrategy(Strategy subStrategy) {
        this.subStrategy = subStrategy;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        return subStrategy.getNextAction(mobAndCoord, map);
    }

    @Override
    public char getView() {
        return 0;
    }

    @Override
    public TextColor getForeground() {
        return subStrategy.getForeground();
    }

    @Override
    public Strategy cloneStrategy() {
        return new InvisibleStrategy(subStrategy.cloneStrategy());
    }
}
