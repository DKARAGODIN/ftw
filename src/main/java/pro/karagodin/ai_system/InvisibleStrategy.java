package pro.karagodin.ai_system;

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
