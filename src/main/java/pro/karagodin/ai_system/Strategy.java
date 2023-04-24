package pro.karagodin.ai_system;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.output.CIDrowable;

public interface Strategy extends CIDrowable {
    Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException;

    default Strategy nextStrategy() {
        return this;
    }

    @Override
    default char getView() {
        return 0;
    }

    @Override
    default TextColor getBackground() {
        return null;
    }
}
