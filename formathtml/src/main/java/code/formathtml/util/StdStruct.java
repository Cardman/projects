package code.formathtml.util;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.FloatStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleIterable;

public final class StdStruct implements RealInstanceStruct {

    private final Object instance;

    private final String className;
    
    private StdStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public StdStruct(StringList _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public StdStruct(Displayable _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public StdStruct(SimpleIterable _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public StdStruct(SimpleEntries _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static StdStruct newInstance(Object _instance, String _className) {
        return new StdStruct(_instance, _className);
    }
    public static StdStruct newListLong(Longs _instance, String _className) {
        return new StdStruct((Object)_instance, _className);
    }
    public static StdStruct newListInt(Ints _instance, String _className) {
        return new StdStruct((Object)_instance, _className);
    }
    public static StdStruct newListByte(Bytes _instance, String _className) {
        return new StdStruct((Object)_instance, _className);
    }
    public static Struct defaultClass(String _element, Analyzable _context) {
        if (PrimitiveTypeUtil.isPrimitive(_element, _context)) {
            return PrimitiveTypeUtil.defaultValue(_element, _context);
        }
        return NullStruct.NULL_VALUE;
    }

    public static Struct wrapStd(Object _element, ContextEl _context) {
        BeanLgNames lgNames_ = (BeanLgNames) _context.getStandards();
        String aliasObject_ = lgNames_.getAliasObject();
        return wrapStd(_element, _context, aliasObject_);
    }

    public static Struct wrapStd(Object _element, ContextEl _context, String _alias) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        if (_element instanceof Byte) {
            return new ByteStruct((Byte) _element);
        }
        if (_element instanceof Short) {
            return new ShortStruct((Short) _element);
        }
        if (_element instanceof Character) {
            return new CharStruct((Character) _element);
        }
        if (_element instanceof Integer) {
            return new IntStruct((Integer) _element);
        }
        if (_element instanceof Long) {
            return new LongStruct((Long) _element);
        }
        if (_element instanceof Float) {
            return new FloatStruct((Float) _element);
        }
        if (_element instanceof Double) {
            return new DoubleStruct((Double) _element);
        }
        if (_element instanceof Boolean) {
            return new BooleanStruct((Boolean) _element);
        }
        if (_element instanceof String) {
            return new StringStruct((String) _element);
        }
        if (_element instanceof StringBuilder) {
            return new StringBuilderStruct((StringBuilder) _element);
        }
        BeanLgNames lgNames_ = (BeanLgNames) _context.getStandards();
        String className_ = lgNames_.getOtherStructClassName(_element, _context);
        if (StringList.quickEq(className_, lgNames_.getAliasObject())) {
            return StdStruct.newInstance(_element, _alias);
        }
        return StdStruct.newInstance(_element, className_);
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof StdStruct)) {
            return false;
        }
        StdStruct other_ = (StdStruct) _other;
        return getInstance() == other_.getInstance();
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }
    public String getClassName() {
        return className;
    }

    @Override
    public Object getInstance() {
        return instance;
    }

}
