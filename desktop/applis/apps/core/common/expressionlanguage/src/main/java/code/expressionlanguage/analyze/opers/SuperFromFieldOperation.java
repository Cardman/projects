package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class SuperFromFieldOperation implements AnaSettableAbstractFieldOperation {
    private OperationsSequence operations;

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public SuperFromFieldOperation(OperationsSequence _op) {
        operations = _op;
    }

    public OperationsSequence getOperations() {
        return operations;
    }

    @Override
    public AnaClassArgumentMatching getFrom(AnalyzedPageEl _page,SettableAbstractFieldOperation _settable) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String className_ = originalStr_.substring(0,originalStr_.lastIndexOf(OperationNode.PAR_RIGHT));
        int lenPref_ = className_.indexOf(OperationNode.PAR_LEFT)+1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
        className_ = ResolvingTypes.resolveCorrectType(lenPref_+loc_,className_, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        AnaClassArgumentMatching clCur_;
        if (!_settable.isIntermediateDottedOperation()) {
            clCur_ = new AnaClassArgumentMatching(_page.getGlobalClass());
        } else {
            clCur_ = _settable.getPreviousResultClass();
        }
        Mapping map_ = new Mapping();
        map_.setParam(className_);
        map_.setArg(clCur_);
        StringMap<StringList> mapping_ = _page.getCurrentConstraints().getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            //type len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clCur_.getNames(), ExportCst.JOIN_TYPES),
                    className_);
            _page.getLocalizer().addError(cast_);
            _settable.addErr(cast_.getBuiltError());
            _settable.setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return null;
        }
        return new AnaClassArgumentMatching(className_);
    }

    @Override
    public String getFieldName() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        StringList classMethod_ = StringUtil.splitChars(str_, OperationNode.PAR_RIGHT);
        return classMethod_.last().trim();
    }

    @Override
    public int getDelta() {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        int ind_ = originalStr_.lastIndexOf(OperationNode.PAR_RIGHT);
        return ind_ +1+ StringUtil.getFirstPrintableCharIndex(originalStr_.substring(ind_+1));
    }
    @Override
    public boolean isBaseAccess() {
        return true;
    }

    @Override
    public boolean isSuperAccess() {
        return true;
    }

    @Override
    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
