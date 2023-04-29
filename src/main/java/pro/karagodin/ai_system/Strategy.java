package pro.karagodin.ai_system;

import java.io.IOException;

import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

/**
 * Responsible for Mobs and player actions
 */
public interface Strategy {
    Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException;
}
