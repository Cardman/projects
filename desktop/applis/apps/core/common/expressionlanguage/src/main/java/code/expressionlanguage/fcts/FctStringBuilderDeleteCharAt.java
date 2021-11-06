package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringBuilderDeleteCharAt implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        Struct index_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        deleteCharAt(inst_, NumParsers.convertToNumber(index_),_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void deleteCharAt(StringBuilderStruct _instance, NumberStruct _index, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ >= _instance.getInstance().length()) {
            FctStringBuilderSetCharAt.exc(_instance,_an,_stackCall,index_);
            return;
        }
        _instance.getInstance().deleteCharAt(index_);
    }

}
