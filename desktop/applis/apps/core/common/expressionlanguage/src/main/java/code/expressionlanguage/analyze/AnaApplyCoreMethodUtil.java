package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.AnaDisplayableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class AnaApplyCoreMethodUtil {
    private AnaApplyCoreMethodUtil() {
    }

    public static Struct invokeAnalyzisStdMethod(StandardMethod _std, Struct _struct, AnalyzedPageEl _page, Struct... _args) {
        StdCaller caller_ = _std.getCaller();
        if (caller_ instanceof AnaStdCaller) {
            return ((AnaStdCaller)caller_).call(_page,_struct,_args);
        }
        return null;
    }

    public static Struct newAnalyzisInstanceStd(StandardConstructor _constructor, AnalyzedPageEl _page, Struct... _args) {
        StdCaller stdCaller_ = StandardType.caller(_constructor, null);
        AnaStdCaller anaStdCaller_;
        if (stdCaller_ instanceof AnaStdCaller) {
            anaStdCaller_ = (AnaStdCaller)stdCaller_;
        } else {
            anaStdCaller_ = null;
        }
        if (anaStdCaller_ != null) {
            return anaStdCaller_.call(_page,NullStruct.NULL_VALUE,_args);
        }
        return null;
    }

    private static AnaDisplayableStruct getAnaDisplayable(Struct _value) {
        if (_value instanceof AnaDisplayableStruct) {
            return (AnaDisplayableStruct) _value;
        }
        return null;
    }

    public static String getString(Struct _value, AnalyzedPageEl _page) {
        AnaDisplayableStruct dis_ = getAnaDisplayable(_value);
        if (dis_ == null) {
            return _page.getCharSeq().getAliasReplacement();
        }
        return dis_.getDisplayedString(_page).getInstance();
    }

    public static Struct localSumDiff(Struct _a, Struct _b, AnalyzedPageEl _page) {
        StringBuilder str_ = new StringBuilder();
        str_.append(getString(_a, _page));
        str_.append(getString(_b, _page));
        return new StringStruct(str_.toString());
    }

}
