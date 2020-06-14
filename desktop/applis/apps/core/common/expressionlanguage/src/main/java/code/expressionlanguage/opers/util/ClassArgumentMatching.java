package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;

public final class ClassArgumentMatching {

    private static final String ARR_CLASS = "[";

    private final StringList className = new StringList();

    private String unwrapObject = "";

    private boolean checkOnlyNullPe;
    private boolean convertToString;
    private CustList<ClassMethodId> implicits = new CustList<ClassMethodId>();

    public ClassArgumentMatching(String _className) {
        className.add(_className);
    }

    public ClassArgumentMatching(StringList _className) {
        className.addAllElts(_className);
    }

    public ClassArgumentMatching(ClassArgumentMatching _copy) {
        className.addAllElts(_copy.className);
        unwrapObject = _copy.unwrapObject;
        checkOnlyNullPe = _copy.checkOnlyNullPe;
        convertToString = _copy.convertToString;
    }

    public static Struct convert(PageEl _page,ClassArgumentMatching _dest,Struct _arg,
                                 ContextEl _exec) {
        ClassArgumentMatching format_ = _dest.format(_page,_exec);
        if (format_.matchClass(_exec.getStandards().getAliasNumber())) {
            return convertToNumber(_arg);
        }
        if (format_.isBoolType(_exec)) {
            return convertToBoolean(_arg);
        }
        if (PrimitiveTypeUtil.toPrimitive(format_,_exec).matchClass(_exec.getStandards().getAliasPrimChar())) {
            return convertToChar(_arg);
        }
        if (PrimitiveTypeUtil.isPureNumberClass(format_,_exec)) {
            return PrimitiveTypeUtil.convertToNumber(format_,_arg,_exec.getStandards());
        }
        return _arg;
    }

    public static Struct convertWide(PageEl _page,ClassArgumentMatching _dest,Struct _arg,
                                 ContextEl _exec) {
        ClassArgumentMatching format_ = _dest.format(_page,_exec);
        return convertWide(format_, _arg, _exec);
    }

    public static Struct convertWide(ClassArgumentMatching _dest, Struct _arg, ContextEl _exec) {
        if (PrimitiveTypeUtil.isPrimitive(_dest,_exec)) {
            if (_dest.isBoolType(_exec)) {
                return convertToBoolean(_arg);
            }
            if (PrimitiveTypeUtil.toPrimitive(_dest,_exec).matchClass(_exec.getStandards().getAliasPrimChar())) {
                return convertToChar(_arg);
            }
            return PrimitiveTypeUtil.convertToNumber(_dest,_arg,_exec.getStandards());
        }
        return _arg;
    }

    public static BooleanStruct convertToBoolean(Struct _arg) {
        if (_arg instanceof BooleanStruct) {
            return (BooleanStruct) _arg;
        }
        return BooleanStruct.of(false);
    }

    public static CharStruct convertToChar(Struct _arg) {
        if (_arg instanceof CharStruct) {
            return (CharStruct) _arg;
        }
        return new CharStruct((char)0);
    }

    public static NumberStruct convertToNumber(Struct _arg) {
        if (_arg instanceof NumberStruct) {
            return (NumberStruct) _arg;
        }
        return new ByteStruct((byte)0);
    }

    private ClassArgumentMatching format(PageEl _page, ContextEl _exec) {
        StringList className_ = new StringList();
        for (String s: className) {
            className_.add(_page.formatVarType(s,_exec));
        }
        return new ClassArgumentMatching(className_);
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
        LgNames stds_ = _context.getStandards();
        String intPr_ = stds_.getAliasPrimInteger();
        String shortPr_ = stds_.getAliasPrimShort();
        String charPr_ = stds_.getAliasPrimChar();
        String bytePr_ = stds_.getAliasPrimByte();
        ClassArgumentMatching prim_ = PrimitiveTypeUtil.toPrimitive(this, _context);
        if (prim_.matchClass(intPr_)) {
            return true;
        }
        if (prim_.matchClass(shortPr_)) {
            return true;
        }
        if (prim_.matchClass(charPr_)) {
            return true;
        }
        return prim_.matchClass(bytePr_);
    }

    public boolean isArray() {
        for (String b: className) {
            if (b.startsWith(ARR_CLASS)) {
                return true;
            }
        }
        return false;
    }
    public boolean matchVoid(ContextEl _classes) {
        LgNames stds_ = _classes.getStandards();
        StringList l_ = new StringList(stds_.getAliasVoid());
        return StringList.equalsSet(className, l_);
    }

    public boolean matchClass(String _class) {
        StringList l_ = new StringList(_class);
        return StringList.equalsSet(className, l_);
    }

    public boolean isVariable() {
        return StringList.contains(className, "");
    }

    public boolean isPrimitive(ContextEl _context) {
        for (String b: className) {
            if (PrimitiveTypeUtil.isPrimitive(b, _context)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWrapper(ContextEl _context) {
        for (String b: className) {
            if (PrimitiveTypeUtil.isWrapper(b, _context)) {
                return true;
            }
        }
        return false;
    }
    public boolean isBoolType(ContextEl _context) {
        LgNames lgNames_ = _context.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        String aliasPrBoolean_ = lgNames_.getAliasPrimBoolean();
        for (String b: className) {
            if (b.isEmpty()) {
                return true;
            }
            if (matchClass(aliasBoolean_)) {
                return true;
            }
            if (matchClass(aliasPrBoolean_)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return className.first();
    }

    public String getSingleNameOrEmpty() {
        if (className.size() != 1) {
            return "";
        }
        return className.first();
    }

    public StringList getNames() {
        return new StringList(className);
    }

    public String getUnwrapObject() {
        return unwrapObject;
    }

    public void setUnwrapObject(String _unwrapObject) {
        unwrapObject = _unwrapObject;
    }

    public void setUnwrapObject(ClassArgumentMatching _other) {
        unwrapObject = _other.getName();
    }

    public boolean isCheckOnlyNullPe() {
        return checkOnlyNullPe;
    }

    public void setCheckOnlyNullPe(boolean _checkOnlyNullPe) {
        checkOnlyNullPe = _checkOnlyNullPe;
    }

    public boolean isConvertToString() {
        return convertToString;
    }

    public void setConvertToString(boolean _convertToString) {
        convertToString = _convertToString;
    }

    public CustList<ClassMethodId> getImplicits() {
        return implicits;
    }
}
