package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractFieldOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.IdMap;

public abstract class ExecAbstractFieldOperation extends ExecVariableLeafOperation implements ExecPossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;

    private Argument previousArgument;

    private int off;

    public ExecAbstractFieldOperation(AbstractFieldOperation _a) {
        super(_a);
        previousResultClass = _a.getPreviousResultClass();
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
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
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
    @Override
    public final Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument arg_ = getCommonArgument(previous_, _conf);
        boolean simple_ = false;
        if (this instanceof ExecSettableFieldOperation) {
            ExecSettableFieldOperation s_ = (ExecSettableFieldOperation) this;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickSimpleArgument(arg_, _conf, _nodes);
        } else {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }
    abstract Argument getCommonArgument(Argument _previous, ExecutableCode _conf);


    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }
}
