package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;

import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class SettableAbstractFieldOperation extends
        AbstractFieldOperation implements SettableElResult {

    private final AnaSettableOperationContent settableFieldContent;
    private MethodAccessKind staticAccess;
    private int valueOffset = -1;
    private int fieldNameLength;

    private int indexBlock;
    private MemberId memberId = new MemberId();
    private RootBlock fieldType;
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
        if (ElUtil.isDeclaringField(this, _page)) {
            indexBlock = _page.getIndexBlock();
            _page.setIndexBlock(indexBlock+1);
            declare = true;
        }
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
            staticAccess = _page.getStaticContext();
        }
        String fieldName_ = getFieldName();
        fieldNameLength = fieldName_.length();
        AnaClassArgumentMatching cl_ = getFrom(_page);
        if (cl_ == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
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
        FieldResult r_ = resolveDeclaredCustField(isStaticAccess() != MethodAccessKind.INSTANCE,
                cl_, baseAccess_, superAccess_, fieldName_, import_, affect_, _page);
        settableFieldContent.setAnc(r_.getAnc());
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            FoundErrorInterpret access_ = new FoundErrorInterpret();
            access_.setFileName(_page.getLocalizer().getCurrentFileName());
            access_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_name len
            access_.buildError(_page.getAnalysisMessages().getUndefinedAccessibleField(),
                    fieldName_,
                    StringUtil.join(cl_.getNames(), ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(access_);
            addErr(access_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        memberId = r_.getMemberId();
        fieldType = r_.getFieldType();
        valueOffset = r_.getValOffset();
        settableFieldContent.setFinalField(r_.isFinalField());
        settableFieldContent.setStaticField(r_.isStaticField());
        settableFieldContent.setClassField(r_.getClassField());
        settableFieldContent.setRealType(r_.getRealType());
        String c_ = r_.getType();
        setResultClass(new AnaClassArgumentMatching(c_, _page.getPrimitiveTypes()));
    }

    abstract AnaClassArgumentMatching getFrom(AnalyzedPageEl _page);
    abstract String getFieldName();
    abstract boolean isBaseAccess();
    abstract boolean isSuperAccess();
    public abstract CustList<PartOffset> getPartOffsets();
    public abstract int getDelta();

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
        return settableFieldContent.getClassField();
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
        return _page.getGlobalType() != fieldType;
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

    public RootBlock getFieldType() {
        return fieldType;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}
