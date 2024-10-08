package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.structs.Struct;

public final class StaticFieldWrapper extends FieldWrapper {
    private final ExecRootBlock root;
    public StaticFieldWrapper(Struct _v,String _fieldType, ExecRootBlock _rootBlock, ClassField _id) {
        super(_v,_fieldType, _id);
        root = _rootBlock;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Struct _right) {
        setValue(_right);
        ExecFieldTemplates.setStaticField(getFieldType(), _right, _conf, _stack, getId());
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {

        return ExecFieldTemplates.getStaticField(getFieldType(), _conf, _stack, getId());
    }
    @Override
    public String getClassName(ContextEl _conf) {
        return getFieldType();
    }

    @Override
    public ExecRootBlock owner() {
        return getRoot();
    }

    public ExecRootBlock getRoot() {
        return root;
    }
}
