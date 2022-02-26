package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.core.StringUtil;

public final class SettableFieldOperation extends
        SettableAbstractFieldOperation {

    private AnaSettableAbstractFieldOperation interf;

    public SettableFieldOperation(int _indexInEl, int _indexChild,
                                  MethodOperation _m, OperationsSequence _op, AnaSettableAbstractFieldOperation _interf) {
        super(_indexInEl, _indexChild, _m, _op);
        interf = _interf;
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
            setStaticAccess(_page.getStaticContext());
        }
        String fieldName_ = interf.getFieldName();
        setFieldNameLength(fieldName_.length());
        AnaClassArgumentMatching cl_ = interf.getFrom(_page,this);
        if (cl_ == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        boolean baseAccess_ = interf.isBaseAccess();
        boolean superAccess_ = interf.isSuperAccess();
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
        ScopeFilter scope_ = new ScopeFilter(null, baseAccess_, superAccess_, false, _page.getGlobalClass());
        FieldResult r_ = resolveDeclaredCustField(isStaticAccess() != MethodAccessKind.INSTANCE, cl_, fieldName_, import_, affect_, _page, scope_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            FoundErrorInterpret access_ = new FoundErrorInterpret();
            access_.setFile(_page.getCurrentFile());
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
        setFieldType(r_.getFieldType());
        setValueOffset(r_.getValOffset());
        getSettableFieldContent().setAnc(r_.getContent().getAnc());
        getSettableFieldContent().setFinalField(r_.getContent().isFinalField());
        getSettableFieldContent().setStaticField(r_.getContent().isStaticField());
        getSettableFieldContent().setClassField(r_.getContent().getClassField());
        getSettableFieldContent().setRealType(r_.getContent().getRealType());
        String c_ = r_.getType();
        setResultClass(new AnaClassArgumentMatching(c_, _page.getPrimitiveTypes()));
    }

    public AnaResultPartType getPartOffsets() {
        return interf.getPartOffsets();
    }

    @Override
    public int getDelta() {
        return interf.getDelta();
    }

    public String getFieldName() {
        return interf.getFieldName();
    }
}
