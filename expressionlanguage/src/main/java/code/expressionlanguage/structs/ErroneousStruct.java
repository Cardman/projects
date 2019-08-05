package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;

public interface ErroneousStruct extends DisplayableStruct {
    ArrayStruct getStack();
    ArrayStruct getFullStack();
    String getStringRep(Analyzable _an, Struct[] _array);
    Struct getMessage();
}
