package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;
import code.util.StringList;

public final class ExecClassArgumentMatching {

    private static final String ARR_CLASS = "[";

    private final StringList className = new StringList();

    private final byte unwrapObjectNb;

    private final boolean checkOnlyNullPe;
    private final boolean convertToString;

    public ExecClassArgumentMatching(String _className) {
        className.add(_className);
        unwrapObjectNb = (byte)-1;
        checkOnlyNullPe = false;
        convertToString = false;
    }

    public ExecClassArgumentMatching(StringList _className, byte _unwrapObjectNb, boolean _checkOnlyNullPe, boolean _convertToString) {
        className.addAllElts(_className);
        unwrapObjectNb = _unwrapObjectNb;
        checkOnlyNullPe = _checkOnlyNullPe;
        convertToString = _convertToString;
    }

    public static Struct convert(PageEl _page, Struct _arg,
                                 ContextEl _exec, StringList _names) {
        StringList className_ = formatted(_page, _exec, _names);
        if (StringList.equalsSet(className_,new StringList(_exec.getStandards().getAliasNumber()))) {
            return NumParsers.convertToNumber(_arg);
        }
        byte cast_ = getPrimitiveWrapCast(NumParsers.getSingleNameOrEmpty(className_), _exec.getStandards());
        if (cast_ == PrimitiveTypes.BOOL_WRAP) {
            return NumParsers.convertToBoolean(_arg);
        }
        if (cast_ == PrimitiveTypes.CHAR_WRAP) {
            return NumParsers.convertToChar(_arg);
        }
        if (cast_ > 0) {
            return NumParsers.convertToNumber(cast_,_arg);
        }
        return _arg;
    }

    public static Struct convertWide(PageEl _page, Struct _arg,
                                     ContextEl _exec, StringList _names) {
        StringList className_ = formatted(_page, _exec, _names);
        return convertWide(_arg, _exec, className_);
    }

    private static Struct convertWide(Struct _arg, ContextEl _exec, StringList _names) {
        if (isPrimitive(_exec, _names)) {
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(_names, _exec.getStandards().getPrimTypes());
            if (cast_ == PrimitiveTypes.BOOL_WRAP) {
                return NumParsers.convertToBoolean(_arg);
            }
            if (cast_ == PrimitiveTypes.CHAR_WRAP) {
                return NumParsers.convertToChar(_arg);
            }
            return NumParsers.convertToNumber(cast_,_arg);
        }
        return _arg;
    }

    private static boolean isPrimitive(ContextEl _conf, StringList _names) {
        LgNames stds_ = _conf.getStandards();
        return isPrimitive(stds_, _names);
    }

    private static StringList formatted(PageEl _page, ContextEl _exec, StringList _clNames) {
        StringList className_ = new StringList();
        for (String s: _clNames) {
            className_.add(_page.formatVarType(s,_exec));
        }
        return className_;
    }

    private static boolean isPrimitive(LgNames stds_, StringList _names) {
        for (String n: _names) {
            if (stds_.getPrimitiveTypes().contains(n)) {
                return true;
            }
        }
        return false;
    }

    public static byte getPrimitiveWrapCast(String _className, LgNames stds_) {
        return ClassArgumentMatching.getPrimitiveCast(new StringList(toPrimitive(_className,stds_)), stds_.getPrimTypes());
    }

    private static String toPrimitive(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getValue().getWrapper(), _class)) {
                return e.getKey();
            }
        }
        return _class;
    }

    public static boolean isPrimitive(String _className, ContextEl _context) {
        return _context.getStandards().getPrimitiveTypes().contains(_className);
    }

    public static Struct defaultValue(String _class, ContextEl _context) {
        byte cast_ = ClassArgumentMatching.getPrimitiveCast(_class, _context.getStandards().getPrimTypes());
        return NumParsers.convert(cast_);
    }


    public String getSingleNameOrEmpty() {
        return NumParsers.getSingleNameOrEmpty(className);
    }
    public boolean isArray() {
        for (String b: className) {
            if (b.startsWith(ARR_CLASS)) {
                return true;
            }
        }
        return false;
    }

    public boolean matchClass(String _class) {
        StringList l_ = new StringList(_class);
        return StringList.equalsSet(className, l_);
    }

    public StringList getNames() {
        return className;
    }

    public byte getUnwrapObjectNb() {
        return unwrapObjectNb;
    }

    public boolean isCheckOnlyNullPe() {
        return checkOnlyNullPe;
    }

    public boolean isConvertToString() {
        return convertToString;
    }

}
