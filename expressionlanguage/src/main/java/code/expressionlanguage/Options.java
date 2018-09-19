package code.expressionlanguage;

public final class Options {

    private boolean eqPlus;
    private boolean catChars = true;
    private boolean multipleAffectations;
    private boolean initializeStaticClassFirst = true;
    private boolean quickCompare = true;
    private boolean doubleBracketsArray;

    public boolean applyEqPlus() {
        if (!isMultipleAffectations()) {
            return true;
        }
        return eqPlus;
    }

    public boolean isEqPlus() {
        return eqPlus;
    }

    public void setEqPlus(boolean _eqPlus) {
        eqPlus = _eqPlus;
    }

    public boolean isCatChars() {
        return catChars;
    }

    public void setCatChars(boolean _catChars) {
        catChars = _catChars;
    }

    public boolean isMultipleAffectations() {
        return multipleAffectations;
    }

    public void setMultipleAffectations(boolean _multipleAffectations) {
        multipleAffectations = _multipleAffectations;
    }

    public boolean isInitializeStaticClassFirst() {
        return initializeStaticClassFirst;
    }

    public void setInitializeStaticClassFirst(boolean _initializeStaticClassFirst) {
        initializeStaticClassFirst = _initializeStaticClassFirst;
    }

    public boolean isQuickCompare() {
        return quickCompare;
    }

    public void setQuickCompare(boolean _quickCompare) {
        quickCompare = _quickCompare;
    }

    public boolean isDoubleBracketsArray() {
        return doubleBracketsArray;
    }

    public void setDoubleBracketsArray(boolean _doubleBracketsArray) {
        doubleBracketsArray = _doubleBracketsArray;
    }

}
