package pro.karagodin.game_engine;

import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeInterval;

import java.io.IOException;

public class Game {

    private Printer printer;
    private Stage stage;
    private Player player;

    public Game() {
        printer = new Printer();
        player = new Player(100, 100, new TimeInterval(200), printer);
    }

    public void start() throws IOException {
        printer.init(player.getInventory()); // aka menu

        printer.printGUI();
        printer.printHeroInfo();

        while (true) {
            stage = new Stage(printer, player); // must be generator

            boolean isGameOver = stage.start();
            if (isGameOver) {
                printer.quitGame();
                break;
            }
        }
    }
}
