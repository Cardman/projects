package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendForwardOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {
    private boolean intermediate;

    public RendForwardOperation(ExecOperationContent _content, boolean _intermediate) {
        super(_content);
        intermediate = _intermediate;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        setSimpleArgument(previous_, _conf,_nodes);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
}
