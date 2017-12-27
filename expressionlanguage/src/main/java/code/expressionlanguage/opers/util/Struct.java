package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;

public interface Struct {

    boolean isNull();

    String getClassName(ContextEl _contextEl);

    boolean sameReference(Struct _other);

    Object getInstance();

    ObjectMap<ClassField,Struct> getFields();
}
