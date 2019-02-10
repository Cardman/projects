package code.expressionlanguage.structs;

public interface ErroneousStruct extends DisplayableStruct {
    ArrayStruct getStack();

    Struct getMessage();
}
