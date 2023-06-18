package pro.karagodin.strategies;

/**
 * Class of primitive strategy, which usually hasn't inner state
 */
public abstract class PrimitiveStrategy implements Strategy {

    @Override
    public Strategy nextStrategy() {
        return this;
    }

    @Override
    public Strategy cloneStrategy() {
        return this;
    }
}
