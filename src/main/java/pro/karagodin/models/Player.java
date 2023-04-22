package pro.karagodin.models;

public class Player extends Mob {

    private int level = 1;
    private int xp = 0;
    private Inventory inventory = new Inventory();
    private boolean inventoryMode = false;

    public Player(int maxHp) {
        super(maxHp);
    }

    public Player(double hp, int maxHp) {
        super(hp, maxHp);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isInventoryMode() {
        return inventoryMode;
    }

    public void setInventoryMode(boolean inventoryMode) {
        this.inventoryMode = inventoryMode;
    }
}
