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
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadConstructorCall;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.IllegalCallCtorByType;
import code.expressionlanguage.errors.custom.UndefinedFieldError;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.methods.AnnotationBlock;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class AnnotationInstanceOperation extends InvokingOperation implements PreAnalyzableOperation {

    private boolean possibleInitClass;

    private String methodName;

    private String className;
    private StringMap<String> fieldNames = new StringMap<String>();
    private boolean array;

    public AnnotationInstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void preAnalyze(Analyzable _conf) {
        if (methodName.trim().isEmpty()) {
            array = true;
            MethodOperation mOp_ = getParent();
            if (mOp_ == null) {
                Block curr_ = _conf.getCurrentBlock();
                if (curr_ instanceof AnnotationMethodBlock) {
                    className = ((AnnotationMethodBlock)curr_).getImportedReturnType();
                }
            }
            if (mOp_ instanceof AssocationOperation) {
                AssocationOperation ass_ = (AssocationOperation) mOp_;
                String fieldName_ = ass_.getFieldName();
                MethodOperation mOpAss_ = ass_.getParent();
                if (mOpAss_ instanceof AnnotationInstanceOperation) {
                    AnnotationInstanceOperation inst_;
                    inst_ = (AnnotationInstanceOperation)mOpAss_;
                    String className_ = inst_.getClassName();
                    Block ann_ = (Block) _conf.getClassBody(className_);
                    String type_ = EMPTY_STRING;
                    for (Block b: Classes.getDirectChildren(ann_)) {
                        if (!(b instanceof AnnotationMethodBlock)) {
                            continue;
                        }
                        AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                        if (StringList.quickEq(a_.getName(), fieldName_)) {
                            type_ = a_.getImportedReturnType();
                            break;
                        }
                    }
                    if (!type_.isEmpty()) {
                        className = type_;
                    } else {
                        className = _conf.getStandards().getAliasObject();
                    }
                } else {
                    className = _conf.getStandards().getAliasObject();
                }
            } else if (mOp_ instanceof AnnotationInstanceOperation) {
                if (((AnnotationInstanceOperation)mOp_).isArray()) {
                    UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                    un_.setRc(_conf.getCurrentLocation());
                    un_.setFileName(_conf.getCurrentFileName());
                    _conf.getClasses().addError(un_);
                    className = _conf.getStandards().getAliasObject();
                } else {
                    AnnotationInstanceOperation inst_;
                    inst_ = (AnnotationInstanceOperation)mOp_;
                    String className_ = inst_.getClassName();
                    Block ann_ = (Block) _conf.getClassBody(className_);
                    CustList<Block> bls_ = Classes.getDirectChildren(ann_);
                    CustList<AnnotationMethodBlock> blsAnn_ = new CustList<AnnotationMethodBlock>();
                    for (Block b: bls_) {
                        if (!(b instanceof AnnotationMethodBlock)) {
                            continue;
                        }
                        AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                        blsAnn_.add(a_);
                    }
                    if (blsAnn_.size() != 1) {
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setRc(_conf.getCurrentLocation());
                        un_.setFileName(_conf.getCurrentFileName());
                        _conf.getClasses().addError(un_);
                        className = _conf.getStandards().getAliasObject();
                    } else {
                        AnnotationMethodBlock a_ =blsAnn_.first();
                        className = a_.getImportedReturnType();
                    }
                }
            }
        } else {
            String className_ = methodName.trim().substring(AROBASE.length());
            className_ = className_.trim();
            String realClassName_ = className_;
            realClassName_ = _conf.resolveCorrectType(realClassName_);
            GeneType g_ = _conf.getClassBody(realClassName_);
            if (g_ == null) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(call_);
                className = _conf.getStandards().getAliasObject();
                setResultClass(new ClassArgumentMatching(className));
                return;
            }
            if (!(g_ instanceof AnnotationBlock)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(call_);
                className = _conf.getStandards().getAliasObject();
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
            className = realClassName_;
            possibleInitClass = !_conf.getOptions().isInitializeStaticClassFirst();
        }
    }
    public boolean isArray() {
        return array;
    }
    public String getClassName() {
        return className;
    }
    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (array) {
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            String eltType_ = PrimitiveTypeUtil.getQuickComponentType(className);
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
            setResultClass(new ClassArgumentMatching(className));
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
        setStaticAccess(_conf.isStaticContext());
        analyzeCtor(_conf, firstArgs_);
    }

    void analyzeCtor(Analyzable _conf, CustList<ClassArgumentMatching> _firstArgs) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        String objCl_ = _conf.getStandards().getAliasObject();
        if (StringList.quickEq(className, objCl_)) {
            setResultClass(new ClassArgumentMatching(className));
            return;
        }

        GeneType g_ = _conf.getClassBody(className);
        StringMap<Boolean> fieldsOpt_ = new StringMap<Boolean>();
        StringMap<String> fieldsTypes_ = new StringMap<String>();
        for (Block b: Classes.getDirectChildren((Block)g_)) {
            if (!(b instanceof AnnotationMethodBlock)) {
                continue;
            }
            AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
            fieldsOpt_.put(a_.getName(), !a_.getDefaultValue().isEmpty());
            fieldsTypes_.put(a_.getName(), a_.getImportedReturnType());
        }
        StringList suppliedFields_ = new StringList();
        StringMap<ClassArgumentMatching> suppliedFieldsType_ = new StringMap<ClassArgumentMatching>();
        for (OperationNode o: filter_) {
            if (!(o instanceof AssocationOperation)) {
                continue;
            }
            AssocationOperation a_ = (AssocationOperation) o;
            suppliedFields_.add(a_.getFieldName());
            suppliedFieldsType_.put(a_.getFieldName(), a_.getResultClass());
        }
        if (filter_.size() == 1 && suppliedFields_.isEmpty()) {
            if (fieldsTypes_.size() == 1) {
                //guess the unique field
                ClassArgumentMatching arg_ = filter_.first().getResultClass();
                String paramName_ = fieldsTypes_.getValue(0);
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                if (!PrimitiveTypeUtil.canBeUseAsArgument(false, param_, arg_, _conf)) {
                    //ERROR
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = PrimitiveTypeUtil.getQuickComponentType(param_);
                        if (PrimitiveTypeUtil.canBeUseAsArgument(false, c_, arg_, _conf)) {
                            fieldNames.put(fieldsTypes_.getKey(0), paramName_);
                            setResultClass(new ClassArgumentMatching(className));
                            return;
                        }
                    }
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    Mapping mapping_ = new Mapping();
                    mapping_.setMapping(vars_);
                    mapping_.setArg(arg_);
                    mapping_.setParam(param_);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(cast_);
                }
                fieldNames.put(fieldsTypes_.getKey(0),EMPTY_STRING);
                setResultClass(new ClassArgumentMatching(className));
                return;
            }
        }
        int nb_ = suppliedFields_.size();
        suppliedFields_.removeDuplicates();
        if (nb_ != suppliedFields_.size()) {
            //ERROR
            BadConstructorCall cast_ = new BadConstructorCall();
            cast_.setLocalOffset(_conf.getCurrentLocation());
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(cast_);
        }
        for (String f: suppliedFields_) {
            if (!fieldsOpt_.contains(f)) {
                //ERROR
                UndefinedFieldError cast_ = new UndefinedFieldError();
                cast_.setId(f);
                cast_.setClassName(className);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
            fieldNames.put(f,EMPTY_STRING);
        }
        for (EntryCust<String, Boolean> e: fieldsOpt_.entryList()) {
            if (e.getValue()) {
                continue;
            }
            if (!suppliedFields_.containsStr(e.getKey())) {
                //ERROR
                BadConstructorCall cast_ = new BadConstructorCall();
                cast_.setLocalOffset(_conf.getCurrentLocation());
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
        }
        for (EntryCust<String, ClassArgumentMatching> e: suppliedFieldsType_.entryList()) {
            for (EntryCust<String, String> f: fieldsTypes_.entryList()) {
                if (!StringList.quickEq(e.getKey(), f.getKey())) {
                    continue;
                }
                String paramName_ = f.getValue();
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                ClassArgumentMatching arg_ = e.getValue();
                if (!PrimitiveTypeUtil.canBeUseAsArgument(false, param_, arg_, _conf)) {
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = PrimitiveTypeUtil.getQuickComponentType(param_);
                        if (PrimitiveTypeUtil.canBeUseAsArgument(false, c_, arg_, _conf)) {
                            fieldNames.put(e.getKey(), paramName_);
                            continue;
                        }
                    }
                    //ERROR
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    Mapping mapping_ = new Mapping();
                    mapping_.setMapping(vars_);
                    mapping_.setArg(arg_);
                    mapping_.setParam(param_);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(cast_);
                }
            }
        }
        setResultClass(new ClassArgumentMatching(className));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
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
            if (_conf.getContextEl().hasException()) {
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
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }

    Argument getArgument(Argument _previous,CustList<Argument> _arguments,
            ExecutableCode _conf) {
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
        if (array) {
            int nbCh_ = chidren_.size();
            int[] args_;
            args_ = new int[CustList.ONE_ELEMENT];
            args_[CustList.FIRST_INDEX] = chidren_.size();
            Argument a_ = new Argument();
            Numbers<Integer> dims_;
            dims_ = new Numbers<Integer>();
            dims_.add(nbCh_);
            String className_ = PrimitiveTypeUtil.getQuickComponentType(className);
            Struct str_ = PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf);
            for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                Argument chArg_ = _arguments.get(i);
                ArrOperation.setCheckedElement(str_, i, chArg_, _conf);
                if (_conf.getContextEl().hasExceptionOrFailInit()) {
                    return a_;
                }
            }
            a_.setStruct(str_);
            return a_;
        }
        if (possibleInitClass) {
            String base_ = Templates.getIdFromAllTypes(className);
            if (InvokingOperation.hasToExit(_conf, base_)) {
                return Argument.createVoid();
            }
        }
        return instancePrepareAnnotation(_conf, className, fieldNames, _arguments);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    boolean isCallMethodCtor(Analyzable _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = methodName.trim().substring(new_.length());
        className_ = ContextEl.removeDottedSpaces(className_);
        return !className_.startsWith(ARR);
    }
}
