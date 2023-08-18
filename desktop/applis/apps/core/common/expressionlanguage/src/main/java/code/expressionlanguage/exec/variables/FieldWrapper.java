package code.expressionlanguage.exec.variables;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.structs.Struct;

public abstract class FieldWrapper extends ValueWrapper {
    private final String fieldType;
    private final ClassField id;
    protected FieldWrapper(Struct _v, String _fieldType,
                           ClassField _id) {
        super(_v);
        fieldType = _fieldType;
        id = _id;
    }

    public String getFieldType() {
        return fieldType;
    }

    public abstract ExecRootBlock owner();
    public ClassField getId() {
        return id;
    }
}
