package code.expressionlanguage.exec.variables;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public abstract class FieldWrapper implements AbstractWrapper {
    private final String fieldType;
    private final ExecRootBlock rootBlock;
    private final ClassField id;
    protected FieldWrapper(String _fieldType, ExecRootBlock _rootBlock,
                           ClassField _id) {
        fieldType = _fieldType;
        rootBlock = _rootBlock;
        id = _id;
    }

    protected String getFieldType() {
        return fieldType;
    }

    protected ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    protected ClassField getId() {
        return id;
    }
}
