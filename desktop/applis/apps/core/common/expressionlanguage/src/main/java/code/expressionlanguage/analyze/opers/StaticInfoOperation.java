package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class StaticInfoOperation extends LeafOperation {

    private String className;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public StaticInfoOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int offset_ = StringUtil.getFirstPrintableCharIndex(originalStr_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset_, _page);
        int afterLeftPar_ = str_.indexOf(PAR_LEFT) + 1;
        String realCl_ = str_.substring(afterLeftPar_, str_.lastIndexOf(PAR_RIGHT));
        if (StringUtil.quickEq(realCl_.trim(), _page.getAliasVoid())) {
            className = realCl_.trim();
            setResultClass(new AnaClassArgumentMatching(_page.getAliasClassType()));
            return;
        }
        int off_ = StringUtil.getFirstPrintableCharIndex(realCl_);
        String classStr_;
        classStr_ = ResolvingTypes.resolveCorrectType(afterLeftPar_ + off_, realCl_, realCl_.contains(Templates.TEMPLATE_BEGIN), _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        className = classStr_;
        setResultClass(new AnaClassArgumentMatching(_page.getAliasClassType()));
    }

    public String getClassName() {
        return className;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
