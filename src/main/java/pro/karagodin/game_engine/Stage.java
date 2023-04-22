package pro.karagodin.game_engine;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.output.Printer;

import java.io.IOException;

public class Stage {

    private Printer printer;
    private Judge judge = new Judge();

    public Stage(Printer printer) {
        this.printer = printer;
    }

    public void start() throws IOException {
        while (true) {
            KeyStroke key = printer.pressedKey();
            if (key != null) {
                judge.doPlayerAction(key);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

}
