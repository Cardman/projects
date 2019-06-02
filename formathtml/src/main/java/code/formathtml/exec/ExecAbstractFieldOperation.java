package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.AbstractFieldOperation;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public abstract class ExecAbstractFieldOperation extends ExecVariableLeafOperation implements ExecPossibleIntermediateDotted, ReductibleOperable {

    private boolean intermediate;

    private Argument previousArgument;

    private int off;

    public ExecAbstractFieldOperation(AbstractFieldOperation _a) {
        super(_a);
        intermediate = _a.isIntermediateDottedOperation();
        previousArgument = _a.getPreviousArgument();
        off = _a.getOff();
    }

    public int getOff() {
        return off;
    }
    public void setStaticAccess(boolean _staticAccess) {
    }
    public boolean isStaticAccess() {
        return false;
    }

    @Override
    public final void calculate(ExecutableCode _conf) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument argres_ = getCommonArgument(previous_, _conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            argres_ = getCommonArgument(previous_, _conf);
        }
        Argument arg_ = argres_;
        if (arg_ == null) {
            return;
        }
        boolean simple_ = false;
        if (this instanceof ExecSettableFieldOperation) {
            ExecSettableFieldOperation s_ = (ExecSettableFieldOperation) this;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickSimpleArgument(arg_, _conf);
        } else {
            setSimpleArgument(arg_, _conf);
        }
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
    abstract Argument getCommonArgument(Argument _previous, ExecutableCode _conf);

    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }
}
