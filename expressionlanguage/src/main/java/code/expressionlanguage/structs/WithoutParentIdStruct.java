package code.expressionlanguage.structs;

public abstract class WithoutParentIdStruct extends WithoutParentStruct {

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
