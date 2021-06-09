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
import code.util.StringList;

public final class ExecParentInstanceOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private final ExecParentInstanceContent parentInstanceContent;
    private final StringList names;

    public ExecParentInstanceOperation(ExecOperationContent _opCont, ExecParentInstanceContent _parentInstanceContent,StringList _names) {
        super(_opCont);
        parentInstanceContent = _parentInstanceContent;
        names = _names;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(parentInstanceContent.getOff(), _stack);
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Struct struct_ = previous_.getStruct();
        Argument arg_ = new Argument(ExecClassArgumentMatching.convertFormatted(struct_.getParent(), _conf, names, _stack));
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return parentInstanceContent.isIntermediate();
    }

}
