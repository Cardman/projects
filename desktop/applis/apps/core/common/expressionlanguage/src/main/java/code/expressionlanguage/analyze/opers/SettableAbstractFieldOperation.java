package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;

import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public abstract class SettableAbstractFieldOperation extends
        AbstractFieldOperation implements SettableElResult {

    private AnaSettableOperationContent settableFieldContent;
    private MethodAccessKind staticAccess;
    private int valueOffset = -1;
    private int fieldNameLength;

    private int indexBlock;
    private int rootNumber = -1;
    private boolean declare;

    public SettableAbstractFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        settableFieldContent = new AnaSettableOperationContent();
    }
    @Override
    public final void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        if (this instanceof StandardFieldOperation&&ElUtil.isDeclaringField(this, _page)) {
            indexBlock = _page.getIndexBlock();
            _page.setIndexBlock(indexBlock+1);
            declare = true;
        }
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
            staticAccess = _page.getStaticContext();
        }
        LgNames stds_ = _page.getStandards();
        String fieldName_ = getFieldName();
        fieldNameLength = fieldName_.length();
        AnaClassArgumentMatching cl_ = getFrom(_page);
        if (cl_ == null) {
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        boolean baseAccess_ = isBaseAccess();
        boolean superAccess_ = isSuperAccess();
        boolean affect_ = false;
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
        } else if (getParent() instanceof CompoundAffectationOperation && getParent().getFirstChild() == this) {
            affect_ = true;
        } else if (getParent() instanceof SemiAffectationOperation) {
            affect_ = true;
        }
        FieldResult r_;
        FieldInfo e_;
        r_ = getDeclaredCustField(this, isStaticAccess(), cl_, baseAccess_, superAccess_, fieldName_, import_, affect_, _page);
        settableFieldContent.setAnc(r_.getAnc());
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        rootNumber = r_.getRootNumber();
        e_ = r_.getId();
        valueOffset = e_.getValOffset();
        settableFieldContent.setFieldMetaInfo(e_);
        String c_ = settableFieldContent.getFieldMetaInfo().getType();
        setResultClass(new AnaClassArgumentMatching(c_, _page.getStandards()));
    }

    abstract AnaClassArgumentMatching getFrom(AnalyzedPageEl _page);
    abstract String getFieldName();
    abstract boolean isBaseAccess();
    abstract boolean isSuperAccess();
    public abstract CustList<PartOffset> getPartOffsets();
    public abstract int getDelta();

    @Override
    public final void setCatenizeStrings() {
        settableFieldContent.setCatString(true);
    }

    @Override
    public final void setVariable(boolean _variable) {
        settableFieldContent.setVariable(_variable);
    }

    public final void setStaticAccess(MethodAccessKind _staticAccess) {
        staticAccess = _staticAccess;
    }

    public final MethodAccessKind isStaticAccess() {
        return staticAccess;
    }
    @Override
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        setPreviousResultClass(_previousResultClass);
        setStaticAccess(_staticAccess);
    }
    public final ClassField getFieldId() {
        if (settableFieldContent.getFieldMetaInfo() == null) {
            return null;
        }
        return settableFieldContent.getFieldMetaInfo().getClassField();
    }
    public final ClassField getFieldIdReadOnly() {
        ClassField fieldId_ = getFieldId();
        if (fieldId_ == null) {
            return new ClassField(EMPTY_STRING,EMPTY_STRING);
        }
        return fieldId_;
    }

    public final boolean isFromCurrentClassReadOnly(AnalyzedPageEl _page) {
        if (notMatchCurrentType(_page)) {
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
    private boolean notMatchCurrentType(AnalyzedPageEl _page) {
        if (settableFieldContent.getFieldMetaInfo() == null) {
            return true;
        }
        ClassField clField_ = settableFieldContent.getFieldMetaInfo().getClassField();
        String gl_ = _page.getGlobalClass();
        String id_ = StringExpUtil.getIdFromAllTypes(gl_);
        return !StringList.quickEq(clField_.getClassName(), id_);
    }
    public final FieldInfo getFieldMetaInfo() {
        return settableFieldContent.getFieldMetaInfo();
    }

    public boolean isDeclare() {
        return declare;
    }

    public AnaSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public int getFieldNameLength() {
        return fieldNameLength;
    }

    public int getIndexBlock() {
        return indexBlock;
    }

    public int getRootNumber() {
        return rootNumber;
    }
}
