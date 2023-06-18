package pro.karagodin.ai_system;

import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.models.Map;

public interface Action {

    GameDiff doAction(Judge judge, MobWithPosition mobAndCoord, Map map);
}
