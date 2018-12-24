package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.StaticAccessFieldError;
import code.expressionlanguage.errors.custom.UndefinedConstructorError;
import code.expressionlanguage.errors.custom.UndefinedMethodError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ConstType;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.OperatorBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.ArgumentsGroup;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorInfo;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.Identifiable;
import code.expressionlanguage.opers.util.InvocationMethod;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.ParametersGroup;
import code.expressionlanguage.opers.util.Parametrable;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class OperationNode implements Operable {

    protected static final char ESCAPE_META_CHAR = '\\';
    protected static final char DELIMITER_CHAR = 39;
    protected static final char DELIMITER_STRING = 34;
    protected static final char ARR_LEFT = '[';
    protected static final char ARR_RIGHT = ']';
    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';
    protected static final String PAR_RIGHT_STR = ")";
    protected static final char SEP_ARG = ',';
    protected static final char FIRST_VAR_ARG = '?';
    protected static final char DOT_VAR = '.';
    protected static final char MIN_ENCODE_DIGIT = '0';
    protected static final char MAX_ENCODE_DIGIT = '9';
    protected static final char MIN_ENCODE_LOW_LETTER = 'a';
    protected static final char MAX_ENCODE_LOW_LETTER = 'f';
    protected static final char MIN_ENCODE_UPP_LETTER = 'A';
    protected static final char MAX_ENCODE_UPP_LETTER = 'F';
    protected static final String VAR_ARG = "$vararg";
    protected static final String FIRST_OPT = "$firstopt";

    protected static final String FCT = "(";
    protected static final char ARR_ANNOT = '{';
    protected static final String ARR = "[";

    protected static final String ARR_DYN = "[]";

    protected static final String DOT = ".";

    protected static final String NEG_BOOL = "!";
    protected static final String NEG_BOOL_BIN = "~";

    protected static final String UNARY_PLUS = "+";

    protected static final String UNARY_MINUS = "-";

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String MOD = "%";

    protected static final String PLUS = "+";

    protected static final String MINUS = "-";

    protected static final String SHIFT_LEFT = "<<";
    protected static final String SHIFT_RIGHT = ">>";
    protected static final String BIT_SHIFT_LEFT = "<<<";
    protected static final String BIT_SHIFT_RIGHT = ">>>";
    protected static final String ROTATE_LEFT = "<<<<";
    protected static final String LOWER_EQ = "<=";

    protected static final String LOWER = "<";

    protected static final String GREATER_EQ = ">=";

    protected static final String GREATER = ">";

    protected static final String AFF = "=";

    protected static final String DIFF = "!=";

    protected static final String AND = "&";

    protected static final String OR = "|";
    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";
    protected static final String SPACE = " ";
    protected static final String RETURN_TAB = "\n\t";

    protected static final String VARARG_SUFFIX = "...";
    protected static final String AROBASE = "@";

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
        if (currentBlock_ == null) {
            return;
        }
        analyzeAssignmentAfter(_conf);
    }
    public abstract void analyzeAssignmentAfter(Analyzable _conf);

    final boolean isCallMethodCtor(){
        if (!(this instanceof InvokingOperation)) {
            return false;
        }
        if (this instanceof AbstractArrayInstancingOperation) {
            return false;
        }
        return !(this instanceof AnnotationInstanceOperation);
    }

    public final void setRelativeOffsetPossibleAnalyzable(int _offset, Analyzable _cont) {
        _cont.setAnalyzedOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, ExecutableCode _cont) {
        _cont.setOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public static OperationNode createOperationNode(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op, Analyzable _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordBool_ = keyWords_.getKeyWordBool();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordFirstopt_ = keyWords_.getKeyWordFirstopt();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        String keyWordValues_ = keyWords_.getKeyWordValues();
        ConstType ct_ = _op.getConstType();
        if (ct_ == ConstType.ERROR) {
            return new ErrorPartOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getOperators().isEmpty()) {
            String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (ct_ == ConstType.CHARACTER) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.STRING) {
                return new ConstantOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.SIMPLE_ANNOTATION) {
                return new AnnotationInstanceOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.STATIC_ACCESS) {
                return new StaticAccessOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.VARARG) {
                return new VarargOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.ID) {
                return new IdFctOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.LAMBDA) {
                return new LambdaOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.CLASS_INFO) {
                return new StaticInfoOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.NUMBER) {
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
            if (ct_ == ConstType.SUPER_ACCESS_KEYWORD) {
                return new SuperFromFieldOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.SUPER_KEYWORD) {
                return new SuperFieldOperation(_index, _indexChild, _m, _op);
            }
            if (_an.isEnabledInternVars()) {
                return new InternVariableOperation(_index, _indexChild, _m, _op);
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
        if (_op.isCallDbArray()) {
            String fctName_ = _op.getFctName().trim();
            if (StringList.quickEq(fctName_, _an.getStandards().getAliasCall()) && _m != null) {
                OperationNode ch_ = _m.getFirstChild();
                if (ch_ != null) {
                    StringList pr_ = ch_.getResultClass().getNames();
                    if (pr_.size() == 1) {
                        String type_ = pr_.first();
                        String id_ = Templates.getIdFromAllTypes(type_);
                        String fct_ = _an.getStandards().getAliasFct();
                        if (StringList.quickEq(id_, fct_)) {
                            return new CallDynMethodOperation(_index, _indexChild, _m, _op);
                        }
                    }
                }
            }
            if (_op.isInstance()) {
                if (fctName_.isEmpty()) {
                    return new InferArrayInstancing(_index, _indexChild, _m, _op);
                }
                char op_ = _op.getOperators().firstValue().charAt(0);
                if (op_ == '{') {
                    return new ElementArrayInstancing(_index, _indexChild, _m, _op);
                }
                if (op_ == '[') {
                    return new DimensionArrayInstancing(_index, _indexChild, _m, _op);
                }
                return new StandardInstancingOperation(_index, _indexChild, _m, _op);
            }
            if (fctName_.isEmpty()) {
                return new IdOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, keyWordValueOf_)) {
                return new EnumValueOfOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, keyWordValues_)) {
                return new ValuesOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, keyWordBool_)) {
                return new TernaryOperation(_index, _indexChild, _m, _op);
            }
            if (ContextEl.startsWithKeyWord(fctName_, keyWordClasschoice_)) {
                return new ChoiceFctOperation(_index, _indexChild, _m, _op);
            }
            if (ContextEl.startsWithKeyWord(fctName_, keyWordSuperaccess_)) {
                return new SuperFctOperation(_index, _indexChild, _m, _op);
            }
            if (ContextEl.startsWithKeyWord(fctName_, keyWordInterfaces_)) {
                return new InterfaceInvokingConstructor(_index, _indexChild, _m, _op);
            }
            if (ContextEl.startsWithKeyWord(fctName_, keyWordFirstopt_)) {
                return new FirstOptOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_,keyWordThis_)) {
                return new CurrentInvokingConstructor(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_,keyWordSuper_)) {
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
            if (StringList.quickEq(value_, NEG_BOOL_BIN)) {
                return new UnaryBinOperation(_index, _indexChild, _m, _op);
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
        if (_op.getPriority() == ElResolver.SHIFT_PRIO) {
            String value_ = _op.getOperators().firstValue().trim();
            if (StringList.quickEq(value_, SHIFT_LEFT)) {
                return new ShiftLeftOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(value_, SHIFT_RIGHT)) {
                return new ShiftRightOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(value_, BIT_SHIFT_LEFT)) {
                return new BitShiftLeftOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(value_, BIT_SHIFT_RIGHT)) {
                return new BitShiftRightOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(value_, ROTATE_LEFT)) {
                return new RotateLeftOperation(_index, _indexChild, _m, _op);
            }
            return new RotateRightOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.CMP_PRIO) {
            if (_op.isInstanceTest()) {
                return new InstanceOfOperation(_index, _indexChild, _m, _op);
            }
            return new CmpOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.EQ_PRIO) {
            return new EqOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.BIT_AND_PRIO) {
            return new BitAndOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.BIT_XOR_PRIO) {
            return new BitXorOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.BIT_OR_PRIO) {
            return new BitOrOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.AND_PRIO) {
            return new AndOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.OR_PRIO) {
            return new OrOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.TERNARY_PRIO) {
            return new ShortTernaryOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.AFF_PRIO) {
            if (_an.isAnnotAnalysis()) {
                String value_ = _op.getValues().firstValue();
                return new AssocationOperation(_index, _indexChild, _m, _op, value_);
            }
            String op_ = _op.getOperators().firstValue();
            if (!StringList.quickEq(op_, AFF)) {
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

    public abstract OperationNode getFirstChild();

    public final OperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(OperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    static FieldResult getDeclaredCustField(Analyzable _cont, boolean _staticContext, ClassArgumentMatching _class, boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        FieldResult fr_ = resolveDeclaredCustField(_cont, _staticContext, _class, _baseClass, _superClass, _name, _import, _aff);
        if (fr_.getStatus() == SearchingMemberStatus.UNIQ && fr_.getId().getType() != null) {
            return fr_;
        }
        StaticAccessFieldError access_ = new StaticAccessFieldError();
        access_.setClassName(_class.getNames().join(""));
        access_.setId(_name);
        access_.setFileName(_cont.getCurrentFileName());
        access_.setIndexFile(_cont.getCurrentLocationIndex());
        _cont.getClasses().addError(access_);
        FieldResult res_ = new FieldResult();
        res_.setStatus(SearchingMemberStatus.ZERO);
        return res_;
    }

    public static FieldResult resolveDeclaredCustField(Analyzable _cont, boolean _staticContext, ClassArgumentMatching _class, boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        if (!_staticContext) {
            FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _baseClass, _superClass, _name, _import, _aff);
            if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
                return resIns_;
            }
        }
        FieldResult resSt_ = getDeclaredCustFieldByContext(_cont, true, _class, _baseClass, _superClass, _name, _import,_aff);
        return resSt_;
    }
    private static FieldResult getDeclaredCustFieldByContext(Analyzable _cont, boolean _static, ClassArgumentMatching _class, boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        StringMap<String> clCurNames_ = new StringMap<String>();
        StringMap<String> clCurNamesBase_ = new StringMap<String>();
        StringList classeNames_ = new StringList();
        for (String c: _class.getNames()) {
            if (c.isEmpty()) {
                continue;
            }
            StringList classeNamesLoc_ = new StringList();
            String base_ = Templates.getIdFromAllTypes(c);
            GeneType root_ = _cont.getClassBody(base_);
            if (root_ == null) {
                continue;
            }
            if (_baseClass) {
                classeNamesLoc_.add(root_.getFullName());
            }
            if (_superClass) {
                classeNamesLoc_.addAllElts(root_.getAllSuperTypes());
            }
            for (String s: classeNamesLoc_) {
                clCurNamesBase_.put(s, base_);
                clCurNames_.put(s, c);
            }
            classeNames_.addAllElts(classeNamesLoc_);
        }
        classeNames_.removeDuplicates();
        String objectType_ = _cont.getStandards().getAliasObject();
        ObjectNotNullMap<ClassField,Integer> imports_ = new ObjectNotNullMap<ClassField,Integer>();
        ObjectNotNullMap<ClassField,Integer> ancestors_ = new ObjectNotNullMap<ClassField,Integer>();
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
            String formatted_;
            String baseCl_ = clCurNames_.getVal(s);
            if (Templates.correctNbParameters(baseCl_, _cont)) {
                formatted_ = Templates.getFullTypeByBases(baseCl_, s, _cont);
            } else {
                formatted_ = s;
            }
            if (formatted_ == null) {
                continue;
            }
            String realType_ = fi_.getType();
            boolean finalField_ = fi_.isFinalField();
            boolean staticField_ = fi_.isStaticField();
            if (FieldInfo.newFieldInfo(_name, formatted_, realType_, _static, finalField_, staticField_, _cont, _aff) == null) {
                continue;
            }
            imports_.add(candidate_, 0);
            ancestors_.add(candidate_, 0);
        }
        int maxAnc_ = 0;
        for (String c: _class.getNames()) {
            if (c.isEmpty()) {
                continue;
            }
            int anc_ = 1;
            boolean keepInstance_ = true;
            String base_ = Templates.getIdFromAllTypes(c);
            GeneType root_ = _cont.getClassBody(base_);
            if (!(root_ instanceof RootBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) root_;
            if (r_.isStaticType()) {
                keepInstance_ = false;
            }
            for (RootBlock p : r_.getAllParentTypes()) {
                String f_ = p.getGenericString();
                if (Templates.correctNbParameters(c, _cont)) {
                    f_ = Templates.quickFormat(c, f_, _cont);
                }
                String baseLoc_ = Templates.getIdFromAllTypes(f_);
                StringList classeNamesPar_ = new StringList();
                if (_baseClass) {
                    classeNamesPar_.add(p.getFullName());
                }
                if (_superClass) {
                    classeNamesPar_.addAllElts(p.getAllSuperTypes());
                }
                for (String s: classeNamesPar_) {
                    clCurNamesBase_.put(s, baseLoc_);
                    clCurNames_.put(s, f_);
                }
                for (String s: classeNamesPar_) {
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
                    String basLoc_ = clCurNamesBase_.getVal(s);
                    if (!Classes.canAccessField(basLoc_, s, _name, _cont)) {
                        continue;
                    }
                    if (!Classes.canAccessField(curClassBase_, s, _name, _cont)) {
                        continue;
                    }
                    if (!keepInstance_ && !_static) {
                        continue;
                    }
                    String formatted_;
                    String baseCl_ = clCurNames_.getVal(s);
                    if (Templates.correctNbParameters(baseCl_, _cont)) {
                        formatted_ = Templates.getFullTypeByBases(baseCl_, s, _cont);
                    } else {
                        formatted_ = s;
                    }
                    if (formatted_ == null) {
                        continue;
                    }
                    String realType_ = fi_.getType();
                    boolean finalField_ = fi_.isFinalField();
                    boolean staticField_ = fi_.isStaticField();
                    if (FieldInfo.newFieldInfo(_name, formatted_, realType_, _static, finalField_, staticField_, _cont, _aff) == null) {
                        continue;
                    }
                    imports_.add(candidate_, anc_);
                    ancestors_.add(candidate_, anc_);
                }
                if (p.isStaticType()) {
                    keepInstance_ = false;
                }
                anc_++;
            }
            maxAnc_ = Math.max(maxAnc_, anc_);
        }
        int max_ = maxAnc_;
        for (int i = 0; i <= maxAnc_; i++) {
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
                String baseCl_ = clCurNames_.getVal(cl_);
                String formatted_;
                if (Templates.correctNbParameters(baseCl_, _cont)) {
                    formatted_ = Templates.getFullTypeByBases(baseCl_, cl_, _cont);
                } else {
                    formatted_ = cl_;
                }
                ClassField id_ = new ClassField(cl_, _name);
                FieldInfo field_ = _cont.getFieldInfo(id_);
                FieldResult r_ = new FieldResult();
                String realType_ = field_.getType();
                FieldInfo f_ = FieldInfo.newFieldInfo(_name, formatted_, realType_, _static, field_.isFinalField(), field_.isEnumField(), _cont, _aff);
                r_.setId(f_);
                r_.setAnc(ancestors_.getVal(id_));
                r_.setStatus(SearchingMemberStatus.UNIQ);
                return r_;
            }
        }
        if (_import) {
            Block curBlock_ = _cont.getCurrentBlock();
            int maxLoc_ = maxAnc_ + 1;
            for (EntryCust<ClassField, Integer> e: _cont.lookupImportStaticFields(curClassBase_, _name, curBlock_).entryList()) {
                max_ = Math.max(max_, e.getValue()+maxAnc_);
                imports_.add(e.getKey(),e.getValue()+maxAnc_);
                ancestors_.add(e.getKey(),0);
            }
            for (int i = maxLoc_; i <= max_; i++) {
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
                    ClassField id_ = new ClassField(cl_, _name);
                    FieldInfo field_ = _cont.getFieldInfo(id_);
                    FieldResult r_ = new FieldResult();
                    String realType_ = field_.getType();
                    FieldInfo f_ = FieldInfo.newFieldInfo(_name, cl_, realType_, _static, field_.isFinalField(), field_.isEnumField(), _cont, _aff);
                    r_.setId(f_);
                    r_.setAnc(ancestors_.getVal(id_));
                    r_.setStatus(SearchingMemberStatus.UNIQ);
                    return r_;
                }
            }
        }
        FieldResult r_ = new FieldResult();
        r_.setStatus(SearchingMemberStatus.ZERO);
        return r_;
    }
    
    static ConstrustorIdVarArg getDeclaredCustConstructor(Analyzable _conf, int _varargOnly, ClassArgumentMatching _class,
            ConstructorId _uniqueId, ClassArgumentMatching... _args) {
        String clCurName_ = _class.getNames().first();
        LgNames stds_ = _conf.getStandards();
        String glClass_ = _conf.getGlobalClass();
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid(_conf)) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(stds_.getAliasVoid());
                mapping_.setParam(stds_.getAliasObject());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
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
        CustList<ConstructorInfo> signatures_ = new CustList<ConstructorInfo>();
        for (GeneConstructor e: constructors_) {
            ConstructorId ctor_ = e.getId();
            boolean varArg_ = ctor_.isVararg();
            if (_varargOnly > -1) {
                if (!varArg_) {
                    continue;
                }
            }
            if (_uniqueId != null) {
                if (!_uniqueId.eq(ctor_)) {
                    continue;
                }
            }
            CustList<GeneConstructor> ctors_ = Classes.getConstructorBodiesById(_conf,clCurName_, ctor_);
            if (!Classes.canAccess(glClass_, ctors_.first(), _conf)) {
                continue;
            }
            ClassMatching[] p_ = getParameters(ctor_);
            ParametersGroup pg_ = new ParametersGroup();
            for (String c: ctor_.getParametersTypes()) {
                pg_.add(new ClassMatching(c));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            mloc_.setConstraints(ctor_);
            mloc_.setParameters(pg_);
            mloc_.setClassName(clCurName_);
            if (!isPossibleMethod(_conf, clCurName_, _varargOnly, mloc_, p_, _args)) {
                continue;
            }
            signatures_.add(mloc_);
        }
        if (signatures_.isEmpty()) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getNames().join(""));
            }
            UndefinedConstructorError undefined_ = new UndefinedConstructorError();
            undefined_.setClassName(clCurName_);
            undefined_.setId(new ConstructorId(clCurName_, classesNames_,false));
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(undefined_);
            ConstrustorIdVarArg out_;
            out_ = new ConstrustorIdVarArg();
            out_.setRealId(undefined_.getId());
            out_.setConstId(undefined_.getId().quickFormat(clCurName_, _conf));
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
        ConstructorInfo cInfo_ = sortCtors(signatures_, gr_);
        if (gr_.isAmbigous()) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getNames().join(""));
            }
            UndefinedConstructorError undefined_ = new UndefinedConstructorError();
            undefined_.setClassName(clCurName_);
            undefined_.setId(new ConstructorId(clCurName_, classesNames_, false));
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(undefined_);
            ConstrustorIdVarArg out_;
            out_ = new ConstrustorIdVarArg();
            out_.setRealId(undefined_.getId());
            out_.setConstId(undefined_.getId().quickFormat(clCurName_, _conf));
            return out_;
        }
        ConstructorId ctor_ = cInfo_.getConstraints();
        ConstrustorIdVarArg out_;
        out_ = new ConstrustorIdVarArg();
        if (_varargOnly == -1 && cInfo_.isVarArgWrap()) {
            out_.setVarArgToCall(true);
        }
        out_.setRealId(ctor_);
        out_.setConstId(cInfo_.getFormatted());
        CustList<GeneConstructor> ctors_ = Classes.getConstructorBodiesById(_conf,clCurName_, ctor_);
        out_.setCtor(ctors_.first());
        return out_;
    }

    static ClassMethodIdReturn getDeclaredCustMethod(Analyzable _conf, int _varargOnly,
    boolean _staticContext, StringList _classes, String _name,
    boolean _superClass, boolean _accessFromSuper, boolean _import, ClassMethodId _uniqueId, ClassArgumentMatching... _argsClass) {
        ObjectNotNullMap<ClassMethodId, MethodInfo> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _staticContext,_varargOnly, _accessFromSuper, _superClass, _classes, _name, _import, _uniqueId, _argsClass);
        ClassMethodIdReturn res_= getCustResult(_conf, _varargOnly, methods_, _name, _argsClass);
        if (res_.isFoundMethod()) {
            return res_;
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
            undefined_.setIndexFile(_conf.getCurrentLocationIndex());
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
        undefined_.setIndexFile(_conf.getCurrentLocationIndex());
        _conf.getClasses().addError(undefined_);
        return_.setId(new ClassMethodId(_classes.first(), new MethodId(mod_, _name, classesNames_)));
        return_.setRealId(new MethodId(mod_, _name, classesNames_));
        return_.setRealClass(_classes.first());
        return_.setReturnType(_conf.getStandards().getAliasObject());
        return return_;
    }
    static ClassMethodIdReturn getOperator(Analyzable _cont, String _op, ClassArgumentMatching... _argsClass) {
        ObjectNotNullMap<ClassMethodId, MethodInfo> ops_ = getOperators(_cont);
        ClassMethodIdReturn res_ = getCustResult(_cont, -1, ops_, _op, _argsClass);
        return res_;
    }
    static ObjectNotNullMap<ClassMethodId, MethodInfo> getOperators(Analyzable _cont){
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
            mloc_.setStatic(true);
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
        boolean _superClass, StringList _fromClasses, String _name, boolean _import, ClassMethodId _uniqueId, ClassArgumentMatching... _argsClass) {
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
            if (_uniqueId != null) {
                if (!StringList.quickEq(_uniqueId.getClassName(), t)) {
                    continue;
                }
            }
            GeneType root_ = _conf.getClassBody(t);
            for (GeneMethod e: ContextEl.getMethodBlocks(root_)) {
                if (!Classes.canAccess(glClass_, e, _conf)) {
                    continue;
                }
                String subType_ = superTypesBase_.getVal(t);
                if (!Classes.canAccess(subType_, e, _conf)) {
                    continue;
                }
                if (_accessFromSuper) {
                    StringList l_ = new StringList(superTypesBase_.values());
                    if (l_.containsStr(t)) {
                        continue;
                    }
                }
                if (_superClass) {
                    StringList l_ = new StringList(superTypesBase_.values());
                    if (!l_.containsStr(t)) {
                        continue;
                    }
                }
                if (e.isStaticMethod()) {
                    MethodId id_ = e.getId();
                    if (_uniqueId != null) {
                        if (!_uniqueId.getConstraints().eq(id_)) {
                            continue;
                        }
                    }
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
                    methods_.add(clId_, mloc_);
                }
            }
            methods_.putAllMap(getPredefineStaticEnumMethods(_conf, t, 0));
        }
        CustList<CustList<GeneType>> rootsAncs_ = new CustList<CustList<GeneType>>();
        StringMap<Integer> superTypesAnc_ = new StringMap<Integer>();
        StringMap<String> superTypesBaseAnc_ = new StringMap<String>();
        for (String s: _fromClasses) {
            String baseCurName_ = Templates.getIdFromAllTypes(s);
            GeneType root_ = _conf.getClassBody(baseCurName_);
            if (!(root_ instanceof RootBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) root_;
            int anc_ = 1;
            CustList<GeneType> rootsAnc_= new CustList<GeneType>();
            boolean add_ = !root_.isStaticType();
            for (RootBlock p: r_.getAllParentTypes()) {
                if (add_) {
                    rootsAnc_.add(p);
                }
                if (p.isStaticType()) {
                    add_ = false;
                }
                String baseCur_ = p.getFullName();
                for (String m: p.getAllSuperTypes()) {
                    superTypesBaseAnc_.put(m, baseCur_);
                    superTypesAnc_.put(m,anc_);
                }
                anc_++;
            }
            rootsAncs_.add(rootsAnc_);
        }
        for (EntryCust<String, Integer> t: superTypesAnc_.entryList()) {
            if (_uniqueId != null) {
                if (!StringList.quickEq(_uniqueId.getClassName(), t.getKey())) {
                    continue;
                }
            }
            String cl_ = t.getKey();
            GeneType root_ = _conf.getClassBody(cl_);
            int anc_ = t.getValue();
            for (GeneMethod e: ContextEl.getMethodBlocks(root_)) {
                if (!Classes.canAccess(glClass_, e, _conf)) {
                    continue;
                }
                String subType_ = superTypesBaseAnc_.getVal(cl_);
                if (!Classes.canAccess(subType_, e, _conf)) {
                    continue;
                }
                if (_accessFromSuper) {
                    StringList l_ = new StringList(superTypesBaseAnc_.values());
                    if (l_.containsStr(cl_)) {
                        continue;
                    }
                }
                if (_superClass) {
                    StringList l_ = new StringList(superTypesBaseAnc_.values());
                    if (!l_.containsStr(cl_)) {
                        continue;
                    }
                }
                if (e.isStaticMethod()) {
                    MethodId id_ = e.getId();
                    if (_uniqueId != null) {
                        if (!_uniqueId.getConstraints().eq(id_)) {
                            continue;
                        }
                    }
                    String returnType_ = e.getImportedReturnType();
                    ParametersGroup p_ = new ParametersGroup();
                    MethodId realId_ = id_;
                    for (String c: realId_.getParametersTypes()) {
                        p_.add(new ClassMatching(c));
                    }
                    MethodInfo mloc_ = new MethodInfo();
                    mloc_.setClassName(cl_);
                    mloc_.setStatic(true);
                    mloc_.setConstraints(realId_);
                    mloc_.setParameters(p_);
                    mloc_.setReturnType(returnType_);
                    mloc_.setAncestor(anc_);
                    ClassMethodId clId_ = new ClassMethodId(cl_, id_);
                    methods_.add(clId_, mloc_);
                }
            }
            methods_.putAllMap(getPredefineStaticEnumMethods(_conf, cl_, anc_));
        }
        if (!_staticContext){
            int indexType_ = 0;
            for (GeneType t: roots_) {
                String clCurName_ = _fromClasses.get(indexType_);
                for (EntryCust<MethodId, EqList<ClassMethodId>> e: t.getAllOverridingMethods().entryList()) {
                    for (ClassMethodId s: e.getValue()) {
                        String name_ = s.getClassName();
                        MethodId id_ = s.getConstraints();
                        String base_ = Templates.getIdFromAllTypes(name_);
                        if (_uniqueId != null) {
                            if (!StringList.quickEq(_uniqueId.getClassName(), base_)) {
                                continue;
                            }
                            if (!_uniqueId.getConstraints().eq(id_)) {
                                continue;
                            }
                        }
                        if (_accessFromSuper) {
                            if (StringList.quickEq(base_, t.getFullName())) {
                                continue;
                            }
                        }
                        String formattedClass_;
                        if (_superClass) {
                            formattedClass_ = Templates.getFullTypeByBases(clCurName_, name_, _conf);
                        } else {
                            if (!StringList.quickEq(base_, t.getFullName())) {
                                continue;
                            }
                            formattedClass_ = clCurName_;
                        }
                        if (formattedClass_ == null) {
                            continue;
                        }
                        GeneMethod sup_ = _conf.getMethodBodiesById(name_, id_).first();
                        if (!Classes.canAccess(glClass_, sup_, _conf)) {
                            continue;
                        }
                        String ret_ = sup_.getImportedReturnType();
                        ret_ = Templates.wildCardFormat(false, formattedClass_, ret_, _conf, true);
                        if (ret_ == null) {
                            continue;
                        }
                        ParametersGroup p_ = new ParametersGroup();
                        MethodId realId_ = id_;
                        for (String c: realId_.getParametersTypes()) {
                            p_.add(new ClassMatching(c));
                        }
                        MethodInfo mloc_ = new MethodInfo();
                        mloc_.setClassName(formattedClass_);
                        mloc_.setStatic(false);
                        mloc_.setAbstractMethod(sup_.isAbstractMethod());
                        mloc_.setFinalMethod(sup_.isFinalMethod());
                        mloc_.setConstraints(realId_);
                        mloc_.setParameters(p_);
                        mloc_.setReturnType(ret_);
                        ClassMethodId clId_ = new ClassMethodId(formattedClass_, id_);
                        methods_.add(clId_, mloc_);
                    }
                }
                indexType_++;
            }
            indexType_ = 0;
            for (CustList<GeneType> l: rootsAncs_) {
                String clCurName_ = _fromClasses.get(indexType_);
                indexType_++;
                int anc_ = 1;
                for (GeneType t: l) {
                    String f_ = Templates.quickFormat(clCurName_, t.getGenericString(), _conf);
                    for (EntryCust<MethodId, EqList<ClassMethodId>> e: t.getAllOverridingMethods().entryList()) {
                        for (ClassMethodId s: e.getValue()) {
                            String name_ = s.getClassName();
                            String base_ = Templates.getIdFromAllTypes(name_);
                            MethodId id_ = s.getConstraints();
                            if (_uniqueId != null) {
                                if (!StringList.quickEq(_uniqueId.getClassName(), base_)) {
                                    continue;
                                }
                                if (!_uniqueId.getConstraints().eq(id_)) {
                                    continue;
                                }
                            }
                            if (_accessFromSuper) {
                                if (StringList.quickEq(base_, t.getFullName())) {
                                    continue;
                                }
                            }
                            String formattedClass_;
                            if (_superClass) {
                                formattedClass_ = Templates.getFullTypeByBases(f_, name_, _conf);
                            } else {
                                if (!StringList.quickEq(base_, t.getFullName())) {
                                    continue;
                                }
                                formattedClass_ = f_;
                            }
                            if (formattedClass_ == null) {
                                continue;
                            }
                            GeneMethod sup_ = _conf.getMethodBodiesById(name_, id_).first();
                            if (!Classes.canAccess(glClass_, sup_, _conf)) {
                                continue;
                            }
                            String ret_ = sup_.getImportedReturnType();
                            ret_ = Templates.wildCardFormat(false, formattedClass_, ret_, _conf, true);
                            if (ret_ == null) {
                                continue;
                            }
                            ParametersGroup p_ = new ParametersGroup();
                            MethodId realId_ = id_;
                            for (String c: realId_.getParametersTypes()) {
                                p_.add(new ClassMatching(c));
                            }
                            MethodInfo mloc_ = new MethodInfo();
                            mloc_.setClassName(formattedClass_);
                            mloc_.setStatic(false);
                            mloc_.setAbstractMethod(sup_.isAbstractMethod());
                            mloc_.setFinalMethod(sup_.isFinalMethod());
                            mloc_.setConstraints(realId_);
                            mloc_.setParameters(p_);
                            mloc_.setReturnType(ret_);
                            mloc_.setAncestor(anc_);
                            ClassMethodId clId_ = new ClassMethodId(formattedClass_, id_);
                            methods_.add(clId_, mloc_);
                        }
                    }
                    anc_++;
                }
            }
        }
        if (_import) {
            for (EntryCust<ClassMethodId, Integer> e: _conf.lookupImportStaticMethods(glClass_, _name, _conf.getCurrentBlock()).entryList()) {
                ClassMethodId m = e.getKey();
                String clName_ = m.getClassName();
                MethodId id_ = m.getConstraints();
                if (_uniqueId != null) {
                    if (!StringList.quickEq(_uniqueId.getClassName(), clName_)) {
                        continue;
                    }
                    if (!_uniqueId.getConstraints().eq(id_)) {
                        continue;
                    }
                }
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
                methods_.add(m, mloc_);
            }
        }
        return methods_;
    }
    private static ObjectNotNullMap<ClassMethodId, MethodInfo> getPredefineStaticEnumMethods(Analyzable _conf, String _className, int _ancestor) {
        ObjectNotNullMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectNotNullMap<ClassMethodId, MethodInfo>();
        String idClass_ = Templates.getIdFromAllTypes(_className);
        RootBlock r_ = _conf.getClasses().getClassBody(idClass_);
        if (!(r_ instanceof EnumBlock)) {
            return methods_;
        }
        String wildCardForm_ = r_.getWildCardString();
        String string_ = _conf.getStandards().getAliasString();
        String returnType_ = wildCardForm_;
        ParametersGroup p_ = new ParametersGroup();
        String valueOf_ = _conf.getStandards().getAliasEnumPredValueOf();
        MethodId realId_ = new MethodId(true, valueOf_, new StringList(string_));
        for (String c: realId_.getParametersTypes()) {
            p_.add(new ClassMatching(c));
        }
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setClassName(idClass_);
        mloc_.setStatic(true);
        mloc_.setConstraints(realId_);
        mloc_.setParameters(p_);
        mloc_.setReturnType(returnType_);
        mloc_.setAncestor(_ancestor);
        ClassMethodId clId_ = new ClassMethodId(idClass_, realId_);
        methods_.add(clId_, mloc_);
        p_ = new ParametersGroup();
        String values_ = _conf.getStandards().getAliasEnumValues();
        realId_ = new MethodId(true, values_, new StringList());
        mloc_ = new MethodInfo();
        mloc_.setClassName(idClass_);
        mloc_.setStatic(true);
        mloc_.setConstraints(realId_);
        mloc_.setParameters(p_);
        returnType_ = PrimitiveTypeUtil.getPrettyArrayType(returnType_);
        mloc_.setReturnType(returnType_);
        mloc_.setAncestor(_ancestor);
        clId_ = new ClassMethodId(idClass_, realId_);
        methods_.add(clId_, mloc_);
        return methods_;
    }
    private static ClassMethodIdReturn getCustResult(Analyzable _conf, int _varargOnly,
            ObjectNotNullMap<ClassMethodId, MethodInfo> _methods,
            String _name, ClassArgumentMatching... _argsClass) {
        String glClass_ = _conf.getGlobalClass();
        CustList<MethodInfo> signatures_ = new CustList<MethodInfo>();
        for (EntryCust<ClassMethodId, MethodInfo> e: _methods.entryList()) {
            ClassMethodId key_ = e.getKey();
            MethodId id_ = key_.getConstraints();
            boolean varArg_ = id_.isVararg();
            if (_varargOnly > -1) {
                if (!varArg_) {
                    continue;
                }
            }
            if (!StringList.quickEq(id_.getName(), _name)) {
                continue;
            }
            ClassMatching[] p_ = getParameters(id_);
            MethodInfo mi_ = e.getValue();
            String formattedType_ = mi_.getClassName();
            if (!isPossibleMethod(_conf, formattedType_, _varargOnly, mi_, p_, _argsClass)) {
                continue;
            }
            signatures_.add(mi_);
        }
        if (signatures_.isEmpty()) {
            ClassMethodIdReturn res_ = new ClassMethodIdReturn(false);
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
        _conf.setAmbigous(false);
        MethodInfo found_ = sortFct(signatures_, gr_);
        if (gr_.isAmbigous()) {
            _conf.setAmbigous(true);
            ClassMethodIdReturn res_ = new ClassMethodIdReturn(false);
            return res_;
        }
        MethodId constraints_ = found_.getConstraints();
        String baseClassName_ = found_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = found_.getFormatted();
        String realClass_ = baseClassName_;
        res_.setId(new ClassMethodId(realClass_, id_));
        if (_varargOnly == -1 && found_.isVarArgWrap()) {
            res_.setVarArgToCall(true);
        }
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(found_.getReturnType());
        res_.setAncestor(found_.getAncestor());
        res_.setAbstractMethod(found_.isAbstractMethod());
        res_.setStaticMethod(found_.isStatic());
        return res_;
    }

    static boolean isPossibleMethod(Analyzable _context, String _class, int _varargOnly, Parametrable _id,ClassMatching[] _params,
    ClassArgumentMatching... _argsClass) {
        int startOpt_ = _argsClass.length;
        boolean checkOnlyDem_ = true;
        int nbDem_ = _params.length;
        boolean static_ = _id.isStatic();
        boolean vararg_ = _id.isVararg();
        if (!vararg_) {
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
        StringMap<StringList> mapCtr_ = Templates.getMapConstraints(glClass_, _context);
        int len_ = nbDem_;
        StringList formatPar_ = new StringList();
        boolean allNotBoxUnbox_ = true;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String wc_ = Templates.wildCardFormat(static_, _class, _params[i].getClassName(), _context, false);
            formatPar_.add(wc_);
            if (_argsClass[i].isVariable()) {
                if (_params[i].isPrimitive(_context)) {
                    return false;
                }
                continue;
            }
            Mapping map_ = new Mapping();
            ClassArgumentMatching arg_ = _argsClass[i];
            map_.setArg(arg_);
            map_.getMapping().putAllMap(mapCtr_);
            if (wc_ == null) {
                return false;
            }
            map_.setParam(wc_);
            if (!Templates.isCorrectOrNumbers(map_, _context)) {
                return false;
            }
            if (PrimitiveTypeUtil.isPrimitive(wc_, _context)) {
                if (!arg_.isPrimitive(_context)) {
                    allNotBoxUnbox_ = false;
                }
            } else {
                if (arg_.isPrimitive(_context)) {
                    allNotBoxUnbox_ = false;
                }
            }
        }
        if (checkOnlyDem_) {
            if (vararg_) {
                allNotBoxUnbox_ = false;
                formatPar_.setLast(PrimitiveTypeUtil.getQuickComponentType(formatPar_.last()));
            }
            if (allNotBoxUnbox_) {
                _id.setInvocation(InvocationMethod.STRICT);
            } else if (!vararg_) {
                _id.setInvocation(InvocationMethod.BOX_UNBOX);
            } else {
                _id.setInvocation(InvocationMethod.ALL);
            }
            _id.format(formatPar_);
            return true;
        }
        if (_params.length == _argsClass.length) {
            int last_ = _params.length - 1;
            Mapping map_ = new Mapping();
            ClassArgumentMatching arg_ = _argsClass[last_];
            map_.setArg(arg_);
            map_.getMapping().putAllMap(mapCtr_);
            String param_ = _params[last_].getClassName();
            String wc_ = Templates.wildCardFormat(static_, _class, param_, _context, false);
            if (wc_ == null) {
                return false;
            }
            String compo_ = PrimitiveTypeUtil.getQuickComponentType(wc_);
            formatPar_.add(compo_);
            _id.format(formatPar_);
            map_.setParam(wc_);
            if (Templates.isCorrectOrNumbers(map_, _context)) {
                if (allNotBoxUnbox_) {
                    _id.setInvocation(InvocationMethod.STRICT);
                } else {
                    _id.setInvocation(InvocationMethod.BOX_UNBOX);
                }
                return true;
            }
            map_.setParam(compo_);
            if (Templates.isCorrectOrNumbers(map_, _context)) {
                _id.setInvocation(InvocationMethod.ALL);
                _id.setVarArgWrap(true);
                return true;
            }
            return false;
        }
        len_ = _argsClass.length;
        Mapping map_ = new Mapping();
        int last_ = _params.length - 1;
        String param_ = _params[last_].getClassName();
        param_ = PrimitiveTypeUtil.getQuickComponentType(param_);
        map_.getMapping().putAllMap(mapCtr_);
        String wc_ = Templates.wildCardFormat(static_, _class, param_, _context, false);
        if (wc_ == null) {
            return false;
        }
        formatPar_.add(wc_);
        _id.format(formatPar_);
        map_.setParam(wc_);
        for (int i = startOpt_; i < len_; i++) {
            map_.setArg(_argsClass[i]);
            if (!Templates.isCorrectOrNumbers(map_, _context)) {
                return false;
            }
        }
        _id.setInvocation(InvocationMethod.ALL);
        _id.setVarArgWrap(true);
        return true;
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

    static MethodInfo sortFct(CustList<MethodInfo> _fct, ArgumentsGroup _context) {
        int len_ = _fct.size();
        CustList<Parametrable> fct_ = new CustList<Parametrable>(new CollCapacity(_fct.size()));
        for (MethodInfo m: _fct) {
            fct_.add(m);
        }
        if (_context.getContext().getOptions().isAllParametersSort()) {
            CustList<Parametrable> pars_ = getFound(fct_, _context, true);
            if (pars_.size() == 1) {
                return (MethodInfo) pars_.first();
            }
        }
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            Parametrable pFirst_ = _fct.first();
            Parametrable pCurrent_ = _fct.get(i);
            int res_ = compare(_context, pFirst_, pCurrent_);
            if (res_ == CustList.SWAP_SORT) {
                _fct.swapIndexes(CustList.FIRST_INDEX, i);
            }
        }
        if (_fct.first().getParameters().isError()) {
            _context.setAmbigous(true);
            return null;
        }
        return _fct.first();
    }
    static ConstructorInfo sortCtors(CustList<ConstructorInfo> _fct, ArgumentsGroup _context) {
        int len_ = _fct.size();
        CustList<Parametrable> fct_ = new CustList<Parametrable>(new CollCapacity(_fct.size()));
        for (ConstructorInfo m: _fct) {
            fct_.add(m);
        }
        if (_context.getContext().getOptions().isAllParametersSort()) {
            CustList<Parametrable> pars_ = getFound(fct_, _context, false);
            if (pars_.size() == 1) {
                return (ConstructorInfo) pars_.first();
            }
        }
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            Parametrable pFirst_ = _fct.first();
            Parametrable pCurrent_ = _fct.get(i);
            int res_ = compare(_context, pFirst_, pCurrent_);
            if (res_ == CustList.SWAP_SORT) {
                _fct.swapIndexes(CustList.FIRST_INDEX, i);
            }
        }
        if (_fct.first().getParameters().isError()) {
            _context.setAmbigous(true);
            return null;
        }
        return _fct.first();
    }

    static CustList<Parametrable> getFound(CustList<Parametrable> _fct, ArgumentsGroup _context, boolean _method) {
        CustList<Parametrable> fct_ = new CustList<Parametrable>();
        for (Parametrable m: _fct) {
            if (m.getInvocation() == InvocationMethod.STRICT) {
                fct_.add(m);
            }
        }
        if (fct_.isEmpty()) {
            for (Parametrable m: _fct) {
                if (m.getInvocation() == InvocationMethod.BOX_UNBOX) {
                    fct_.add(m);
                }
            }
        }
        CustList<Parametrable> allMax_;
        if (!fct_.isEmpty()) {
            allMax_ = getAllMaximalSpecificFixArity(fct_, _context);
        } else {
            allMax_ = getAllMaximalSpecificVariableArity(_fct, _context);
        }
        if (allMax_.size() <= 1 || !_method) {
            return allMax_;
        }
        Identifiable id_ = allMax_.first().getFormatted();
        int lenMax_ = allMax_.size();
        boolean allOvEq_ = true;
        for (int i = 1; i < lenMax_; i++) {
            if (!allMax_.get(i).same(id_)) {
                allOvEq_ = false;
                break;
            }
        }
        CustList<Parametrable> nonAbs_ = new CustList<Parametrable>();
        CustList<Parametrable> finals_ = new CustList<Parametrable>();
        if (allOvEq_) {
            for (Parametrable p: allMax_) {
                MethodInfo m_ = (MethodInfo)p;
                if (!m_.isFinalMethod()) {
                    continue;
                }
                finals_.add(p);
            }
            if (finals_.size() == 1) {
                return finals_;
            }
            Analyzable context_ = _context.getContext();
            for (Parametrable p: allMax_) {
                MethodInfo m_ = (MethodInfo)p;
                if (m_.isAbstractMethod()) {
                    continue;
                }
                String type_ = m_.getClassName();
                type_ = Templates.getIdFromAllTypes(type_);
                if (context_.getClassBody(type_) instanceof GeneInterface) {
                    continue;
                }
                nonAbs_.add(p);
            }
            if (nonAbs_.size() == 1) {
                return nonAbs_;
            }
            StringMap<StringList> map_;
            map_ = _context.getMap();
            int lenAllMax_ = allMax_.size();
            for (int i = 0; i < lenAllMax_; i++) {
                MethodInfo curMi_ = (MethodInfo) allMax_.get(i);
                boolean spec_ = true;
                String curRet_ = curMi_.getReturnType();
                String cl_ = Templates.getIdFromAllTypes(curMi_.getClassName());
                for (int j = 0; j < lenAllMax_; j++) {
                    if (i == j) {
                        continue;
                    }
                    MethodInfo otherMi_ = (MethodInfo) allMax_.get(j);
                    String clOther_ = Templates.getIdFromAllTypes(otherMi_.getClassName());
                    String otherRet_ = otherMi_.getReturnType();
                    if (StringList.quickEq(curRet_, otherRet_)) {
                        if (StringList.quickEq(clOther_, cl_)) {
                            spec_ = false;
                            break;
                        }
                        continue;
                    }
                    if (!Templates.isReturnCorrect(otherRet_, curRet_, map_, context_)) {
                        spec_ = false;
                        break;
                    }
                }
                if (spec_) {
                    return new CustList<Parametrable>(curMi_);
                }
            }
        }
        return new CustList<Parametrable>();
    }
    static CustList<Parametrable> getAllMaximalSpecificFixArity(CustList<Parametrable> _all, ArgumentsGroup _context) {
        CustList<Parametrable> list_ = new CustList<Parametrable>();
        int len_ = _all.size();
        for (int i = 0; i < len_; i++) {
            Parametrable current_ = _all.get(i);
            boolean max_ = true;
            for (int j = 0; j < len_; j++) {
                if (i == j) {
                    continue;
                }
                Parametrable other_ = _all.get(j);
                if (isStrictMoreSpecificThanFixArity(other_, current_, _context)) {
                    max_ = false;
                    break;
                }
            }
            if (max_) {
                list_.add(current_);
            }
        }
        return list_;
    }
    static CustList<Parametrable> getAllMaximalSpecificVariableArity(CustList<Parametrable> _all, ArgumentsGroup _context) {
        CustList<Parametrable> list_ = new CustList<Parametrable>();
        int len_ = _all.size();
        for (int i = 0; i < len_; i++) {
            Parametrable current_ = _all.get(i);
            boolean max_ = true;
            for (int j = 0; j < len_; j++) {
                if (i == j) {
                    continue;
                }
                Parametrable other_ = _all.get(j);
                if (isStrictMoreSpecificThanVariableArity(other_, current_, _context)) {
                    max_ = false;
                    break;
                }
            }
            if (max_) {
                list_.add(current_);
            }
        }
        return list_;
    }
    static boolean isStrictMoreSpecificThanVariableArity(Parametrable _one,Parametrable _two, ArgumentsGroup _context) {
        if (!isMoreSpecificThanVariableArity(_one, _two, _context)) {
            return false;
        }
        return !isMoreSpecificThanVariableArity(_two, _one, _context);
    }
    static boolean isStrictMoreSpecificThanFixArity(Parametrable _one,Parametrable _two, ArgumentsGroup _context) {
        if (!isMoreSpecificThanFixArity(_one, _two, _context)) {
            return false;
        }
        return !isMoreSpecificThanFixArity(_two, _one, _context);
    }
    static boolean isMoreSpecificThanFixArity(Parametrable _one,Parametrable _two, ArgumentsGroup _context) {
        if (_one.getImported() > _two.getImported()) {
            return false;
        }
        if (_two.getImported() > _one.getImported()) {
            return true;
        }
        if (_one.getAncestor() > _two.getAncestor()) {
            return false;
        }
        if (_two.getAncestor() > _one.getAncestor()) {
            return true;
        }
        Analyzable context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        int len_ = _one.getParameters().size();
        boolean all_ = true;
        Identifiable idOne_ = _one.getFormatted();
        Identifiable idTwo_ = _two.getFormatted();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String wcOne_ = idOne_.getParametersTypes().get(i);
            String wcTwo_ = idTwo_.getParametersTypes().get(i);
            if (idOne_.isVararg() && i + 1 == len_) {
                wcOne_ = PrimitiveTypeUtil.getPrettyArrayType(wcOne_);
            }
            if (idTwo_.isVararg() && i + 1 == len_) {
                wcTwo_ = PrimitiveTypeUtil.getPrettyArrayType(wcTwo_);
            }
            if (!isPreferred(wcOne_, wcTwo_, map_, context_)) {
                all_ = false;
                break;
            }
        }
        return all_;
    }
    static boolean isMoreSpecificThanVariableArity(Parametrable _one,Parametrable _two, ArgumentsGroup _context) {
        if (_one.getImported() > _two.getImported()) {
            return false;
        }
        if (_two.getImported() > _one.getImported()) {
            return true;
        }
        if (_one.getAncestor() > _two.getAncestor()) {
            return false;
        }
        if (_two.getAncestor() > _one.getAncestor()) {
            return true;
        }
        Analyzable context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        int lenOne_ = _one.getParameters().size();
        int lenTwo_ = _two.getParameters().size();
        boolean all_ = true;
        if (lenOne_ >= lenTwo_) {
            int pr_ = lenTwo_-1;
            for (int i = CustList.FIRST_INDEX; i < pr_; i++) {
                String wcOne_ = _one.getFormatted().getParametersTypes().get(i);
                String wcTwo_ = _two.getFormatted().getParametersTypes().get(i);
                if (!isPreferred(wcOne_, wcTwo_, map_, context_)) {
                    all_ = false;
                    break;
                }
            }
            String wcTwo_ = _two.getFormatted().getParametersTypes().last();
            for (int i = pr_; i < lenOne_; i++) {
                String wcOne_ = _one.getFormatted().getParametersTypes().get(i);
                if (!isPreferred(wcOne_, wcTwo_, map_, context_)) {
                    all_ = false;
                    break;
                }
            }
        } else {
            int pr_ = lenOne_-1;
            for (int i = CustList.FIRST_INDEX; i < pr_; i++) {
                String wcOne_ = _one.getFormatted().getParametersTypes().get(i);
                String wcTwo_ = _two.getFormatted().getParametersTypes().get(i);
                if (!isPreferred(wcOne_, wcTwo_, map_, context_)) {
                    all_ = false;
                    break;
                }
            }
            String wcOne_ = _one.getFormatted().getParametersTypes().last();
            for (int i = pr_; i < lenTwo_; i++) {
                String wcTwo_ = _two.getFormatted().getParametersTypes().get(i);
                if (!isPreferred(wcOne_, wcTwo_, map_, context_)) {
                    all_ = false;
                    break;
                }
            }
        }
        return all_;
    }
    private static boolean isPreferred(String _paramFctOne, String _paramFctTwo, StringMap<StringList> _map, Analyzable _ana) {
        ClassMatching one_;
        ClassMatching two_;
        if (_paramFctOne == null) {
            if (_paramFctTwo != null) {
                return false;
            }
            return true;
        }
        if (_paramFctTwo == null) {
            return true;
        }
        one_ = new ClassMatching(_paramFctOne);
        two_ = new ClassMatching(_paramFctTwo);
        if (one_.matchClass(two_)) {
            return true;
        }
        if (!two_.isAssignableFrom(one_, _map, _ana)) {
            return false;
        }
        return true;
    }

    static int compare(ArgumentsGroup _context, Parametrable _o1, Parametrable _o2) {
        if (_o1.getImported() > _o2.getImported()) {
            return CustList.SWAP_SORT;
        }
        if (_o2.getImported() > _o1.getImported()) {
            return CustList.NO_SWAP_SORT;
        }
        if (_o1.getAncestor() > _o2.getAncestor()) {
            return CustList.SWAP_SORT;
        }
        if (_o2.getAncestor() > _o1.getAncestor()) {
            return CustList.NO_SWAP_SORT;
        }
        int len_ = _o1.getParameters().size();
        Analyzable context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
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
                boolean varOne_ = _o1.isVarArgWrap();
                boolean varTwo_ = _o2.isVarArgWrap();
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
            ClassMatching one_;
            ClassMatching two_;
            String wcOne_ = _o1.getFormatted().getParametersTypes().get(i);
            String wcTwo_ = _o2.getFormatted().getParametersTypes().get(i);
            if (wcOne_ == null) {
                if (wcTwo_ != null) {
                    return CustList.SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }
            if (wcTwo_ == null) {
                return CustList.NO_SWAP_SORT;
            }
            one_ = new ClassMatching(wcOne_);
            two_ = new ClassMatching(wcTwo_);
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
            ClassMatching one_;
            String paramOne_ = _o1.getFormatted().getParametersTypes().last();
            paramOne_ = PrimitiveTypeUtil.getPrettyArrayType(paramOne_);
            ClassMatching two_;
            String paramTwo_ = _o2.getFormatted().getParametersTypes().last();
            paramTwo_ = PrimitiveTypeUtil.getPrettyArrayType(paramTwo_);
            String wcOne_ = paramOne_;
            String wcTwo_ = paramTwo_;
            if (wcOne_ == null) {
                if (wcTwo_ != null) {
                    return CustList.SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }
            if (wcTwo_ == null) {
                return CustList.NO_SWAP_SORT;
            }
            one_ = new ClassMatching(wcOne_);
            two_ = new ClassMatching(wcTwo_);
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
            if (!PrimitiveTypeUtil.canBeUseAsArgument(false, baseTypeTwo_, baseTypeOne_, context_)) {
                return CustList.SWAP_SORT;
            }
            return CustList.NO_SWAP_SORT;
        }
        String p_ = _o1.getReturnType();
        String a_ = _o2.getReturnType();
        if (Templates.isReturnCorrect(p_, a_, map_, context_)) {
            return CustList.SWAP_SORT;
        }
        a_ = _o1.getReturnType();
        p_ = _o2.getReturnType();
        if (Templates.isReturnCorrect(p_, a_, map_, context_)) {
            return CustList.NO_SWAP_SORT;
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(false, baseTypeOne_, baseTypeTwo_, context_)) {
            return CustList.SWAP_SORT;
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(false, baseTypeTwo_, baseTypeOne_, context_)) {
            return CustList.NO_SWAP_SORT;
        }
        //inherits types if static methods
        _o1.getParameters().setError(true);
        _o2.getParameters().setError(true);
        return CustList.NO_SWAP_SORT;
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

    @Override
    public final Argument getArgument() {
        return argument;
    }

    @Override
    public final void setSimpleArgument(Argument _argument) {
        argument = _argument;
    }

    @Override
    public final void setSimpleArgumentAna(Argument _argument, Analyzable _conf) {
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
        String un_ = resultClass.getUnwrapObject();
        if (!un_.isEmpty()) {
            if (_argument.isNull()) {
                return;
            }
            _argument.setStruct(PrimitiveTypeUtil.unwrapObject(un_, _argument.getStruct(), _conf.getStandards()));
        }
        argument = _argument;
    }
    public final boolean isStaticBlock() {
        return staticBlock;
    }

    public final void setStaticBlock(boolean _staticBlock) {
        staticBlock = _staticBlock;
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

    public final PossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(PossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    public int getIndexBegin() {
        return operations.getDelimiter().getIndexBegin();
    }
}
