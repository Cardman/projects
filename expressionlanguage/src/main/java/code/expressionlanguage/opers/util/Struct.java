package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;

public abstract class Struct {

    public abstract boolean isNull();

    public abstract Boolean isJavaObject();

    public abstract String getClassName(ContextEl _contextEl);

    public String getRealClassName(ContextEl _contextEl) {
        return getClassName(_contextEl);
    }

    public abstract Object getInstance();

    public abstract ObjectMap<ClassField,Struct> getFields();
}
