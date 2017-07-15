package code.expressionlanguage.opers.util;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.OperationNode;
import code.util.CustList;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ClassArgumentMatching {

    private static final String ARR_CLASS = "[";
//    private final Class<?> clazz;
    private final String className;

    private boolean variable;

    public ClassArgumentMatching(String _className) {
        className = _className;
    }

    public static ClassArgumentMatching[] toArgArray(CustList<ClassArgumentMatching> _args) {
        int len_ = _args.size();
        ClassArgumentMatching[] args_;
        args_ = new ClassArgumentMatching[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            args_[i] = _args.get(i);
        }
        return args_;
    }

    @Override
    public String toString() {
        return className;
    }

    public boolean isNumericInt() {
        try {
            Class<?> clazz_ = getClazz();
            if (clazz_  == Integer.class) {
                return true;
            }
            if (clazz_ == int.class) {
                return true;
            }
            if (clazz_ == Short.class) {
                return true;
            }
            if (clazz_ == short.class) {
                return true;
            }
            if (clazz_ == Byte.class) {
                return true;
            }
            if (clazz_ == byte.class) {
                return true;
            }
        } catch (RuntimeClassNotFoundException _0) {
        }
        return false;
    }
    public Class<?> getClazz() {
        if (className.startsWith(PrimitiveTypeUtil.PRIM)) {
            return ConstClasses.getPrimitiveClass(className.substring(1));
        }
        return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
    }

    public boolean isArray() {
        return className.startsWith(ARR_CLASS);
//        try {
//            Class<?> cl_ = ConstClasses.classForNameNotInit(className);
//            return cl_.isArray();
//        } catch (RuntimeClassNotFoundException _0) {
//            return false;
//        }
    }
    
    public boolean matchClass(ClassArgumentMatching _class) {
        return StringList.quickEq(className, _class.getName());
    }
    
    public boolean matchVoid() {
        return StringList.quickEq(className, OperationNode.VOID_RETURN);
    }
    
    public boolean matchClass(Class<?> _class) {
        return StringList.quickEq(className, _class.getName());
    }

    public boolean matchClass(String _class) {
        return StringList.quickEq(className, _class);
    }

    public CustList<Class<?>> getDeclaredClasses() {
        try {
            Class<?> cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
            CustList<Class<?>> cls_ = new CustList<Class<?>>();
            for (Class<?> c: cl_.getDeclaredClasses()) {
                cls_.add(c);
            }
            return cls_;
        } catch (RuntimeClassNotFoundException _0) {
            return new CustList<Class<?>>();
        }
    }

    public boolean isInterface() {
        try {
            Class<?> cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
            return cl_.isInterface();
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
    }

    public boolean isAssignableFrom(ClassArgumentMatching _c, Classes _classes) {
        AssignableFrom a_ = PrimitiveTypeUtil.isAssignableFromCust(className, _c.getName(), _classes);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }
        try {
            if (className.startsWith(PrimitiveTypeUtil.PRIM)) {
                Class<?> cl_ = ConstClasses.getPrimitiveClass(className.substring(1));
                return cl_.isAssignableFrom(_c.getClazz());
            }
            Class<?> cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
            return cl_.isAssignableFrom(_c.getClazz());
        } catch (RuntimeClassNotFoundException _0) {
            return false;
        }
    }

    public boolean isAssignableFrom(ClassMatching _c, Classes _classes) {
        AssignableFrom a_ = PrimitiveTypeUtil.isAssignableFromCust(className, _c.getName(), _classes);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }
        try {
            Class<?> cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
            return cl_.isAssignableFrom(_c.getClazz());
        } catch (RuntimeClassNotFoundException _0_) {
            return false;
        }
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    public boolean isPrimitive() {
        if (className.startsWith(PrimitiveTypeUtil.PRIM)) {
            return true;
        }
        try {
            Class<?> cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
            return cl_.isPrimitive();
        } catch (RuntimeClassNotFoundException _0_) {
            return false;
        }
    }

    public String getName() {
        return className;
    }

//    public String getComponentTypeName() {
//        return getComponentType().getName();
//    }

//    public Class<?> getComponentType() {
//        Class<?> cl_ = ConstClasses.classForNameNotInit(className);
//        return cl_.getComponentType();
//    }

}
