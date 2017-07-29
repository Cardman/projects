package code.expressionlanguage.opers;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.AbstractClassConstructorException;
import code.expressionlanguage.exceptions.AmbiguousChoiceCallingException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ArgumentsGroup;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdResult;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorInfo;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.FctConstraints;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodInfo;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.ParametersGroup;
import code.expressionlanguage.opers.util.ParametersGroupComparator;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.Struct;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.NoSuchDeclaredConstructorException;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.serialize.exceptions.RuntimeInstantiationException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.SortedNode;

public abstract class OperationNode implements SortedNode<OperationNode>, Operable {

    public static final String VOID_RETURN = "$void";
    public static final String METH_NAME = "name";
    public static final String METH_ORDINAL = "ordinal";
    public static final String METH_VALUES = "values";
    public static final String METH_VALUEOF = "valueOf";

    protected static final char ESCAPE_META_CHAR = '\\';
    protected static final char DELIMITER_CHAR = 39;
    protected static final char DELIMITER_STRING = 34;
    protected static final char UNICODE = 'u';
    protected static final char IND_FORM = 'f';
    protected static final char IND_LINE = 'n';
    protected static final char IND_LINE_FEED = 'r';
    protected static final char IND_TAB = 't';
    protected static final char IND_BOUND = 'b';
    protected static final char ARR_LEFT = '[';
    protected static final char ARR_RIGHT = ']';
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
    protected static final String PAR_RIGHT_STR = ")";
    protected static final char SEP_ARG = ',';
    protected static final char FIRST_VAR_ARG = '?';
    protected static final char GET_VAR = ';';
    protected static final char DOT_VAR = '.';
    protected static final char EXTERN_CLASS = '^';
    protected static final char INTERN_CLASS = '$';
    protected static final String SUPER_ACCESS = "super";
    protected static final String CURRENT = "this";
    protected static final String INSTANCE = "new";
    protected static final String STATIC_ACCESS = "static";
    protected static final String NULL_REF_STRING = "null";
    protected static final String TRUE_STRING = "true";
    protected static final String FALSE_STRING = "false";
    protected static final String INSTANCEOF = "instanceof";
    protected static final String BOOLEAN = "boolean";
    protected static final String CAST = "class";
    protected static final char MIN_ENCODE_DIGIT = '0';
    protected static final char MAX_ENCODE_DIGIT = '9';
    protected static final char MIN_ENCODE_LOW_LETTER = 'a';
    protected static final char MAX_ENCODE_LOW_LETTER = 'f';
    protected static final char MIN_ENCODE_UPP_LETTER = 'A';
    protected static final char MAX_ENCODE_UPP_LETTER = 'F';
    protected static final String GET_INDEX = ";;";
    protected static final String GET_CATCH_VAR = ";..";
    protected static final String GET_LOC_VAR = ";.";
    protected static final String GET_ATTRIBUTE = ";";
    protected static final char MATH_INTERPRET = '`';
    protected static final String GET_PARAM = ";.;";
    protected static final String GET_FIELD = ";;;";
    protected static final String CURRENT_INTANCE = "^this";
    protected static final String STATIC_CALL = "^^";

    protected static final String FCT = "(";

    protected static final String ARR = "[";

    protected static final String ARR_DYN = "[]";

    protected static final String DOT = ".";

    protected static final String NEG_BOOL = "!";

    protected static final String UNARY_PLUS = "+";

    protected static final String UNARY_MINUS = "-";

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String MOD = "%";

    protected static final String PLUS = "+";

    protected static final String MINUS = "-";

    protected static final String LOWER_EQ = "<=";

    protected static final String LOWER = "<";

    protected static final String GREATER_EQ = ">=";

    protected static final String GREATER = ">";

    protected static final String EQ = "=";

    protected static final String DIFF = "!=";

    protected static final String AND = "&";

    protected static final String OR = "|";
    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";
    protected static final String SPACE = " ";
    protected static final String RETURN_TAB = RETURN_LINE+"\t";

    protected static final String JAVA = "java";
    protected static final String CSHARP = "csharp";
    protected static final String JAVA_GET_CLASS = "getClass";
    protected static final String CSHARP_GET_TYPE = "GetType";

    private MethodOperation parent;

    private boolean initializedNextSibling;

    private OperationNode nextSibling;

    private boolean resetablePreviousArg = true;

    private boolean resetableArg = true;

    private Argument previousArgument;

    private Argument argument;

    private OperationsSequence operations;

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    //    private ImportingPage importingPage;

    private ContextEl conf;

    private final int indexChild;

    private boolean vararg;
    private boolean firstOptArg;
    private ClassArgumentMatching previousResultClass;
    private ClassArgumentMatching resultClass;
    private boolean calculatedLater;
    private boolean needPrevious;
    private boolean staticAccess;

    //    private boolean booleanPrio;

    //    private boolean childrenWithBoolPrio;

    OperationNode(String _el, int _indexInEl, ContextEl _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        conf = _importingPage;
        vararg = _el.trim().startsWith(String.valueOf(FIRST_VAR_ARG));
        //        firstOptArg = _el.trim().endsWith(String.valueOf(FIRST_VAR_ARG));
        firstOptArg = _op.isFirstOpt();
        indexChild = _indexChild;
    }

    //    OperationNode(String _el, int _indexInEl, ContextEl _importingPage, MethodOperation _m, OperationsSequence _op) {
    //        parent = _m;
    //        indexInEl = _indexInEl;
    //        operations = _op;
    //        conf = _importingPage;
    //        vararg = _el.trim().startsWith(String.valueOf(FIRST_VAR_ARG));
    ////        firstOptArg = _el.trim().endsWith(String.valueOf(FIRST_VAR_ARG));
    //        firstOptArg = _op.isFirstOpt();
    //    }
    //    public abstract void analyze(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting);
    public abstract void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _conf, boolean _enumContext, String _op);
    public abstract void analyzeRight(CustList<OperationNode> _nodes, ContextEl _conf, boolean _enumContext, String _op);
    public abstract void analyzeSetting(CustList<OperationNode> _nodes, ContextEl _conf, boolean _enumContext, String _op);
    //    public abstract void calculate(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting);
    public abstract void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf, String _op);
    public abstract void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf, String _op);
    public abstract void calculateSetting(CustList<OperationNode> _nodes, ContextEl _conf, String _op);
    //    public abstract Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf, Calculation _setting);
    public abstract Argument calculateLeft(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op);
    public abstract Argument calculateRight(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op);
    public abstract Argument calculateSetting(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op);


    public final void setRelativeOffsetPossibleLastPage(int _offset, ContextEl _cont) {
        if (_cont.isEmptyPages()) {
            return;
        }
        _cont.getLastPage().setOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public static void addRelativeToOffsetPossibleLastPage(int _offset, ContextEl _cont) {
        if (_cont.isEmptyPages()) {
            return;
        }
        _cont.getLastPage().addToOffset(_offset);
    }

    public static OperationNode createOperationNode(String _el, int _index, ContextEl _conf,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        String value_ = _el;
        //        System.out.println(value_);
        if (_op.getOperators().isEmpty()) {
            return new ConstantOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO) {
            if (_op.getFctName().trim().isEmpty()) {
                return new IdOperation(value_, _index, _conf, _indexChild, _m, _op);
            }
            if (_op.getFctName().trim().startsWith(EXTERN_CLASS+INSTANCE+DOT_VAR)) {
                return new InstanceOperation(value_, _index, _conf, _indexChild, _m, _op);
            }
            return new FctOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.ARR_OPER_PRIO) {
            return new ArrOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.DOT_PRIO) {
            return new DotOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.UNARY_PRIO) {
            int key_ = _op.getOperators().firstKey();
            if (StringList.quickEq(_op.getOperators().getVal(key_).trim(), NEG_BOOL)) {
                return new UnaryBooleanOperation(value_, _index, _conf, _indexChild, _m, _op);
            }
            return new UnaryOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.MULT_PRIO) {
            return new MultOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.ADD_PRIO) {
            return new AddOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.CMP_PRIO) {
            return new CmpOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.EQ_PRIO) {
            return new EqOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.AND_PRIO) {
            return new AndOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.OR_PRIO) {
            return new OrOperation(value_, _index, _conf, _indexChild, _m, _op);
        }
        return null;
    }

    //    boolean isDirectlyCalculable() {
    //        return isRealLeaf() && isFirstChild();
    //    }
    //    abstract boolean isFirstLeaf();
    abstract boolean isFirstChild();
    //    abstract boolean isRealLeaf();

    final boolean isAnalyzed() {
        return resultClass != null;
    }

    //    public final boolean isCalculated(Calculation _setting, IdMap<OperationNode, ArgumentsPair> _nodes) {
    //        if (isPossibleInitClass()) {
    //            return false;
    //        }
    //        if (_setting.getStep() == StepCalculation.SETTING) {
    //            if (calculatedLater) {
    //                return false;
    //            }
    //        }
    //        OperationNode op_ = this;
    //        while (op_ != null) {
    //            if (_nodes.getVal(op_).getArgument() != null) {
    //                return true;
    //            }
    //            op_ = op_.getParent();
    //        }
    //        return false;
    //    }

    public final boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        if (isPossibleInitClass()) {
            return false;
        }
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    public final boolean isCalculatedSetting(IdMap<OperationNode, ArgumentsPair> _nodes) {
        if (isPossibleInitClass()) {
            return false;
        }
        if (calculatedLater) {
            return false;
        }
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    public final boolean isCalculatedSetting() {
        if (isPossibleInitClass()) {
            return false;
        }
        if (calculatedLater) {
            return false;
        }
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }
    public final boolean isCalculated() {
        if (isPossibleInitClass()) {
            return false;
        }
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }
    //    public final boolean isCalculated(Calculation _setting) {
        //        if (isPossibleInitClass()) {
            //            //            Argument a_ = getArgument();
            //            //            a_.getArgClassName();
            //            return false;
            //        }
        //        if (_setting.getStep() == StepCalculation.SETTING) {
    //            if (calculatedLater) {
    //                return false;
    //            }
    //        }
    //        OperationNode op_ = this;
    //        while (op_ != null) {
    //            if (op_.getArgument() != null) {
    //                return true;
    //            }
    //            op_ = op_.getParent();
    //        }
    //        return false;
    //    }

    @Override
    public boolean isPossibleInitClass() {
        return false;
    }

    public final boolean isSuperThis() {
        return isSuperConstructorCall() || isOtherConstructorClass();
    }

    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public FctConstraints getConstId() {
        return null;
    }

    @Override
    public final OperationNode getNextSibling() {
        if (initializedNextSibling) {
            //            conf = null;
            return nextSibling;
        }
        initializedNextSibling = true;
        MethodOperation p_ = getParent();
        if (p_ == null) {
            //            conf = null;
            return null;
        }
        NatTreeMap<Integer,String> children_ = p_.getChildren();
        if (indexChild + 1 >= children_.size()) {
            //            conf = null;
            return null;
        }
        String value_ = children_.getValue(indexChild + 1);
        //        OperationsSequence r_ = ElResolver.getOperationsSequence(indexInEl, value_, conf);
        Delimiters d_ = getOperations().getDelimiter();
        int curKey_ = children_.getKey(indexChild + 1);
        d_.setChildOffest(curKey_);
        OperationsSequence r_ = ElResolver.getOperationsSequence(p_.getIndexInEl(), value_, conf, d_);
        //        if (r_.getOperators().isEmpty()) {
        //            nextSibling = new ConstantOperation(value_, children_.getKey(indexChild + 1), importingPage, p_, r_, indexChild + 1);
        //            return nextSibling;
        //        }
        //        if (r_.getPriority() == ExpressionLanguageResolver.getMaxPriority()) {
        //            nextSibling = new FctOperation(value_, children_.getKey(indexChild + 1), importingPage, indexChild + 1, p_, r_);
        //            return nextSibling;
        //        }
        //        if (r_.getPriority() == ExpressionLanguageResolver.ARR_OPER_PRIO) {
        //            nextSibling = new ArrOperation(value_, children_.getKey(indexChild + 1), importingPage, indexChild + 1, p_, r_);
        //            return nextSibling;
        //        }
        nextSibling = createOperationNode(value_, p_.getIndexInEl()+curKey_, conf, indexChild + 1, p_, r_);
        //        conf = null;
        return nextSibling;
    }
    //    static boolean access(Constructor<?> _method) {
    //        return _method.getAnnotation(Accessible.class) != null;
    //    }
    //
    //    static boolean access(Method _method) {
    //        return _method.getAnnotation(Accessible.class) != null;
    //    }
    //
    //    static boolean access(Field _field) {
    //        return _field.getAnnotation(Accessible.class) != null;
    //    }
    static boolean canBeUsed(AccessibleObject _field, ContextEl _conf) {
        return _conf.getAccessValue().canBeUsed(_field, _conf);
    }
    static void setAccess(AccessibleObject _field, ContextEl _conf) {
        _conf.getAccessValue().setAccess(_field, _conf);
    }
    void checkExist(ContextEl _cont, String _className, boolean _checkVoid, boolean _setOffset, int _offset) {
        Classes classes_ = _cont.getClasses();
        if (_checkVoid && StringList.quickEq(_className, OperationNode.VOID_RETURN)) {
            throw new VoidArgumentException(_cont.joinPages());
        }
        ClassMetaInfo custClass_ = null;
        //        String baseName_ = PrimitiveTypeUtil.getComponentBaseType(_className).getComponent();
        String baseName_ = PrimitiveTypeUtil.getQuickComponentBaseType(_className).getComponent();
        if (classes_ != null) {
            custClass_ = classes_.getClassMetaInfo(baseName_);
        }
        if (custClass_ == null) {
            try {
                if (baseName_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    Class<?> cl_ = ConstClasses.getPrimitiveClass(baseName_.substring(1));
                    if (cl_ == null) {
                        throw new RuntimeClassNotFoundException(baseName_);
                    }
                } else {
                    ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(baseName_));
                }
                //                if (_checkVoid && cl_ == void.class) {
                    //                    throw new VoidArgumentException(_cont.joinPages());
                    //                }
            } catch (RuntimeClassNotFoundException _0) {
                if (_setOffset) {
                    setRelativeOffsetPossibleLastPage(_offset, _cont);
                }
                throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_cont.joinPages());
            }
        }
    }
    static FieldMetaInfo getDeclaredCustField(ContextEl _cont,ClassArgumentMatching _class, boolean _superClass, String _name) {
        String clCurName_ = _class.getName();
        ClassMetaInfo custClass_;
        Classes classes_ = _cont.getClasses();
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        String glClass_ = _cont.getLastPage().getGlobalClass();
        while (custClass_ != null) {
            for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                if (!StringList.quickEq(e.getKey(), _name)) {
                    continue;
                }
                if (!_cont.getClasses().canAccessField(glClass_, clCurName_, _name)) {
                    throw new BadAccessException(clCurName_+DOT+_name+RETURN_LINE+_cont.joinPages());
                }
                return e.getValue();
            }
            clCurName_ = custClass_.getSuperClass();
            custClass_ = classes_.getClassMetaInfo(clCurName_);
        }
        throw new NoSuchDeclaredFieldException(_cont.joinPages());
    }
    static Field getDeclaredField(ContextEl _cont,ClassArgumentMatching _class, String _name) {
        Class<?> class_ = _class.getClazz();
        StringList traces_ = new StringList();
        while (class_ != null) {
            try {
                Field field_ = class_.getDeclaredField(_name);
                if (!canBeUsed(field_, _cont)) {
                    throw new BadAccessException(_class.getName()+DOT+_name+RETURN_LINE+_cont.joinPages());
                }
                return field_;
            } catch (NoSuchFieldException _0) {
                String trace_ = class_.getName()+DOT+_name;
                traces_.add(trace_);
            }
            class_ = class_.getSuperclass();
        }
        //        conf.getLastPage().addToOffset(getOperations().getValues().getKey(CustList.FIRST_INDEX));
        throw new NoSuchDeclaredFieldException(traces_.join(RETURN_TAB)+RETURN_LINE+_cont.joinPages());
    }
    static FctConstraints getDeclaredCustConstructor(ContextEl _conf, ClassArgumentMatching _class, ClassArgumentMatching..._args) {
        Classes classes_ = _conf.getClasses();
        if (classes_ == null) {
            return null;
        }
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _class.getName();
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        if (custClass_ == null) {
            return null;
        }
        if (custClass_.isAbstractType() && custClass_.getCategory() != ClassCategory.ENUM) {
            throw new AbstractClassConstructorException(_class.getName()+RETURN_LINE+_conf.joinPages());
        }
        String glClass_ = _conf.getLastPage().getGlobalClass();
        CustList<FctConstraints> possibleMethods_ = new CustList<FctConstraints>();
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(clCurName_+RETURN_LINE+_conf.joinPages());
            }
        }
        ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> constructors_;
        constructors_ = custClass_.getConstructors();
        if (constructors_.isEmpty()) {
            if (_args.length == 0) {
                return new FctConstraints(clCurName_, new EqList<StringList>());
            }
        }
        for (EntryCust<FctConstraints, ConstructorMetaInfo> e: constructors_.entryList()) {
            EqList<StringList> params_ = e.getKey().getConstraints();
            ClassMatching[] p_ = new ClassMatching[params_.size()];
            int i_ = CustList.FIRST_INDEX;
            for (StringList c: params_) {
                p_[i_] = new ClassMatching(c.first());
                i_++;
            }
            if (!isPossibleMethod(_conf, p_, _args)) {
                continue;
            }
            possibleMethods_.add(e.getKey());
        }
        if (possibleMethods_.isEmpty()) {
            String trace_ = clCurName_+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
        }
        if (possibleMethods_.size() == CustList.ONE_ELEMENT) {
            return possibleMethods_.first();
        }
        possibleMethods_ = filterCtr(glClass_, clCurName_, possibleMethods_, _conf);
        if (possibleMethods_.isEmpty()) {
            String trace_ = clCurName_+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            //TODO bas access
            throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(classes_, _args);
        //        ParametersGroupComparator<ConstructorInfo> cmp_ = new ParametersGroupComparator<ConstructorInfo>(gr_);
        CustList<ConstructorInfo> signatures_ = new CustList<ConstructorInfo>();
        for (FctConstraints m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            for (StringList c: m.getConstraints()) {
                p_.add(new ClassMatching(c.first()));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            ConstructorBlock ctr_ = classes_.getConstructorBody(clCurName_, m);
            mloc_.setConstr(ctr_.getId());
            mloc_.setConstraints(m);
            mloc_.setParameters(p_);
            signatures_.add(mloc_);
        }
        signatures_.sortElts(new ParametersGroupComparator<ConstructorInfo>(gr_));
        CustList<ConstructorInfo> errors_ = new CustList<ConstructorInfo>();
        for (ConstructorInfo p : signatures_) {
            if (p.getParameters().isError()) {
                errors_.add(p);
            }
        }
        if (!signatures_.first().getParameters().isError()) {
            return signatures_.first().getConstraints();
        }
        throw new AmbiguousChoiceCallingException(errors_.join(RETURN_LINE)+RETURN_LINE+_conf.joinPages());
    }
    private static CustList<FctConstraints> filterCtr(String _glClass, String _accessedClass, CustList<FctConstraints> _found, ContextEl _conf) {
        CustList<FctConstraints> accessible_ = new CustList<FctConstraints>();
        for (FctConstraints i: _found) {
            if (_conf.getClasses().canAccessConstructor(_glClass, _accessedClass, i)) {
                accessible_.add(i);
            }
        }
        return accessible_;
    }
    static Constructor<?> getDeclaredConstructor(ContextEl _conf, int _offsetIncr, ClassArgumentMatching _class, ClassArgumentMatching..._args) {
        if (Modifier.isAbstract(_class.getClazz().getModifiers())) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new AbstractClassConstructorException(_class.getName()+RETURN_LINE+_conf.joinPages());
        }
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(_class.getName()+RETURN_LINE+_conf.joinPages());
            }
        }
        CustList<Constructor<?>> possibleConstructors_ = new CustList<Constructor<?>>();
        for (Constructor<?> m: _class.getClazz().getDeclaredConstructors()) {
            Class<?>[] params_ = m.getParameterTypes();
            ClassMatching[] p_ = new ClassMatching[params_.length];
            int i_ = CustList.FIRST_INDEX;
            for (Class<?> c: params_) {
                //                p_[i_] = new ClassMatching(c.getName());
                p_[i_] = new ClassMatching(PrimitiveTypeUtil.getAliasArrayClass(c));
                i_++;
            }
            if (!isPossibleMethod(_conf, p_, _args)) {
                continue;
            }
            possibleConstructors_.add(m);
        }
        if (possibleConstructors_.isEmpty()) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new NoSuchDeclaredConstructorException(_class.getName()+RETURN_LINE+_conf.joinPages());
        }
        if (possibleConstructors_.size() == CustList.ONE_ELEMENT) {
            return possibleConstructors_.first();
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf.getClasses(), _args);
        //        ParametersGroupComparator<ConstructorInfo> cmp_ = new ParametersGroupComparator<ConstructorInfo>(gr_);
        CustList<ConstructorInfo> signatures_ = new CustList<ConstructorInfo>();
        for (Constructor<?> m: possibleConstructors_) {
            ParametersGroup p_ = new ParametersGroup();
            for (Class<?> c: m.getParameterTypes()) {
                p_.add(new ClassMatching(c.getName()));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            mloc_.setMethod(m);
            mloc_.setParameters(p_);
            signatures_.add(mloc_);
        }
        //signatures_.size() >= 2
        signatures_.sortElts(new ParametersGroupComparator<ConstructorInfo>(gr_));
        if (!signatures_.first().getParameters().isError()) {
            return signatures_.first().getMethod();
        }
        CustList<ConstructorInfo> errors_ = new CustList<ConstructorInfo>();
        for (ConstructorInfo p : signatures_) {
            if (p.getParameters().isError()) {
                errors_.add(p);
            }
        }
        throw new AmbiguousChoiceCallingException(errors_.join(RETURN_LINE)+RETURN_LINE+_conf.joinPages());
        //        try {
            //            return _class.getClazz().getDeclaredConstructor(getClasses(_args));
            //        } catch (NoSuchMethodException _0) {
        //            _conf.getLastPage().addToOffset(_offsetIncr);
        //            throw new NoSuchDeclaredConstructorException(_0.getMessage()+RETURN_LINE+_conf.joinPages());
        //        }
    }
    static String getDeclaredCustMethod(ContextEl _conf, String _realClassName, ClassMethodId _idMeth) {
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _realClassName;
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        FctConstraints id_ = _idMeth.getConstraints();
        String stopClass_ = _idMeth.getClassName().getName();
        while (true) {
            MethodBlock method_ = classes_.getMethodBody(clCurName_, id_);
            if (method_ == null) {
                String superClass_ = custClass_.getSuperClass();
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            if (method_.isAbstractMethod()) {
                String superClass_ = custClass_.getSuperClass();
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            if (StringList.quickEq(clCurName_, stopClass_)) {
                return clCurName_;
            }
            String superClass_ = custClass_.getSuperClass();
            if (!method_.getAllOverridenClasses().containsStr(stopClass_)) {
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            return clCurName_;
        }
    }
    static ClassMethodId getDeclaredCustMethod(ContextEl _conf, ClassArgumentMatching _class, String _name, boolean _superClass, ClassArgumentMatching... _argsClass) {
        Classes classes_ = _conf.getClasses();
        if (classes_ == null) {
            return null;
        }
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _class.getName();
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        if (custClass_ == null) {
            return null;
        }
        String glClass_ = _conf.getLastPage().getGlobalClass();
        for (ClassArgumentMatching c:_argsClass) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(clCurName_+DOT+_name+RETURN_LINE+_conf.joinPages());
            }
        }
        StringList traces_ = new StringList();
        while (custClass_ != null) {
            ClassMethodIdResult res_ = getDeclaredCustMethodByClass(_conf, new ClassArgumentMatching(clCurName_), _name, _argsClass);
            if (res_.getStatus() == SearchingMemberStatus.ZERO) {
                if (!classes_.canAccessClass(glClass_, clCurName_)) {
                    traces_.add(clCurName_);
                } else {
                    String trace_ = clCurName_+DOT+_name+PAR_LEFT;
                    StringList classesNames_ = new StringList();
                    for (ClassArgumentMatching c: _argsClass) {
                        classesNames_.add(c.getName());
                    }
                    trace_ += classesNames_.join(SEP_ARG);
                    trace_ += PAR_RIGHT;
                    traces_.add(trace_);
                }
                if (!_superClass) {
                    break;
                }
                String superClass_ = custClass_.getSuperClass();
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            if (res_.getStatus() == SearchingMemberStatus.UNIQ) {
                return res_.getId();
            }
            throw new AmbiguousChoiceCallingException(res_.getMethods().join(RETURN_LINE)+RETURN_LINE+_conf.joinPages());
        }
        throw new NoSuchDeclaredMethodException(traces_.join(RETURN_TAB)+RETURN_LINE+_conf.joinPages());
    }
    private static ClassMethodIdResult getDeclaredCustMethodByClass(ContextEl _conf, ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _class.getName();
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        String glClass_ = _conf.getLastPage().getGlobalClass();
        if (!classes_.canAccessClass(glClass_, clCurName_)) {
            String superClass_ = custClass_.getSuperClass();
            custClass_ = classes_.getClassMetaInfo(superClass_);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        CustList<FctConstraints> possibleMethods_ = new CustList<FctConstraints>();
        for (EntryCust<FctConstraints, MethodMetaInfo> e: custClass_.getMethods().entryList()) {
            if (!StringList.quickEq(e.getKey().getName(), _name)) {
                continue;
            }
            EqList<StringList> params_ = e.getKey().getConstraints();
            ClassMatching[] p_ = new ClassMatching[params_.size()];
            int i_ = CustList.FIRST_INDEX;
            for (StringList c: params_) {
                p_[i_] = new ClassMatching(c.first());
                i_++;
            }
            if (!isPossibleMethod(_conf, p_, _argsClass)) {
                continue;
            }
            possibleMethods_.add(e.getKey());
        }
        if (possibleMethods_.isEmpty()) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        if (possibleMethods_.size() == CustList.ONE_ELEMENT) {
            FctConstraints methodId_ = possibleMethods_.first();
            MethodId id_ = classes_.getMethodBody(clCurName_, methodId_).getId();
            ClassMethodId cl_ = new ClassMethodId(new ClassName(clCurName_, false), id_, methodId_);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.UNIQ);
            res_.setId(cl_);
            return res_;
        }
        possibleMethods_ = filterMeth(glClass_, clCurName_, possibleMethods_, _conf);
        if (possibleMethods_.isEmpty()) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(classes_, _argsClass);
        //        ParametersGroupComparator<MethodInfo> cmp_ = new ParametersGroupComparator<MethodInfo>(gr_);
        CustList<MethodInfo> signatures_ = new CustList<MethodInfo>();
        for (FctConstraints m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            for (StringList c: m.getConstraints()) {
                p_.add(new ClassMatching(c.first()));
            }
            MethodInfo mloc_ = new MethodInfo();
            mloc_.setClassName(clCurName_);
            MethodId id_ = classes_.getMethodBody(clCurName_, m).getId();
            mloc_.setMethodId(id_);
            mloc_.setConstraints(m);
            mloc_.setParameters(p_);
            signatures_.add(mloc_);
        }
        signatures_.sortElts(new ParametersGroupComparator<MethodInfo>(gr_));
        CustList<MethodInfo> errors_ = new CustList<MethodInfo>();
        for (MethodInfo p : signatures_) {
            if (p.getParameters().isError()) {
                errors_.add(p);
            }
        }
        if (!signatures_.first().getParameters().isError()) {
            MethodId methodId_ = signatures_.first().getMethodId();
            FctConstraints constraints_ = signatures_.first().getConstraints();
            ClassMethodId cl_ = new ClassMethodId(new ClassName(clCurName_, false), methodId_, constraints_);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.UNIQ);
            res_.setId(cl_);
            return res_;
        }
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        res_.setStatus(SearchingMemberStatus.AMBIGUOUS);
        res_.setMethods(errors_);
        return res_;
    }
    private static EqList<FctConstraints> filterMeth(String _glClass, String _accessedClass, CustList<FctConstraints> _found, ContextEl _conf) {
        EqList<FctConstraints> accessible_ = new EqList<FctConstraints>();
        for (FctConstraints i: _found) {
            if (_conf.getClasses().canAccessMethod(_glClass, _accessedClass, i)) {
                accessible_.add(i);
            }
        }
        return accessible_;
    }
    static Method getDeclaredMethod(ContextEl _cont, ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        Class<?> class_ = _class.getClazz();
        if (class_.isPrimitive()) {
            throw new PrimitiveTypeException(_class.getName()+RETURN_LINE+_cont.joinPages());
        }
        for (ClassArgumentMatching c:_argsClass) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(_class.getName()+DOT+_name+RETURN_LINE+_cont.joinPages());
            }
        }
        StringList traces_ = new StringList();
        while (class_ != null) {
            boolean addToTrace_ = false;

            CustList<Method> possibleMethods_ = new CustList<Method>();
            for (Method m: class_.getDeclaredMethods()) {
                if (!StringList.quickEq(m.getName(), _name)) {
                    continue;
                }
                Class<?>[] params_ = m.getParameterTypes();
                ClassMatching[] p_ = new ClassMatching[params_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Class<?> c: params_) {
                    //                    p_[i_] = new ClassMatching(c.getName());
                    p_[i_] = new ClassMatching(PrimitiveTypeUtil.getAliasArrayClass(c));
                    i_++;
                }
                if (!isPossibleMethod(_cont, p_, _argsClass)) {
                    continue;
                }
                possibleMethods_.add(m);
            }
            boolean keep_ = true;
            if (possibleMethods_.isEmpty()) {
                if (class_ == Object.class) {
                    addToTrace_ = true;
                }
                keep_ = false;
            }
            if (keep_) {
                if (possibleMethods_.size() == CustList.ONE_ELEMENT) {
                    return possibleMethods_.first();
                }
                ArgumentsGroup gr_ = new ArgumentsGroup(_cont.getClasses(), _argsClass);
                //                ParametersGroupComparator<MethodInfo> cmp_ = new ParametersGroupComparator<MethodInfo>(gr_);
                CustList<MethodInfo> signatures_ = new CustList<MethodInfo>();
                for (Method m: possibleMethods_) {
                    ParametersGroup p_ = new ParametersGroup();
                    for (Class<?> c: m.getParameterTypes()) {
                        p_.add(new ClassMatching(c.getName()));
                    }
                    MethodInfo mloc_ = new MethodInfo();
                    mloc_.setMethod(m);
                    mloc_.setParameters(p_);
                    signatures_.add(mloc_);
                }
                signatures_.sortElts(new ParametersGroupComparator<MethodInfo>(gr_));
                CustList<MethodInfo> errors_ = new CustList<MethodInfo>();
                for (MethodInfo p : signatures_) {
                    if (p.getParameters().isError()) {
                        errors_.add(p);
                    }
                }
                if (!signatures_.first().getParameters().isError()) {
                    return signatures_.first().getMethod();
                }
                throw new AmbiguousChoiceCallingException(errors_.join(RETURN_LINE)+RETURN_LINE+_cont.joinPages());
            }
            //            Method method_ = class_.getDeclaredMethod(_name, getClasses(_argsClass));
            //            return method_;
            //            try {} catch (NoSuchMethodException _0) {
            //                if (class_.getSuperclass() == Object.class) {
            //                    addToTrace_ = true;
            //                }
            //            }
            if (addToTrace_) {
                String trace_ = _class.getName()+DOT+_name+PAR_LEFT;
                StringList classes_ = new StringList();
                for (ClassArgumentMatching c: _argsClass) {
                    classes_.add(c.getName());
                }
                trace_ += classes_.join(SEP_ARG);
                trace_ += PAR_RIGHT;
                traces_.add(trace_);
            }
            class_ = class_.getSuperclass();
        }
        throw new NoSuchDeclaredMethodException(traces_.join(RETURN_TAB)+RETURN_LINE+_cont.joinPages());
    }
    static boolean isPossibleMethod(ContextEl _context, ClassMatching[] _params, ClassArgumentMatching..._argsClass) {
        if (_params.length != _argsClass.length) {
            return false;
        }
        int len_ = _params.length;
        boolean skip_ = false;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (_argsClass[i].isVariable()) {
                if (_params[i].isPrimitive()) {
                    skip_ = true;
                    break;
                }
                continue;
            }
            if (!PrimitiveTypeUtil.canBeUseAsArgument(_params[i].getName(), _argsClass[i].getName(), _context.getClasses())) {
                skip_ = true;
                break;
            }
        }
        return !skip_;
    }
    //    Argument getConstructorThenInvoke(ContextEl _conf, int _offsetIncr, Class<?> _class, Argument... _args) {
    //        Constructor m_;
    //        try {
    //            m_ = _class.getDeclaredConstructor(getClasses(_args));
    //        } catch (NoSuchMethodException _0) {
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new NoSuchDeclaredConstructorException(_0.getMessage()+RETURN_LINE+_conf.joinPages());
    //        }
    //        setAccess(m_);
    //        try {
    //            Argument a_ = new Argument();
    //            a_.setObject(m_.newInstance(getObjects(_args)));
    //            a_.setArgClass(a_.getObject().getClass());
    //            return a_;
    //        } catch (InstantiationException _0) {
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new RuntimeInstantiationException(_0, m_.toString()+RETURN_LINE+_conf.joinPages());
    //        } catch (IllegalAccessException _0) {
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new BadAccessException(_0, m_.toString()+RETURN_LINE+_conf.joinPages());
    //        } catch (IllegalArgumentException _0) {
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new DynamicCastClassException(_class.getName()+RETURN_LINE+_conf.joinPages());
    //        } catch (InvocationTargetException _0) {
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new InvokeException(_conf.joinPages(), _0.getTargetException());
    //        } catch (VirtualMachineError _0) {
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new ErrorCausingException(_conf.joinPages(), _0);
    //        } catch (ExceptionInInitializerError _0) {
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new ErrorCausingException(_conf.joinPages(), _0);
    //        }
    //    }
    static Argument newInstance(ContextEl _conf, Argument _need, int _offsetIncr, Constructor<?> _const, Argument... _args) {
        try {
            Object[] args_ = getObjects(_args);
            checkArgumentsForInvoking(_conf, _const.getParameterTypes(), args_);
            Argument a_ = new Argument();
            Object o_ = _const.newInstance(adaptedArgs(_const.getParameterTypes(), args_));
            if (_need != null) {
                a_.setStructArgClassName(new Struct(o_, _need.getStruct()));
            } else {
                a_.setStructArgClassName(new Struct(o_));
            }
            //            a_.setArgClassName(a_.getObject().getClass().getName());
            return a_;
        } catch (InstantiationException _0) {
            //            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new RuntimeInstantiationException(_0, _const.toString()+RETURN_LINE+_conf.joinPages());
        } catch (IllegalAccessException _0) {
            //            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new BadAccessException(_0, _const.toString()+RETURN_LINE+_conf.joinPages());
        } catch (IllegalArgumentException _0) {
            //            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new DynamicCastClassException(_const.getDeclaringClass().getName()+RETURN_LINE+_conf.joinPages());
        } catch (InvocationTargetException _0) {
            //            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new InvokeException(_conf.joinPages(), new Struct(_0.getTargetException()));
        } catch (VirtualMachineError _0) {
            //            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (ExceptionInInitializerError _0) {
            //            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
    }
    //    Argument getMethodThenInvoke(ContextEl _conf, int _offsetIncr, Object _instance, Class<?> _class,String _name, Argument... _args) {
    //        Method m_;
    //        try {
    //            m_ = SerializeXmlObject.getDeclaredMethod(_class, _name, getClasses(_args));
    //        } catch (NoSuchDeclaredMethodException _0) {
    //            //            if (!_useNode) {
    //            //                throw _0;
    //            //            }
    //            _conf.getLastPage().addToOffset(_offsetIncr);
    //            throw new NoSuchDeclaredMethodException(_0.getMessage()+RETURN_LINE+_conf.joinPages());
    //        }
    //        setAccess(m_);
    //        Argument a_ = new Argument();
    //        a_.setArgClass(m_.getReturnType());
    //        a_.setObject(invokeMethod(_conf, _offsetIncr, _class, m_, _instance, getObjects(_args)));
    //        return a_;
    //    }

    static Struct invokeMethod(ContextEl _cont,int _offsetIncr, String _className, Method _method, Object _instance, Object... _args) {
        try {
            checkArgumentsForInvoking(_cont, _method.getParameterTypes(), _args);
            Object o_ = _method.invoke(_instance, adaptedArgs(_method.getParameterTypes(), _args));
            if (o_ == null) {
                return new Struct();
            }
            return new Struct(o_);
        } catch (IllegalAccessException _0) {
            //            if (!_useNode) {
                //                throw new BadAccessException(_0, _method.toString());
            //            }
            //            conf.getLastPage().addToOffset(_offsetIncr);
            throw new BadAccessException(_0, _method.toString()+RETURN_LINE+_cont.joinPages());
        } catch (IllegalArgumentException _0) {
            //            if (!_useNode) {
            //                throw new ClassCastException(_instance.getClass().getName()+SPACE+_class.getName());
            //            }
            //            conf.getLastPage().addToOffset(_offsetIncr);
            //            System.out.println(_method);
            //            System.out.println(_args.length);
            //            System.out.println(_args[1].getClass());
            //            System.err.println(_method);
            throw new DynamicCastClassException(_instance.getClass().getName()+SPACE+_className+RETURN_LINE+_cont.joinPages());
        } catch (InvocationTargetException _0) {
            //            if (!_useNode) {
            //                throw new InvokeException(_0.getTargetException());
            //            }
            //            conf.getLastPage().addToOffset(_offsetIncr);
            throw new InvokeException(_cont.joinPages(), new Struct(_0.getTargetException()));
        } catch (VirtualMachineError _0) {
            //            conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_cont.joinPages(), new Struct(_0));
        } catch (ExceptionInInitializerError _0) {
            //            conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_cont.joinPages(), new Struct(_0));
        }
    }
    static void checkArgumentsForInvoking(ContextEl _cont,Class<?>[] _params,Object... _args) {
        int len_ = _params.length;
        StringList traces_ = new StringList();
        for (int i = 0; i < len_; i++) {
            if (_params[i].isPrimitive() && _args[i] == null) {
                traces_.add(i+RETURN_LINE+_params[i].getName()+RETURN_LINE+null);
            }
        }
        if (!traces_.isEmpty()) {
            throw new UnwrappingException(traces_.join(SEP_ARG)+RETURN_LINE+_cont.joinPages());
        }
    }
    static Object[] adaptedArgs(Class<?>[] _params,Object... _args) {
        int len_ = _params.length;
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Class<?> p_ = _params[i];
            Object a_ = _args[i];
            if (p_ == double.class || p_ == Double.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).doubleValue();
                }
            } else if (p_ == float.class || p_ == Float.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).floatValue();
                }
            } else if (p_ == long.class || p_ == Long.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).longValue();
                }
            } else if (p_ == int.class || p_ == Integer.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).intValue();
                }
            } else if (p_ == short.class || p_ == Short.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).shortValue();
                }
            } else if (p_ == byte.class || p_ == Byte.class) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).byteValue();
                }
            } else {
                args_[i] = a_;
            }
        }
        return args_;
    }
    //    static Class<?>[] getClasses(Argument... _args) {
        //        int len_ = _args.length;
        //        Class<?>[] classes_ = new Class<?>[len_];
        //        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            //            classes_[i] = _args[i].getArgClass();
            //        }
        //        return classes_;
        //    }
    //    static Class<?>[] getClasses(ClassMatching... _args) {
    //        int len_ = _args.length;
    //        Class<?>[] classes_ = new Class<?>[len_];
    //        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
    //            classes_[i] = _args[i].getClazz();
    //        }
    //        return classes_;
    //    }

    static Object[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Object[] classes_ = new Object[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            //            _expected.get(i);
            classes_[i] = _args[i].getObject();
        }
        return classes_;
    }

    final void setNextSiblingsArg(Argument _arg, ContextEl _cont) {
        MethodOperation par_ = getParent();
        Object o_ = _arg.getObject();
        if (o_ == null) {
            if (par_ instanceof QuickOperation) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                throw new NullObjectException(_cont.joinPages());
            }
            return;
        }
        if (!(o_ instanceof Boolean)) {
            return;
        }
        //        String className_ = _arg.getArgClassName();
        //        if (!StringList.quickEq(className_, PrimitiveTypeUtil.PRIM_BOOLEAN)) {
        //            if (!StringList.quickEq(className_, Boolean.class.getName())) {
        //                return;
        //            }
        //        }
        Boolean b_ = (Boolean) o_;
        //        if (par_ instanceof QuickOperation) {
        //            if (b_ == null) {
        //                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
        //                throw new NullObjectException(_cont.joinPages());
        //            }
        //        }
        boolean ternaryParent_ = false;
        if (par_ instanceof FctOperation) {
            FctOperation op_ = (FctOperation) par_;
            ternaryParent_ = op_.isTernary();
        }
        if (!ternaryParent_ && !(par_ instanceof QuickOperation)) {
            return;
        }
        if (b_) {
            if (ternaryParent_) {
                CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
                OperationNode opElt_ = (OperationNode) l_.last();
                opElt_.setSimpleArgument(_arg);
                return;
            }
            if (par_ instanceof OrOperation) {
                CustList<OperationNode> opers_ = new CustList<OperationNode>();
                for (OperationNode s: ElUtil.getDirectChildren(par_)) {
                    opers_.add(s);
                }
                int len_ = opers_.size();
                for (int i = getIndexChild() + 1; i < len_; i++) {
                    opers_.get(i).setSimpleArgument(_arg);
                }
            }
        } else {
            if (ternaryParent_) {
                CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
                OperationNode opElt_ = (OperationNode) l_.get(CustList.SECOND_INDEX);
                opElt_.setSimpleArgument(_arg);
                return;
            }
            if (par_ instanceof AndOperation) {
                CustList<OperationNode> opers_ = new CustList<OperationNode>();
                for (OperationNode s: ElUtil.getDirectChildren(par_)) {
                    opers_.add(s);
                }
                int len_ = opers_.size();
                for (int i = getIndexChild() + 1; i < len_; i++) {
                    opers_.get(i).setSimpleArgument(_arg);
                }
            }
        }
        //        if (par_ instanceof OrOperation && b_) {
            //            CustList<OperationNode> opers_ = new CustList<OperationNode>();
            //            for (SortedNode s: TreeRetrieving.getDirectChildren(par_)) {
                //                opers_.add((OperationNode) s);
                //            }
            //            int len_ = opers_.size();
            //            for (int i = getIndexChild() + 1; i < len_; i++) {
                //                opers_.get(i).setSimpleArgument(_arg);
        //            }
        //        } else if (par_ instanceof AndOperation && !b_) {
        //            CustList<OperationNode> opers_ = new CustList<OperationNode>();
        //            for (SortedNode s: TreeRetrieving.getDirectChildren(par_)) {
        //                opers_.add((OperationNode) s);
        //            }
        //            int len_ = opers_.size();
        //            for (int i = getIndexChild() + 1; i < len_; i++) {
        //                opers_.get(i).setSimpleArgument(_arg);
        //            }
        //        }
    }

    final void setNextSiblingsArg(Argument _arg, ContextEl _cont, IdMap<OperationNode, ArgumentsPair> _nodes) {
        Object o_ = _arg.getObject();
        MethodOperation par_ = getParent();
        if (o_ == null) {
            if (par_ instanceof QuickOperation) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                throw new NullObjectException(_cont.joinPages());
            }
            return;
        }
        if (!(o_ instanceof Boolean)) {
            return;
        }
        //        String className_ = _arg.getArgClassName();
        //        if (!StringList.quickEq(className_, PrimitiveTypeUtil.PRIM_BOOLEAN)) {
        //            if (!StringList.quickEq(className_, Boolean.class.getName())) {
        //                return;
        //            }
        //        }
        Boolean b_ = (Boolean) o_;
        //        MethodOperation par_ = getParent();
        //        if (par_ instanceof QuickOperation) {
        //            if (b_ == null) {
        //                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
        //                throw new NullObjectException(_cont.joinPages());
        //            }
        //        }
        boolean ternaryParent_ = false;
        if (par_ instanceof FctOperation) {
            FctOperation op_ = (FctOperation) par_;
            ternaryParent_ = op_.isTernary();
        }
        if (!ternaryParent_ && !(par_ instanceof QuickOperation)) {
            return;
        }
        if (b_) {
            if (ternaryParent_) {
                CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
                OperationNode opElt_ = (OperationNode) l_.last();
                _nodes.getVal(opElt_).setArgument(_arg);
                return;
            }
            if (par_ instanceof OrOperation) {
                CustList<OperationNode> opers_ = new CustList<OperationNode>();
                for (OperationNode s: ElUtil.getDirectChildren(par_)) {
                    opers_.add(s);
                }
                int len_ = opers_.size();
                for (int i = getIndexChild() + 1; i < len_; i++) {
                    _nodes.getVal(opers_.get(i)).setArgument(_arg);
                }
            }
        } else {
            if (ternaryParent_) {
                CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
                OperationNode opElt_ = (OperationNode) l_.get(CustList.SECOND_INDEX);
                _nodes.getVal(opElt_).setArgument(_arg);
                return;
            }
            if (par_ instanceof AndOperation) {
                CustList<OperationNode> opers_ = new CustList<OperationNode>();
                for (OperationNode s: ElUtil.getDirectChildren(par_)) {
                    opers_.add(s);
                }
                int len_ = opers_.size();
                for (int i = getIndexChild() + 1; i < len_; i++) {
                    _nodes.getVal(opers_.get(i)).setArgument(_arg);
                }
            }
        }
        //        if (par_ instanceof OrOperation && b_) {
            //            CustList<OperationNode> opers_ = new CustList<OperationNode>();
            //            for (SortedNode s: TreeRetrieving.getDirectChildren(par_)) {
                //                opers_.add((OperationNode) s);
                //            }
            //            int len_ = opers_.size();
            //            for (int i = getIndexChild() + 1; i < len_; i++) {
                //                _nodes.getVal(opers_.get(i)).setArgument(_arg);
        //            }
        //        } else if (par_ instanceof AndOperation && !b_) {
        //            CustList<OperationNode> opers_ = new CustList<OperationNode>();
        //            for (SortedNode s: TreeRetrieving.getDirectChildren(par_)) {
        //                opers_.add((OperationNode) s);
        //            }
        //            int len_ = opers_.size();
        //            for (int i = getIndexChild() + 1; i < len_; i++) {
        //                _nodes.getVal(opers_.get(i)).setArgument(_arg);
        //            }
        //        }
    }

    @Override
    public final MethodOperation getParent() {
        return parent;
    }

    public final OperationsSequence getOperations() {
        return operations;
    }

    public final int getOrder() {
        return order;
    }

    public final void setOrder(int _order) {
        order = _order;
    }

    public final void setConf(ContextEl _conf) {
        conf = _conf;
    }

    public final ContextEl getConf() {
        return conf;
    }

    //    public ImportingPage getImportingPage() {
        //        return importingPage;
    //    }

    public final boolean isVararg() {
        return vararg;
    }

    public final boolean isFirstOptArg() {
        return firstOptArg;
    }

    public final int getFullIndexInEl() {
        String meth_ = getOperations().getFctName();
        int off_ = StringList.getFirstPrintableCharIndex(meth_);
        return off_+operations.getDelimiter().getIndexBegin()+indexInEl;
    }

    public final int getIndexInEl() {
        return indexInEl;
    }

    public final int getIndexChild() {
        return indexChild;
    }

    public final boolean isStaticAccess() {
        return staticAccess;
    }

    public final boolean isResetablePreviousArg() {
        return resetablePreviousArg;
    }

    public final void setResetablePreviousArg(boolean _resetablePreviousArg) {
        resetablePreviousArg = _resetablePreviousArg;
    }

    public final boolean isResetableArg() {
        return resetableArg;
    }

    public final void setResetableArg(boolean _resetableArg) {
        resetableArg = _resetableArg;
    }

    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public final Argument getArgument() {
        return argument;
    }

    //    public final void setArgument(Argument _argument, ContextEl _cont) {
        //        argument = _argument;
        //        OperationNode n_ = getNextSibling();
    //        if (n_ != null && getParent() instanceof DotOperation) {
    //            n_.setPreviousArgument(_argument);
    //        }
    //        setSimpleNextSiblingsArg(_argument, _cont);
    //    }
    //    public final void setArgument(Argument _argument) {
    //        argument = _argument;
    //        OperationNode n_ = getNextSibling();
    //        if (n_ != null) {
    //            n_.setPreviousArgument(_argument);
    //        }
    //    }

    public final void resetArguments() {
        if (resetableArg) {
            argument = null;
        }
        if (resetablePreviousArg) {
            previousArgument = null;
        }
    }

    protected final void setCalculatedLater(boolean _calculatedLater) {
        calculatedLater = _calculatedLater;
    }

    public final void setSimpleArgument(Argument _argument) {
        argument = _argument;
    }

    public final void setSimpleArgument(Argument _argument, boolean _resetable) {
        argument = _argument;
        resetableArg = _resetable;
        OperationNode n_ = getNextSibling();
        if (n_ == null) {
            return;
        }
        if (getParent() instanceof DotOperation) {
            if (n_ instanceof ArrOperation) {
                n_.getFirstChild().setPreviousArgument(_argument);
            }
            n_.setPreviousArgument(_argument);
            n_.resetablePreviousArg = _resetable;
        }
    }

    //TODO look for uses of following method
    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes) {
        //        argument = _argument;
        OperationNode n_ = getNextSibling();
        if (n_ != null) {
            if (getParent() instanceof DotOperation) {
                if (n_ instanceof ArrOperation) {
                    n_.getFirstChild().setPreviousArgument(_argument);
                }
                _nodes.getVal(n_).setPreviousArgument(_argument);
            }
        }
        setNextSiblingsArg(_argument, _conf, _nodes);
    }

    public final void setSimpleArgument(Argument _argument, ContextEl _conf) {
        argument = _argument;
        OperationNode n_ = getNextSibling();
        if (n_ != null) {
            if (getParent() instanceof DotOperation) {
                if (n_ instanceof ArrOperation) {
                    n_.getFirstChild().setPreviousArgument(_argument);
                }
                n_.setPreviousArgument(_argument);
            }
        }
        setNextSiblingsArg(_argument, _conf);
    }

    //    public final void setSimpleArgument(Argument _argument, boolean _calculateLater) {
    //        argument = _argument;
    //        calculatedLater = _calculateLater;
    //        OperationNode n_ = getNextSibling();
    //        if (n_ != null && getParent() instanceof DotOperation) {
    //            n_.setPreviousArgument(_argument);
    //        }
    //    }

    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass) {
        setPreviousResultClass(_previousResultClass, false);
    }

    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
        staticAccess = _staticAccess;
    }

    public final boolean isVoidArg() {
        return StringList.quickEq(resultClass.getName(), OperationNode.VOID_RETURN);
    }

    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final void setResultClass(ClassArgumentMatching _resultClass) {
        resultClass = _resultClass;
        OperationNode n_ = getNextSibling();
        if (n_ == null) {
            return;
        }
        if (getParent() instanceof DotOperation) {
            if (n_ instanceof ArrOperation) {
                n_.getFirstChild().setPreviousResultClass(resultClass);
            }
            n_.setPreviousResultClass(resultClass);
        }
    }

    public final void setResultClass(ClassArgumentMatching _resultClass, boolean _staticPrevious) {
        resultClass = _resultClass;
        OperationNode n_ = getNextSibling();
        if (n_ == null) {
            return;
        }
        if (getParent() instanceof DotOperation) {
            if (n_ instanceof ArrOperation) {
                n_.getFirstChild().setPreviousResultClass(resultClass, _staticPrevious);
            }
            n_.setPreviousResultClass(resultClass, _staticPrevious);
        }
    }

    public final boolean isNeedPrevious() {
        return needPrevious;
    }

    public final void setNeedPrevious(boolean _needPrevious) {
        needPrevious = _needPrevious;
    }

    //    public boolean isCalulated() {
        //        return calulated;
        //    }
    //
    //    public void setCalulated(boolean _calulated) {
        //        calulated = _calulated;
        //    }
}
