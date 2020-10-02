package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecStaticInitOperation extends ExecLeafOperation implements AtomicExecCalculableOperation {

    private boolean possibleInitClass;

    public ExecStaticInitOperation(ExecOperationContent _opCont, boolean _possibleInitClass) {
        super(_opCont);
        possibleInitClass = _possibleInitClass;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument arg_ = Argument.createVoid();
        if (possibleInitClass) {
            String className_ = getResultClass().getSingleNameOrEmpty();
            _conf.getExiting().hasToExit(className_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
