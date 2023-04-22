package pro.karagodin.game_engine;

import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

import java.io.IOException;

public class Game {

    private Printer printer;
    private Stage stage;
    private Player player;

    public Game() {
        printer = new Printer();
        player = new Player(100, 100);
    }

    public void start() throws IOException {
        printer.init(); // aka menu

        printer.printGUI();
        printer.printHeroInfo();

        while (true) {
            stage = new Stage(printer, player); // must be generator

            Stage.StageEnd stageEnd = stage.start();
            if (stageEnd.quitGame) {
                printer.quitGame();
                break;
            }
        }
    }
}
