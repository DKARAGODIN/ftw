package pro.karagodin.game_logic;

import pro.karagodin.ai_system.Effect;
import pro.karagodin.models.Mob;

import java.util.Random;

/**
 * Calculates effects when combat happens
 */
public class CombatSystem {

    private final Random random = new Random();

    /**
     * Formula taken from Heroes 3.
     * Attacker does first damage and if defender still alive, he strikes back
     * HP won't take negative values
     * @param attacker
     * @param defending
     */

    public void attack(Mob attacker, Mob defending) {
        double attackerDamage = doDamage(attacker, defending);
        defending.setHp(defending.getHp() - (int) attackerDamage);
        if (defending.getHp() > 0) {
            double defendingDamage = doDamage(defending, attacker);
            attacker.setHp(attacker.getHp() - (int) defendingDamage);
            if (attacker.getHp() < 0) {
                attacker.setHp(0);
            }
        } else {
            defending.setHp(0);
        }
        for (Effect effect : defending.getAttackEffects()) {
            effect.doEffect(defending);
        }
    }

    private double doDamage(Mob attacker, Mob defending) {
        double attakerAtackModif = attacker.getAttack() - defending.getDefence();
        if (attakerAtackModif > 60) attakerAtackModif = 60;
        if (attakerAtackModif < -60) attakerAtackModif = -60;

        if (attakerAtackModif >= 0) {
            attakerAtackModif = 1 + attakerAtackModif * 0.05;
        } else if (attakerAtackModif < 0) {
            attakerAtackModif = 1 / (1 + (-attakerAtackModif) * 0.05);
        }

        int i = attacker.getMinDamage() == attacker.getMaxDamage() ?
                attacker.getMinDamage() :
                random.nextInt(attacker.getMaxDamage() - attacker.getMinDamage()) + attacker.getMinDamage();
        return i * attakerAtackModif;
    }
}
