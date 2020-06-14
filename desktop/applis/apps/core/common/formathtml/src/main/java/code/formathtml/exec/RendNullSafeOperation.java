package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendNullSafeOperation extends RendMethodOperation implements RendCalculableOperation {
    public RendNullSafeOperation(OperationNode _m) {
        super(_m);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode first_ = chidren_.first();
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (abs_ != NullStruct.NULL_VALUE) {
            f_ = new Argument(ClassArgumentMatching.convert(_conf.getPageEl(),getResultClass(),abs_,_conf.getContext()));
            setQuickConvertSimpleArgument(f_, _conf, _nodes);
            return;
        }
        RendDynOperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        a_ = new Argument(ClassArgumentMatching.convert(_conf.getPageEl(),getResultClass(),a_.getStruct(),_conf.getContext()));
        setSimpleArgument(a_, _conf, _nodes);
    }
}
