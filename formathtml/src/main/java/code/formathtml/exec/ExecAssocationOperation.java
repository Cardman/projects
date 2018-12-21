package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AssocationOperation;

public final class ExecAssocationOperation extends ExecAbstractUnaryOperation {

    private String fieldName;

    public ExecAssocationOperation(AssocationOperation _a) {
        super(_a);
        fieldName = _a.getFieldName();
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        Argument arg_ = getFirstChild().getArgument();
        setSimpleArgumentAna(arg_, _conf);
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
