package pro.karagodin.models;

public class Mob {
    protected double hp;
    protected int maxHp;

    public Mob(int maxHp) {
        this.hp = maxHp;
        this.maxHp = maxHp;
    }

    public Mob(double hp, int maxHp) {
        this.hp = hp;
        this.maxHp = maxHp;
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

}
