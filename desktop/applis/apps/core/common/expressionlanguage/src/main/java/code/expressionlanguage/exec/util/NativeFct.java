package code.expressionlanguage.exec.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public interface NativeFct {
    Struct compute(Struct _arg, ContextEl _conf);
}
