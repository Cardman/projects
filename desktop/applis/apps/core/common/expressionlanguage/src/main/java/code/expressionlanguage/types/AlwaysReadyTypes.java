package code.expressionlanguage.types;

public final class AlwaysReadyTypes implements ReadyTypes {
    @Override
    public boolean isReady(String _type) {
        return true;
    }
}
