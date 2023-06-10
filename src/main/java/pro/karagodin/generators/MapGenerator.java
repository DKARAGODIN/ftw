package pro.karagodin.generators;

import java.util.List;
import java.util.Random;

import pro.karagodin.ai_system.StatueStrategy;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Item;
import pro.karagodin.models.Map;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Player;
import pro.karagodin.models.Wall;
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
     * Puts items on map
     * @param map
     * @param items
     */
    public static void placeItems(Map map, List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            map.getCell(getFreeCellPosition(map)).getFloor().setItem(items.get(i));
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

    public static Map genEmptytMap() {
        return new Map(60, 195);
    }

    /**
     * Default map for testing and first stage play
     * @param stage
     * @param player
     * @return
     */
    public static Map genDefaultMap(int stage, Player player) {
        var map = new Map(60, 195);
        placeWalls(map, 300);
        var items = ItemGenerator.generateItems(stage);
        placeItems(map, items);
        placeMob(map, player);
        Mob m1 = new Mob(1, 100, new TimeMoment(1000), new StatueStrategy());
        m1.setMinDamage(0); m1.setMaxDamage(0);
        placeMob(map, m1);
        Mob m2 = new Mob(1, 100, new TimeMoment(1000), new StatueStrategy());
        m2.setMinDamage(0); m2.setMaxDamage(0);
        placeMob(map, m2);
        Mob m3 = new Mob(1, 100, new TimeMoment(1000), new StatueStrategy());
        m3.setMinDamage(0); m3.setMaxDamage(0);
        placeMob(map, m3);
        Mob m4 = new Mob(1, 100, new TimeMoment(1000), new StatueStrategy());
        m4.setMinDamage(0); m4.setMaxDamage(0);
        placeMob(map, m4);
        Mob m5 = new Mob(1, 100, new TimeMoment(1000), new StatueStrategy());
        m5.setMinDamage(0); m5.setMaxDamage(0);
        placeMob(map, m5);


        //placeMob(map, new Mob(100, 100, new TimeMoment(50), new RoamStrategy(), new ArrayList<>(List.of(new ConfusedEffect(new TimeMoment(3000))))));
        return map;
    }
}
