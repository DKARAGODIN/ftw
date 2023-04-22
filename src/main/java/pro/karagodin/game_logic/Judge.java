package pro.karagodin.game_logic;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class Judge {
    public GameDiff doPlayerAction(KeyStroke key, MobWithPosition playerAndCoord, Map map) {
        MapDiff diff = new MapDiff();
        Player player = (Player)playerAndCoord.getMob();
        Coordinate newPlayerCoord = playerAndCoord.getPosition();
        switch (key.getKeyType()) {
            case ArrowLeft:
                if (player.isInventoryMode()) {

                } else {
                    if (playerAndCoord.getPosition().getX() > 0) {
                        map.getCell(playerAndCoord.getPosition()).setUnit(null);
                        diff.addNewCoordinate(playerAndCoord.getPosition());
                        newPlayerCoord = new Coordinate(playerAndCoord.getPosition().getX() - 1, playerAndCoord.getPosition().getY());
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowRight:
                if (player.isInventoryMode()) {
                } else {
                    if (playerAndCoord.getPosition().getX() < map.getWidth() - 1) {
                        map.getCell(playerAndCoord.getPosition()).setUnit(null);
                        diff.addNewCoordinate(playerAndCoord.getPosition());
                        newPlayerCoord = new Coordinate(playerAndCoord.getPosition().getX() + 1, playerAndCoord.getPosition().getY());
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowDown:
                if (player.isInventoryMode()) {
                } else {
                    if (playerAndCoord.getPosition().getY() < map.getHeight() - 1) {
                        map.getCell(playerAndCoord.getPosition()).setUnit(null);
                        diff.addNewCoordinate(playerAndCoord.getPosition());
                        newPlayerCoord = new Coordinate(playerAndCoord.getPosition().getX(), playerAndCoord.getPosition().getY() + 1);
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case ArrowUp:
                if (player.isInventoryMode()) {
                } else {
                    if (playerAndCoord.getPosition().getY() > 0) {
                        map.getCell(playerAndCoord.getPosition()).setUnit(null);
                        diff.addNewCoordinate(playerAndCoord.getPosition());
                        newPlayerCoord = new Coordinate(playerAndCoord.getPosition().getX(), playerAndCoord.getPosition().getY() - 1);
                        map.getCell(newPlayerCoord).setUnit(player);
                        diff.addNewCoordinate(newPlayerCoord);
                    }
                }
                break;
            case Character: {
                switch (key.getCharacter()) {
                    case 'q':
                    case 'i':
                        player.setInventoryMode(!player.isInventoryMode());
                        if (player.isInventoryMode()) {
                            player.getInventory().setCoordinates(0, 0);
                        }
                    break;
                }
            }
            break;
        }
        return new GameDiff(diff, new MobWithPosition(map, newPlayerCoord));
    }
}
