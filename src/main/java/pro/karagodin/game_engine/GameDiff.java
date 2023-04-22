package pro.karagodin.game_engine;

public class GameDiff {
    private MapDiff mapDiff;
    private MobWithPosition newMobPosition;
    private boolean inventoryMode;

    public GameDiff(MapDiff mapDiff, MobWithPosition newMobPosition) {
        this.mapDiff = mapDiff;
        this.newMobPosition = newMobPosition;
    }

    public MapDiff getMapDiff() {
        return mapDiff;
    }

    public void setMapDiff(MapDiff mapDiff) {
        this.mapDiff = mapDiff;
    }

    public MobWithPosition getNewMobPosition() {
        return newMobPosition;
    }

    public void setNewMobPosition(MobWithPosition newMobPosition) {
        this.newMobPosition = newMobPosition;
    }
}
