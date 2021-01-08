package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.ExecTernaryOperation;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class RendRefTernaryOperation extends RendMethodOperation implements RendCalculableOperation {

    private final int offsetLocal;

    public RendRefTernaryOperation(ExecOperationContent _content, int _offsetLocal) {
        super(_content);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _rendStack);
        AbstractWrapper res_ = getWrapper(_nodes);
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        pair_.setWrapper(res_);
        setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context, _stack);
    }

    private AbstractWrapper getWrapper(IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        AbstractWrapper arg_;
        if (BooleanStruct.isTrue(getArgumentPair(_nodes,getNode(getChildrenNodes(),0)).getArgument().getStruct())) {
            arg_ = getArgumentPair(_nodes, getNode(getChildrenNodes(),1)).getWrapper();
        } else {
            arg_ = getArgumentPair(_nodes,getNode(getChildrenNodes(),2)).getWrapper();
        }
        return arg_;
    }
}
