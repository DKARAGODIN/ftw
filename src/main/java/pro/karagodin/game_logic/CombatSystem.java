package pro.karagodin.game_logic;

import java.util.Random;

import pro.karagodin.actions.Direction;
import pro.karagodin.effects.Effect;
import pro.karagodin.models.Mob;

/**
 * Calculates effects when combat happens
 */
public class CombatSystem {

    private static final int MAX_ATTACK_DEFENCE_DIF = 60;
    private static final double PARAM_DIFF_MULTIPLIER = 0.05;

    private final Random random = new Random();

    /**
     * Formula taken from Heroes 3.
     * HP won't take negative values
     */
    public void attack(Mob attacker, Mob defending, Direction directionOfImpact) {
        double attackerDamage = getDamage(attacker, defending);
        defending.setHp(defending.getHp() - (int) attackerDamage);
        for (Effect effect : attacker.getAttackEffects()) {
            effect.doEffect(defending, directionOfImpact);
        }
    }

    private double getDamage(Mob attacker, Mob defending) {
        double attackerAtackModif = attacker.getAttack() - defending.getDefence();
        if (attackerAtackModif > MAX_ATTACK_DEFENCE_DIF) attackerAtackModif = MAX_ATTACK_DEFENCE_DIF;
        if (attackerAtackModif < -MAX_ATTACK_DEFENCE_DIF) attackerAtackModif = -MAX_ATTACK_DEFENCE_DIF;

        if (attackerAtackModif >= 0) {
            attackerAtackModif = 1 + attackerAtackModif * PARAM_DIFF_MULTIPLIER;
        } else {
            attackerAtackModif = 1 / (1 + (-attackerAtackModif) * PARAM_DIFF_MULTIPLIER);
        }
        double attackerDamage = random.nextDouble() * (attacker.getMaxDamage() - attacker.getMinDamage()) + attacker.getMinDamage();
        return attackerDamage * attackerAtackModif;
    }
}
