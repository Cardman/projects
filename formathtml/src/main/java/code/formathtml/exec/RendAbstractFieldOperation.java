package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractFieldOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.IdMap;

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
    RendAbstractFieldOperation(Argument _a,int _indexChild, ClassArgumentMatching _res, int _order, boolean _int) {
        super(_indexChild,_res,_order);
        previousArgument = _a;
        intermediate = _int;
    }

    public int getOff() {
        return off;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = getCommonArgument(previous_, _conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        CallingState state_ = _conf.getContextEl().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            argres_ = getCommonArgument(previous_, _conf);
        }
        Argument arg_ = argres_;
        boolean simple_ = false;
        if (this instanceof RendSettableFieldOperation) {
            RendSettableFieldOperation s_ = (RendSettableFieldOperation) this;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickNoConvertSimpleArgument(arg_, _conf,_nodes);
        } else {
            setSimpleArgument(arg_, _conf,_nodes);
        }
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
    abstract Argument getCommonArgument(Argument _previous, Configuration _conf);

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

}
