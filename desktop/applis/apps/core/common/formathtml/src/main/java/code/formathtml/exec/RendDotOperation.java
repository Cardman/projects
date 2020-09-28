package code.formathtml.exec;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendDotOperation extends RendAbstractDotOperation {

    public RendDotOperation(ExecOperationContent _content) {
        super(_content);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        calculateDot(_nodes,_conf);
    }
}
