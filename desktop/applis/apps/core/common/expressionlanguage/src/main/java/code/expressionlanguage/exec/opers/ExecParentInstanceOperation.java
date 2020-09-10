package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ParentInstanceOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public class ExecParentInstanceOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private boolean intermediate;
    private int off;

    ExecParentInstanceOperation(ParentInstanceOperation _l) {
        super(_l);
        intermediate = _l.isIntermediate();
        off = _l.getOff();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument arg_ = getCommonArgument(_nodes,_conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    Argument getCommonArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Struct struct_ = previous_.getStruct();
        return new Argument(ClassArgumentMatching.convert(_conf.getLastPage(),getResultClass(),struct_.getParent(),_conf));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
