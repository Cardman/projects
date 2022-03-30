package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public abstract class RendSettableCallFctOperation extends RendInvokingOperation implements RendSettableElResult {
    private final ExecArrContent arrContent;
    protected RendSettableCallFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation);
        arrContent = _arrContent;
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack) {
        return trySetArgument(_nodes, _context, _right, _rendStack);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    private Argument trySetArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res, RendStackCall _stackCall) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        return processCall(ExecVariableTemplates.trySetArgument(_conf, _res, pair_, _stackCall.getStackCall()),_conf,_stackCall).getValue();
    }
}
