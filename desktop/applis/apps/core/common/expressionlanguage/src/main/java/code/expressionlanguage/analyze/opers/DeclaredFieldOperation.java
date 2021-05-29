package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class DeclaredFieldOperation extends
        SettableAbstractFieldOperation {

    private RootBlock declaring;
    public DeclaredFieldOperation(int _indexInEl, int _indexChild,
                                  MethodOperation _m, OperationsSequence _op, RootBlock _declaring) {
        super(_indexInEl, _indexChild, _m, _op);
        declaring = _declaring;
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        setIndexBlock(_page.getIndexBlock());
        _page.setIndexBlock(getIndexBlock() +1);
        setStaticAccess(_page.getStaticContext());
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String fieldName_ = originalStr_.trim();
        setFieldNameLength(fieldName_.length());
        FieldResult r_ = getDeclaringCustFieldByContext(declaring,_page.getStaticContext(), fieldName_, _page);
        getSettableFieldContent().setAnc(r_.getAnc());
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            FoundErrorInterpret access_ = new FoundErrorInterpret();
            access_.setFileName(_page.getLocalizer().getCurrentFileName());
            access_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_name len
            access_.buildError(_page.getAnalysisMessages().getUndefinedAccessibleField(),
                    fieldName_,
                    _page.getGlobalClass());
            _page.getLocalizer().addError(access_);
            addErr(access_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setMemberId(r_.getMemberId());
        setFieldType(r_.getFieldType());
        setValueOffset(r_.getValOffset());
        getSettableFieldContent().setFinalField(r_.isFinalField());
        getSettableFieldContent().setStaticField(r_.isStaticField());
        getSettableFieldContent().setClassField(r_.getClassField());
        getSettableFieldContent().setRealType(r_.getRealType());
        String c_ = r_.getType();
        setResultClass(new AnaClassArgumentMatching(c_, _page.getPrimitiveTypes()));
    }

    @Override
    public int getDelta() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        return StringUtil.getFirstPrintableCharIndex(originalStr_);
    }

    @Override
    public CustList<PartOffset> getPartOffsets() {
        return new CustList<PartOffset>();
    }
}
