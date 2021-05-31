package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;

import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.util.CustList;

public abstract class SettableAbstractFieldOperation extends
        AbstractFieldOperation implements SettableElResult {

    private final AnaSettableOperationContent settableFieldContent;
    private MethodAccessKind staticAccess;
    private int valueOffset = -1;
    private int fieldNameLength;

    private RootBlock fieldType;

    protected SettableAbstractFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        settableFieldContent = new AnaSettableOperationContent();
    }
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

    public AnaSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public void setValueOffset(int _valueOffset) {
        this.valueOffset = _valueOffset;
    }

    public int getFieldNameLength() {
        return fieldNameLength;
    }

    public void setFieldNameLength(int _fieldNameLength) {
        this.fieldNameLength = _fieldNameLength;
    }

    public RootBlock getFieldType() {
        return fieldType;
    }

    public void setFieldType(RootBlock _fieldType) {
        this.fieldType = _fieldType;
    }

}
