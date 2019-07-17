package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AssocationOperation;

public final class RendAssocationOperation extends RendAbstractUnaryOperation {

    private String fieldName;

    public RendAssocationOperation(AssocationOperation _a) {
        super(_a);
        fieldName = _a.getFieldName();
    }
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument arg_ = getFirstChild().getArgument();
        setSimpleArgument(arg_, _conf);
    }

}
