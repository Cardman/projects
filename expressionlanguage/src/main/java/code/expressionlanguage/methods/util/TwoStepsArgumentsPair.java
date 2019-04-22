package code.expressionlanguage.methods.util;

public final class TwoStepsArgumentsPair extends ArgumentsPair {

    private boolean calledIndexer;

    public boolean isCalledIndexer() {
        return calledIndexer;
    }

    public void setCalledIndexer(boolean _calledIndexer) {
        calledIndexer = _calledIndexer;
    }
}
