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
        String className_ = StringList.getAllTypes(className).first();
        if (PrimitiveTypeUtil.isPrimitive(className_)) {
            return PrimitiveTypeUtil.getPrimitiveClass(className_);
        }
        return ConstClasses.classForObjectNameNotInit(PrimitiveTypeUtil.getArrayClass(className_));
    }

    public boolean isArray() {
        return className.startsWith(ARR_CLASS);
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
            Class<?> cl_ = ConstClasses.classForObjectNameNotInit(PrimitiveTypeUtil.getArrayClass(className));
            CustList<Class<?>> cls_ = new CustList<Class<?>>();
            for (Class<?> c: cl_.getDeclaredClasses()) {
                cls_.add(c);
            }
            return cls_;
        } catch (RuntimeClassNotFoundException _0) {
            return new CustList<Class<?>>();
        }
    }

    public boolean isAssignableFrom(ClassMatching _arg, Classes _classes) {
        for (String c: _arg.getClassName()) {
            if (!PrimitiveTypeUtil.canBeUseAsArgument(className, c, _classes)) {
                continue;
            }
            return true;
        }
        return false;
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    public boolean isPrimitive() {
        return PrimitiveTypeUtil.isPrimitive(className);
    }

    public String getName() {
        return className;
    }
}
