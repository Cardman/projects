package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.ExpressionLanguage;

public interface WithNotEmptyEl extends WithEl {

    ExpressionLanguage getEl(ContextEl _context, int _indexProcess);

}
