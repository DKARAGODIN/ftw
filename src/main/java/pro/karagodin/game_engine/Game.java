package pro.karagodin.game_engine;

import pro.karagodin.output.Printer;

import java.io.IOException;

public class Game {

    private Printer printer;
    private Stage stage;

    public Game() {
        printer = new Printer();
    }

    public void start() throws IOException {
        printer.init(); // aka menu

        while (true) {
            stage = new Stage(printer); // must be generator
            stage.start();
        }
    }
}
