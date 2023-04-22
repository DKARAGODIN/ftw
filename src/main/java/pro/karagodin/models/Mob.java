package pro.karagodin.models;

import pro.karagodin.time.TimeInterval;

public class Mob {
    protected double hp;
    protected int maxHp;
    protected TimeInterval pace;

    public Mob(int maxHp) {
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.pace = new TimeInterval(100);
    }

    public Mob(double hp, int maxHp) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.pace = new TimeInterval(100);
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
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

}
