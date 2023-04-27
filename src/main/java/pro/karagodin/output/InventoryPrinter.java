package pro.karagodin.output;

import static pro.karagodin.output.CHARACTERS.BLACK_EMPTY;
import static pro.karagodin.output.CHARACTERS.GREY_EMPTY;

import java.util.List;
import java.util.Map;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Inventory;
import pro.karagodin.models.Item;


public class InventoryPrinter {
    private static final int CELL_WIDTH = 5;
    private static final int CELL_HEIGHT = 3;
    private static final int CELLS_COLS = 5;
    private static final int EQUIPPED_CELL_ROWS = 2;
    private static final int STASHED_CELL_ROWS = 5;
    private static final int ITEM_DESCRIPTION_BLOCK_HEIGHT = 8;

    private final Screen screen;
    private final Coordinate equippedTitle, stashedTitle;
    private final Coordinate equippedTable;
    private final Inventory inventory;
    private final Coordinate stashedTable;
    private final Coordinate itemDescriptionFirstLine;

    public InventoryPrinter(Screen screen, Coordinate lu, Inventory inventory) {
        this.screen = screen;
        this.equippedTitle = new Coordinate(lu.getX(), lu.getY());
        this.equippedTable = new Coordinate(lu.getX(), lu.getY() + 1);
        this.inventory = inventory;
        var equippedTableHeight = EQUIPPED_CELL_ROWS * (CELL_HEIGHT + 1) + 1;
        this.stashedTitle = new Coordinate(lu.getX(), lu.getY() + 1 + equippedTableHeight);
        this.stashedTable = new Coordinate(lu.getX(), lu.getY() + 1 + equippedTableHeight + 1);
        var stashedTableHeight = STASHED_CELL_ROWS * (CELL_HEIGHT + 1) + 1;
        this.itemDescriptionFirstLine = new Coordinate(lu.getX(), lu.getY() + 1 + equippedTableHeight + 1 + stashedTableHeight + 1);

    }

    public void moveCellFocus(Coordinate newPosition, Coordinate oldPosition) {
        if (oldPosition != null) {
            fillCell(oldPosition.getX(), oldPosition.getY(), BLACK_EMPTY);
        }
        if (newPosition != null) {
            fillCell(newPosition.getX(), newPosition.getY(), GREY_EMPTY);
        }
        updateItemDescription(newPosition);
        refreshCells();
    }

    private void updateItemDescription(Coordinate newPosition) {
        fillRect(itemDescriptionFirstLine.getX(), itemDescriptionFirstLine.getY(), Printer.GUI_INVENTORY_WIDTH, ITEM_DESCRIPTION_BLOCK_HEIGHT, BLACK_EMPTY);
        if (newPosition != null) {
            Item item = getItemByCoordinate(newPosition);
            if (item != null) {
                TextGraphics g = this.screen.newTextGraphics();
                int row = itemDescriptionFirstLine.getY();
                for (Map.Entry<Item.Modifier, Integer> e : item.getItemModifiers().entrySet()) {
                    g.putString(itemDescriptionFirstLine.getX(), row, e.getValue() + " " + e.getKey().getDescription());
                    row++;
                }
            }
        }
    }

    private Item getItemByCoordinate(Coordinate newPosition) {
        if (newPosition.getY() < 2) {
            int idx = newPosition.getX() + newPosition.getY() * CELLS_COLS;
            return idx > inventory.getEquippedItems().size() - 1 ? null : inventory.getEquippedItems().get(idx);
        } else {
            int idx = newPosition.getX() + (newPosition.getY() - 2) * CELLS_COLS;
            return idx > inventory.getStashedItems().size() - 1 ? null : inventory.getStashedItems().get(idx);
        }
    }

    public void printInventoryGUI(TextGraphics textGraphics) {
        textGraphics.putString(equippedTitle.getX(), equippedTitle.getY(), "Hero equipped items");
        drawTableGUI(equippedTable.getX(), equippedTable.getY(), EQUIPPED_CELL_ROWS);

        textGraphics.putString(stashedTitle.getX(), stashedTitle.getY(), "Hero stashed items");
        drawTableGUI(stashedTable.getX(), stashedTable.getY(), STASHED_CELL_ROWS);

        textGraphics.putString(itemDescriptionFirstLine.getX(), itemDescriptionFirstLine.getY() - 1, "Selected item description");
    }

    public void refreshCells() {
        drawItemsInTable(equippedTable, inventory.getEquippedItems());
        drawItemsInTable(stashedTable, inventory.getStashedItems());
    }

    public void refreshLastCellAfterMoveItem() {
        fillCell(inventory.getEquippedItems().size() % CELLS_COLS, inventory.getEquippedItems().size() / CELLS_COLS, BLACK_EMPTY);
        fillCell(inventory.getStashedItems().size() % CELLS_COLS, 2 + inventory.getStashedItems().size() / CELLS_COLS, BLACK_EMPTY);
        moveCellFocus(inventory.getCoordinate(), null);
    }

    private void drawItemsInTable(Coordinate table, List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            int row = i / CELLS_COLS;
            int col = i % CELLS_COLS;
            drawItemInCell(table.getX() + 1 + col * (CELL_WIDTH + 1), table.getY() + 1 + row * (CELL_HEIGHT + 1), items.get(i));
        }
    }

    private void drawItemInCell(int startX, int startY, Item item) {
        screen.setCharacter(startX + CELL_WIDTH / 2, startY + CELL_HEIGHT / 2, new TextCharacter(item.getView()));
    }

    private void drawTableGUI(int startX, int startY, int rows) {
        for (int i = 0; i <= CELLS_COLS; i++)
            drawColGUI(startX + i * (CELL_WIDTH + 1), startY, rows * (CELL_HEIGHT + 1) + 1);

        for (int i = 0; i <= rows; i++)
            drawRowGUI(startX, startY + i * (CELL_HEIGHT + 1), CELLS_COLS * (CELL_WIDTH + 1) + 1);
    }

    private void drawRowGUI(int startX, int startY, int size) {
        for (int x = 0; x < size; x++)
            screen.setCharacter(startX + x, startY, CHARACTERS.HORIZONTAL_GREEN_LINE);
    }

    private void drawColGUI(int startX, int startY, int size) {
        for (int y = 0; y < size; y++)
            screen.setCharacter(startX, startY + y, CHARACTERS.VERTICAL_GREEN_LINE);
    }

    private void fillCell(int col, int row, TextCharacter c) {
        Coordinate startPoint = row < EQUIPPED_CELL_ROWS ? equippedTable : stashedTable;
        if (row >= EQUIPPED_CELL_ROWS)
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
