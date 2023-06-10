package pro.karagodin.ai_system;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class AttackAroundStrategy implements Strategy {
    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        return map
                .getAllDirections()
                .stream()
                .filter(direction -> {
                    Coordinate newCoord = direction.getOperator().apply(mobAndCoord.getPosition());
                    return newCoord != null && map.getCell(newCoord).getUnit() instanceof Player;
                })
                .findAny()
                .map(Map.MapDirection::getAction)
                .orElse(Action.DoNothing);
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.RED_BRIGHT;
    }
}
