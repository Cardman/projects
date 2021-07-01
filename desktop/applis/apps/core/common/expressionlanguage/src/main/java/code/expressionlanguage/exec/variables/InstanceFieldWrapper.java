package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.structs.Struct;

public final class InstanceFieldWrapper extends FieldWrapper {
    private final int anc;
    private final Struct container;
    private final String className;
    public InstanceFieldWrapper(int _anc,Struct _container, String _className, String _fieldType, ExecRootBlock _rootBlock, ClassField _id) {
        super(_fieldType, _rootBlock, _id);
        anc = _anc;
        container = _container;
        className = _className;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecTemplates.setSafeInstanceField(anc,new Argument(container), _right, _conf, _stack, getId(), new ExecTypeReturn(getRootBlock(), getFieldType()));
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecTemplates.getSafeInstanceField(anc,new Argument(container),  _conf, _stack, getId()).getStruct();
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return ExecTemplates.formatType(_conf,getRootBlock(),getFieldType(),className);
    }
}
