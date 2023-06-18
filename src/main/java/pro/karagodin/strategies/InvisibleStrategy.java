package pro.karagodin.strategies;

/**
 * Hides mob on the map.
 * ToDo - invisible mobs can be detected if they step into cell with some item. Player can detect that item disappeared for a moment
 */
public class InvisibleStrategy extends DecoratingStrategy {

    public InvisibleStrategy(Strategy subStrategy) {
        this.subStrategy = subStrategy;
    }

    @Override
    protected Strategy constructor(Strategy subStrategy) {
        return new InvisibleStrategy(subStrategy);
    }

    @Override
    public char getView() {
        return ' ';
    }
}
