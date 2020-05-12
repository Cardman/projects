package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ForwardOperation extends LeafOperation implements PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private String classType = EMPTY_STRING;
    private boolean staticChoiceMethod;
    private boolean accessSuperTypes = true;
    private boolean accessFromSuper;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    ForwardOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        if (isIntermediateDottedOperation()) {
            setResultClass(new ClassArgumentMatching(previousResultClass.getNames()));
        } else {
            String arg_ = _conf.getAnalyzing().getGlobalClass();
            setResultClass(new ClassArgumentMatching(arg_));
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String trimMeth_ = originalStr_.trim();
        String kw_;
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            kw_ = keyWordSuper_;
            staticChoiceMethod = true;
            accessFromSuper = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            kw_ = keyWordThat_;
            staticChoiceMethod = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            kw_ = keyWordThisaccess_;
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringList.getFirstPrintableCharIndex(className_);
            classType = ResolvingImportTypes.resolveCorrectType(_conf,lenPref_+loc_,className_);
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            Mapping map_ = new Mapping();
            map_.setParam(classType);
            map_.setArg(getResultClass());
            StringMap<StringList> mapping_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!Templates.isCorrectOrNumbers(map_, _conf)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                cast_.setFileName(_conf.getCurrentFileName());
                //type len
                cast_.buildError(_conf.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(getResultClass().getNames(),"&"),
                        classType);
                _conf.addError(cast_);
            }
            accessSuperTypes = false;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordClasschoice_)) {
            kw_ = keyWordClasschoice_;
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringList.getFirstPrintableCharIndex(className_);
            classType = ResolvingImportTypes.resolveCorrectType(_conf,lenPref_+loc_,className_);
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            setResultClass(new ClassArgumentMatching(classType));
            accessSuperTypes = false;
            staticChoiceMethod = true;
        } else {
            kw_ = keyWords_.getKeyWordSuperaccess();
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringList.getFirstPrintableCharIndex(className_);
            className_ = ResolvingImportTypes.resolveCorrectType(_conf,lenPref_+loc_,className_);
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            classType = className_;
            Mapping map_ = new Mapping();
            map_.setParam(classType);
            map_.setArg(getResultClass());
            StringMap<StringList> mapping_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!Templates.isCorrectOrNumbers(map_, _conf)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                cast_.setFileName(_conf.getCurrentFileName());
                //type len
                cast_.buildError(_conf.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(getResultClass().getNames(),"&"),
                        classType);
                _conf.addError(cast_);
            }
            staticChoiceMethod = true;
        }
        if (!isIntermediateDottedOperation()) {
            if (_conf.getAnalyzing().getStaticContext() != MethodAccessKind.INSTANCE) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setIndexFile(_conf.getCurrentLocationIndex());
                //kw_ len
                static_.buildError(_conf.getContextEl().getAnalysisMessages().getStaticAccess(),
                        kw_);
                _conf.addError(static_);
            }
        }
    }

    @Override
    public void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public void setPreviousResultClass(ClassArgumentMatching _previousResultClass, MethodAccessKind _static) {
        previousResultClass = _previousResultClass;
    }

    @Override
    public void setPreviousArgument(Argument _argument) {

    }

    public boolean isIntermediate() {
        return intermediate;
    }


    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public boolean isAccessFromSuper() {
        return accessFromSuper;
    }

    public boolean isAccessSuperTypes() {
        return accessSuperTypes;
    }

    public String getClassType() {
        return classType;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
