package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecUnaryBinOperation extends ExecAbstractUnaryOperation {

    public ExecUnaryBinOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecOperationNode op_ = getFirstChild();
        Argument arg_ = getArgument(_nodes,op_);
        Argument a_ = getArgument(arg_, _stack);
        setSimpleArgument(a_, _conf, _nodes, _stack);
    }

    Argument getArgument(Argument _in, StackCall _stackCall) {
        setRelativeOffsetPossibleLastPage(_stackCall);
        return new Argument(NumParsers.negBinNumber(NumParsers.convertToNumber(_in.getStruct()), getResultClass().getUnwrapObjectNb()));
    }
}
