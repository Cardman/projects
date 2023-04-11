package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.common.AnaGeneType;

public final class StaticAccessOperation extends LeafOperation {
    private AnaResultPartTypeDtoInt partOffsets;
    private AnaGeneType extractStaticType;
    private final String extractType;

    public StaticAccessOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        extractType = _op.getExtractType();
        if (!extractType.isEmpty()) {
            extractStaticType = _op.getExtractStaticType();
            partOffsets = _op.getPartOffsets();
        }
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        if (!extractType.isEmpty()) {
            Argument a_ = new Argument();
            setSimpleArgument(a_);
            setResultClass(new AnaClassArgumentMatching(extractType));
            return;
        }
        int relativeOff_ = getOffset();
        String originalStr_ = getValue();
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        String glClass_ = _page.getGlobalClass();
        String classStr_;
        if (!realCl_.trim().isEmpty()) {
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(str_.indexOf(PAR_LEFT) + 1, realCl_, _page);
            classStr_ = resolvedIdType_.getFullName();
            partOffsets = resolvedIdType_.getDels();
            extractStaticType = resolvedIdType_.getGeneType();
        } else {
            classStr_ = glClass_;
            partOffsets = new AnaResultPartTypeDirectDto();
            extractStaticType = _page.getGlobalType().getRootBlock();
        }
        classStr_ = emptyToObject(classStr_, _page);
        checkClassAccess(glClass_, classStr_, _page);
        Argument a_ = new Argument();
        setSimpleArgument(a_);
        setResultClass(new AnaClassArgumentMatching(classStr_));
    }

    public AnaGeneType getExtractStaticType() {
        return extractStaticType;
    }

    public AnaResultPartTypeDtoInt getPartOffsets() {
        return partOffsets;
    }
}
