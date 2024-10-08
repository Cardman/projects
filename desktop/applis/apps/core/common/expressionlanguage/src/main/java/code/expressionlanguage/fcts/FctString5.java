package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctString5 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct argArrByte_ = argumentWrappers_.get(2).getValue();
        if (!(argArrByte_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct byteArr_ = (ArrayStruct) argArrByte_;
        int len_ = byteArr_.getLength();
        byte[] arr_ = new byte[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = NumParsers.convertToNumber(byteArr_.get(i)).byteStruct();
        }
        Struct offset_ = argumentWrappers_.get(0).getValue();
        Struct to_ = argumentWrappers_.get(1).getValue();
        int one_ = NumParsers.convertToNumber(offset_).intStruct();
        int two_ = NumParsers.convertToNumber(to_).intStruct();
        if (one_ < 0 || two_ < 0 || one_ > arr_.length - two_) {
            if (one_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getBeginMessage(one_), _stackCall)));
            } else if (two_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getBeginMessage(two_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getBeginEndMessage(arr_.length, one_, two_), _stackCall)));
            }
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String chars_ = StringUtil.decode(arr_, one_, two_);
        if (chars_ == null) {
            int index_ = StringUtil.badDecode(arr_, one_, two_);
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, Long.toString(index_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new StringStruct(chars_));
    }
}
