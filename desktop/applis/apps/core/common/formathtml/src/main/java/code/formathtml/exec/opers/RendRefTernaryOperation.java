package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendRefTernaryOperation extends RendMethodOperation implements RendCalculableOperation, RendSettableElResult {

    private final int offsetLocal;
    private final ExecArrContent arrContent;

    public RendRefTernaryOperation(ExecOperationContent _content, int _offsetLocal,ExecArrContent _arrContent) {
        super(_content);
        offsetLocal = _offsetLocal;
        arrContent = _arrContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _rendStack);
        AbstractWrapper res_ = getWrapper(_nodes);
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        pair_.setWrapper(res_);
        Argument arg_ = ExecTemplates.getArgValue(res_, _context, _stack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _nodes,_context, _stack);
            return;
        }
        setSimpleArgument(arg_, _nodes,_context, _stack,_rendStack);
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

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        return trySetArgument(_nodes,_context,_right,_stack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Argument left_ = ExecTemplates.getArgValue(pair_.getWrapper(), _context, _stack);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, arrContent.isCatString(), _cl.getNames(), _cast, _context,_stack);
        return trySetArgument(_nodes,_context,res_,_stack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Argument left_ = ExecTemplates.getArgValue(pair_.getWrapper(), _context, _stack);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        trySetArgument(_nodes, _context, res_, _stack);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        return trySetArgument(_nodes,_context,_right,_stack);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        trySetArgument(_nodes,_context,_right,_stack);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument trySetArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res, StackCall _stackCall) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        return ExecTemplates.trySetArgument(_conf, _res, pair_, _stackCall);
    }
}
