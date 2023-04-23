package pro.karagodin.game_engine;

public class GameDiff {
    private MapDiff mapDiff;
    private MobWithPosition newMobPosition;
    private boolean isInventoryChanged;

    public GameDiff(MobWithPosition oldMobPosition) {
        this(new MapDiff(), oldMobPosition, false);
    }

    public GameDiff(MapDiff mapDiff, MobWithPosition newMobPosition) {
        this(mapDiff, newMobPosition, false);
    }

    public GameDiff(MapDiff mapDiff, MobWithPosition newMobPosition, boolean isInventoryChanged) {
        this.mapDiff = mapDiff;
        this.newMobPosition = newMobPosition;
        this.isInventoryChanged = isInventoryChanged;
    }

    public MapDiff getMapDiff() {
        return mapDiff;
    }

    public void setMapDiff(MapDiff mapDiff) {
        this.mapDiff = mapDiff;
    }

    public boolean isInventoryChanged() {
        return isInventoryChanged;
    }

    public MobWithPosition getNewMobPosition() {
        return newMobPosition;
    }

    public void setNewMobPosition(MobWithPosition newMobPosition) {
        this.newMobPosition = newMobPosition;
    }
}
