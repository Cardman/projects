package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.calls.util.CustomReflectMethod;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.errors.custom.AbstractMethod;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class SuperFctOperation extends InvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int anc;
    public SuperFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    boolean isCallMethodCtor(Analyzable _an) {
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
        ClassMethodId idMethod_ = lookOnlyForId();
        LgNames stds_ = _conf.getStandards();
        boolean import_ = false;
        ClassArgumentMatching clCur_;
        if (!isIntermediateDottedOperation()) {
            clCur_ = new ClassArgumentMatching(_conf.getGlobalClass());
            import_ = true;
            setStaticAccess(_conf.isStaticContext());
        } else {
            clCur_ = getPreviousResultClass();
        }
        String className_ = methodName.substring(0, methodName.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName.indexOf(PAR_LEFT) + 1;
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
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            cast_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
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
        ClassMethodId feed_ = null;
        if (idMethod_ != null) {
            String idClass_ = idMethod_.getClassName();
            MethodId mid_ = idMethod_.getConstraints();
            boolean vararg_ = mid_.isVararg();
            StringList params_ = mid_.getParametersTypes();
            boolean static_ = isStaticAccess() || mid_.isStaticMethod();
            feed_ = new ClassMethodId(idClass_, new MethodId(static_, trimMeth_, params_, vararg_));
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, true, false, import_, feed_, ClassArgumentMatching.toArgArray(firstArgs_));
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
            abs_.setIndexFile(_conf.getCurrentLocationIndex());
            abs_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(abs_);
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
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
        staticMethod = clMeth_.isStaticMethod();
        unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, firstArgs_, _conf);
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
        if (isIntermediateDottedOperation() && !staticMethod) {
            Argument arg_ = getPreviousArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setIndexFile(_conf.getCurrentLocationIndex());
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
            if (_conf.getContextEl().hasException()) {
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
            res_ = ProcessMethod.reflectArgument(ref_.getGl(), ref_.getArguments(), _conf.getContextEl(), ref_.getReflect(), ref_.isLambda());
        } else {
            res_ = argres_;
        }
        if (_conf.getContextEl().hasException()) {
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
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        if (!staticMethod) {
            prev_.setStruct(_previous.getStruct());
            classNameFound_ = classMethodId.getClassName();
            prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, prev_.getStruct(), _conf));
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                Argument a_ = new Argument();
                return a_;
            }
            String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
            String base_ = Templates.getIdFromAllTypes(classNameFound_);
            String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
            lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            methodId_ = classMethodId.getConstraints();
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
