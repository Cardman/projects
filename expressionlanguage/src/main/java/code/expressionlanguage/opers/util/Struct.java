package code.expressionlanguage.opers.util;
import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;

public interface Struct {

    boolean isNull();

    boolean isArray();


    String getClassName(ExecutableCode _contextEl);

    boolean sameReference(Struct _other);

    Object getInstance();

    ObjectMap<ClassField,Struct> getFields();
}
