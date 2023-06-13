package pro.karagodin.generators;

import pro.karagodin.models.Map;
import pro.karagodin.models.Player;

public class MapBuilder {
    private int height, weight;
    private MapGenerator mapGenerator = new CaveMapGenerator();
    private Player player;
    private int stage = 1;
    private String filename;
    private MobFactory mobFactory;
    private boolean isMapGenerating = true;

    private void checkPlayer() {
        if (player == null)
            throw new RuntimeException("player is not set");
    }

    private void checkMobFactory() {
        if (mobFactory == null)
            mobFactory = new SimpleMobFactory(stage);
    }

    private Map buildByGenerating() {
        if (height == 0 || weight == 0)
            throw new RuntimeException("Size of generated map is not set");
        checkPlayer();
        checkMobFactory();
        return mapGenerator.createMap(player, mobFactory, stage, height, weight);
    }

    private Map buildByLoadingFromFile() {
        checkPlayer();
        checkMobFactory();
        var loader = new MapLoader(filename);
        return loader.loadMap(player, mobFactory, stage);
    }


    public MapBuilder setMobFactory(MobFactory mobFactory) {
        this.mobFactory = mobFactory;
        return this;
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

    public MapBuilder setGenerating(MapGenerator mapGenerator) {
        this.isMapGenerating = true;
        this.mapGenerator = mapGenerator;
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
