package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.CustomReflectMethod;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UndefinedMethodError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
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

        if (clCur_ == null || clCur_.isUndefined()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(static_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        StringList l_ = clCur_.getNames();
        CustList<OperationNode> chidren_ = getChildrenNodes();
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
        if (trimMeth_.startsWith(StringList.concat(keyWordSuper_, String.valueOf(DOT_VAR)))) {
            trimMeth_ = trimMeth_.substring(keyWordSuper_.length() + 1);
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (trimMeth_.startsWith(StringList.concat(keyWordThat_, String.valueOf(DOT_VAR)))) {
            trimMeth_ = trimMeth_.substring(keyWordThat_.length() + 1);
            staticChoiceMethod_ = true;
        } else if (ElResolver.procWordFirstChar(trimMeth_, 0, keyWordThisaccess_)) {
            String className_ = trimMeth_.substring(0, trimMeth_.lastIndexOf(PAR_RIGHT));
            int lenPref_ = trimMeth_.indexOf(PAR_LEFT) + 1;
            className_ = className_.substring(lenPref_);
            className_ = _conf.resolveCorrectType(className_);
            Mapping map_ = new Mapping();
            map_.setParam(className_);
            map_.setArg(clCur_);
            StringMap<StringList> mapping_ = new StringMap<StringList>();
            for (TypeVar t: Templates.getConstraints(_conf.getGlobalClass(), _conf)) {
                mapping_.put(t.getName(), t.getConstraints());
            }
            map_.setMapping(mapping_);
            if (!Templates.isCorrect(map_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(map_);
                cast_.setRc(_conf.getCurrentLocation());
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
            boolean vararg_ = idMethod_.getConstraints().isVararg();
            StringList params_ = idMethod_.getConstraints().getParametersTypes();
            boolean static_ = isStaticAccess();
            feed_ = new ClassMethodId(idClass_, new MethodId(static_, trimMeth_, params_, vararg_));
        }
        boolean cloneArray_ = false;
        StringList bounds_ = new StringList();
        for (String c: l_) {
            if (c.isEmpty()) {
                continue;
            }
            if (hasVoidPrevious(c, _conf)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            bounds_.addAllElts(getBounds(c, _conf));
        }
        for (String b: bounds_) {
            if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                cloneArray_ = true;
                break;
            }
        }
        if (cloneArray_) {
            if (!StringList.quickEq(trimMeth_, stds_.getAliasClone())) {
                StringList classesNames_ = new StringList();
                UndefinedMethodError undefined_ = new UndefinedMethodError();
                MethodModifier mod_ = MethodModifier.FINAL;
                undefined_.setClassName(bounds_);
                undefined_.setId(new MethodId(mod_, trimMeth_, classesNames_));
                undefined_.setFileName(_conf.getCurrentFileName());
                undefined_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(undefined_);
                return;
            }
            StringList a_ = new StringList();
            for (String b: bounds_) {
                if (b.startsWith(PrimitiveTypeUtil.ARR_CLASS)) {
                    a_.add(b);
                }
            }
            String foundClass_ = PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject());
            MethodId id_ = new MethodId(false, trimMeth_, new StringList());
            classMethodId = new ClassMethodId(foundClass_, id_);
            setResultClass(new ClassArgumentMatching(a_));
            Argument arg_ = getPreviousArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
            }
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
                abs_.setSgn(clMeth_.getRealId().getSignature());
                abs_.setRc(_conf.getCurrentLocation());
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
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
            }
        }
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = _nodes.getVal(this).getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        Argument res_ = getArgument(previous_, arguments_, _conf);
        if (_conf.callsOrException()) {
            return res_;
        }
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isGearConst()) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        if (classMethodId == null) {
            return;
        }
        Argument previous_;
        previous_ = getPreviousArgument();
        Struct str_;
        if (!classMethodId.getConstraints().isStaticMethod()) {
            if (previous_ == null || previous_.isNull()) {
                return;
            }
            str_ = previous_.getStruct();
        } else if (previous_ != null) {
            str_ = previous_.getStruct();
        } else {
            str_ = NullStruct.NULL_VALUE;
        }
        String cl_ = classMethodId.getClassName();
        if (_conf.getClasses().isCustomType(cl_)) {
            return;
        }
        boolean proc_ = false;
        if (PrimitiveTypeUtil.isPrimitiveOrWrapper(cl_, _conf)) {
            proc_ = true;
        } else if (StringList.quickEq(cl_, _conf.getStandards().getAliasString())) {
            proc_ = true;
        } else if (StringList.quickEq(cl_, _conf.getStandards().getAliasMath())) {
            proc_ = true;
        }
        if (!proc_) {
            return;
        }
        if (lastType == null) {
            return;
        }
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        CustList<Argument> firstArgs_ = quickListArguments(chidren_, naturalVararg_, lastType_, arguments_, _conf);
        if (firstArgs_ == null) {
            return;
        }
        ResultErrorStd res_ = LgNames.invokeStdMethod(_conf, classMethodId, str_, Argument.toArgArray(firstArgs_));
        if (res_.getResult() == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_.getResult());
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument argres_ = getArgument(previous_, arguments_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        CustomFoundConstructor ctor_ = _conf.getContextEl().getCallCtor();
        CustomFoundMethod method_ = _conf.getContextEl().getCallMethod();
        CustomReflectMethod ref_ = _conf.getContextEl().getReflectMethod();
        Argument res_;
        if (ctor_ != null) {
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContextEl());
        } else if (method_ != null) {
            res_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), _conf.getContextEl());
        } else if (ref_ != null) {
            res_ = ProcessMethod.reflectArgument(ref_.getGl(), ref_.getArguments(), _conf.getContextEl(), ref_.getReflect());
        } else {
            res_ = argres_;
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }
    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        if (!staticMethod) {
            classNameFound_ = classMethodId.getClassName();
            prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, _previous.getStruct(), _conf));
            if (_conf.getException() != null) {
                Argument a_ = new Argument();
                return a_;
            }
            if (prev_.getStruct().isArray()) {
                int offLoc_ = -1;
                if (!chidren_.isEmpty()) {
                    offLoc_ = chidren_.last().getIndexInEl() + getOperations().getDelimiter().getIndexBegin();
                }
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                return callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, offLoc_);
            }
            String base_ = Templates.getIdFromAllTypes(classNameFound_);
            if (staticChoiceMethod) {
                String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = classMethodId.getConstraints();
            } else {
                Struct previous_ = prev_.getStruct();
                ContextEl context_ = _conf.getContextEl();
                ClassMethodId methodToCall_ = polymorph(context_, previous_, classMethodId);
                String argClassName_ = stds_.getStructClassName(previous_, context_);
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = methodToCall_.getConstraints();
                classNameFound_ = methodToCall_.getClassName();
            }
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            classNameFound_ = classMethodId.getClassName();
            if (hasToExit(_conf, classNameFound_)) {
                return Argument.createVoid();
            }
        }
        int offLoc_ = -1;
        if (!chidren_.isEmpty()) {
            offLoc_ = chidren_.last().getIndexInEl() + getOperations().getDelimiter().getIndexBegin();
        }
        return callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, offLoc_);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    boolean isCallMethodCtor(Analyzable _an) {
        return true;
    }
}
