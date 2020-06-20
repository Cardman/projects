package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.DotOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.util.IdMap;

public final class ExecDotOperation extends ExecAbstractDotOperation {

    public ExecDotOperation(DotOperation _d) {
        super(_d);
    }
    public ExecDotOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        calculateDot(_nodes,_conf);
    }

}
