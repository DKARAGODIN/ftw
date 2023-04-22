package pro.karagodin.models;

import pro.karagodin.ai_system.PlayerStrategy;
import pro.karagodin.output.Printer;
import pro.karagodin.time.TimeInterval;

public class Player extends Mob {

    private int level = 1;
    private int xp = 0;
    private Inventory inventory = new Inventory();
    private boolean inventoryMode = false;
    private boolean wantsToContinuePlaying = true;

    public Player(int hp, int maxHp, TimeInterval pace, Printer printer) {
        super(hp, maxHp, pace, new PlayerStrategy(printer));
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

    public boolean doesPlayerWantToPlay() {
        return wantsToContinuePlaying;
    }

    public void quitFromGame() {
        wantsToContinuePlaying = false;
    }
}
