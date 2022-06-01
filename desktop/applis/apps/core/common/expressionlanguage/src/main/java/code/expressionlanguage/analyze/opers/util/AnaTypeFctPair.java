package code.expressionlanguage.analyze.opers.util;

public final class AnaTypeFctPair {

    private AnaTypeFct function;
    private AnaTypeFct functionImpl;
    private int testOffset;

    public AnaTypeFct getFunction() {
        return function;
    }

    public void setFunction(AnaTypeFct _f) {
        this.function = _f;
    }

    public AnaTypeFct getFunctionImpl() {
        return functionImpl;
    }

    public void setFunctionImpl(AnaTypeFct _f) {
        this.functionImpl = _f;
    }

    public int getTestOffset() {
        return testOffset;
    }

    public void setTestOffset(int _t) {
        this.testOffset = _t;
    }
}
