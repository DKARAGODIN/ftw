package pro.karagodin.models;

import pro.karagodin.ai_system.Action;
import pro.karagodin.ai_system.Strategy;
import pro.karagodin.game_engine.Coordinate;
import pro.karagodin.game_engine.MobWithPosition;
import pro.karagodin.time.TimeInterval;

public class Mob {
    protected int hp;
    protected int maxHp;
    protected TimeInterval pace;
    protected Strategy strategy;

    public Mob(int hp, int maxHp, TimeInterval pace, Strategy strategy) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.pace = pace;
        this.strategy = strategy;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public TimeInterval getPace() {
        return pace;
    }

    public void setPace(TimeInterval pace) {
        this.pace = pace;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy newStrategy) {
        this.strategy = newStrategy;
    }
}
