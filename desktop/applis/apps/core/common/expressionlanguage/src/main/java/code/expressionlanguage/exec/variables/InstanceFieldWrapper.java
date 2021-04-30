package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class InstanceFieldWrapper extends FieldWrapper {
    public InstanceFieldWrapper(Struct _container, String _fieldType, ExecRootBlock _rootBlock, ClassField _id) {
        super(_container, _fieldType, _rootBlock, _id);
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        String className_ = getId().getClassName();
        String fieldName_ = getId().getFieldName();
        ExecTemplates.setInstanceField(getRootBlock(), className_, fieldName_, getFieldType(), new Argument(getContainer()), _right, _conf, _stack);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        String className_ = getId().getClassName();
        String fieldName_ = getId().getFieldName();

        return ExecTemplates.getInstanceField(className_, fieldName_, new Argument(getContainer()),  _conf, _stack).getStruct();
    }
}
