package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.AbstractMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
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
    private MethodId realId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

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
        ClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            clCur_ = new ClassArgumentMatching(_conf.getGlobalClass());
            setStaticAccess(_conf.isStaticContext());
        }
        String clCurName_;

        if (clCur_ == null || clCur_.isUndefined()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(static_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        clCurName_ = clCur_.getName();
        if (hasVoidPrevious(clCurName_, _conf)) {
            return;
        }
        StringList bounds_ = getBounds(clCurName_, _conf);
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String trimMeth_ = methodName.trim();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        int varargOnly_ = lookOnlyForVarArg();
        if (hasVoidArguments(chidren_, firstArgs_, off_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }

        boolean staticChoiceMethod_ = false;
        boolean accessFromSuper_ = false;
        if (trimMeth_.startsWith(prefixFunction(StringList.concat(SUPER_ACCESS, String.valueOf(DOT_VAR))))) {
            trimMeth_ = trimMeth_.substring(SUPER_ACCESS.length() + 2);
            staticChoiceMethod_ = true;
            accessFromSuper_ = true;
        } else if (trimMeth_.startsWith(prefixFunction(StringList.concat(THAT, String.valueOf(DOT_VAR))))) {
            trimMeth_ = trimMeth_.substring(THAT.length() + 2);
            staticChoiceMethod_ = true;
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, true, accessFromSuper_, ClassArgumentMatching.toArgArray(firstArgs_));
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
                _conf.getClasses().getErrorsDet().add(abs_);
                setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
                return;
            }
        }
        String foundClass_ = clMeth_.getRealClass();
        foundClass_ = Templates.getIdFromAllTypes(foundClass_);
        MethodId id_ = clMeth_.getRealId();
        classMethodId = new ClassMethodId(foundClass_, id_);
        realId = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticChoiceMethod = staticChoiceMethod_;
        staticMethod = clMeth_.isStaticMethod();
        unwrapArgsFct(chidren_, realId, naturalVararg, lastType, firstArgs_, _conf);
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
        if (isIntermediateDottedOperation() && !staticMethod) {
            Argument arg_ = getPreviousArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(static_);
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
        Argument res_;
        if (ctor_ != null) {
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContextEl());
        } else if (method_ != null) {
            res_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), _conf.getContextEl());
        } else {
            res_ = argres_;
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }
    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        if (!staticMethod) {
            if (_previous.isNull()) {
                String null_;
                null_ = stds_.getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                Argument a_ = new Argument();
                return a_;
            }
            if (staticChoiceMethod) {
                classNameFound_ = classMethodId.getClassName();
                String base_ = Templates.getIdFromAllTypes(classNameFound_);
                String argClassName_ = _previous.getObjectClassName(_conf.getContextEl());
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.format(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = realId;
            } else {
                classNameFound_ = classMethodId.getClassName();
                classNameFound_ = Templates.getIdFromAllTypes(classNameFound_);
                String argClassName_ = _previous.getObjectClassName(_conf.getContextEl());
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
                lastType_ = Templates.format(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                argClassName_ = Templates.getGenericString(argClassName_, _conf);
                String base_ = Templates.getIdFromAllTypes(argClassName_);
                MethodId id_ = classMethodId.getConstraints();
                if (_conf.getMethodBodiesById(classNameFound_, id_).first().isFinalMethod()) {
                    classNameFound_ = classMethodId.getClassName();
                    methodId_ = realId;
                } else {
                    GeneType info_ = _conf.getClassBody(classNameFound_);
                    StringMap<ClassMethodId> overriding_ = TypeUtil.getConcreteMethodsToCall(info_,id_, _conf.getContextEl());
                    if (overriding_.contains(base_)) {
                        ClassMethodId res_ = overriding_.getVal(base_);
                        classNameFound_ = res_.getClassName();
                        methodId_ = res_.getConstraints();
                    } else {
                        classNameFound_ = classMethodId.getClassName();
                        methodId_ = realId;
                    }
                }
            }
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            classNameFound_ = classMethodId.getClassName();
            if (classes_.isCustomType(classNameFound_)) {
                InitClassState res_ = classes_.getLocks().getState(_conf.getContextEl(), classNameFound_);
                if (res_ == InitClassState.NOT_YET) {
                    _conf.getContextEl().setInitClass(new NotInitializedClass(classNameFound_));
                    return Argument.createVoid();
                }
                if (res_ == InitClassState.ERROR) {
                    CausingErrorStruct causing_ = new CausingErrorStruct(classNameFound_);
                    _conf.setException(causing_);
                    return Argument.createVoid();
                }
            }
        }
        int offLoc_ = -1;
        if (!chidren_.isEmpty()) {
            offLoc_ = chidren_.last().getIndexInEl() + getOperations().getDelimiter().getIndexBegin();
        }
        return callPrepare(_conf, classNameFound_, methodId_, _previous, firstArgs_, offLoc_);
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
    boolean isCallMethodCtor() {
        return true;
    }
}
