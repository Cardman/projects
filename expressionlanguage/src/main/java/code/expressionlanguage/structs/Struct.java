package code.expressionlanguage.structs;
import code.expressionlanguage.ExecutableCode;

public interface Struct {

    Struct getParent();

    String getClassName(ExecutableCode _contextEl);

    boolean sameReference(Struct _other);
}
