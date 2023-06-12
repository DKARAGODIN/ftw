package pro.karagodin.generators;


import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public interface MapGenerator {
    Map createMap(Player player, MobFactory mobFactory, int stage, int height, int width);


}
