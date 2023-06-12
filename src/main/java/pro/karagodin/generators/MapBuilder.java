package pro.karagodin.generators;

import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class MapBuilder {
    private int height, weight;
    private boolean isMapGenerating = true;
    private Player player;
    private int stage = 0;
    private String filename;

    private Map buildByGenerating() {
        if (height == 0 || weight == 0)
            throw new RuntimeException("Size of generated map is not set");
        var generator = new MapGenerator(stage, height, weight);
        return generator.createMap(player);
    }

    private Map buildByLoadingFromFile() {
        var loader = new MapLoader(filename);
        return loader.createMap(player);
    }

    public MapBuilder setSize(int h, int w) {
        height = h;
        weight = w;
        return this;
    }

    public MapBuilder setStage(int stage) {
        this.stage = stage;
        return this;
    }

    public MapBuilder setIsGenerating() {
        this.isMapGenerating = true;
        return this;
    }

    public MapBuilder setLoadFromFile(String file) {
        this.filename = file;
        this.isMapGenerating = false;
        return this;
    }

    public MapBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Map build() {
        if (isMapGenerating)
            return buildByGenerating();
        return buildByLoadingFromFile();
    }

}
