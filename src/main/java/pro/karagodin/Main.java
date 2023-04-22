package pro.karagodin;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;


public class Main {

    private static final int MAX_COL = 239;
    private static final int MAX_ROW = 67;
    private static final int GUI_VERTICAL_LINE_COL = MAX_COL - 25;

    private static int hero_col = MAX_COL / 2;
    private static int hero_row = MAX_ROW / 2;
    private static final TextCharacter HERO_CHAR = TextCharacter.fromCharacter('@', TextColor.ANSI.RED, TextColor.ANSI.GREEN)[0];
    private static final TextCharacter BLACK_CHAR = TextCharacter.fromCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.BLACK)[0];
    private static final TextCharacter VERTICAL_RED_LINE_CHAR = TextCharacter.fromCharacter('|', TextColor.ANSI.RED, TextColor.ANSI.BLACK)[0];


    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        try (Screen screen = terminalFactory.createScreen()) {
            screen.startScreen();
            printWelcomeMessage(screen);

            printGUI(screen);


            screen.setCharacter(hero_col, hero_row, HERO_CHAR);
            screen.refresh(Screen.RefreshType.COMPLETE);


screenLoop: while (true) {
                KeyStroke key = screen.pollInput();
                if (key != null) {
                    switch (key.getKeyType()) {
                        case ArrowLeft -> {
                            screen.setCharacter(hero_col, hero_row, BLACK_CHAR);
                            hero_col--;
                        }
                        case ArrowRight -> {
                            screen.setCharacter(hero_col, hero_row, BLACK_CHAR);
                            hero_col++;
                        }
                        case ArrowDown -> {
                            screen.setCharacter(hero_col, hero_row, BLACK_CHAR);
                            hero_row++;
                        }
                        case ArrowUp -> {
                            screen.setCharacter(hero_col, hero_row, BLACK_CHAR);
                            hero_row--;
                        }
                        case Character -> {
                            if (key.getCharacter() == 'q')
                                break screenLoop;
                        }
                    }
                    screen.setCharacter(hero_col, hero_row, HERO_CHAR);
                    screen.refresh(Screen.RefreshType.DELTA);
                }
                Thread.sleep(10);
            }

            screen.stopScreen();
        }
        catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printGUI(Screen screen) {
        for (int i = 0; i <= MAX_ROW; i++) {
            screen.setCharacter(GUI_VERTICAL_LINE_COL, i, VERTICAL_RED_LINE_CHAR);
        }
    }

    private static void printWelcomeMessage(Screen screen) throws InterruptedException, IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(15,11, "Please maximise the window to go FOR THE WIN!");
        screen.refresh();
        while (true) {
            TerminalSize newTerminalSize = screen.doResizeIfNecessary();
            if (newTerminalSize != null && newTerminalSize.getColumns() == MAX_COL + 1 && newTerminalSize.getRows() == MAX_ROW+1)
                break;
            Thread.sleep(50);
        }
        screen.clear();
    }
}
