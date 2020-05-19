package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public interface ErroneousStruct extends DisplayableStruct {
    ArrayStruct getStack();
    ArrayStruct getFullStack();
    Struct getCause();
    String getStringRep(ContextEl _an, Struct[] _array);
    Struct getMessage();
}
