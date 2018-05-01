package code.expressionlanguage;

public final class Options {

    private boolean breadthFirst = true;

    private boolean multipleAffectations;

    public boolean isBreadthFirst() {
        return breadthFirst;
    }

    public void setBreadthFirst(boolean _breadthFirst) {
        breadthFirst = _breadthFirst;
    }

    public boolean isMultipleAffectations() {
        return multipleAffectations;
    }

    public void setMultipleAffectations(boolean _multipleAffectations) {
        multipleAffectations = _multipleAffectations;
    }
}
