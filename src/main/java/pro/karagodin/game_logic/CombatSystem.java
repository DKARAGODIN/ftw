package pro.karagodin.game_logic;

import pro.karagodin.ai_system.Effect;
import pro.karagodin.models.Mob;

public class CombatSystem {

    public void attack(Mob attacker, Mob attacked) {
        for (Effect effect : attacker.getAttackEffects()) {
            effect.doEffect(attacked);
        }
    }
}
