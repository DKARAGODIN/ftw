package pro.karagodin.game_engine;

/**
 * Game changes after quantum of time
 */
public class GameDiff {
    private MapDiff mapDiff;
    private MobWithPosition newMobPosition;
    private boolean isInventoryChanged = false;
    private boolean playerStatsChanged = false;

    public GameDiff() {
    }

    public GameDiff(MobWithPosition oldMobPosition) {
        this(new MapDiff(oldMobPosition.getPosition()), oldMobPosition, false);
    }

    public GameDiff(MapDiff mapDiff, MobWithPosition newMobPosition) {
        this(mapDiff, newMobPosition, false);
    }

    public GameDiff(MapDiff mapDiff, MobWithPosition newMobPosition, boolean isInventoryChanged) {
        this(mapDiff, newMobPosition, isInventoryChanged, false);
    }

    public GameDiff(MapDiff mapDiff, MobWithPosition newMobPosition, boolean isInventoryChanged, boolean playerStatsChanged) {
        this.mapDiff = mapDiff;
        this.newMobPosition = newMobPosition;
        this.isInventoryChanged = isInventoryChanged;
        this.playerStatsChanged = playerStatsChanged;
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

    public boolean isPlayerStatsChanged() {
        return playerStatsChanged;
    }

    public void setPlayerStatsChanged(boolean playerStatsChanged) {
        this.playerStatsChanged = playerStatsChanged;
    }
}
