package pro.karagodin.generators;

import java.util.List;
import java.util.Random;

import pro.karagodin.ai_system.PursuitStrategy;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.*;
import pro.karagodin.time.TimeMoment;

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

    /**
     * Puts smallThings on map
     * @param map
     * @param smallThings
     */
    public static void placeSmallThings(Map map, List<SmallThing> smallThings) {
        for (int i = 0; i < smallThings.size(); i++) {
            map.getCell(getFreeCellPosition(map)).getFloor().setItem(smallThings.get(i));
        }
    }

    /**
     * Puts walls on map
     * @param map
     * @param amount
     */
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

    /**
     * Puts mobs on map
     * @param map
     * @param mob
     */
    public static void placeMob(Map map, Mob mob) {
        map.getCell(getFreeCellPosition(map)).setUnit(mob);
    }

    public static void placeItem(Map map, LowerItem item) {
        map.getCell(getFreeCellPosition(map)).getFloor().setItem(item);
    }

    public static Map genEmptytMap() {
        return new Map(60, 195);
    }

    /**
     * Default map for testing and first stage play
     * @param stage
     * @param player
     * @return
     */
    public static Map generate(int stage, Player player) {
        var map = new Map(60, 195);
        placeWalls(map, stage > 10 ? 1000 : stage * 70 + 300);
        var items = ItemGenerator.generateItems(stage);
        placeSmallThings(map, items);
        placeMob(map, player);
        for (int i = 0; i < stage; i++) {
            placeMob(map, new Mob(
                    100,
                    100,
                    10,
                    3,
                    4,
                    6,
                    new TimeMoment(stage > 10 ? 10 : 1010 - stage * 100),
                    new PursuitStrategy(0.7)));
        }
        placeItem(map, new Hole());
        return map;
    }
}
