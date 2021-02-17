package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;

public final class ExecSafeDotOperation extends ExecAbstractDotOperation {

    public ExecSafeDotOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecOperationNode o_ = getFirstChild();
        ExecOperationNode l_ = ExecHelper.getLastNode(this);
        Argument a_ = getArgument(_nodes,o_);
        if (a_.isNull()&&!(l_ instanceof ExecAbstractLambdaOperation)) {
            a_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, getResultClass().getNames(), _stack));
            setQuickConvertSimpleArgument(a_, _conf, _nodes,_stack);
            return;
        }
        calculateDot(_nodes,_conf, _stack);
    }

}
