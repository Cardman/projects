package code.expressionlanguage.opers.util;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.util.StringList;
import code.util.consts.ConstClasses;

public final class ClassMatching {

    private StringList className;

    public ClassMatching(StringList _className) {
        className = _className;
    }
    public ClassMatching(String _className) {
        className = new StringList(_className);
    }

    @Override
    public String toString() {
        return className.toString();
    }
    public boolean matchClass(String _className) {
        return StringList.equalsSet(className, new StringList(_className));
    }
    public boolean matchClass(Class<?> _class) {
        return StringList.equalsSet(className, new StringList(_class.getName()));
    }

    public boolean matchClass(ClassMatching _className) {
        return StringList.equalsSet(className, _className.className);
    }

    public boolean isAssignableFrom(ClassMatching _c, Classes _classes) {
        for (String p: className) {
            if (StringList.quickEq(p, Object.class.getName())) {
                continue;
            }
            boolean ok_ = false;
            for (String c: _c.getClassName()) {
                if (PrimitiveTypeUtil.canBeUseAsArgument(p, c, _classes)) {
                    ok_  = true;
                    break;
                }
            }
            if (!ok_) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrimitive() {
        return PrimitiveTypeUtil.isPrimitive(className.first());
    }

    public StringList getClassName() {
        return className;
    }
}
