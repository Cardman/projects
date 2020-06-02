package code.expressionlanguage.opers.util;
import code.util.CustList;

public final class ParametersGroup {

    private boolean error;

    private CustList<ClassMatching> parameters = new CustList<ClassMatching>();

    public ParametersGroup() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean _error) {
        error = _error;
    }

    public int size() {
        return parameters.size();
    }

    public void add(ClassMatching _cl) {
        parameters.add(_cl);
    }
}
