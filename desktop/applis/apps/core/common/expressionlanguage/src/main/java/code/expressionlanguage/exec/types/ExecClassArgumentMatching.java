package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;
import code.util.StringList;

public final class ExecClassArgumentMatching {

    private static final String ARR_CLASS = "[";

    private final StringList className = new StringList();

    private byte unwrapObjectNb = (byte)-1;

    private boolean checkOnlyNullPe;
    private boolean convertToString;

    public ExecClassArgumentMatching(String _className) {
        className.add(_className);
    }

    public ExecClassArgumentMatching(String _className, byte _conv) {
        className.add(_className);
        unwrapObjectNb = _conv;
    }

    public ExecClassArgumentMatching(StringList _className) {
        className.addAllElts(_className);
    }

    public static Struct convert(PageEl _page, Struct _arg,
                                 ContextEl _exec, StringList _names) {
        ExecClassArgumentMatching format_ = format(_page,_exec, _names);
        if (format_.matchClass(_exec.getStandards().getAliasNumber())) {
            return NumParsers.convertToNumber(_arg);
        }
        byte cast_ = getPrimitiveWrapCast(toPrimitive(format_,_exec).getSingleNameOrEmpty(), _exec.getStandards());
        if (cast_ == PrimitiveTypes.BOOL_WRAP) {
            return NumParsers.convertToBoolean(_arg);
        }
        if (toPrimitive(format_,_exec).matchClass(_exec.getStandards().getAliasPrimChar())) {
            return NumParsers.convertToChar(_arg);
        }
        if (cast_ > 0) {
            return NumParsers.convertToNumber(cast_,_arg);
        }
        return _arg;
    }

    public static Struct convertWide(PageEl _page, Struct _arg,
                                     ContextEl _exec, StringList _names) {
        ExecClassArgumentMatching format_ = format(_page,_exec, _names);
        return convertWide(format_, _arg, _exec);
    }

    public static Struct convertWide(ExecClassArgumentMatching _dest, Struct _arg, ContextEl _exec) {
        if (isPrimitive(_dest,_exec)) {
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(_dest.getNames(), _exec.getStandards());
            if (cast_ == PrimitiveTypes.BOOL_WRAP) {
                return NumParsers.convertToBoolean(_arg);
            }
            if (toPrimitive(_dest,_exec).matchClass(_exec.getStandards().getAliasPrimChar())) {
                return NumParsers.convertToChar(_arg);
            }
            return NumParsers.convertToNumber(cast_,_arg);
        }
        return _arg;
    }

    public static boolean isPrimitive(ExecClassArgumentMatching _clMatchLeft,
                                      ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        return isPrimitive(_clMatchLeft, stds_);
    }

    public static ExecClassArgumentMatching toPrimitive(ExecClassArgumentMatching _class, ContextEl _context) {
        return toPrimitive(_class, _context.getStandards());
    }

    private static ExecClassArgumentMatching format(PageEl _page, ContextEl _exec, StringList _clNames) {
        StringList className_ = new StringList();
        for (String s: _clNames) {
            className_.add(_page.formatVarType(s,_exec));
        }
        return new ExecClassArgumentMatching(className_);
    }

    public static ExecClassArgumentMatching toPrimitive(ExecClassArgumentMatching _class, LgNames _stds) {
        for (String w: _class.getNames()) {
            for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
                if (StringList.quickEq(e.getValue().getWrapper(), w)) {
                    return new ExecClassArgumentMatching(e.getKey(),e.getValue().getCastNb());
                }
            }
        }
        return _class;
    }

    public static boolean isPrimitive(ExecClassArgumentMatching _clMatchLeft, LgNames stds_) {
        for (String n: _clMatchLeft.getNames()) {
            if (PrimitiveTypeUtil.isPrimitive(n, stds_)) {
                return true;
            }
        }
        return false;
    }

    public static byte getPrimitiveWrapCast(String _className, LgNames stds_) {
        return ClassArgumentMatching.getPrimitiveCast(new StringList(toPrimitive(_className,stds_)),stds_);
    }

    public static String toPrimitive(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getValue().getWrapper(), _class)) {
                return e.getKey();
            }
        }
        return _class;
    }

    public static boolean isPrimitive(String _className, ContextEl _context) {
        return PrimitiveTypeUtil.isPrimitive(_className, _context.getStandards());
    }

    public static Struct defaultValue(String _class, ContextEl _context) {
        return PrimitiveTypeUtil.defaultValue(_class, _context.getStandards());
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

    public String getName() {
        return className.first();
    }

    public StringList getNames() {
        return className;
    }

    public void setUnwrapObject(String _unwrapObject,LgNames _stds) {
        unwrapObjectNb = (byte)-1;
        for (EntryCust<String,PrimitiveType> p: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(p.getKey(),_unwrapObject)) {
                unwrapObjectNb = p.getValue().getCastNb();
            }
        }
    }

    public byte getUnwrapObjectNb() {
        return unwrapObjectNb;
    }

    public void setUnwrapObjectNb(byte unwrapObjectNb) {
        this.unwrapObjectNb = unwrapObjectNb;
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

}
