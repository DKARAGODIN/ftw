package pro.karagodin.ai_system;

import java.util.function.Predicate;

import com.googlecode.lanterna.TextColor;
import pro.karagodin.models.Mob;
import pro.karagodin.models.Player;

public class AttackOnlyPlayerStrategy extends SmartAttackAroundStrategy {

    public AttackOnlyPlayerStrategy(Strategy subStrategy) {
        this(subStrategy, mob -> mob instanceof Player);
    }

    private AttackOnlyPlayerStrategy(Strategy subStrategy, Predicate<Mob> attackMobPredicate) {
        this.subStrategy = subStrategy;
        this.attackMobPredicate = attackMobPredicate;
    }

    @Override
    protected Strategy constructor(Strategy subStrategy) {
        return new AttackOnlyPlayerStrategy(subStrategy, attackMobPredicate);
    }

    @Override
    public TextColor getForeground() {
        return TextColor.ANSI.RED_BRIGHT;
    }
}
