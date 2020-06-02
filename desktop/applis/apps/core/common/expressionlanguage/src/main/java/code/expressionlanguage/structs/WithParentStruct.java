package code.expressionlanguage.structs;

public interface WithParentStruct extends FieldableStruct {
    String getParentClassName();
    void setParent(Struct _par);
}
