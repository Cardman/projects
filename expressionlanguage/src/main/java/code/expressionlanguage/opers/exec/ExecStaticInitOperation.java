package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
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
        Argument current_ = getArgument(_nodes,this);
        Argument arg_ = getCommonArgument(current_, _conf);
        if (arg_ == null) {
            _nodes.getVal(this).setArgument(Argument.createVoid());
            return arg_;
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    Argument getCommonArgument(Argument _argument, ExecutableCode _conf) {
        if (possibleInitClass) {
            String className_ = getResultClass().getNames().first();
            if (ExecInvokingOperation.hasToExit(_conf, className_)) {
                return Argument.createVoid();
            }
        }
        return null;
    }

}
