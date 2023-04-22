package pro.karagodin.models;

public class Mob {
    protected double hp;
    protected int maxHp;
    protected Cell position;

    public Mob(int maxHp, Cell position) {
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.position = position;
    }

    public Mob(double hp, int maxHp, Cell position) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.position = position;
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

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}
