package code.expressionlanguage.exec.variables;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.structs.Struct;

public abstract class FieldWrapper implements AbstractWrapper {
    private final Struct container;
    private final String fieldType;
    private final ExecRootBlock rootBlock;
    private final ClassField id;
    public FieldWrapper(Struct _container, String _fieldType, ExecRootBlock _rootBlock,
                        ClassField _id) {
        container = _container;
        fieldType = _fieldType;
        rootBlock = _rootBlock;
        id = _id;
    }

    protected Struct getContainer() {
        return container;
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
