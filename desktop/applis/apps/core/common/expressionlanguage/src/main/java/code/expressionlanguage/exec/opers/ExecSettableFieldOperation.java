package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.util.IdMap;

public final class ExecSettableFieldOperation extends
        ExecAbstractFieldOperation implements ExecSettableElResult {

    private final ExecSettableOperationContent settableFieldContent;

    private final ExecRootBlock rootBlock;

    public ExecSettableFieldOperation(ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont, _fieldCont);
        settableFieldContent = _setFieldCont;
        rootBlock = _rootBlock;
    }

    public boolean resultCanBeSet() {
        return settableFieldContent.isVariable();
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = getOff();
        ClassField fieldId_ = settableFieldContent.getClassField();
        boolean staticField_ = settableFieldContent.isStaticField();
        if (resultCanBeSet()) {
            Argument arg_ = Argument.createVoid();
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes, _stack);
        } else {
            String fieldType_ = settableFieldContent.getRealType();
            _stack.setOffset(off_);
            Argument arg_;
            if (staticField_) {
                arg_ = ExecTemplates.getStaticField(_conf.getExiting(),rootBlock, fieldType_,_conf, _stack, fieldId_);
            } else {
                arg_ = ExecTemplates.getSafeInstanceField(settableFieldContent.getAnc(), previous_,_conf, _stack, fieldId_);
            }
            setSimpleArgument(arg_, _conf, _nodes, _stack);
        }
    }


    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right, StackCall _stack) {
        return getCommonSetting(_conf,_nodes,_right,_stack);
    }

    private Argument getCommonSetting(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stackCall) {
        if (_conf.callsOrException(_stackCall)) {
            return _right;
        }
        Argument prev_ = getPreviousArg(this, _nodes, _stackCall);
        int off_ = getOff();
        String fieldType_ = settableFieldContent.getRealType();
        boolean isStatic_ = settableFieldContent.isStaticField();
        ClassField fieldId_ = settableFieldContent.getClassField();
        //Come from code directly so constant static fields can be initialized here
        _stackCall.setOffset(off_);
        if (isStatic_) {
            return ExecTemplates.setStaticField(_conf.getExiting(), rootBlock, fieldType_, _right, _conf, _stackCall, fieldId_);
        }
        return ExecTemplates.setSafeInstanceField(settableFieldContent.getAnc(), prev_, _right, _conf, _stackCall, fieldId_, new ExecTypeReturn(rootBlock, fieldType_));
    }

    public ExecSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }
}
