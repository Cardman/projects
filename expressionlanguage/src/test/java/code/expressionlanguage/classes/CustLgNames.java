package code.expressionlanguage.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.AbstractForEachLoop;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.NativeIterableAnalysisResult;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.SimpleItr;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Countable;
import code.util.ints.SimpleIterable;

public final class CustLgNames extends LgNames {

    private String aliasStringList = "code.util.StringList";
    private String aliasComposite = "code.expressionlanguage.classes.Composite";
    private String aliasInheritedComposite = "code.expressionlanguage.classes.InheritedComposite";
    private String aliasFailMethods = "code.expressionlanguage.classes.FailMethods";
    private String aliasNotRead = "NOT_READ";
    private String aliasStrangeInit = "code.expressionlanguage.classes.StrangeInit";
    private String aliasFail = "fail";
    private String aliasBeanOne = "code.expressionlanguage.classes.BeanOne";
    private String aliasInts = "code.expressionlanguage.classes.Ints";
    private String aliasAdd = "add";
    private String aliasSize = "size";
    private String aliasIntegerField = "integer";
    private String aliasCompositeField = "composite";
    private String aliasGetList = "getList";
    private String aliasRemoveAndExistAfter = "removeAndExistAfter";
    private String aliasGeneObjects = "code.expressionlanguage.classes.GeneObjects";
    private String aliasPickableList = "code.expressionlanguage.classes.PickableList";
    private String aliasGetOverridenOne = "getOverridenOne";
    private String aliasGetOverridenTwo = "getOverridenTwo";
    private String aliasGetOverridenThree = "getOverridenThree";
    private String aliasGetOverridenFour = "getOverridenFour";
    private String aliasGetOverridenFive = "getOverridenFive";
    private String aliasGetOverridenSix = "getOverridenSix";
    private String aliasSetPrivateInt = "setPrivateInt";
    private String aliasGetPrivateInt = "getPrivateInt";
    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private CustList<ExecOperationNode> expsIterator;
    private CustList<ExecOperationNode> expsHasNext;
    private CustList<ExecOperationNode> expsNext;
    private String aliasSimpleIteratorType = "code.util.SimpleItr";
    private String aliasSimpleIterableType = "code.util.ints.SimpleIterable";

    private String aliasGet = "get";
    private String aliasSimpleIterator = "iterator";

    @Override
    public void buildOther() {
        StringMap<StandardField> fields_;
        StandardField field_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList(getAliasInteger());
        stdcl_ = new StandardClass(aliasInts, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        stdcl_.setIterative(getAliasInteger());
        stdcl_.getDirectInterfaces().add(aliasSimpleIterableType);
        std_ = stdcl_;
        getStandards().put(aliasInts, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPickableList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetList, params_, aliasGeneObjects, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveAndExistAfter, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasPickableList, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasGeneObjects, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSize, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
//        params_ = new StringList(aliasSimpleIteratorType);
//        method_ = new StandardMethod(aliasSimpleIterator, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, aliasGeneObjects);
//        methods_.put(method_.getId(), method_);
        stdcl_.setIterative(getAliasObject());
        std_ = stdcl_;
        getStandards().put(aliasGeneObjects, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
//        params_ = new StringList();
//        method_ = new StandardMethod(getAliasNext(), params_, getAliasObject(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
//        methods_.put(method_.getId(), method_);
//        params_ = new StringList();
//        method_ = new StandardMethod(getAliasHasNext(), params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, aliasSimpleIteratorType);
//        methods_.put(method_.getId(), method_);
        stdcl_ = new StandardClass(aliasStringList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        ctor_ = new StandardConstructor(new StringList(getAliasString()), true, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("get", params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasStringList, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasComposite, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasNumber());
        method_ = new StandardMethod(aliasGetOverridenOne, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasGetOverridenTwo, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasGetOverridenTwo, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimDouble());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasDouble());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenThree, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenFour, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenFour, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimDouble());
        method_ = new StandardMethod(aliasGetOverridenFive, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenFive, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasDouble());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod(aliasGetOverridenSix, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetPrivateInt, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrivateInt, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        String aliasGetInteger = "getInteger";
        method_ = new StandardMethod(aliasGetInteger, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        //aliasGetOverridenOne
        field_ = new StandardField(aliasIntegerField, getAliasPrimInteger(), false, false, stdcl_);
        fields_.put(aliasIntegerField, field_);
        std_ = stdcl_;
        getStandards().put(aliasComposite, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasInheritedComposite, fields_, constructors_, methods_, aliasComposite, MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().put(aliasInheritedComposite, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasFailMethods, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFail, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasFailMethods, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasStrangeInit, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFail, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        fields_.put(aliasNotRead, new StandardField(aliasNotRead, getAliasString(), true, true, stdcl_));
        std_ = stdcl_;
        getStandards().put(aliasStrangeInit, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdcl_ = new StandardClass(aliasBeanOne, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        field_ = new StandardField(aliasCompositeField, aliasComposite, false, false, stdcl_);
        fields_.put(aliasCompositeField, field_);
        std_ = stdcl_;
        getStandards().put(aliasBeanOne, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        StandardInterface stdi_ = new StandardInterface(aliasSimpleIterableType, methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSimpleIterator, params_, aliasSimpleIteratorType, false, MethodModifier.ABSTRACT, stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasSimpleIterableType, stdi_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        String aliasCountable = "code.util.ints.Countable";
        stdi_ = new StandardInterface(aliasCountable, methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSize, params_, getAliasPrimInteger(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGet, params_, getAliasObject(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasCountable, std_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdcl_ = new StandardClass(aliasSimpleIteratorType, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasNext(), params_, getAliasObject(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasHasNext(), params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasSimpleIteratorType, std_);
    }
    @Override
    public void buildIterable(ContextEl _context) {
        super.buildIterable(_context);
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(aliasSimpleIterableType);
        _context.getInternVars().put(locName_, locVar_);
        iteratorVar = locName_;
        String simpleIterator_ = aliasSimpleIterator;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(simpleIterator_,PARS));
        expsIterator = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(aliasSimpleIteratorType);
        _context.getInternVars().put(locName_, locVar_);
        hasNextVar = locName_;
        String hasNext_ = getAliasHasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        expsHasNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(aliasSimpleIteratorType);
        _context.getInternVars().put(locName_, locVar_);
        nextVar = locName_;
        String next_ = getAliasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        expsNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
    }
    
    public String getIteratorVar() {
        return iteratorVar;
    }
    public String getHasNextVar() {
        return hasNextVar;
    }
    public String getNextVar() {
        return nextVar;
    }
    public CustList<ExecOperationNode> getExpsIterator() {
        return expsIterator;
    }
    public CustList<ExecOperationNode> getExpsHasNext() {
        return expsHasNext;
    }
    public CustList<ExecOperationNode> getExpsNext() {
        return expsNext;
    }
    @Override
    public IterableAnalysisResult getCustomType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        Boolean nativeCmp_ = null;
        for (String f: _names) {
            String type_ = getIterableFullTypeByStds(f, _context);
            String iterable_ = getAliasIterable();
            if (type_ == null) {
                type_ = Templates.getFullTypeByBases(f, iterable_, _context);
                nativeCmp_ = false;
            } else {
                nativeCmp_ = true;
            }
            if (type_ != null) {
                out_.add(type_);
            }
        }
        out_.removeDuplicates();
        return new NativeIterableAnalysisResult(out_, nativeCmp_);
    }
    static String getIterableFullTypeByStds(String _subType, ContextEl _context) {
        String baseSubType_ = _subType;
        LgNames lgNames_ = _context.getStandards();
        StandardType std_ = lgNames_.getStandards().getVal(baseSubType_);
        if (std_ == null) {
            return null;
        }
        if (std_.getIterative().isEmpty()) {
            return null;
        }
        return StringList.concat(lgNames_.getAliasIterable(),"<",std_.getIterative(),">");
    }
    @Override
    public AbstractForEachLoop newForeachLoop(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        return new NativeForEach(_importingPage, _m, _className, _variable, _expression, _classIndex, _label, _offset);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        Object instance_ = ((RealInstanceStruct)_instance).getInstance();
        ResultErrorStd res_ = new ResultErrorStd();
        if (instance_ instanceof Countable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasIsEmpty())) {
                res_.setResult(new BooleanStruct(((Countable) instance_).isEmpty()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasSize)) {
                res_.setResult(new IntStruct(((Countable) instance_).size()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasGet)) {
                res_.setResult(StdStruct.wrapStd(((Countable) instance_).get(((NumberStruct) _args[0]).getInstance().intValue()), _cont));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof SimpleIterable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, aliasSimpleIterator)) {
                String typeInst_ = getStructClassName(_instance, _cont);
                String it_ = getStandards().getVal(typeInst_).getIterative();
                res_.setResult(StdStruct.newInstance(((SimpleIterable) instance_).simpleIterator(), StringList.concat(aliasSimpleIteratorType,Templates.TEMPLATE_BEGIN,it_,Templates.TEMPLATE_END)));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof SimpleItr) {
            String name_ = _method.getConstraints().getName();
            return prIterator(_cont, name_, _instance);
        }
        if (StringList.quickEq(_method.getClassName(), aliasInts)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasAdd)) {
                Integer arg_ = ((NumberStruct) _args[0]).getInstance().intValue();
                ((Ints)instance_).add(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasGeneObjects)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasAdd)) {
                Object arg_ = _args[0];
                ((GeneObjects)instance_).add(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasSize)) {
                res_.setResult(new IntStruct(((GeneObjects)instance_).size()));
                return res_;
            }

        }
        if (StringList.quickEq(_method.getClassName(), aliasPickableList)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetList)) {
                res_.setResult(new StdStruct(((PickableList)instance_).getList(), aliasGeneObjects));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasRemoveAndExistAfter)) {
                Integer arg_ = ((NumberStruct) _args[0]).getInstance().intValue();
                res_.setResult(new BooleanStruct(((PickableList)instance_).removeAndExistAfter(arg_)));
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasComposite)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenOne)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasObject())) {
                    Object arg_ = _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasNumber())) {
                    Number arg_ = (Number) _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasString())) {
                    String arg_ = ((StringStruct) _args[0]).getInstance();
                    String resLoc_ = ((Composite)instance_).getOverridenOne(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenTwo)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasObject())) {
                    Object arg_ = _args[0];
                    String resLoc_ = ((Composite)instance_).getOverridenTwo(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasString())) {
                    String arg_ = ((StringStruct) _args[0]).getInstance();
                    String resLoc_ = ((Composite)instance_).getOverridenTwo(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenThree)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).getInstance().doubleValue();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).getInstance().longValue();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).getInstance().doubleValue();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_.doubleValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).getInstance().longValue();
                    String resLoc_ = ((Composite)instance_).getOverridenThree(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenFour)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).getInstance().longValue();
                    String resLoc_ = ((Composite)instance_).getOverridenFour(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).getInstance().longValue();
                    String resLoc_ = ((Composite)instance_).getOverridenFour(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenFive)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).getInstance().longValue();
                    String resLoc_ = ((Composite)instance_).getOverridenFive(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).getInstance().doubleValue();
                    String resLoc_ = ((Composite)instance_).getOverridenFive(arg_.doubleValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetOverridenSix)) {
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasPrimLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).getInstance().longValue();
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_.longValue());
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasLong())) {
                    Long arg_ = ((NumberStruct) _args[0]).getInstance().longValue();
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
                if (StringList.quickEq(_method.getConstraints().getParametersTypes().first(), getAliasDouble())) {
                    Double arg_ = ((NumberStruct) _args[0]).getInstance().doubleValue();
                    String resLoc_ = ((Composite)instance_).getOverridenSix(arg_);
                    res_.setResult(new StringStruct(resLoc_));
                    return res_;
                }
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasGetPrivateInt)) {
                res_.setResult(new IntStruct(((Composite)instance_).getPrivateInt()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), aliasSetPrivateInt)) {
                Integer arg_ = ((NumberStruct) _args[0]).getInstance().intValue();
                ((Composite)instance_).setPrivateInt(arg_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasFailMethods)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasFail)) {
                res_.setError(getAliasError());
                return res_;
            }
        }
        if (StringList.quickEq(_method.getClassName(), aliasStrangeInit)) {
            if (StringList.quickEq(_method.getConstraints().getName(), aliasFail)) {
                res_.setError(getAliasError());
                return res_;
            }
        }
        return res_;
    }
    ResultErrorStd prIterator(ContextEl _cont, String _name, Struct _struct) {
        ResultErrorStd result_ = new ResultErrorStd();
        Object instance_ = ((RealInstanceStruct) _struct).getInstance();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(_name, lgNames_.getAliasNext())) {
            Object resObj_ = ((SimpleItr)instance_).next();
            result_.setResult(StdStruct.wrapStd(resObj_, _cont));
            return result_;
        }
        result_.setResult(new BooleanStruct(((SimpleItr)instance_).hasNext()));
        return result_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), aliasInts)) {
            res_.setResult(new StdStruct(new Ints(), aliasInts));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasPickableList)) {
            res_.setResult(StdStruct.newInstance(new PickableList(), aliasPickableList));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasStringList)) {
            res_.setResult(new StdStruct(new StringList(), aliasStringList));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasInheritedComposite)) {
            res_.setResult(StdStruct.newInstance(new InheritedComposite(), aliasInheritedComposite));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasFailMethods)) {
            res_.setError(getAliasError());
            return res_;
        }
        if (StringList.quickEq(_method.getName(), aliasStrangeInit)) {
            res_.setError(getAliasError());
            return res_;
        }
        return super.getOtherResult(_cont, _method, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(_classField.getClassName(), aliasComposite)) {
            if (StringList.quickEq(fieldName_, aliasIntegerField)) {
                Composite cpt_ = (Composite) ((RealInstanceStruct)_instance).getInstance();
                res_.setResult(new IntStruct(cpt_.getInteger()));
                return res_;
            }
        }
        if (StringList.quickEq(_classField.getClassName(), aliasBeanOne)) {
            if (StringList.quickEq(fieldName_, aliasCompositeField)) {
                BeanOne cpt_ = (BeanOne) ((RealInstanceStruct)_instance).getInstance();
                res_.setResult(StdStruct.newInstance(cpt_.getComposite(), aliasComposite));
                return res_;
            }
        }
        if (StringList.quickEq(_classField.getClassName(), aliasStrangeInit)) {
            if (StringList.quickEq(fieldName_, aliasNotRead)) {
                res_.setError(getAliasError());
                return res_;
            }
        }
        return super.getOtherResult(_cont, _classField, _instance);
    }
    @Override
    public ResultErrorStd setOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(_classField.getClassName(), aliasComposite)) {
            if (StringList.quickEq(fieldName_, aliasIntegerField)) {
                Composite cpt_ = (Composite) ((RealInstanceStruct)_instance).getInstance();
                cpt_.setInteger(((NumberStruct) _value).getInstance().intValue());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        return super.setOtherResult(_cont, _classField, _instance, _value);
    }
    public String getAliasInts() {
        return aliasInts;
    }
    public String getAliasComposite() {
        return aliasComposite;
    }
}
