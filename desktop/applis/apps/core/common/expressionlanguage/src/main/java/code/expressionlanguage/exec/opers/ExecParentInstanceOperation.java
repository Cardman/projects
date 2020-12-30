package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecParentInstanceContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecParentInstanceOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private final ExecParentInstanceContent parentInstanceContent;

    public ExecParentInstanceOperation(ExecOperationContent _opCont, ExecParentInstanceContent _parentInstanceContent) {
        super(_opCont);
        parentInstanceContent = _parentInstanceContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument arg_ = getCommonArgument(_nodes,_conf, _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    Argument getCommonArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stackCall) {
        setRelOffsetPossibleLastPage(parentInstanceContent.getOff(), _stackCall);
        Argument previous_ = getPreviousArg(this, _nodes, _stackCall);
        Struct struct_ = previous_.getStruct();
        return new Argument(ExecClassArgumentMatching.convertFormatted(struct_.getParent(),_conf, getResultClass().getNames(), _stackCall));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return parentInstanceContent.isIntermediate();
    }

}
