package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public abstract class RendSettableFieldOperation extends
        RendAbstractFieldOperation implements RendSettableElResult {

    private final ExecSettableOperationContent settableFieldContent;

    protected RendSettableFieldOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont,_fieldCont);
        settableFieldContent = _setFieldCont;
    }
    @Override
    public boolean resultCanBeSet() {
        return settableFieldContent.isVariable();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        int off_ = getOff();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        _rendStack.setOffset(off_);
        Argument result_;
        if (resultCanBeSet()) {
            result_ = Argument.createVoid();
        } else {
            result_ = getField(_nodes, _context, _rendStack);
        }
        Argument arg_ = RendDynOperationNode.processCall(result_, _context, _rendStack).getValue();
        if (_context.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _nodes, _context, _rendStack);
        } else {
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
        }
    }

    protected abstract Argument getField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack);

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        return processField(_nodes, _right, _context, _rendStack);
    }

    private Argument processField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStackCall) {
        if (_context.callsOrException(_rendStackCall.getStackCall())) {
            return _right;
        }
        int off_ = getOff();
        _rendStackCall.setOffset(off_);
        //Come from code directly so constant static fields can be initialized here
        Argument arg_ = setField(_nodes, _right, _context, _rendStackCall);
        return RendDynOperationNode.processCall(arg_,_context,_rendStackCall).getValue();
    }

    protected abstract Argument setField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack);

    public ExecSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

    public String getRealType() {
        return settableFieldContent.getRealType();
    }

    public ClassField getClassField() {
        return settableFieldContent.getClassField();
    }

    public int getAnc() {
        return settableFieldContent.getAnc();
    }

}
