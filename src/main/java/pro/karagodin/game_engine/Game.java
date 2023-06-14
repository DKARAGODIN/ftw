package pro.karagodin.game_engine;

import static pro.karagodin.models.Player.BASE_MAX_HEALTH;
import static pro.karagodin.models.Player.BASE_PACE;

import java.io.IOException;

import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

public class Game {

    private Printer printer;
    private Stage stage;
    private Player player;

    public Game() {
        printer = new Printer();
        player = new Player(BASE_MAX_HEALTH, BASE_MAX_HEALTH, BASE_PACE, printer);
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
                boolean gameOver = printer.gameOver();
                if (gameOver) {
                    printer.quitGame();
                    break;
                } else {
                    player.refresh(printer);
                    printer.cleanInventory();
                    currentStage = 0;
                }
            }
        }
    }
}
