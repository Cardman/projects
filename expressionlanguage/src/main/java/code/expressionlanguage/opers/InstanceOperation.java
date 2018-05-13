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
import code.expressionlanguage.methods.Block;
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
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.ObjectMap;
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

    public boolean initStaticClass() {
        return isCallMethodCtor();
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf, _fieldName);
    }

    void analyzeCommon(Analyzable _conf, String _fieldName) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
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
                    setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
                    if (!o.getResultClass().isNumericInt(_conf)) {
                        ClassArgumentMatching cl_ = o.getResultClass();
                        UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                        un_.setRc(_conf.getCurrentLocation());
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                        un_.setOperands(new StringList(cl_.getName()));
                        _conf.getClasses().getErrorsDet().add(un_);
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
                    if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                        o.getResultClass().setUnwrapObject(eltType_);
                    }
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
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
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(filter_, _conf);
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
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
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
        possibleInitClass = !_conf.getOptions().isInitializeStaticClassFirst();
        setResultClass(new ClassArgumentMatching(realClassName_));
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: children_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        if (filter_.isEmpty()) {
            for (EntryCust<ClassField, AssignmentBefore> e: vars_.getFieldsBefore().getVal(this).entryList()) {
                AssignmentBefore b_ = e.getValue();
                fieldsAfter_.put(e.getKey(), b_.assignAfter(isBool_));
            }
            for (StringMap<AssignmentBefore> s: vars_.getVariablesBefore().getVal(this)) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore b_ = e.getValue();
                    sm_.put(e.getKey(), b_.assignAfter(isBool_));
                }
                variablesAfter_.add(sm_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            vars_.getVariables().put(this, variablesAfter_);
            return;
        }
        OperationNode last_ = filter_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+1);
        className_ = StringList.removeAllSpaces(className_);
        if (!_conf.isGearConst()) {
            return;
        }
        if (className_.startsWith(ARR)) {
            for (OperationNode o: chidren_) {
                arguments_.add(o.getArgument());
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
            int nbCh_ = chidren_.size();
            int[] args_;
            if (elts_) {
                args_ = new int[CustList.ONE_ELEMENT];
                args_[CustList.FIRST_INDEX] = chidren_.size();
            } else {
                args_ = new int[chidren_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (OperationNode o: chidren_) {
                    Number n_ = (Number)arguments_.get(i_).getObject();
                    if (n_ == null) {
                        return;
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        return;
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
                ArrayStruct str_ = PrimitiveTypeUtil.newCustomArray(realClassName_, dims_, _conf);
                for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                    Argument chArg_ = arguments_.get(i);
                    if (!setCheckedElement(str_, i, chArg_, _conf)) {
                        return;
                    }
                }
                a_.setStruct(str_);
                setSimpleArgumentAna(a_, _conf);
                return;
            } else {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                for (int d: args_) {
                    dims_.add(d);
                }
                a_.setStruct(PrimitiveTypeUtil.newCustomArray(realClassName_, dims_, _conf));
                setSimpleArgumentAna(a_, _conf);
                return;
            }
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
        ResultErrorStd res_ = LgNames.newInstanceStd(_conf, naturalVararg > -1, constId, Argument.toArgArray(firstArgs_));
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
        return calculateCommon(_nodes, _conf);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
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
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf);
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
    public void calculate(ContextEl _conf) {
        calculateCommon(_conf);
    }

    void calculateCommon(ContextEl _conf) {
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
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        ArgumentCall argres_ = getArgument(previous_, arguments_, _conf);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
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
            ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String size_;
        null_ = stds_.getAliasNullPe();
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
            int nbCh_ = chidren_.size();
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
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, _arguments, _conf);
        if (_conf.getException() != null) {
            Argument a_ = new Argument();
            return ArgumentCall.newArgument(a_);
        }
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
        return !className_.startsWith(ARR);
    }
}
