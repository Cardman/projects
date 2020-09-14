package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;

public final class StaticCallAccessOperation extends LeafOperation {
    private CustList<PartOffset> partOffsets;
    private boolean implicit;
    public StaticCallAccessOperation(int _indexInEl, int _indexChild,
                                 MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        String classStr_;
        if (!realCl_.trim().isEmpty()) {
            classStr_ = ResolvingImportTypes.resolveCorrectType(_conf,str_.indexOf(PAR_LEFT)+1,realCl_);
            partOffsets = new CustList<PartOffset>(_conf.getAnalyzing().getCurrentParts());
        } else {
            implicit = true;
            classStr_ = glClass_;
            partOffsets = new CustList<PartOffset>();
        }
        if (classStr_.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //type len
            badAccess_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    classStr_);
            _conf.getAnalyzing().getLocalizer().addError(badAccess_);
            getErrs().add(badAccess_.getBuiltError());
        }
        if (classStr_.startsWith(AnaTemplates.ARR_BEG_STRING)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //type len
            badAccess_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    classStr_);
            _conf.getAnalyzing().getLocalizer().addError(badAccess_);
            getErrs().add(badAccess_.getBuiltError());
        }
        boolean ok_ = true;
        for (String p: StringExpUtil.getAllTypes(classStr_).mid(1)) {
            if (p.startsWith(Templates.SUB_TYPE)) {
                ok_ = false;
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                ok_ = false;
            }
        }
        if (!ok_) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //type len
            badAccess_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    classStr_);
            _conf.getAnalyzing().getLocalizer().addError(badAccess_);
            getErrs().add(badAccess_.getBuiltError());
        }
        checkClassAccess(this,_conf, glClass_, classStr_);
        Argument a_ = new Argument();
        setSimpleArgument(a_);
        setResultClass(new ClassArgumentMatching(classStr_));
    }

    public boolean isImplicit() {
        return implicit;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
