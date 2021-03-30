package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.IdMap;

public final class ExecRefTernaryOperation extends ExecSettableCallFctOperation implements AtomicExecCalculableOperation {

    private final int offsetLocal;

    public ExecRefTernaryOperation(ExecOperationContent _opCont, int _offsetLocal,ExecArrContent _arrContent) {
        super(_opCont,false,_arrContent);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(offsetLocal, _stack);
        ArgumentsPair ch_ = getChosenArgumentsPair(_nodes);
        AbstractWrapper res_ = ch_.getWrapper();
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        if (res_ != null) {
            ExecHelper.fwdWrapper(pair_,ch_);
            Argument arg_ = ExecTemplates.getArgValue(res_, _conf, _stack);
            setResult(arg_, _conf, _nodes, _stack);
        } else {
            setSimpleArgument(ch_.getArgument(), _conf, _nodes, _stack);
        }
    }

    private ArgumentsPair getChosenArgumentsPair(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        ArgumentsPair arg_;
        if (BooleanStruct.isTrue(ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(getChildrenNodes(),0)).getArgument().getStruct())) {
            arg_ = ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(getChildrenNodes(),1));
        } else {
            arg_ = ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(getChildrenNodes(),2));
        }
        return arg_;
    }

}
