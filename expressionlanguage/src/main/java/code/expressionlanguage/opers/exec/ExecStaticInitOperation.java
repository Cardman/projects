package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.StaticInitOperation;
import code.util.IdMap;

public final class ExecStaticInitOperation extends ExecVariableLeafOperation {

    private boolean possibleInitClass;

    public ExecStaticInitOperation(StaticInitOperation _s) {
        super(_s);
        possibleInitClass = _s.isPossibleInitClass();
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = Argument.createVoid();
        if (possibleInitClass) {
            String className_ = getResultClass().getNames().first();
            ExecInvokingOperation.hasToExit(_conf, className_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

}
