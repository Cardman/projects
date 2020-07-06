package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.opers.ReductibleOperable;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class SettableAbstractFieldOperation extends
        AbstractFieldOperation implements SettableElResult, ReductibleOperable {

    private boolean variable;
    private FieldInfo fieldMetaInfo;
    private MethodAccessKind staticAccess;
    private String fieldType = EMPTY_STRING;
    private int valueOffset;
    private int fieldNameLength;

    private boolean catString;

    private int anc;

    public SettableAbstractFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
            staticAccess = _conf.getAnalyzing().getStaticContext();
        }
        LgNames stds_ = _conf.getStandards();
        String fieldName_ = getFieldName();
        fieldNameLength = fieldName_.length();
        ClassArgumentMatching cl_ = getFrom(_conf);
        if (cl_ == null) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        boolean baseAccess_ = isBaseAccess();
        boolean superAccess_ = isSuperAccess();
        boolean affect_ = false;
        int indexCh_ = 0;
        if (getParent() instanceof DeclaringOperation) {
            indexCh_ = getIndexChild();
        }
        if (getParent() instanceof AbstractDotOperation && isIntermediateDottedOperation()) {
            if (getParent().getParent() instanceof AffectationOperation && getParent().getParent().getFirstChild() == getParent()) {
                affect_ = true;
            } else if (getParent().getParent() instanceof CompoundAffectationOperation && getParent().getParent().getFirstChild() == getParent()) {
                affect_ = true;
            } else if (getParent().getParent() instanceof SemiAffectationOperation) {
                affect_ = true;
            }
        } else if (getParent() instanceof AffectationOperation && getParent().getFirstChild() == this) {
            affect_ = true;
            indexCh_ = getParent().getIndexChild();
        } else if (getParent() instanceof CompoundAffectationOperation && getParent().getFirstChild() == this) {
            affect_ = true;
        } else if (getParent() instanceof SemiAffectationOperation) {
            affect_ = true;
        }
        FieldResult r_;
        FieldInfo e_;
        r_ = getDeclaredCustField(this,_conf, isStaticAccess(), cl_, baseAccess_, superAccess_, fieldName_, import_, affect_);
        anc = r_.getAnc();
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        e_ = r_.getId();
        if (e_.getValueOffset().isValidIndex(indexCh_)) {
            valueOffset = e_.getValueOffset().get(indexCh_);
        }
        fieldType = e_.getType();
        fieldMetaInfo = e_;
        String c_ = fieldMetaInfo.getType();
        setResultClass(new ClassArgumentMatching(c_));
        if (isIntermediateDottedOperation() && !fieldMetaInfo.isStaticField()) {
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
        }
    }

    abstract ClassArgumentMatching getFrom(ContextEl _an);
    abstract String getFieldName();
    abstract boolean isBaseAccess();
    abstract boolean isSuperAccess();
    public abstract CustList<PartOffset> getPartOffsets();
    public abstract int getDelta();

    @Override
    public final void setCatenizeStrings() {
        catString = true;
    }

    @Override
    public final void setVariable(boolean _variable) {
        variable = _variable;
    }

    public final void setStaticAccess(MethodAccessKind _staticAccess) {
        staticAccess = _staticAccess;
    }

    public final MethodAccessKind isStaticAccess() {
        return staticAccess;
    }
    @Override
    public void setPreviousResultClass(ClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        setPreviousResultClass(_previousResultClass);
        setStaticAccess(_staticAccess);
    }
    public final ClassField getFieldId() {
        if (fieldMetaInfo == null) {
            return null;
        }
        return fieldMetaInfo.getClassField();
    }
    public final ClassField getFieldIdReadOnly() {
        if (fieldMetaInfo == null) {
            return new ClassField(EMPTY_STRING,EMPTY_STRING);
        }
        return fieldMetaInfo.getClassField();
    }

    public final boolean isFromCurrentClassReadOnly(ContextEl _an) {
        if (notMatchCurrentType(_an)) {
            return false;
        }
        if (isFirstChildInParent()) {
            return true;
        }
        MethodOperation par_ = getParent();
        if (par_.getFirstChild() instanceof ThisOperation) {
            return true;
        }
        if (par_.getFirstChild() instanceof StaticAccessOperation) {
            return true;
        }
        if (par_.getFirstChild() instanceof AbstractDotOperation) {
            OperationNode op_ = ((AbstractDotOperation)par_.getFirstChild()).getChildrenNodes().last();
            return op_ instanceof ThisOperation;
        }
        return false;
    }
    private boolean notMatchCurrentType(ContextEl _an) {
        if (fieldMetaInfo == null) {
            return true;
        }
        ClassField clField_ = fieldMetaInfo.getClassField();
        String gl_ = _an.getAnalyzing().getGlobalClass();
        String id_ = StringExpUtil.getIdFromAllTypes(gl_);
        return !StringList.quickEq(clField_.getClassName(), id_);
    }
    public final FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    @Override
    public final void tryCalculateNode(ContextEl _conf) {
        trySet(_conf, this, fieldMetaInfo);
    }
    public static void trySet(ContextEl _conf, OperationNode _oper, FieldInfo _info) {
        if (_info == null) {
            return;
        }
        if (!_info.isStaticField()) {
            return;
        }
        if (!_info.isFinalField()) {
            return;
        }
        Classes cl_ = _conf.getClasses();
        ClassField fieldId_ = _info.getClassField();
        StringMap<Struct> map_ = cl_.getStaticFieldMap(fieldId_.getClassName());
        Struct str_ = cl_.getStaticField(fieldId_);
        if (map_.isEmpty()) {
            ResultErrorStd res_ = _conf.getStandards().getSimpleResult(_conf, fieldId_);
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(res_.getResult());
            _oper.setSimpleArgumentAna(arg_,_conf);
            return;
        }
        if (ElUtil.isDeclaringField(_oper, _conf)) {
            Argument arg_ = Argument.createVoid();
            _oper.setSimpleArgument(arg_);
            return;
        }
        if (str_ != null) {
            Argument arg_ = Argument.createVoid();
            arg_.setStruct(str_);
            _oper.setSimpleArgumentAna(arg_,_conf);
        }
    }
    public boolean isVariable() {
        return variable;
    }
    public boolean isCatString() {
        return catString;
    }
    public int getAnc() {
        return anc;
    }

    public String getFieldType() {
        return fieldType;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public int getFieldNameLength() {
        return fieldNameLength;
    }
}
