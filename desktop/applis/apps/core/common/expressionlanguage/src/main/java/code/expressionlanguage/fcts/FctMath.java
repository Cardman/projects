package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public abstract class FctMath implements StdCaller {
    public static ErrorStruct getDivideZero(ContextEl _cont, StackCall _stackCall) {
        return new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasDivisionZero(), _stackCall);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isInitEnums()) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return alea(_exit,_cont,_firstArgs,_stackCall);
    }

    protected static boolean matchNotNull(ContextEl _cont, Struct _seed, String _type) {
        return _seed != NullStruct.NULL_VALUE
                && ExecInherits.safeObject(_type, Argument.getNull(_seed).getClassName(_cont), _cont) == ErrorType.NOTHING;
    }
    protected abstract ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall);
}
