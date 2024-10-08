package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        offset(_rendStack);
        Struct result_;
        if (resultCanBeSet()) {
            result_ = NullStruct.NULL_VALUE;
        } else {
            String fieldType_ = getRealType();
            ClassField fieldId_ = getClassField();
            result_ = ExecFieldTemplates.getStaticField(fieldType_, _context, _rendStack.getStackCall(), fieldId_);
        }
        postCalulate(_nodes, _context, _rendStack, result_);
    }

    @Override
    public Struct calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Struct _right, ContextEl _context, RendStackCall _rendStack) {
        if (_context.callsOrException(_rendStack.getStackCall())) {
            return _right;
        }
        offsetLoc(_rendStack);
        //Come from code directly so constant static fields can be initialized here
        String fieldType_ = getRealType();
        ClassField fieldId_ = getClassField();
        Struct arg_ = ExecFieldTemplates.setStaticField(fieldType_, _right, _context, _rendStack.getStackCall(), fieldId_);
        return RendDynOperationNode.processCall(arg_, _context, _rendStack).getValue();
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
