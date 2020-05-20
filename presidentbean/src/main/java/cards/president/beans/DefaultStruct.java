package cards.president.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.RealInstanceStruct;
import code.util.Longs;
import code.util.StringList;
import code.util.ints.SimpleIterable;

public final class DefaultStruct implements RealInstanceStruct {

    private final Object instance;

    private final String className;

    private DefaultStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public DefaultStruct(StringList _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public DefaultStruct(SimpleIterable _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static DefaultStruct newInstance(Object _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newListLong(Longs _instance, String _className) {
        return new DefaultStruct((Object)_instance, _className);
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
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
