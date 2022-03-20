package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendSettableFieldInstOperation extends
        RendSettableFieldOperation {

    private final ExecTypeReturn pair;

    public RendSettableFieldInstOperation(ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont, ExecRootBlock _rootBlock) {
        super(_opCont, _fieldCont, _setFieldCont);
        pair = new ExecTypeReturn(_rootBlock,_setFieldCont.getRealType());
    }

    @Override
    protected Argument getField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        ClassField fieldId_ = getClassField();
        return ExecTemplates.getSafeInstanceField(getAnc(), previous_, _context, _rendStack.getStackCall(), fieldId_);
    }

    @Override
    protected Argument setField(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack) {
        Argument prev_ = getPreviousArg(this, _nodes, _rendStack);
        ClassField fieldId_ = getClassField();
        return ExecTemplates.setSafeInstanceField(getAnc(), prev_, _right, _context, _rendStack.getStackCall(), fieldId_, pair);
    }

    public ExecTypeReturn getPair() {
        return pair;
    }
}
