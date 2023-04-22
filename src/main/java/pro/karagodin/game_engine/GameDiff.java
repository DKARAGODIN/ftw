package pro.karagodin.game_engine;

public class GameDiff {
    private MapDiff mapDiff;
    private Coordinate newPlayerCoordinate;

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
}
