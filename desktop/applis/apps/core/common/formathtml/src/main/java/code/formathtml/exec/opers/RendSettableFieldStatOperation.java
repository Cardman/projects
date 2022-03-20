package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendSettableFieldStatOperation extends
        RendSettableFieldOperation {

    private final ExecRootBlock rootBlock;

    public RendSettableFieldStatOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont, ExecRootBlock _rootBlock) {
        super(_opCont, _fieldCont, _setFieldCont);
        rootBlock = _rootBlock;
    }

    @Override
    protected Argument getField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        String fieldType_ = getRealType();
        ClassField fieldId_ = getClassField();
        return ExecTemplates.getStaticField(_context.getExiting(), getRootBlock(), fieldType_, _context, _rendStack.getStackCall(), fieldId_);
    }

    @Override
    protected Argument setField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack) {
        String fieldType_ = getRealType();
        ClassField fieldId_ = getClassField();
        return ExecTemplates.setStaticField(_context.getExiting(), getRootBlock(), fieldType_, _right, _context, _rendStack.getStackCall(), fieldId_);
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
