package pro.karagodin.models;

import static pro.karagodin.models.Inventory.MAX_EQUIPPED_ITEMS;

import java.util.Map;

import com.googlecode.lanterna.TextColor;
import lombok.Getter;
import lombok.Setter;
import pro.karagodin.ai_system.PlayerStrategy;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeMoment;


/**
 * A game object controlled by user
 */
@Setter
@Getter
public class Player extends Mob {

    private static final int BASE_MAX_HEALTH = 100;
    private static final int BASE_ATTACK = 3;
    private static final int BASE_DEFENCE = 3;
    private static final int BASE_MIN_DAMAGE = 5;
    private static final int BASE_MAX_DAMAGE = 15;
    private static final int BASE_MAX_STAMINA = 100;

    private int level = 1;
    private int xp = 0;

    private Inventory inventory = new Inventory();
    private boolean inventoryMode = false;
    private boolean wantsToContinuePlaying = true;

    public Player(int hp, int maxHp, int attack, int defence, int minDamage, int maxDamage, TimeMoment pace, Printer printer) {
        super(hp, maxHp, attack, defence, minDamage, maxDamage, pace, new PlayerStrategy(printer));
        this.view = '@';
        this.color = TextColor.ANSI.RED;

        this.defence = BASE_ATTACK;
        this.attack = BASE_DEFENCE;
        this.minDamage = BASE_MIN_DAMAGE;
        this.maxDamage = BASE_MAX_DAMAGE;
        this.stamina = BASE_MAX_STAMINA;
        this.maxStamina = BASE_MAX_STAMINA;

    }

    public void quitFromGame() {
        wantsToContinuePlaying = false;
    }

    public Item moveItem() {
        Coordinate invCoord = inventory.getCoordinate();
        if (invCoord.getY() > 1) {
            int idx = 5 * (inventory.getY() - 2) + inventory.getX();
            if (idx < inventory.getStashedItems().size() && inventory.getEquippedItems().size() < MAX_EQUIPPED_ITEMS) {
                Item item = inventory.getStashedItems().remove(idx);
                inventory.getEquippedItems().add(item);
                item.setEquipped(true);
                return item;
            }
        } else {
            int idx = 5 * inventory.getY() + inventory.getX();
            if (idx < inventory.getEquippedItems().size()) {
                Item item = inventory.getEquippedItems().remove(idx);
                inventory.getStashedItems().add(item);
                item.setEquipped(false);
                return item;
            }
        }
        return null;
    }

    public void applyMovedItem(Item item) {
        for (Map.Entry<Item.Modifier, Integer> e : item.getItemModifiers().entrySet()) {
            if (item.isEquipped()) {
                switch (e.getKey()) {
                    case ATTACK -> attack += e.getValue();
                    case DEFENCE -> defence += e.getValue();
                    case MIN_DAMAGE -> minDamage += e.getValue();
                    case MAX_DAMAGE -> maxDamage += e.getValue();
                    case MAX_HP -> maxHp += e.getValue();
                    case MAX_STAMINA -> maxStamina += e.getValue();
                    default -> {
                    }
                }
            } else {
                switch (e.getKey()) {
                    case ATTACK -> attack -= e.getValue();
                    case DEFENCE -> defence -= e.getValue();
                    case MIN_DAMAGE -> minDamage -= e.getValue();
                    case MAX_DAMAGE -> maxDamage -= e.getValue();
                    case MAX_HP -> maxHp -= e.getValue();
                    case MAX_STAMINA -> maxStamina -= e.getValue();
                    default -> {
                    }
                }
            }
        }
    }
}
