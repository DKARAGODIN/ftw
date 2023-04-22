package pro.karagodin.ai_system;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

import java.io.IOException;

public class PlayerStrategy implements Strategy {

    private final Printer printer;

    public PlayerStrategy(Printer printer) {
        this.printer = printer;
    }

    @Override
    public Action getNextAction(MobWithPosition mobAndCoord, Map map) {
        Player player = (Player) mobAndCoord.getMob();
        char key = getPressedKey();
        if (player.isInventoryMode()) {
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

    public char getPressedKey() {
        try {
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
        } catch (IOException e) {
            return 0;
        }
    }
}
