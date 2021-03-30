package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundSwitch;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecSwitchOperation extends ExecSettableCallFctOperation {
    private final ExecRootBlock type;
    private final ExecAbstractSwitchMethod switchMethod;
    public ExecSwitchOperation(ExecOperationContent _opCont, ExecRootBlock _type, ExecAbstractSwitchMethod _switchMethod, ExecArrContent _arrContent) {
        super(_opCont,false,_arrContent);
        type = _type;
        switchMethod = _switchMethod;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecOperationNode o_ = getFirstChild();
        Argument value_ = getArgument(_nodes,o_);
        AbstractPageEl last_ = _stack.getLastPage();
        Argument instance_ = last_.getGlobalArgument();
        String glClass_ = last_.getGlobalClass();
        _stack.setCallingState(new CustomFoundSwitch(instance_,glClass_,type,switchMethod, new Cache(last_),value_));
    }
}
