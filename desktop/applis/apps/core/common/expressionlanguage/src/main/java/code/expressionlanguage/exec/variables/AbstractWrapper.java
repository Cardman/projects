package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;

public interface AbstractWrapper {
    void setValue(StackCall _stack, ContextEl _conf, Struct _right);
    Struct getValue();
    void setValue(Struct _v);
    Struct getValue(StackCall _stack, ContextEl _conf);
    String getClassName(ContextEl _conf);
}
