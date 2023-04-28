package pro.karagodin.ai_system;

import java.io.IOException;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

public interface Strategy {
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException;

    public default Strategy getNextStrategy() {
        return this;
    }

    public TypeOfStrategy getTypeOfStrategy();
}
