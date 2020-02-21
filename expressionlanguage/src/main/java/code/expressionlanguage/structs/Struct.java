package code.expressionlanguage.structs;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CallingState;

public interface Struct extends CallingState {

    Struct getParent();

    String getClassName(ExecutableCode _contextEl);

    boolean sameReference(Struct _other);
}
