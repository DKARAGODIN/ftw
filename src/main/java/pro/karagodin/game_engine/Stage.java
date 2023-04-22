package pro.karagodin.game_engine;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_logic.GameJudge;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

import java.io.IOException;

public class Stage {

    private Printer printer;
    private Player player;
    private GameJudge gameJudge = new GameJudge();

    public Stage(Printer printer, Player player) {
        this.printer = printer;
        this.player = player;
    }

    public void start() throws IOException {
        while (true) {
            KeyStroke key = printer.pressedKey();
            if (key != null) {
                gameJudge.doPlayerAction(key, player);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

}
