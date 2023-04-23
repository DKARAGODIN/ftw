package pro.karagodin.output;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import pro.karagodin.game_engine.Coordinate;

import java.io.IOException;


public class InventoryPrinter {
    private static final int CELL_WIDTH = 5;
    private static final int CELL_HEIGHT = 3;
    private static final int CELLS_COLS = 5;
    private static final int EQUIPPED_CELL_ROWS = 2;
    private static final int UNEQUIPPED_CELL_ROWS = 5;
    private final Screen screen;
    private final Coordinate equippedTitle, stashedTitle;
    private final Coordinate equippedTable, stashedTable;

    public InventoryPrinter(Screen screen, Coordinate lu) {
        this.screen = screen;
        this.equippedTitle = new Coordinate(lu.getX(), lu.getY());
        this.equippedTable = new Coordinate(lu.getX(), lu.getY() + 1);
        var equippedTableHeight = EQUIPPED_CELL_ROWS * (CELL_HEIGHT + 1) + 1;
        this.stashedTitle = new Coordinate(lu.getX(), lu.getY() + 1 + equippedTableHeight);
        this.stashedTable = new Coordinate(lu.getX(), lu.getY() + 1 + equippedTableHeight + 1);
    }

    public void moveCellFocus(Coordinate newPosition, Coordinate oldPosition) throws IOException {
        if (oldPosition != null) {
            fillCell(oldPosition.getX(), oldPosition.getY(), CHARACTERS.BLACK_EMPTY);
        }
        if (newPosition != null) {
            fillCell(newPosition.getX(), newPosition.getY(), CHARACTERS.GREY_EMPTY);
        }
        screen.refresh(Screen.RefreshType.DELTA);
    }

    public void printInventoryGUI(TextGraphics textGraphics) {
        textGraphics.putString(equippedTitle.getX(), equippedTitle.getY(), "Hero equipped items");
        drawTable(equippedTable.getX(), equippedTable.getY(), EQUIPPED_CELL_ROWS);

        textGraphics.putString(stashedTitle.getX(), stashedTitle.getY(), "Hero stashed items");
        drawTable(stashedTable.getX(), stashedTable.getY(), UNEQUIPPED_CELL_ROWS);
    }

    private void drawTable(int startX, int startY, int rows) {
        for (int i = 0; i <= CELLS_COLS; i++)
            drawCol(startX + i * (CELL_WIDTH + 1), startY, rows * (CELL_HEIGHT + 1) + 1);

        for (int i = 0; i <= rows; i++)
            drawRow(startX, startY + i * (CELL_HEIGHT + 1), CELLS_COLS * (CELL_WIDTH + 1) + 1);
    }

    private void drawRow(int startX, int startY, int size) {
        for (int x = 0; x < size; x++)
            screen.setCharacter(startX + x, startY, CHARACTERS.HORIZONTAL_GREEN_LINE);
    }

    private void drawCol(int startX, int startY, int size) {
        for (int y = 0; y < size; y++)
            screen.setCharacter(startX, startY + y, CHARACTERS.VERTICAL_GREEN_LINE);
    }

    private void fillCell(int col, int row, TextCharacter c) {
        Coordinate startPoint = row < EQUIPPED_CELL_ROWS ? equippedTable : stashedTable;
        if(row >= EQUIPPED_CELL_ROWS)
            row -= EQUIPPED_CELL_ROWS;
        int x = startPoint.getX() + 1 + col * (CELL_WIDTH + 1);
        int y = startPoint.getY() + 1 + row * (CELL_HEIGHT + 1);
        fillRect(x, y, CELL_WIDTH, CELL_HEIGHT, c);
    }

    private void fillRect(int startX, int startY, int width, int height, TextCharacter c) {
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                screen.setCharacter(startX + x, startY + y, c);
    }
}
