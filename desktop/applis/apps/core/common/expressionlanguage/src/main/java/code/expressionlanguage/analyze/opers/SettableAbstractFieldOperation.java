package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.CaseCondition;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
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
    private int valueOffset = -1;
    private int fieldNameLength;

    private boolean catString;

    private int anc;
    private int indexBlock;
    private int rootNumber = -1;
    private boolean declare;

    public SettableAbstractFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
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
        anc = r_.getAnc();
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        rootNumber = r_.getRootNumber();
        e_ = r_.getId();
        valueOffset = e_.getValOffset();
        fieldMetaInfo = e_;
        String c_ = fieldMetaInfo.getType();
        setResultClass(new AnaClassArgumentMatching(c_, _page.getStandards()));
        if (isIntermediateDottedOperation() && !fieldMetaInfo.isStaticField()) {
            Argument arg_ = getPreviousArgument();
            checkNull(arg_, _page);
        }
    }

    abstract AnaClassArgumentMatching getFrom(AnalyzedPageEl _page);
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
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
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
        if (fieldMetaInfo == null) {
            return true;
        }
        ClassField clField_ = fieldMetaInfo.getClassField();
        String gl_ = _page.getGlobalClass();
        String id_ = StringExpUtil.getIdFromAllTypes(gl_);
        return !StringList.quickEq(clField_.getClassName(), id_);
    }
    public final FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    @Override
    public final void tryCalculateNode(AnalyzedPageEl _page) {
        trySet(this, fieldMetaInfo, _page);
    }
    private static void trySet(OperationNode _oper, FieldInfo _info, AnalyzedPageEl _page) {
        if (_info == null) {
            return;
        }
        if (!_info.isStaticField()) {
            return;
        }
        if (!_info.isFinalField()) {
            return;
        }
        Classes cl_ = _page.getClasses();
        ClassField fieldId_ = _info.getClassField();
        StringMap<Struct> map_ = cl_.getStaticFieldMap(fieldId_.getClassName());
        Struct str_ = cl_.getStaticField(fieldId_);
        if (map_.isEmpty()) {
            LgNames stds_ = _page.getStandards();
            ResultErrorStd res_ = stds_.getSimpleResult(fieldId_);
            Argument arg_ = new Argument(res_.getResult());
            _oper.setSimpleArgumentAna(arg_, _page);
            trySetDotParent(_oper, arg_, _page);
            return;
        }
        if (ElUtil.isDeclaringField(_oper, _page)) {
            Argument arg_ = Argument.createVoid();
            _oper.setSimpleArgument(arg_);
            return;
        }
        if (str_ != null) {
            Argument arg_ = new Argument(str_);
            _oper.setSimpleArgumentAna(arg_, _page);
            trySetDotParent(_oper, arg_, _page);
        }
    }
    private static void trySetDotParent(OperationNode _oper, Argument _arg, AnalyzedPageEl _page) {
        Block bl_ = _page.getCurrentBlock();
        if (!(bl_ instanceof CaseCondition)) {
            return;
        }
        if (_oper.getIndexChild() > 0
                && _oper.getParent() instanceof AbstractDotOperation) {
            _oper.getParent().setSimpleArgumentAna(_arg, _page);
        }
    }

    public boolean isDeclare() {
        return declare;
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
