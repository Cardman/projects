package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;

public interface BuildableElMethod extends WithEl {
    void buildExpressionLanguage(ContextEl _cont);
    void buildExpressionLanguageReadOnly(ContextEl _cont);
}
