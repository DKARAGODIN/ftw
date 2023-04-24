package pro.karagodin.generators;

import pro.karagodin.models.Item;

public class ItemGenerator {
    private static Integer statValueByLevel(int level) {
        return (int) (Math.random() * level + level);
    }

    public static Item generateWeapon(int level) {
        var weapon = new Item();
        weapon.setAttackIncrement(statValueByLevel(level));
        weapon.setView('/');
        return weapon;
    }

    public static Item generateHealth(int level) {
        var med = new Item();
        med.setHpIncrement(statValueByLevel(level));
        med.setView('+');
        return med;
    }

    public static Item generateDefence(int level) {
        var def = new Item();
        def.setHpIncrement(statValueByLevel(level));
        def.setView('%');
        return def;
    }
}
