package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.FileResolver;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.OperatorBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.StaticAccessFieldError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UndefinedConstructorError;
import code.expressionlanguage.methods.util.UndefinedMethodError;
import code.expressionlanguage.opers.util.ArgumentsGroup;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdResult;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorInfo;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.Fcts;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.Identifiable;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.ParametersGroup;
import code.expressionlanguage.opers.util.Parametrable;
import code.expressionlanguage.opers.util.Parametrables;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class OperationNode {

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
    protected static final char DOT_VAR = '.';
    protected static final char EXTERN_CLASS = FileResolver.SUPPLEMENT_CHAR;
    protected static final char INTERN_CLASS = FileResolver.SUPPLEMENT_CHAR;
    protected static final String SUPER_ACCESS = "super";
    protected static final String CURRENT = "this";
    protected static final String THAT = "that";
    protected static final String INSTANCE = "new";
    protected static final String INSTANCEOF = "instanceof";
    protected static final String BOOLEAN = "bool";
    protected static final String VALUES = "values";
    protected static final String VALUE_OF = "valueOf";
    protected static final String CAST = "class";
    protected static final char MIN_ENCODE_DIGIT = '0';
    protected static final char MAX_ENCODE_DIGIT = '9';
    protected static final char MIN_ENCODE_LOW_LETTER = 'a';
    protected static final char MAX_ENCODE_LOW_LETTER = 'f';
    protected static final char MIN_ENCODE_UPP_LETTER = 'A';
    protected static final char MAX_ENCODE_UPP_LETTER = 'F';
    protected static final String VAR_ARG = "vararg";
    protected static final String FIRST_OPT = "firstopt";

    protected static final String CLASS_CHOICE = "classchoice";
    protected static final String INTERFACES = "interfaces";
    protected static final String INTERN_BEAN = "intern";

    protected static final String FCT = "(";
    protected static final char ARR_ANNOT = '{';
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
    protected static final String RETURN_TAB = "\n\t";

    protected static final String VARARG_SUFFIX = "...";
    protected static final String AROBASE = "@";

    private static final int QUICK_OP = 3;

    private MethodOperation parent;

    private OperationNode nextSibling;

    private Argument argument;

    private OperationsSequence operations;

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private final int indexChild;

    private ClassArgumentMatching resultClass;

    private boolean staticBlock;

    private PossibleIntermediateDotted siblingSet;

    OperationNode(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        indexChild = _indexChild;
    }

    public abstract void analyze(Analyzable _conf);

    public final void tryAnalyzeAssignmentAfter(Analyzable _conf) {
        Block currentBlock_ = _conf.getCurrentBlock();
        if (currentBlock_  == null) {
            return;
        }
        analyzeAssignmentAfter(_conf);
    }
    public abstract void analyzeAssignmentAfter(Analyzable _conf);
    public abstract void calculate(ExecutableCode _conf);
    public abstract void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current);
    public abstract void tryCalculateNode(Analyzable _conf);

    public abstract Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf);


    public final void setRelativeOffsetPossibleAnalyzable(int _offset, Analyzable _cont) {
        _cont.setAnalyzedOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, ExecutableCode _cont) {
        _cont.setOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public static OperationNode createOperationNode(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op, Analyzable _an) {
        if (!_op.getOperators().isEmpty()) {
            if (!_op.getValues().isEmpty()) {
                String originalStr_ = _op.getFctName();
                String str_ = originalStr_.trim();
                if (StringList.quickEq(str_, prefixFunction(INTERN_BEAN))) {
                    //qualified this
                    return new QualifiedThisOperation(_index, _indexChild, _m, _op);
                }
            }
        }
        if (_op.getOperators().isEmpty()) {
            ConstType ct_ = _op.getConstType();
            if (_op.getValues().isEmpty()) {
                return new EmptyPartOperation(_index, _indexChild, _m, _op);
            }
            String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (str_.isEmpty()) {
                return new EmptyPartOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.STATIC_ACCESS) {
                return new StaticAccessOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.VARARG) {
                return new VarargOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.CLASS_INFO) {
                return new StaticInfoOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.NUMBER) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.CHARACTER) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.STRING) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.THIS_KEYWORD) {
                return new ThisOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.TRUE_CST) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.FALSE_CST) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.NULL_CST) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.CUST_FIELD) {
                return new StandardFieldOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.CLASSCHOICE_KEYWORD) {
                return new ChoiceFieldOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.SUPER_KEYWORD) {
                return new SuperFieldOperation(_index, _indexChild, _m, _op);
            }
            if (_an.isEnabledInternVars()) {
                return new InternVariableOperation(_index, _indexChild, _m, _op);
            }
            if (_an.isInternGlobal() && StringList.quickEq(str_, prefixFunction(INTERN_BEAN))) {
                return new InternGlobalOperation(_index, _indexChild, _m, _op);
            }
            if (ElUtil.isDeclaringLoopVariable(_m, _an)) {
                return new MutableLoopVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ElUtil.isDeclaringVariable(_m, _an)) {
                return new VariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.PARAM) {
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.CATCH_VAR) {
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.LOOP_INDEX) {
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.LOOP_VAR) {
                if (_an.containsMutableLoopVar(str_)) {
                    return new MutableLoopVariableOperation(_index, _indexChild, _m, _op);
                }
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.LOC_VAR) {
                return new VariableOperation(_index, _indexChild, _m, _op);
            }
            if (_m instanceof DotOperation) {
                OperationNode ch_ = _m.getFirstChild();
                if (ch_ == null) {
                    return new StandardFieldOperation(_index, _indexChild, _m, _op);
                }
                if (ch_.getResultClass().isArray()) {
                    return new ArrayFieldOperation(_index, _indexChild, _m, _op);
                }
            }
            return new StandardFieldOperation(_index, _indexChild, _m, _op);
        }
        if (_op.isDeclaring()) {
            return new DeclaringOperation(_index, _indexChild, _m, _op);
        }
        if (_an.isAnnotAnalysis()) {
            String op_ = _op.getOperators().firstValue();
            if (StringList.quickEq(op_, String.valueOf(ARR_ANNOT))) {
                return new AnnotationInstanceOperation(_index, _indexChild, _m, _op);
            }
            String fctName_ = _op.getFctName().trim();
            if (fctName_.startsWith(AROBASE)) {
                return new AnnotationInstanceOperation(_index, _indexChild, _m, _op);
            }
        }
        if (_op.isCall()) {
            String fctName_ = _op.getFctName().trim();
            if (fctName_.isEmpty()) {
                return new IdOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, prefixFunction(VALUE_OF))) {
                return new EnumValueOfOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, prefixFunction(VALUES))) {
                return new ValuesOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, prefixFunction(BOOLEAN))) {
                return new TernaryOperation(_index, _indexChild, _m, _op);
            }
            if (fctName_.startsWith(prefixFunction(INSTANCE))) {
                return new InstanceOperation(_index, _indexChild, _m, _op);
            }
            if (ElResolver.procWordFirstChar(fctName_, 0, prefixFunction(CLASS_CHOICE))) {
                return new ChoiceFctOperation(_index, _indexChild, _m, _op);
            }
            if (ElResolver.procWordFirstChar(fctName_, 0, prefixFunction(INTERFACES))) {
                return new InterfaceInvokingConstructor(_index, _indexChild, _m, _op);
            }
            if (fctName_.startsWith(prefixFunction(FIRST_OPT))) {
                return new FirstOptOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_,prefixFunction(CURRENT))) {
                return new CurrentInvokingConstructor(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_,prefixFunction(SUPER_ACCESS))) {
                return new SuperInvokingConstructor(_index, _indexChild, _m, _op);
            }
            return new FctOperation(_index, _indexChild, _m, _op);
        }
        if (_op.isArray()) {
            return new ArrOperation(_index, _indexChild, _m, _op);
        }
        if (_op.isDot()) {
            return new DotOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.POST_INCR_PRIO) {
            return new SemiAffectationOperation(_index, _indexChild, _m, _op, true);
        }
        if (_op.getPriority() == ElResolver.UNARY_PRIO) {
            String value_ = _op.getOperators().firstValue().trim();
            if (StringList.quickEq(value_, NEG_BOOL)) {
                return new UnaryBooleanOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(value_, MINUS) || StringList.quickEq(value_, PLUS)) {
                return new UnaryOperation(_index, _indexChild, _m, _op);
            }
            if (value_.startsWith(MINUS) || value_.startsWith(PLUS)) {
                return new SemiAffectationOperation(_index, _indexChild, _m, _op, false);
            }
            return new CastOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.MULT_PRIO) {
            return new MultOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.ADD_PRIO) {
            return new AddOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.CMP_PRIO) {
            if (_op.isInstanceTest()) {
                return new InstanceOfOperation(_index, _indexChild, _m, _op);
            }
            if (_an.getOptions().isQuickCompare()) {
                return new QuickCmpOperation(_index, _indexChild, _m, _op);
            }
            return new CmpOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.EQ_PRIO) {
            return new EqOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.AND_PRIO) {
            return new AndOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.OR_PRIO) {
            return new OrOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.AFF_PRIO) {
            if (_an.isAnnotAnalysis()) {
                String value_ = _op.getValues().firstValue();
                return new AssocationOperation(_index, _indexChild, _m, _op, value_);
            }
            String op_ = _op.getOperators().firstValue();
            if (!StringList.quickEq(op_, EQ)) {
                return new CompoundAffectationOperation(_index, _indexChild, _m, _op);
            }
            return new AffectationOperation(_index, _indexChild, _m, _op);
        }
        return new DotOperation(_index, _indexChild, _m, _op);
    }

    final boolean isFirstChild() {
        MethodOperation par_ = getParent();
        if (par_ == null) {
            return true;
        }
        if (par_.getFirstChild() instanceof StaticInitOperation) {
            return getIndexChild() == CustList.SECOND_INDEX;
        }
        return getIndexChild() == CustList.FIRST_INDEX;
    }

    public abstract boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes);

    public abstract boolean isCalculated();

    public abstract ConstructorId getConstId();

    public abstract OperationNode getFirstChild();

    public final OperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(OperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    static FieldResult getDeclaredCustField(Analyzable _cont, boolean _staticContext, ClassArgumentMatching _class, boolean _baseClass, boolean _superClass, String _name, boolean _import) {
        if (!_staticContext) {
            FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _baseClass, _superClass, _name, _import);
            if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
                return resIns_;
            }
        }
        FieldResult resSt_ = getDeclaredCustFieldByContext(_cont, true, _class, _baseClass, _superClass, _name, _import);
        if (resSt_.getStatus() == SearchingMemberStatus.UNIQ) {
            return resSt_;
        }
        //Errors
        FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _baseClass, _superClass, _name, _import);
        if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
            StaticAccessFieldError access_ = new StaticAccessFieldError();
            access_.setClassName(_class.getNames().join(""));
            access_.setId(_name);
            access_.setFileName(_cont.getCurrentFileName());
            access_.setRc(_cont.getCurrentLocation());
            _cont.getClasses().addError(access_);
            return resIns_;
        }
        resSt_ = new FieldResult();
        resSt_.setStatus(SearchingMemberStatus.ZERO);
        return resSt_;
    }
    private static FieldResult getDeclaredCustFieldByContext(Analyzable _cont, boolean _static, ClassArgumentMatching _class, boolean _baseClass, boolean _superClass, String _name, boolean _import) {
        StringMap<String> clCurNames_ = new StringMap<String>();
        StringMap<String> clCurNamesBase_ = new StringMap<String>();
        StringList classeNames_ = new StringList();
        for (String c: _class.getNames()) {
            if (c.isEmpty()) {
                continue;
            }
            String base_ = Templates.getIdFromAllTypes(c);
            GeneType root_ = _cont.getClassBody(base_);
            if (_baseClass) {
                classeNames_.add(root_.getFullName());
            }
            if (_superClass) {
                classeNames_.addAllElts(root_.getAllSuperTypes());
            }
            for (String s: classeNames_) {
                clCurNamesBase_.put(s, base_);
                clCurNames_.put(s, c);
            }
        }
        String objectType_ = _cont.getStandards().getAliasObject();
        ObjectNotNullMap<ClassField,Integer> imports_ = new ObjectNotNullMap<ClassField,Integer>();
        String glClass_ = _cont.getGlobalClass();
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = Templates.getIdFromAllTypes(glClass_);
        }
        for (String s: classeNames_) {
            if (StringList.quickEq(s, objectType_)) {
                continue;
            }
            ClassField candidate_ = new ClassField(s, _name);
            FieldInfo fi_ = _cont.getFieldInfo(candidate_);
            if (fi_ == null) {
                continue;
            }
            if (_static) {
                if (!fi_.isStaticField()) {
                    continue;
                }
            } else {
                if (fi_.isStaticField()) {
                    continue;
                }
            }
            String baseLoc_ = clCurNamesBase_.getVal(s);
            if (!Classes.canAccessField(baseLoc_, s, _name, _cont)) {
                continue;
            }
            if (!Classes.canAccessField(curClassBase_, s, _name, _cont)) {
                continue;
            }
            imports_.put(candidate_, 0);
        }
        int max_ = 0;
        if (_import) {
            for (EntryCust<ClassField, Integer> e: _cont.lookupImportStaticFields(curClassBase_, _name, _cont.getCurrentBlock()).entryList()) {
                if (imports_.contains(e.getKey())) {
                    continue;
                }
                max_ = Math.max(max_, e.getValue());
                imports_.put(e.getKey(),e.getValue());
            }
        }
        max_++;
        for (int i = 0; i < max_; i++) {
            StringList subClasses_ = new StringList();
            for (EntryCust<ClassField,Integer> e: imports_.entryList()) {
                if (e.getValue().intValue() != i) {
                    continue;
                }
                subClasses_.add(e.getKey().getClassName());
            }
            StringList subs_ = PrimitiveTypeUtil.getSubclasses(subClasses_, _cont);
            if (subs_.size() == CustList.ONE_ELEMENT) {
                String cl_ = subs_.first();
                String formatted_;
                if (i == 0) {
                    String baseCl_ = clCurNames_.getVal(cl_);
                    formatted_ = Templates.getFullTypeByBases(baseCl_, cl_, _cont);
                } else {
                    formatted_ = cl_;
                }
                FieldInfo field_ = _cont.getFieldInfo(new ClassField(cl_, _name));
                FieldResult r_ = new FieldResult();
                String realType_ = field_.getType();
                FieldInfo f_ = FieldInfo.newFieldInfo(_name, formatted_, realType_, _static, field_.isFinalField(), field_.isEnumField(), _cont, false);
                r_.setId(f_);
                r_.setStatus(SearchingMemberStatus.UNIQ);
                return r_;
            }
        }
        FieldResult r_ = new FieldResult();
        r_.setStatus(SearchingMemberStatus.ZERO);
        return r_;
    }
    
    static ConstrustorIdVarArg getDeclaredCustConstructor(Analyzable _conf, int _varargOnly, ClassArgumentMatching _class,
    ClassArgumentMatching... _args) {
        String clCurName_ = _class.getNames().first();
        LgNames stds_ = _conf.getStandards();
        String glClass_ = _conf.getGlobalClass();
        CustList<ConstructorId> possibleMethods_ = new CustList<ConstructorId>();
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid(_conf)) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(stds_.getAliasVoid());
                mapping_.setParam(stds_.getAliasObject());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
        }
        CustList<GeneConstructor> constructors_ = Classes.getConstructorBodies(_conf, clCurName_);
        if (constructors_.isEmpty()) {
            if (_args.length == 0) {
                ConstrustorIdVarArg out_;
                out_ = new ConstrustorIdVarArg();
                out_.setRealId(new ConstructorId(clCurName_, new StringList(),false));
                out_.setConstId(out_.getRealId());
                return out_;
            }
        }
        for (GeneConstructor e: constructors_) {
            ConstructorId ctor_ = e.getId();
            if (_varargOnly > -1) {
                if (!ctor_.isVararg()) {
                    continue;
                }
            }
            CustList<GeneConstructor> ctors_ = TypeUtil.getConstructorBodiesById(clCurName_, ctor_, _conf);
            if (!Classes.canAccess(glClass_, ctors_.first(), _conf)) {
                continue;
            }
            ClassMatching[] p_ = getParameters(ctor_);
            if (!isPossibleMethod(_conf, clCurName_, _varargOnly, ctor_.isVararg(), p_, _args)) {
                continue;
            }
            possibleMethods_.add(ctor_);
        }
        if (possibleMethods_.isEmpty()) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getNames().join(""));
            }
            UndefinedConstructorError undefined_ = new UndefinedConstructorError();
            undefined_.setClassName(clCurName_);
            undefined_.setId(new ConstructorId(clCurName_, classesNames_,false));
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(undefined_);
            ConstrustorIdVarArg out_;
            out_ = new ConstrustorIdVarArg();
            out_.setRealId(undefined_.getId());
            out_.setConstId(undefined_.getId().format(clCurName_, _conf));
            return out_;
        }
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_, _args);
        gr_.setGlobalClass(glClass_);
        Parametrables<ConstructorInfo> signatures_ = new Parametrables<ConstructorInfo>();
        for (ConstructorId m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            for (String c: m.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            mloc_.setConstraints(m);
            mloc_.setParameters(p_);
            mloc_.setClassName(clCurName_);
            signatures_.add(mloc_);
        }
        sortCtors(signatures_, gr_);
        if (gr_.isAmbigous()) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getNames().join(""));
            }
            UndefinedConstructorError undefined_ = new UndefinedConstructorError();
            undefined_.setClassName(clCurName_);
            undefined_.setId(new ConstructorId(clCurName_, classesNames_, false));
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(undefined_);
            ConstrustorIdVarArg out_;
            out_ = new ConstrustorIdVarArg();
            out_.setRealId(undefined_.getId());
            out_.setConstId(undefined_.getId().format(clCurName_, _conf));
            return out_;
        }
        ConstructorId ctor_ = signatures_.first().getConstraints();
        ConstrustorIdVarArg out_;
        out_ = new ConstrustorIdVarArg();
        if (_varargOnly == -1 && varArgWrap(_conf, glClass_, clCurName_, ctor_, _args)) {
            out_.setVarArgToCall(true);
        }
        out_.setRealId(ctor_);
        out_.setConstId(ctor_.format(clCurName_, _conf));
        CustList<GeneConstructor> ctors_ = Classes.getConstructorBodiesById(_conf,clCurName_, ctor_);
        out_.setCtor(ctors_.first());
        return out_;
    }

    static ClassMethodIdReturn getDeclaredCustMethod(Analyzable _conf, int _varargOnly,
    boolean _staticContext, StringList _classes, String _name,
    boolean _superClass, boolean _accessFromSuper, boolean _import, ClassArgumentMatching... _argsClass) {
        ObjectNotNullMap<ClassMethodId, MethodInfo> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _staticContext,_varargOnly, _accessFromSuper, _superClass, _classes, _name, _import, _argsClass);
        ClassMethodIdResult res_= getCustResult(_conf, _varargOnly, methods_, _name, _argsClass);
        if (res_.getStatus() == SearchingMemberStatus.UNIQ) {
            return toFoundMethod(_conf, res_);
        }
        //Error
        if (_conf.isAmbigous()) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getNames().join(""));
            }
            UndefinedMethodError undefined_ = new UndefinedMethodError();
            MethodModifier mod_;
            if (_staticContext) {
                mod_ = MethodModifier.STATIC;
            } else {
                mod_ = MethodModifier.FINAL;
            }
            undefined_.setClassName(_classes);
            undefined_.setId(new MethodId(mod_, _name, classesNames_));
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(undefined_);
            ClassMethodIdReturn return_ = new ClassMethodIdReturn(false);
            return_.setId(new ClassMethodId(_classes.first(), new MethodId(mod_, _name, classesNames_)));
            return_.setRealId(new MethodId(mod_, _name, classesNames_));
            return_.setRealClass(_classes.first());
            return_.setStaticMethod(_staticContext);
            return_.setReturnType(_conf.getStandards().getAliasObject());
            return return_;
        }
        ClassMethodIdReturn return_ = new ClassMethodIdReturn(false);
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(c.getNames().join(""));
        }
        UndefinedMethodError undefined_ = new UndefinedMethodError();
        MethodModifier mod_;
        if (_staticContext) {
            mod_ = MethodModifier.STATIC;
        } else {
            mod_ = MethodModifier.FINAL;
        }
        undefined_.setClassName(_classes);
        undefined_.setId(new MethodId(mod_, _name, classesNames_));
        undefined_.setFileName(_conf.getCurrentFileName());
        undefined_.setRc(_conf.getCurrentLocation());
        _conf.getClasses().addError(undefined_);
        return_.setId(new ClassMethodId(_classes.first(), new MethodId(mod_, _name, classesNames_)));
        return_.setRealId(new MethodId(mod_, _name, classesNames_));
        return_.setRealClass(_classes.first());
        return_.setReturnType(_conf.getStandards().getAliasObject());
        return return_;
    }
    private static ClassMethodIdReturn toFoundMethod(Analyzable _conf, ClassMethodIdResult _res){
        ClassMethodIdReturn idRet_ = new ClassMethodIdReturn(true);
        ClassMethodId idCl_ = _res.getId();
        String clCurName_ = idCl_.getClassName();
        MethodId id_ = idCl_.getConstraints();
        MethodId realId_ = _res.getRealId();
        idRet_.setRealId(realId_);
        String realClass_ = _res.getRealClass();
        idRet_.setRealClass(realClass_);
        idRet_.setId(new ClassMethodId(clCurName_, id_));
        idRet_.setVarArgToCall(_res.isVarArgToCall());
        CustList<GeneMethod> methods_ = _conf.getMethodBodiesById(realClass_, realId_);
        GeneMethod m_ = methods_.first();
        idRet_.setReturnType(_res.getReturnType());
        idRet_.setStaticMethod(m_.isStaticMethod());
        idRet_.setAbstractMethod(m_.isAbstractMethod());
        return idRet_;
    }
    ClassMethodIdReturn getOperator(Analyzable _cont, String _op, ClassArgumentMatching... _argsClass) {
        ObjectNotNullMap<ClassMethodId, MethodInfo> ops_ = getOperators(_cont);
        ClassMethodIdResult res_ = getCustResult(_cont, -1, ops_, _op, _argsClass);
        ClassMethodIdReturn idRet_ = new ClassMethodIdReturn(res_.getStatus() == SearchingMemberStatus.UNIQ);
        ClassMethodId idCl_ = res_.getId();
        if (idCl_ == null) {
            return idRet_;
        }
        String clCurName_ = idCl_.getClassName();
        MethodId id_ = idCl_.getConstraints();
        MethodId realId_ = res_.getRealId();
        idRet_.setRealId(realId_);
        String realClass_ = res_.getRealClass();
        idRet_.setRealClass(realClass_);
        idRet_.setId(new ClassMethodId(clCurName_, id_));
        idRet_.setVarArgToCall(res_.isVarArgToCall());
        idRet_.setReturnType(res_.getReturnType());
        idRet_.setStaticMethod(true);
        idRet_.setAbstractMethod(false);
        return idRet_;
    }
    ObjectNotNullMap<ClassMethodId, MethodInfo> getOperators(Analyzable _cont){
        String objType_ = _cont.getStandards().getAliasObject();
        ObjectNotNullMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectNotNullMap<ClassMethodId, MethodInfo>();
        for (OperatorBlock o: _cont.getClasses().getOperators()) {
            String ret_ = o.getImportedReturnType();
            MethodId id_ = o.getId();
            ParametersGroup p_ = new ParametersGroup();
            MethodId realId_ = id_;
            for (String c: realId_.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            MethodInfo mloc_ = new MethodInfo();
            mloc_.setClassName(objType_);
            mloc_.setStatic(false);
            mloc_.setConstraints(realId_);
            mloc_.setParameters(p_);
            mloc_.setReturnType(ret_);
            ClassMethodId clId_ = new ClassMethodId(objType_, id_);
            methods_.put(clId_, mloc_);
        }
        return methods_;
    }
    private static ObjectNotNullMap<ClassMethodId, MethodInfo>
    getDeclaredCustMethodByType(Analyzable _conf, boolean _staticContext, int _varargOnly, boolean _accessFromSuper,
        boolean _superClass, StringList _fromClasses, String _name, boolean _import, ClassArgumentMatching... _argsClass) {
        String glClass_ = _conf.getGlobalClass();
        CustList<GeneType> roots_ = new CustList<GeneType>();
        ObjectNotNullMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectNotNullMap<ClassMethodId, MethodInfo>();
        StringList superTypes_ = new StringList();
        StringMap<String> superTypesBase_ = new StringMap<String>();
        for (String s: _fromClasses) {
            String baseCurName_ = Templates.getIdFromAllTypes(s);
            superTypes_.add(baseCurName_);
            superTypesBase_.put(baseCurName_,baseCurName_);
            GeneType root_ = _conf.getClassBody(baseCurName_);
            if (root_ == null) {
                continue;
            }
            roots_.add(root_);
            for (String m: root_.getAllSuperTypes()) {
                superTypesBase_.put(m, baseCurName_);
                superTypes_.add(m);
            }
        }
        for (String t: superTypes_) {
            GeneType root_ = _conf.getClassBody(t);
            for (GeneMethod e: ContextEl.getMethodBlocks(root_)) {
                if (!Classes.canAccess(glClass_, e, _conf)) {
                    continue;
                }
                String subType_ = superTypesBase_.getVal(t);
                if (!Classes.canAccess(subType_, e, _conf)) {
                    continue;
                }
                if (e.isStaticMethod()) {
                    MethodId id_ = e.getId();
                    String returnType_ = e.getImportedReturnType();
                    ParametersGroup p_ = new ParametersGroup();
                    MethodId realId_ = id_;
                    for (String c: realId_.getParametersTypes()) {
                        p_.add(new ClassMatching(c));
                    }
                    MethodInfo mloc_ = new MethodInfo();
                    mloc_.setClassName(t);
                    mloc_.setStatic(true);
                    mloc_.setConstraints(realId_);
                    mloc_.setParameters(p_);
                    mloc_.setReturnType(returnType_);
                    ClassMethodId clId_ = new ClassMethodId(t, id_);
                    methods_.put(clId_, mloc_);
                }
            }
        }
        if (!_staticContext){
            //TODO add numbering enclose
            int indexType_ = 0;
            for (GeneType t: roots_) {
                String clCurName_ = _fromClasses.get(indexType_);
                for (EntryCust<MethodId, EqList<ClassMethodId>> e: t.getAllOverridingMethods().entryList()) {
                    for (ClassMethodId s: e.getValue()) {
                        String name_ = s.getClassName();
                        if (_accessFromSuper) {
                            String base_ = Templates.getIdFromAllTypes(name_);
                            if (StringList.quickEq(base_, t.getFullName())) {
                                continue;
                            }
                        }
                        String formattedClass_;
                        if (_superClass) {
                            formattedClass_ = Templates.getFullTypeByBases(clCurName_, name_, _conf);
                        } else {
                            String base_ = Templates.getIdFromAllTypes(name_);
                            if (!StringList.quickEq(base_, t.getFullName())) {
                                continue;
                            }
                            formattedClass_ = clCurName_;
                        }
                        MethodId id_ = s.getConstraints();
                        GeneMethod sup_ = _conf.getMethodBodiesById(name_, id_).first();
                        if (!Classes.canAccess(glClass_, sup_, _conf)) {
                            continue;
                        }
                        String ret_ = sup_.getImportedReturnType();
                        ret_ = Templates.generalFormat(formattedClass_, ret_, _conf);
                        ParametersGroup p_ = new ParametersGroup();
                        MethodId realId_ = id_;
                        for (String c: realId_.getParametersTypes()) {
                            p_.add(new ClassMatching(c));
                        }
                        MethodInfo mloc_ = new MethodInfo();
                        mloc_.setClassName(formattedClass_);
                        mloc_.setStatic(false);
                        mloc_.setConstraints(realId_);
                        mloc_.setParameters(p_);
                        mloc_.setReturnType(ret_);
                        ClassMethodId clId_ = new ClassMethodId(formattedClass_, id_);
                        methods_.put(clId_, mloc_);
                    }
                }
                indexType_++;
            }
        }
        if (_import) {
            for (EntryCust<ClassMethodId, Integer> e: _conf.lookupImportStaticMethods(glClass_, _name, _conf.getCurrentBlock()).entryList()) {
                ClassMethodId m = e.getKey();
                if (methods_.contains(m)) {
                    continue;
                }
                String clName_ = m.getClassName();
                MethodId id_ = m.getConstraints();
                GeneMethod method_ = _conf.getMethodBodiesById(clName_, id_).first();
                String returnType_ = method_.getImportedReturnType();
                ParametersGroup p_ = new ParametersGroup();
                MethodId realId_ = id_;
                for (String c: realId_.getParametersTypes()) {
                    p_.add(new ClassMatching(c));
                }
                MethodInfo mloc_ = new MethodInfo();
                mloc_.setImported(e.getValue());
                mloc_.setClassName(clName_);
                mloc_.setStatic(true);
                mloc_.setConstraints(realId_);
                mloc_.setParameters(p_);
                mloc_.setReturnType(returnType_);
                methods_.put(m, mloc_);
            }
        }
        return methods_;
    }
    private static ClassMethodIdResult getCustResult(Analyzable _conf, int _varargOnly,
            ObjectNotNullMap<ClassMethodId, MethodInfo> _methods,
            String _name, ClassArgumentMatching... _argsClass) {
        CustList<ClassMethodId> possibleMethods_ = new CustList<ClassMethodId>();
        String glClass_ = _conf.getGlobalClass();
        for (EntryCust<ClassMethodId, MethodInfo> e: _methods.entryList()) {
            ClassMethodId key_ = e.getKey();
            MethodId id_ = key_.getConstraints();
            if (_varargOnly > -1) {
                if (!id_.isVararg()) {
                    continue;
                }
            }
            if (!StringList.quickEq(id_.getName(), _name)) {
                continue;
            }
            ClassMatching[] p_ = getParameters(id_);
            String formattedType_ = e.getValue().getClassName();
            if (!isPossibleMethod(_conf, formattedType_, _varargOnly, id_.isVararg(), p_, _argsClass)) {
                continue;
            }
            possibleMethods_.add(key_);
        }
        if (possibleMethods_.isEmpty()) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_, _argsClass);
        gr_.setGlobalClass(glClass_);
        Parametrables<MethodInfo> signatures_ = new Parametrables<MethodInfo>();
        for (ClassMethodId m: possibleMethods_) {
            MethodInfo info_ = _methods.getVal(m);
            signatures_.add(info_);
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
        MethodId realId_ = constraints_;
        String className_ = signatures_.first().getClassName();
        MethodInfo info_ = _methods.getVal(new ClassMethodId(className_, realId_));
        String baseClassName_ = info_.getClassName();
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        MethodId id_;
        String realClass_;
        boolean correctTemplated_ = Templates.correctNbParameters(baseClassName_, _conf);
        if (!correctTemplated_) {
            realClass_ = Templates.getGenericString(baseClassName_, _conf);
            id_ = constraints_;
        } else {
            realClass_ = baseClassName_;
            if (info_.isStatic()) {
                id_ = constraints_;
            } else {
                id_ = constraints_.format(baseClassName_, _conf);
            }
            res_.setCorrectTemplated(true);
        }
        res_.setId(new ClassMethodId(realClass_, id_));
        res_.setStatus(SearchingMemberStatus.UNIQ);
        if (_varargOnly == -1 && varArgWrap(_conf, glClass_, realClass_, constraints_, _argsClass)) {
            res_.setVarArgToCall(true);
        }
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(info_.getReturnType());
        return res_;
    }

    static boolean isPossibleMethod(Analyzable _context, String _class, int _varargOnly, boolean _vararg, ClassMatching[] _params,
    ClassArgumentMatching... _argsClass) {
        int startOpt_ = _argsClass.length;
        boolean checkOnlyDem_ = true;
        int nbDem_ = _params.length;
        if (!_vararg) {
            if (_params.length != _argsClass.length) {
                return false;
            }
        } else {
            if (_params.length > _argsClass.length + 1) {
                return false;
            }
            if (_varargOnly != 0) {
                checkOnlyDem_ = false;
                nbDem_--;
                startOpt_ = _params.length - 1;
            }
            if (_varargOnly > 0) {
                if (startOpt_ != _varargOnly - 1) {
                    return false;
                }
            }
        }
        String glClass_ = _context.getGlobalClass();
        CustList<TypeVar> vars_;
        if (glClass_ != null) {
            vars_ = Templates.getConstraints(glClass_, _context);
        } else {
            vars_ = new CustList<TypeVar>();
        }
        int len_ = nbDem_;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (_argsClass[i].isVariable()) {
                if (_params[i].isPrimitive(_context)) {
                    return false;
                }
                continue;
            }
            Mapping map_ = new Mapping();
            map_.setArg(_argsClass[i]);
            for (TypeVar t: vars_) {
                map_.getMapping().put(t.getName(), t.getConstraints());
            }
            map_.setParam(Templates.generalFormat(_class, _params[i].getClassName(), _context));
            if (!Templates.isCorrect(map_, _context)) {
                return false;
            }
        }
        if (checkOnlyDem_) {
            return true;
        }
        if (_params.length == _argsClass.length) {
            int last_ = _params.length - 1;
            Mapping map_ = new Mapping();
            map_.setArg(_argsClass[last_]);
            for (TypeVar t: vars_) {
                map_.getMapping().put(t.getName(), t.getConstraints());
            }
            String param_ = _params[last_].getClassName();
            map_.setParam(Templates.generalFormat(_class, param_, _context));
            if (Templates.isGenericCorrect(map_, _context)) {
                return true;
            }
            param_ = PrimitiveTypeUtil.getQuickComponentType(param_);
            map_.setParam(Templates.generalFormat(_class, param_, _context));
            return Templates.isGenericCorrect(map_, _context);
        }
        len_ = _argsClass.length;
        Mapping map_ = new Mapping();
        int last_ = _params.length - 1;
        String param_ = _params[last_].getClassName();
        param_ = PrimitiveTypeUtil.getQuickComponentType(param_);
        for (TypeVar t: vars_) {
            map_.getMapping().put(t.getName(), t.getConstraints());
        }
        map_.setParam(Templates.generalFormat(_class, param_, _context));
        for (int i = startOpt_; i < len_; i++) {
            map_.setArg(_argsClass[i]);
            if (!Templates.isGenericCorrect(map_, _context)) {
                return false;
            }
        }
        return true;
    }
    static boolean varArgWrap(Analyzable _context, String _globalClass, String _class, Identifiable _id,
    ClassArgumentMatching... _argsClass) {
        if (!_id.isVararg()) {
            return false;
        }
        ClassMatching[] p_ = getParameters(_id);
        if (p_.length != _argsClass.length) {
            return true;
        }
        String glClass_ = _globalClass;
        CustList<TypeVar> vars_;
        if (glClass_ != null) {
            vars_ = Templates.getConstraints(glClass_, _context);
        } else {
            vars_ = new CustList<TypeVar>();
        }
        Mapping map_ = new Mapping();
        int last_ = p_.length - 1;
        String param_ = p_[last_].getClassName();
        for (TypeVar t: vars_) {
            map_.getMapping().put(t.getName(), t.getConstraints());
        }
        map_.setArg(_argsClass[last_]);
        map_.setParam(Templates.generalFormat(_class, param_, _context));
        return !Templates.isGenericCorrect(map_, _context);
    }
    static ClassMatching[] getParameters(Identifiable _id) {
        StringList params_ = _id.getParametersTypes();
        int nbParams_ = params_.size();
        ClassMatching[] p_ = new ClassMatching[nbParams_];
        int i_ = CustList.FIRST_INDEX;
        if (!_id.isVararg()) {
            for (String c: params_) {
                p_[i_] = new ClassMatching(c);
                i_++;
            }
        } else {
            for (String c: params_) {
                if (i_ == nbParams_ - 1) {
                    String c_ = StringList.replace(c, VARARG_SUFFIX, EMPTY_STRING);
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                    p_[i_] = new ClassMatching(c_);
                } else {
                    p_[i_] = new ClassMatching(c);
                }
                i_++;
            }
        }
        return p_;
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
    static int compare(ArgumentsGroup _context, Parametrable _o1, Parametrable _o2) {
        if (_o1.getImported() > _o2.getImported()) {
            return CustList.SWAP_SORT;
        }
        if (_o2.getImported() > _o1.getImported()) {
            return CustList.NO_SWAP_SORT;
        }
        int len_ = _o1.getParameters().size();
        Analyzable context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        String glClass_ = _context.getGlobalClass();
        String glClassOne_ = _o1.getClassName();
        String glClassTwo_ = _o2.getClassName();
        if (_o1.isStatic()) {
            if (!_o2.isStatic()) {
                return CustList.SWAP_SORT;
            }
        }
        if (!_o1.isStatic()) {
            if (_o2.isStatic()) {
                return CustList.NO_SWAP_SORT;
            }
        }
        if (_o1.isVararg()) {
            if (!_o2.isVararg()) {
                return CustList.SWAP_SORT;
            }
        }
        if (!_o1.isVararg()) {
            if (_o2.isVararg()) {
                return CustList.NO_SWAP_SORT;
            }
        }
        boolean vararg_ = false;
        if (_o1.isVararg()) {
            if (_o2.isVararg()) {
                if (len_ < _o2.getParameters().size()) {
                    return CustList.SWAP_SORT;
                }
                if (len_ > _o2.getParameters().size()) {
                    return CustList.NO_SWAP_SORT;
                }
                boolean varOne_ = varArgWrap(context_, glClass_, glClassOne_, _o1.getId(), _context.getArgumentsArray());
                boolean varTwo_ = varArgWrap(context_, glClass_, glClassTwo_, _o2.getId(), _context.getArgumentsArray());
                if (varOne_ && !varTwo_) {
                    return CustList.SWAP_SORT;
                }
                if (!varOne_ && varTwo_) {
                    return CustList.NO_SWAP_SORT;
                }
                vararg_ = true;
            }
        }
        if (vararg_) {
            len_--;
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            ClassArgumentMatching selected_ = _context.get(i);
            ClassMatching one_ = _o1.getParameters().get(i);
            String paramOne_ = one_.getClassName();
            ClassMatching two_ = _o2.getParameters().get(i);
            String paramTwo_ = two_.getClassName();
            one_ = new ClassMatching(Templates.generalFormat(glClassOne_, paramOne_, context_));
            two_ = new ClassMatching(Templates.generalFormat(glClassTwo_, paramTwo_, context_));
            if (one_.matchClass(two_)) {
                continue;
            }
            if (selected_.isVariable()) {
                if (one_.isAssignableFrom(two_, map_, context_)) {
                    return CustList.SWAP_SORT;
                }
                if (two_.isAssignableFrom(one_, map_, context_)) {
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
            if (one_.isPrimitive(context_) && !two_.isPrimitive(context_)) {
                onePrimExcl_ = true;
            }
            if (!one_.isPrimitive(context_) && two_.isPrimitive(context_)) {
                twoPrimExcl_ = true;
            }
            if (selected_.isPrimitive(context_)) {
                if (onePrimExcl_) {
                    return CustList.NO_SWAP_SORT;
                }
                if (twoPrimExcl_) {
                    return CustList.SWAP_SORT;
                }
                toPrOne_ = PrimitiveTypeUtil.toPrimitive(one_, context_);
                toPrTwo_ = PrimitiveTypeUtil.toPrimitive(two_, context_);
            } else {
                ClassArgumentMatching clMatch_ = PrimitiveTypeUtil.toPrimitive(selected_, true, context_);
                if (clMatch_.isPrimitive(context_)) {
                    if (onePrimExcl_) {
                        return CustList.SWAP_SORT;
                    }
                    if (twoPrimExcl_) {
                        return CustList.NO_SWAP_SORT;
                    }
                    toPrOne_ = PrimitiveTypeUtil.toPrimitive(one_, context_);
                    toPrTwo_ = PrimitiveTypeUtil.toPrimitive(two_, context_);
                }
            }
            if (toPrOne_.isAssignableFrom(toPrTwo_, map_, context_)) {
                return CustList.SWAP_SORT;
            }
            if (toPrTwo_.isAssignableFrom(toPrOne_, map_, context_)) {
                return CustList.NO_SWAP_SORT;
            }
            _o1.getParameters().setError(true);
            _o2.getParameters().setError(true);
            return CustList.NO_SWAP_SORT;
        }
        if (vararg_) {
            ClassMatching one_ = _o1.getParameters().last();
            String paramOne_ = one_.getClassName();
            paramOne_ = StringList.replace(paramOne_, VARARG_SUFFIX, EMPTY_STRING);
            paramOne_ = PrimitiveTypeUtil.getPrettyArrayType(paramOne_);
            ClassMatching two_ = _o2.getParameters().last();
            String paramTwo_ = two_.getClassName();
            paramTwo_ = StringList.replace(paramTwo_, VARARG_SUFFIX, EMPTY_STRING);
            paramTwo_ = PrimitiveTypeUtil.getPrettyArrayType(paramTwo_);
            one_ = new ClassMatching(Templates.generalFormat(glClassOne_, paramOne_, context_));
            two_ = new ClassMatching(Templates.generalFormat(glClassTwo_, paramTwo_, context_));
            if (!one_.matchClass(two_)) {
                if (one_.isAssignableFrom(two_, map_, context_)) {
                    return CustList.SWAP_SORT;
                }
                if (two_.isAssignableFrom(one_, map_, context_)) {
                    return CustList.NO_SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }
        }
        String baseTypeOne_ = Templates.getIdFromAllTypes(glClassOne_);
        String baseTypeTwo_ = Templates.getIdFromAllTypes(glClassTwo_);
        if (StringList.quickEq(_o2.getReturnType(), _o1.getReturnType())) {
            if (!PrimitiveTypeUtil.canBeUseAsArgument(baseTypeTwo_, baseTypeOne_, context_)) {
                return CustList.SWAP_SORT;
            }
            return CustList.NO_SWAP_SORT;
        }
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(map_);
        mapping_.setArg(_o2.getReturnType());
        mapping_.setParam(_o1.getReturnType());
        if (Templates.isCorrect(mapping_, context_)) {
            return CustList.SWAP_SORT;
        }
        mapping_.setArg(_o1.getReturnType());
        mapping_.setParam(_o2.getReturnType());
        if (Templates.isCorrect(mapping_, context_)) {
            return CustList.NO_SWAP_SORT;
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(baseTypeOne_, baseTypeTwo_, context_)) {
            return CustList.SWAP_SORT;
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(baseTypeTwo_, baseTypeOne_, context_)) {
            return CustList.NO_SWAP_SORT;
        }
        //inherits types if static methods
        _o1.getParameters().setError(true);
        _o2.getParameters().setError(true);
        return CustList.NO_SWAP_SORT;
    }

    final void setNextSiblingsArg(Argument _arg, ExecutableCode _cont) {
        if (_cont.getException() != null) {
            return;
        }
        String un_ = resultClass.getUnwrapObject();
        if (resultClass.isCheckOnlyNullPe() || !un_.isEmpty()) {
            if (_arg.isNull()) {
                LgNames stds_ = _cont.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                return;
            }
        }
        if (!un_.isEmpty()) {
            _arg.setStruct(PrimitiveTypeUtil.unwrapObject(un_, _arg.getStruct(), _cont.getStandards()));
        }
        int res_ = processBooleanValues(_arg, _cont);
        if (res_ <= 0) {
            return;
        }
        MethodOperation par_ = getParent();
        Object o_ = _arg.getObject();
        Boolean b_ = (Boolean) o_;
        if (res_ != QUICK_OP) {
            CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
            OperationNode opElt_ = l_.get(res_);
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
        if (_cont.getException() != null) {
            return;
        }
        String un_ = resultClass.getUnwrapObject();
        if (resultClass.isCheckOnlyNullPe() || !un_.isEmpty()) {
            if (_arg.isNull()) {
                LgNames stds_ = _cont.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                return;
            }
        }
        if (!un_.isEmpty()) {
            _arg.setStruct(PrimitiveTypeUtil.unwrapObject(un_, _arg.getStruct(), _cont.getStandards()));
        }
        int res_ = processBooleanValues(_arg, _cont);
        if (res_ <= 0) {
            return;
        }
        Object o_ = _arg.getObject();
        MethodOperation par_ = getParent();
        Boolean b_ = (Boolean) o_;
        if (res_ != QUICK_OP) {
            CustList<OperationNode> l_ = ElUtil.getDirectChildren(par_);
            OperationNode opElt_ = l_.get(res_);
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

    final int processBooleanValues(Argument _arg, ExecutableCode _cont) {
        Object o_ = _arg.getObject();
        MethodOperation par_ = getParent();
        if (!(o_ instanceof Boolean)) {
            return 0;
        }
        if (!(par_ instanceof QuickOperation)) {
            boolean ternaryParent_ = false;
            if (par_ instanceof TernaryOperation) {
                ternaryParent_ = isFirstChild();
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
        return QUICK_OP;
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

    public final Argument getArgument() {
        return argument;
    }

    public final void setSimpleArgument(Argument _argument) {
        argument = _argument;
    }

    public final void setArguments(Argument _argument) {
        argument = _argument;
    }

    public final void setQuickSimpleArgument(Argument _argument, ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes) {
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getVal((OperationNode)n_).setPreviousArgument(_argument);
        }
    }

    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes) {
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getVal((OperationNode)n_).setPreviousArgument(_argument);
        }
        setNextSiblingsArg(_argument, _conf, _nodes);
    }

    public final void setQuickSimpleArgument(Argument _argument, ExecutableCode _conf) {
        argument = _argument;
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
    }

    public final void setSimpleArgument(Argument _argument, ExecutableCode _conf) {
        argument = _argument;
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
        setNextSiblingsArg(_argument, _conf);
    }

    public final void setSimpleArgumentAna(Argument _argument, Analyzable _conf) {
        argument = _argument;
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
        String un_ = resultClass.getUnwrapObject();
        if (!un_.isEmpty()) {
            argument.setStruct(PrimitiveTypeUtil.unwrapObject(un_, argument.getStruct(), _conf.getStandards()));
        }
    }
    public final boolean isStaticBlock() {
        return staticBlock;
    }

    public final void setStaticBlock(boolean _staticBlock) {
        staticBlock = _staticBlock;
    }

    public final boolean isVoidArg(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        return resultClass.matchVoid(stds_);
    }

    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final void setResultClass(ClassArgumentMatching _resultClass) {
        resultClass = _resultClass;
    }

    public final void setStaticResultClass(ClassArgumentMatching _resultClass) {
        resultClass = _resultClass;
    }

    public PossibleIntermediateDotted getSiblingToSet() {
        OperationNode n_ = getNextSibling();
        if (n_ == null) {
            return null;
        }
        if (!(getParent() instanceof DotOperation)) {
            return null;
        }
        if (!(n_ instanceof PossibleIntermediateDotted)) {
            OperationNode child_ = n_.getFirstChild();
            while (!(child_ instanceof PossibleIntermediateDotted)) {
                child_ = child_.getFirstChild();
            }
            return (PossibleIntermediateDotted)child_;
        }
        return (PossibleIntermediateDotted)n_;
    }

    protected static String prefixFunction(String _fct) {
        return StringList.concat(String.valueOf(EXTERN_CLASS), _fct);
    }

    public PossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public void setSiblingSet(PossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }
}
