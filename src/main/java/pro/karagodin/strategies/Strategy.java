package pro.karagodin.strategies;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.actions.Action;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.output.CIDrowable;

/**
 * Responsible for Mobs and player actions
 */
public interface Strategy extends CIDrowable {
    /**
     * Main method, "brain" of every mob
     * @param mobAndCoord contains information about mob and its coordinate on map
     * @param map contains map itself
     * @return action of mob which mob want to do after analyzing of environment
     * @throws IOException so far only for PlayerStrategy because of interacting with laterna lib
     */
    Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException;

    /**
     * Method from pattern "Command"
     * @return next strategy of mob
     */
    Strategy nextStrategy();

    /**
     * Method from interface CIDrowable
     * @return no view for mob by default, it's mean that strategy usually does not affect on view of mob
     */
    @Override
    default char getView() {
        return 0;
    }

    /**
     * Method from interface CIDrowable
     * @return no background because strategy usually can't affect on floor drawing
     */
    @Override
    default TextColor getBackground() {
        return null;
    }

    /**
     * Method from pattern "Prototype", use in cloning of mobs
     * @return strategy that is copy of current strategy
     */
    Strategy cloneStrategy();
}
