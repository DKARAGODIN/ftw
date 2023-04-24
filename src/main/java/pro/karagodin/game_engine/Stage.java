package pro.karagodin.game_engine;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.ai_system.Action;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.generators.MapGenerator;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

import java.io.IOException;

public class Stage {

    private final Printer printer;
    private final Judge judge;
    private final Map map;
    private final Timeline timeline;

    public Stage(Printer printer, Player player) {
        this.printer = printer;
        this.judge = new Judge(player);
        this.map = MapGenerator.genDefaultMap(1);
        map.setPlayer(player, new Coordinate(97, 10));
        this.timeline = new Timeline(this.map);
    }

    public boolean start() throws IOException {
        printer.printMap(map);
        while (!judge.isStageOver()) {
            if (timeline.getDeltaTimeForAction() > 0) {
                try {
                    Thread.sleep(Math.max(0, timeline.getDeltaTimeForAction()));
                } catch (InterruptedException ignore) {}
            } else {
                MobWithPosition mobAndCoord = timeline.getMobForDoingAction();
                Action mobAction = mobAndCoord.getNextAction(map);
                GameDiff gameDiff = judge.doAction(mobAction, mobAndCoord, map);
                if(gameDiff != null) {
                    printer.updateCoordinates(map, gameDiff.getMapDiff());
                    if(gameDiff.isInventoryChanged())
                        printer.refreshInventory();
                    mobAndCoord = gameDiff.getNewMobPosition();
                }
                timeline.addUpdatedMob(mobAndCoord);
            }
        }
        return judge.isGameOver();
    }

    public static class StageEnd {
        public boolean quitGame = false;
        public boolean nextStage = false;

        public StageEnd(boolean quitGame, boolean nextStage) {
            this.quitGame = quitGame;
            this.nextStage = nextStage;
        }

        public StageEnd() {
        }
    }

}
