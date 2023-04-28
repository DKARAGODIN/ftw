package pro.karagodin.generators;

import pro.karagodin.models.Item;
import pro.karagodin.models.TypeOfItem;

public class ItemGenerator {
    private static Integer statValueByLevel(int level) {
        return (int) (Math.random() * level + level);
    }

    public static Item generateWeapon(int level) {
        var weapon = new Item();
        weapon.setAttackIncrement(statValueByLevel(level));
        weapon.setType(TypeOfItem.AttackingItem);
        return weapon;
    }

    public static Item generateHealth(int level) {
        var med = new Item();
        med.setHpIncrement(statValueByLevel(level));
        med.setType(TypeOfItem.RevitalizingItem);
        return med;
    }

    public static Item generateDefence(int level) {
        var def = new Item();
        def.setHpIncrement(statValueByLevel(level));
        def.setType(TypeOfItem.ProtectingItem);
        return def;
    }
}
