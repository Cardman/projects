package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.util.IdMap;

public final class ExecSettableFieldStatOperation extends
        ExecSettableFieldOperation {

    private final ExecRootBlock rootBlock;

    public ExecSettableFieldStatOperation(ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont, _fieldCont, _setFieldCont);
        rootBlock = _rootBlock;
    }

    @Override
    protected Argument getField(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ClassField fieldId_ = getSettableFieldContent().getClassField();
        String fieldType_ = getSettableFieldContent().getRealType();
        return ExecFieldTemplates.getStaticField(_conf.getExiting(), rootBlock, fieldType_, _conf, _stack, fieldId_);
    }

    @Override
    protected Argument setField(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stackCall) {
        String fieldType_ = getSettableFieldContent().getRealType();
        ClassField fieldId_ = getSettableFieldContent().getClassField();
        return ExecFieldTemplates.setStaticField(_conf.getExiting(), rootBlock, fieldType_, _right, _conf, _stackCall, fieldId_);
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
