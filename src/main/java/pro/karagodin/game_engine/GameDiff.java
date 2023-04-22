package pro.karagodin.game_engine;

public class GameDiff {
    private MapDiff mapDiff;
    private Coordinate newPlayerCoordinate;

    private int inventory_prev_x;
    private int inventory_prev_y;
    private int inventory_x;
    private int inventory_y;
    private boolean inventoryMode;
    private boolean exitInventoryMode;
    private boolean quitGame;

    public GameDiff(MapDiff mapDiff, Coordinate newPlayerCoordinate) {
        this.mapDiff = mapDiff;
        this.newPlayerCoordinate = newPlayerCoordinate;
    }

    public MapDiff getMapDiff() {
        return mapDiff;
    }

    public void setMapDiff(MapDiff mapDiff) {
        this.mapDiff = mapDiff;
    }

    public Coordinate getNewPlayerCoordinate() {
        return newPlayerCoordinate;
    }

    public void setNewPlayerCoordinate(Coordinate newPlayerCoordinate) {
        this.newPlayerCoordinate = newPlayerCoordinate;
    }

    public boolean isInventoryMode() {
        return inventoryMode;
    }

    public void setInventoryMode(boolean inventoryMode) {
        this.inventoryMode = inventoryMode;
    }

    public int getInventory_prev_x() {
        return inventory_prev_x;
    }

    public void setInventory_prev_x(int inventory_prev_x) {
        this.inventory_prev_x = inventory_prev_x;
    }

    public int getInventory_prev_y() {
        return inventory_prev_y;
    }

    public void setInventory_prev_y(int inventory_prev_y) {
        this.inventory_prev_y = inventory_prev_y;
    }

    public int getInventory_x() {
        return inventory_x;
    }

    public void setInventory_x(int inventory_x) {
        this.inventory_x = inventory_x;
    }

    public int getInventory_y() {
        return inventory_y;
    }

    public void setInventory_y(int inventory_y) {
        this.inventory_y = inventory_y;
    }

    public boolean isExitInventoryMode() {
        return exitInventoryMode;
    }

    public void setExitInventoryMode(boolean exitInventoryMode) {
        this.exitInventoryMode = exitInventoryMode;
    }

    public boolean isQuitGame() {
        return quitGame;
    }

    public void setQuitGame(boolean quitGame) {
        this.quitGame = quitGame;
    }
}
