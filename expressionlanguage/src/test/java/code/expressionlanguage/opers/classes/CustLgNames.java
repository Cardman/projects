package code.expressionlanguage.opers.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.classes.NativeForEach;
import code.expressionlanguage.methods.AbstractForEachLoop;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
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
    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private CustList<OperationNode> expsIterator;
    private CustList<OperationNode> expsHasNext;
    private CustList<OperationNode> expsNext;
    private String aliasSimpleIteratorType = "code.util.SimpleItr";
    private String aliasSimpleIterableType = "code.util.ints.SimpleIterable";
    private String aliasCountable = "code.util.ints.Countable";
    @Override
    public void buildOther() {
        StringMap<StandardField> fields_;
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
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasStringList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        ctor_ = new StandardConstructor(new StringList(getAliasString()), true, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("get", params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasStringList, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        StandardInterface stdi_ = new StandardInterface(aliasSimpleIterableType, methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasSimpleIterator(), params_, aliasSimpleIteratorType, false, MethodModifier.ABSTRACT, stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasSimpleIterableType, stdi_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdi_ = new StandardInterface(aliasCountable, methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasSize(), params_, getAliasPrimInteger(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(getAliasGet(), params_, getAliasObject(), false, MethodModifier.ABSTRACT,stdi_);
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
        String simpleIterator_ = getAliasSimpleIterator();
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
    public String getIteratorVar() {
        return iteratorVar;
    }
    public String getHasNextVar() {
        return hasNextVar;
    }
    public String getNextVar() {
        return nextVar;
    }
    public CustList<OperationNode> getExpsIterator() {
        return expsIterator;
    }
    public CustList<OperationNode> getExpsHasNext() {
        return expsHasNext;
    }
    public CustList<OperationNode> getExpsNext() {
        return expsNext;
    }
    @Override
    public AbstractForEachLoop newForeachLoop(ContextEl _importingPage, int _indexChild,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        return new NativeForEach(_importingPage, _indexChild, _m, _className, _variable, _expression, _classIndex, _label, _offset);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        Object instance_ = _instance.getInstance();
        if (instance_ instanceof Countable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasIsEmpty())) {
                res_.setResult(new BooleanStruct(((Countable) instance_).isEmpty()));
                return res_;
            }
            if (StringList.quickEq(name_, getAliasSize())) {
                res_.setResult(new IntStruct(((Countable) instance_).size()));
                return res_;
            }
            if (StringList.quickEq(name_, getAliasGet())) {
                res_.setResult(StdStruct.wrapStd(((Countable) instance_).get((Integer)_args[0]), _cont));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof SimpleIterable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasSimpleIterator())) {
                String typeInst_ = getStructClassName(_instance, _cont);
                String it_ = getStandards().getVal(typeInst_).getIterative();
                res_.setResult(StdStruct.newInstance(((SimpleIterable) instance_).simpleIterator(), StringList.concat(aliasSimpleIteratorType,Templates.TEMPLATE_BEGIN,it_,Templates.TEMPLATE_END)));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof SimpleItr) {
            String name_ = _method.getConstraints().getName();
            return prIterator(_cont, name_, _instance);
        }
        return res_;
    }
    ResultErrorStd prIterator(ContextEl _cont, String _name, Struct _struct) {
        ResultErrorStd result_ = new ResultErrorStd();
        Object instance_ = _struct.getInstance();
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
            ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), aliasStringList)) {
            res_.setResult(new StdStruct(new StringList(), aliasStringList));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        return res_;
    }
    @Override
    public ResultErrorStd setOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        return res_;
    }
}
