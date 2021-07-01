package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendRefTernaryOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final int offsetLocal;

    public RendRefTernaryOperation(ExecOperationContent _content, int _offsetLocal,ExecArrContent _arrContent) {
        super(_content,false,_arrContent);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(offsetLocal, _rendStack);
        ArgumentsPair ch_ = getChosenArgumentsPair(_nodes);
        AbstractWrapper res_ = ch_.getWrapper();
        if (res_ != null) {
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            ExecHelper.fwdWrapper(pair_,ch_);
            Argument arg_ = ch_.getArgument();
            if (resultCanBeSet()) {
                setQuickNoConvertSimpleArgument(arg_, _nodes,_context, _rendStack);
                return;
            }
            setSimpleArgument(arg_, _nodes,_context, _rendStack);
        } else {
            setSimpleArgument(ch_.getArgument(), _nodes,_context, _rendStack);
        }

    }

    private ArgumentsPair getChosenArgumentsPair(IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        ArgumentsPair arg_;
        CustList<RendDynOperationNode> childrenNodes_ = getChildrenNodes();
        if (BooleanStruct.isTrue(getArgumentPair(_nodes,getNode(childrenNodes_,0)).getArgument().getStruct())) {
            arg_ = getArgumentPair(_nodes, getNode(childrenNodes_,1));
        } else {
            arg_ = getArgumentPair(_nodes,getNode(childrenNodes_,2));
        }
        return arg_;
    }

}
