package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.AdvancedExiting;
import code.util.IdMap;

public final class RendStaticInitOperation extends RendLeafOperation implements RendCalculableOperation {

    private boolean possibleInitClass;

    public RendStaticInitOperation(ExecOperationContent _content, boolean _possibleInitClass) {
        super(_content);
        possibleInitClass = _possibleInitClass;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        String className_ = getResultClass().getSingleNameOrEmpty();
        if (possibleInitClass && AdvancedExiting.hasToExit(className_, _conf.getContext())) {
            NotInitializedClass statusInit_ = (NotInitializedClass) _conf.getContext().getCallingState();
            ProcessMethod.initializeClass(statusInit_.getClassName(),statusInit_.getRootBlock(), _conf.getContext());
        }
        setQuickNoConvertSimpleArgument(Argument.createVoid(), _conf,_nodes);
    }
}
