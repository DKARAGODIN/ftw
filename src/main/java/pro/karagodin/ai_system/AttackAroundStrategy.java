package pro.karagodin.ai_system;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class AttackAroundStrategy implements Strategy {

    protected final Strategy subStrategy;

    public AttackAroundStrategy(Strategy subStrategy) {
        this.subStrategy = subStrategy;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        return map
                .getAllDirections()
                .stream()
                .filter(direction -> {
                    Coordinate newCoord = direction.getOperator().apply(mobAndCoord.getPosition());
                    return newCoord != null && map.getCell(newCoord).getUnit() instanceof Player;
                })
                .findAny()
                .map(Map.Direction::getAction)
                .orElse(subStrategy.getNextAction(mobAndCoord, map));
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.RED_BRIGHT;
    }

    @Override
    public Strategy cloneStrategy() {
        return new AttackAroundStrategy(subStrategy.cloneStrategy());
    }
}
