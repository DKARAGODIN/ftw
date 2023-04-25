package pro.karagodin.models;

import static pro.karagodin.models.Inventory.MAX_EQUIPPED_ITEMS;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.PlayerStrategy;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeInterval;


/**
 * A game object that handled by users
 */
@Setter
@Getter
public class Player extends Mob {

    private int level = 1;
    private int xp = 0;
    private Inventory inventory = new Inventory();
    private boolean inventoryMode = false;
    private boolean wantsToContinuePlaying = true;

    public Player(int hp, int maxHp, TimeInterval pace, Printer printer) {
        super(hp, maxHp, pace, new PlayerStrategy(printer));
        this.view = '@';
        this.color = TextColor.ANSI.RED;
    }

    public void quitFromGame() {
        wantsToContinuePlaying = false;
    }

    public boolean moveItem() {
        Coordinate invCoord = inventory.getCoordinate();
        if (invCoord.getY() > 1) {
            int idx = 5 * (inventory.getY() - 2) + inventory.getX();
            if (idx < inventory.getBackpackItems().size() && inventory.getEquippedItems().size() < MAX_EQUIPPED_ITEMS) {
                Item item = inventory.getBackpackItems().remove(idx);
                inventory.getEquippedItems().add(item);
                return true;
            }
        } else {
            int idx = 5 * inventory.getY() + inventory.getX();
            if (idx < inventory.getEquippedItems().size()) {
                Item item = inventory.getEquippedItems().remove(idx);
                inventory.getBackpackItems().add(item);
                return true;
            }
        }
        return false;
    }
}
