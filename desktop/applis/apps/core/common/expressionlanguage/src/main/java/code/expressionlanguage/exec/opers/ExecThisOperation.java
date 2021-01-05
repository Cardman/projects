package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecThisContent;
import code.util.IdMap;

public final class ExecThisOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private final ExecThisContent thisContent;

    public ExecThisOperation(ExecOperationContent _opCont, ExecThisContent _thisContent) {
        super(_opCont);
        thisContent = _thisContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(thisContent.getOff(), _stack);
        PageEl ip_ = _stack.getLastPage();
        Argument a_;
        if (isIntermediateDottedOperation()) {
            String c_ = getResultClass().getSingleNameOrEmpty();
            a_ = new Argument(ExecTemplates.getParent(thisContent.getNbAncestors(), c_, ip_.getGlobalStruct(), _conf, _stack));
        } else {
            a_ = new Argument(ip_.getGlobalStruct());
        }
        Argument arg_ = a_;
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return thisContent.isIntermediate();
    }

}
