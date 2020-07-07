package code.expressionlanguage.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AnaInheritedType;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.InheritedType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class PrimitiveTypeUtil {
    public static final String ARR_CLASS = "[";
    private static final byte DOUBLE_CASTING = 7;
    private static final byte FLOAT_CASTING = 6;
    private static final byte LONG_CASTING = 5;
    private static final byte INT_CASTING = 4;
    private static final byte CHAR_CASTING = 3;
    private static final byte SHORT_CASTING = 2;
    private static final byte BYTE_CASTING = 1;
    private PrimitiveTypeUtil() {
    }


    public static boolean isPrimitive(String _className, ContextEl _context) {
        return isPrimitive(_className, _context.getStandards());
    }
    public static boolean isWrapper(String _className, ContextEl _context) {
        return isWrapper(_className, _context.getStandards());
    }
    private static boolean isWrapper(String _className, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            String wrap_ = e.getValue().getWrapper();
            if (StringList.quickEq(wrap_, _className)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPrimitive(String _className, LgNames _stds) {
        return _stds.getPrimitiveTypes().contains(_className);
    }

    /** Only "object" classes are used as arguments */
    public static StringList getSubclasses(StringList _classNames, ContextEl _context) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = StringExpUtil.getIdFromAllTypes(i);
                String baseSub_ = StringExpUtil.getIdFromAllTypes(j);
                if (StringList.quickEq(baseSup_, baseSub_)) {
                    continue;
                }
                GeneType subType_ = _context.getClassBody(baseSub_);
                if (subType_.isSubTypeOf(baseSup_,_context)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        return types_;
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

    public static Struct convertStrictObject(ClassArgumentMatching _match, Struct _obj, LgNames _context) {
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
        if (isFloatOrderClass(_match,_stds)) {
            if (isFloatType(_obj)) {
                if (_match.matchClass(_stds.getAliasPrimFloat()) || _match.matchClass(_stds.getAliasFloat())) {
                    return new FloatStruct(_obj.floatStruct());
                }
                return new DoubleStruct(_obj.doubleStruct());
            }
            return _obj;
        }
        if (isFloatType(_obj)) {
            return _obj;
        }
        return convertIntNb(_match, _obj, _stds);
    }

    public static NumberStruct convertIntNb(ClassArgumentMatching _match, NumberStruct _obj, LgNames _stds) {
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

    private static boolean isFloatType(NumberStruct _obj) {
        return _obj instanceof FloatStruct ||_obj instanceof DoubleStruct;
    }

    public static Struct unwrapObject(String _match, Struct _obj, LgNames _stds) {
        return convertStrictObject(new ClassArgumentMatching(_match), _obj, _stds);
    }

    public static int cmpTypes(String _one, String _two, ContextEl _context) {
        AnaInheritedType one_ = _context.getAnalyzing().getAnaGeneType(_context,_one);
        AnaInheritedType two_ = _context.getAnalyzing().getAnaGeneType(_context,_two);
        if (two_.isSubTypeOf(_one,_context)) {
            return CustList.SWAP_SORT;
        }
        if (one_.isSubTypeOf(_two,_context)) {
            return CustList.NO_SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }


    public static boolean isLessInt(ClassArgumentMatching _class, ContextEl _context) {
        ClassArgumentMatching class_ = toPrimitive(_class, _context.getStandards());
        return class_.matchClass(_context.getStandards().getAliasPrimShort())
                || class_.matchClass(_context.getStandards().getAliasPrimByte())
                || class_.matchClass(_context.getStandards().getAliasPrimChar());
    }

    public static boolean isByte(ClassArgumentMatching _class, ContextEl _context) {
        ClassArgumentMatching class_ = toPrimitive(_class, _context.getStandards());
        return class_.matchClass(_context.getStandards().getAliasPrimByte());
    }

    public static boolean isShort(ClassArgumentMatching _class, ContextEl _context) {
        ClassArgumentMatching class_ = toPrimitive(_class, _context.getStandards());
        return class_.matchClass(_context.getStandards().getAliasPrimShort());
    }

    public static boolean isChar(ClassArgumentMatching _class, ContextEl _context) {
        ClassArgumentMatching class_ = toPrimitive(_class, _context.getStandards());
        return class_.matchClass(_context.getStandards().getAliasPrimChar());
    }

    public static boolean isIntOrLess(ClassArgumentMatching _class, ContextEl _context) {
        return isInt(_class,_context) || isLessInt(_class,_context);
    }

    public static boolean isInt(ClassArgumentMatching _class, ContextEl _context) {
        ClassArgumentMatching class_ = toPrimitive(_class, _context.getStandards());
        return class_.matchClass(_context.getStandards().getAliasPrimInteger());
    }

    public static boolean isLong(ClassArgumentMatching _class, ContextEl _context) {
        ClassArgumentMatching class_ = toPrimitive(_class, _context.getStandards());
        return class_.matchClass(_context.getStandards().getAliasPrimLong());
    }

    public static boolean isFloat(ClassArgumentMatching _class, ContextEl _context) {
        ClassArgumentMatching class_ = toPrimitive(_class, _context.getStandards());
        return class_.matchClass(_context.getStandards().getAliasPrimFloat());
    }

    public static boolean isFloatOrderClass(ClassArgumentMatching _class, ClassArgumentMatching _classTwo,ContextEl _context) {
        return isFloatOrderClass(_class, _context) && isFloatOrderClass(_classTwo, _context);
    }

    private static boolean isFloatOrderClass(ClassArgumentMatching _class, ContextEl _context) {
        return isFloatOrderClass(_class,_context.getStandards());
    }

    private static boolean isFloatOrderClass(ClassArgumentMatching _class, LgNames _context) {
        return getFloatOrderClass(_class,_context) > 0;
    }

    public static int getFloatOrderClass(ClassArgumentMatching _class, ContextEl _context) {
        return getFloatOrderClass(_class, _context.getStandards());
    }
    private static int getFloatOrderClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching class_ = toPrimitive(_class, _stds);
        if (class_.matchClass(_stds.getAliasPrimDouble())) {
            return DOUBLE_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimFloat())) {
            return FLOAT_CASTING;
        }
        return 0;
    }

    public static int getIntOrderClass(String _class, ContextEl _context) {
        return getIntOrderClass(_class, _context.getStandards());
    }
    private static int getIntOrderClass(String _class, LgNames _stds) {
        return getIntOrderClass(new ClassArgumentMatching(_class), _stds);
    }
    public static boolean isIntOrderClass(ClassArgumentMatching _class, ClassArgumentMatching _classTwo,ContextEl _context) {
        return isIntOrderClass(_class, _context) && isIntOrderClass(_classTwo, _context);
    }

    public static boolean isIntOrderClass(ClassArgumentMatching _class, ContextEl _context) {
        return getIntOrderClass(_class,_context) > 0;
    }

    public static int getIntOrderClass(ClassArgumentMatching _class, ContextEl _context) {
        return getIntOrderClass(_class, _context.getStandards());
    }
    private static int getIntOrderClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching class_ = toPrimitive(_class, _stds);
        if (class_.matchClass(_stds.getAliasPrimLong())) {
            return LONG_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimInteger())) {
            return INT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimChar())) {
            return CHAR_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimShort())) {
            return SHORT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimByte())) {
            return BYTE_CASTING;
        }
        return 0;
    }
    public static boolean isPrimitiveOrWrapper(ClassArgumentMatching _className, ContextEl _context) {
        for (String c: _className.getNames()) {
            if (isPrimitiveOrWrapper(c, _context.getStandards())) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPrimitiveOrWrapper(String _className, ContextEl _context) {
        return isPrimitiveOrWrapper(_className, _context.getStandards());
    }
    public static boolean isPrimitiveOrWrapper(String _className, LgNames _stds) {
        if (isPrimitive(_className, _stds)) {
            return true;
        }
        return isWrapper(_className, _stds);
    }
    public static boolean isPureNumberClass(ClassArgumentMatching _class, ContextEl _context) {
        return isPureNumberClass(_class, _context.getStandards());
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
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, ContextEl _context) {
        return toPrimitive(_class, _context.getStandards());
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
    public static String toPrimitive(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getValue().getWrapper(), _class)) {
                return e.getKey();
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

    private static Struct defaultValue(String _class, LgNames _stds) {
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

    public static boolean isPrimitive(ClassArgumentMatching _clMatchLeft,
            ContextEl _conf) {
        for (String n: _clMatchLeft.getNames()) {
            if (isPrimitive(n, _conf)) {
                return true;
            }
        }
        return false;
    }
}
