package code.expressionlanguage.opers.util;


public interface FieldableStruct extends Struct {

    Struct getStruct(ClassField _classField);

    String getClassName();

    void setStruct(ClassField _classField, Struct _value);
}
