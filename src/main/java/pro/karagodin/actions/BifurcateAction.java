package pro.karagodin.actions;

import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.GameDiff;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.game_logic.Judge;
import pro.karagodin.models.Map;

public record BifurcateAction(Direction direction) implements Action {

    @Override
    public GameDiff doAction(Judge judge, MobWithPosition mobAndCoord, Map map) {
        Coordinate initialPosition = mobAndCoord.getPosition();
        Coordinate newMobCoord = map.getCoordinateByDirection(initialPosition, direction);
        if (newMobCoord != null && judge.canDoMovement(map, newMobCoord)) {
            GameDiff result = judge.doMovement(map, initialPosition, newMobCoord);
            map.getCell(initialPosition).setUnit(mobAndCoord.getMob().cloneMob());
            judge.addNewMobAtTimeline(new MobWithPosition(map, initialPosition));
            return result;
        }
        return new GameDiff(mobAndCoord);
    }
}
