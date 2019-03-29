package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.StaticAccessThisError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ForwardOperation extends VariableLeafOperation implements PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private int off;
    private String classType = EMPTY_STRING;
    private boolean staticChoiceMethod;
    private boolean accessSuperTypes = true;
    private boolean accessFromSuper;

    ForwardOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (isIntermediateDottedOperation()) {
            setResultClass(new ClassArgumentMatching(previousResultClass.getNames()));
        } else {
            String arg_ = _conf.getGlobalClass();
            if (_conf.isStaticContext()) {
                StaticAccessThisError static_ = new StaticAccessThisError();
                static_.setClassName(arg_);
                static_.setFileName(_conf.getCurrentFileName());
                static_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(static_);
            }
            setResultClass(new ClassArgumentMatching(arg_));
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String trimMeth_ = originalStr_.trim();
        if (ContextEl.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            staticChoiceMethod = true;
            accessFromSuper = true;
        } else if (ContextEl.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            staticChoiceMethod = true;
        } else if (ContextEl.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            classType = _conf.resolveCorrectType(className_);
            Mapping map_ = new Mapping();
            map_.setParam(classType);
            map_.setArg(getResultClass());
            StringMap<StringList> mapping_ = _conf.getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!Templates.isCorrectOrNumbers(map_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(map_);
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                cast_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(cast_);
            }
            accessSuperTypes = false;
        } else if (ContextEl.startsWithKeyWord(trimMeth_, keyWordClasschoice_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            classType = _conf.resolveCorrectType(className_);
            setResultClass(new ClassArgumentMatching(classType));
            accessSuperTypes = false;
            staticChoiceMethod = true;
        } else {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            className_ = _conf.resolveCorrectType(className_);
            classType = className_;
            Mapping map_ = new Mapping();
            map_.setParam(classType);
            map_.setArg(getResultClass());
            StringMap<StringList> mapping_ = _conf.getCurrentConstraints();
            map_.setMapping(mapping_);
            if (!Templates.isCorrectOrNumbers(map_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(map_);
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                cast_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(cast_);
            }
            staticChoiceMethod = true;
        }
    }

    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _static) {
        previousResultClass = _previousResultClass;
    }

    @Override
    public void setPreviousArgument(Argument _argument) {

    }

    public boolean isIntermediate() {
        return intermediate;
    }
    public int getOff() {
        return off;
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
}
