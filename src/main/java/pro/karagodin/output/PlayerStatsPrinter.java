package pro.karagodin.output;

import java.io.IOException;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.models.Player;

/**
 * Prints hero statistics GUI
 */
public class PlayerStatsPrinter {

    private static final int STATS_DESCRIPTION_COLUMN_OFFSET = 20;

    private final Screen screen;
    private final Player player;
    private final Coordinate coordinate;

    public PlayerStatsPrinter(Screen screen, Coordinate coordinate, Player player) {
        this.screen = screen;
        this.coordinate = coordinate;
        this.player = player;
    }

    public void printGui() throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        int col = coordinate.getX() + STATS_DESCRIPTION_COLUMN_OFFSET;
        int row = coordinate.getY();

        textGraphics.putString(col, row, "LVL");
        textGraphics.putString(col, row + 1, "XP");
        textGraphics.putString(col, row + 2, "HP");
        textGraphics.putString(col, row + 3, "Attack");
        textGraphics.putString(col, row + 4, "Defence");
        textGraphics.putString(col, row + 5, "Damage");
        textGraphics.putString(col, row + 6, "Delay");
    }

    public void refreshHeroStats() {
        TextGraphics textGraphics = screen.newTextGraphics();
        int col = coordinate.getX();
        int row = coordinate.getY();
        textGraphics.putString(col, row, String.valueOf(player.getLevel()));
        textGraphics.putString(col, row + 1, player.getXp() + " (" + player.getNextLevel() + ")" + "  ");
        textGraphics.putString(col, row + 2, player.getHp() + " (" + player.getMaxHp() + ")" + "   ");
        textGraphics.putString(col, row + 3, player.getAttack() + "   ");
        textGraphics.putString(col, row + 4, player.getDefence() + "   ");
        textGraphics.putString(col, row + 5, player.getMinDamage() + " - " + player.getMaxDamage() + "   ");
        textGraphics.putString(col, row + 6, player.getPace().getTimeInMs() + "   ");
    }
}
