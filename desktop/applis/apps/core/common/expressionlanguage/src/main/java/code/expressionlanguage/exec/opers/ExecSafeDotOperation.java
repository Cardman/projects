package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;

public final class ExecSafeDotOperation extends ExecAbstractDotOperation {

    private final StringList names;

    public ExecSafeDotOperation(ExecOperationContent _opCont,StringList _names) {
        super(_opCont);
        names = _names;
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecOperationNode o_ = getFirstChild();
        ExecOperationNode l_ = ExecHelper.getLastNode(this);
        Struct a_ = getArgument(_nodes,o_);
        if (a_ == NullStruct.NULL_VALUE&&!(l_ instanceof ExecAbstractLambdaOperation)) {
            a_ = ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, names, _stack.getLastPage());
            setQuickConvertSimpleArgument(a_, _conf, _nodes,_stack);
            return;
        }
        calculateDot(_nodes,_conf, _stack);
    }

}
