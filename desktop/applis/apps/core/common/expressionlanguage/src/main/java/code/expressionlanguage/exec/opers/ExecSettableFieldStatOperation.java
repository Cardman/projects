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
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (resultCanBeSet()) {
            setter(_nodes, _conf, _stack);
        } else {
            offset(_stack);
            ClassField fieldId_ = getSettableFieldContent().getClassField();
            String fieldType_ = getSettableFieldContent().getRealType();
            Argument arg_ = ExecFieldTemplates.getStaticField(_conf.getExiting(), rootBlock, fieldType_, _conf, _stack, fieldId_);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
        }
    }

    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right, StackCall _stack) {
        if (_conf.callsOrException(_stack)) {
            return _right;
        }
        //Come from code directly so constant static fields can be initialized here
        offset(_stack);
        String fieldType_ = getSettableFieldContent().getRealType();
        ClassField fieldId_ = getSettableFieldContent().getClassField();
        return ExecFieldTemplates.setStaticField(_conf.getExiting(), rootBlock, fieldType_, _right, _conf, _stack, fieldId_);
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
