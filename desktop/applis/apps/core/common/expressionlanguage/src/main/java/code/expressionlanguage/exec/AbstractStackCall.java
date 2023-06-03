package code.expressionlanguage.exec;

import code.expressionlanguage.exec.variables.LocalVariable;

public interface AbstractStackCall {
    String formatVarType(String _varType);
    StackCall stack();
    void putVar(String _key, LocalVariable _wrapper);
    int sizeElLast();
}
