package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.util.IdMap;

public final class ExecSettableFieldInstOperation extends
        ExecSettableFieldOperation {

    private final ExecTypeReturn pair;

    public ExecSettableFieldInstOperation(ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont, _fieldCont, _setFieldCont);
        pair = new ExecTypeReturn(_rootBlock, _setFieldCont.getRealType());
    }

    @Override
    protected Argument getField(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        ClassField fieldId_ = getSettableFieldContent().getClassField();
        return ExecFieldTemplates.getSafeInstanceField(getSettableFieldContent().getAnc(), previous_, _conf, _stack, fieldId_);
    }

    @Override
    protected Argument setField(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stackCall) {
        Argument prev_ = getPreviousArg(this, _nodes, _stackCall);
        ClassField fieldId_ = getSettableFieldContent().getClassField();
        return ExecFieldTemplates.setSafeInstanceField(getSettableFieldContent().getAnc(), prev_, _right, _conf, _stackCall, fieldId_, pair);
    }

    public ExecTypeReturn getPair() {
        return pair;
    }
}
