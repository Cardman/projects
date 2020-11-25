package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public interface AbstractWrapper {
    void setValue(ContextEl _conf, Argument _right);
    Struct getValue();
}
