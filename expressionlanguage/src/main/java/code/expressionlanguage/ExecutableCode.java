package code.expressionlanguage;

import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.structs.Struct;

public interface ExecutableCode extends Analyzable {

    PageEl getOperationPageEl();
    void setException(Struct _struct);
    String joinPages();
    Struct getException();
}
