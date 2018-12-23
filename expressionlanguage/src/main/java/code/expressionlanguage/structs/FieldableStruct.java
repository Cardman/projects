package code.expressionlanguage.structs;

import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;


public interface FieldableStruct extends Struct {

    Struct getStruct(ClassField _classField);

    String getClassName();

    ObjectMap<ClassField,Struct> getFields();

    void setStruct(ClassField _classField, Struct _value);
}
