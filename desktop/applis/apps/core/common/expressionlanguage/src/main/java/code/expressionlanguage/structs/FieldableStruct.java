package code.expressionlanguage.structs;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.util.CustList;


public interface FieldableStruct extends Struct {

    ClassFieldStruct getEntryStruct(ClassField _classField);

    CustList<ClassFieldStruct> getFields();

}
