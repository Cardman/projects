package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecAbstractDotOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    protected ExecAbstractDotOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }

    public ExecAbstractDotOperation(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    public void calculateDot(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument a_ = getLastArgument(_nodes,this);
        boolean simple_;
        if (getParent() instanceof ExecAffectationOperation) {
            ExecAffectationOperation aff_ = (ExecAffectationOperation) getParent();
            simple_ = aff_.getSettable() == ExecTemplates.getLastNode(this);
        } else {
            simple_ = false;
        }
        if (simple_) {
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes);
        } else {
            setSimpleArgument(a_, _conf, _nodes);
        }
    }
}
