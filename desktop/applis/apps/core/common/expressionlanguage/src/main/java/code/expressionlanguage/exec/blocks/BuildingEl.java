package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;

public interface BuildingEl {

    ExpressionLanguage getEl(ContextEl _context, int _indexProcess);

}
