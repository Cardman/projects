package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;


public final class RendQuickNatOperation extends RendQuickOperation implements RendCalculableOperation {

    public RendQuickNatOperation(ExecOperationContent _content, boolean _absorbingValue) {
        super(_content, _absorbingValue);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode first_ = getFirstNode(this);
        Argument f_ = getArgument(_nodes, first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _nodes, _context, _stack);
            return;
        }
        Argument a_ = getArgument(_nodes,getLastNode(this));
        setSimpleArgument(a_, _nodes, _context, _stack, _rendStack);
    }

}
