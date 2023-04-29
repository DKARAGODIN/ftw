package pro.karagodin.game_logic;

import pro.karagodin.ai_system.Effect;
import pro.karagodin.models.Mob;

/**
 * Calculates effects when combat happens
 */
public class CombatSystem {

    public void attack(Mob attacker, Mob attacked) {
        for (Effect effect : attacker.getAttackEffects()) {
            effect.doEffect(attacked);
        }
    }
}
