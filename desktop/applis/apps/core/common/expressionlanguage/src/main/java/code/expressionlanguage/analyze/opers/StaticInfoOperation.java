package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.util.core.StringUtil;

public final class StaticInfoOperation extends LeafOperation {

    private String className;

    private AnaResultPartType partOffsets = new AnaResultPartType();

    public StaticInfoOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        String originalStr_ = getValue();
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
        partOffsets = ResolvingTypes.resolveCorrectType(afterLeftPar_ + off_, realCl_, realCl_.contains(StringExpUtil.TEMPLATE_BEGIN), _page);
        classStr_ = partOffsets.getResult(_page);
        className = classStr_;
        setResultClass(new AnaClassArgumentMatching(_page.getAliasClassType()));
    }

    public String getClassName() {
        return className;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }
}
