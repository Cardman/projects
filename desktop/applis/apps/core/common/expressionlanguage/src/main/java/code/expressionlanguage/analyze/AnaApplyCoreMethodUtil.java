package code.expressionlanguage.analyze;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.stds.AnaStdCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.util.core.IndexConstants;

public final class AnaApplyCoreMethodUtil {
    private AnaApplyCoreMethodUtil() {
    }

    public static Struct invokeAnalyzisStdMethod(StandardMethod _std, Struct _struct, AnalyzedPageEl _page, Argument... _args) {
        StdCaller caller_ = _std.getCaller();
        Struct[] args_ = getObjects(_args);
        if (caller_ instanceof AnaStdCaller) {
            return ((AnaStdCaller)caller_).call(_page,_struct,args_);
        }
        return null;
    }

    public static Struct newAnalyzisInstanceStd(StandardConstructor _constructor, AnalyzedPageEl _page, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        StdCaller stdCaller_ = StandardType.caller(_constructor, null);
        AnaStdCaller anaStdCaller_;
        if (stdCaller_ instanceof AnaStdCaller) {
            anaStdCaller_ = (AnaStdCaller)stdCaller_;
        } else {
            anaStdCaller_ = null;
        }
        if (anaStdCaller_ != null) {
            return anaStdCaller_.call(_page,NullStruct.NULL_VALUE,args_);
        }
        return null;
    }

    private static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    public static AnaDisplayableStruct getAnaDisplayable(Struct _value) {
        if (_value instanceof NumberStruct) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof ClassMetaInfo) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof BooleanStruct) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof NullStruct) {
            return (AnaDisplayableStruct) _value;
        }
        if (_value instanceof RangeStruct) {
            return (AnaDisplayableStruct) _value;
        }
        return NumParsers.getString(_value);
    }

    public static String getString(Argument _value, AnalyzedPageEl _page) {
        Struct struct_ = _value.getStruct();
        if (struct_ instanceof ReplacementStruct) {
            return _page.getCharSeq().getAliasReplacement();
        }
        return getAnaDisplayable(struct_).getDisplayedString(_page).getInstance();
    }

    public static Argument localSumDiff(Argument _a, Argument _b, AnalyzedPageEl _page) {
        StringBuilder str_ = new StringBuilder();
        str_.append(getString(_a, _page));
        str_.append(getString(_b, _page));
        return new Argument(new StringStruct(str_.toString()));
    }

}
