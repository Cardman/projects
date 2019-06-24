package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.ValuesOperation;

public final class ExecValuesOperation extends ExecLeafOperation implements DirectExecCalculableOperation {

    private String className;
    private int argOffset;

    public ExecValuesOperation(ValuesOperation _v) {
        super(_v);
        className = _v.getClassName();
        argOffset = _v.getArgOffset();
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument argres_ = getCommonArgument(_conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getCommonArgument(_conf);
        }
        if (_conf.getContextEl().hasException()) {
            return;
        }
        Argument argRes_ = argres_;
        setSimpleArgument(argRes_, _conf);
    }

    Argument getCommonArgument(ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValues(className, _conf);
    }

}
