package code.expressionlanguage.structs;
import code.expressionlanguage.ContextEl;

public interface Struct {

    Struct getParent();

    String getClassName(ContextEl _contextEl);

    boolean sameReference(Struct _other);

    long randCode();
}
