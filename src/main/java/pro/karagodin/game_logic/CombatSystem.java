package pro.karagodin.game_logic;

import java.util.Random;

import pro.karagodin.ai_system.Action;
import pro.karagodin.ai_system.Effect;
import pro.karagodin.models.Mob;

/**
 * Calculates effects when combat happens
 */
public class CombatSystem {

    private final Random random = new Random();

    /**
     * Formula taken from Heroes 3.
     * Attacker does first damage and if defender still alive, he strikes back
     * HP won't take negative values
     */

    public void attack(Mob attacker, Mob defending, Action directionOfImpact) {
        double attackerDamage = getDamage(attacker, defending);
        defending.setHp(defending.getHp() - (int) attackerDamage);
        for (Effect effect : attacker.getAttackEffects()) {
            effect.doEffect(defending, attacker, directionOfImpact);
        }
    }

    private double getDamage(Mob attacker, Mob defending) {
        double attackerAtackModif = attacker.getAttack() - defending.getDefence();
        if (attackerAtackModif > 60) attackerAtackModif = 60;
        if (attackerAtackModif < -60) attackerAtackModif = -60;

        if (attackerAtackModif >= 0) {
            attackerAtackModif = 1 + attackerAtackModif * 0.05;
        } else {
            attackerAtackModif = 1 / (1 + (-attackerAtackModif) * 0.05);
        }
        double attackerDamage = random.nextDouble() * (attacker.getMaxDamage() - attacker.getMinDamage()) + attacker.getMinDamage();
        return attackerDamage * attackerAtackModif;
    }
}
