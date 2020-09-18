package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ParentInstanceOperation;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.IdMap;

public class RendParentInstanceOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {
    private boolean intermediate;
    private int off;
    RendParentInstanceOperation(ParentInstanceOperation _l) {
        super(_l);
        intermediate = _l.isIntermediate();
        off = _l.getOff();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument arg_ = getCommonArgument(_nodes,_conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    Argument getCommonArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Struct struct_ = previous_.getStruct();
        return new Argument(ExecClassArgumentMatching.convert(_conf.getPageEl(), struct_.getParent(),_conf.getContext(), getResultClass().getNames()));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
}
