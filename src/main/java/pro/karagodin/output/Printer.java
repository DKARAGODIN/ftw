package pro.karagodin.output;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.models.Inventory;
import pro.karagodin.models.Map;

import java.io.IOException;

public class Printer {
    private static final int MAX_COL = 230;
    private static final int MAX_ROW = 60;

    private static int hero_col = MAX_COL / 2;
    private static int hero_row = MAX_ROW / 2;

    private Screen screen;
    private InventoryPrinter inventoryPrinter;

    public void init(Inventory inventory) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        this.screen = screen;
        this.inventoryPrinter = new InventoryPrinter(screen, new Coordinate(MAX_COL - 34, 13), inventory);
        screen.startScreen();

        printWelcomeMessage(screen);
    }

    public void updateCoordinates(Map map, MapDiff diff) throws IOException {
        for (Coordinate coord : diff.getUpdatedCoordinatesInMap()) {
            var cell = map.getCell(coord);
                screen.setCharacter(coord.getX(), coord.getY(), new TextCharacter(cell.getView()));
        }
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void moveCellFocus(Coordinate newPosition, Coordinate oldPosition) throws IOException {
        inventoryPrinter.moveCellFocus(newPosition, oldPosition);
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void refreshInventory() throws IOException {
        inventoryPrinter.refreshCells();
    }

    public void printHeroInfo() throws IOException {
        final int GUI_VERTICAL_LINE_COL = MAX_COL - 35;

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,0, "Game stats");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,1, "0 - current stage");

        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,3, "Hero stats");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,4, "1 (1000) - LVL (XP to next LVL)");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,5, "0 (0) - XP (XP modifier)");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,6, "100 (0) - HP (HPS)");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,7, "1 - Attack");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,8, "1 - Defense");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,9, "5-10 - Damage");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,10, "100 (0) - Stamina (Stamina modifier)");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,11, "50 (0) - Speed (speed modifier)");

        inventoryPrinter.printInventoryGUI(textGraphics);

        screen.refresh(Screen.RefreshType.DELTA);
    }
    public void printMap(Map map){
        for (int x  = 0; x < map.getWidth(); x++) {
            for (int y = 0 ; y < map.getHeight(); y++){
                var coord = new Coordinate(x, y);
                screen.setCharacter(x, y, new TextCharacter(map.getCell(coord).getView()));
            }
        }
    }
    public void printGUI() throws IOException {
        final int GUI_VERTICAL_LINE_COL = MAX_COL - 35;
        final int GUI_HORIZONTAL_LINE_ROW = MAX_ROW - 15;
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
            screen.setCharacter(i, GUI_HORIZONTAL_LINE_ROW, HORIZONTAL_RED_LINE_CHAR);
        }

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,GUI_HORIZONTAL_LINE_ROW+1, "Controls");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,GUI_HORIZONTAL_LINE_ROW+2, "arrows - move your hero");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,GUI_HORIZONTAL_LINE_ROW+3, "space - make some action");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,GUI_HORIZONTAL_LINE_ROW+4, "i - change inventory");
        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,GUI_HORIZONTAL_LINE_ROW+5, "q - quit the game");

        screen.refresh(Screen.RefreshType.DELTA);
    }

    private void printWelcomeMessage(Screen screen) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(15,11, "Please maximise the window to go FOR THE WIN!");
        screen.refresh();
        while (true) {
            TerminalSize newTerminalSize = screen.doResizeIfNecessary();
            if (newTerminalSize != null && newTerminalSize.getColumns() >= MAX_COL && newTerminalSize.getRows() >= MAX_ROW)
                break;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        screen.clear();
    }

    public KeyStroke pressedKey() throws IOException {
        return screen.pollInput();
    }

    public void quitGame() {
        try {
            this.screen.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
