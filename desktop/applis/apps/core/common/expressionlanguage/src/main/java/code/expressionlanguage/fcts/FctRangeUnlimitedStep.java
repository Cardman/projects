package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.RangeChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;

public final class FctRangeUnlimitedStep implements AnaStdCaller {

    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return rangeUnlimitStep(_args);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(RangeChecker.rangeUnlimitStep(_cont,_stackCall,_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(), _firstArgs.getArgumentWrappers().get(1).getValue().getStruct()).getStruct());
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

}
