package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendStdRefVariableOperation extends RendLeafOperation implements
        RendCalculableOperation,RendSettableElResult{
    private final ExecVariableContent variableContent;
    private final boolean declare;
    public RendStdRefVariableOperation(ExecOperationContent _l, ExecVariableContent _variableContent, boolean _declare) {
        super(_l);
        variableContent = _variableContent;
        declare = _declare;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _rendStack);
        if (resultCanBeSet()) {
            if (!declare) {
                ImportingPage ip_ = _rendStack.getLastPage();
                AbstractWrapper val_ = ip_.getRefParams().getVal(getVariableName());
                ArgumentsPair pair_ = getArgumentPair(_nodes, this);
                pair_.setWrapper(val_);
                setQuickNoConvertSimpleArgument(ExecTemplates.getArgValue(val_,_context, _stack), _nodes, _context, _stack);
            } else {
                setQuickNoConvertSimpleArgument(new Argument(), _nodes, _context, _stack);
            }
        } else {
            ImportingPage ip_ = _rendStack.getLastPage();
            AbstractWrapper val_ = ip_.getRefParams().getVal(getVariableName());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setSimpleArgument(ExecTemplates.getArgValue(val_,_context, _stack), _nodes, _context, _stack, _rendStack);
        }
    }
    public String getVariableName() {
        return variableContent.getVariableName();
    }

    public ExecVariableContent getVariableContent() {
        return variableContent;
    }


    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        return trySetArgument(_context, _right, _stack, _rendStack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Struct store_ = ExecTemplates.getWrapValue(_context,getVariableName(),variableContent.getDeep(), _rendStack.getLastPage().getPageEl().getCache(), _rendStack.getLastPage().getPageEl().getRefParams(), _stack).getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, variableContent.isCatString(), _cl.getNames(), _cast, _context, _stack);
        return trySetArgument(_context, res_, _stack, _rendStack);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Struct store_ = ExecTemplates.getWrapValue(_context,getVariableName(),variableContent.getDeep(), _rendStack.getLastPage().getPageEl().getCache(), _rendStack.getLastPage().getPageEl().getRefParams(), _stack).getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        trySetArgument(_context, res_, _stack, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        return trySetArgument(_context, _right, _stack, _rendStack);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        trySetArgument(_context, _right, _stack, _rendStack);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument trySetArgument(ContextEl _conf, Argument _res, StackCall _stackCall, RendStackCall _rendStackCall) {
        return ExecTemplates.setWrapValue(_conf,variableContent.getVariableName(), _res,variableContent.getDeep(), _rendStackCall.getLastPage().getPageEl().getCache(), _rendStackCall.getLastPage().getPageEl().getRefParams(), _stackCall);
    }

    public boolean isDeclare() {
        return declare;
    }
}
