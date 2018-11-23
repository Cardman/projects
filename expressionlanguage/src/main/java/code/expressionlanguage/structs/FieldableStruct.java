package code.expressionlanguage.structs;

import code.expressionlanguage.opers.util.ClassField;


public interface FieldableStruct extends Struct {

    Struct getStruct(ClassField _classField);

    String getClassName();

    void setStruct(ClassField _classField, Struct _value);
}
