package pro.karagodin.game_engine;

import java.util.ArrayList;

public class MapDiff {
    private final ArrayList<Coordinate> updatedCoordinatesInMap;

    public MapDiff() {
        updatedCoordinatesInMap = new ArrayList<>();
    }

    public MapDiff(Coordinate position) {
        updatedCoordinatesInMap = new ArrayList<>(1);
        updatedCoordinatesInMap.add(position);
    }

    public void addNewCoordinate(Coordinate updatedCoordinate) {
        updatedCoordinatesInMap.add(updatedCoordinate);
    }

    public ArrayList<Coordinate> getUpdatedCoordinatesInMap() {
        return updatedCoordinatesInMap;
    }
}
