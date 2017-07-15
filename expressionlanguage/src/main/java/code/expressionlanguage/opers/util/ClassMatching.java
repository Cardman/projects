package code.expressionlanguage.opers.util;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ClassMatching {

    private String className;
//    private Class<?> clazz;

//    public ClassMatching(Class<?> _clazz) {
//        clazz = _clazz;
//    }

    public ClassMatching(String _className) {
        className = _className;
//        clazz = ConstClasses.classForNameNotInit(_className);
    }

    @Override
    public String toString() {
        return className;
    }
    
    public boolean matchClass(String _className) {
        return StringList.quickEq(className, _className);
    }
    
    
    public boolean matchClass(Class<?> _class) {
        return StringList.quickEq(className, _class.getName());
    }
    

    public boolean matchClass(ClassMatching _className) {
        return StringList.quickEq(className, _className.className);
    }

    public Class<?> getClazz() {
        return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
    }

//    public void setClazz(Class<?> _clazz) {
//        clazz = _clazz;
//    }

    public boolean isAssignableFrom(ClassMatching _c) {
        if (StringList.quickEq(className, Object.class.getName())) {
            return true;
        }
        try {
            Class<?> cur_ = getNativeClass();
            Class<?> arg_ = _c.getNativeClass();
            return cur_.isAssignableFrom(arg_);
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
//        return clazz.isAssignableFrom(_c.clazz);
    }

    public boolean isPrimitive() {
        if (className.startsWith(PrimitiveTypeUtil.PRIM)) {
            return true;
        }
        try {
            return getNativeClass().isPrimitive();
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
    }

    public String getName() {
        return className;
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

    private Class<?> getNativeClass() {
        return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
    }
}
