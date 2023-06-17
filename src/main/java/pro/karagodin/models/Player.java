package pro.karagodin.models;

import static pro.karagodin.models.Inventory.MAX_EQUIPPED_ITEMS;

import java.util.List;
import java.util.Map;

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

    public static final int BASE_MAX_HEALTH = 100;
    public static final int BASE_ATTACK = 3;
    public static final int BASE_DEFENCE = 3;
    public static final int BASE_MIN_DAMAGE = 5;
    public static final int BASE_MAX_DAMAGE = 15;
    public static final TimeMoment BASE_PACE = new TimeMoment(200);
    public static final int MIN_PACE = 75;

    private int level = 1;
    private int xp = 0;
    private int nextLevel = 100;
    private int nextLevelIncrease = 100;

    //These values have cap. So we need to store value exceeding cap to handle loot changes
    protected int calculatedMinDamage;
    protected long calculatedPace;


    private Inventory inventory = new Inventory();
    private boolean inventoryMode = false;
    private boolean wantsToContinuePlaying = true;

    public Player(int hp, int maxHp, TimeMoment pace, Printer printer) {
        super(hp, maxHp, BASE_ATTACK, BASE_DEFENCE, BASE_MIN_DAMAGE, BASE_MAX_DAMAGE, pace, new PlayerStrategy(printer), '@', List.of());
        this.calculatedMinDamage = BASE_MIN_DAMAGE;
        this.calculatedPace = BASE_PACE.getTimeInMs();
    }

    public void quitFromGame() {
        wantsToContinuePlaying = false;
    }

    public LootItem moveItem() {
        Coordinate invCoord = inventory.getCoordinate();
        if (invCoord.getY() > 1) {
            int idx = 5 * (inventory.getY() - 2) + inventory.getX();
            if (idx < inventory.getStashedSmallThings().size() && inventory.getEquippedSmallThings().size() < MAX_EQUIPPED_ITEMS) {
                LootItem smallThing = inventory.getStashedSmallThings().remove(idx);
                inventory.getEquippedSmallThings().add(smallThing);
                smallThing.setEquipped(true);
                return smallThing;
            }
        } else {
            int idx = 5 * inventory.getY() + inventory.getX();
            if (idx < inventory.getEquippedSmallThings().size()) {
                LootItem smallThing = inventory.getEquippedSmallThings().remove(idx);
                inventory.getStashedSmallThings().add(smallThing);
                smallThing.setEquipped(false);
                return smallThing;
            }
        }
        return null;
    }

    /**
     * Incremental leveling. Next level is harder to get.
     */
    private void processIncreaseXP() {
        if (this.xp >= this.nextLevel) {
            this.nextLevel = this.nextLevel + this.nextLevelIncrease;
            this.nextLevelIncrease = (int) (this.nextLevelIncrease * 1.2);
            this.attack++;
            this.defence++;
            this.maxDamage += 2;
            this.calculatedMinDamage++;
            this.minDamage = Math.min(maxDamage, calculatedMinDamage);
            this.maxHp += 10;
            this.hp += 10;
            this.level++;
            processIncreaseXP();
        }
    }

    public void increaseXP(int value) {
        this.xp += value;
        processIncreaseXP();
    }

    public void applyMovedLootItem(LootItem item) {
        if (item.isEquipped()) {
            for (Map.Entry<LootItem.Modifier, Integer> e : item.getItemModifiers().entrySet()) {
                switch (e.getKey()) {
                    case ATTACK -> attack += e.getValue();
                    case DEFENCE -> defence += e.getValue();
                    case MAX_DAMAGE -> maxDamage += e.getValue();
                    case MAX_HP -> maxHp += e.getValue();
                    case SPEED -> {
                        calculatedPace -= e.getValue();
                        pace.setTimeInMs(Math.max(calculatedPace, MIN_PACE));
                    }
                    default -> {
                    }
                }
            }
            //MIN Damage has to be processed after MAX Damage
            if (item.getItemModifiers().containsKey(LootItem.Modifier.MIN_DAMAGE)) {
                calculatedMinDamage += item.getItemModifiers().get(LootItem.Modifier.MIN_DAMAGE);
                minDamage = Math.min(calculatedMinDamage, maxDamage);
            }
        } else {
            for (Map.Entry<LootItem.Modifier, Integer> e : item.getItemModifiers().entrySet()) {
                switch (e.getKey()) {
                    case ATTACK -> attack -= e.getValue();
                    case DEFENCE -> defence -= e.getValue();
                    case MAX_DAMAGE -> maxDamage -= e.getValue();
                    case MAX_HP -> maxHp -= e.getValue();
                    case SPEED -> {
                        calculatedPace += e.getValue();
                        pace.setTimeInMs(Math.max(calculatedPace, MIN_PACE));
                    }
                    default -> {
                    }
                }
            }
            //MIN Damage has to be processed after MAX Damage
            if (item.getItemModifiers().containsKey(LootItem.Modifier.MIN_DAMAGE)) {
                calculatedMinDamage -= item.getItemModifiers().get(LootItem.Modifier.MIN_DAMAGE);
                minDamage = Math.min(calculatedMinDamage, maxDamage);
            }
        }
    }

    @Override
    public Mob cloneMob() {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets field values to defaults in case player wants to restart
     */
    public void refresh(Printer printer) {
        this.inventory.clear();
        this.hp = BASE_MAX_HEALTH;
        this.maxHp = BASE_MAX_HEALTH;
        this.minDamage = BASE_MIN_DAMAGE;
        this.maxDamage = BASE_MAX_DAMAGE;
        this.level = 1;
        this.xp = 0;
        this.nextLevel = 100;
        this.nextLevelIncrease = 100;
        this.defence = BASE_DEFENCE;
        this.attack = BASE_ATTACK;
        this.pace = BASE_PACE;
        this.wantsToContinuePlaying = true;
        this.strategy = new PlayerStrategy(printer);
    }
}
