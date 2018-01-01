package code.expressionlanguage.opers.util;
import code.util.CustList;
import code.util.ints.Displayable;

public final class ParametersGroup extends CustList<ClassMatching> implements Displayable {

    private static final String SEP = ",";
    private static final String LEFT_PAR = "(";
    private static final String RIGHT_PAR = ")";
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

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(LEFT_PAR);
        for (ClassMatching c: this) {
            str_.append(c.getClassName());
            str_.append(SEP);
        }
        str_.deleteCharAt(str_.length() - 1);
        str_.append(RIGHT_PAR);
        return str_.toString();
    }
}
