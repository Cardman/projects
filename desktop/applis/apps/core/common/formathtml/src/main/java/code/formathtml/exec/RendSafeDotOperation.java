package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendSafeDotOperation extends RendAbstractDotOperation {

    public RendSafeDotOperation(ExecOperationContent _d) {
        super(_d);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode o_ = chidren_.first();
        RendDynOperationNode l_ = chidren_.last();
        Argument a_ = getArgument(_nodes,o_);
        if (a_.isNull()&&!(l_ instanceof RendLambdaOperation)) {
            a_ = new Argument(ExecClassArgumentMatching.convert(_conf.getPageEl(), NullStruct.NULL_VALUE,_conf.getContext(), getResultClass().getNames()));
            setQuickConvertSimpleArgument(a_, _conf, _nodes);
            return;
        }
        calculateDot(_nodes,_conf);
    }
}
