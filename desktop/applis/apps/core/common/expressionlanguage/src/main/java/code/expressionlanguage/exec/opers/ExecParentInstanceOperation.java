package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecParentInstanceContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public class ExecParentInstanceOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private ExecParentInstanceContent parentInstanceContent;

    public ExecParentInstanceOperation(ExecOperationContent _opCont, ExecParentInstanceContent _parentInstanceContent) {
        super(_opCont);
        parentInstanceContent = _parentInstanceContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument arg_ = getCommonArgument(_nodes,_conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    Argument getCommonArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ parentInstanceContent.getOff(), _conf);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Struct struct_ = previous_.getStruct();
        return new Argument(ExecClassArgumentMatching.convert(_conf.getLastPage(), struct_.getParent(),_conf, getResultClass().getNames()));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return parentInstanceContent.isIntermediate();
    }

}
