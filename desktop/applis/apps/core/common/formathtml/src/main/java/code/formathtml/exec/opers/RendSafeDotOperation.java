package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.StringList;

public final class RendSafeDotOperation extends RendAbstractDotOperation {

    private final StringList names;

    public RendSafeDotOperation(ExecOperationContent _d,StringList _names) {
        super(_d);
        names = _names;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode o_ = getFirstNode(this);
        RendDynOperationNode l_ = getLastNode(this);
        Struct a_ = getArgument(_nodes,o_);
        if (a_ == NullStruct.NULL_VALUE&&!(l_ instanceof RendAbstractLambdaOperation)) {
            a_ = ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE, _context, names,_rendStack.getLastPage());
            setQuickConvertSimpleArgument(a_, _nodes, _context, _rendStack);
            return;
        }
        calculateDot(_nodes, _context, _rendStack);
    }
}
