package pro.karagodin.generators;

import pro.karagodin.models.Mob;

/**
 * Generate mob
 */
public interface MobFactory {

    /**
     * @return Powerefull mob which pursuits player
     */
    Mob createAggressiveMob();

    /**
     * @return Powerefull mob which generally does not care about player
     */
    Mob createPassiveAggressiveMob();

    /**
     * @return Weak mob which do not attack
     */
    Mob createPassiveMob();

    /**
     * @return Weak mob but can strike back
     */
    Mob createCowardMob();
}
