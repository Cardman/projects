package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class SuperFromFieldOperation extends
        SettableAbstractFieldOperation {

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public SuperFromFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        String className_ = originalStr_.substring(0,originalStr_.lastIndexOf(PAR_RIGHT));
        int lenPref_ = className_.indexOf(PAR_LEFT)+1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringList.getFirstPrintableCharIndex(className_);
        className_ = ResolvingImportTypes.resolveCorrectType(_conf,lenPref_+loc_,className_);
        partOffsets.addAllElts(page_.getCurrentParts());
        ClassArgumentMatching clCur_;
        if (!isIntermediateDottedOperation()) {
            clCur_ = new ClassArgumentMatching(page_.getGlobalClass());
        } else {
            clCur_ = getPreviousResultClass();
        }
        Mapping map_ = new Mapping();
        map_.setParam(className_);
        map_.setArg(clCur_);
        StringMap<StringList> mapping_ = page_.getCurrentConstraints().getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            cast_.setFileName(page_.getLocalizer().getCurrentFileName());
            //type len
            cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(clCur_.getNames(),"&"),
                    className_);
            page_.getLocalizer().addError(cast_);
            getErrs().add(cast_.getBuiltError());
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return null;
        }
        return new ClassArgumentMatching(className_);
    }

    @Override
    String getFieldName() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        StringList classMethod_ = StringList.splitChars(str_, PAR_RIGHT);
        return classMethod_.last().trim();
    }

    @Override
    public int getDelta() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        int ind_ = originalStr_.lastIndexOf(PAR_RIGHT);
        return ind_ +1+ StringList.getFirstPrintableCharIndex(originalStr_.substring(ind_+1));
    }
    @Override
    boolean isBaseAccess() {
        return true;
    }

    @Override
    boolean isSuperAccess() {
        return true;
    }

    @Override
    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
