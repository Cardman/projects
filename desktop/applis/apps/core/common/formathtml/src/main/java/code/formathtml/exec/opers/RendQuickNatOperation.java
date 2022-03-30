package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;


public final class RendQuickNatOperation extends RendQuickOperation implements RendCalculableOperation {

    private final int opOffset;

    public RendQuickNatOperation(ExecOperationContent _content, int _opOffset, boolean _absorbingValue) {
        super(_content, _absorbingValue);
        opOffset = _opOffset;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(opOffset, _rendStack);
        RendDynOperationNode first_ = getFirstNode(this);
        Argument f_ = getArgument(_nodes, first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _nodes, _context, _rendStack);
            return;
        }
        Argument a_ = getArgument(_nodes,getLastNode(this));
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }

}
