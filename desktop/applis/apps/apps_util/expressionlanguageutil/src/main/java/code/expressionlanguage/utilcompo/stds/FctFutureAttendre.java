package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsDbgFutureStruct;
import code.expressionlanguage.utilcompo.AbsFutureStruct;

public final class FctFutureAttendre implements StdCaller {

    private final String intro;

    public FctFutureAttendre(String _id) {
        intro = _id;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_instance instanceof AbsDbgFutureStruct) {
            return new ArgumentWrapper(((AbsDbgFutureStruct)_instance).attendre(_stackCall,_cont, intro));
        }
        return new ArgumentWrapper(((AbsFutureStruct)_instance).attendre());
    }
}
