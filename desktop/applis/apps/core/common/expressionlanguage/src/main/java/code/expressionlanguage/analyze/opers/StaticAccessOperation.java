package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;

public final class StaticAccessOperation extends LeafOperation {
    private CustList<PartOffset> partOffsets;
    public StaticAccessOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        String ext_ = op_.getExtractType();
        ext_ = StringExpUtil.removeDottedSpaces(ext_);
        if (!ext_.isEmpty()) {
            partOffsets = op_.getPartOffsets();
            Argument a_ = new Argument();
            setSimpleArgument(a_);
            setResultClass(new AnaClassArgumentMatching(ext_));
            return;
        }
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        String glClass_ = _page.getGlobalClass();
        String classStr_;
        if (!realCl_.trim().isEmpty()) {
            classStr_ = ResolvingImportTypes.resolveAccessibleIdType(str_.indexOf(PAR_LEFT)+1,realCl_, _page);
            partOffsets = new CustList<PartOffset>(_page.getCurrentParts());
        } else {
            classStr_ = glClass_;
            partOffsets = new CustList<PartOffset>();
        }
        classStr_ = emptyToObject(classStr_, _page);
        checkClassAccess(this, glClass_, classStr_, _page);
        Argument a_ = new Argument();
        setSimpleArgument(a_);
        setResultClass(new AnaClassArgumentMatching(classStr_));
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
