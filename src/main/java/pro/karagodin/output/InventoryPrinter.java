package pro.karagodin.output;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import pro.karagodin.game_engine.Coordinate;

import java.io.IOException;

public class InventoryPrinter {
    private final Screen screen;
    private final Coordinate lu;
    private final Coordinate rb;

    public InventoryPrinter(Screen screen, Coordinate lu, Coordinate rb){
        this.screen = screen;
        this.lu = lu;
        this.rb = rb;
    }

    public void updateInventory(Coordinate newPosition, Coordinate oldPosition) throws IOException {
        if (oldPosition != null) {
            applyInventoryDiff(oldPosition.getX(), oldPosition.getY(), CHARACTERS.BLACK_EMPTY);
        }
        if (newPosition != null) {
            applyInventoryDiff(newPosition.getX(), newPosition.getY(), CHARACTERS.GREY_EMPTY);
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
}
