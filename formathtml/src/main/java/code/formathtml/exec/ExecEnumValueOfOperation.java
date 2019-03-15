package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.EnumValueOfOperation;

public final class ExecEnumValueOfOperation extends ExecAbstractUnaryOperation {

    private String className;
    private int argOffset;

    public ExecEnumValueOfOperation(EnumValueOfOperation _e) {
        super(_e);
        className = _e.getClassName();
        argOffset = _e.getArgOffset();
    }


    @Override
    public void calculate(ExecutableCode _conf) {
        ExecDynOperationNode first_ = getFirstChild();
        Argument arg_ = first_.getArgument();
        Argument argres_ = getCommonArgument(arg_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getCommonArgument(arg_, _conf);
        }
        if (_conf.getContextEl().hasException()) {
            return;
        }
        Argument argRes_ = argres_;
        setSimpleArgument(argRes_, _conf);
    }

    Argument getCommonArgument(Argument _argument, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValue(className, _argument, _conf);
    }

}
