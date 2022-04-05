package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.fcts.FctMath;
import code.expressionlanguage.fcts.FctUtil;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class RangeChecker {
    private RangeChecker() {
    }

    public static Argument range(ContextEl _conf, StackCall _stack, Struct... _args) {
        if (_args.length == 3) {
            return rangeBoundsStep(_conf, _stack, _args[0], _args[1], _args[2]).getValue();
        }
        if (_args.length == 2) {
            return rangeBounds(_conf, _stack, _args[0], _args[1]).getValue();
        }
        return rangeUnlimit(_conf,_stack,_args[0]).getValue();
    }

    public static Argument rangeUnlimitStep(ContextEl _conf, StackCall _stack, Struct... _args) {
        int lower_ = NumParsers.convertToNumber(_args[0]).intStruct();
        if (lower_ < 0) {
            _stack.setCallingState(new CustomFoundExc(getBadIndex(_conf, getBeginMessage(lower_), _stack)));
            return Argument.createVoid();
        }
        int step_ = NumParsers.convertToNumber(_args[1]).intStruct();
        if (step_ == 0) {
            _stack.setCallingState(new CustomFoundExc(getDivideZero(_conf, _stack)));
            return Argument.createVoid();
        }
        return new Argument(new RangeStruct(lower_, -1,step_));
    }

    public static ArgumentWrapper rangeUnlimit(ContextEl _cont, StackCall _stackCall, Struct _min) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getBeginMessage(lower_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new RangeStruct(lower_));
    }

    public static ArgumentWrapper rangeBounds(ContextEl _cont, StackCall _stackCall, Struct _min, Struct _max) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getBeginMessage(lower_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int upper_ = NumParsers.convertToNumber(_max).intStruct();
        if (upper_ < lower_) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getEndMessage(lower_, ">", upper_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new RangeStruct(lower_, upper_));
    }

    public static ArgumentWrapper rangeBoundsStep(ContextEl _cont, StackCall _stackCall, Struct _min, Struct _max, Struct _step) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getBeginMessage(lower_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int upper_ = NumParsers.convertToNumber(_max).intStruct();
        if (upper_ < lower_) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_cont, FctUtil.getEndMessage(lower_, ">", upper_), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int step_ = NumParsers.convertToNumber(_step).intStruct();
        if (step_ == 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctMath.getDivideZero(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new RangeStruct(lower_, upper_,step_));
    }

    private static ErrorStruct getDivideZero(ContextEl _cont, StackCall _stackCall) {
        return new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasDivisionZero(), _stackCall);
    }

    private static ErrorStruct getBadIndex(ContextEl _context, String _message, StackCall _stackCall) {
        return new ErrorStruct(_context, _message, _context.getStandards().getContent().getCoreNames().getAliasBadIndex(), _stackCall);
    }

    private static String getBeginMessage(int _begin) {
        return StringUtil.concat(Long.toString(_begin), "<0");
    }

}
