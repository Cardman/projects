package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.StaticInitOperation;
import code.util.IdMap;

public final class ExecStaticInitOperation extends ExecLeafOperation implements AtomicExecCalculableOperation {

    private boolean possibleInitClass;

    public ExecStaticInitOperation(StaticInitOperation _s) {
        super(_s);
        possibleInitClass = _s.isPossibleInitClass();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument arg_ = Argument.createVoid();
        if (possibleInitClass) {
            String className_ = getResultClass().getName();
            ExecutingUtil.hasToExit(_conf,className_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
