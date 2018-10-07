package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
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
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class ChoiceFctOperation extends InvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;
    private MethodId realId;

    private boolean staticMethod;

    private boolean staticChoiceMethodTemplate;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int anc;
    public ChoiceFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    boolean isCallMethodCtor() {
        return true;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String trimMeth_ = methodName.trim();
        int varargOnly_ = lookOnlyForVarArg();
        LgNames stds_ = _conf.getStandards();
        boolean import_ = false;
        if (!isIntermediateDottedOperation()) {
            import_ = true;
            setStaticAccess(_conf.isStaticContext());
        }
        String className_ = methodName.substring(0, methodName.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName.indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        className_ = _conf.resolveCorrectType(className_);
        staticChoiceMethodTemplate = true;
        String clCurName_ = className_;
        if (hasVoidPrevious(clCurName_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        StringList bounds_ = getBounds(clCurName_, _conf);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        if (hasVoidArguments(chidren_, firstArgs_, off_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);

        trimMeth_ = methodName.substring(methodName.lastIndexOf(PAR_RIGHT)+1).trim();
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, false, false, import_, ClassArgumentMatching.toArgArray(firstArgs_));
        anc = clMeth_.getAncestor();
        if (!clMeth_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
        }
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
        classMethodId = clMeth_.getId();
        realId = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticMethod = clMeth_.isStaticMethod();
        unwrapArgsFct(chidren_, realId, naturalVararg, lastType, firstArgs_, _conf);
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

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
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
    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        if (!staticMethod) {
            prev_.setStruct(_previous.getStruct());
            for (int i = 0; i < anc; i++) {
                prev_.setStruct(prev_.getStruct().getParent());
            }
            if (prev_.isNull()) {
                String null_;
                null_ = stds_.getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                Argument a_ = new Argument();
                return a_;
            }
            classNameFound_ = classMethodId.getClassName();
            prev_.setStruct(PrimitiveTypeUtil.getParent(classNameFound_, prev_.getStruct(), _conf));
            String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
            if (staticChoiceMethodTemplate) {
                classNameFound_ = Templates.quickFormat(argClassName_, classNameFound_, _conf);
                Mapping map_ = new Mapping();
                map_.setArg(argClassName_);
                map_.setParam(classNameFound_);
                if (!Templates.isCorrect(map_, _conf)) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                String base_ = Templates.getIdFromAllTypes(classNameFound_);
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            } else {
                classNameFound_ = Templates.getIdFromAllTypes(classNameFound_);
                String baseArgClassName_ = Templates.getIdFromAllTypes(argClassName_);
                if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, baseArgClassName_, _conf)) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(baseArgClassName_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
                methodId_ = realId.quickFormat(classNameFound_, _conf);
                if (!methodId_.isVararg()) {
                    lastType_ = EMPTY_STRING;
                    naturalVararg_ = -1;
                } else {
                    if (methodId_.getParametersTypes().size() != _arguments.size()) {
                        lastType_ = methodId_.getParametersTypes().last();
                        naturalVararg_ = methodId_.getParametersTypes().size() - 1;
                    } else {
                        Mapping map_ = new Mapping();
                        String param_ = methodId_.getParametersTypes().last();
                        param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                        String argClass_ = _arguments.last().getObjectClassName(_conf.getContextEl());
                        map_.setArg(argClass_);
                        if (argClass_ != null) {
                            map_.setParam(param_);
                            if (!Templates.isCorrect(map_, _conf)) {
                                lastType_ = methodId_.getParametersTypes().last();
                                naturalVararg_ = methodId_.getParametersTypes().size() - 1;
                            } else {
                                naturalVararg_ = -1;
                            }
                        } else {
                            String paramOr_ = methodId_.getParametersTypes().last();
                            if (PrimitiveTypeUtil.isPrimitive(paramOr_, _conf)) {
                                naturalVararg_ = -1;
                            } else {
                                lastType_ = methodId_.getParametersTypes().last();
                                naturalVararg_ = methodId_.getParametersTypes().size() - 1;
                            }
                        }
                    }
                }
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            }
            methodId_ = realId;
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

}
