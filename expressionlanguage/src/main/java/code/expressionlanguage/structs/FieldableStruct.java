package code.expressionlanguage.structs;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassFieldStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;


public interface FieldableStruct extends Struct {

    ClassFieldStruct getEntryStruct(ClassField _classField);

    CustList<ClassFieldStruct> getFields();

}
