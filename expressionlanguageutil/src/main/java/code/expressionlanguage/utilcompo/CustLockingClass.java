package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.InitClassState;

public class CustLockingClass extends DefaultLockingClass {

    @Override
    public InitClassState getState(ContextEl _context, String _className) {
        return InitClassState.SUCCESS;
    }
}
