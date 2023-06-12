package pro.karagodin.game_engine;

import java.io.IOException;

import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeMoment;

public class Game {

    private Printer printer;
    private Stage stage;
    private Player player;

    public Game() {
        printer = new Printer();
        player = new Player(100, 100, new TimeMoment(200L), printer);
    }

    /**
     * Main game loop
     * @throws IOException
     */
    public void start() throws IOException {
        printer.init(player); // aka menu

        printer.printGUI();

        for (int currentStage = 1;; currentStage++) {
            stage = new Stage(printer, player, currentStage); // must be generator

            printer.refreshCurrentStageNumber(currentStage);
            printer.refreshHeroStats();

            boolean isGameOver = stage.start();
            if (isGameOver) {
                printer.quitGame();
                break;
            }
        }
    }
}
