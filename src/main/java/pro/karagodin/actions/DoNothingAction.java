package pro.karagodin.actions;

import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.models.Map;

/**
 * Stub class
 */
public class DoNothingAction implements Action {

    @Override
    public GameDiff doAction(Judge judge, MobWithPosition mobAndCoord, Map map) {
        return null;
    }
}
