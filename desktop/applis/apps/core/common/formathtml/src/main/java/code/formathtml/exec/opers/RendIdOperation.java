package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendIdOperation extends RendAbstractUnaryOperation {

    public RendIdOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode o_ = chidren_.first();
        Argument a_ = getArgument(_nodes,o_);
        boolean simple_ = false;
        if (o_ instanceof RendSettableElResult) {
            RendSettableElResult s_ = (RendSettableElResult) o_;
            if (s_.resultCanBeSet()) {
                simple_ = true;
            }
        }
        if (simple_) {
            setQuickNoConvertSimpleArgument(a_, _nodes, _context);
        } else {
            setSimpleArgument(a_, _conf,_nodes, _context);
        }
    }
}
