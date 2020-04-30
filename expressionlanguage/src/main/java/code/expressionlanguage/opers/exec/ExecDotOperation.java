package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.DotOperation;
import code.util.IdMap;

public final class ExecDotOperation extends ExecAbstractDotOperation {

    public ExecDotOperation(DotOperation _d) {
        super(_d);
    }


    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        calculateDot(_nodes,_conf);
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        DotOperation.setArg(this,_conf);
    }

}
