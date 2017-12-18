package code.expressionlanguage.stds;

import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;

public final class StandardConstructor extends StandardNamedFunction {

    private static final String DEFAULT_NAME = "";

    protected StandardConstructor(StringList _parametersTypes, boolean _varargs) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs);
    }

    public ConstructorId getId(String _className) {
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(new ClassName(n_, i + 1 == len_ && isVarargs()));
        }
        return new ConstructorId(_className, pTypes_);
    }

    public String getPrettyString(String _className) {
        return getId(_className).getSignature();
    }
}
