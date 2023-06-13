package pro.karagodin.generators;

import static pro.karagodin.generators.MapFiller.createRandomMob;
import static pro.karagodin.generators.MapFiller.placeBorders;
import static pro.karagodin.generators.MapFiller.randomPlaceItems;
import static pro.karagodin.generators.MapFiller.randomPlaceMob;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Hole;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.models.Wall;

public class MapLoader {
    private final String filename;
    private Coordinate playerCrd;
    private Coordinate holeCrd;

    public MapLoader(String filename) {
        this.filename = filename;
    }

    private Map loadMapFromFile() {
        var sc = new Scanner(getClass().getResourceAsStream(filename), StandardCharsets.UTF_8);
        int height = sc.nextInt();
        int weight = sc.nextInt();
        var map = new Map(height, weight);

        for (int y = 0; y < height; y++) {
            String line = sc.next();
            for (int x = 0; x < weight; x++) {
                if (line.charAt(x) == '#')
                    map.getCell(new Coordinate(x,y)).setWall(new Wall());
                else if (line.charAt(x) == 'P')
                    playerCrd = new Coordinate(x, y);
                else if (line.charAt(x) == 'H')
                    holeCrd = new Coordinate(x, y);
            }
        }
        return map;
    }

    public Map loadMap(Player player, MobFactory mobFactory, int stage) {
        var map = loadMapFromFile();
        placeBorders(map);
        var items = ItemGenerator.generateItems(stage);
        randomPlaceItems(map, items);
        map.getCell(playerCrd).setUnit(player);
        map.getCell(holeCrd).setItem(new Hole());

        for (int i = 0; i < stage; i++) {
            randomPlaceMob(map, createRandomMob(mobFactory));
        }
        return map;
    }
}
