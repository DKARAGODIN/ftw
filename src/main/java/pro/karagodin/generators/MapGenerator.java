package pro.karagodin.generators;

import pro.karagodin.ai_system.BifurcateStrategy;
import pro.karagodin.ai_system.RoamStrategy;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.*;
import pro.karagodin.time.TimeMoment;

import java.util.List;
import java.util.Random;

public class MapGenerator implements MapFactory {

    private static final Random RANDOM = new Random();
    private int stage;
    private int height;
    private int width;

    public MapGenerator(int stage, int height, int width) {
        this.stage = stage;
        this.height = height;
        this.width = width;
    }


    private static void placeVerticalWall(Map map, Coordinate crd, int size) {
        for (int y = 0; y < size; y++) {
            var cell = map.getCell(new Coordinate(crd.getX(), y));
            cell.setWall(new Wall());
        }
    }

    private static void placeHorizontalWall(Map map, Coordinate crd, int size) {
        for (int x = 0; x < size; x++) {
            var cell = map.getCell(new Coordinate(x, crd.getY()));
            cell.setWall(new Wall());
        }
    }

    public static void placeBorders(Map map) {
        placeHorizontalWall(map, new Coordinate(0, 0), map.getWidth());
        placeHorizontalWall(map, new Coordinate(0, map.getHeight() - 1), map.getWidth());
        placeVerticalWall(map, new Coordinate(0, 0), map.getHeight());
        placeVerticalWall(map, new Coordinate(map.getWidth() - 1, 0), map.getHeight());
    }

    public static Coordinate getFreeCellPosition(Map map) {
        Coordinate position;
        while (true) {
            position = new Coordinate(RANDOM.nextInt(0, map.getWidth()), RANDOM.nextInt(0, map.getHeight()));
            if (map.getCell(position).getWall() == null && map.getCell(position).getItem() == null && map.getCell(position).getUnit() == null) {
                return position;
            }
        }
    }

    /**
     * Puts smallThings on map
     *
     * @param map
     * @param smallThings
     */
    public static void placeItems(Map map, List<? extends LowerItem> smallThings) {
        for (int i = 0; i < smallThings.size(); i++) {
            map.getCell(getFreeCellPosition(map)).setItem(smallThings.get(i));
        }
    }

    /**
     * Puts walls on map
     *
     * @param map
     * @param amount
     */
    public static void placeWalls(Map map, int amount) {
        for (int i = 0; i < amount; ) {
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
     *
     * @param map
     * @param mob
     */
    public static void placeMob(Map map, Mob mob) {
        map.getCell(getFreeCellPosition(map)).setUnit(mob);
    }

    public static void placeItem(Map map, LowerItem item) {
        map.getCell(getFreeCellPosition(map)).setItem(item);
    }


    public static int getRandomWallsAmount(Map map, int stage) {
        int defaultAmount = stage > 10 ? 1000 : stage * 70 + 300;
        int maxSize = 60 + 195;
        double sizeCoef = (double) (map.getHeight() + map.getWidth()) / maxSize;
        return (int) (defaultAmount * sizeCoef);
    }

    /**
     * Default map for testing and first stage play
     *
     * @param player
     * @return
     */


    @Override
    public Map createMap(Player player) {
        var map = new Map(height, width);
        placeBorders(map);
        placeWalls(map, getRandomWallsAmount(map, stage));
        var items = ItemGenerator.generateItems(stage);
        placeItems(map, items);
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
                    new BifurcateStrategy(new RoamStrategy(), 5),
                    'A',
                    List.of()));
        }
        placeItem(map, new Hole());
        return map;
    }
}
