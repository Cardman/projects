package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractReplacingType;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctClassIsInstance extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String param_ = ((ClassMetaInfo)_instance).getFormatted().getFormatted();
        if (param_.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            return new ArgumentWrapper(BooleanStruct.of(false));
        }
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (arg_ == NullStruct.NULL_VALUE) {
            return new ArgumentWrapper(BooleanStruct.of(false));
        }
        return new ArgumentWrapper(BooleanStruct.of(ExecInherits.safeObject(param_, Argument.getNull(arg_).getClassName(_cont), _cont) == ErrorType.NOTHING));
    }
}
