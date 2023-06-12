package pro.karagodin.generators;

import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class MapLoader implements MapFactory {
    private final String filename;

    public MapLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public Map createMap(Player player, MobFactory mobFactory) {
        return null;
    }
}
