package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ForwardOperation;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendForwardOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {
    private boolean intermediate;
    private Argument previousArgument;

    RendForwardOperation(ForwardOperation _v) {
        super(_v);
        intermediate = _v.isIntermediate();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        setSimpleArgument(previous_, _conf,_nodes);
    }

    @Override
    public Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
}
