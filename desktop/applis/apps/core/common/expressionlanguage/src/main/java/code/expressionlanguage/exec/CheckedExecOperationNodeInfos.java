package code.expressionlanguage.exec;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.opers.ExecSettableFieldInstOperation;
import code.expressionlanguage.exec.opers.ExecSettableFieldOperation;
import code.expressionlanguage.exec.opers.ExecSettableFieldStatOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.FieldWrapper;
import code.expressionlanguage.exec.variables.InstanceFieldWrapper;
import code.expressionlanguage.exec.variables.StaticFieldWrapper;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class CheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final boolean staticField;
    private final int nbType;
    private final ClassField idClass;
    private final int modeField;
    private final ExecTypeReturn fieldType;

    public CheckedExecOperationNodeInfos(ExecSettableFieldOperation _s, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, _r);
        this.staticField = _s instanceof ExecSettableFieldStatOperation;
        this.nbType = numberType(_s);
        this.idClass = _s.getSettableFieldContent().getClassField();
        this.modeField = _mode;
        if (_s instanceof ExecSettableFieldInstOperation ){
            fieldType = ((ExecSettableFieldInstOperation)_s).getPair();
        } else{
            fieldType = new ExecTypeReturn(_s.owner(), _s.getSettableFieldContent().getRealType());
        }
    }

    public CheckedExecOperationNodeInfos(FieldWrapper _s, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, _r);
        this.staticField = _s instanceof StaticFieldWrapper;
        this.nbType = numberType(_s);
        this.idClass = _s.getId();
        this.modeField = _mode;
        if (_s instanceof InstanceFieldWrapper) {
            fieldType = ((InstanceFieldWrapper)_s).getPair();
        } else {
            fieldType = new ExecTypeReturn(_s.owner(), _s.getFieldType());
        }
    }

    public CheckedExecOperationNodeInfos(FieldMetaInfo _s, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, _r);
        this.staticField = _s.isStaticField();
        this.nbType = ExecRootBlock.numberType(_s.getFormatted().getRootBlock());
        this.idClass = new ClassField(StringExpUtil.getIdFromAllTypes(_s.getFormatted().getFormatted()), _s.getName());
        this.modeField = _mode;
        this.fieldType = new ExecTypeReturn(_s.getDeclaring(), _s.getType());
    }

    public ExecTypeReturn getFieldType() {
        return fieldType;
    }

    private static int numberType(ExecSettableFieldOperation _set) {
        return ExecRootBlock.numberType(_set.owner());
    }

    private static int numberType(FieldWrapper _w) {
        return ExecRootBlock.numberType(_w.owner());
    }
    public boolean isStaticField() {
        return staticField;
    }

    public int getNbType() {
        return nbType;
    }

    public ClassField getIdClass() {
        return idClass;
    }

    public int getModeField() {
        return modeField;
    }

}
