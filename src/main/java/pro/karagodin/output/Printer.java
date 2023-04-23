package pro.karagodin.output;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MapDiff;
import pro.karagodin.models.Map;

import java.io.IOException;

public class Printer {
    private static final int MAX_COL = 230;
    private static final int MAX_ROW = 60;

    private static int hero_col = MAX_COL / 2;
    private static int hero_row = MAX_ROW / 2;
    private static final TextCharacter HERO_CHAR = TextCharacter.fromCharacter('@', TextColor.ANSI.RED, TextColor.ANSI.GREEN)[0];
    private static final TextCharacter BLACK_EMPTY_CHAR = TextCharacter.fromCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.BLACK)[0];
    private static final TextCharacter GREY_EMPTY_CHAR = TextCharacter.fromCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.WHITE)[0];

    private Screen screen;

    public void init() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        this.screen = screen;
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

    public void updateInventory(Coordinate newPosition, Coordinate oldPosition) throws IOException {
        if (oldPosition != null) {
            applyInventoryDiff(oldPosition.getX(), oldPosition.getY(), BLACK_EMPTY_CHAR);
        }
        if (newPosition != null) {
            applyInventoryDiff(newPosition.getX(), newPosition.getY(), GREY_EMPTY_CHAR);
        }
        screen.refresh(Screen.RefreshType.DELTA);
    }

    private void applyInventoryDiff(int x, int y, TextCharacter c) {
        if (y <= 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    int real_x = 197 + i + x * 6;
                    int real_y = 15 + j + y * 4;
                    screen.setCharacter(real_x, real_y, c);
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    int real_x = 197 + i + x * 6;
                    int real_y = 17 + j + y * 4;
                    screen.setCharacter(real_x, real_y, c);
                }
            }
        }
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

        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,13, "Hero equipped items");

        final TextCharacter VERTICAL_GREEN_LINE_CHAR = TextCharacter.fromCharacter('|', TextColor.ANSI.GREEN, TextColor.ANSI.BLACK)[0];
        final TextCharacter HORIZONTAL_GREEN_LINE_CHAR = TextCharacter.fromCharacter('-', TextColor.ANSI.GREEN, TextColor.ANSI.BLACK)[0];

        for (int i = GUI_VERTICAL_LINE_COL + 1; i <= 226; i++) {
            screen.setCharacter(i, 14, HORIZONTAL_GREEN_LINE_CHAR);
            screen.setCharacter(i, 18, HORIZONTAL_GREEN_LINE_CHAR);
            screen.setCharacter(i, 22, HORIZONTAL_GREEN_LINE_CHAR);
        }

        for (int i = 0; i <=5; i++) {
            int s = 196 + i * 6;
            for (int j = 0; j <= 2; j++) {
                int k = 15 + j;
                int l = 19 + j;
                screen.setCharacter(s, k, VERTICAL_GREEN_LINE_CHAR);
                screen.setCharacter(s, l, VERTICAL_GREEN_LINE_CHAR);
            }
        }

        textGraphics.putString(GUI_VERTICAL_LINE_COL+1,23, "Hero stashed items");
        for (int i = 0; i <=5; i++) {
            int s = 196 + i * 6;
            for (int j = 0; j <= 3; j++) {
                for (int k = 0; k <= 4; k++) {
                    int l = 25 + j + k * 4;
                    screen.setCharacter(s, l, VERTICAL_GREEN_LINE_CHAR);
                }
            }
        }
        for (int i = GUI_VERTICAL_LINE_COL + 1; i <= 226; i++) {
            for (int j = 0; j <= 5; j++) {
                int k = 24 + j * 4;
                screen.setCharacter(i, k, HORIZONTAL_GREEN_LINE_CHAR);
            }
        }
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
