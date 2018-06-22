package code.expressionlanguage;

import code.expressionlanguage.opers.util.Struct;

public interface ExecutableCode extends Analyzable {

    PageEl getOperationPageEl();
    void setException(Struct _struct);
    String joinPages();
    Struct getException();
    ContextEl getContextEl();
}
