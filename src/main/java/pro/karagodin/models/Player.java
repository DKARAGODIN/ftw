package pro.karagodin.models;

import static pro.karagodin.models.Inventory.MAX_EQUIPPED_ITEMS;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.PlayerStrategy;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeInterval;

import java.util.Map;

/**
 * A game object that handled by users
 */
@Setter
@Getter
public class Player extends Mob {

    private static final int BASE_MAX_HEALTH = 100;
    private static final int BASE_ATTACK = 1;
    private static final int BASE_DEFENCE = 1;
    private static final int BASE_MIN_DAMAGE = 5;
    private static final int BASE_MAX_DAMAGE = 15;
    private static final int BASE_MAX_STAMINA = 100;

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
            if (idx < inventory.getStashedItems().size() && inventory.getEquippedItems().size() < MAX_EQUIPPED_ITEMS) {
                Item item = inventory.getStashedItems().remove(idx);
                inventory.getEquippedItems().add(item);
                return true;
            }
        } else {
            int idx = 5 * inventory.getY() + inventory.getX();
            if (idx < inventory.getEquippedItems().size()) {
                Item item = inventory.getEquippedItems().remove(idx);
                inventory.getStashedItems().add(item);
                return true;
            }
        }
        return false;
    }

    public void applyEquippedItems() {
        for (Item item : inventory.getEquippedItems()) {
            for (Map.Entry<Item.Modifier, Integer> e : item.getItemModifiers().entrySet()) {
                switch (e.getKey()) {
                    case ATTACK -> attack = BASE_ATTACK + e.getValue();
                    case DEFENCE -> defence = BASE_DEFENCE + e.getValue();
                    case MIN_DAMAGE -> minDamage = BASE_MIN_DAMAGE + e.getValue();
                    case MAX_DAMAGE -> maxDamage = BASE_MAX_DAMAGE + e.getValue();
                    case MAX_HP -> maxHp = BASE_MAX_HEALTH + e.getValue();
                    case MAX_STAMINA -> maxStamina = BASE_MAX_STAMINA + e.getValue();
                }
            }
        }
    }
}
