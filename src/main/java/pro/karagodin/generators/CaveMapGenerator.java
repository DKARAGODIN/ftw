package pro.karagodin.generators;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.*;

import java.util.Random;

import static pro.karagodin.generators.MapFiller.*;

public class CaveMapGenerator implements MapGenerator {
    private static final Random RANDOM = new Random();
    private static final int MIN_FLOORS_COUNT = 100;
    private int floorsCount = 0;
    private void fillMapWithWalls(Map map) {
        for (int y = 0; y < map.getHeight(); y++)
            for (int x = 0; x < map.getWidth(); x++)
                map.getCell(new Coordinate(x, y)).setWall(new Wall());
    }

    private boolean isSkipCell(Cell cell) {
        return cell.getWall() == null && RANDOM.nextInt(7) > 0;
    }

    private void tryMovePos(Coordinate crd, Map map, int newX, int newY) {
        int height = map.getHeight();
        int width = map.getWidth();
        if (newX < 1 || newY < 1 || newX >= width-1 || newY >= height-1 || isSkipCell(map.getCell(newX, newY)))
            return;
        crd.setX(newX);
        crd.setY(newY);
    }

    private void randMovePos(Map map, Coordinate crd) {
        int x = crd.getX();
        int y = crd.getY();

        switch (RANDOM.nextInt(4)) {
            case 0 -> tryMovePos(crd, map, x - 1, y);
            case 1 -> tryMovePos(crd, map, x + 1, y);
            case 2 -> tryMovePos(crd, map, x, y - 1);
            case 3 -> tryMovePos(crd, map, x, y + 1);
        }

    }

    private void randWalk(Map map, Coordinate startPos, int steps) {
        var curPos = new Coordinate(startPos.getX(), startPos.getY());
        for (int i = 0; i < steps; i++) {
            if(map.getCell(curPos).getWall() != null)
                floorsCount++;
            map.getCell(curPos).setWall(null);
            randMovePos(map, curPos);
        }
    }

    private void runRandomWalkers(Map map, Coordinate startPos, int walkers, int height, int width) {
        for (int i = 0; i < walkers || floorsCount < MIN_FLOORS_COUNT; i++)
            randWalk(map, startPos, RANDOM.nextInt(15 * (height + width)));
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
        fillMapWithWalls(map);
        runRandomWalkers(map, new Coordinate(width / 2, height / 2), RANDOM.nextInt(25), height, width);
        randomPlaceItems(map, ItemGenerator.generateItems(stage));
        randomPlaceMob(map, player);
        for (int i = 0; i < stage; i++) {
            randomPlaceMob(map, createRandomMob(mobFactory));
        }
        randomPlaceItem(map, new Hole());
        return map;
    }
}
