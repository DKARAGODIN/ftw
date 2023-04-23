package pro.karagodin.ai_system;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

import java.io.IOException;

public interface Strategy {
    Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException;
}
