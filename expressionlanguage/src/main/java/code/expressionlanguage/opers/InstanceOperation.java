package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.InvokingConstructor;
import code.expressionlanguage.InvokingMethod;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessConstructor;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.IllegalCallCtorByType;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class InstanceOperation extends InvokingOperation {

    private boolean possibleInitClass;

    private String methodName;

    private ConstructorId constId;

    private String className;

    private String fieldName = EMPTY_STRING;
    private int blockIndex = -1;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;

    public InstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf, _fieldName);
    }

    void analyzeCommon(Analyzable _conf, String _fieldName) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+1);
        className_ = StringList.removeAllSpaces(className_);
        if (!className_.startsWith(ARR) && className_.endsWith(ARR_DYN)) {
            int len_ = className_.length();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(new StringList(className_.substring(0, len_-ARR_DYN.length())));
            _conf.getClasses().getErrorsDet().add(un_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        boolean elts_ = false;
        String realClassName_;
        if (className_.endsWith(ARR_DYN)) {
            elts_ = true;
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        if (realClassName_.startsWith(ARR)) {
            if (chidren_.isEmpty() && !elts_) {
                BadOperandsNumber badCall_ = new BadOperandsNumber();
                badCall_.setOperandsNumber(0);
                badCall_.setFileName(_conf.getCurrentFileName());
                badCall_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(badCall_);
                LgNames stds_ = _conf.getStandards();
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
                return;
            }
            if (!elts_) {
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (!o.getResultClass().isNumericInt(_conf)) {
                        ClassArgumentMatching cl_ = o.getResultClass();
                        UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                        un_.setRc(_conf.getCurrentLocation());
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                        un_.setOperands(new StringList(cl_.getName()));
                        _conf.getClasses().getErrorsDet().add(un_);
                    }
                }
            } else {
                StringMap<StringList> map_;
                map_ = new StringMap<StringList>();
                String glClass_ = _conf.getGlobalClass();
                if (glClass_ != null) {
                    for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                        map_.put(t.getName(), t.getConstraints());
                    }
                }
                String eltType_ = PrimitiveTypeUtil.getQuickComponentType(realClassName_);
                Mapping mapping_ = new Mapping();
                mapping_.setParam(eltType_);
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    String argType_ = o.getResultClass().getName();
                    mapping_.setArg(argType_);
                    mapping_.setMapping(map_);
                    if (!Templates.isGenericCorrect(mapping_, _conf)) {
                        BadImplicitCast cast_ = new BadImplicitCast();
                        cast_.setMapping(mapping_);
                        cast_.setFileName(_conf.getCurrentFileName());
                        cast_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(cast_);
                    }
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            if (!checkCorrect(_conf, realClassName_, false, 0)) {
                realClassName_ = _conf.getStandards().getAliasObject();
            }
            if (!elts_) {
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, chidren_.size())));
                return;
            }
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, CustList.ONE_ELEMENT)));
            return;
        }
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        boolean intern_ = true;
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_conf.isStaticContext());
            intern_ = false;
//            if (StringList.isWord(realClassName_)) {
//                needGlobalArgument();
//                ClassArgumentMatching arg_ = getPreviousResultClass();
//                if (arg_ == null) {
//                    throw new NullGlobalObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
//                }
//                //TODO wrap getDeclaredClasses
//                for (Class<?> c:arg_.getDeclaredClasses()) {
//                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
//                        intern_ = true;
//                        if (!Modifier.isStatic(c.getModifiers())) {
//                            firstArgs_.add(CustList.FIRST_INDEX, arg_);
//                        }
//                        realClassName_ = arg_.getName()+INTERN_CLASS+realClassName_;
//                        break;
//                    }
//                }
//            }
            analyzeCtor(_conf, _fieldName, realClassName_, firstArgs_, intern_);
            return;
        }
        ClassArgumentMatching arg_ = getPreviousResultClass();
        if (isIntermediateDottedOperation()) {
            arg_ = getPreviousResultClass();
        } else {
            arg_ = new ClassArgumentMatching(_conf.getGlobalClass());
            setStaticAccess(_conf.isStaticContext());
        }
        if (arg_ == null || arg_.getName() == null) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        firstArgs_.add(CustList.FIRST_INDEX, arg_);
        realClassName_ = StringList.concat(arg_.getName(),String.valueOf(INTERN_CLASS),realClassName_);
        analyzeCtor(_conf, _fieldName, realClassName_, firstArgs_, intern_);
    }

    void analyzeCtor(Analyzable _conf, String _fieldName, String _realClassName, CustList<ClassArgumentMatching> _firstArgs, boolean _intern) {
        String realClassName_ = _realClassName;
        LgNames stds_ = _conf.getStandards();
        if (StringList.quickEq(realClassName_, stds_.getAliasVoid())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(realClassName_);
            mapping_.setParam(stds_.getAliasObject());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        int varargOnly_ = lookOnlyForVarArg();
        ConstrustorIdVarArg ctorRes_ = null;

        if (!checkCorrect(_conf, realClassName_, false, 0)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(realClassName_, _conf)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        ClassMetaInfo custClass_ = null;
        custClass_ = _conf.getClassMetaInfo(realClassName_);
        if (custClass_ == null) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (custClass_.isAbstractType() && custClass_.getCategory() != ClassCategory.ENUM) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (custClass_.getCategory() == ClassCategory.INTERFACE) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (custClass_.getCategory() == ClassCategory.ENUM) {
            if (_fieldName.isEmpty() || getParent() != null) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(call_);
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
            blockIndex = _conf.getCurrentChildTypeIndex();
            fieldName = _fieldName;
        }
        ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(_firstArgs));
        constId = ctorRes_.getRealId();
        className = ctorRes_.getConstId().getName();
        if (ctorRes_.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        String glClass_ = _conf.getGlobalClass();
        CustList<GeneConstructor> ctors_ = TypeUtil.getConstructorBodiesById(realClassName_, constId, _conf);
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = StringList.getAllTypes(glClass_).first();
        }
        if (!ctors_.isEmpty() && !Classes.canAccess(curClassBase_, ctors_.first(), _conf)) {
            GeneConstructor ctr_ = ctors_.first();
            BadAccessConstructor access_ = new BadAccessConstructor();
            access_.setId(ctr_.getId());
            access_.setFileName(_conf.getCurrentFileName());
            access_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(access_);
        }
        possibleInitClass = true;
        setResultClass(new ClassArgumentMatching(realClassName_));
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public boolean isPossibleInitClass() {
        return false;
    }
    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
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
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf, _op);
        Argument res_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            _conf.setCallCtor(new CustomFoundConstructor(i_.getClassName(), i_.getFieldName(), i_.getOrdinal(), i_.getCalled(), i_.getId(), i_.getCurrentObject(), i_.getArguments(), i_.getInstanceStep()));
        } else {
            setSimpleArgument(res_, _conf, _nodes);
        }
        return res_;
    }
    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf, _op);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf, _op);
        }
        Argument res_;
        if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            res_ = ProcessMethod.instanceArgument(i_.getClassName(), i_.getCurrentObject(), i_.getId(), i_.getArguments(), _conf);
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            res_ = ProcessMethod.calculateArgument(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments(), _conf);
        } else {
            res_ = argres_.getArgument();
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }

    ArgumentCall getArgument(Argument _previous,CustList<Argument> _arguments,
            ContextEl _conf, String _op) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String size_;
        null_ = stds_.getAliasNullPe();
        size_ = stds_.getAliasBadSize();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int nbCh_ = chidren_.size();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+1);
        className_ = StringList.removeAllSpaces(className_);
        boolean elts_ = false;
        String realClassName_;
        if (className_.endsWith(ARR_DYN)) {
            elts_ = true;
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        PageEl page_ = _conf.getLastPage();
        realClassName_ = page_.formatVarType(realClassName_, _conf);
        if (realClassName_.startsWith(ARR)) {
            int[] args_;
            if (elts_) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Number n_ = (Number)_arguments.get(i_).getObject();
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (n_ == null) {
                        _conf.setException(new StdStruct(new CustomError(StringList.concat(String.valueOf(i_),RETURN_LINE,_conf.joinPages())),null_));
                        Argument a_ = new Argument();
                        return ArgumentCall.newArgument(a_);
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        _conf.setException(new StdStruct(new CustomError(StringList.concat(String.valueOf(dim_),RETURN_LINE,String.valueOf(i_),RETURN_LINE,_conf.joinPages())),size_));
                        Argument a_ = new Argument();
                        return ArgumentCall.newArgument(a_);
                    }
                    args_[i_] = dim_;
                    i_++;
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            Argument a_ = new Argument();
            if (elts_) {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                dims_.add(nbCh_);
                Struct str_ = PrimitiveTypeUtil.newCustomArray(realClassName_, dims_, _conf);
                for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                    Argument chArg_ = _arguments.get(i);
                    ArrOperation.setCheckedElement(str_, i, chArg_, _conf);
                    if (_conf.getException() != null) {
                        return ArgumentCall.newArgument(a_);
                    }
                }
                a_.setStruct(str_);
                return ArgumentCall.newArgument(a_);
            } else {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                for (int d: args_) {
                    dims_.add(d);
                }
                a_.setStruct(PrimitiveTypeUtil.newCustomArray(realClassName_, dims_, _conf));
                return ArgumentCall.newArgument(a_);
            }
        }
        if (possibleInitClass) {
            String base_ = StringList.getAllTypes(realClassName_).first();
            if (_conf.getClasses().isCustomType(base_)) {
                InitClassState res_ = _conf.getClasses().getLocks().getState(_conf, base_);
                if (res_ == InitClassState.NOT_YET) {
                    InitializatingClass inv_ = new InitializatingClass(realClassName_);
                    return ArgumentCall.newCall(inv_);
                }
                if (res_ == InitClassState.ERROR) {
                    CausingErrorStruct causing_ = new CausingErrorStruct(base_);
                    _conf.setException(causing_);
                    return ArgumentCall.newArgument(Argument.createVoid());
                }
            }
        }
        className_ = page_.formatVarType(className_, _conf);
        String lastType_ = Templates.format(className_, lastType, _conf);
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType_, _arguments, _conf);
        if (!isIntermediateDottedOperation()) {
//            Class<?> class_ = null;
//            if (StringList.isWord(realClassName_)) {
//                for (Class<?> c:getPreviousResultClass().getDeclaredClasses()) {
//                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
//                        class_ = c;
//                        break;
//                    }
//                }
//            }
            return getArg(_previous, firstArgs_, _conf);
        }
        return getArg(_previous, firstArgs_, _conf);
    }
    ArgumentCall getArg(Argument _previous, CustList<Argument> _arguments,
            ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
//        String null_;
//        null_ = stds_.getAliasNullPe();
        Argument needed_ = null;
//        if (_class != null && !Modifier.isStatic(_class.getModifiers())) {
//            Argument arg_ = _previous;
//            if (arg_.isNull()) {
//                throw new InvokeException(new StdStruct(new CustomError(_class.getName()+RETURN_LINE+_conf.joinPages()),null_));
//            }
//            needed_ = arg_;
//            _arguments.add(CustList.FIRST_INDEX, arg_);
//        }
        String base_ = StringList.getAllTypes(className).first();
        if (!_conf.getClasses().isCustomType(base_)) {
            ResultErrorStd res_ = LgNames.newInstance(_conf, naturalVararg > -1, constId, Argument.toArgArray(_arguments));
            if (_conf.getException() != null) {
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                Argument a_ = new Argument();
                return ArgumentCall.newArgument(a_);
            }
            Argument arg_ = new Argument();
            arg_.setStruct(res_.getResult());
            return ArgumentCall.newArgument(arg_);
        }
        String className_ = className;
        PageEl page_ = _conf.getLastPage();
        className_ = page_.formatVarType(className_, _conf);
        StringList params_ = new StringList();
        int j_ = 0;
        for (String c: constId.getParametersTypes()) {
            String class_ = c;
            class_ = Templates.format(className_, class_, _conf);
            if (j_ + 1 == constId.getParametersTypes().size() && constId.isVararg()) {
                class_ = PrimitiveTypeUtil.getPrettyArrayType(class_);
            }
            params_.add(class_);
            j_++;
        }
        StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: _arguments) {
            if (i_ < params_.size()) {
                Struct str_ = a.getStruct();
                if (PrimitiveTypeUtil.primitiveTypeNullObject(params_.get(i_), str_, _conf)) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                    Argument a_ = new Argument();
                    return ArgumentCall.newArgument(a_);
                }
                if (!str_.isNull()) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(a.getObjectClassName(_conf));
                    mapping_.setParam(params_.get(i_));
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        String cast_;
                        cast_ = stds_.getAliasCast();
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                        Argument a_ = new Argument();
                        return ArgumentCall.newArgument(a_);
                    }
                }
                if (str_ instanceof NumberStruct || str_ instanceof CharStruct) {
                    ClassArgumentMatching clArg_ = new ClassArgumentMatching(params_.get(i_));
                    a.setStruct(PrimitiveTypeUtil.convertObject(clArg_, str_, _conf));
                }
            }
            i_++;
        }
        InvokingConstructor inv_ = new InvokingConstructor(className_, fieldName, blockIndex,constId, needed_, _arguments, InstancingStep.NEWING, called_);
        return ArgumentCall.newCall(inv_);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    boolean isCallMethodCtor() {
        String className_ = methodName.trim().substring(INSTANCE.length()+1);
        className_ = StringList.removeAllSpaces(className_);
        String realClassName_;
        if (className_.endsWith(ARR_DYN)) {
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        return !realClassName_.startsWith(ARR);
    }
}
