package code.expressionlanguage.opers;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.AbstractClassConstructorException;
import code.expressionlanguage.exceptions.BadIndexTypeException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.EmptyArrayDimensionsException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.IllegalClassConstructorException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NegativeSizeException;
import code.expressionlanguage.exceptions.NotArrayException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.Struct;
import code.serialize.exceptions.BadAccessException;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class InstanceOperation extends InvokingOperation {

    private boolean possibleInitClass;

    private String methodName;

    private Constructor<?> contructor;

    private ConstructorId constId;

    private String fieldName = EMPTY_STRING;

    public InstanceOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
        analyzeCommon(_nodes, _conf, _fieldName, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _fieldName, String _op) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+2);
        className_ = StringList.removeAllSpaces(className_);
        if (!className_.startsWith(ARR) && className_.endsWith(ARR_DYN)) {
            int len_ = className_.length();
            throw new NotArrayException(className_.substring(0, len_-ARR_DYN.length())+RETURN_LINE+_conf.joinPages());
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
        if (!isStaticBlock()) {
            String glClass_ = _conf.getLastPage().getGlobalClass();
            realClassName_ = Templates.format(glClass_, realClassName_, classes_);
        }
        if (realClassName_.startsWith(ARR)) {
            if (chidren_.isEmpty() && !elts_) {
                throw new EmptyArrayDimensionsException(realClassName_+RETURN_LINE+_conf.joinPages());
            }
            if (!elts_) {
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    if (!o.getResultClass().isNumericInt()) {
                        ClassArgumentMatching cl_ = o.getResultClass();
                        throw new BadIndexTypeException(cl_+RETURN_LINE+_conf.joinPages());
                    }
                }
            } else {
                String eltType_ = PrimitiveTypeUtil.getQuickComponentType(realClassName_);
                for (OperationNode o: chidren_) {
                    setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
                    String argType_ = o.getResultClass().getName();
                    if (!PrimitiveTypeUtil.canBeUseAsArgument(eltType_, argType_, classes_)) {
                        throw new DynamicCastClassException(argType_+RETURN_LINE+eltType_+RETURN_LINE+_conf.joinPages());
                    }
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
            checkExist(_conf, realClassName_, false, 0);
            if (!elts_) {
                setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, chidren_.size())));
                return;
            }
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(realClassName_, CustList.ONE_ELEMENT)));
            return;
        }
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
        boolean intern_ = true;
        if (!isIntermediateDotted()) {
            intern_ = false;
            if (StringList.isWord(realClassName_)) {
                needGlobalArgument();
                ClassArgumentMatching arg_ = getPreviousResultClass();
                if (arg_ == null) {
                    throw new NullGlobalObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
                }
                //TODO wrap getDeclaredClasses
                for (Class<?> c:arg_.getDeclaredClasses()) {
                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
                        intern_ = true;
                        if (!Modifier.isStatic(c.getModifiers())) {
                            firstArgs_.add(CustList.FIRST_INDEX, arg_);
                        }
                        realClassName_ = arg_.getName()+INTERN_CLASS+realClassName_;
                        break;
                    }
                }
            }
            analyzeCtor(_nodes, _conf, _fieldName, _op, realClassName_, firstArgs_, intern_);
            return;
        }
        ClassArgumentMatching arg_ = getPreviousResultClass();
        if (arg_ == null) {
            throw new NullGlobalObjectException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        firstArgs_.add(CustList.FIRST_INDEX, arg_);
        realClassName_ = arg_.getName()+INTERN_CLASS+realClassName_;
        analyzeCtor(_nodes, _conf, _fieldName, _op, realClassName_, firstArgs_, intern_);
    }

    void analyzeCtor(CustList<OperationNode> _nodes, ContextEl _conf, String _fieldName, String _op, String _realClassName, CustList<ClassArgumentMatching> _firstArgs, boolean _intern) {
        Classes classes_ = _conf.getClasses();
        boolean staticBlock_ = isStaticBlock();
        String realClassName_ = _realClassName;
        if (StringList.quickEq(realClassName_, OperationNode.VOID_RETURN)) {
            throw new VoidArgumentException(_conf.joinPages());
        }
        if (classes_ != null) {
            ClassMetaInfo custClass_ = null;
            custClass_ = classes_.getClassMetaInfo(realClassName_);
            if (custClass_ != null) {
                if (custClass_.isAbstractType() && custClass_.getCategory() != ClassCategory.ENUM) {
                    throw new AbstractClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
                }
                if (custClass_.getCategory() == ClassCategory.INTERFACE) {
                    throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
                }
                if (custClass_.getCategory() == ClassCategory.ENUM) {
                    if (_fieldName.isEmpty() || _nodes.last() != this) {
                        throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
                    }
                    fieldName = _fieldName;
                }
            }
        }
        constId = getDeclaredCustConstructor(_conf, staticBlock_, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(_firstArgs));
        if (constId != null) {
            String glClass_ = _conf.getLastPage().getGlobalClass();
            CustList<ConstructorBlock> ctors_ = classes_.getConstructorBodiesByFormattedId(realClassName_, constId);
            String curClassBase_ = null;
            if (glClass_ != null) {
                curClassBase_ = StringList.getAllTypes(glClass_).first();
            }
            if (!ctors_.isEmpty() && !classes_.canAccess(curClassBase_, ctors_.first())) {
                ConstructorBlock ctr_ = ctors_.first();
                throw new BadAccessException(ctr_.getId().getSignature()+RETURN_LINE+_conf.joinPages());
            }
            possibleInitClass = true;
            setResultClass(new ClassArgumentMatching(realClassName_));
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(realClassName_)) {
            throw new PrimitiveTypeException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        Class<?> cl_;
        try {
            cl_ = PrimitiveTypeUtil.getSingleNativeClass(realClassName_);
        } catch (RuntimeClassNotFoundException _0_) {
            throw new RuntimeClassNotFoundException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        if (cl_.isEnum()) {
            throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        if (cl_.isInterface()) {
            throw new IllegalClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        if (_intern && isStaticAccess() && !Modifier.isStatic(cl_.getModifiers())) {
            throw new StaticAccessException(_conf.joinPages());
        }
        if (Modifier.isAbstract(cl_.getModifiers())) {
            throw new AbstractClassConstructorException(realClassName_+RETURN_LINE+_conf.joinPages());
        }
        Constructor<?> const_ = getDeclaredConstructor(_conf, staticBlock_, 0, new ClassArgumentMatching(realClassName_), ClassArgumentMatching.toArgArray(_firstArgs));
        if (!canBeUsed(const_, _conf)) {
            throw new BadAccessException(const_+RETURN_LINE+_conf.joinPages());
        }
        contructor = const_;
        setAccess(contructor, _conf);
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
        Argument a_ = getArgument(_nodes.getVal(this).getPreviousArgument(), arguments_, false, _conf, _op);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/
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
        Argument a_ = getArgument(getPreviousArgument(), arguments_, true, _conf, _op);
        setSimpleArgument(a_, _conf);
    }

    Argument getArgument(Argument _previous,CustList<Argument> _arguments,
            boolean _processInit, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int nbCh_ = chidren_.size();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+2);
        className_ = StringList.removeAllSpaces(className_);
        boolean elts_ = false;
        String realClassName_;
        String instanceClassName_;
        if (className_.endsWith(ARR_DYN)) {
            elts_ = true;
            int len_ = className_.length();
            realClassName_ = className_.substring(0, len_-ARR_DYN.length());
        } else {
            realClassName_ = className_;
        }
        if (!isStaticBlock()) {
            String glClass_ = _conf.getLastPage().getGlobalClass();
            realClassName_ = Templates.format(glClass_, realClassName_, _conf.getClasses());
        }
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
                        throw new NullObjectException(i_+RETURN_LINE+_conf.joinPages());
                    }
                    int dim_ = n_.intValue();
                    if (dim_ < 0) {
                        throw new NegativeSizeException(String.valueOf(dim_)+RETURN_LINE+i_+RETURN_LINE+_conf.joinPages());
                    }
                    args_[i_] = dim_;
                    i_++;
                }
            }
            realClassName_ = realClassName_.substring(ARR.length());
            boolean cust_ = false;
            Classes classes_ = _conf.getClasses();
            ClassMetaInfo custClass_ = null;
            instanceClassName_ = realClassName_;
            if (classes_ != null) {
                DimComp clCurName_ = PrimitiveTypeUtil.getQuickComponentBaseType(realClassName_);
                custClass_ = classes_.getClassMetaInfo(clCurName_.getComponent());
                if (custClass_ != null) {
                    cust_ = true;
                    int dim_ = clCurName_.getDim();
                    instanceClassName_ = PrimitiveTypeUtil.getPrettyArrayType(Struct.class.getName(), dim_);
                }
            }
            Argument a_ = new Argument();
            if (elts_) {
                if (cust_) {
                    Struct[] array_ = new Struct[nbCh_];
                    for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                        Argument chArg_ = _arguments.get(i);
                        array_[i] = chArg_.getStruct();
                    }
                    String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(realClassName_, args_.length);
                    a_.setStruct(new Struct(array_,clArr_));
                    return a_;
                }
                Object array_ = newClassicArray(_conf, instanceClassName_, realClassName_, args_);
                Struct strArr_ = new Struct(array_);
                for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                    Argument chArg_ = _arguments.get(i);
                    ArrOperation.setCheckedElement(strArr_, i, chArg_, _conf);
                }
                a_.setStruct(strArr_);
                return a_;
            } else if (cust_) {
                Numbers<Integer> dims_;
                dims_ = new Numbers<Integer>();
                for (int d: args_) {
                    dims_.add(d);
                }
                a_.setStruct(PrimitiveTypeUtil.newCustomArray(realClassName_, dims_));
                return a_;
            } else {
                Object o_ = newClassicArray(_conf, instanceClassName_, realClassName_, args_);
                a_.setStruct(new Struct(o_));
                return a_;
            }
        }
        if (possibleInitClass) {
            if (!_conf.getClasses().isInitialized(realClassName_)) {
                _conf.getClasses().initialize(realClassName_);
                if (!_processInit) {
                    throw new NotInitializedClassException(realClassName_);
                }
                ProcessXmlMethod.initializeClass(realClassName_, _conf);
            }
        }
        CustList<Argument> firstArgs_ = listArguments(chidren_, _arguments, _conf, true);
        if (!isIntermediateDotted()) {
            Class<?> class_ = null;
            if (StringList.isWord(realClassName_)) {
                for (Class<?> c:getPreviousResultClass().getDeclaredClasses()) {
                    if (StringList.quickEq(c.getSimpleName(), realClassName_)) {
                        class_ = c;
                        break;
                    }
                }
            }
            return getArg(_previous, class_, firstArgs_, _processInit, _conf);
        }
        return getArg(_previous, contructor.getDeclaringClass(), firstArgs_, _processInit, _conf);
    }
    Argument getArg(Argument _previous, Class<?> _class,CustList<Argument> _arguments,
            boolean _processInit, ContextEl _conf) {
        Argument needed_ = null;
        if (_class != null && !Modifier.isStatic(_class.getModifiers())) {
            Argument arg_ = _previous;
            if (arg_.isNull()) {
                throw new NullObjectException(_class.getName()+RETURN_LINE+_conf.joinPages());
            }
            needed_ = arg_;
            _arguments.add(CustList.FIRST_INDEX, arg_);
        }
        if (constId != null) {
            String className_ = constId.getName();
            if (!isStaticBlock()) {
                String glClass_ = _conf.getLastPage().getGlobalClass();
                className_ = Templates.format(glClass_, className_, _conf.getClasses());
            }
            StringList params_ = new StringList();
            for (String c: constId.getParametersTypes()) {
                String class_ = c;
                if (!isStaticBlock()) {
                    String glClass_ = _conf.getLastPage().getGlobalClass();
                    class_ = Templates.format(glClass_, class_, _conf.getClasses());
                }
                params_.add(class_);
            }
            checkArgumentsForInvoking(_conf, params_, getObjects(Argument.toArgArray(_arguments)));
            ConstructorId cid_;
            if (!isStaticBlock()) {
                String glClass_ = _conf.getLastPage().getGlobalClass();
                cid_ = constId.format(glClass_, _conf.getClasses());
            } else {
                cid_ = constId;
            }
            if (_processInit) {
                return ProcessXmlMethod.instanceArgument(className_, needed_, cid_, _arguments, _conf);
            }
            StringList called_ = _conf.getLastPage().getCallingConstr().getCalledConstructors();
            throw new CustomFoundConstructorException(className_, fieldName, called_, cid_, needed_, _arguments, InstancingStep.NEWING);
        }
        return newInstance(_conf, needed_, 0, contructor, Argument.toArgArray(_arguments));
    }
    static Object newClassicArray(ContextEl _conf, String _instanceClassName, String _realClassName,int[] _args) {
        Class<?> cl_;
        try {
            if (PrimitiveTypeUtil.isPrimitive(_instanceClassName)) {
                cl_ = PrimitiveTypeUtil.getPrimitiveClass(_instanceClassName);
            } else {
                cl_ = PrimitiveTypeUtil.getSingleNativeClass(_instanceClassName);
            }
        } catch (RuntimeClassNotFoundException _0) {
            throw new RuntimeClassNotFoundException(_realClassName+RETURN_LINE+_conf.joinPages());
        }
        return Array.newInstance(cl_, _args);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    boolean isCallMethodCtor() {
        String className_ = methodName.trim().substring(INSTANCE.length()+2);
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
