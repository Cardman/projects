package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasCharSequenceType;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctStringBuilderInsert2 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct index_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct arr_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct start_ = argumentWrappers_.get(2).getValue().getStruct();
        Struct end_ = argumentWrappers_.get(3).getValue().getStruct();
        insert(inst_, NumParsers.convertToNumber(index_),ExecCatOperation.getDisplayable(new Argument(arr_),_cont),NumParsers.convertToNumber(start_),NumParsers.convertToNumber(end_),_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void insert(StringBuilderStruct _instance, NumberStruct _dstOffset, DisplayableStruct _s, NumberStruct _start,
                               NumberStruct _end, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        String toApp_= _s.getDisplayedString(_an).getInstance();
        int index_ = _dstOffset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getEndMessage(index_, ">", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || end_ < 0 || start_ > end_ || end_ > toApp_.length()) {
            if (start_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(start_), _stackCall)));
            } else if (end_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getBeginMessage(end_), _stackCall)));
            } else if (start_ > end_) {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getEndMessage(start_, ">", end_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(AliasCharSequenceType.getBadIndex(_an, AliasCharSequenceType.getEndMessage(end_, ">", toApp_.length()), _stackCall)));
            }
            return;
        }
        _instance.getInstance().insert(index_, toApp_, start_, end_);
    }
}
