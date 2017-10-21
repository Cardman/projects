package code.expressionlanguage.opers;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.ClassBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ArgumentsGroup;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdResult;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorInfo;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.Fcts;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodInfo;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.ParametersGroup;
import code.expressionlanguage.opers.util.Parametrable;
import code.expressionlanguage.opers.util.Parametrables;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.types.NativeTypeUtil;
import code.serialize.ConverterMethod;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoSuchDeclaredConstructorException;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public abstract class OperationNode {

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
    protected static final String CLASS_CHOICE = "classchoice";
    protected static final String VAR_ARG = "vararg";
    protected static final String FIRST_OPT = "firstopt";

    protected static final String CLASS_CHOICE_PREF = EXTERN_CLASS + CLASS_CHOICE + EXTERN_CLASS;

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

    protected static final String GET_CLASS = "getClass";

    private MethodOperation parent;

    private boolean initializedNextSibling;

    private OperationNode previousSibling;

    private OperationNode nextSibling;

    private Argument previousArgument;

    private Argument argument;

    private OperationsSequence operations;

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private ContextEl conf;

    private final int indexChild;

    private boolean vararg;
    private boolean firstOptArg;
    private ClassArgumentMatching previousResultClass;
    private ClassArgumentMatching resultClass;

    private boolean needGlobalArgument;
    private boolean staticAccess;
    private boolean staticBlock;

    OperationNode(int _indexInEl, ContextEl _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        conf = _importingPage;
        indexChild = _indexChild;
    }

    public abstract void analyze(CustList<OperationNode> _nodes, ContextEl _conf, String _fieldName, String _op);

    public abstract void calculate(CustList<OperationNode> _nodes, ContextEl _conf, String _op);

    public abstract Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op);


    public final void setRelativeOffsetPossibleLastPage(int _offset, ContextEl _cont) {
        _cont.getLastPage().setOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public static void addRelativeToOffsetPossibleLastPage(int _offset, ContextEl _cont) {
        _cont.getLastPage().addToOffset(_offset);
    }

    public static OperationNode createOperationNode(int _index, ContextEl _conf,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        if (_op.getOperators().isEmpty()) {
            return new ConstantOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO) {
            if (_op.getFctName().trim().isEmpty()) {
                return new IdOperation(_index, _conf, _indexChild, _m, _op);
            }
            if (_op.getFctName().trim().startsWith(EXTERN_CLASS+INSTANCE+DOT_VAR)) {
                return new InstanceOperation(_index, _conf, _indexChild, _m, _op);
            }
            return new FctOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.ARR_OPER_PRIO) {
            return new ArrOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.DOT_PRIO) {
            return new DotOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.UNARY_PRIO) {
            int key_ = _op.getOperators().firstKey();
            if (StringList.quickEq(_op.getOperators().getVal(key_).trim(), NEG_BOOL)) {
                return new UnaryBooleanOperation(_index, _conf, _indexChild, _m, _op);
            }
            return new UnaryOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.MULT_PRIO) {
            return new MultOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.ADD_PRIO) {
            return new AddOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.CMP_PRIO) {
            return new CmpOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.EQ_PRIO) {
            return new EqOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.AND_PRIO) {
            return new AndOperation(_index, _conf, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.OR_PRIO) {
            return new OrOperation(_index, _conf, _indexChild, _m, _op);
        }
        return null;
    }

    final boolean isIntermediateDotted() {
        MethodOperation par_ = getParent();
        if (par_ instanceof ArrOperation && isFirstChild()) {
            return par_.isSimpleIntermediateDotted();
        }
        return isSimpleIntermediateDotted();
    }

    final boolean isSimpleIntermediateDotted() {
        MethodOperation par_ = getParent();
        return !isFirstChild() && par_ instanceof DotOperation;
    }

    abstract boolean isFirstChild();

    final boolean isAnalyzed() {
        return resultClass != null;
    }

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

    public abstract boolean isPossibleInitClass();

    public final boolean isSuperThis() {
        return isSuperConstructorCall() || isOtherConstructorClass();
    }

    public abstract boolean isSuperConstructorCall();

    public abstract boolean isOtherConstructorClass();

    public abstract ConstructorId getConstId();

    public abstract OperationNode getFirstChild();

    public final OperationNode getNextSibling() {
        if (initializedNextSibling) {
            return nextSibling;
        }
        initializedNextSibling = true;
        MethodOperation p_ = getParent();
        if (p_ == null) {
            return null;
        }
        NatTreeMap<Integer,String> children_ = p_.getChildren();
        if (indexChild + 1 >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(indexChild + 1);
        Delimiters d_ = getOperations().getDelimiter();
        int curKey_ = children_.getKey(indexChild + 1);
        d_.setChildOffest(curKey_);
        OperationsSequence r_ = ElResolver.getOperationsSequence(p_.getIndexInEl(), value_, conf, d_);
        nextSibling = createOperationNode(p_.getIndexInEl()+curKey_, conf, indexChild + 1, p_, r_);
        nextSibling.previousSibling = this;
        return nextSibling;
    }
    static boolean canBeUsed(AccessibleObject _field, ContextEl _conf) {
        if (_field instanceof Member) {
            if (Modifier.isPublic(((Member)_field).getModifiers())) {
                return true;
            }
        }
        return _conf.getAccessValue().canBeUsed(_field, _conf);
    }
    static void setAccess(AccessibleObject _field, ContextEl _conf) {
        if (_field instanceof Member) {
            if (Modifier.isPublic(((Member)_field).getModifiers())) {
                return;
            }
        }
        _conf.getAccessValue().setAccess(_field, _conf);
    }
    void checkExist(ContextEl _cont, String _className, boolean _setOffset, int _offset) {
        Classes classes_ = _cont.getClasses();
        if (StringList.quickEq(_className, OperationNode.VOID_RETURN)) {
            throw new VoidArgumentException(_cont.joinPages());
        }
        ClassMetaInfo custClass_ = null;
        String baseName_ = PrimitiveTypeUtil.getQuickComponentBaseType(_className).getComponent();
        if (classes_ != null) {
            custClass_ = classes_.getClassMetaInfo(baseName_);
        }
        if (custClass_ == null) {
            try {
                if (PrimitiveTypeUtil.isPrimitive(baseName_)) {
                    Class<?> cl_ = PrimitiveTypeUtil.getPrimitiveClass(baseName_);
                    if (cl_ == null) {
                        throw new RuntimeClassNotFoundException(baseName_);
                    }
                } else {
                    ConstClasses.classForObjectNameNotInit(PrimitiveTypeUtil.getArrayClass(baseName_));
                }
            } catch (RuntimeClassNotFoundException _0) {
                if (_setOffset) {
                    setRelativeOffsetPossibleLastPage(_offset, _cont);
                }
                throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_cont.joinPages());
            }
        }
    }
    static FieldResult getDeclaredCustField(ContextEl _cont, boolean _staticContext, ClassArgumentMatching _class, boolean _superClass, String _name) {
        FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _superClass, _name);
        FieldResult resSt_ = getDeclaredCustFieldByContext(_cont, true, _class, _superClass, _name);
        if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
            if (!_staticContext) {
                return resIns_;
            }
            throw new StaticAccessException(_cont.joinPages());
        }
        return resSt_;
    }
    static FieldResult getDeclaredCustFieldByContext(ContextEl _cont, boolean _static, ClassArgumentMatching _class, boolean _superClass, String _name) {
        String clCurName_ = _class.getName();
        ClassMetaInfo custClass_;
        Classes classes_ = _cont.getClasses();
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        while (custClass_ != null) {
            for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                if (!StringList.quickEq(e.getKey(), _name)) {
                    continue;
                }
                if (_static) {
                    if (!e.getValue().isStaticField()) {
                        break;
                    }
                } else {
                    if (e.getValue().isStaticField()) {
                        break;
                    }
                }
                FieldResult r_ = new FieldResult();
                r_.setId(e.getValue());
                r_.setStatus(SearchingMemberStatus.UNIQ);
                return r_;
            }
            if (!_superClass) {
                FieldResult r_ = new FieldResult();
                r_.setStatus(SearchingMemberStatus.ZERO);
                return r_;
            }
            clCurName_ = custClass_.getSuperClass();
            custClass_ = classes_.getClassMetaInfo(clCurName_);
        }
        FieldResult r_ = new FieldResult();
        r_.setStatus(SearchingMemberStatus.ZERO);
        return r_;
    }
    static Field getDeclaredField(ContextEl _cont,ClassArgumentMatching _class, String _name) {
        Class<?> class_ = _class.getClazz();
        StringList traces_ = new StringList();
        while (class_ != null) {
            try {
                return class_.getDeclaredField(_name);
            } catch (NoSuchFieldException _0) {
                String trace_ = class_.getName()+DOT+_name;
                traces_.add(trace_);
            }
            class_ = class_.getSuperclass();
        }
        throw new NoSuchDeclaredFieldException(traces_.join(RETURN_TAB)+RETURN_LINE+_cont.joinPages());
    }
    static ConstructorId getDeclaredCustConstructor(ContextEl _conf, boolean _staticBlock, ClassArgumentMatching _class, ClassArgumentMatching..._args) {
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
        CustList<ConstructorId> possibleMethods_ = new CustList<ConstructorId>();
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(clCurName_+RETURN_LINE+_conf.joinPages());
            }
        }
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> constructors_;
        constructors_ = custClass_.getConstructors();
        if (constructors_.isEmpty()) {
            if (_args.length == 0) {
                return new ConstructorId(clCurName_, new EqList<ClassName>());
            }
        }
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: constructors_.entryList()) {
            StringList params_ = e.getKey().getParametersTypes();
            ClassMatching[] p_ = new ClassMatching[params_.size()];
            int i_ = CustList.FIRST_INDEX;
            for (String c: params_) {
                if (!_staticBlock) {
                    String formatted_ = Templates.format(glClass_, c, classes_);
                    p_[i_] = new ClassMatching(formatted_);
                } else {
                    p_[i_] = new ClassMatching(c);
                }
                i_++;
            }
            if (!isPossibleMethod(_conf, _staticBlock, p_, _args)) {
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
            throw new NoSuchDeclaredConstructorException(trace_+RETURN_LINE+_conf.joinPages());
        }
        if (possibleMethods_.size() == CustList.ONE_ELEMENT) {
            if (_staticBlock) {
                return possibleMethods_.first();
            }
            return possibleMethods_.first().format(glClass_, classes_);
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
            throw new NoSuchDeclaredConstructorException(trace_+RETURN_LINE+_conf.joinPages());
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(classes_, _args);
        Parametrables<ConstructorInfo> signatures_ = new Parametrables<ConstructorInfo>();
        for (ConstructorId m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            for (String c: m.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            ConstructorBlock ctr_ = classes_.getConstructorBodiesByFormattedId(clCurName_, m).first();
            mloc_.setConstr(ctr_.getGenericId());
            mloc_.setConstraints(m);
            mloc_.setParameters(p_);
            signatures_.add(mloc_);
        }
        sortCtors(signatures_, gr_);
        if (gr_.isAmbigous()) {
            String trace_ = clCurName_+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredConstructorException(trace_+RETURN_LINE+_conf.joinPages());
        }
        if (_staticBlock) {
            return signatures_.first().getConstraints();
        }
        return signatures_.first().getConstraints().format(glClass_, classes_);
    }
    private static CustList<ConstructorId> filterCtr(String _glClass, String _accessedClass, CustList<ConstructorId> _found, ContextEl _conf) {
        CustList<ConstructorId> accessible_ = new CustList<ConstructorId>();
        for (ConstructorId i: _found) {
            CustList<ConstructorBlock> ctors_ = _conf.getClasses().getConstructorBodiesByFormattedId(_accessedClass, i);
            if (ctors_.isEmpty()) {
                accessible_.add(i);
                continue;
            }
            String curClassBase_ = StringList.getAllTypes(_glClass).first();
            if (_conf.getClasses().canAccess(curClassBase_, ctors_.first())) {
                accessible_.add(i);
            }
        }
        return accessible_;
    }
    static Constructor<?> getDeclaredConstructor(ContextEl _conf, boolean _staticBlock, int _offsetIncr, ClassArgumentMatching _class, ClassArgumentMatching..._args) {
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(_class.getName()+RETURN_LINE+_conf.joinPages());
            }
        }
        Classes classes_ = _conf.getClasses();
        String glClass_ = _conf.getLastPage().getGlobalClass();
        CustList<Constructor<?>> possibleConstructors_ = new CustList<Constructor<?>>();
        for (Constructor<?> m: _class.getClazz().getDeclaredConstructors()) {
            Class<?>[] params_ = m.getParameterTypes();
            int nbParams_ = m.getTypeParameters().length;
            ClassMatching[] p_ = new ClassMatching[params_.length];
            int i_ = CustList.FIRST_INDEX;
            for (Class<?> c: params_) {
                Type type_ = m.getGenericParameterTypes()[i_];
                String pre_ = NativeTypeUtil.getFormattedType(c.getName(), nbParams_, type_);
                if (!_staticBlock) {
                    String formatted_ = Templates.format(glClass_, pre_, classes_);
                    p_[i_] = new ClassMatching(formatted_);
                } else {
                    p_[i_] = new ClassMatching(pre_);
                }
                i_++;
            }
            if (!isPossibleMethod(_conf, _staticBlock, p_, _args)) {
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
        Parametrables<ConstructorInfo> signatures_ = new Parametrables<ConstructorInfo>();
        for (Constructor<?> m: possibleConstructors_) {
            ParametersGroup p_ = new ParametersGroup();
            for (Class<?> c: m.getParameterTypes()) {
                p_.add(new ClassMatching(PrimitiveTypeUtil.getAliasArrayClass(c)));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            mloc_.setMethod(m);
            mloc_.setParameters(p_);
            signatures_.add(mloc_);
        }
        //signatures_.size() >= 2
        sortCtors(signatures_, gr_);
        if (gr_.isAmbigous()) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new NoSuchDeclaredConstructorException(_class.getName()+RETURN_LINE+_conf.joinPages());
        }
        return signatures_.first().getMethod();
    }
    static String getDynDeclaredCustMethod(ContextEl _conf, String _realClassName, boolean _interface, ClassMethodId _idMeth) {
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _realClassName;
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        if (_interface) {
            return getDynDeclaredCustMethodByInterface(_conf, _realClassName, _idMeth);
        }
        MethodId id_ = _idMeth.getConstraints();
        String stopClass_ = _idMeth.getClassName();
        while (true) {
            String baseClass_ = StringList.getAllTypes(clCurName_).first();
            RootBlock clBlock_ = classes_.getClassBody(baseClass_);
            CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(clCurName_, id_);
            if (methods_.isEmpty()) {
                ObjectMap<MethodId, String> def_;
                def_ = clBlock_.getDefaultMethodIds();
                if (def_.contains(_idMeth.getConstraints())) {
                    return def_.getVal(_idMeth.getConstraints());
                }
                String superClass_ = custClass_.getSuperClass();
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            MethodBlock method_ = methods_.first();
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
            if (!clBlock_.getAllOverridingMethods().getVal(method_.getId()).containsStr(stopClass_)) {
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            return clCurName_;
        }
    }
    static ClassMethodIdReturn getDeclaredCustMethod(ContextEl _conf, boolean _staticBlock, boolean _staticContext, ClassArgumentMatching _class, String _name, boolean _superClass, ClassArgumentMatching... _argsClass) {
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
        for (ClassArgumentMatching c:_argsClass) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(clCurName_+DOT+_name+RETURN_LINE+_conf.joinPages());
            }
        }
        if (_superClass && custClass_.getCategory() == ClassCategory.INTERFACE) {
            ObjectNotNullMap<MethodId, MethodMetaInfo> methodsInst_;
            methodsInst_ = getInterfacesMethods(_conf, false, _class, _name, _argsClass);
            ClassMethodIdResult resInst_ = getDeclaredCustMethodByInterface(_conf, _staticBlock, methodsInst_, _class, _name, _argsClass);
            boolean foundInst_ = false;
            if (!_staticContext) {
                if (resInst_.getStatus() == SearchingMemberStatus.UNIQ) {
                    foundInst_ = true;
                }
            }
            ObjectNotNullMap<MethodId, MethodMetaInfo> methodsStatic_;
            methodsStatic_ = getInterfacesMethods(_conf, true, _class, _name, _argsClass);
            ClassMethodIdResult resStatic_ = getDeclaredCustMethodByInterface(_conf, _staticBlock, methodsStatic_, _class, _name, _argsClass);
            if (foundInst_) {
                ClassMethodIdReturn idRet_ = new ClassMethodIdReturn();
                idRet_.setId(new ClassMethodId(_class.getName(), resInst_.getId().getConstraints()));
                idRet_.setReturnType(methodsInst_.getVal(resInst_.getId().getConstraints()).getReturnType());
                return idRet_;
            }
            if (!_staticContext && _conf.isAmbigous()) {
                clCurName_ = _class.getName();
                String trace_ = clCurName_+DOT+_name+PAR_LEFT;
                StringList classesNames_ = new StringList();
                for (ClassArgumentMatching c: _argsClass) {
                    classesNames_.add(c.getName());
                }
                trace_ += classesNames_.join(SEP_ARG);
                trace_ += PAR_RIGHT;
                throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
            }
            if (resStatic_.getStatus() == SearchingMemberStatus.UNIQ) {
                ClassMethodIdReturn idRet_ = new ClassMethodIdReturn();
                idRet_.setId(new ClassMethodId(_class.getName(), resStatic_.getId().getConstraints()));
                idRet_.setReturnType(methodsStatic_.getVal(resStatic_.getId().getConstraints()).getReturnType());
                idRet_.setStaticMethod(true);
                return idRet_;
            }
            if (_staticContext && resInst_.getStatus() == SearchingMemberStatus.UNIQ) {
                //static access
                throw new StaticAccessException(_conf.joinPages());
            }
            String trace_ = clCurName_+DOT+_name+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
        }
        ClassMethodIdResult resInst_ = getDeclaredCustMethodByClassInherit(_conf, _staticBlock, false, _class, _name, _superClass, _argsClass);
        boolean foundInst_ = false;
        if (!_staticContext) {
            if (resInst_.getStatus() == SearchingMemberStatus.UNIQ) {
                foundInst_ = true;
            }
        }
        ClassMethodIdResult resStatic_ = getDeclaredCustMethodByClassInherit(_conf, _staticBlock, true, _class, _name, _superClass, _argsClass);
        if (foundInst_) {
            return toFoundMethod(_conf, resInst_);
        }
        if (!_staticContext && _conf.isAmbigous()) {
            clCurName_ = _class.getName();
            String trace_ = clCurName_+DOT+_name+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
        }
        if (resStatic_.getStatus() == SearchingMemberStatus.UNIQ) {
            return toFoundMethod(_conf, resStatic_);
        }
        if (resInst_.getStatus() == SearchingMemberStatus.UNIQ) {
            //static access
            throw new StaticAccessException(_conf.joinPages());
        }
        String trace_ = clCurName_+DOT+_name+PAR_LEFT;
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(c.getName());
        }
        trace_ += classesNames_.join(SEP_ARG);
        trace_ += PAR_RIGHT;
        throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
    }
    private static ClassMethodIdReturn toFoundMethod(ContextEl _conf, ClassMethodIdResult _res){
        Classes classes_ = _conf.getClasses();
        ClassMethodIdReturn idRet_ = new ClassMethodIdReturn();
        idRet_.setId(_res.getId());
        String clCurName_ = _res.getId().getClassName();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(clCurName_, _res.getId().getConstraints());
        MethodBlock m_;
        if (methods_.isEmpty()) {
            String baseClass_ = StringList.getAllTypes(clCurName_).first();
            UniqueRootedBlock u_ = (UniqueRootedBlock) classes_.getClassBody(baseClass_);
            String int_ = u_.getDefaultMethodIds().getVal(_res.getId().getConstraints());
            int_ = Templates.format(clCurName_, int_, classes_);
            m_ = classes_.getMethodBodiesByFormattedId(int_, _res.getId().getConstraints()).first();
        } else {
            m_ = methods_.first();
        }
        idRet_.setReturnType(m_.getReturnType());
        idRet_.setStaticMethod(m_.isStaticMethod());
        idRet_.setAbstractMethod(m_.isAbstractMethod());
        return idRet_;
    }
    private static ClassMethodIdResult getDeclaredCustMethodByClassInherit(ContextEl _conf, boolean _staticBlock, boolean _static, ClassArgumentMatching _class, String _name, boolean _superClass, ClassArgumentMatching... _argsClass) {
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _class.getName();
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        while (custClass_ != null) {
            ClassMethodIdResult res_ = getDeclaredCustMethodByClass(_conf, _staticBlock, _static, new ClassArgumentMatching(clCurName_), _name, _argsClass);
            if (res_.getStatus() == SearchingMemberStatus.ZERO) {
                if (!_superClass) {
                    return res_;
                }
                String superClass_ = custClass_.getSuperClass();
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            return res_;
        }
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        res_.setStatus(SearchingMemberStatus.ZERO);
        return res_;
    }
    private static ClassMethodIdResult getDeclaredCustMethodByInterface(
            ContextEl _conf,
            boolean _staticBlock,
            ObjectNotNullMap<MethodId, MethodMetaInfo> _methods,
            ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        return getCustResult(_conf, _staticBlock, _class, _methods, true, _name, _argsClass);
    }
    private static ObjectNotNullMap<MethodId, MethodMetaInfo> getInterfacesMethods(
            ContextEl _conf, boolean _static, ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        Classes classes_ = _conf.getClasses();
        String clCurName_ = _class.getName();
        String baseClass_ = StringList.getAllTypes(clCurName_).first();
        InterfaceBlock intBl_ = (InterfaceBlock) classes_.getClassBody(baseClass_);
        StringList superInts_ = new StringList(clCurName_);
        if (!_static) {
            superInts_.addAllElts(intBl_.getAllSuperClasses());
        }
        ObjectMap<MethodId, StringList> signatures_;
        signatures_ = new ObjectMap<MethodId, StringList>();
        if (_static) {
            ObjectMap<MethodId, String> signaturesInt_;
            signaturesInt_ = intBl_.getLocalSignatures(classes_);
            StringList subList_ = new StringList();
            for (EntryCust<MethodId, String> m: signaturesInt_.entryList()) {
                MethodBlock m_ = classes_.getMethodBodiesByFormattedId(clCurName_, m.getKey()).first();
                if (!m_.isStaticMethod()) {
                    continue;
                }
                subList_.add(clCurName_);
                signatures_.put(m.getKey(), subList_);
            }
        } else {
            for (String s: superInts_) {
                if (StringList.quickEq(s, Object.class.getName())) {
                    continue;
                }
                baseClass_ = StringList.getAllTypes(s).first();
                InterfaceBlock superBl_ = (InterfaceBlock) classes_.getClassBody(baseClass_);
                ObjectMap<MethodId, StringList> signaturesInt_;
                signaturesInt_ = superBl_.getAllInstanceSignatures(classes_);
                for (EntryCust<MethodId, StringList> m: signaturesInt_.entryList()) {
                    StringList subList_ = new StringList();
                    for (String i: m.getValue()) {
                        String formattedCl_ = Templates.format(s, i, classes_);
                        MethodBlock m_ = classes_.getMethodBodiesByFormattedId(formattedCl_, m.getKey()).first();
                        if (m_.isStaticMethod()) {
                            continue;
                        }
                        subList_.add(i);
                    }
                    if (subList_.isEmpty()) {
                        continue;
                    }
                    if (!signatures_.contains(m.getKey())) {
                        signatures_.put(m.getKey(), subList_);
                    } else {
                        signatures_.getVal(m.getKey()).addAllElts(subList_);
                        signatures_.getVal(m.getKey()).removeDuplicates();
                    }
                }
            }
        }
        
        ObjectMap<MethodId, StringList> ov_;
        if (!_static) {
            ov_ = RootBlock.getAllOverridingMethods(signatures_, classes_);
        } else {
            ov_ = signatures_;
        }
        ObjectNotNullMap<MethodId, MethodMetaInfo> methods_;
        methods_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
        String cl_ = EMPTY_STRING;
        for (EntryCust<MethodId, StringList> e: ov_.entryList()) {
            StringList retTypes_ = new StringList();
            for (String i: e.getValue()) {
                String formattedCl_ = Templates.format(clCurName_, i, classes_);
                MethodBlock m_ = classes_.getMethodBodiesByFormattedId(formattedCl_, e.getKey()).first();
                retTypes_.add(m_.getReturnType());
            }
            String ret_ = PrimitiveTypeUtil.getSubslass(retTypes_, classes_);
            cl_ = e.getValue().first();
            if (!_static) {
                MethodMetaInfo info_ = new MethodMetaInfo(cl_, MethodModifier.NORMAL, ret_);
                methods_.put(e.getKey(), info_);
            } else {
                MethodMetaInfo info_ = new MethodMetaInfo(cl_, MethodModifier.STATIC, ret_);
                methods_.put(e.getKey(), info_);
            }
        }
        return methods_;
    }
    private static String getDynDeclaredCustMethodByInterface(ContextEl _conf, String _realClassName, ClassMethodId _idMeth) {
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _realClassName;
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        MethodId id_ = _idMeth.getConstraints();
        while (true) {
            String baseClass_ = StringList.getAllTypes(clCurName_).first();
            CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(clCurName_, id_);
            if (methods_.isEmpty()) {
                ClassBlock clBlock_ = (ClassBlock) classes_.getClassBody(baseClass_);
                ObjectMap<MethodId, String> def_;
                def_ = clBlock_.getDefaultMethodIds();
                if (def_.contains(_idMeth.getConstraints())) {
                    return def_.getVal(_idMeth.getConstraints());
                }
                String superClass_ = custClass_.getSuperClass();
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            MethodBlock method_ = methods_.first();
            if (method_.isAbstractMethod()) {
                String superClass_ = custClass_.getSuperClass();
                custClass_ = classes_.getClassMetaInfo(superClass_);
                clCurName_ = superClass_;
                continue;
            }
            return clCurName_;
        }
    }
    private static ClassMethodIdResult getDeclaredCustMethodByClass(ContextEl _conf, boolean _staticBlock, 
            boolean _static, ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_;
        String clCurName_ = _class.getName();
        String baseCurName_ = StringList.getAllTypes(clCurName_).first();
        RootBlock root_ = classes_.getClassBody(baseCurName_);
        custClass_ = classes_.getClassMetaInfo(clCurName_);
        ObjectNotNullMap<MethodId, MethodMetaInfo> methods_;
        methods_ = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
        if (_static) {
            for (EntryCust<MethodId, MethodMetaInfo> e: custClass_.getMethods().entryList()) {
                if (e.getValue().isStatic()) {
                    methods_.put(e.getKey(), e.getValue());
                }
            }
        } else {
            String generic_ = root_.getGenericString();
            for (EntryCust<MethodId, StringList> e: root_.getAllOverridingMethods().entryList()) {
                for (String c: e.getValue()) {
                    if (StringList.quickEq(c, generic_)) {
                        MethodBlock m_ = classes_.getMethodBodiesByFormattedId(generic_, e.getKey()).first();
                        MethodMetaInfo info_ = new MethodMetaInfo(c, MethodModifier.NORMAL, m_.getReturnType());
                        methods_.put(e.getKey(), info_);
                    }
                }
            }
        }
        String baseClass_ = StringList.getAllTypes(clCurName_).first();
        RootBlock clBl_ = classes_.getClassBody(baseClass_);
        for (EntryCust<MethodId, String> e: clBl_.getDefaultMethodIds().entryList()) {
            String formattedCl_ = Templates.format(clCurName_, e.getValue(), classes_);
            MethodBlock m_ = classes_.getMethodBodiesByFormattedId(formattedCl_, e.getKey()).first();
            String ret_ = m_.getReturnType();
            MethodMetaInfo info_ = new MethodMetaInfo(e.getValue(), MethodModifier.NORMAL, ret_);
            methods_.put(e.getKey(), info_);
        }
        return getCustResult(_conf, _staticBlock, _class, methods_, false, _name, _argsClass);
    }
    private static ClassMethodIdResult getCustResult(ContextEl _conf, boolean _staticBlock, ClassArgumentMatching _class,
            ObjectNotNullMap<MethodId, MethodMetaInfo> _methods, boolean _int,
            String _name, ClassArgumentMatching... _argsClass) {
        Classes classes_ = _conf.getClasses();
        String clCurName_ = _class.getName();
        CustList<MethodId> possibleMethods_ = new CustList<MethodId>();
        String glClass_ = _conf.getLastPage().getGlobalClass();
        for (EntryCust<MethodId, MethodMetaInfo> e: _methods.entryList()) {
            if (!StringList.quickEq(e.getKey().getName(), _name)) {
                continue;
            }
            StringList params_ = e.getKey().getParametersTypes();
            ClassMatching[] p_ = new ClassMatching[params_.size()];
            int i_ = CustList.FIRST_INDEX;
            for (String c: params_) {
                p_[i_] = new ClassMatching(c);
                i_++;
            }
            if (!isPossibleMethod(_conf, _staticBlock, p_, _argsClass)) {
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
            MethodId methodId_ = possibleMethods_.first();
            ClassMethodId cl_ = new ClassMethodId(clCurName_, methodId_);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.UNIQ);
            res_.setId(cl_);
            return res_;
        }
        if (!_int) {
            possibleMethods_ = filterMeth(glClass_, clCurName_, possibleMethods_, _conf);
        }
        if (possibleMethods_.isEmpty()) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(classes_, _argsClass);
        Parametrables<MethodInfo> signatures_ = new Parametrables<MethodInfo>();
        for (MethodId m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            for (String c: m.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            MethodInfo mloc_ = new MethodInfo();
            mloc_.setClassName(_methods.getVal(m).getClassName());
            mloc_.setConstraints(m);
            mloc_.setParameters(p_);
            mloc_.setReturnType(_methods.getVal(m).getReturnType());
            signatures_.add(mloc_);
        }
        _conf.setAmbigous(false);
        sortFct(signatures_, gr_);
        if (gr_.isAmbigous()) {
            _conf.setAmbigous(true);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        MethodId constraints_ = signatures_.first().getConstraints();
        ClassMethodId cl_ = new ClassMethodId(clCurName_, constraints_);
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        res_.setStatus(SearchingMemberStatus.UNIQ);
        res_.setId(cl_);
        return res_;
    }
    private static EqList<MethodId> filterMeth(String _glClass, String _accessedClass, CustList<MethodId> _found, ContextEl _conf) {
        EqList<MethodId> accessible_ = new EqList<MethodId>();
        for (MethodId i: _found) {
            MethodBlock m_ = _conf.getClasses().getMethodBodiesByFormattedId(_accessedClass, i).first();
            String curClassBase_ = StringList.getAllTypes(_glClass).first();
            if (_conf.getClasses().canAccess(curClassBase_, m_)) {
                accessible_.add(i);
            }
        }
        return accessible_;
    }
    static Method getDeclaredMethod(ContextEl _cont, boolean _staticBlock, boolean _staticContext, ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        Class<?> class_ = _class.getClazz();
        class_ = PrimitiveTypeUtil.toBooleanWrapper(class_, true);
        for (ClassArgumentMatching c:_argsClass) {
            if (c.matchVoid()) {
                throw new VoidArgumentException(_class.getName()+DOT+_name+RETURN_LINE+_cont.joinPages());
            }
        }
        if (class_.isInterface()) {
            ClassMethodIdResult resInst_ = getInterfaceMethod(_cont, _staticBlock, false, _class, _name, _argsClass);
            ClassMethodIdResult resStatic_ = getInterfaceMethod(_cont, _staticBlock, true, _class, _name, _argsClass);
            return getFoundMethod(_cont, _staticContext, resInst_, resStatic_, _class, _name, _argsClass);
        }
        ClassMethodIdResult resInst_ = getDeclaredMethodLoop(_cont, _staticBlock, false, _class, _name, _argsClass);
        ClassMethodIdResult resStatic_ = getDeclaredMethodLoop(_cont, _staticBlock, true, _class, _name, _argsClass);
        return getFoundMethod(_cont, _staticContext, resInst_, resStatic_, _class, _name, _argsClass);
    }
    private static Method getFoundMethod(ContextEl _cont, boolean _staticContext,
            ClassMethodIdResult _resInst, ClassMethodIdResult _resStatic,
            ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        boolean foundInst_ = false;
        if (!_staticContext) {
            if (_resInst.getStatus() == SearchingMemberStatus.UNIQ) {
                foundInst_ = true;
            }
        }
        if (foundInst_) {
            return _resInst.getMethod();
        }
        if (!_staticContext && _cont.isAmbigous()) {
            String clCurName_ = _class.getName();
            String trace_ = clCurName_+DOT+_name+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_cont.joinPages());
        }
        if (_resStatic.getStatus() == SearchingMemberStatus.UNIQ) {
            return _resStatic.getMethod();
        }
        if (_resInst.getStatus() == SearchingMemberStatus.UNIQ) {
            //static access
            throw new StaticAccessException(_cont.joinPages());
        }
        String clCurName_ = _class.getName();
        String trace_ = clCurName_+DOT+_name+PAR_LEFT;
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(c.getName());
        }
        trace_ += classesNames_.join(SEP_ARG);
        trace_ += PAR_RIGHT;
        throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_cont.joinPages());
    }
    private static ClassMethodIdResult getInterfaceMethod(ContextEl _cont, boolean _staticBlock, boolean _static, ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        Class<?> class_ = _class.getClazz();
        CustList<Method> possibleMethods_ = new CustList<Method>(class_.getMethods());
        return getResult(_cont, _staticBlock, _static, _class, possibleMethods_, _name, _argsClass);
    }
    static ClassMethodIdResult getDeclaredMethodLoop(ContextEl _cont, boolean _staticBlock, boolean _static, ClassArgumentMatching _class,
            String _name, ClassArgumentMatching... _argsClass) {
        Class<?> class_ = _class.getClazz();
        IdList<Class<?>> superTypes_ = new IdList<Class<?>>(class_);
        while (class_ != null) {
            superTypes_.add(class_);
            superTypes_.addAllElts(getSuperInterfaces(class_));
            class_ = class_.getSuperclass();
        }
        superTypes_.removeDuplicates();
        for (Class<?> c: superTypes_) {
            CustList<Method> possibleMethods_ = new CustList<Method>(c.getDeclaredMethods());
            ClassMethodIdResult res_ = getResult(_cont, _staticBlock, _static, _class, possibleMethods_, _name, _argsClass);
            if (res_.getStatus() == SearchingMemberStatus.ZERO) {
                continue;
            }
            return res_;
        }
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        res_.setStatus(SearchingMemberStatus.ZERO);
        return res_;
    }
    private static IdList<Class<?>> getSuperInterfaces(Class<?> _class) {
        IdList<Class<?>> cur_ = new IdList<Class<?>>(_class);
        IdList<Class<?>> all_ = new IdList<Class<?>>();
        while (true) {
            IdList<Class<?>> next_ = new IdList<Class<?>>();
            for (Class<?> c: cur_) {
                for (Class<?> i: c.getInterfaces()) {
                    if (!all_.containsObj(i)) {
                        next_.add(i);
                        all_.add(i);
                    }
                }
            }
            if (next_.isEmpty()) {
                return all_;
            }
            cur_ = next_;
        }
    }
    private static ClassMethodIdResult getResult(ContextEl _conf, boolean _staticBlock, boolean _static, ClassArgumentMatching _class,
            CustList<Method> _methods,
            String _name, ClassArgumentMatching... _argsClass) {
        CustList<Method> possibleMethods_ = new CustList<Method>();
        for (Method m: _methods) {
            if (_static) {
                if (!Modifier.isStatic(m.getModifiers())) {
                    continue;
                }
            } else {
                if (Modifier.isStatic(m.getModifiers())) {
                    continue;
                }
            }
            if (!StringList.quickEq(m.getName(), _name)) {
                continue;
            }
            Class<?>[] params_ = m.getParameterTypes();
            ClassMatching[] p_ = new ClassMatching[params_.length];
            int i_ = CustList.FIRST_INDEX;
            for (Class<?> c: params_) {
                p_[i_] = new ClassMatching(PrimitiveTypeUtil.getAliasArrayClass(c));
                i_++;
            }
            if (!isPossibleMethod(_conf, _staticBlock, p_, _argsClass)) {
                continue;
            }
            possibleMethods_.add(m);
        }
        if (possibleMethods_.isEmpty()) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        if (possibleMethods_.size() == CustList.ONE_ELEMENT) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.UNIQ);
            res_.setMethod(possibleMethods_.first());
            return res_;
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf.getClasses(), _argsClass);
        Parametrables<MethodInfo> signatures_ = new Parametrables<MethodInfo>();
        for (Method m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            for (Class<?> c: m.getParameterTypes()) {
                p_.add(new ClassMatching(PrimitiveTypeUtil.getAliasArrayClass(c)));
            }
            MethodInfo mloc_ = new MethodInfo();
            mloc_.setMethod(m);
            mloc_.setParameters(p_);
            mloc_.setReturnType(PrimitiveTypeUtil.getAliasArrayClass(m.getReturnType()));
            signatures_.add(mloc_);
        }
        _conf.setAmbigous(false);
        sortFct(signatures_, gr_);
        if (gr_.isAmbigous()) {
            _conf.setAmbigous(true);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        res_.setStatus(SearchingMemberStatus.UNIQ);
        res_.setMethod(signatures_.first().getMethod());
        return res_;
    }
    static boolean isPossibleMethod(ContextEl _context, boolean _staticBlock, ClassMatching[] _params, ClassArgumentMatching..._argsClass) {
        if (_params.length != _argsClass.length) {
            return false;
        }
        String glClass_ = _context.getLastPage().getGlobalClass();
        CustList<TypeVar> vars_;
        if (!_staticBlock) {
            vars_ = Templates.getConstraints(glClass_, _context.getClasses());
        } else {
            vars_ = new CustList<TypeVar>();
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
            boolean ok_ = true;
            Mapping map_ = new Mapping();
            map_.setArg(_argsClass[i].getName());
            for (TypeVar t: vars_) {
                map_.getMapping().put(t.getName(), t.getConstraints());
            }
            for (String t: _params[i].getClassName()) {
                map_.setParam(t);
                if (!Templates.isCorrect(map_, _context.getClasses())) {
                    ok_ = false;
                    break;
                }
            }
            if (!ok_) {
                skip_ = true;
                break;
            }
        }
        return !skip_;
    }
    static Argument newInstance(ContextEl _conf, Argument _need, int _offsetIncr, Constructor<?> _const, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        checkArgumentsForInvoking(_conf, toClassNames(_const.getParameterTypes()), args_);
        try {
            Argument a_ = new Argument();
            Object o_ = ConverterMethod.newInstance(_const, adaptedArgs(_const.getParameterTypes(), args_));
            if (_need != null) {
                a_.setStruct(new Struct(o_, _need.getStruct()));
            } else {
                a_.setStruct(new Struct(o_));
            }
            return a_;
        } catch (InvokingException _0) {
            throw new InvokeException(_conf.joinPages(), new Struct(_0.getTarget()));
        } catch (Throwable _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
    }

    static Struct invokeMethod(ContextEl _cont,int _offsetIncr, String _className, Method _method, Object _instance, Argument... _args) {
        Struct[] args_ = getObjects(_args);
        checkArgumentsForInvoking(_cont, toClassNames(_method.getParameterTypes()), args_);
        try {
            Object o_ = ConverterMethod.invokeMethod(_method, _instance, adaptedArgs(_method.getParameterTypes(), args_));
            if (o_ == null) {
                return new Struct();
            }
            if (o_ instanceof Struct) {
                return (Struct) o_;
            }
            return new Struct(o_);
        } catch (InvokingException _0) {
            throw new InvokeException(_cont.joinPages(), new Struct(_0.getTarget()));
        } catch (Throwable _0) {
            throw new ErrorCausingException(_cont.joinPages(), new Struct(_0));
        }
    }
    static void sortFct(Parametrables<MethodInfo> _fct, ArgumentsGroup _context) {
        int len_ = _fct.size();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            process(_fct, i, _context);
        }
        if (_fct.first().getParameters().isError()) {
            _context.setAmbigous(true);
        }
    }
    static void sortCtors(Parametrables<ConstructorInfo> _fct, ArgumentsGroup _context) {
        int len_ = _fct.size();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            process(_fct, i, _context);
        }
        if (_fct.first().getParameters().isError()) {
            _context.setAmbigous(true);
        }
    }
    static void process(Fcts _list, int _i, ArgumentsGroup _context) {
        Parametrable pFirst_ = _list.first();
        Parametrable pCurrent_ = _list.get(_i);
        int res_ = compare(_context, pFirst_, pCurrent_);
        if (res_ == CustList.SWAP_SORT) {
            _list.swapIndexes(CustList.FIRST_INDEX, _i);
        }
    }
    static boolean ok(ArgumentsGroup _context,Parametrable _o1, Parametrable _o2) {
        int len_ = _o1.getParameters().size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            ClassArgumentMatching selected_ = _context.get(i);
            ClassMatching one_ = _o1.getParameters().get(i);
            ClassMatching two_ = _o2.getParameters().get(i);
            if (one_.matchClass(two_)) {
                continue;
            }
            if (selected_.isVariable()) {
                if (two_.isAssignableFrom(one_, _context.getClasses())) {
                    continue;
                }
                return false;
            }
            ClassMatching toPrOne_ = one_;
            ClassMatching toPrTwo_ = two_;
            boolean onePrimExcl_ = false;
            boolean twoPrimExcl_ = false;
            if (one_.isPrimitive() && !two_.isPrimitive()) {
                onePrimExcl_ = true;
            }
            if (!one_.isPrimitive() && two_.isPrimitive()) {
                twoPrimExcl_ = true;
            }
            if (selected_.isPrimitive()) {
                if (onePrimExcl_) {
                    return true;
                }
                toPrOne_ = PrimitiveTypeUtil.toAllPrimitive(one_);
                toPrTwo_ = PrimitiveTypeUtil.toAllPrimitive(two_);
            } else {
                ClassArgumentMatching clMatch_ = PrimitiveTypeUtil.toAllPrimitive(selected_, true);
                if (clMatch_.isPrimitive()) {
                    if (twoPrimExcl_) {
                        return true;
                    }
                    toPrOne_ = PrimitiveTypeUtil.toAllPrimitive(one_);
                    toPrTwo_ = PrimitiveTypeUtil.toAllPrimitive(two_);
                }
            }
            if (toPrTwo_.isAssignableFrom(toPrOne_, _context.getClasses())) {
                return true;
            }
            return false;
        }
        return false;
    }
    static int compare(ArgumentsGroup _context,Parametrable _o1, Parametrable _o2) {
        int len_ = _o1.getParameters().size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            ClassArgumentMatching selected_ = _context.get(i);
            ClassMatching one_ = _o1.getParameters().get(i);
            ClassMatching two_ = _o2.getParameters().get(i);
            if (one_.matchClass(two_)) {
                continue;
            }
            if (selected_.isVariable()) {
                if (one_.isAssignableFrom(two_, _context.getClasses())) {
                    return CustList.SWAP_SORT;
                }
                if (two_.isAssignableFrom(one_, _context.getClasses())) {
                    return CustList.NO_SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }
            ClassMatching toPrOne_ = one_;
            ClassMatching toPrTwo_ = two_;
            boolean onePrimExcl_ = false;
            boolean twoPrimExcl_ = false;
            if (one_.isPrimitive() && !two_.isPrimitive()) {
                onePrimExcl_ = true;
            }
            if (!one_.isPrimitive() && two_.isPrimitive()) {
                twoPrimExcl_ = true;
            }
            if (selected_.isPrimitive()) {
                if (onePrimExcl_) {
                    return CustList.NO_SWAP_SORT;
                }
                if (twoPrimExcl_) {
                    return CustList.SWAP_SORT;
                }
                toPrOne_ = PrimitiveTypeUtil.toAllPrimitive(one_);
                toPrTwo_ = PrimitiveTypeUtil.toAllPrimitive(two_);
            } else {
                ClassArgumentMatching clMatch_ = PrimitiveTypeUtil.toAllPrimitive(selected_, true);
                if (clMatch_.isPrimitive()) {
                    if (onePrimExcl_) {
                        return CustList.SWAP_SORT;
                    }
                    if (twoPrimExcl_) {
                        return CustList.NO_SWAP_SORT;
                    }
                    toPrOne_ = PrimitiveTypeUtil.toAllPrimitive(one_);
                    toPrTwo_ = PrimitiveTypeUtil.toAllPrimitive(two_);
                }
            }
            if (toPrOne_.isAssignableFrom(toPrTwo_, _context.getClasses())) {
                return CustList.SWAP_SORT;
            }
            if (toPrTwo_.isAssignableFrom(toPrOne_, _context.getClasses())) {
                return CustList.NO_SWAP_SORT;
            }
            _o1.getParameters().setError(true);
            _o2.getParameters().setError(true);
            return CustList.NO_SWAP_SORT;
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(_o1.getReturnType(), _o2.getReturnType(), _context.getClasses())) {
            return CustList.SWAP_SORT;
        }
        return CustList.NO_SWAP_SORT;
    }
    static void checkArgumentsForInvoking(ContextEl _cont,StringList _params,Struct... _args) {
        int len_ = _params.size();
        StringList traces_ = new StringList();
        for (int i = 0; i < len_; i++) {
            if (PrimitiveTypeUtil.primitiveTypeNullObject(_params.get(i), _args[i])) {
                traces_.add(i+RETURN_LINE+_params.get(i)+RETURN_LINE+null);
            }
        }
        if (!traces_.isEmpty()) {
            throw new UnwrappingException(traces_.join(SEP_ARG)+RETURN_LINE+_cont.joinPages());
        }
    }
    static StringList toClassNames(Class<?>[] _params) {
        StringList params_ = new StringList();
        for (Class<?> c: _params) {
            if (c.isPrimitive()) {
                params_.add(PrimitiveTypeUtil.PRIM+c.getName());
            } else {
                params_.add(PrimitiveTypeUtil.getAliasArrayClass(c));
            }
        }
        return params_;
    }
    static Object[] adaptedArgs(Class<?>[] _params,Struct... _args) {
        int len_ = _params.length;
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Struct argStruct_ = _args[i];
            if (argStruct_.isNull()) {
                continue;
            }
            if (!argStruct_.isJavaObject()) {
                args_[i] = argStruct_;
                continue;
            }
            Object a_ = argStruct_.getInstance();
            Class<?> p_ = _params[i];
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

    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    final void setNextSiblingsArg(Argument _arg, ContextEl _cont) {
        if (!processBooleanValues(_arg, _cont)) {
            return;
        }
        MethodOperation par_ = getParent();
        Object o_ = _arg.getObject();
        Boolean b_ = (Boolean) o_;
        int index_ = getTernary(_arg);
        if (index_ > 0) {
            CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
            OperationNode opElt_ = (OperationNode) l_.get(index_);
            opElt_.setSimpleArgument(_arg);
            return;
        }
        QuickOperation q_ = (QuickOperation) par_;
        if (b_ == q_.absorbingValue()) {
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

    final void setNextSiblingsArg(Argument _arg, ContextEl _cont, IdMap<OperationNode, ArgumentsPair> _nodes) {
        if (!processBooleanValues(_arg, _cont)) {
            return;
        }
        Object o_ = _arg.getObject();
        MethodOperation par_ = getParent();
        Boolean b_ = (Boolean) o_;
        int index_ = getTernary(_arg);
        if (index_ > 0) {
            CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
            OperationNode opElt_ = (OperationNode) l_.get(index_);
            _nodes.getVal(opElt_).setArgument(_arg);
            return;
        }
        QuickOperation q_ = (QuickOperation) par_;
        if (b_ == q_.absorbingValue()) {
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

    final boolean processBooleanValues(Argument _arg, ContextEl _cont) {
        Object o_ = _arg.getObject();
        MethodOperation par_ = getParent();
        if (o_ == null) {
            if (par_ instanceof QuickOperation) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                throw new NullObjectException(_cont.joinPages());
            }
            return false;
        }
        if (!(o_ instanceof Boolean)) {
            return false;
        }
        if (!(par_ instanceof QuickOperation)) {
            return getTernary(_arg) > 0;
        }
        return true;
    }

    final int getTernary(Argument _arg) {
        Object o_ = _arg.getObject();
        boolean ternaryParent_ = false;
        MethodOperation par_ = getParent();
        if (par_ instanceof FctOperation) {
            FctOperation op_ = (FctOperation) par_;
            ternaryParent_ = op_.isTernary();
        }
        if (!ternaryParent_) {
            return 0;
        }
        Boolean b_ = (Boolean) o_;
        if (b_) {
            return 2;
        }
        return 1;
    }

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

    public final boolean isVararg() {
        return vararg;
    }

    final void setVararg(boolean _vararg) {
        vararg = _vararg;
    }

    public final boolean isFirstOptArg() {
        return firstOptArg;
    }

    final void setFirstOptArg(boolean _firstOptArg) {
        firstOptArg = _firstOptArg;
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

    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public final Argument getArgument() {
        return argument;
    }

    public final void setSimpleArgument(Argument _argument) {
        argument = _argument;
    }

    public final void setArguments(Argument _argument) {
        argument = _argument;
        OperationNode n_ = getSiblingToSet();
        if (n_ == null) {
            return;
        }
        n_.setPreviousArgument(_argument);
    }

    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode n_ = getSiblingToSet();
        if (n_ != null) {
            _nodes.getVal(n_).setPreviousArgument(_argument);
        }
        setNextSiblingsArg(_argument, _conf, _nodes);
    }

    public final void setSimpleArgument(Argument _argument, ContextEl _conf) {
        argument = _argument;
        OperationNode n_ = getSiblingToSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
        setNextSiblingsArg(_argument, _conf);
    }

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

    public final boolean isStaticBlock() {
        return staticBlock;
    }

    public final void setStaticBlock(boolean _staticBlock) {
        staticBlock = _staticBlock;
    }

    public final boolean isVoidArg() {
        return StringList.quickEq(resultClass.getName(), OperationNode.VOID_RETURN);
    }

    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final void setResultClass(ClassArgumentMatching _resultClass) {
        resultClass = _resultClass;
        OperationNode n_ = getSiblingToSet();
        if (n_ == null) {
            return;
        }
        n_.setPreviousResultClass(resultClass);
    }

    public final void setResultClass(ClassArgumentMatching _resultClass, boolean _staticPrevious) {
        resultClass = _resultClass;
        OperationNode n_ = getSiblingToSet();
        if (n_ == null) {
            return;
        }
        n_.setPreviousResultClass(resultClass, _staticPrevious);
    }

    final OperationNode getSiblingToSet() {
        OperationNode n_ = getNextSibling();
        if (n_ == null) {
            return null;
        }
        if (!(getParent() instanceof DotOperation)) {
            return null;
        }
        if (n_ instanceof ArrOperation) {
            return n_.getFirstChild();
        }
        return n_;
    }

    protected final OperationNode getPreviousSibling() {
        return previousSibling;
    }

    public final boolean isNeedGlobalArgument() {
        return needGlobalArgument;
    }

    public final void needGlobalArgument() {
        if (isIntermediateDotted()) {
            return;
        }
        needGlobalArgument = true;
    }
}
