package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendAffectationOperation extends RendAbstractAffectOperation {

    public RendAffectationOperation(ExecOperationContent _content,StringList _names) {
        super(_content, _names);
    }

    @Override
    protected void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        if (getSettable() instanceof RendStdRefVariableOperation && ((RendStdRefVariableOperation) getSettable()).isDeclare()) {
            CustList<RendDynOperationNode> childrenNodes_ = getChildrenNodes();
            ArgumentsPair pairRight_ = getArgumentPair(_nodes, getNode(childrenNodes_, childrenNodes_.size() - 1));
            _rendStack.getLastPage().getRefParams().put(((RendStdRefVariableOperation) getSettable()).getVariableName(), pairRight_.getWrapper());
            setQuickNoConvertSimpleArgument(NullStruct.NULL_VALUE, _nodes, _context, _rendStack);
            return;
        }
        RendDynOperationNode right_ = getLastNode(this);
        Struct rightArg_ = getArgument(_nodes,right_);
        Struct arg_ = calculateChSetting(_nodes, rightArg_, _context, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
