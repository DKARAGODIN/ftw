package pro.karagodin.ai_system;

public class BloodsuckerStrategy extends InvisibleStrategy {

    public BloodsuckerStrategy(double pursuitIndex, int criticalDistance) {
        super(new AttackAroundStrategy(new PursuitStrategy(pursuitIndex)), criticalDistance);
    }
}
