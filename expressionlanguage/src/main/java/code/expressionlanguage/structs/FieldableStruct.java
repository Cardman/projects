package code.expressionlanguage.structs;

import code.expressionlanguage.opers.util.ClassField;
import code.util.EntryCust;
import code.util.ObjectMap;


public interface FieldableStruct extends Struct {

    EntryCust<ClassField,Struct> getEntryStruct(ClassField _classField);

    ObjectMap<ClassField,Struct> getFields();

}
