package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.opers.ExecAnnotationMethodOperation;
import code.expressionlanguage.exec.opers.ExecSettableFieldInstOperation;
import code.expressionlanguage.exec.opers.ExecSettableFieldOperation;
import code.expressionlanguage.exec.opers.ExecSettableFieldStatOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.FieldWrapper;
import code.expressionlanguage.exec.variables.InstanceFieldWrapper;
import code.expressionlanguage.exec.variables.StaticFieldWrapper;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class FieldCheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final boolean staticField;
    private final boolean checkFinalField;
    private final boolean trueField;
    private final int nbType;
    private final ClassField idClass;
    private final int modeField;
    private final ExecTypeReturn fieldType;

    public FieldCheckedExecOperationNodeInfos(ContextEl _ctx, ExecSettableFieldOperation _s, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, ArrCheckedExecOperationNodeInfos.cacheRightValue(_ctx,_r));
        this.staticField = _s instanceof ExecSettableFieldStatOperation;
        this.checkFinalField = false;
        this.nbType = numberType(_s);
        this.idClass = _s.getSettableFieldContent().getClassField();
        this.modeField = _mode;
        if (_s instanceof ExecSettableFieldInstOperation ){
            fieldType = ((ExecSettableFieldInstOperation)_s).getPair();
        } else{
            fieldType = new ExecTypeReturn(_s.owner(), _s.getSettableFieldContent().getRealType());
        }
        trueField = true;
    }

    public FieldCheckedExecOperationNodeInfos(ExecAnnotationMethodOperation _s, ContextEl _ctx, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        this(_s.getFieldName(),_ctx,_mode,_d,_i,_r);
    }

    public FieldCheckedExecOperationNodeInfos(String _s, ContextEl _ctx, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        this(_s,_ctx,_mode,_d,_i,_r,"");
    }
    public FieldCheckedExecOperationNodeInfos(String _s, ContextEl _ctx, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r, String _ip) {
        super(_d, _i, ArrCheckedExecOperationNodeInfos.cacheRightValue(_ctx,_r));
        this.staticField = false;
        this.checkFinalField = false;
        String cl_ = _i.getClassName(_ctx);
        ExecRootBlock root_ = _ctx.getClasses().getClassBody(cl_);
        this.nbType = ExecRootBlock.numberType(root_);
        this.idClass = new ClassField(cl_,_s);
        this.modeField = _mode;
        fieldType = new ExecTypeReturn(root_, _ip);
        trueField = false;
    }
    public FieldCheckedExecOperationNodeInfos(ContextEl _ctx, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r, ExecNamedFieldContent _content) {
        super(_d, _i, ArrCheckedExecOperationNodeInfos.cacheRightValue(_ctx,_r));
        this.staticField = false;
        this.checkFinalField = false;
        ExecRootBlock root_ = _content.getDeclaring();
        this.nbType = ExecRootBlock.numberType(root_);
        this.idClass = new ClassField(_content.getIdClass(),_content.getName());
        this.modeField = _mode;
        fieldType = new ExecTypeReturn(root_, _content.getType());
        trueField = true;
    }

    public FieldCheckedExecOperationNodeInfos(ContextEl _ctx, FieldWrapper _s, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, ArrCheckedExecOperationNodeInfos.cacheRightValue(_ctx,_r));
        this.staticField = _s instanceof StaticFieldWrapper;
        this.checkFinalField = false;
        this.nbType = numberType(_s);
        this.idClass = _s.getId();
        this.modeField = _mode;
        if (_s instanceof InstanceFieldWrapper) {
            fieldType = ((InstanceFieldWrapper)_s).getPair();
        } else {
            fieldType = new ExecTypeReturn(_s.owner(), _s.getFieldType());
        }
        trueField = true;
    }

    public FieldCheckedExecOperationNodeInfos(ContextEl _ctx, FieldMetaInfo _s, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, ArrCheckedExecOperationNodeInfos.cacheRightValue(_ctx,_r));
        this.staticField = _s.isStaticField();
        this.checkFinalField = _s.isFinalField();
        this.nbType = ExecRootBlock.numberType(_s.getFormatted().getRootBlock());
        this.idClass = new ClassField(StringExpUtil.getIdFromAllTypes(_s.getFormatted().getFormatted()), _s.getName());
        this.modeField = _mode;
        this.fieldType = new ExecTypeReturn(_s.getDeclaring(), _s.getType());
        trueField = true;
    }

    public boolean isCheckFinalField() {
        return checkFinalField;
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

    public boolean isTrueField() {
        return trueField;
    }
}
