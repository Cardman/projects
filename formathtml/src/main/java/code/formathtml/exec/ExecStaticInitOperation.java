package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.StaticInitOperation;

public final class ExecStaticInitOperation extends ExecVariableLeafOperation {

    private boolean possibleInitClass;

    public ExecStaticInitOperation(StaticInitOperation _s) {
        super(_s);
        possibleInitClass = _s.isPossibleInitClass();
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument current_ = getArgument();
        Argument argres_ = getCommonArgument(current_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getCommonArgument(current_, _conf);
        }
        if (_conf.getContextEl().hasException()) {
            return;
        }
        Argument arg_ = argres_;
        if (arg_ == null) {
            setQuickSimpleArgument(Argument.createVoid(), _conf);
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    Argument getCommonArgument(Argument _argument, ExecutableCode _conf) {
        if (possibleInitClass) {
            String className_ = getResultClass().getName();
            if (ExecInvokingOperation.hasToExit(_conf, className_)) {
                return Argument.createVoid();
            }
        }
        return null;
    }

}
