package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.AbstractFieldOperation;
import code.util.IdMap;

public abstract class ExecAbstractFieldOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private boolean intermediate;

    private int off;

    public ExecAbstractFieldOperation(AbstractFieldOperation _a) {
        super(_a);
        intermediate = _a.isIntermediateDottedOperation();
        off = _a.getOff();
    }

    public int getOff() {
        return off;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
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
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes);
        } else {
            setSimpleArgument(arg_, _conf, _nodes);
        }
    }
    abstract Argument getCommonArgument(Argument _previous, ContextEl _conf);

}
