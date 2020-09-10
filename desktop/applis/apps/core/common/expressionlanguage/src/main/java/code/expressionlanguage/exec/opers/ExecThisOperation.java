package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ThisOperation;
import code.util.IdMap;

public final class ExecThisOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private boolean intermediate;
    private int nbAncestors;
    private int off;

    public ExecThisOperation(ThisOperation _t) {
        super(_t);
        intermediate = _t.isIntermediate();
        nbAncestors = _t.getNbAncestors();
        off = _t.getOff();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    Argument getCommonArgument(ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getLastPage();
        Argument a_;
        if (isIntermediateDottedOperation()) {
            String c_ = getResultClass().getName();
            a_ = new Argument(ExecTemplates.getParent(nbAncestors, c_, ip_.getGlobalStruct(), _conf));
        } else {
            a_ = new Argument(ip_.getGlobalStruct());
        }
        return a_;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
