package pro.karagodin.effects;

import pro.karagodin.actions.Direction;
import pro.karagodin.models.Mob;

/**
 * Side effect of the attack
 */
public interface Effect {

    /**
     * Applies the effect to victim of attack
     */
    void doEffect(Mob defending, Direction directionOfImpact);
}
