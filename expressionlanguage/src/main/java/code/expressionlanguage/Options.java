package code.expressionlanguage;

public final class Options {

    private boolean breadthFirst = true;

    private boolean eqPlus;
    private boolean multipleAffectations;

    public boolean applyEqPlus() {
        if (!isMultipleAffectations()) {
            return true;
        }
        return eqPlus;
    }

    public boolean isBreadthFirst() {
        return breadthFirst;
    }

    public void setBreadthFirst(boolean _breadthFirst) {
        breadthFirst = _breadthFirst;
    }

    public boolean isEqPlus() {
        return eqPlus;
    }

    public void setEqPlus(boolean _eqPlus) {
        eqPlus = _eqPlus;
    }

    public boolean isMultipleAffectations() {
        return multipleAffectations;
    }

    public void setMultipleAffectations(boolean _multipleAffectations) {
        multipleAffectations = _multipleAffectations;
    }
}
