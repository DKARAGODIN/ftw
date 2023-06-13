package pro.karagodin.ai_system;

import java.util.function.Predicate;

import pro.karagodin.models.Mob;

public class NoAttackStrategy extends SmartAttackAroundStrategy {

    public NoAttackStrategy(Strategy subStrategy) {
        this(subStrategy, mob -> false);
    }

    private NoAttackStrategy(Strategy subStrategy, Predicate<Mob> attackMobPredicate) {
        this.subStrategy = subStrategy;
        this.attackMobPredicate = attackMobPredicate;
    }

    @Override
    protected Strategy constructor(Strategy subStrategy) {
        return new NoAttackStrategy(subStrategy, attackMobPredicate);
    }
}
