package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultSetOffset;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class FieldWrapper implements AbstractWrapper {
    private Struct container;
    private String fieldType;
    private ExecRootBlock rootBlock;
    private boolean staticField;
    private boolean finalField;
    private ClassField id;
    private Struct value;
    public void setValue(ContextEl _conf, Argument _right) {
        String className_ = id.getClassName();
        String fieldName_ = id.getFieldName();
        ExecTemplates.setField(new DefaultSetOffset(_conf), _conf.getExiting(),rootBlock,className_,fieldName_,staticField,finalField,false,fieldType,new Argument(container),_right,_conf,-1);
        value = _right.getStruct();
    }

    public Struct getValue() {
        return value;
    }

    public void setContainer(Struct _container) {
        container = _container;
    }

    public void setFieldType(String _fieldType) {
        fieldType = _fieldType;
    }

    public void setRootBlock(ExecRootBlock _rootBlock) {
        rootBlock = _rootBlock;
    }

    public void setStaticField(boolean _staticField) {
        staticField = _staticField;
    }

    public void setFinalField(boolean _finalField) {
        finalField = _finalField;
    }

    public void setId(ClassField _id) {
        id = _id;
    }

    public void setValue(Struct _value) {
        value = _value;
    }

}
