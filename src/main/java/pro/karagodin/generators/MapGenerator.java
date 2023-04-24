package pro.karagodin.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.*;

public class MapGenerator {

    private static final Random RANDOM = new Random();

    public static Coordinate getFreeCellPosition(Map map) {
        Coordinate position;
        while (true) {
            position = new Coordinate(RANDOM.nextInt(0, map.getWidth()), RANDOM.nextInt(0, map.getHeight()));
            if (map.getCell(position).getWall() == null && ! map.getCell(position).getFloor().hasItem()) {
                return position;
            }
        }
    }

    public static void placeItems(Map map, List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            map.getCell(getFreeCellPosition(map)).getFloor().setItem(items.get(i));
        }
    }

    public static void placeWalls(Map map, int amount) {
        for (int i = 0; i < amount; i++) {
            map.getCell(getFreeCellPosition(map)).setWall(new Wall());
        }
    }

    public static void placePlayer(Map map, Player player) {
        map.getCell(getFreeCellPosition(map)).setUnit(player);
    }

    public static Map genEmptytMap() {
        return new Map(60, 195);
    }

    public static Map genDefaultMap(int level, Player player) {
        var map = new Map(60, 195);
        placeWalls(map, 4000);
        var items = new ArrayList<Item>();
        items.add(ItemGenerator.generateDefence(level));
        items.add(ItemGenerator.generateHealth(level));
        placeItems(map, items);
        placePlayer(map, player);
        return map;
    }
}
