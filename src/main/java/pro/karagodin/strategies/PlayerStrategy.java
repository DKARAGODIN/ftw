package pro.karagodin.strategies;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.actions.Action;
import pro.karagodin.actions.Direction;
import pro.karagodin.actions.DoNothingAction;
import pro.karagodin.actions.InteractWithItemOnFloorAction;
import pro.karagodin.actions.MoveAction;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.LootItem;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

/**
 * Implements interaction between Player and Character
 */
public class PlayerStrategy extends PrimitiveStrategy {

    private final Printer printer;

    public PlayerStrategy(Printer printer) {
        this.printer = printer;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) throws IOException {
        Player player = (Player) mobAndCoord.getMob();
        char key = getPressedKey();
        if (player.isInventoryMode()) {
            Coordinate oldInventoryCoord = player.getInventory().getCoordinate();
            Coordinate newInventoryCoord;
            switch (key) {
                case '^':
                    newInventoryCoord = oldInventoryCoord.withY(y -> y == 0 ? 6 : y - 1);
                    printer.moveInventoryCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case 'v':
                    newInventoryCoord = oldInventoryCoord.withY(y -> y == 6 ? 0 : y + 1);
                    printer.moveInventoryCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case '<':
                    newInventoryCoord = oldInventoryCoord.withX(x -> x == 0 ? 4 : x - 1);
                    printer.moveInventoryCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case '>':
                    newInventoryCoord = oldInventoryCoord.withX(x -> x == 4 ? 0 : x + 1);
                    printer.moveInventoryCellFocus(newInventoryCoord, oldInventoryCoord);
                    player.getInventory().setCoordinate(newInventoryCoord);
                    break;
                case 'i':
                    player.setInventoryMode(false);
                    printer.moveInventoryCellFocus(null, player.getInventory().getCoordinate());
                    break;
                case ' ':
                    LootItem moved = player.moveItem();
                    if (moved != null) {
                        printer.moveInventoryItems();
                        player.applyMovedLootItem(moved);
                        printer.refreshHeroStats();
                    }
                    break;
                default:
                    return new DoNothingAction();
            }
            return new DoNothingAction();
        } else {
            switch (key) {
                case '^':
                    return new MoveAction(Direction.Up);
                case 'v':
                    return new MoveAction(Direction.Down);
                case '<':
                    return new MoveAction(Direction.Left);
                case '>':
                    return new MoveAction(Direction.Right);
                case 'i':
                    player.setInventoryMode(true);
                    printer.moveInventoryCellFocus(new Coordinate(0, 0), null);
                    player.getInventory().setCoordinates(0, 0);
                    return new DoNothingAction();
                case 'q':
                    player.quitFromGame();
                    return new DoNothingAction();
                case ' ':
                    return new InteractWithItemOnFloorAction();
                default:
                    return new DoNothingAction();
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

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.RED;
    }

    @Override
    public Strategy cloneStrategy() {
        throw new UnsupportedOperationException();
    }
}
