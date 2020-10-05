package code.formathtml.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendDotOperation extends RendAbstractDotOperation {

    public RendDotOperation(ExecOperationContent _content) {
        super(_content);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        calculateDot(_nodes,_conf, _context);
    }
}
