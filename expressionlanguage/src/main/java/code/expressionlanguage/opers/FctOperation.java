package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.AbstractMethod;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UndefinedMethodError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.exec.PossibleIntermediateDottedOperable;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public final class FctOperation extends InvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    private int anc;

    private int delta;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public FctOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyze(Analyzable _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        boolean import_ = false;
        ClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            import_ = true;
            clCur_ = new ClassArgumentMatching(_conf.getGlobalClass());
            setStaticAccess(_conf.isStaticContext());
        }

        StringList l_ = clCur_.getNames();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setDelta(_conf);
        String trimMeth_ = methodName.trim();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodId idMethod_ = lookOnlyForId();
        if (hasVoidArguments(chidren_, firstArgs_, off_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }

        boolean staticChoiceMethod_ = false;
        boolean accessSuperTypes_ = true;
        boolean accessFromSuper_ = false;
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        if (ContextEl.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (ContextEl.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
            staticChoiceMethod_ = true;
        } else if (ContextEl.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            className_ = _conf.resolveCorrectType(lenPref_,className_);
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
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
                _conf.getClasses().addError(cast_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            trimMeth_ = trimMeth_.substring(trimMeth_.lastIndexOf(PAR_RIGHT) + 1).trim();
            l_ = getBounds(className_, _conf);
            accessSuperTypes_ = false;
        }
        ClassMethodId feed_ = null;
        if (idMethod_ != null) {
            String idClass_ = idMethod_.getClassName();
            MethodId mid_ = idMethod_.getConstraints();
            boolean vararg_ = mid_.isVararg();
            StringList params_ = mid_.getParametersTypes();
            boolean static_ = isStaticAccess() || mid_.isStaticMethod();
            feed_ = new ClassMethodId(idClass_, new MethodId(static_, trimMeth_, params_, vararg_));
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            if (hasVoidPrevious(c, _conf)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            bounds_.addAllElts(getBounds(c, _conf));
        }
        StringList arrayBounds_ = getArrayBounds(bounds_);
        if (!arrayBounds_.isEmpty()) {
            if (!StringList.quickEq(trimMeth_, stds_.getAliasClone())) {
                StringList classesNames_ = new StringList();
                UndefinedMethodError undefined_ = new UndefinedMethodError();
                MethodModifier mod_ = MethodModifier.FINAL;
                undefined_.setClassName(bounds_);
                undefined_.setId(new MethodId(mod_, trimMeth_, classesNames_).getSignature(_conf));
                undefined_.setFileName(_conf.getCurrentFileName());
                undefined_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(undefined_);
                return;
            }
            String foundClass_ = PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject());
            MethodId id_ = new MethodId(false, trimMeth_, new StringList());
            classMethodId = new ClassMethodId(foundClass_, id_);
            setResultClass(new ClassArgumentMatching(arrayBounds_));
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
            return;
        }
        ClassMethodIdReturn clMeth_;
        clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, accessSuperTypes_, accessFromSuper_, import_, feed_, ClassArgumentMatching.toArgArray(firstArgs_));
        anc = clMeth_.getAncestor();
        if (!clMeth_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
        }
        if (staticChoiceMethod_) {
            if (clMeth_.isAbstractMethod()) {
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
                AbstractMethod abs_ = new AbstractMethod();
                abs_.setClassName(clMeth_.getRealClass());
                abs_.setSgn(clMeth_.getRealId().getSignature(_conf));
                abs_.setIndexFile(_conf.getCurrentLocationIndex());
                abs_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(abs_);
                setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
                return;
            }
        }
        String foundClass_ = clMeth_.getRealClass();
        foundClass_ = Templates.getIdFromAllTypes(foundClass_);
        MethodId id_ = clMeth_.getRealId();
        classMethodId = new ClassMethodId(foundClass_, id_);
        MethodId realId_ = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = clMeth_.isStaticMethod();
        unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, firstArgs_, _conf);
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
        if (isIntermediateDottedOperation() && !staticMethod) {
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
        }
    }
    private void setDelta(Analyzable _conf) {
        String trimMeth_ = methodName.trim();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        int delta_ = 0;
        if (ContextEl.startsWithKeyWord(trimMeth_, keyWordSuper_)) {
            delta_ = trimMeth_.indexOf('.')+1;
            delta_ += StringList.getFirstPrintableCharIndex(trimMeth_.substring(delta_));
        } else if (ContextEl.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            delta_ = trimMeth_.indexOf('.')+1;
            delta_ += StringList.getFirstPrintableCharIndex(trimMeth_.substring(delta_));
        } else if (ContextEl.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            delta_ = trimMeth_.lastIndexOf(PAR_RIGHT) + 1;
            delta_ += StringList.getFirstPrintableCharIndex(trimMeth_.substring(delta_));
        }
        delta = delta_;
    }
    private static StringList getArrayBounds(StringList _bounds) {
        StringList b_ = new StringList();
        for (String b: _bounds) {
            if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                b_.add(b);
            }
        }
        return b_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        tryGetArg(this, getPreviousArgument(),_conf, classMethodId, naturalVararg, lastType);
    }

    public static void tryGetArg(PossibleIntermediateDottedOperable _current, Argument _pr,Analyzable _conf,
                                 ClassMethodId _classMethodId, int _naturalVararg, String _lastType) {
        if (!_conf.isGearConst()) {
            return;
        }
        CustList<Operable> chidren_ = ((ParentOperable)_current).getChildrenOperable();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (Operable o: chidren_) {
            arguments_.add(o.getArgument());
        }
        if (_classMethodId == null) {
            return;
        }
        Argument previous_;
        previous_ = _pr;
        Struct str_;
        if (!_classMethodId.getConstraints().isStaticMethod()) {
            if (previous_ == null || previous_.isNull()) {
                return;
            }
            str_ = previous_.getStruct();
        } else if (previous_ != null) {
            str_ = previous_.getStruct();
        } else {
            str_ = NullStruct.NULL_VALUE;
        }
        CustList<Argument> firstArgs_ = quickListArguments(chidren_, _naturalVararg, _lastType, arguments_, _conf);
        ResultErrorStd res_ = LgNames.invokeStdMethod(_conf, _classMethodId, str_, Argument.toArgArray(firstArgs_));
        Struct out_ = res_.getResult();
        if (out_ == null || out_ instanceof ArrayStruct) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(out_);
        _current.setSimpleArgumentAna(arg_, _conf);
    }
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public int getAnc() {
        return anc;
    }

    public int getDelta() {
        return delta;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
