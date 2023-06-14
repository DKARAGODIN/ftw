package pro.karagodin.ai_system;

import pro.karagodin.models.Mob;

public interface Effect {

    /**
     * Side effect of the attack
     */
    void doEffect(Mob defending, Mob attacker, Action directionOfImpact);
}
