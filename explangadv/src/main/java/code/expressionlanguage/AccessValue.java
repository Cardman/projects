package code.expressionlanguage;
import java.lang.reflect.AccessibleObject;

public abstract class AccessValue {

    public void setAccess(AccessibleObject _f, ContextEl _context) {
        _f.setAccessible(canAccess(_f, _context));
    }
    public abstract boolean canBeUsed(AccessibleObject _f, ContextEl _context);
    public abstract boolean canAccess(AccessibleObject _f, ContextEl _context);
}
