package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class FctClassIsAssignableFrom extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String param_ = ((ClassMetaInfo)_instance).getFormatted().getFormatted();
        Struct subType_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(subType_ instanceof ClassMetaInfo)) {
            return new ArgumentWrapper(BooleanStruct.of(!ExecClassArgumentMatching.isPrimitive(param_,_cont)));
        }
        String arg_ = NumParsers.getClass(subType_).getFormatted().getFormatted();
        return new ArgumentWrapper(BooleanStruct.of(ExecInherits.isCorrectExecute(arg_,param_, _cont)));
    }
}
