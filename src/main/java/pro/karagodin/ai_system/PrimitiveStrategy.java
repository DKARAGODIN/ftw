package pro.karagodin.ai_system;

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
