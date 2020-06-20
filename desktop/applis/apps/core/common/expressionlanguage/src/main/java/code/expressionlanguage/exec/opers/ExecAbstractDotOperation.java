package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecAbstractDotOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    public ExecAbstractDotOperation(OperationNode _m) {
        super(_m);
    }

    public ExecAbstractDotOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    public void calculateDot(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.last();
        Argument a_ = getArgument(_nodes,o_);
        boolean simple_;
        if (getParent() instanceof ExecAffectationOperation) {
            ExecAffectationOperation aff_ = (ExecAffectationOperation) getParent();
            simple_ = aff_.getSettable() == chidren_.last();
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
