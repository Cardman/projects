package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendNullSafeOperation extends RendMethodOperation implements RendCalculableOperation {

    private final StringList names;

    public RendNullSafeOperation(ExecOperationContent _content,StringList _names) {
        super(_content);
        names = _names;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode first_ = getFirstNode(this);
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (abs_ != NullStruct.NULL_VALUE) {
            f_ = new Argument(ExecClassArgumentMatching.convert(abs_, _context, names));
            setQuickConvertSimpleArgument(f_, _nodes, _context, _rendStack);
            return;
        }
        RendDynOperationNode last_ = getLastNode(this);
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _rendStack);
        Argument a_ = getArgument(_nodes,last_);
        a_ = new Argument(ExecClassArgumentMatching.convert(a_.getStruct(), _context, names));
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }
}
