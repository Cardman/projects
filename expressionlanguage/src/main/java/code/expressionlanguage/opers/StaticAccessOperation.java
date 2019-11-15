package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class StaticAccessOperation extends LeafOperation {
    private CustList<PartOffset> partOffsets;
    public StaticAccessOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String ext_ = op_.getExtractType();
        ext_ = ContextEl.removeDottedSpaces(ext_);
        if (!ext_.isEmpty()) {
            partOffsets = op_.getPartOffsets();
            Argument a_ = new Argument();
            setSimpleArgument(a_);
            setStaticResultClass(new ClassArgumentMatching(ext_));
            return;
        }
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        String glClass_ = _conf.getGlobalClass();
        String classStr_;
        if (!realCl_.trim().isEmpty()) {
            classStr_ = _conf.resolveAccessibleIdType(str_.indexOf(PAR_LEFT)+1,realCl_);
            partOffsets = new CustList<PartOffset>(_conf.getContextEl().getCoverage().getCurrentParts());
        } else {
            classStr_ = glClass_;
            partOffsets = new CustList<PartOffset>();
        }
        checkClassAccess(_conf, glClass_, classStr_);
        Argument a_ = new Argument();
        setSimpleArgument(a_);
        setStaticResultClass(new ClassArgumentMatching(classStr_));
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
