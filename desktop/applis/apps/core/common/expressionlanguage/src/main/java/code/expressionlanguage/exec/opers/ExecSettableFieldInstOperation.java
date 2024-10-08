package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecSettableFieldInstOperation extends
        ExecSettableFieldOperation {

    private final ExecTypeReturn pair;

    public ExecSettableFieldInstOperation(ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecFieldOperationContent _fieldCont, ExecSettableOperationContent _setFieldCont) {
        super(_opCont, _fieldCont, _setFieldCont);
        pair = new ExecTypeReturn(_rootBlock, _setFieldCont.getRealType());
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (resultCanBeSet()) {
            setter(_nodes, _conf, _stack);
        } else {
            offset(_stack);
            Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
            ClassField fieldId_ = getSettableFieldContent().getClassField();
            Struct parent_ = ExecFieldTemplates.getParent(getSettableFieldContent().getAnc(), previous_, _conf, _stack);
            ExecHelper.getArgumentPair(_nodes,this).setArgumentParent(parent_);
            Struct arg_ = ExecFieldTemplates.getSafeInstanceField(_conf, _stack, fieldId_, parent_);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
        }
    }

    @Override
    public Struct calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Struct _right, StackCall _stack) {
        if (_conf.callsOrException(_stack)) {
            return _right;
        }
        //Come from code directly so constant static fields can be initialized here
        offset(_stack);
        ClassField fieldId_ = getSettableFieldContent().getClassField();
        Struct parent_;
        if (resultCanBeSet()) {
            Struct prev_ = getPreviousArg(this, _nodes, _stack.getLastPage());
            parent_ = ExecFieldTemplates.getParent(getSettableFieldContent().getAnc(), prev_, _conf, _stack);
        } else {
            parent_ = ArgumentListCall.getNull(ExecHelper.getArgumentPair(_nodes,this).getArgumentParent());
        }
        return ExecFieldTemplates.setSafeInstanceField(_right, _conf, _stack, fieldId_, pair, parent_);
    }

    @Override
    public ExecRootBlock owner() {
        return getPair().getRootBlock();
    }

    public ExecTypeReturn getPair() {
        return pair;
    }
}
