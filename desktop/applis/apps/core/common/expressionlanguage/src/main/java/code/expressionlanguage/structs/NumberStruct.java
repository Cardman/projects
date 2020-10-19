package code.expressionlanguage.structs;

public abstract class NumberStruct extends WithoutParentStruct implements DisplayableStruct,AnaDisplayableStruct {

    public abstract double doubleStruct();
    public abstract float floatStruct();
    public abstract long longStruct();
    public abstract int intStruct();
    public abstract short shortStruct();
    public abstract byte byteStruct();
}
