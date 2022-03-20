package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.structs.Struct;

public final class InstanceFieldWrapper extends FieldWrapper {
    private final int anc;
    private final Struct container;
    private final String className;
    private final ExecTypeReturn pair;

    public InstanceFieldWrapper(int _anc, Struct _container, String _className, String _fieldType, ClassField _id, ExecTypeReturn _pair) {
        super(_fieldType, _id);
        anc = _anc;
        container = _container;
        className = _className;
        pair = _pair;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecTemplates.setSafeInstanceField(anc,new Argument(container), _right, _conf, _stack, getId(), pair);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecTemplates.getSafeInstanceField(anc,new Argument(container),  _conf, _stack, getId()).getStruct();
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return ExecTemplates.formatType(_conf,pair.getRootBlock(),getFieldType(),className);
    }
}
