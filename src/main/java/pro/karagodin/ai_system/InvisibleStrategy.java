package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;

import java.io.IOException;

public class InvisibleStrategy implements Strategy {

    private final Strategy subStrategy;
    private final int criticalDistance;
    private int distanceBetweenMobAndPlayer;

    public InvisibleStrategy(Strategy subStrategy, int criticalDistance) {
        this.subStrategy = subStrategy;
        this.criticalDistance = criticalDistance;
        this.distanceBetweenMobAndPlayer = -1;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        distanceBetweenMobAndPlayer = map.findPlayerCoordinate().getDistanceBetween(mobAndCoord.getPosition());
        return subStrategy != null ? subStrategy.getNextAction(mobAndCoord, map) : Action.DoNothing;
    }

    @Override
    public char getView() {
        return distanceBetweenMobAndPlayer != -1 && distanceBetweenMobAndPlayer <= criticalDistance ? 0 : ' ';
    }

    @Override
    public TextColor getForeground() {
        return subStrategy != null ? subStrategy.getForeground() : TextColor.ANSI.WHITE;
    }
}
