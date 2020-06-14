package code.formathtml.exec;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendDotOperation extends RendAbstractDotOperation {

    public RendDotOperation(DotOperation _d) {
        super(_d);
    }
    public RendDotOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        calculateDot(_nodes,_conf);
    }
}
