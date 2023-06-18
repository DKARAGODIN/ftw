package pro.karagodin.actions;

import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.models.Map;

/**
 * Command from pattern "Command" which contains action of mob
 */
public interface Action {

    GameDiff doAction(Judge judge, MobWithPosition mobAndCoord, Map map);
}
