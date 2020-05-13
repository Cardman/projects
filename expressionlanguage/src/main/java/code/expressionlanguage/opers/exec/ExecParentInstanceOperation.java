package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ParentInstanceOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public class ExecParentInstanceOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private boolean intermediate;
    private int off;

    ExecParentInstanceOperation(ParentInstanceOperation _l) {
        super(_l);
        intermediate = _l.isIntermediate();
        off = _l.getOff();
        setPreviousArgument(null);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument arg_ = getCommonArgument(_nodes,_conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    Argument getCommonArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        Struct struct_;
        if (isIntermediateDottedOperation()) {
            Argument previous_ = getPreviousArg(this, _nodes, _conf);
            struct_ = previous_.getStruct();
        } else {
            struct_ = ip_.getGlobalArgument().getStruct();
        }
        Argument a_ = new Argument();
        a_.setStruct(ClassArgumentMatching.convert(getResultClass(),struct_.getParent(),_conf));
        return a_;
    }

    @Override
    public Argument getPreviousArgument() {
        return null;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
    }
}
