package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.IdOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendMultIdOperation extends RendAbstractUnaryOperation {

    public RendMultIdOperation(IdOperation _i) {
        super(_i);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode o_ = chidren_.first();
        Argument a_ = getArgument(_nodes,o_);
        setSimpleArgument(a_, _conf,_nodes);
    }
}
