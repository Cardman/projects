package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ForwardOperation extends LeafOperation implements PossibleIntermediateDotted {

    private AnaClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private String classType = EMPTY_STRING;
    private boolean staticChoiceMethod;
    private boolean accessSuperTypes = true;
    private boolean accessFromSuper;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private int length;
    ForwardOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        if (isIntermediateDottedOperation()) {
            setResultClass(new AnaClassArgumentMatching(previousResultClass.getNames()));
        } else {
            String arg_ = _page.getGlobalClass();
            setResultClass(new AnaClassArgumentMatching(arg_));
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String trimMeth_ = originalStr_.trim();
        String kw_;
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            length = keyWordSuper_.length();
            kw_ = keyWordSuper_;
            staticChoiceMethod = true;
            accessFromSuper = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            length = keyWordThat_.length();
            kw_ = keyWordThat_;
            staticChoiceMethod = true;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            length = keyWordThisaccess_.length();
            kw_ = keyWordThisaccess_;
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
            classType = ResolvingImportTypes.resolveCorrectType(lenPref_+loc_,className_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            Mapping map_ = new Mapping();
            map_.setParam(classType);
            map_.setArg(getResultClass());
            StringMap<StringList> mapping_ = _page.getCurrentConstraints().getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                //type len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(getResultClass().getNames(),"&"),
                        classType);
                _page.getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
            accessSuperTypes = false;
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordClasschoice_)) {
            length = keyWordClasschoice_.length();
            kw_ = keyWordClasschoice_;
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
            classType = ResolvingImportTypes.resolveCorrectType(lenPref_+loc_,className_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            setResultClass(new AnaClassArgumentMatching(classType));
            accessSuperTypes = false;
            staticChoiceMethod = true;
        } else {
            length = keyWords_.getKeyWordSuperaccess().length();
            kw_ = keyWords_.getKeyWordSuperaccess();
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
            className_ = ResolvingImportTypes.resolveCorrectType(lenPref_+loc_,className_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            classType = className_;
            Mapping map_ = new Mapping();
            map_.setParam(classType);
            map_.setArg(getResultClass());
            StringMap<StringList> mapping_ = _page.getCurrentConstraints().getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                //type len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(getResultClass().getNames(),"&"),
                        classType);
                _page.getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
            staticChoiceMethod = true;
        }
        if (!isIntermediateDottedOperation()) {
            if (_page.getStaticContext() != MethodAccessKind.INSTANCE) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //kw_ len
                static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                        kw_);
                _page.getLocalizer().addError(static_);
                getErrs().add(static_.getBuiltError());
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
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _static) {
        previousResultClass = _previousResultClass;
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

    public int getLength() {
        return length;
    }
}
