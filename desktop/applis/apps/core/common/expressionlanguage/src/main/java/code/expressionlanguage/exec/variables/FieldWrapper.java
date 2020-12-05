package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultSetOffset;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class FieldWrapper implements AbstractWrapper {
    private final Struct container;
    private final String fieldType;
    private final ExecRootBlock rootBlock;
    private final boolean staticField;
    private final boolean finalField;
    private final ClassField id;
    public FieldWrapper(Struct _container,String _fieldType,ExecRootBlock _rootBlock,
                        boolean _staticField,boolean _finalField,ClassField _id) {
        container = _container;
        fieldType = _fieldType;
        rootBlock = _rootBlock;
        staticField = _staticField;
        finalField = _finalField;
        id = _id;
    }
    public void setValue(ContextEl _conf, Argument _right) {
        String className_ = id.getClassName();
        String fieldName_ = id.getFieldName();
        ExecTemplates.setField(new DefaultSetOffset(_conf), _conf.getExiting(),rootBlock,className_,fieldName_,staticField,finalField,false,fieldType,new Argument(container),_right,_conf,-1);
    }

    public Struct getValue(ContextEl _conf) {
        String className_ = id.getClassName();
        String fieldName_ = id.getFieldName();
        return ExecTemplates.getField(new DefaultSetOffset(_conf), _conf.getExiting(),className_,fieldName_,staticField,fieldType,new Argument(container),_conf,-1).getStruct();
    }

}
