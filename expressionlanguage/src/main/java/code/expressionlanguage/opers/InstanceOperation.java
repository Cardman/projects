package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessConstructor;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.IllegalCallCtorByType;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
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
    private boolean array;
    private boolean elts;

    private String fieldName = EMPTY_STRING;
    private int blockIndex = -1;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;

    public InstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    public void setFieldName(String _fieldName) {
        fieldName = _fieldName;
    }

    public boolean initStaticClass() {
        return isCallMethodCtor();
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        className = _conf.getStandards().getAliasObject();
        String className_ = methodName.trim().substring(INSTANCE.length()+1);
        className_ = className_.trim();
        if (!className_.startsWith(ARR) && className_.endsWith(ARR_DYN)) {
            int len_ = className_.length();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(new StringList(className_.substring(0, len_-ARR_DYN.length())));
            _conf.getClasses().addError(un_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        String realClassName_;
        if (className_.endsWith(ARR_DYN)) {
            elts = true;
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        if (realClassName_.startsWith(ARR)) {
            array = true;
            if (StringList.quickEq(realClassName_.trim(), ARR)) {
                int nbParents_ = 0;
                MethodOperation m_ = getParent();
                while (m_ != null) {
                    if (!(m_ instanceof InstanceOperation)) {
                        break;
                    }
                    InstanceOperation i_ = (InstanceOperation) m_;
                    String classNamePar_ = i_.methodName.trim().substring(INSTANCE.length()+1);
                    classNamePar_ = classNamePar_.trim();
                    if (!classNamePar_.startsWith(ARR)) {
                        break;
                    }
                    String sub_ = classNamePar_.substring(ARR.length()).trim();
                    if (!StringList.quickEq(sub_, ARR_DYN)) {
                        break;
                    }
                    nbParents_++;
                    m_ = m_.getParent();
                }
                String type_ = EMPTY_STRING;
                if (m_ == null && _conf.getCurrentBlock() instanceof InfoBlock) {
                    InfoBlock i_ = (InfoBlock) _conf.getCurrentBlock();
                    type_ = i_.getClassName();
                } else if (!(m_ instanceof AffectationOperation)) {
                    //ERROR
                    type_ = EMPTY_STRING;
                } else {
                    AffectationOperation a_ = (AffectationOperation) m_;
                    SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
                    if (s_ != null) {
                        ClassArgumentMatching c_ = s_.getResultClass();
                        if (c_.getNames().size() == 1) {
                            type_ = c_.getName();
                        }
                    }
                }
                if (type_.isEmpty() || StringList.quickEq(type_, TypeUtil.VAR_TYPE)) {
                    int len_ = className_.length();
                    UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                    un_.setRc(_conf.getCurrentLocation());
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
                    un_.setOperands(new StringList(className_.substring(0, len_-ARR_DYN.length())));
                    _conf.getClasses().addError(un_);
                    LgNames stds_ = _conf.getStandards();
                    setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
                    return;
                }
                String n_ = type_;
                String cp_ = PrimitiveTypeUtil.getQuickComponentType(n_, nbParents_);
                className = PrimitiveTypeUtil.getQuickComponentType(cp_);
                StringMap<StringList> map_;
                map_ = new StringMap<StringList>();
                String glClass_ = _conf.getGlobalClass();
                if (glClass_ != null) {
                    for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                        map_.put(t.getName(), t.getConstraints());
                    }
                }
                Mapping mapping_ = new Mapping();
                mapping_.setParam(className);
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
                    ClassArgumentMatching argType_ = o.getResultClass();
                    mapping_.setArg(argType_);
                    mapping_.setMapping(map_);
                    if (!Templates.isGenericCorrect(mapping_, _conf)) {
                        BadImplicitCast cast_ = new BadImplicitCast();
                        cast_.setMapping(mapping_);
                        cast_.setFileName(_conf.getCurrentFileName());
                        cast_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(cast_);
                    }
                    if (PrimitiveTypeUtil.isPrimitive(cp_, _conf)) {
                        o.getResultClass().setUnwrapObject(cp_);
                    }
                }
                setResultClass(new ClassArgumentMatching(cp_));
                return;
            }
            realClassName_ = _conf.resolveCorrectType(realClassName_);
            if (chidren_.isEmpty() && !elts) {
                BadOperandsNumber badCall_ = new BadOperandsNumber();
                badCall_.setOperandsNumber(0);
                badCall_.setFileName(_conf.getCurrentFileName());
                badCall_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(badCall_);
                LgNames stds_ = _conf.getStandards();
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
                return;
            }
            if (!elts) {
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
                    if (!o.getResultClass().isNumericInt(_conf)) {
                        ClassArgumentMatching cl_ = o.getResultClass();
                        UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                        un_.setRc(_conf.getCurrentLocation());
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                        un_.setOperands(cl_);
                        _conf.getClasses().addError(un_);
                    }
                    o.getResultClass().setUnwrapObject(_conf.getStandards().getAliasPrimInteger());
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
                    setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
                    ClassArgumentMatching argType_ = o.getResultClass();
                    mapping_.setArg(argType_);
                    mapping_.setMapping(map_);
                    if (!Templates.isGenericCorrect(mapping_, _conf)) {
                        BadImplicitCast cast_ = new BadImplicitCast();
                        cast_.setMapping(mapping_);
                        cast_.setFileName(_conf.getCurrentFileName());
                        cast_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(cast_);
                    }
                    if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                        o.getResultClass().setUnwrapObject(eltType_);
                    }
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            className = realClassName_;
            if (!elts) {
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, chidren_.size())));
                return;
            }
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, CustList.ONE_ELEMENT)));
            return;
        }
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(filter_, _conf);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_conf.isStaticContext());
            analyzeCtor(_conf, realClassName_, firstArgs_);
            return;
        }
        if (realClassName_.startsWith("..")) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        ClassArgumentMatching arg_ = getPreviousResultClass();
        arg_.setCheckOnlyNullPe(true);
        if (arg_ == null || arg_.isUndefined()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        StringMap<String> ownersMap_ = new StringMap<String>();
        String idClass_ = Templates.getIdFromAllTypes(realClassName_);
        for (String o: arg_.getNames()) {
            StringList ids_ = new StringList(Templates.getIdFromAllTypes(o));
            StringList owners_ = new StringList();
            while (true) {
                StringList new_ = new StringList();
                for (String s: ids_) {
                    GeneType g_ = _conf.getClassBody(s);
                    if (!(g_ instanceof RootBlock)) {
                        continue;
                    }
                    RootBlock sub_ = (RootBlock)g_;
                    boolean add_ = false;
                    for (Block b: Classes.getDirectChildren(sub_)) {
                        if (!(b instanceof RootBlock)) {
                            continue;
                        }
                        RootBlock inner_ = (RootBlock) b;
                        if (StringList.quickEq(inner_.getName(), idClass_)) {
                            owners_.add(s);
                            add_ = true;
                        }
                    }
                    if (add_) {
                        continue;
                    }
                    for (String t: sub_.getImportedDirectSuperTypes()) {
                        String id_ = Templates.getIdFromAllTypes(t);
                        new_.add(id_);
                    }
                }
                if (new_.isEmpty()) {
                    break;
                }
                ids_ = new_;
            }
            owners_.removeDuplicates();
            if (owners_.size() == 1) {
                ownersMap_.put(o, owners_.first());
            }
        }
        if (ownersMap_.size() != 1) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String sub_ = ownersMap_.getKeys().first();
        String sup_ = ownersMap_.values().first();
        String new_ = Templates.getFullTypeByBases(sub_, sup_, _conf);
        realClassName_ = StringList.concat(new_,"..",realClassName_);
        analyzeCtor(_conf, realClassName_, firstArgs_);
    }

    void analyzeCtor(Analyzable _conf, String _realClassName, CustList<ClassArgumentMatching> _firstArgs) {
        String realClassName_ = _realClassName;
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        LgNames stds_ = _conf.getStandards();
        if (StringList.quickEq(realClassName_.trim(), stds_.getAliasVoid())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(realClassName_);
            mapping_.setParam(stds_.getAliasObject());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        int varargOnly_ = lookOnlyForVarArg();
        ConstrustorIdVarArg ctorRes_ = null;

        realClassName_ = _conf.resolveCorrectType(realClassName_);
        if (PrimitiveTypeUtil.isPrimitive(realClassName_, _conf)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        String base_ = Templates.getIdFromAllTypes(realClassName_);
        GeneType g_ = _conf.getClassBody(base_);
        if (g_ == null) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (!g_.isStaticType() && !isIntermediateDottedOperation() && isStaticAccess()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(static_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (g_.isAbstractType() && !(g_ instanceof EnumBlock)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (g_ instanceof GeneInterface) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (g_ instanceof EnumBlock) {
            if (fieldName.isEmpty()) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(call_);
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
            blockIndex = _conf.getCurrentChildTypeIndex();
        }
        ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(_firstArgs));
        constId = ctorRes_.getRealId();
        className = ctorRes_.getConstId().getName();
        if (ctorRes_.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        if (!filter_.isEmpty() && filter_.first() instanceof VarargOperation) {
            int i_ = CustList.FIRST_INDEX;
            for (OperationNode o: filter_) {
                if (o instanceof VarargOperation) {
                    i_++;
                    continue;
                }
                if (o instanceof FirstOptOperation) {
                    break;
                }
                String param_ = constId.getParametersTypes().get(i_-1);
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    o.getResultClass().setUnwrapObject(param_);
                }
                i_++;
            }
        } else if (naturalVararg > -1) {
            int lenCh_ = _firstArgs.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _firstArgs.get(i);
                if (i >= naturalVararg) {
                    if (PrimitiveTypeUtil.isPrimitive(lastType, _conf)) {
                        a_.setUnwrapObject(lastType);
                    }
                } else {
                    String param_ = constId.getParametersTypes().get(i);
                    if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                        a_.setUnwrapObject(param_);
                    }
                }
            }
        } else {
            int lenCh_ = _firstArgs.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _firstArgs.get(i);
                String param_ = constId.getParametersTypes().get(i);
                if (i + 1 == lenCh_ && constId.isVararg()) {
                    param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                }
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    a_.setUnwrapObject(param_);
                }
            }
        }
        String glClass_ = _conf.getGlobalClass();
        CustList<GeneConstructor> ctors_ = TypeUtil.getConstructorBodiesById(realClassName_, constId, _conf);
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = Templates.getIdFromAllTypes(glClass_);
        }
        if (!ctors_.isEmpty() && !Classes.canAccess(curClassBase_, ctors_.first(), _conf)) {
            GeneConstructor ctr_ = ctors_.first();
            BadAccessConstructor access_ = new BadAccessConstructor();
            access_.setId(ctr_.getId());
            access_.setFileName(_conf.getCurrentFileName());
            access_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(access_);
        }
        possibleInitClass = !_conf.getOptions().isInitializeStaticClassFirst() && g_.isStaticType();
        setResultClass(new ClassArgumentMatching(realClassName_));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (!_conf.isGearConst()) {
            return;
        }
        if (array) {
            for (OperationNode o: chidren_) {
                arguments_.add(o.getArgument());
            }
            int nbCh_ = chidren_.size();
            int[] args_;
            if (elts) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                    Number n_ = (Number)arguments_.get(i).getObject();
                    if (n_ == null) {
                        return;
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        return;
                    }
                    args_[i] = dim_;
                }
            }
            Argument a_ = new Argument();
            if (elts) {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                dims_.add(nbCh_);
                ArrayStruct str_ = PrimitiveTypeUtil.newCustomArray(className, dims_, _conf);
                for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                    Argument chArg_ = arguments_.get(i);
                    if (!setCheckedElement(str_, i, chArg_, _conf)) {
                        return;
                    }
                }
                a_.setStruct(str_);
                setSimpleArgumentAna(a_, _conf);
                return;
            }
            Numbers<Integer> dims_;
            dims_ = new Numbers<Integer>();
            for (int d: args_) {
                dims_.add(d);
            }
            a_.setStruct(PrimitiveTypeUtil.newCustomArray(className, dims_, _conf));
            setSimpleArgumentAna(a_, _conf);
            return;
        }
        String cl_ = className;
        if (cl_ == null) {
            return;
        }
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
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            arguments_.add(o.getArgument());
            filter_.add(o);
        }
        CustList<Argument> firstArgs_ = quickListArguments(filter_, naturalVararg_, lastType_, arguments_, _conf);
        if (firstArgs_ == null) {
            return;
        }
        ResultErrorStd res_ = LgNames.newInstanceStd(_conf, constId, Argument.toArgArray(firstArgs_));
        if (res_.getResult() == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_.getResult());
        setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
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
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
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
        Argument res_;
        CustomFoundConstructor ctor_ = _conf.getContextEl().getCallCtor();
        if (ctor_ != null) {
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContextEl());
        } else {
            res_ = argres_;
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }

    Argument getArgument(Argument _previous,CustList<Argument> _arguments,
            ExecutableCode _conf) {
        LgNames stds_ = _conf.getStandards();
        String size_;
        size_ = stds_.getAliasBadSize();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getOperationPageEl();
        className_ = page_.formatVarType(className, _conf);
        if (array) {
            int nbCh_ = chidren_.size();
            int[] args_;
            if (elts) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Number n_ = (Number)_arguments.get(i_).getObject();
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        _conf.setException(new StdStruct(new CustomError(StringList.concat(String.valueOf(dim_),RETURN_LINE,String.valueOf(i_),RETURN_LINE,_conf.joinPages())),size_));
                        Argument a_ = new Argument();
                        return a_;
                    }
                    args_[i_] = dim_;
                    i_++;
                }
            }
            Argument a_ = new Argument();
            if (elts) {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                dims_.add(nbCh_);
                Struct str_ = PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf);
                for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                    Argument chArg_ = _arguments.get(i);
                    ArrOperation.setCheckedElement(str_, i, chArg_, _conf);
                    if (_conf.getException() != null) {
                        return a_;
                    }
                }
                a_.setStruct(str_);
                return a_;
            }
            Numbers<Integer> dims_;
            dims_ = new Numbers<Integer>();
            for (int d: args_) {
                dims_.add(d);
            }
            a_.setStruct(PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf));
            return a_;
        }
        if (possibleInitClass) {
            String base_ = Templates.getIdFromAllTypes(className_);
            if (InvokingOperation.hasToExit(_conf, base_)) {
                return Argument.createVoid();
            }
        }
        String lastType_ = Templates.format(className_, lastType, _conf);
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, _arguments, _conf);
        return instancePrepare(_conf, className_, constId, _previous, firstArgs_, fieldName, blockIndex, true);
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
        className_ = ContextEl.removeDottedSpaces(className_);
        return !className_.startsWith(ARR);
    }
}
