package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.formathtml.exec.RendStackCall;
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

    protected void offset(RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(getOff(), _rendStack);
        offsetLoc(_rendStack);
    }

    protected void postCalulate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack, Argument _result) {
        ArgumentWrapper arg_ = RendDynOperationNode.tryGetValue(_context, _rendStack,new ArgumentWrapper(ArgumentListCall.toStr(_result)));
        if (arg_ == null) {
            return;
        }
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_.getValue(), _nodes, _context, _rendStack);
        } else {
            setSimpleArgument(arg_.getValue(), _nodes, _context, _rendStack);
        }
    }

    protected void offsetLoc(RendStackCall _rendStackCall) {
        _rendStackCall.setOffset(getOff());
    }

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
