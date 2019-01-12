package code.expressionlanguage.structs;

public interface ErroneousStruct extends DisplayableStruct {
    Struct getStack();
    Struct getMessage();
}
