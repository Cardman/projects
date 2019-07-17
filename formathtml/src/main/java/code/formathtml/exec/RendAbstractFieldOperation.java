package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.AbstractFieldOperation;

public abstract class RendAbstractFieldOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {

    private boolean intermediate;

    private Argument previousArgument;

    private int off;

    public RendAbstractFieldOperation(AbstractFieldOperation _a) {
        super(_a);
        intermediate = _a.isIntermediateDottedOperation();
        previousArgument = _a.getPreviousArgument();
        off = _a.getOff();
    }

    public int getOff() {
        return off;
    }

    @Override
    public final void calculate(ExecutableCode _conf) {
        Argument previous_ = getPreviousArg(this,_conf);
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
        if (this instanceof RendSettableFieldOperation) {
            RendSettableFieldOperation s_ = (RendSettableFieldOperation) this;
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

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }
}
