package pro.karagodin.game_logic;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class Judge {
    public GameDiff doPlayerAction(KeyStroke key, Coordinate playerCoord, Map map) {
        Player player = (Player)map.getCell(playerCoord).getUnit();
        MapDiff diff = new MapDiff();
        Coordinate newPlayerCoord = new Coordinate(playerCoord.getX(), playerCoord.getY());
        switch (key.getKeyType()) {
            case ArrowLeft:
                if (player.isInventoryMode()) {
                    GameDiff gameDiff = new GameDiff(diff, newPlayerCoord);
                    gameDiff.setInventoryMode(true);
                    int x = player.getInventory().getX();
                    int y = player.getInventory().getY();
                    int new_x = x == 0 ? 4 : x - 1;
                    int new_y = y;
                    gameDiff.setInventory_prev_x(x);
                    gameDiff.setInventory_prev_y(y);
                    gameDiff.setInventory_x(new_x);
                    gameDiff.setInventory_y(new_y);
                    player.getInventory().setX(new_x);
                    player.getInventory().setY(new_y);
                    return gameDiff;
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
            case ArrowRight:
                if (player.isInventoryMode()) {
                    GameDiff gameDiff = new GameDiff(diff, newPlayerCoord);
                    gameDiff.setInventoryMode(true);
                    int x = player.getInventory().getX();
                    int y = player.getInventory().getY();
                    int new_x = x == 4 ? 0 : x + 1;
                    int new_y = y;
                    gameDiff.setInventory_prev_x(x);
                    gameDiff.setInventory_prev_y(y);
                    gameDiff.setInventory_x(new_x);
                    gameDiff.setInventory_y(new_y);
                    player.getInventory().setX(new_x);
                    player.getInventory().setY(new_y);
                    return gameDiff;
                } else {
                    if (playerCoord.getX() < map.getWidth() - 1) {
                        map.getCell(playerCoord).setUnit(null);
                        diff.addNewCoordinate(playerCoord);
                        newPlayerCoord = new Coordinate(playerCoord.getX() + 1, playerCoord.getY());
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowDown:
                if (player.isInventoryMode()) {
                    GameDiff gameDiff = new GameDiff(diff, newPlayerCoord);
                    gameDiff.setInventoryMode(true);
                    int x = player.getInventory().getX();
                    int y = player.getInventory().getY();
                    int new_x = x;
                    int new_y = y == 6 ? 0 : y + 1;
                    gameDiff.setInventory_prev_x(x);
                    gameDiff.setInventory_prev_y(y);
                    gameDiff.setInventory_x(new_x);
                    gameDiff.setInventory_y(new_y);
                    player.getInventory().setX(new_x);
                    player.getInventory().setY(new_y);
                    return gameDiff;
                } else {
                    if (playerCoord.getY() < map.getHeight() - 1) {
                        map.getCell(playerCoord).setUnit(null);
                        diff.addNewCoordinate(playerCoord);
                        newPlayerCoord = new Coordinate(playerCoord.getX(), playerCoord.getY() + 1);
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowUp:
                if (player.isInventoryMode()) {
                    GameDiff gameDiff = new GameDiff(diff, newPlayerCoord);
                    gameDiff.setInventoryMode(true);
                    int x = player.getInventory().getX();
                    int y = player.getInventory().getY();
                    int new_x = x;
                    int new_y = y == 0 ? 6 : y - 1;
                    gameDiff.setInventory_prev_x(x);
                    gameDiff.setInventory_prev_y(y);
                    gameDiff.setInventory_x(new_x);
                    gameDiff.setInventory_y(new_y);
                    player.getInventory().setX(new_x);
                    player.getInventory().setY(new_y);
                    return gameDiff;
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
            case Character: {
                switch (key.getCharacter()) {
                    case 'q': {
                        GameDiff gameDiff = new GameDiff(diff, newPlayerCoord);
                        gameDiff.setQuitGame(true);
                        return gameDiff;
                    }
                    case 'i':
                        player.setInventoryMode(!player.isInventoryMode());
                        if (player.isInventoryMode()) {
                            //Enable inventory Mode
                            player.getInventory().setCoordinates(0, 0);
                            GameDiff gameDiff = new GameDiff(diff, newPlayerCoord);
                            gameDiff.setInventoryMode(true);

                            return gameDiff;
                        } else {
                            //Disable inventory Mode
                            GameDiff gameDiff = new GameDiff(diff, newPlayerCoord);
                            gameDiff.setInventoryMode(true);
                            gameDiff.setExitInventoryMode(true);

                            int x = player.getInventory().getX();
                            int y = player.getInventory().getY();
                            gameDiff.setInventory_x(x);
                            gameDiff.setInventory_y(y);
                            return gameDiff;
                        }
                }
            }
            break;
        }
        return new GameDiff(diff, newPlayerCoord);
    }
}
