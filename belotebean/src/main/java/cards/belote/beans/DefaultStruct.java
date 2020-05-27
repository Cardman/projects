package cards.belote.beans;

import code.expressionlanguage.ContextEl;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.util.Longs;
import code.util.StringList;
import code.util.ints.SimpleIterable;

public final class DefaultStruct extends WithoutParentIdStruct implements RealInstanceStruct {

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

    public static DefaultStruct newInstance(Object _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newListLong(Longs _instance, String _className) {
        return new DefaultStruct((Object)_instance, _className);
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
