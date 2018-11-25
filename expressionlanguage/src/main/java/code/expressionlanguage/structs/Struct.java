package code.expressionlanguage.structs;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;

public interface Struct {

    boolean isNull();

    boolean isArray();
    Struct getParent();


    String getClassName(ExecutableCode _contextEl);

    boolean sameReference(Struct _other);

    Object getInstance();

    ObjectMap<ClassField,Struct> getFields();
}
