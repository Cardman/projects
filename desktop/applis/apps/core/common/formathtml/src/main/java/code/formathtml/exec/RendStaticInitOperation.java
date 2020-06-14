package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.StaticInitOperation;
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
        if (possibleInitClass && _conf.hasToExit(className_)) {
            NotInitializedClass statusInit_ = (NotInitializedClass) _conf.getContext().getCallingState();
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
        }
        setQuickNoConvertSimpleArgument(Argument.createVoid(), _conf,_nodes);
    }
}
