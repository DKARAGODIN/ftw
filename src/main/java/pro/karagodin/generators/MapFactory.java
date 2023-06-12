package pro.karagodin.generators;


import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public interface MapFactory {


    Map createMap(Player player, MobFactory mobFactory, int stage);
}
