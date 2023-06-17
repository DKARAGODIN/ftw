package pro.karagodin.output;

import java.io.IOException;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;


public class Printer {
    private static final int MAX_COL = 230;
    private static final int MAX_ROW = 60;
    private static final int GUI_CONTROLS_HORIZONTAL_LINE_ROW = MAX_ROW - 6;
    private static final int STATS_DESCRIPTION_COLUMN_OFFSET = 20;

    private static final int MAP_WIDTH = 195;
    private static final int MAP_HEIGHT = 60;

    private static final int GAME_OVER_RECTANGLE_ROW = 25;
    private static final int GAME_OVER_RECTANGLE_COL = 77;
    private static final int GAME_OVER_RECTANGLE_HEIGHT = 5;
    private static final int GAME_OVER_RECTANGLE_WIDTH = 40;


    public static final int GUI_INVENTORY_WIDTH = 34;

    private Screen screen;
    private InventoryPrinter inventoryPrinter;
    private PlayerStatsPrinter playerStatsPrinter;

    public int init(Player player) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        this.screen = screen;
        this.inventoryPrinter = new InventoryPrinter(screen, new Coordinate(MAX_COL - GUI_INVENTORY_WIDTH, 13), player.getInventory());
        this.playerStatsPrinter = new PlayerStatsPrinter(screen, new Coordinate(MAX_COL - GUI_INVENTORY_WIDTH, 3), player);
        screen.startScreen();

        return printWelcomeMessage(screen);
    }

    public void updateCoordinates(Map map, MapDiff diff) throws IOException {
        for (Coordinate coord : diff.getUpdatedCoordinatesInMap()) {
            var cell = map.getCell(coord);
                screen.setCharacter(coord.getX(), coord.getY(), cell.asCharacter());
        }
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void moveInventoryCellFocus(Coordinate newPosition, Coordinate oldPosition) throws IOException {
        inventoryPrinter.moveCellFocus(newPosition, oldPosition);
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void moveInventoryItems() throws IOException {
        inventoryPrinter.refreshLastCellAfterMoveItem();
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void cleanInventory() throws IOException {
        inventoryPrinter.cleanInventory();
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void refreshInventory() throws IOException {
        inventoryPrinter.refreshCells();
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void refreshHeroStats() throws IOException {
        playerStatsPrinter.refreshHeroStats();
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void refreshCurrentStageNumber(int currentStage) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(MAX_COL - GUI_INVENTORY_WIDTH, 1, String.valueOf(currentStage));
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void printMap(Map map) throws IOException {
        //Clean all map
        for (int x  = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                screen.setCharacter(x, y, CHARACTERS.BLACK_EMPTY);
            }
        }
        //Printing actual map
        for (int x  = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                var coord = new Coordinate(x, y);
                screen.setCharacter(x, y, map.getCell(coord).asCharacter());
            }
        }
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void printGUI() throws IOException {
        final int GUI_VERTICAL_LINE_COL = MAX_COL - 35;
        final TextCharacter VERTICAL_RED_LINE_CHAR = TextCharacter.fromCharacter('|', TextColor.ANSI.RED, TextColor.ANSI.BLACK)[0];
        final TextCharacter HORIZONTAL_RED_LINE_CHAR = TextCharacter.fromCharacter('-', TextColor.ANSI.RED, TextColor.ANSI.BLACK)[0];

        for (int i = 0; i <= MAX_ROW; i++) {
            //Gui vertical line
            screen.setCharacter(GUI_VERTICAL_LINE_COL, i, VERTICAL_RED_LINE_CHAR);
            //Total right screen border
            screen.setCharacter(MAX_COL, i, VERTICAL_RED_LINE_CHAR);
        }
        // Total Bottom screen border
        for (int i = 0; i < MAX_COL; i++) {
            screen.setCharacter(i, MAX_ROW, HORIZONTAL_RED_LINE_CHAR);
        }
        // Controls line
        for (int i = GUI_VERTICAL_LINE_COL + 1; i <= MAX_COL; i++) {
            screen.setCharacter(i, GUI_CONTROLS_HORIZONTAL_LINE_ROW, HORIZONTAL_RED_LINE_CHAR);
        }

        inventoryPrinter.printInventoryGUI();
        playerStatsPrinter.printGui();

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1, 0, "Game stats");
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1 + STATS_DESCRIPTION_COLUMN_OFFSET, 1, "Stage");
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1, 2, "Hero Stats");
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1, GUI_CONTROLS_HORIZONTAL_LINE_ROW + 1, "Controls");
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1, GUI_CONTROLS_HORIZONTAL_LINE_ROW + 2, "arrows - move your hero");
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1, GUI_CONTROLS_HORIZONTAL_LINE_ROW + 3, "space - make some action");
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1, GUI_CONTROLS_HORIZONTAL_LINE_ROW + 4, "i - change inventory");
        textGraphics.putString(GUI_VERTICAL_LINE_COL + 1, GUI_CONTROLS_HORIZONTAL_LINE_ROW + 5, "q - quit the game");

        screen.refresh(Screen.RefreshType.DELTA);
    }

    private int printWelcomeMessage(Screen screen) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        int difficulty = 0;
        textGraphics.putString(15,11, "Please maximise the window to go FOR THE WIN!");
        textGraphics.putString(15, 13, "Mobs strength - " + difficulty);
        textGraphics.putString(15, 15, "Press q to quit");
        screen.refresh();

        while (true) {
            TerminalSize newTerminalSize = screen.doResizeIfNecessary();
            if (newTerminalSize != null && newTerminalSize.getColumns() >= MAX_COL && newTerminalSize.getRows() >= MAX_ROW)
                break;

            KeyStroke ks = screen.pollInput();
            if (ks != null) {
                Character c = ks.getCharacter();
                if (c != null) {
                    if (Character.isDigit(c)) {
                        difficulty = Character.getNumericValue(c);
                        textGraphics.putString(15, 13, "Mobs strength - " + difficulty);
                        screen.refresh();
                    } else if (c == 'q') {
                        return -1;
                    }
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        screen.clear();

        return difficulty;
    }

    public KeyStroke pressedKey() throws IOException {
        return screen.pollInput();
    }

    public boolean gameOver() throws IOException {
        for (int x = GAME_OVER_RECTANGLE_COL; x < GAME_OVER_RECTANGLE_COL + GAME_OVER_RECTANGLE_WIDTH; x++) {
            for (int y = GAME_OVER_RECTANGLE_ROW; y < GAME_OVER_RECTANGLE_ROW + GAME_OVER_RECTANGLE_HEIGHT; y++) {
                screen.setCharacter(x, y, CHARACTERS.BLACK_EMPTY);
            }
        }

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(GAME_OVER_RECTANGLE_COL + 1, GAME_OVER_RECTANGLE_ROW + 2, "Game over. Want to restart? yes \\ no ");

        screen.refresh(Screen.RefreshType.DELTA);

        while (true) {
            try {
                KeyStroke ks = pressedKey();
                if (ks != null) {
                    Character c = ks.getCharacter();
                    if (c != null) {
                        if (c == 'y' || c == 'Y') {
                            return false;
                        } else if (c == 'n' || c == 'N') {
                            return true;
                        }
                    }
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void quitGame() {
        try {
            this.screen.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
