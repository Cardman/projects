package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.core.StringUtil;

public final class ArrayFieldOperation extends AbstractFieldOperation {

    public ArrayFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        String originalStr_ = getValue();
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        AnaClassArgumentMatching cl_ = getPreviousResultClass();
        String aliasLength_ = _page.getAliasLength();
        if (StringUtil.quickEq(str_, aliasLength_)) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP));
            return;
        }
        FoundErrorInterpret und_ = new FoundErrorInterpret();
        und_.setFile(_page.getCurrentFile());
        und_.setIndexFile(_page);
        //str_ len
        und_.buildError(_page.getAnalysisMessages().getUndefinedAccessibleField(),
                str_,
                StringUtil.join(cl_.getNames(), ExportCst.JOIN_TYPES));
        _page.getLocalizer().addError(und_);
        addErr(und_.getBuiltError());
        setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP));
    }

}
