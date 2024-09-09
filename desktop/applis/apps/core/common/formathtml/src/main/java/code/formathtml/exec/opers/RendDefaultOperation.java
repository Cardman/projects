package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendDefaultOperation extends RendMethodOperation implements RendCalculableOperation {

    private final int offset;
    private final StringList names;

    public RendDefaultOperation(ExecOperationContent _content, int _offset,StringList _names) {
        super(_content);
        offset = _offset;
        names = _names;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<Struct> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(offset, _rendStack);
        Struct argres_ = ExecClassArgumentMatching.convertFormattedWide(ExecHelper.getFirstArgument(arguments_), _context, names, _rendStack.getLastPage());
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
