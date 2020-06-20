package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.UnaryBinOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;
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
        Argument out_ = new Argument();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ClassArgumentMatching res_ = getResultClass();
        out_.setStruct(NumberStruct.negBinNumber(ClassArgumentMatching.convertToNumber(_in.getStruct()), _conf.getContext(), res_));
        return out_;
    }
}
