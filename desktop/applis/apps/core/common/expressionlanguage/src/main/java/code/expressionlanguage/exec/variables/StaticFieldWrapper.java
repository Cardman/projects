package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class StaticFieldWrapper extends FieldWrapper {
    public StaticFieldWrapper(String _fieldType, ExecRootBlock _rootBlock, ClassField _id) {
        super(_fieldType, _rootBlock, _id);
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecTemplates.setStaticField(_conf.getExiting(),getRootBlock(), getFieldType(), _right, _conf, _stack, getId());
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {

        return ExecTemplates.getStaticField(_conf.getExiting(),getRootBlock(), getFieldType(), _conf, _stack, getId()).getStruct();
    }
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return getFieldType();
    }
}
