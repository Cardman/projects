package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class ClassArgumentMatching {

    private static final String ARR_CLASS = "[";

    private final String className;

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

    public boolean isNumericInt(ContextEl _context) {
        ClassArgumentMatching cl_ = new ClassArgumentMatching(className);
        LgNames stds_ = _context.getStandards();
        String intPr_ = stds_.getAliasPrimInteger();
        String shortPr_ = stds_.getAliasPrimShort();
        String bytePr_ = stds_.getAliasPrimByte();
        ClassArgumentMatching prim_ = PrimitiveTypeUtil.toPrimitive(cl_, true, _context);
        if (prim_.matchClass(intPr_)) {
            return true;
        }
        if (prim_.matchClass(shortPr_)) {
            return true;
        }
        if (prim_.matchClass(bytePr_)) {
            return true;
        }
        return false;
    }

    public boolean isArray() {
        return className.startsWith(ARR_CLASS);
    }
    public boolean matchClass(ClassArgumentMatching _class) {
        return StringList.quickEq(className, _class.getName());
    }
    public boolean matchVoid(ContextEl _classes) {
        LgNames stds_ = _classes.getStandards();
        return StringList.quickEq(className, stds_.getAliasVoid());
    }
    public boolean matchVoid(LgNames _classes) {
        LgNames stds_ = _classes;
        return StringList.quickEq(className, stds_.getAliasVoid());
    }

    public boolean matchClass(String _class) {
        return StringList.quickEq(className, _class);
    }

//    public CustList<Class<?>> getDeclaredClasses() {
//        try {
//            Class<?> cl_ = PrimitiveTypeUtil.getSingleNativeClass(className);
//            CustList<Class<?>> cls_ = new CustList<Class<?>>();
//            for (Class<?> c: cl_.getDeclaredClasses()) {
//                cls_.add(c);
//            }
//            return cls_;
//        } catch (Throwable _0) {
//            return new CustList<Class<?>>();
//        }
//    }

    public boolean isVariable() {
        return className.isEmpty();
    }

    public boolean isPrimitive(LgNames _context) {
        return PrimitiveTypeUtil.isPrimitive(className, _context);
    }

    public boolean isPrimitive(ContextEl _context) {
        return PrimitiveTypeUtil.isPrimitive(className, _context);
    }

    public String getName() {
        return className;
    }
}
