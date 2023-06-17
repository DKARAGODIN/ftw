package pro.karagodin.game_engine;

import java.io.IOException;
import java.util.Random;

import pro.karagodin.ai_system.Action;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.generators.CaveMapGenerator;
import pro.karagodin.generators.ComplexMobFactory;
import pro.karagodin.generators.MapBuilder;
import pro.karagodin.generators.SimpleMobFactory;
import pro.karagodin.generators.WallsMapGenerator;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

/**
 * Game round.
 */
public class Stage {

    private final Printer printer;
    private final Judge judge;
    private final Map map;
    private final Timeline timeline;
    private final Random random = new Random();

    public Stage(Printer printer, Player player, int currentStage, int difficlulty) {
        this.printer = printer;

        var mapBuilder = new MapBuilder()
                .setStage(currentStage)
                .setPlayer(player)
                .setSize(50, 100)
                .setMobFactory(difficlulty < 5 ?
                        new SimpleMobFactory(currentStage, difficlulty) :
                        new ComplexMobFactory(currentStage, difficlulty));
        if (currentStage == 1)
            mapBuilder = mapBuilder.setLoadFromFile("/stages/ftw");
        else if (random.nextInt(2) == 0)
            mapBuilder = mapBuilder.setGenerating(new CaveMapGenerator());
        else
            mapBuilder = mapBuilder.setGenerating(new WallsMapGenerator());

        this.map = mapBuilder.build();
        this.timeline = new Timeline(this.map);
        this.judge = new Judge(player, this.timeline);
    }

    public boolean start() throws IOException {
        printer.printMap(map);
        while (!judge.isStageOver()) {
            if (timeline.getDeltaTimeForAction() > 0) {
                try {
                    Thread.sleep(Math.max(0, timeline.getDeltaTimeForAction()));
                } catch (InterruptedException ignore) {
                }
            } else {
                MobWithPosition mobAndCoord = timeline.getMobForDoingAction();
                if (mobAndCoord == null)
                    continue;

                Action mobAction = mobAndCoord.getNextAction(map);
                GameDiff gameDiff = judge.doAction(mobAction, mobAndCoord, map);
                if (gameDiff != null) {
                    printer.updateCoordinates(map, gameDiff.getMapDiff());
                    if (gameDiff.isPlayerStatsChanged()) {
                        printer.refreshHeroStats();
                    }
                    if (gameDiff.isInventoryChanged()) {
                        printer.refreshInventory();
                    }
                    mobAndCoord = gameDiff.getNewMobPosition();
                }
                timeline.addUpdatedMob(mobAndCoord);
            }
        }
        return judge.isGameOver();
    }
}
