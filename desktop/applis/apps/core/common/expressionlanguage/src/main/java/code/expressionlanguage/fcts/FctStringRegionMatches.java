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
        Struct other_ = _args[2];
        if (!(other_ instanceof StringStruct)) {
            return null;
        }
        return regMatches((StringStruct)_instance, NumParsers.convertToBoolean(_args[0]), NumParsers.convertToNumber(_args[1]), (StringStruct) other_, NumParsers.convertToNumber(_args[3]), NumParsers.convertToNumber(_args[4]));
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct three_ = argumentWrappers_.get(2).getValue().getStruct();
        Struct four_ = argumentWrappers_.get(3).getValue().getStruct();
        Struct five_ = argumentWrappers_.get(4).getValue().getStruct();
        if (!(three_ instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(AliasReflection.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(regMatches((StringStruct)_instance, NumParsers.convertToBoolean(one_), NumParsers.convertToNumber(two_), (StringStruct) three_, NumParsers.convertToNumber(four_), NumParsers.convertToNumber(five_)));
    }

    private static BooleanStruct regMatches(StringStruct _str, BooleanStruct _case, NumberStruct _toffset, StringStruct _other, NumberStruct _ooffset, NumberStruct _len) {
        boolean case_ = BooleanStruct.isTrue(_case);
        int to_ = _toffset.intStruct();
        int po_ = _ooffset.intStruct();
        int comLen_ = _len.intStruct();
        return BooleanStruct.of(NumParsers.regionMatches(_str.getInstance(), case_, to_, _other.getInstance(), po_, comLen_));
    }
}
