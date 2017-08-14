package code.expressionlanguage.opers.util;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ClassMatching {

    private StringList className;
//    private Class<?> clazz;

//    public ClassMatching(Class<?> _clazz) {
//        clazz = _clazz;
//    }

    public ClassMatching(StringList _className) {
        className = _className;
//        clazz = ConstClasses.classForNameNotInit(_className);
    }
    
    public ClassMatching(String _className) {
        className = new StringList(_className);
//        clazz = ConstClasses.classForNameNotInit(_className);
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
//        return StringList.quickEq(className, _className.className);
        return StringList.equalsSet(className, _className.className);
    }

//    public Class<?> getClazz() {
//        return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
//    }

//    public void setClazz(Class<?> _clazz) {
//        clazz = _clazz;
//    }

    public boolean isAssignableFrom(ClassMatching _c, Classes _classes) {
        for (String p: className) {
            if (StringList.quickEq(p, Object.class.getName())) {
                continue;
            }
            boolean ok_ = false;
            for (String c: _c.getClassName()) {
                if (PrimitiveTypeUtil.isAssignableFrom(p, c, _classes)) {
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
        if (className.first().startsWith(PrimitiveTypeUtil.PRIM)) {
            return true;
        }
        try {
            return getSingleNativeClass().isPrimitive();
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
    }

//
//    public Class<?> getComponentType() {
//        try {
//            Class<?> c_ = getNativeClass();
//            return c_.getComponentType();
//        } catch (Exception _0_) {
//            return null;
//        }
//    }
    
    private Class<?> getSingleNativeClass() {
        return ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(className.first()));
    }

    public static Class<?> getSingleNativeClass(String _className) {
        return ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(_className));
    }

    public StringList getClassName() {
        return className;
    }
}
