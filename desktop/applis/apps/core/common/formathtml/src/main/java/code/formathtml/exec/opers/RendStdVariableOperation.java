package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.SimplePageEl;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendStdVariableOperation  extends RendLeafOperation implements
        RendCalculableOperation,RendSettableElResult{

    private final ExecVariableContent variableContent;

    public RendStdVariableOperation(ExecOperationContent _content, ExecVariableContent _variableContent) {
        super(_content);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _rendStack);
        SimplePageEl ip_ = _rendStack.getPageEl();
        Argument arg_;
        if (resultCanBeSet()) {
            arg_ = Argument.createVoid();
        } else {
            arg_ = ExecTemplates.getWrapValue(_context, variableContent.getVariableName(), variableContent.getDeep(), ip_.getCache(), ip_.getRefParams(), _stack);
        }
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _nodes, _context, _stack);
        } else {
            setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
        }
    }

    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        return processVariable(_right,_context,_stack,_rendStack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, variableContent.isCatString(), _cl.getNames(), _cast, _context, _stack);
        return processVariable(res_,_context,_stack,_rendStack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Struct store_ = _store.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        processVariable(res_,_context,_stack,_rendStack);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        processVariable(_right, _context, _stack, _rendStack);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        processVariable(_right, _context, _stack, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument processVariable(Argument _right, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        SimplePageEl ip_ = _rendStackCall.getPageEl();
        return ExecTemplates.setWrapValue(_context, variableContent.getVariableName(), _right, variableContent.getDeep(), ip_.getCache(), ip_.getRefParams(), _stackCall);
    }

    public ExecVariableContent getVariableContent() {
        return variableContent;
    }
}
