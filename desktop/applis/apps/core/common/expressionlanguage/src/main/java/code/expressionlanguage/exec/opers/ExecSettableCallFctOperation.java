package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public abstract class ExecSettableCallFctOperation extends ExecInvokingOperation implements ExecSettableElResult {
    private final ExecArrContent arrContent;
    protected ExecSettableCallFctOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrContent _execArr) {
        super(_opCont, _intermediateDottedOperation);
        arrContent = _execArr;
    }

    protected ExecSettableCallFctOperation(int _indexChild, ExecClassArgumentMatching _res, int _order, boolean _intermediate, ExecArrContent _execArr) {
        super(_indexChild, _res, _order, _intermediate);
        arrContent = _execArr;
    }

    protected void setResult(Argument _res, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stack) {
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(_res, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_res, _conf, _nodes, _stack);
    }
    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        return trySetArgument(_nodes, _conf, _right, _stack);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    private Argument trySetArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res, StackCall _stackCall) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        return ExecVariableTemplates.trySetArgument(_conf, _res, pair_, _stackCall);
    }
}
