package code.expressionlanguage.opers.util;
import code.util.CustList;

public final class ParametersGroup extends CustList<ClassMatching> {

    private boolean error;

    public ParametersGroup() {
    }

    public ParametersGroup(ClassMatching _element) {
        super(_element);
    }

    public ParametersGroup(ClassMatching... _elements) {
        super(_elements);
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean _error) {
        error = _error;
    }
}
