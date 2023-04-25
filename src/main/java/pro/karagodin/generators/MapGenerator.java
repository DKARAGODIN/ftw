package pro.karagodin.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Item;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.models.Wall;

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
        for (int i = 0; i < amount;) {
            Coordinate cellPosition = getFreeCellPosition(map);
            int length = RANDOM.nextInt(10) + 1;
            boolean toRight = RANDOM.nextBoolean();
            if ((toRight && cellPosition.getX() + length >= map.getWidth()) || (!toRight && cellPosition.getY() + length >= map.getHeight()))
                continue;
            for (int j = 0; j < length; j++) {
                if (map.getCell(cellPosition).getWall() == null) {
                    map.getCell(cellPosition).setWall(new Wall());
                    i++;
                }
                if (toRight) {
                    cellPosition.setX(cellPosition.getX() + 1);
                } else {
                    cellPosition.setY(cellPosition.getY() + 1);
                }
            }
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
        placeWalls(map, 300);
        var items = new ArrayList<Item>();
        items.add(ItemGenerator.generateDefence(level));
        items.add(ItemGenerator.generateHealth(level));
        placeItems(map, items);
        placePlayer(map, player);
        return map;
    }
}
