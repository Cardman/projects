package code.expressionlanguage;

import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.expressionlanguage.structs.Struct;

public interface ExecutableCode extends Analyzable {

    PageEl getOperationPageEl();
    ExecutableCode getExecutingInstance();
    ArrayStruct newStackTraceElementArray();
    StackTraceElementStruct newStackTraceElement(int _index);
    void setException(Struct _struct);

    Struct getException();
    boolean hasToExit(String _className);
}
