package code.expressionlanguage;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

import code.expressionlanguage.AccessValue;
import code.expressionlanguage.ContextEl;

public class AccessValueEx extends AccessValue {

    @Override
    public boolean canBeUsed(AccessibleObject _f, ContextEl _context) {
        if (canAccess(_f, _context)) {
            return true;
        }
        if (_f instanceof Member) {
            return Modifier.isPublic(((Member)_f).getModifiers());
        }
        return false;
    }

    @Override
    public boolean canAccess(AccessibleObject _f, ContextEl _context) {
        return _f.getAnnotation(AccEl.class) != null;
    }

}
