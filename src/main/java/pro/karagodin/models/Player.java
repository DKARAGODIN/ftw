package pro.karagodin.models;

public class Player extends Mob {
    public Player(int maxHp) {
        super(maxHp);
    }

    public Player(double hp, int maxHp) {
        super(hp, maxHp);
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
