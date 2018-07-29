package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.IllegalCallCtorByType;
import code.expressionlanguage.methods.util.UndefinedFieldError;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class AnnotationInstanceOperation extends InvokingOperation {

    private boolean possibleInitClass;

    private String methodName;

    private String className;
    private StringList fieldNames = new StringList();
    private boolean array;

    public AnnotationInstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
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
        String className_ = methodName.trim().substring(AROBASE.length());
        className_ = className_.trim();
        String realClassName_ = className_;
        if (!methodName.trim().startsWith(AROBASE)) {
            array = true;
            StringList subclasses_ = new StringList();
            boolean existNull_ = false;
            for (OperationNode o: chidren_) {
                String argType_ = o.getResultClass().getName();
                if (argType_.isEmpty()) {
                    existNull_ = true;
                    continue;
                }
                subclasses_.add(argType_);
            }
            subclasses_ = PrimitiveTypeUtil.getSubclasses(subclasses_, _conf);
            if (subclasses_.size() != 1 || StringList.quickEq(subclasses_.first(), _conf.getStandards().getAliasObject())) {
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setRc(_conf.getCurrentLocation());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
                un_.setOperands(subclasses_);
                _conf.getClasses().addError(un_);
                LgNames stds_ = _conf.getStandards();
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
                return;
            }
            String eltType_ = subclasses_.first();
            if (existNull_) {
                eltType_ = PrimitiveTypeUtil.toWrapper(eltType_, true, _conf.getStandards());
            }
            if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                for (OperationNode o: chidren_) {
                    o.getResultClass().setUnwrapObject(eltType_);
                }
            }
            realClassName_ = eltType_;
            className = realClassName_;
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
        setStaticAccess(_conf.isStaticContext());
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

        realClassName_ = _conf.resolveCorrectType(realClassName_);
        GeneType g_ = _conf.getClassBody(realClassName_);
        if (g_ == null) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(realClassName_);
            call_.setFileName(_conf.getCurrentFileName());
            call_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(call_);
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        className = realClassName_;
        possibleInitClass = !_conf.getOptions().isInitializeStaticClassFirst();
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
        StringMap<String> suppliedFieldsType_ = new StringMap<String>();
        boolean exist_ = false;
        for (OperationNode o: filter_) {
            if (!(o instanceof AssocationOperation)) {
                exist_ = true;
                continue;
            }
            AssocationOperation a_ = (AssocationOperation) o;
            suppliedFields_.add(a_.getFieldName());
            suppliedFieldsType_.put(a_.getFieldName(), a_.getResultClass().getName());
        }
        if (exist_ && filter_.size() == 1 && suppliedFields_.isEmpty()) {
            //guess the unique field
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
                cast_.setClassName(realClassName_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
            fieldNames.add(f);
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
        for (EntryCust<String, String> e: suppliedFieldsType_.entryList()) {
            String param_ = fieldsTypes_.getVal(e.getKey());
            String arg_ = e.getValue();
            if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, arg_, _conf)) {
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
        setResultClass(new ClassArgumentMatching(realClassName_));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (array) {
            for (OperationNode o: chidren_) {
                arguments_.add(o.getArgument());
            }
            int nbCh_ = chidren_.size();
            int[] args_;
            args_ = new int[CustList.ONE_ELEMENT];
            args_[CustList.FIRST_INDEX] = chidren_.size();
            Argument a_ = new Argument();
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
        }
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
            args_ = new int[CustList.ONE_ELEMENT];
            args_[CustList.FIRST_INDEX] = chidren_.size();
            Argument a_ = new Argument();
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
        if (possibleInitClass) {
            String base_ = Templates.getIdFromAllTypes(className_);
            if (InvokingOperation.hasToExit(_conf, base_)) {
                return Argument.createVoid();
            }
        }
        return instancePrepareAnnotation(_conf, className_, fieldNames, _arguments);
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
