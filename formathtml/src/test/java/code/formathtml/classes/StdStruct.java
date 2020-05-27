package code.formathtml.classes;

import code.bean.RealInstanceStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleIterable;

public final class StdStruct extends WithoutParentIdStruct implements RealInstanceStruct {

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
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return instance;
    }

}
