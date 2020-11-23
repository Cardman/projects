package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendNullSafeOperation extends RendMethodOperation implements RendCalculableOperation {
    public RendNullSafeOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        RendDynOperationNode first_ = getFirstNode(this);
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (abs_ != NullStruct.NULL_VALUE) {
            f_ = new Argument(ExecClassArgumentMatching.convert(abs_, _context, getResultClass().getNames()));
            setQuickConvertSimpleArgument(f_, _nodes, _context);
            return;
        }
        RendDynOperationNode last_ = getLastNode(this);
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        a_ = new Argument(ExecClassArgumentMatching.convert(a_.getStruct(), _context, getResultClass().getNames()));
        setSimpleArgument(a_, _conf, _nodes, _context);
    }
}
