package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecAffectationOperation extends ExecAbstractAffectOperation {

    public ExecAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _opOffset, StringList _names) {
        super(_opCont, _opOffset, _names);
    }

    @Override
    protected void calculateAffect(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        if (getSettable() instanceof ExecStdRefVariableOperation && ((ExecStdRefVariableOperation) getSettable()).isDeclare()) {
            CustList<ExecOperationNode> childrenNodes_ = getChildrenNodes();
            ArgumentsPair pairRight_ = ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(childrenNodes_, childrenNodes_.size() - 1));
            AbstractPageEl ip_ = _stack.getLastPage();
            ip_.getRefParams().put(((ExecStdRefVariableOperation) getSettable()).getVariableName(), ExecTemplates.getWrap(pairRight_.getWrapper()));
            setQuickNoConvertSimpleArgument(new Argument(), _conf, _nodes, _stack);
            return;
        }
        Argument rightArg_ = getLastArgument(_nodes, this);
        Argument arg_ = calculateChSetting(_nodes, _conf, rightArg_, _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
