package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class FctRangeUnlimitedStep implements AnaStdCaller {

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return rangeUnlimitStep(_args);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(rangeUnlimitStep(_cont,_stackCall,_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(), _firstArgs.getArgumentWrappers().get(1).getValue().getStruct()).getStruct());
    }

    public static Struct range(Struct... _args) {
        if (_args.length == 3) {
            return rangeBoundsStep(_args[0], _args[1], _args[2]);
        }
        if (_args.length == 2) {
            return rangeBounds(_args[0], _args[1]);
        }
        return rangeUnlimit(_args[0]);
    }

    public static Struct rangeBoundsStep(Struct _min, Struct _max, Struct _step) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            return null;
        }
        int upper_ = NumParsers.convertToNumber(_max).intStruct();
        if (upper_ < lower_) {
            return null;
        }
        int step_ = NumParsers.convertToNumber(_step).intStruct();
        if (step_ == 0) {
            return null;
        }
        return new RangeStruct(lower_, upper_, step_);
    }

    public static Struct rangeUnlimitStep(Struct... _args) {
        int lower_ = NumParsers.convertToNumber(_args[0]).intStruct();
        if (lower_ < 0) {
            return null;
        }
        int step_ = NumParsers.convertToNumber(_args[1]).intStruct();
        if (step_ == 0) {
            return null;
        }
        return new RangeStruct(lower_, -1, step_);
    }

    public static Struct rangeBounds(Struct _min, Struct _max) {
        int lower_ = NumParsers.convertToNumber(_min).intStruct();
        if (lower_ < 0) {
            return null;
        }
        int upper_ = NumParsers.convertToNumber(_max).intStruct();
        if (upper_ < lower_) {
            return null;
        }
        return new RangeStruct(lower_, upper_);
    }

    public static Struct rangeUnlimit(Struct _arg) {
        int lower_ = NumParsers.convertToNumber(_arg).intStruct();
        if (lower_ < 0) {
            return null;
        }
        return new RangeStruct(lower_);
    }

    public static Argument range(ContextEl _conf, StackCall _stack, Struct... _args) {
        if (_args.length == 3) {
            return FctRange2.rangeBoundsStep(_conf, _stack, _args[0], _args[1], _args[2]).getValue();
        }
        if (_args.length == 2) {
            return FctRange1.rangeBounds(_conf, _stack, _args[0], _args[1]).getValue();
        }
        return FctRange0.rangeUnlimit(_conf,_stack,_args[0]).getValue();
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
