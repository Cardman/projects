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


    public static ExecClassArgumentMatching toExec(AnaClassArgumentMatching _cl) {
        ExecClassArgumentMatching e_ = new ExecClassArgumentMatching(_cl.getNames(),_cl.getUnwrapObjectNb(),
                _cl.isCheckOnlyNullPe(),_cl.isConvertToString());
        return e_;
    }


}
