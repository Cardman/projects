package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecThisContent;
import code.expressionlanguage.structs.Struct;
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
        AbstractPageEl ip_ = _stack.getLastPage();
        Struct a_;
        if (isIntermediateDottedOperation()) {
            a_ = ExecFieldTemplates.getParent(thisContent.getNbAncestors(), ip_.getGlobalStruct(), _conf, _stack);
        } else {
            a_ = ip_.getGlobalStruct();
        }
        setSimpleArgument(a_, _conf, _nodes, _stack);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return thisContent.isIntermediate();
    }

    public ExecThisContent getThisContent() {
        return thisContent;
    }
}
