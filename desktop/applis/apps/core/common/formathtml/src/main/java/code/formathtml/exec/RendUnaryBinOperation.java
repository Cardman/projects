package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.UnaryBinOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendUnaryBinOperation extends RendAbstractUnaryOperation {

    public RendUnaryBinOperation(UnaryBinOperation _u) {
        super(_u);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = getArgument(_nodes,chidren_.first());
        Argument a_ = getArgument(_conf, arg_);
        setSimpleArgument(a_, _conf,_nodes);
    }

    Argument getArgument(Configuration _conf,
                         Argument _in) {
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        return new Argument(NumParsers.negBinNumber(NumParsers.convertToNumber(_in.getStruct()), getResultClass().getUnwrapObjectNb()));
    }
}
