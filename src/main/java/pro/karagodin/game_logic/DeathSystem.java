package pro.karagodin.game_logic;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Map;

public class DeathSystem {

    public void killMob (Coordinate mobPosition, Map map) {
        if (!map.getCell(mobPosition).getUnit().isKilled()) {
            throw new RuntimeException("DeathSystem can't kill alive mob");
        }
        map.getCell(mobPosition).setUnit(null);
    }
}
