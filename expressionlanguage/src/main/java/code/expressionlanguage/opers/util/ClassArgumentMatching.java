package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.OperationNode;
import code.util.CustList;
import code.util.StringList;
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

    public boolean isNumericInt(ContextEl _context) {
        ClassArgumentMatching cl_ = new ClassArgumentMatching(className);
        ClassArgumentMatching prim_ = PrimitiveTypeUtil.toPrimitive(cl_, true);
        if (prim_.matchClass(PrimitiveTypeUtil.PRIM_INT)) {
            return true;
        }
        if (prim_.matchClass(PrimitiveTypeUtil.PRIM_SHORT)) {
            return true;
        }
        if (prim_.matchClass(PrimitiveTypeUtil.PRIM_BYTE)) {
            return true;
        }
        return false;
    }
    public Class<?> getClazz() {
        String className_ = StringList.getAllTypes(className).first();
        if (PrimitiveTypeUtil.isPrimitive(className_)) {
            return PrimitiveTypeUtil.getPrimitiveClass(className_);
        }
        return PrimitiveTypeUtil.getSingleNativeClass(className_);
    }

    public Class<?> getClassOrNull() {
        try {
            return getClazz();
        } catch (RuntimeClassNotFoundException _0) {
            return null;
        }
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
            Class<?> cl_ = PrimitiveTypeUtil.getSingleNativeClass(className);
            CustList<Class<?>> cls_ = new CustList<Class<?>>();
            for (Class<?> c: cl_.getDeclaredClasses()) {
                cls_.add(c);
            }
            return cls_;
        } catch (RuntimeClassNotFoundException _0) {
            return new CustList<Class<?>>();
        }
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    public boolean isPrimitive(ContextEl _context) {
        return PrimitiveTypeUtil.isPrimitive(className);
    }

    public String getName() {
        return className;
    }
}
