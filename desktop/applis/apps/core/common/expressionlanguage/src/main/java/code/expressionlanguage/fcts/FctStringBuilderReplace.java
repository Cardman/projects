package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasCharSequenceType;
import code.expressionlanguage.stds.AliasReflection;
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
        Struct start_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct end_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct str_ = argumentWrappers_.get(2).getValue().getStruct();
        replace(inst_, NumParsers.convertToNumber(start_),NumParsers.convertToNumber(end_),str_,_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void replace(StringBuilderStruct _instance, NumberStruct _start, NumberStruct _end, Struct _str, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > _instance.getInstance().length() || start_ > end_) {
            if (start_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(start_), _stackCall)));
            } else if (start_ > _instance.getInstance().length()) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getEndMessage(start_, ">", _instance.getInstance().length()), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getEndMessage(start_, ">", end_), _stackCall)));
            }
            return;
        }
        if (!(_str instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_an, _stackCall)));
            return;
        }
        CharSequenceStruct ch_ = NumParsers.getCharSeq(_str);
        _instance.getInstance().replace(start_, end_, ch_.toStringInstance());
    }

}
