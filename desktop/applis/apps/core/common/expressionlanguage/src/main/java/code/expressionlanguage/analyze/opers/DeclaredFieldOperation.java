package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
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
        AnaFormattedRootBlock f_ = new AnaFormattedRootBlock(declaring);
        String id_ = StringExpUtil.getIdFromAllTypes(f_.getFormatted());
        FieldInfo fi_ = ContextUtil.getFieldInfo(f_.getRootBlock(), id_, fieldName_);
        getSettableFieldContent().setAnc(0);
        if (fi_ == null) {
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
        setMemberId(fi_.getMemberId());
        setFieldType(fi_.getFieldType());
        setValueOffset(fi_.getValOffset());
        getSettableFieldContent().setFinalField(fi_.isFinalField());
        getSettableFieldContent().setStaticField(fi_.isStaticField());
        getSettableFieldContent().setClassField(fi_.getClassField());
        getSettableFieldContent().setRealType(fi_.getType());
        String c_ = fi_.getType();
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
