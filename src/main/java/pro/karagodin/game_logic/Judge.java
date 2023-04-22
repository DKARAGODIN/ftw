package pro.karagodin.game_logic;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class Judge {
    /**
     *
     * @param key - not null;
     */
    public GameDiff doPlayerAction(KeyStroke key, Coordinate playerCoord, Map map) {
        Player player = (Player)map.getCell(playerCoord).getUnit();
        MapDiff diff = new MapDiff();
        Coordinate newPlayerCoord = new Coordinate(playerCoord.getX(), playerCoord.getY());
        switch (key.getKeyType()) {
            case ArrowLeft:
                if (player.isInventoryMode()) {
                } else {
                    if (playerCoord.getY() > 0) {
                        map.getCell(playerCoord).setUnit(null);
                        diff.addNewCoordinate(playerCoord);
                        newPlayerCoord = new Coordinate(playerCoord.getX(), playerCoord.getY() - 1);
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowRight:
                if (player.isInventoryMode()) {
                } else {
                    if (playerCoord.getY() < map.getWidth() - 1) {
                        map.getCell(playerCoord).setUnit(null);
                        diff.addNewCoordinate(playerCoord);
                        newPlayerCoord = new Coordinate(playerCoord.getX(), playerCoord.getY() + 1);
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowDown:
                if (player.isInventoryMode()) {
                } else {
                    if (playerCoord.getX() > map.getHeight() - 1) {
                        map.getCell(playerCoord).setUnit(null);
                        diff.addNewCoordinate(playerCoord);
                        newPlayerCoord = new Coordinate(playerCoord.getX() + 1, playerCoord.getY());
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowUp:
                if (player.isInventoryMode()) {
                } else {
                    if (playerCoord.getX() > 0) {
                        map.getCell(playerCoord).setUnit(null);
                        diff.addNewCoordinate(playerCoord);
                        newPlayerCoord = new Coordinate(playerCoord.getX() - 1, playerCoord.getY());
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case Character: {
                switch (key.getCharacter()) {
                    case 'q':
                    case 'i':
                        player.setInventoryMode(true);
                    break;
                }
            }
            break;
        }
        return new GameDiff(diff, newPlayerCoord);
    }
}
