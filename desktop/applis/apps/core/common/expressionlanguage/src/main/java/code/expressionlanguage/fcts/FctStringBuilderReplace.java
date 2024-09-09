package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctStringBuilderReplace implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct start_ = argumentWrappers_.get(0).getValue();
        Struct end_ = argumentWrappers_.get(1).getValue();
        Struct str_ = argumentWrappers_.get(2).getValue();
        replace(inst_, NumParsers.convertToNumber(start_),NumParsers.convertToNumber(end_),str_,_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void replace(StringBuilderStruct _instance, NumberStruct _start, NumberStruct _end, Struct _str, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.failInitEnums();
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > _instance.getInstance().length() || start_ > end_) {
            FctStringBuilderDelete.exc(_instance,_an,_stackCall,start_,end_);
            return;
        }
        if (!(_str instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_an, _stackCall)));
            return;
        }
        CharSequenceStruct ch_ = NumParsers.getCharSeq(_str);
        _instance.getInstance().replace(start_, end_, ch_.toStringInstance());
    }

}
