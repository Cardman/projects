package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;

public abstract class ExecSettableFieldOperation extends
        ExecAbstractFieldOperation implements ExecSettableElResult {

    private final ExecSettableOperationContent settableFieldContent;

    protected ExecSettableFieldOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont, _fieldCont);
        settableFieldContent = _setFieldCont;
    }

    public boolean resultCanBeSet() {
        return settableFieldContent.isVariable();
    }

    protected void offset(StackCall _stack) {
        _stack.setOffset(getOff());
    }

    protected void setter(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setQuickNoConvertSimpleArgument(NullStruct.NULL_VALUE, _conf, _nodes, _stack);
    }

    public boolean isDeclare() {
        return settableFieldContent.isDeclare();
    }

    public ExecSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }
    public abstract ExecRootBlock owner();
}
