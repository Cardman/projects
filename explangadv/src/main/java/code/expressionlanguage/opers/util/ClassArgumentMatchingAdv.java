package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtilAdv;
import code.util.StringList;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ClassArgumentMatchingAdv {

    private final ClassArgumentMatching arg;

    public ClassArgumentMatchingAdv(ClassArgumentMatching _arg) {
        arg = _arg;
    }
    public ClassArgumentMatchingAdv(String _globalClass) {
        arg = new ClassArgumentMatching(_globalClass);
    }
    public Class<?> getClazz() {
        String className_ = StringList.getAllTypes(arg.getName()).first();
        if (PrimitiveTypeUtilAdv.isPrimitive(className_)) {
            return PrimitiveTypeUtilAdv.getPrimitiveClass(className_);
        }
        return PrimitiveTypeUtilAdv.getSingleNativeClass(className_);
    }

    public Class<?> getClassOrNull() {
        try {
            return getClazz();
        } catch (RuntimeClassNotFoundException _0) {
            return null;
        }
    }
    public String getName() {
        return arg.getName();
    }
    public boolean isPrimitive(ContextEl _context) {
        // TODO Auto-generated method stub
        return false;
    }
}
