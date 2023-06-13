package pro.karagodin.generators;

import static pro.karagodin.generators.MapFiller.createRandomMob;
import static pro.karagodin.generators.MapFiller.getFreeCellPosition;
import static pro.karagodin.generators.MapFiller.placeBorders;
import static pro.karagodin.generators.MapFiller.randomPlaceItem;
import static pro.karagodin.generators.MapFiller.randomPlaceItems;
import static pro.karagodin.generators.MapFiller.randomPlaceMob;

import java.util.Random;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Hole;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.models.Wall;

public class WallsMapGenerator implements MapGenerator {

    private static final Random RANDOM = new Random();

    private int getWallsAmount(Map map, int stage) {
        int defaultAmount = stage > 10 ? 1000 : stage * 70 + 300;
        int maxSize = 60 + 195;
        double sizeCoef = (double) (map.getHeight() + map.getWidth()) / maxSize;
        return (int) (defaultAmount * sizeCoef);
    }

    /**
     * Puts randomly walls on map
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
     * Default map for testing and first stage play
     *
     * @param player
     * @return
     */
    @Override
    public Map createMap(Player player, MobFactory mobFactory, int stage, int height, int width) {
        var map = new Map(height, width);
        placeBorders(map);
        placeWalls(map, getWallsAmount(map, stage));
        var items = ItemGenerator.generateItems(stage);
        randomPlaceItems(map, items);
        randomPlaceMob(map, player);
        for (int i = 0; i < stage; i++) {
            randomPlaceMob(map, createRandomMob(mobFactory));
        }
        randomPlaceItem(map, new Hole());
        return map;
    }
}
