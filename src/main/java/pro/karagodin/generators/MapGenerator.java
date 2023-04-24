package pro.karagodin.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Item;
import pro.karagodin.models.Map;

public class MapGenerator {

    private static final Random RANDOM = new Random();

    public static void placeItems(Map map, List<Item> items) {
        int maxW = map.getWidth(), maxH = map.getHeight();

        for (int i = 0; i < items.size(); i++) {
            int x  = RANDOM.nextInt(0, maxW);
            int y  = RANDOM.nextInt(0, maxH);
            var cell = map.getCell(new Coordinate(x, y));
            if (cell.getWall() != null || cell.getFloor().getItem() != null) {
                i--;
                continue;
            }
            cell.getFloor().setItem(items.get(i));
        }
    }

    public static Map genEmptytMap() {
        return new Map(60, 195);
    }

    public static Map genDefaultMap(int level) {
        var map = new Map(60, 195);
        var items = new ArrayList<Item>();
        items.add(ItemGenerator.generateDefence(level));
        items.add(ItemGenerator.generateHealth(level));
        placeItems(map, items);
        return map;
    }
}
