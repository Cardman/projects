package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecSwitchOperation extends ExecSettableCallFctOperation {
    private final ExecAbstractSwitchMethod switchMethod;
    public ExecSwitchOperation(ExecOperationContent _opCont, ExecAbstractSwitchMethod _switchMethod, ExecArrContent _arrContent) {
        super(_opCont,false,_arrContent);
        switchMethod = _switchMethod;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecOperationNode o_ = getFirstChild();
        Argument value_ = getArgument(_nodes,o_);
        ExecTemplates.okArgsSetSwCall(switchMethod,_conf,_stack,value_);
    }
}
