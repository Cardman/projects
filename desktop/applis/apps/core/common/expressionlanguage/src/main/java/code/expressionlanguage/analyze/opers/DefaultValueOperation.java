package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.util.core.StringUtil;

public final class DefaultValueOperation extends LeafOperation  {

    private String className;

    private AnaResultPartType partOffsets = new AnaResultPartType();
    DefaultValueOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        String originalStr_ = getValue();
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        int afterLeftPar_ = str_.indexOf(PAR_LEFT) + 1;
        String realCl_ = str_.substring(afterLeftPar_, str_.lastIndexOf(PAR_RIGHT));
        int offLoc_ = StringUtil.getFirstPrintableCharIndex(realCl_);
        String classStr_;
        partOffsets = ResolvingTypes.resolveCorrectType(afterLeftPar_ + offLoc_, realCl_, _page);
        classStr_ = partOffsets.getResult(_page);
        className = classStr_;
        setResultClass(new AnaClassArgumentMatching(className, _page.getPrimitiveTypes()));
    }

    public String getClassName() {
        return className;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }
}
