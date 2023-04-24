package pro.karagodin.ai_system;

import java.io.IOException;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

public class PlayerStrategy implements Strategy {

    private final Printer printer;

    public PlayerStrategy(Printer printer) {
        this.printer = printer;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        Player player = (Player) mobAndCoord.getMob();
        char key = getPressedKey();
        if (player.isInventoryMode()) {
            Coordinate oldInventoryCoord, newInventoryCoord;
            switch (key) {
                case '^':
                    oldInventoryCoord = player.getInventory().getCoordinate();
                    newInventoryCoord = oldInventoryCoord.withY(y -> y == 0 ? 6 : y - 1);
                    printer.moveCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case 'v':
                    oldInventoryCoord = player.getInventory().getCoordinate();
                    newInventoryCoord = oldInventoryCoord.withY(y -> y == 6 ? 0 : y + 1);
                    printer.moveCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case '<':
                    oldInventoryCoord = player.getInventory().getCoordinate();
                    newInventoryCoord = oldInventoryCoord.withX(x -> x == 0 ? 4 : x - 1);
                    printer.moveCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case '>':
                    oldInventoryCoord = player.getInventory().getCoordinate();
                    newInventoryCoord = oldInventoryCoord.withX(x -> x == 4 ? 0 : x + 1);
                    printer.moveCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case 'i':
                    player.setInventoryMode(false);
                    printer.moveCellFocus(null, player.getInventory().getCoordinate());
                default:
                    return Action.DoNothing;
            }
            return Action.DoNothing;
        } else {
            switch (key) {
                case '^':
                    return Action.MoveUp;
                case 'v':
                    return Action.MoveDown;
                case '<':
                    return Action.MoveLeft;
                case '>':
                    return Action.MoveRight;
                case 'i':
                    player.setInventoryMode(true);
                    printer.moveCellFocus(new Coordinate(0, 0), null);
                    player.getInventory().setCoordinates(0, 0);
                    return Action.DoNothing;
                case 'q':
                    player.quitFromGame();
                    return Action.DoNothing;
                case ' ':
                    return Action.InteractWithObjectOnFloor;
                default:
                    return Action.DoNothing;
            }
        }
    }

    public char getPressedKey() throws IOException {
        KeyStroke key = printer.pressedKey();
        if (key == null) {
            return 0;
        }
        KeyStroke key2 = printer.pressedKey();
        while (key2 != null) {
            key = key2;
            key2 = printer.pressedKey();
        }
        switch (key.getKeyType()) {
            case ArrowDown:
                return 'v';
            case ArrowUp:
                return '^';
            case ArrowLeft:
                return '<';
            case ArrowRight:
                return '>';
            case Character:
                return key.getCharacter();
            default:
                return 0;
        }
    }
}
