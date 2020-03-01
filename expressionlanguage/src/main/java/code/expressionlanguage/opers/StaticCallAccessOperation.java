package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadAccessClass;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class StaticCallAccessOperation extends LeafOperation {
    private CustList<PartOffset> partOffsets;
    private boolean implicit;
    public StaticCallAccessOperation(int _indexInEl, int _indexChild,
                                 MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        String glClass_ = _conf.getGlobalClass();
        String classStr_;
        if (!realCl_.trim().isEmpty()) {
            classStr_ = _conf.resolveCorrectType(str_.indexOf(PAR_LEFT)+1,realCl_);
            partOffsets = new CustList<PartOffset>(_conf.getContextEl().getCoverage().getCurrentParts());
        } else {
            implicit = true;
            classStr_ = glClass_;
            partOffsets = new CustList<PartOffset>();
        }
        if (classStr_.startsWith(Templates.PREFIX_VAR_TYPE)) {
            BadAccessClass badAccess_ = new BadAccessClass();
            badAccess_.setId(classStr_);
            badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(badAccess_);
        }
        if (classStr_.startsWith(Templates.ARR_BEG_STRING)) {
            BadAccessClass badAccess_ = new BadAccessClass();
            badAccess_.setId(classStr_);
            badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(badAccess_);
        }
        boolean ok_ = true;
        for (String p: Templates.getAllTypes(classStr_).mid(1)) {
            if (p.startsWith(Templates.SUB_TYPE)) {
                ok_ = false;
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                ok_ = false;
            }
        }
        if (!ok_) {
            BadAccessClass badAccess_ = new BadAccessClass();
            badAccess_.setId(classStr_);
            badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(badAccess_);
        }
        checkClassAccess(_conf, glClass_, classStr_);
        Argument a_ = new Argument();
        setSimpleArgument(a_);
        setStaticResultClass(new ClassArgumentMatching(classStr_));
    }

    public boolean isImplicit() {
        return implicit;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
