package pro.karagodin.game_engine;

import com.googlecode.lanterna.input.KeyStroke;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.models.Map;
import pro.karagodin.models.Player;
import pro.karagodin.output.Printer;

import java.io.IOException;

public class Stage {

    private Printer printer;
    private Judge judge;
    private Player player;
    private Map map;
    private Timeline timeline;

    public Stage(Printer printer, Player player) {
        this.printer = printer;
        this.judge = new Judge();
        this.player = player;
        this.map = new Map(60, 195, player);
        this.timeline = new Timeline(this.map);
    }

    public StageEnd start() throws IOException {
        while (true) {
            if (timeline.getDeltaTimeForAction() > 0) {
                try {
                    Thread.sleep(Math.max(0, timeline.getDeltaTimeForAction()));
                } catch (InterruptedException ignore) {}
            } else {
                Coordinate mobCoord = timeline.getMobForDoingAction();
                KeyStroke key = printer.pressedKey();
                if (key != null) {
                    GameDiff gameDiff = judge.doPlayerAction(key, mobCoord, map);
                    printer.updateCoordinates(map, gameDiff.getMapDiff());
                    mobCoord = gameDiff.getNewPlayerCoordinate();
                }
                timeline.addUpdatedMob(map.getCell(mobCoord).getUnit(), mobCoord);
            }
        }
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
