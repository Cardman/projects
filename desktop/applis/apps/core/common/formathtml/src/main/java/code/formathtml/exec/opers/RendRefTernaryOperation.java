package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendRefTernaryOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final int offsetLocal;

    public RendRefTernaryOperation(ExecOperationContent _content, int _offsetLocal,ExecArrContent _arrContent) {
        super(_content,false,_arrContent);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _rendStack);
        ArgumentsPair ch_ = getChosenArgumentsPair(_nodes);
        AbstractWrapper res_ = ch_.getWrapper();
        if (res_ != null) {
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            ExecHelper.fwdWrapper(pair_,ch_);
            Argument arg_ = ExecTemplates.getArgValue(res_, _context, _stack);
            if (resultCanBeSet()) {
                setQuickNoConvertSimpleArgument(arg_, _nodes,_context, _stack);
                return;
            }
            setSimpleArgument(arg_, _nodes,_context, _stack,_rendStack);
        } else {
            setSimpleArgument(ch_.getArgument(), _nodes,_context, _stack,_rendStack);
        }

    }

    private ArgumentsPair getChosenArgumentsPair(IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        ArgumentsPair arg_;
        if (BooleanStruct.isTrue(getArgumentPair(_nodes,getNode(getChildrenNodes(),0)).getArgument().getStruct())) {
            arg_ = getArgumentPair(_nodes, getNode(getChildrenNodes(),1));
        } else {
            arg_ = getArgumentPair(_nodes,getNode(getChildrenNodes(),2));
        }
        return arg_;
    }

}
