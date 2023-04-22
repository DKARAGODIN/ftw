package pro.karagodin.game_engine;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.output.Printer;

import java.io.IOException;

public class Stage {

    private Printer printer;

    public Stage(Printer printer) {
        this.printer = printer;
    }

    public void start() throws IOException {
        //printer.printStage(stage);
        while (true) {
            KeyStroke key = printer.pressedKey();

        }
    }

}
