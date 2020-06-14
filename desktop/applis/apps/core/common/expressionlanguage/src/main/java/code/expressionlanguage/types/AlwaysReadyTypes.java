package code.expressionlanguage.types;

public final class AlwaysReadyTypes implements ReadyTypes {
    public AlwaysReadyTypes(){}
    @Override
    public boolean isReady(String _type) {
        return true;
    }

}
