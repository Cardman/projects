package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public interface NativeFct {
    Struct compute(Argument _arg, ContextEl _conf);
}
