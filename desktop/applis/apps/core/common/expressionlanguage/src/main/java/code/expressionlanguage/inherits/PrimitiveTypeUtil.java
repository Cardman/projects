package code.expressionlanguage.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class PrimitiveTypeUtil {
    public static final String ARR_CLASS = "[";

    private PrimitiveTypeUtil() {
    }


    public static boolean isPrimitive(String _className, ContextEl _context) {
        return isPrimitive(_className, _context.getStandards());
    }

    public static boolean isPrimitive(String _className, LgNames _stds) {
        return _stds.getPrimitiveTypes().contains(_className);
    }


    public static NumberStruct convertToNumber(ClassArgumentMatching _match, Struct _obj, LgNames _context) {
        if (_obj instanceof NumberStruct) {
            return convertObject(_match, (NumberStruct)_obj,_context);
        }
        return convertObject(_match,new ByteStruct((byte)0),_context);
    }

    public static Struct convertObject(ClassArgumentMatching _match, Struct _obj, LgNames _context) {
        if (_obj instanceof NumberStruct) {
            return convertObject(_match, (NumberStruct)_obj, _context);
        }
        return _obj;
    }

    private static Struct convertStrictObject(ClassArgumentMatching _match, Struct _obj, LgNames _context) {
        if (_obj instanceof NumberStruct) {
            return convertStrictObject(_match, (NumberStruct)_obj, _context);
        }
        return _obj;
    }
    private static NumberStruct convertObject(ClassArgumentMatching _match, NumberStruct _obj, LgNames _stds) {
        if (_match.matchClass(_stds.getAliasPrimDouble()) || _match.matchClass(_stds.getAliasDouble())) {
            return new DoubleStruct(_obj.doubleStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimFloat()) || _match.matchClass(_stds.getAliasFloat())) {
            return new FloatStruct(_obj.floatStruct());
        }
        return convertIntNb(_match, _obj, _stds);
    }

    private static NumberStruct convertStrictObject(ClassArgumentMatching _match, NumberStruct _obj, LgNames _stds) {
        if (isFloatType(_obj)) {
            return convertToFloat(_match, _obj, _stds);
        }
        return convertToInt(_match, _obj, _stds);
    }

    public static NumberStruct convertToInt(ClassArgumentMatching _match, NumberStruct _obj, LgNames _stds) {
        return convertIntNb(_match, _obj, _stds);
    }

    public static NumberStruct convertToFloat(ClassArgumentMatching _match, NumberStruct _obj, LgNames _stds) {
        if (isInternFloat(_match,_stds)) {
            return new FloatStruct(_obj.floatStruct());
        }
        return new DoubleStruct(_obj.doubleStruct());
    }

    private static NumberStruct convertIntNb(ClassArgumentMatching _match, NumberStruct _obj, LgNames _stds) {
        if (_match.matchClass(_stds.getAliasPrimLong()) || _match.matchClass(_stds.getAliasLong())) {
            return new LongStruct(_obj.longStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimInteger()) || _match.matchClass(_stds.getAliasInteger())) {
            return new IntStruct(_obj.intStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimShort()) || _match.matchClass(_stds.getAliasShort())) {
            return new ShortStruct(_obj.shortStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimByte()) || _match.matchClass(_stds.getAliasByte())) {
            return new ByteStruct(_obj.byteStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimChar()) || _match.matchClass(_stds.getAliasCharacter())) {
            return new CharStruct((char) _obj.intStruct());
        }
        return _obj;
    }

    private static boolean isFloatType(Struct _obj) {
        return _obj instanceof FloatStruct ||_obj instanceof DoubleStruct;
    }

    public static Struct unwrapObject(String _match, Struct _obj, LgNames _stds) {
        return convertStrictObject(new ClassArgumentMatching(_match), _obj, _stds);
    }


    public static boolean isLessInt(ClassArgumentMatching _class, LgNames standards) {
        ClassArgumentMatching class_ = toPrimitive(_class, standards);
        return class_.matchClass(standards.getAliasPrimShort())
                || class_.matchClass(standards.getAliasPrimByte())
                || class_.matchClass(standards.getAliasPrimChar());
    }

    public static boolean isByte(ClassArgumentMatching _class, LgNames standards) {
        ClassArgumentMatching class_ = toPrimitive(_class, standards);
        return class_.matchClass(standards.getAliasPrimByte());
    }

    public static boolean isShort(ClassArgumentMatching _class, LgNames standards) {
        ClassArgumentMatching class_ = toPrimitive(_class, standards);
        return class_.matchClass(standards.getAliasPrimShort());
    }

    public static boolean isChar(ClassArgumentMatching _class, LgNames standards) {
        ClassArgumentMatching class_ = toPrimitive(_class, standards);
        return class_.matchClass(standards.getAliasPrimChar());
    }

    public static boolean isIntOrLess(ClassArgumentMatching _class, LgNames standards) {
        return isInt(_class, standards) || isLessInt(_class, standards);
    }

    public static boolean isInt(ClassArgumentMatching _class, LgNames standards) {
        ClassArgumentMatching class_ = toPrimitive(_class, standards);
        return class_.matchClass(standards.getAliasPrimInteger());
    }

    public static boolean isLong(ClassArgumentMatching _class, LgNames standards) {
        ClassArgumentMatching class_ = toPrimitive(_class, standards);
        return class_.matchClass(standards.getAliasPrimLong());
    }

    public static boolean isFloat(ClassArgumentMatching _class, LgNames standards) {
        LgNames standards_ = standards;
        return isInternFloat(_class, standards_);
    }

    public static boolean isInternFloat(ClassArgumentMatching _class, LgNames standards_) {
        ClassArgumentMatching class_ = toPrimitive(_class, standards_);
        return class_.matchClass(standards_.getAliasPrimFloat());
    }

    public static boolean isPureNumberClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching out_ = toPrimitive(_class, _stds);
        if (out_.matchClass(_stds.getAliasPrimDouble())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimFloat())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimLong())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimInteger())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimChar())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimShort())) {
            return true;
        }
        return out_.matchClass(_stds.getAliasPrimByte());
    }

    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, LgNames _stds) {
        for (String w: _class.getNames()) {
            for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
                if (StringList.quickEq(e.getValue().getWrapper(), w)) {
                    return new ClassArgumentMatching(e.getKey());
                }
            }
        }
        return _class;
    }

    public static String toWrapper(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getKey(), _class)) {
                return e.getValue().getWrapper();
            }
        }
        return _class;
    }
    public static Struct defaultClass(String _element, ContextEl _context) {
        if (isPrimitive(_element, _context)) {
            return defaultValue(_element, _context);
        }
        return NullStruct.NULL_VALUE;
    }

    public static Struct defaultValue(String _class, ContextEl _context) {
        return defaultValue(_class, _context.getStandards());
    }

    public static Struct defaultValue(String _class, LgNames _stds) {
        if (isPrimitive(_class, _stds)) {
            if (StringList.quickEq(_class, _stds.getAliasPrimBoolean())) {
                return BooleanStruct.of(false);
            }
            return convert(_class, _stds);
        }
        return NullStruct.NULL_VALUE;
    }
    private static Struct convert(String _toClass, LgNames _stds) {
        ClassArgumentMatching class_ = new ClassArgumentMatching(_toClass);
        ClassArgumentMatching prim_ = toPrimitive(class_, _stds);
        if (prim_.matchClass(_stds.getAliasPrimDouble())) {
            return new DoubleStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimFloat())) {
            return new FloatStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimLong())) {
            return new LongStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimInteger())) {
            return new IntStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimChar())) {
            return new CharStruct((char)0);
        }
        if (prim_.matchClass(_stds.getAliasPrimShort())) {
            return new ShortStruct((short)0);
        }
        return new ByteStruct((byte)0);
    }

    public static boolean isPrimitive(ClassArgumentMatching _clMatchLeft, LgNames stds_) {
        for (String n: _clMatchLeft.getNames()) {
            if (isPrimitive(n, stds_)) {
                return true;
            }
        }
        return false;
    }
}
