package pro.karagodin.generators;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.LowerItem;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Wall;

import java.util.List;
import java.util.Random;

/**
 * Some static methods to fill map
 */
public class MapFiller {
    private static final Random RANDOM = new Random();

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

    /**
     * Puts randomly smallThings on map
     */
    public static void randomPlaceItems(Map map, List<? extends LowerItem> smallThings) {
        for (LowerItem smallThing : smallThings) {
            randomPlaceItem(map, smallThing);
        }
    }

    /**
     * Create randomly mob from mobFactory
     */
    public static Mob createRandomMob(MobFactory mobFactory) {
        switch (RANDOM.nextInt(4)) {
            case 0 -> {
                return mobFactory.createPassiveMob();
            }
            case 1 -> {
                return mobFactory.createPassiveAggressiveMob();
            }
            case 2 -> {
                return mobFactory.createAggressiveMob();
            }
            default -> {
                return mobFactory.createCowardMob();
            }
        }
    }

    /**
     * Puts randomly smallThing on map
     */
    public static void randomPlaceItem(Map map, LowerItem item) {
        map.getCell(getFreeCellPosition(map)).setItem(item);
    }


    /**
     * Place borders on map
     */
    public static void placeBorders(Map map) {
        placeHorizontalWall(map, new Coordinate(0, 0), map.getWidth());
        placeHorizontalWall(map, new Coordinate(0, map.getHeight() - 1), map.getWidth());
        placeVerticalWall(map, new Coordinate(0, 0), map.getHeight());
        placeVerticalWall(map, new Coordinate(map.getWidth() - 1, 0), map.getHeight());
    }

    /**
     * Find random not used cell
     */
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
     * Puts mobs on map at random position
     */
    public static void randomPlaceMob(Map map, Mob mob) {
        map.getCell(getFreeCellPosition(map)).setUnit(mob);
    }
}
