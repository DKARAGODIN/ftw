package pro.karagodin.generators;

import pro.karagodin.models.Mob;

public interface MobFactory {

    Mob createAggressiveMob();

    Mob createPassiveAggressiveMob();

    Mob createPassiveMob();

    Mob createCowardMob();
}
