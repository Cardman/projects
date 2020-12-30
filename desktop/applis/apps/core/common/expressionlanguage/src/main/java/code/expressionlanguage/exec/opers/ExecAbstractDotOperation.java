package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public abstract class ExecAbstractDotOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    protected ExecAbstractDotOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }

    public ExecAbstractDotOperation(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    public void calculateDot(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stackCall) {
        ExecOperationNode lastNode_ = ExecTemplates.getLastNode(this);
        ArgumentsPair pairCh_ = ExecTemplates.getArgumentPair(_nodes, lastNode_);
        Argument a_ = Argument.getNullableValue(pairCh_.getArgument());
        boolean simple_;
        if (getParent() instanceof ExecAffectationOperation) {
            ExecAffectationOperation aff_ = (ExecAffectationOperation) getParent();
            simple_ = aff_.getSettable() == lastNode_;
        } else {
            simple_ = false;
        }
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
        pair_.setWrapper(pairCh_.getWrapper());
        if (simple_) {
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes, _stackCall);
        } else {
            setSimpleArgument(a_, _conf, _nodes, _stackCall);
        }
    }
}
