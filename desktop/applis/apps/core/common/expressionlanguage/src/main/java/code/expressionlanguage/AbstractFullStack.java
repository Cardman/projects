package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ArrayStruct;

public interface AbstractFullStack {
    ArrayStruct newStackTraceElementArray(StackCall _stack);
}
