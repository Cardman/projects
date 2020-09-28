package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendNullSafeOperation extends RendMethodOperation implements RendCalculableOperation {
    public RendNullSafeOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode first_ = chidren_.first();
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (abs_ != NullStruct.NULL_VALUE) {
            f_ = new Argument(ExecClassArgumentMatching.convert(_conf.getPageEl(), abs_,_conf.getContext(), getResultClass().getNames()));
            setQuickConvertSimpleArgument(f_, _conf, _nodes);
            return;
        }
        RendDynOperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        a_ = new Argument(ExecClassArgumentMatching.convert(_conf.getPageEl(), a_.getStruct(),_conf.getContext(), getResultClass().getNames()));
        setSimpleArgument(a_, _conf, _nodes);
    }
}
