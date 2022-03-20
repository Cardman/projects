package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
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
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        int off_ = getOff();
        if (resultCanBeSet()) {
            Argument arg_ = Argument.createVoid();
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes, _stack);
        } else {
            _stack.setOffset(off_);
            Argument arg_ = getField(_nodes, _conf, _stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
        }
    }

    protected abstract Argument getField(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                         ContextEl _conf, StackCall _stack);

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
        int off_ = getOff();
        //Come from code directly so constant static fields can be initialized here
        _stackCall.setOffset(off_);
        return setField(_conf, _nodes, _right, _stackCall);
    }

    protected abstract Argument setField(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stackCall);
    public ExecSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }
}
