package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.structs.Struct;

public final class InstanceFieldWrapper extends FieldWrapper {
    private final String className;
    private final ExecTypeReturn pair;
    private final Struct parent;

    public InstanceFieldWrapper(Struct _v,Struct _p, String _className, String _fieldType, ClassField _id, ExecTypeReturn _pair) {
        super(_v,_fieldType, _id);
        parent = _p;
        className = _className;
        pair = _pair;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Struct _right) {
        setValue(_right);
        ExecFieldTemplates.setSafeInstanceField(_right, _conf, _stack, getId(), pair, parent);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecFieldTemplates.getSafeInstanceField(_conf, _stack, getId(), parent);
    }

    @Override
    public String getClassName(ContextEl _conf) {
        return ExecFieldTemplates.formatType(_conf,pair.getRootBlock(),getFieldType(),className);
    }

    @Override
    public ExecRootBlock owner() {
        return pair.getRootBlock();
    }

    public ExecTypeReturn getPair() {
        return pair;
    }

    public Struct getParent() {
        return parent;
    }
}
