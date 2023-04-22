package pro.karagodin.game_engine;

import java.util.ArrayList;

public class MapDiff {
    private ArrayList<Coordinate> updatedCoordinatesInMap;

    public MapDiff() {
        updatedCoordinatesInMap = new ArrayList<>();
    }

    public void addNewCoordinate(Coordinate updatedCoordinate) {
        updatedCoordinatesInMap.add(updatedCoordinate);
    }

    public ArrayList<Coordinate> getUpdatedCoordinatesInMap() {
        return updatedCoordinatesInMap;
    }
}
