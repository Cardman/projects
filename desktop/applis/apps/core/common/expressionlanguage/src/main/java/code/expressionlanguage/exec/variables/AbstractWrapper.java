package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;

public interface AbstractWrapper {
    void setValue(StackCall _stack, ContextEl _conf, Argument _right);
    Struct getValue(StackCall _stack, ContextEl _conf);
    String getClassName(StackCall _stack, ContextEl _conf);
}
