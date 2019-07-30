package code.formathtml.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
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

    public StdStruct(Rate _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public StdStruct(LgInt _instance, String _className) {
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
