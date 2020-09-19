package code.expressionlanguage.inherits;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.*;

public final class PrimitiveTypeUtil {
    public static final String ARR_CLASS = "[";

    private PrimitiveTypeUtil() {
    }


    public static boolean isPrimitive(String _className, LgNames _stds) {
        return _stds.getPrimitiveTypes().contains(_className);
    }


    public static ExecClassArgumentMatching toExec(AnaClassArgumentMatching _cl) {
        ExecClassArgumentMatching e_ = new ExecClassArgumentMatching(_cl.getNames());
        e_.setCheckOnlyNullPe(_cl.isCheckOnlyNullPe());
        e_.setConvertToString(_cl.isConvertToString());
        e_.setUnwrapObjectNb(_cl.getUnwrapObjectNb());
        return e_;
    }


    public static String toWrapper(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getKey(), _class)) {
                return e.getValue().getWrapper();
            }
        }
        return _class;
    }

    public static Struct defaultValue(String _class, LgNames _stds) {
        byte cast_ = ClassArgumentMatching.getPrimitiveCast(_class, _stds);
        if (cast_ > -1) {
            if (cast_ == PrimitiveTypes.BOOL_WRAP) {
                return BooleanStruct.of(false);
            }
            return NumParsers.convert(cast_);
        }
        return NullStruct.NULL_VALUE;
    }

}
