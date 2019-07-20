package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.StaticInitOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendStaticInitOperation extends RendLeafOperation implements RendCalculableOperation {

    private boolean possibleInitClass;

    public RendStaticInitOperation(StaticInitOperation _s) {
        super(_s);
        possibleInitClass = _s.isPossibleInitClass();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        String className_ = getResultClass().getName();
        if (possibleInitClass && ExecInvokingOperation.hasToExit(_conf, className_)) {
            NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
        }
        setQuickSimpleArgument(Argument.createVoid(), _conf,_nodes);
    }
}
