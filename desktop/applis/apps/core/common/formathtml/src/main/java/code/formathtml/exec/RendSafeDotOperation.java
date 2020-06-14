package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.SafeDotOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendSafeDotOperation extends RendAbstractDotOperation {

    public RendSafeDotOperation(SafeDotOperation _d) {
        super(_d);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode o_ = chidren_.first();
        Argument a_ = getArgument(_nodes,o_);
        if (a_.isNull()) {
            a_ = new Argument(ClassArgumentMatching.convert(_conf.getPageEl(),getResultClass(),NullStruct.NULL_VALUE,_conf.getContext()));
            setQuickConvertSimpleArgument(a_, _conf, _nodes);
            return;
        }
        calculateDot(_nodes,_conf);
    }
}
