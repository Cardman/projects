package code.expressionlanguage.structs;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;

public interface Struct extends CallingState {

    Struct getParent();

    String getClassName(ContextEl _contextEl);

    boolean sameReference(Struct _other);
}
