package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.Struct;
import code.util.ObjectMap;

public interface Initializer {

    Struct processInit(ContextEl _context, Struct _parent, String _className, String _fieldName, int _ordinal);
    ObjectMap<ClassField, Struct> feedFields(ContextEl _context, String _className);
    Struct processInitAnnot(ContextEl _context, String _className);

    void loopCalling(ContextEl _owner);
}
