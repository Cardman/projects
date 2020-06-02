package code.expressionlanguage.structs;

public abstract class WithoutParentStruct implements Struct {

    @Override
    public final Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
}
