package pro.karagodin.models;

public class Player extends Mob {
    public Player(int maxHp, Cell position) {
        super(maxHp, position);
    }

    public Player(double hp, int maxHp, Cell position) {
        super(hp, maxHp, position);
    }

    protected int level = 1;
    protected int xp = 0;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
