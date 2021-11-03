package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class FctStringRegionMatches implements AnaStdCaller {
    @Override
    public Struct call(AnalyzedPageEl _page, Struct _instance, Struct[] _args) {
        return regionMatches((StringStruct)_instance, NumParsers.convertToBoolean(_args[0]), NumParsers.convertToNumber(_args[1]), _args[2],
                NumParsers.convertToNumber(_args[3]), NumParsers.convertToNumber(_args[4]));
    }

    private static Struct regionMatches(StringStruct _str, BooleanStruct _case, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                        NumberStruct _len) {
        if (!(_other instanceof StringStruct)) {
            return null;
        }
        boolean case_ = BooleanStruct.isTrue(_case);
        StringStruct other_ = (StringStruct) _other;
        int comLen_ = _len.intStruct();
        int to_ = _toffset.intStruct();
        int po_ = _ooffset.intStruct();
        return BooleanStruct.of(NumParsers.regionMatches(_str.getInstance(),case_,to_, other_.getInstance(), po_, comLen_));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct three_ = argumentWrappers_.get(2).getValue().getStruct();
        Struct four_ = argumentWrappers_.get(3).getValue().getStruct();
        Struct five_ = argumentWrappers_.get(4).getValue().getStruct();
        return regionMatches((StringStruct)_instance, NumParsers.convertToBoolean(one_), NumParsers.convertToNumber(two_), three_, NumParsers.convertToNumber(four_), NumParsers.convertToNumber(five_), _cont, _stackCall);
    }

    private static ArgumentWrapper regionMatches(StringStruct _str, BooleanStruct _case, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                      NumberStruct _len, ContextEl _context, StackCall _stackCall) {
        if (!(_other instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        boolean case_ = BooleanStruct.isTrue(_case);
        int to_ = _toffset.intStruct();
        StringStruct other_ = (StringStruct) _other;
        int po_ = _ooffset.intStruct();
        int comLen_ = _len.intStruct();
        return new ArgumentWrapper(BooleanStruct.of(NumParsers.regionMatches(_str.getInstance(),case_,to_, other_.getInstance(), po_, comLen_)));
    }
}
