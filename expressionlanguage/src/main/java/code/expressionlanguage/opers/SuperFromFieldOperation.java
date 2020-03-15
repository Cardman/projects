package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
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
    ClassArgumentMatching getFrom(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        LgNames stds_ = _conf.getStandards();
        String className_ = originalStr_.substring(0,originalStr_.lastIndexOf(PAR_RIGHT));
        int lenPref_ = className_.indexOf(PAR_LEFT)+1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringList.getFirstPrintableCharIndex(className_);
        className_ = _conf.resolveCorrectType(lenPref_+loc_,className_);
        partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
        ClassArgumentMatching clCur_;
        if (!isIntermediateDottedOperation()) {
            clCur_ = new ClassArgumentMatching(_conf.getGlobalClass());
        } else {
            clCur_ = getPreviousResultClass();
        }
        Mapping map_ = new Mapping();
        map_.setParam(className_);
        map_.setArg(clCur_);
        StringMap<StringList> mapping_ = _conf.getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!Templates.isCorrectOrNumbers(map_, _conf)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(map_);
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            cast_.setFileName(_conf.getCurrentFileName());
            _conf.addError(cast_);
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
