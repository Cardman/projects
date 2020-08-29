package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.ImportedField;
import code.expressionlanguage.analyze.ImportedMethod;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.TypeInfo;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.*;

public abstract class OperationNode {

    protected static final char PAR_LEFT = '(';
    protected static final char PAR_RIGHT = ')';

    protected static final String ARR = "[";

    protected static final String NEG_BOOL = "!";
    protected static final String NEG_BOOL_BIN = "~";

    protected static final String MULT = "*";

    protected static final String DIV = "/";

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

    protected static final String EMPTY_STRING = "";

    protected static final String VARARG_SUFFIX = "...";
    protected static final String AROBASE = "@";
    protected static final int BOOLEAN_ARGS = 3;

    private MethodOperation parent;

    private OperationNode nextSibling;

    private Argument argument;

    private OperationsSequence operations;

    private StringList errs = new StringList();

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private final int indexChild;

    private ClassArgumentMatching resultClass;

    private PossibleIntermediateDotted siblingSet;

    OperationNode(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        indexChild = _indexChild;
        resultClass = new ClassArgumentMatching(EMPTY_STRING);
    }

    public static ClassArgumentMatching[] toArgArray(CustList<ClassArgumentMatching> _args) {
        int len_ = _args.size();
        ClassArgumentMatching[] args_;
        args_ = new ClassArgumentMatching[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            args_[i] = _args.get(i);
        }
        return args_;
    }

    public abstract void analyze(ContextEl _conf);

    final boolean isCallMethodCtor(){
        if (!(this instanceof InvokingOperation)) {
            return false;
        }
        if (this instanceof AbstractArrayInstancingOperation) {
            return false;
        }
        return !(this instanceof AnnotationInstanceOperation);
    }

    public final void setRelativeOffsetPossibleAnalyzable(int _offset, ContextEl _cont) {
        _cont.getAnalyzing().setOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public static OperationNode createOperationNode(int _index,
                                                    int _indexChild, MethodOperation _m, OperationsSequence _op, ContextEl _an) {
        OperationNode res_ = createOperationNodeBis(_index, _indexChild, _m, _op, _an);
        if (_m instanceof AbstractDotOperation&&_m.getFirstChild() != null && !(res_ instanceof PossibleIntermediateDotted)) {
            return new ErrorPartOperation(_index, _indexChild, _m, _op);
        }
        return res_;
    }
    private static OperationNode createOperationNodeBis(int _index,
                                                        int _indexChild, MethodOperation _m, OperationsSequence _op, ContextEl _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordBool_ = keyWords_.getKeyWordBool();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordDefault_ = keyWords_.getKeyWordDefault();
        String keyWordFirstopt_ = keyWords_.getKeyWordFirstopt();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
        ConstType ct_ = _op.getConstType();
        if (ct_ == ConstType.ERROR) {
            return new ErrorPartOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.ERROR_INST) {
            return new BadInstancingOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getOperators().isEmpty()) {
            return createLeaf(_index, _indexChild, _m, _op, _an);
        }
        if (_op.getPriority() == ElResolver.DECL_PRIO) {
            return new DeclaringOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO) {
            if (_an.getAnalyzing().getAnnotationAnalysis().isAnnotAnalysis(_m,_op)) {
                return new AnnotationInstanceOperation(_index, _indexChild, _m, _op);
            }
            String fctName_ = _op.getFctName().trim();
            if (fctName_.startsWith(AROBASE)) {
                return new AnnotationInstanceOperation(_index, _indexChild, _m, _op);
            }
        }
        int ternary_ = 0;
        if (_op.getPriority() == ElResolver.TERNARY_PRIO) {
            ternary_ = _op.getValues().size();
        } else if (_op.getPriority() == ElResolver.FCT_OPER_PRIO){
            String fctName_ = _op.getFctName().trim();
            if (StringList.quickEq(fctName_, keyWordBool_)) {
                ternary_ = _op.getValues().size()-1;
            }
        }
        if (ternary_ == BOOLEAN_ARGS) {
            if (_op.getPriority() == ElResolver.TERNARY_PRIO) {
                return new ShortTernaryOperation(_index, _indexChild, _m, _op);
            }
            return new TernaryOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO && _op.isCallDbArray()) {
            String fctName_ = _op.getFctName().trim();
            if (_m instanceof AbstractDotOperation) {
                OperationNode ch_ = _m.getFirstChild();
                if (ch_ != null) {
                    String type_ = ch_.getResultClass().getSingleNameOrEmpty();
                    if (!type_.isEmpty()) {
                        String id_ = StringExpUtil.getIdFromAllTypes(type_);
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
                String op_ = _op.getOperators().firstValue();
                if (StringList.quickEq(op_,"{")) {
                    return new ElementArrayInstancing(_index, _indexChild, _m, _op);
                }
                if (StringList.quickEq(op_,"[")) {
                    return new DimensionArrayInstancing(_index, _indexChild, _m, _op);
                }
                Block block_ = _op.getBlock();
                if (block_ instanceof AnonymousTypeBlock) {
                    ((AnonymousTypeBlock)block_).setIndexEnd(((AnonymousTypeBlock)block_).getIdRowCol()+_op.getLength());
                    return new AnonymousInstancingOperation(_index,_indexChild,_m,_op,(AnonymousTypeBlock)block_);
                }
                return new StandardInstancingOperation(_index, _indexChild, _m, _op);
            }
            if (fctName_.isEmpty()) {
                return new IdOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, keyWordValueOf_)) {
                return new EnumValueOfOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, keyWordBool_)) {
                return new BadTernaryOperation(_index, _indexChild, _m, _op);
            }
            if (StringExpUtil.startsWithKeyWord(fctName_, keyWordClasschoice_)) {
                return new ChoiceFctOperation(_index, _indexChild, _m, _op);
            }
            if (StringExpUtil.startsWithKeyWord(fctName_, keyWordSuperaccess_)) {
                return new SuperFctOperation(_index, _indexChild, _m, _op);
            }
            if (StringExpUtil.startsWithKeyWord(fctName_, keyWordInterfaces_)) {
                if (_m instanceof IdOperation) {
                    return new InterfaceFctConstructor(_index, _indexChild, _m, _op);
                }
                return new InterfaceInvokingConstructor(_index, _indexChild, _m, _op);
            }
            if (StringExpUtil.startsWithKeyWord(fctName_, keyWordOperator_)) {
                return new ExplicitOperatorOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, keyWordFirstopt_)) {
                return new FirstOptOperation(_index, _indexChild, _m, _op);
            }
            if (StringList.quickEq(fctName_, keyWordDefault_)) {
                return new DefaultOperation(_index, _indexChild, _m, _op);
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
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO && !_op.getValues().isEmpty()) {
            if (_op.isErrorDot()) {
                return new BadDottedOperation(_index, _indexChild, _m, _op);
            }
            if (_op.getOperators().firstValue().contains("?")) {
                return new SafeDotOperation(_index, _indexChild, _m, _op);
            }
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
            if (StringExpUtil.startsWithKeyWord(value_, _an.getKeyWords().getKeyWordExplicit())) {
                return new ExplicitOperation(_index, _indexChild, _m, _op);
            }
            if (StringExpUtil.startsWithKeyWord(value_, _an.getKeyWords().getKeyWordCast())) {
                String clName_ = _op.getOperators().firstValue();
                String extract_ = clName_.substring(clName_.indexOf(PAR_LEFT)+1, clName_.lastIndexOf(PAR_RIGHT));
                StringList types_ = StringExpUtil.getAllSepCommaTypes(extract_);
                if (types_.size() > 1) {
                    return new ImplicitOperation(_index, _indexChild, _m, _op);
                }
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
        if (_op.getPriority() == ElResolver.NULL_SAFE_PRIO) {
            return new NullSafeOperation(_index,_indexChild,_m,_op);
        }
        if (_op.getPriority() == ElResolver.AFF_PRIO) {
            if (_m instanceof AnnotationInstanceOperation&&!((AnnotationInstanceOperation) _m).isArray()) {
                String value_ = _op.getValues().firstValue();
                return new AssocationOperation(_index, _indexChild, _m, _op, value_);
            }
            String op_ = _op.getOperators().firstValue();
            if (!StringList.quickEq(op_, AFF)) {
                return new CompoundAffectationOperation(_index, _indexChild, _m, _op);
            }
            return new AffectationOperation(_index, _indexChild, _m, _op);
        }
        return new ErrorPartOperation(_index, _indexChild, _m, _op);
    }

    private static OperationNode createLeaf(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op, ContextEl _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        ConstType ct_ = _op.getConstType();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        Block block_ = _op.getBlock();
        if (block_ instanceof AnonymousFunctionBlock) {
            ((AnonymousFunctionBlock)block_).setIndexEnd(((AnonymousFunctionBlock)block_).getNameOffset()+_op.getLength());
            return new AnonymousLambdaOperation(_index,_indexChild,_m,_op,(AnonymousFunctionBlock)block_,_op.getResults());
        }
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
        if (ct_ == ConstType.STATIC_CALL_ACCESS) {
            return new StaticCallAccessOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.VARARG) {
            return new VarargOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.DEFAULT_VALUE) {
            return new DefaultValueOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.ID) {
            return new IdFctOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.LAMBDA) {
            return new LambdaOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.VALUES) {
            return new ValuesOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.CLASS_INFO) {
            return new StaticInfoOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.NUMBER) {
            return new ConstantOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.ACCESS_INDEXER) {
            return new ForwardOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.THIS_KEYWORD) {
            return new ThisOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.PARENT_KEY_WORD) {
            return new ParentInstanceOperation(_index, _indexChild, _m, _op);
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
        if (ct_ == ConstType.CLASSCHOICE_KEYWORD) {
            return new ChoiceFieldOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.SUPER_ACCESS_KEYWORD) {
            return new SuperFromFieldOperation(_index, _indexChild, _m, _op);
        }
        if (ct_ == ConstType.SUPER_KEYWORD) {
            return new SuperFieldOperation(_index, _indexChild, _m, _op);
        }
        if (ElUtil.isDeclaringLoopVariable(_m, _an)) {
            return new MutableLoopVariableOperation(_index, _indexChild, _m, _op);
        }
        if (ElUtil.isDeclaringVariable(_m, _an)) {
            return new VariableOperation(_index, _indexChild, _m, _op);
        }
        if (_m instanceof AbstractDotOperation) {
            OperationNode ch_ = _m.getFirstChild();
            if (ch_ != null) {
                if (ch_.getResultClass().isArray()) {
                    return new ArrayFieldOperation(_index, _indexChild, _m, _op);
                }
                return new StandardFieldOperation(_index, _indexChild, _m, _op);
            }
        }
        if (ct_ == ConstType.LOOP_INDEX) {
            return new FinalVariableOperation(_index, _indexChild, _m, _op);
        }
        AnaLocalVariable val_ = _an.getAnalyzing().getInfosVars().getVal(str_);
        if (val_ != null) {
            if (val_.getConstType() == ConstType.LOC_VAR) {
                return new VariableOperation(_index, _indexChild, _m, _op, val_.getClassName(), val_.getRef());
            }
            if (val_.getConstType() == ConstType.MUTABLE_LOOP_VAR) {
                return new MutableLoopVariableOperation(_index, _indexChild, _m, _op, val_.getClassName(), val_.getRef());
            }
            if (val_.getConstType() == ConstType.PARAM) {
                FunctionBlock fct_ = _an.getAnalyzing().getCurrentFct();
                if (fct_ instanceof OverridableBlock) {
                    OverridableBlock indexer_ = (OverridableBlock) fct_;
                    if (indexer_.getKind() == MethodKind.SET_INDEX) {
                        String keyWordValue_ = keyWords_.getKeyWordValue();
                        if (StringList.quickEq(keyWordValue_, str_)) {
                            return new ValueOperation(_index, _indexChild, _m, _op,val_.getClassName());
                        }
                    }
                }
            }
            return new FinalVariableOperation(_index, _indexChild, _m, _op,val_.getClassName(),val_.getRef());
        }
        return new StandardFieldOperation(_index, _indexChild, _m, _op);
    }

    static String emptyToObject(String _str, ContextEl _conf) {
        if (_str.isEmpty()) {
            return _conf.getStandards().getAliasObject();
        }
        return _str;
    }
    static void checkClassAccess(OperationNode _op,ContextEl _conf, String _glClass, String _classStr) {
        RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(_classStr);
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(_glClass);
        Accessed a_;
        if (r_ != null) {
            a_ = new Accessed(r_.getAccess(), r_.getPackageName(), _classStr, r_.getOuterFullName());
        } else {
            a_ = new Accessed(AccessEnum.PUBLIC,"", "","");
        }
        if (!ContextUtil.canAccessType(curClassBase_, a_, _conf)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //className len
            badAccess_.buildError(_conf.getAnalysisMessages().getInaccessibleType(),
                    _classStr,
                    curClassBase_);
            _conf.getAnalyzing().getLocalizer().addError(badAccess_);
            _op.getErrs().add(badAccess_.getBuiltError());
        }
    }

    final boolean isFirstChildInParent() {
        MethodOperation par_ = getParent();
        int ind_ = 0;
        if (par_.getFirstChild() instanceof StaticInitOperation) {
            ind_++;
        }
        return getIndexChild() == ind_;
    }

    public abstract OperationNode getFirstChild();

    public final OperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(OperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    static FieldResult getDeclaredCustField(OperationNode _op,ContextEl _cont, MethodAccessKind _staticContext, ClassArgumentMatching _class,
                                            boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        FieldResult fr_ = resolveDeclaredCustField(_cont, _staticContext != MethodAccessKind.INSTANCE,
                _class, _baseClass, _superClass, _name, _import, _aff);
        if (fr_.getStatus() == SearchingMemberStatus.UNIQ) {
            return fr_;
        }
        FoundErrorInterpret access_ = new FoundErrorInterpret();
        access_.setFileName(_cont.getAnalyzing().getLocalizer().getCurrentFileName());
        access_.setIndexFile(_cont.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //_name len
        access_.buildError(_cont.getAnalysisMessages().getUndefinedAccessibleField(),
                _name,
                StringList.join(_class.getNames(),"&"));
        _cont.getAnalyzing().getLocalizer().addError(access_);
        _op.getErrs().add(access_.getBuiltError());
        FieldResult res_ = new FieldResult();
        res_.setStatus(SearchingMemberStatus.ZERO);
        return res_;
    }

    static FieldResult getDeclaredCustFieldLambda(int _offset,CustList<PartOffset> _parts, ContextEl _cont, ClassArgumentMatching _class,
                                                  boolean _baseClass, boolean _superClass, String _name, boolean _aff) {
        FieldResult fr_ = resolveDeclaredCustField(_cont, false,
                _class, _baseClass, _superClass, _name, false, _aff);
        if (fr_.getStatus() == SearchingMemberStatus.UNIQ) {
            return fr_;
        }
        FoundErrorInterpret access_ = new FoundErrorInterpret();
        access_.setFileName(_cont.getAnalyzing().getLocalizer().getCurrentFileName());
        int i_ = _cont.getAnalyzing().getLocalizer().getCurrentLocationIndex()+_offset;
        access_.setIndexFile(i_);
        //_name len
        access_.buildError(_cont.getAnalysisMessages().getUndefinedAccessibleField(),
                _name,
                StringList.join(_class.getNames(),"&"));
        _cont.getAnalyzing().getLocalizer().addError(access_);
        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(access_.getBuiltError()) +"\" class=\"e\">",i_));
        _parts.add(new PartOffset("</a>",i_+Math.max(1, _name.length())));
        FieldResult res_ = new FieldResult();
        res_.setStatus(SearchingMemberStatus.ZERO);
        return res_;
    }
    public static FieldResult resolveDeclaredCustField(ContextEl _cont, boolean _staticContext, ClassArgumentMatching _class,
                                                       boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        if (!_staticContext) {
            FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _baseClass, _superClass, _name, _import, _aff);
            if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
                return resIns_;
            }
        }
        return getDeclaredCustFieldByContext(_cont, true, _class, _baseClass, _superClass, _name, _import,_aff);
    }
    private static FieldResult getDeclaredCustFieldByContext(ContextEl _cont, boolean _static, ClassArgumentMatching _class,
                                                             boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        StringMap<FieldResult> ancestors_ = new StringMap<FieldResult>();
        String glClass_ = _cont.getAnalyzing().getGlobalClass();
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
        int maxAnc_ = 0;
        MethodAccessKind k_;
        if (_static) {
            k_ = MethodAccessKind.STATIC;
        } else {
            k_ = MethodAccessKind.INSTANCE;
        }
        CustList<CustList<TypeInfo>> typesGroup_= typeLists(_cont,_class.getNames(),k_);
        for (CustList<TypeInfo> g: typesGroup_) {
            StringList baseTypes_ = new StringList();
            StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
            feedTypes(g, baseTypes_, superTypesBaseAncBis_);
            for (TypeInfo t: g) {
                String f_ = t.getType();
                String cl_ = StringExpUtil.getIdFromAllTypes(f_);
                AnaGeneType root_ = _cont.getAnalyzing().getAnaGeneType(_cont,cl_);
                fetchFieldsType(_cont,!_baseClass,_superClass,t.getAncestor(),t.getScope() == MethodAccessKind.STATIC,_aff,_name,glClass_,ancestors_,f_,root_,baseTypes_,superTypesBaseAncBis_);
                maxAnc_ = Math.max(maxAnc_, t.getAncestor());
            }
        }
        int max_ = maxAnc_;
        for (int i = 0; i <= maxAnc_; i++) {
            StringList subClasses_ = new StringList();
            for (EntryCust<String,FieldResult> e: ancestors_.entryList()) {
                if (e.getValue().getAnc() != i) {
                    continue;
                }
                subClasses_.add(e.getKey());
            }
            StringList subs_ = AnaTypeUtil.getSubclasses(subClasses_, _cont);
            FieldResult res_ = getRes(ancestors_, subs_);
            if (res_ != null) {
                return res_;
            }
        }
        if (_import) {
            int maxLoc_ = maxAnc_ + 1;
            for (EntryCust<String, ImportedField> e: ResolvingImportTypes.lookupImportStaticFields(_cont,curClassBase_, _name).entryList()) {
                ImportedField v_ = e.getValue();
                max_ = Math.max(max_, v_.getImported() +maxAnc_);
                FieldResult res_ = new FieldResult();
                String realType_ = v_.getType();
                boolean finalField_ = v_.isFinalField();
                String formatted_ = e.getKey();
                FieldInfo if_ = FieldInfo.newFieldInfo(_name, formatted_, realType_, _static, finalField_, _cont, _aff,v_.getReturnType(),v_.getValueOffset());
                res_.setFileName(v_.getFileName());
                res_.setMemberNumber(v_.getMemberNumber());
                res_.setRootNumber(v_.getRootNumber());
                res_.setId(if_);
                res_.setAnc(v_.getImported() +maxAnc_);
                res_.setStatus(SearchingMemberStatus.UNIQ);
                addIfNotExist(ancestors_,e.getKey(),res_);
            }
            for (int i = maxLoc_; i <= max_; i++) {
                StringList subClasses_ = new StringList();
                for (EntryCust<String,FieldResult> e: ancestors_.entryList()) {
                    if (e.getValue().getAnc() != i) {
                        continue;
                    }
                    subClasses_.add(e.getKey());
                }
                StringList subs_ = AnaTypeUtil.getSubclasses(subClasses_, _cont);
                FieldResult res_ = getRes(ancestors_, subs_);
                if (res_ != null) {
                    return res_;
                }
            }
        }
        FieldResult r_ = new FieldResult();
        r_.setStatus(SearchingMemberStatus.ZERO);
        return r_;
    }

    private static void feedTypes(CustList<TypeInfo> _list, StringList _baseTypes, StringMap<String> _superTypesBaseAnc) {
        for (TypeInfo t: _list) {
            if (t.isBase()) {
                String id_ = StringExpUtil.getIdFromAllTypes(t.getType());
                _baseTypes.add(id_);
                _superTypesBaseAnc.put(id_, id_);
                for (String m: t.getSuperTypes()) {
                    _superTypesBaseAnc.put(m, id_);
                }
            }
        }
    }

    private static void fetchFieldsType(ContextEl _conf, boolean _accessFromSuper, boolean _superClass, int _anc, boolean _static,
                                        boolean _aff,
                                        String _name, String _glClass, StringMap<FieldResult> _ancestors,
                                        String _cl, AnaGeneType _root, StringList _superTypesBase, StringMap<String> _superTypesBaseMap) {
        String fullName_ = _root.getFullName();
        String genericString_ = _root.getGenericString();
        String id_ = StringExpUtil.getIdFromAllTypes(fullName_);
        ClassField candidate_ = new ClassField(id_, _name);
        FieldInfo fi_ = ContextUtil.getFieldInfo(_conf,candidate_);
        if (fi_ == null) {
            return;
        }
        boolean staticField_ = fi_.isStaticField();
        if (_static) {
            if (!staticField_) {
                return;
            }
        } else {
            if (staticField_) {
                return;
            }
        }
        if (cannotAccess(_conf,fullName_,fi_.getAccessed(),_glClass,_superTypesBaseMap)) {
            return;
        }
        if (filterMember(_accessFromSuper,_superClass,_superTypesBase,fullName_)) {
            return;
        }
        String formatted_;
        if (staticField_) {
            formatted_ = _cl;
        } else {
            formatted_ = AnaTemplates.quickFormat(_root,_cl,genericString_,_conf);
        }
        String realType_ = fi_.getType();
        boolean finalField_ = fi_.isFinalField();
        int valOffset_ = fi_.getValOffset();
        FieldInfo if_ = FieldInfo.newFieldInfo(_name, formatted_, realType_, staticField_, finalField_, _conf, _aff, null,valOffset_);
        if (if_ == null) {
            return;
        }
        FieldResult res_ = new FieldResult();
        res_.setFileName(fi_.getFileName());
        res_.setMemberNumber(fi_.getMemberNumber());
        res_.setRootNumber(fi_.getRootNumber());
        res_.setId(if_);
        res_.setAnc(_anc);
        res_.setStatus(SearchingMemberStatus.UNIQ);
        addIfNotExist(_ancestors,id_, res_);
    }
    private static FieldResult getRes(StringMap<FieldResult> _ancestors, StringList _classes) {
        if (_classes.onlyOneElt()) {
            return _ancestors.getVal(_classes.first());
        }
        return null;
    }
    private static void addIfNotExist(StringMap<FieldResult> _ancestors, String _cl, FieldResult _res) {
        for (EntryCust<String, FieldResult> e: _ancestors.entryList()) {
            if (StringList.quickEq(e.getKey(), _cl)) {
                return;
            }
        }
        _ancestors.addEntry(_cl,_res);
    }
    static ConstrustorIdVarArg getDeclaredCustConstructor(OperationNode _oper,ContextEl _conf, int _varargOnly, ClassArgumentMatching _class,
                                                          AnaGeneType _type,
                                                          ConstructorId _uniqueId, String _param, ClassArgumentMatching... _args) {
        String clCurName_ = _class.getName();
        int varargOnly_ = _varargOnly;
        boolean uniq_ = false;
        if (_uniqueId != null) {
            uniq_ = isUniqCtor(varargOnly_);
            varargOnly_ = -1;
        }
        CustList<GeneConstructor> constructors_ = ContextUtil.getConstructorBodies(_type);
        if (constructors_.isEmpty()) {
            if (_args.length == 0) {
                ConstrustorIdVarArg out_;
                out_ = new ConstrustorIdVarArg();
                out_.setRealId(new ConstructorId(clCurName_, new StringList(),false));
                out_.setConstId(out_.getRealId());
                setupContainer(_type, out_);
                return out_;
            }
        }
        CustList<ConstructorInfo> signatures_ = new CustList<ConstructorInfo>();
        for (GeneConstructor e: constructors_) {
            ConstructorId ctor_ = e.getId();
            if (exclude(_type,_conf,_uniqueId,varargOnly_, e)) {
                continue;
            }
            ParametersGroup pg_ = new ParametersGroup();
            ConstructorInfo mloc_ = new ConstructorInfo();
            if (e instanceof NamedFunctionBlock) {
                mloc_.setMemberNumber(((NamedFunctionBlock)e).getNameNumber());
            }
            mloc_.setConstraints(ctor_);
            mloc_.setParameters(pg_);
            mloc_.setClassName(clCurName_);
            mloc_.format(_conf);
            if (!isPossibleMethod(_conf, uniq_, varargOnly_, mloc_, _param,_args)) {
                continue;
            }
            signatures_.add(mloc_);
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        ConstructorInfo cInfo_ = sortCtors(signatures_, gr_);
        if (cInfo_ == null) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(StringList.join(c.getNames(), "&"));
            }
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            undefined_.buildError(_conf.getAnalysisMessages().getUndefinedCtor(),
                    new ConstructorId(clCurName_, classesNames_, false).getSignature(_conf));
            _conf.getAnalyzing().getLocalizer().addError(undefined_);
            _oper.getErrs().add(undefined_.getBuiltError());
            ConstrustorIdVarArg out_;
            out_ = new ConstrustorIdVarArg();
            return out_;
        }
        ConstructorId ctor_ = cInfo_.getConstraints();
        int len_ = cInfo_.getImplicits().size();
        for (int i = 0; i < len_; i++) {
            CustList<ImplicitInfos> implicitInfos_ = cInfo_.getImplicits().get(i);
            for (ImplicitInfos j: implicitInfos_) {
                _args[i].getImplicits().add(j.getIdMethod());
                _args[i].setRootNumber(j.getRootNumber());
                _args[i].setMemberNumber(j.getMemberNumber());
            }
        }
        ConstrustorIdVarArg out_;
        out_ = new ConstrustorIdVarArg();
        if (cInfo_.isVarArgWrap()) {
            out_.setVarArgToCall(true);
        }
        out_.setRealId(ctor_);
        out_.setConstId(cInfo_.getFormatted());
        setupContainer(_type, out_);
        out_.setMemberNumber(cInfo_.getMemberNumber());
        return out_;
    }
    static ConstrustorIdVarArg getDeclaredCustConstructorLambda(OperationNode _op,ContextEl _conf, int _varargOnly, ClassArgumentMatching _class,
                                                                AnaGeneType _type,
            ConstructorId _uniqueId, ClassArgumentMatching... _args) {
        String clCurName_ = _class.getName();
        int varargOnly_ = _varargOnly;
        if (_uniqueId != null) {
            varargOnly_ = -1;
        }
        CustList<GeneConstructor> constructors_ = ContextUtil.getConstructorBodies(_type);
        if (constructors_.isEmpty()) {
            if (_args.length == 0) {
                ConstrustorIdVarArg out_;
                out_ = new ConstrustorIdVarArg();
                out_.setRealId(new ConstructorId(clCurName_, new StringList(),false));
                out_.setConstId(out_.getRealId());
                setupContainer(_type, out_);
                return out_;
            }
        }
        CustList<ConstructorInfo> signatures_ = new CustList<ConstructorInfo>();
        for (GeneConstructor e: constructors_) {
            ConstructorId ctor_ = e.getId();
            if (exclude(_type,_conf,_uniqueId,varargOnly_, e)) {
                continue;
            }
            ParametersGroup pg_ = new ParametersGroup();
            ConstructorInfo mloc_ = new ConstructorInfo();
            mloc_.setConstraints(ctor_);
            if (e instanceof NamedFunctionBlock) {
                mloc_.setMemberNumber(((NamedFunctionBlock)e).getNameNumber());
            }
            mloc_.setParameters(pg_);
            mloc_.setClassName(clCurName_);
            mloc_.format(_conf);
            if (!isPossibleMethodLambda(_conf, mloc_, _args)) {
                continue;
            }
            signatures_.add(mloc_);
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        ConstructorInfo cInfo_ = sortCtors(signatures_, gr_);
        if (cInfo_ == null) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(StringList.join(c.getNames(), "&"));
            }
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            undefined_.buildError(_conf.getAnalysisMessages().getUndefinedCtor(),
                    new ConstructorId(clCurName_, classesNames_, false).getSignature(_conf));
            _conf.getAnalyzing().getLocalizer().addError(undefined_);
            _op.getErrs().add(undefined_.getBuiltError());
            ConstrustorIdVarArg out_;
            out_ = new ConstrustorIdVarArg();
            return out_;
        }
        ConstructorId ctor_ = cInfo_.getConstraints();
        ConstrustorIdVarArg out_;
        out_ = new ConstrustorIdVarArg();
        out_.setRealId(ctor_);
        out_.setConstId(cInfo_.getFormatted());
        setupContainer(_type, out_);
        out_.setMemberNumber(cInfo_.getMemberNumber());
        return out_;
    }

    private static void setupContainer(AnaGeneType _type, ConstrustorIdVarArg out_) {
        if (_type instanceof RootBlock) {
            out_.setFileName(((Block)_type).getFile().getFileName());
            out_.setRootNumber(((RootBlock)_type).getNumberAll());
        }
    }

    private static boolean isUniqCtor(int varargOnly_) {
        return varargOnly_ > -1;
    }

    protected static boolean exclude(AnaGeneType _g,ContextEl _conf, ConstructorId _uniqueId, int _varargOnly, GeneConstructor e) {
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        ConstructorId ctor_ = e.getId();
        boolean varArg_ = ctor_.isVararg();
        if (_varargOnly > -1) {
            if (!varArg_) {
                return true;
            }
        }
        if (_uniqueId != null) {
            if (!_uniqueId.eq(ctor_)) {
                return true;
            }
        }
        String outer_ ="";
        if (_g instanceof RootBlock) {
            outer_ = ((RootBlock)_g).getOuterFullName();
        }
        if (e instanceof ConstructorBlock) {
            ConstructorBlock c = (ConstructorBlock) e;
            Accessed a_ = new Accessed(c.getAccess(), _g.getPackageName(), _g.getFullName(), outer_);
            return !ContextUtil.canAccess(glClass_, a_, _conf);
        }
        return false;
    }

    void checkNull(Argument _arg, ContextEl _conf) {
        if (Argument.isNullValue(_arg)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //leaf or header parent or first operator
            static_.buildError(_conf.getAnalysisMessages().getNullValue(),
                    _conf.getStandards().getAliasNullPe());
            _conf.getAnalyzing().getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
        }
    }
    static ClassMethodIdReturn getDeclaredCustMethod(OperationNode _op,ContextEl _conf, int _varargOnly,
                                                     MethodAccessKind _staticContext, StringList _classes, String _name,
                                                     boolean _superClass, boolean _accessFromSuper, boolean _import, ClassMethodIdAncestor _uniqueId, String _param, ClassArgumentMatching... _argsClass) {
        ClassMethodIdReturn res_ = tryGetDeclaredCustMethod(_conf, _varargOnly, _staticContext,false, _classes, _name, _superClass, _accessFromSuper, _import, _uniqueId, _param,_argsClass);
        if (res_.isFoundMethod()) {
            return res_;
        }
        ClassMethodIdReturn return_ = new ClassMethodIdReturn(false);
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringList.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //_name len
        undefined_.buildError(_conf.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(_staticContext, _name, classesNames_).getSignature(_conf));
        _conf.getAnalyzing().getLocalizer().addError(undefined_);
        _op.getErrs().add(undefined_.getBuiltError());
        return_.setId(new ClassMethodId(_classes.first(), new MethodId(_staticContext, _name, classesNames_)));
        return_.setRealId(new MethodId(_staticContext, _name, classesNames_));
        return_.setRealClass(_classes.first());
        return_.setReturnType(_conf.getStandards().getAliasObject());
        return return_;
    }
    static ClassMethodIdReturn getDeclaredCustTrueFalse(OperationNode _op, ContextEl _conf,
                                                        MethodAccessKind _staticContext, StringList _classes, String _name,
                                                        ClassMethodId _uniqueId, ClassArgumentMatching... _argsClass) {
        ClassMethodIdReturn res_ = tryGetDeclaredCustTrueFalse(_conf, _staticContext, _classes, _name, _uniqueId,_argsClass);
        if (res_.isFoundMethod()) {
            return res_;
        }
        ClassMethodIdReturn return_ = new ClassMethodIdReturn(false);
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringList.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //_name len
        undefined_.buildError(_conf.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(_staticContext, _name, classesNames_).getSignature(_conf));
        _conf.getAnalyzing().getLocalizer().addError(undefined_);
        _op.getErrs().add(undefined_.getBuiltError());
        return_.setId(new ClassMethodId(_classes.first(), new MethodId(_staticContext, _name, classesNames_)));
        return_.setRealId(new MethodId(_staticContext, _name, classesNames_));
        return_.setRealClass(_classes.first());
        return_.setReturnType(_conf.getStandards().getAliasObject());
        return return_;
    }
    static ClassMethodIdReturn getDeclaredCustMethodLambda(OperationNode _op,ContextEl _conf, int _varargOnly,
                                                           MethodAccessKind _staticContext, StringList _classes, String _name,
                                                           boolean _superClass, boolean _accessFromSuper, ClassMethodIdAncestor _uniqueId, ClassArgumentMatching... _argsClass) {
        ClassMethodIdReturn res_ = tryGetDeclaredCustMethodLambda(_conf, _varargOnly, _staticContext, _classes, _name, _superClass, _accessFromSuper, false, _uniqueId, _argsClass);
        if (res_.isFoundMethod()) {
            return res_;
        }
        ClassMethodIdReturn return_ = new ClassMethodIdReturn(false);
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringList.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //_name len
        undefined_.buildError(_conf.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(_staticContext, _name, classesNames_).getSignature(_conf));
        _conf.getAnalyzing().getLocalizer().addError(undefined_);
        _op.getErrs().add(undefined_.getBuiltError());
        return_.setId(new ClassMethodId(_classes.first(), new MethodId(_staticContext, _name, classesNames_)));
        return_.setRealId(new MethodId(_staticContext, _name, classesNames_));
        return_.setRealClass(_classes.first());
        return_.setReturnType(_conf.getStandards().getAliasObject());
        return return_;
    }

    protected static ClassArgumentMatching voidToObject(ClassArgumentMatching _original, ContextEl _context) {
        if (_original.matchVoid(_context)) {
            return new ClassArgumentMatching(_context.getStandards().getAliasObject());
        }
        return _original;
    }

    protected static ClassMethodIdReturn tryGetDeclaredCustMethod(ContextEl _conf, int _varargOnly,
                                                                  MethodAccessKind _staticContext,
                                                                  boolean _excVararg,
                                                                  StringList _classes, String _name,
                                                                  boolean _superClass, boolean _accessFromSuper,
                                                                  boolean _import, ClassMethodIdAncestor _uniqueId,
                                                                  String _param,ClassArgumentMatching[] _argsClass) {
        CustList<CustList<MethodInfo>> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _staticContext, _accessFromSuper, _superClass, _classes, _name, _import, _uniqueId);
        int varargOnly_ = _varargOnly;
        boolean uniq_ = uniq(_uniqueId,_varargOnly);
        if (_uniqueId != null) {
            varargOnly_ = -1;
        }
        return getCustResult(_conf,uniq_,_excVararg, varargOnly_, methods_, _name, _param,_argsClass);
    }

    protected static ClassMethodIdReturn tryGetDeclaredCustTrueFalse(ContextEl _conf,
                                                                     MethodAccessKind _staticContext,
                                                                     StringList _classes, String _name,
                                                                     ClassMethodId _uniqueId,
                                                                     ClassArgumentMatching[] _argsClass) {
        if (_argsClass.length != 1 || _staticContext == MethodAccessKind.STATIC) {
            return new ClassMethodIdReturn(false);
        }
        ClassMethodIdReturn res_;
        if (StringList.quickEq(_name,_conf.getKeyWords().getKeyWordTrue())) {
            res_ = fetchTrueOperator(_conf,_classes,_argsClass[0],_uniqueId);
        } else {
            res_ = fetchFalseOperator(_conf,_classes,_argsClass[0],_uniqueId);
        }
        if (res_.isFoundMethod()) {
            ClassMethodId id_ = res_.getId();
            MethodId cts_ = id_.getConstraints();
            res_.setId(new ClassMethodId(id_.getClassName(),new MethodId(cts_.getKind(),cts_.getName(),cts_.shiftFirst(),cts_.isVararg())));
        }
        return res_;
    }
    protected static ClassMethodIdReturn tryGetDeclaredCustIncrDecrMethod(ContextEl _conf,
                                                                          StringList _classes, String _name,
                                                                          ClassArgumentMatching _argsClass) {
        CustList<CustList<MethodInfo>> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, MethodAccessKind.STATIC, false, false, _classes, _name, false, null);
        return getCustIncrDecrResult(_conf, methods_, _name, _argsClass);
    }
    protected static ReversibleConversion tryGetPair(ContextEl _conf,ClassArgumentMatching _argsClass) {
        StringList conv_ = new StringList();
        conv_.add(_conf.getStandards().getAliasPrimInteger());
        conv_.add(_conf.getStandards().getAliasPrimLong());
        conv_.add(_conf.getStandards().getAliasPrimFloat());
        conv_.add(_conf.getStandards().getAliasPrimDouble());
        for (String p: conv_) {
            ClassArgumentMatching r_ = new ClassArgumentMatching(p);
            ClassMethodIdReturn from_ = tryGetDeclaredImplicitCast(_conf, p, _argsClass);
            if (!from_.isFoundMethod()) {
                continue;
            }
            String param_ = from_.getRealId().getParametersType(1);
            ClassMethodIdReturn to_ = tryGetDeclaredImplicitCast(_conf, param_, r_);
            if (!to_.isFoundMethod()) {
                continue;
            }
            ClassMethodId clFrom_ = new ClassMethodId(from_.getId().getClassName(),from_.getRealId());
            ClassMethodId clTo_ = new ClassMethodId(to_.getId().getClassName(),to_.getRealId());
            return new ReversibleConversion(clFrom_,from_.getRootNumber(),from_.getMemberNumber(),
                    clTo_, to_.getRootNumber(), to_.getMemberNumber());
        }
        return null;
    }
    protected static ClassMethodIdReturn tryGetDeclaredCustMethodLambda(ContextEl _conf, int _varargOnly,
                                                                        MethodAccessKind _staticContext,
                                                                        StringList _classes, String _name,
                                                                        boolean _superClass, boolean _accessFromSuper,
                                                                        boolean _import, ClassMethodIdAncestor _uniqueId,
                                                                        ClassArgumentMatching[] _argsClass) {
        CustList<CustList<MethodInfo>> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _staticContext, _accessFromSuper, _superClass, _classes, _name, _import, _uniqueId);
        int varargOnly_ = _varargOnly;
        if (_uniqueId != null) {
            varargOnly_ = -1;
        }
        return getCustResultLambda(_conf, varargOnly_, methods_, _name, _argsClass);
    }

    protected static ClassMethodIdReturn tryGetDeclaredTrue(ContextEl _conf, String _classes) {
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        fetchTrue(_conf,methods_,_classes, null);
        CustList<MethodInfo> candidates_ = new CustList<MethodInfo>();
        for (MethodInfo m: methods_) {
            if (StringList.quickEq(
                    StringExpUtil.getIdFromAllTypes(m.getClassName()),
                    StringExpUtil.getIdFromAllTypes(_classes))){
                candidates_.add(m);
            }
        }
        if (candidates_.size() == 1) {
            MethodInfo m_ = candidates_.first();
            MethodId constraints_ = m_.getConstraints();
            String baseClassName_ = m_.getClassName();
            ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
            MethodId id_ = m_.getFoundFormatted();
            ClassMethodId idForm_ = new ClassMethodId(baseClassName_, id_);
            res_.setId(idForm_);
            res_.setRealId(constraints_);
            res_.setRealClass(baseClassName_);
            res_.setReturnType(m_.getReturnType());
            res_.setOriginalReturnType(m_.getOriginalReturnType());
            res_.setFileName(m_.getFileName());
            res_.setMemberNumber(m_.getMemberNumber());
            res_.setRootNumber(m_.getRootNumber());
            res_.setStandardMethod(m_.getStandardMethod());
            res_.setAncestor(m_.getAncestor());
            res_.setAbstractMethod(m_.isAbstractMethod());
            res_.setStaticMethod(m_.isStatic());
            MethodId cts_ = idForm_.getConstraints();
            res_.setId(new ClassMethodId(idForm_.getClassName(),new MethodId(cts_.getKind(),cts_.getName(),cts_.shiftFirst(),cts_.isVararg())));
            return res_;
        }
        return new ClassMethodIdReturn(false);
    }

    protected static ClassMethodIdReturn tryGetDeclaredFalse(ContextEl _conf, String _classes) {
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        fetchFalse(_conf,methods_,_classes, null);
        CustList<MethodInfo> candidates_ = new CustList<MethodInfo>();
        for (MethodInfo m: methods_) {
            if (StringList.quickEq(
                    StringExpUtil.getIdFromAllTypes(m.getClassName()),
                    StringExpUtil.getIdFromAllTypes(_classes))){
                candidates_.add(m);
            }
        }
        if (candidates_.size() == 1) {
            MethodInfo m_ = candidates_.first();
            MethodId constraints_ = m_.getConstraints();
            String baseClassName_ = m_.getClassName();
            ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
            MethodId id_ = m_.getFoundFormatted();
            ClassMethodId idForm_ = new ClassMethodId(baseClassName_, id_);
            res_.setId(idForm_);
            res_.setRealId(constraints_);
            res_.setRealClass(baseClassName_);
            res_.setReturnType(m_.getReturnType());
            res_.setOriginalReturnType(m_.getOriginalReturnType());
            res_.setFileName(m_.getFileName());
            res_.setMemberNumber(m_.getMemberNumber());
            res_.setRootNumber(m_.getRootNumber());
            res_.setStandardMethod(m_.getStandardMethod());
            res_.setAncestor(m_.getAncestor());
            res_.setAbstractMethod(m_.isAbstractMethod());
            res_.setStaticMethod(m_.isStatic());
            MethodId cts_ = idForm_.getConstraints();
            res_.setId(new ClassMethodId(idForm_.getClassName(),new MethodId(cts_.getKind(),cts_.getName(),cts_.shiftFirst(),cts_.isVararg())));
            return res_;
        }
        return new ClassMethodIdReturn(false);
    }
    protected static ClassMethodIdReturn tryGetDeclaredCast(ContextEl _conf, String _classes, ClassMethodId _uniqueId, ClassArgumentMatching[] _argsClass) {
        CustList<MethodInfo> methods_;
        ClassArgumentMatching cl_;
        if (_argsClass.length > 1) {
            cl_ = _argsClass[1];
        } else {
            cl_ = _argsClass[0];
        }
        methods_ = getDeclaredCustCast(_conf, _classes, _uniqueId, cl_);
        ClassMethodIdReturn res_ = getCustCastResult(_conf, methods_, cl_);
        if (res_.isFoundMethod()) {
            ClassMethodId id_ = res_.getId();
            MethodId cts_ = id_.getConstraints();
            res_.setId(new ClassMethodId(id_.getClassName(),new MethodId(cts_.getKind(),cts_.getName(),cts_.shiftFirst(),cts_.isVararg())));
        }
        return res_;
    }
    protected static ClassMethodIdReturn tryGetDeclaredImplicitCast(ContextEl _conf, String _classes, ClassMethodId _uniqueId, ClassArgumentMatching[] _argsClass) {
        CustList<MethodInfo> methods_;
        ClassArgumentMatching cl_;
        if (_argsClass.length > 1) {
            cl_ = _argsClass[1];
        } else {
            cl_ = _argsClass[0];
        }
        methods_ = getDeclaredCustImplicitCast(_conf, _classes, _uniqueId, cl_);
        ClassMethodIdReturn res_ = getCustCastResult(_conf, methods_, cl_);
        if (res_.isFoundMethod()) {
            ClassMethodId id_ = res_.getId();
            MethodId cts_ = id_.getConstraints();
            res_.setId(new ClassMethodId(id_.getClassName(),new MethodId(cts_.getKind(),cts_.getName(),cts_.shiftFirst(),cts_.isVararg())));
        }
        return res_;
    }
    public static ClassMethodIdReturn tryGetDeclaredImplicitCast(ContextEl _conf, String _classes, ClassArgumentMatching _arg) {
        CustList<MethodInfo> methods_ = new CustList<MethodInfo>();
        for (String n: _arg.getNames()) {
            methods_.addAllElts(getDeclaredCustImplicitCast(_conf, _classes,n));
        }
        return getCustCastResult(_conf, methods_, _arg);
    }

    static OperatorConverter getUnaryOperatorOrMethod(MethodOperation _node,
                                                  ClassArgumentMatching _operand,
                                                  String _op, ContextEl _cont) {
        if (isNativeUnaryOperator(_operand,_op,_cont)) {
            return null;
        }
        CustList<CustList<MethodInfo>> listsUnary_ = new CustList<CustList<MethodInfo>>();
        CustList<MethodInfo> listUnary_ = new CustList<MethodInfo>();
        for (String n:_operand.getNames()) {
            fetchUnary(_cont, listUnary_,n);
        }
        listsUnary_.add(listUnary_);
        ClassMethodIdReturn clMethImp_ = getCustResult(_cont, false, true, -1, listsUnary_, _op, "", _operand);
        if (clMethImp_.isFoundMethod()) {
            CustList<OperationNode> chidren_ = _node.getChildrenNodes();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            for (OperationNode o: chidren_) {
                firstArgs_.add(o.getResultClass());
            }
            _node.setResultClass(voidToObject(new ClassArgumentMatching(clMethImp_.getReturnType()), _cont));
            String foundClass_ = clMethImp_.getRealClass();
            MethodId id_ = clMethImp_.getRealId();
            MethodId realId_ = clMethImp_.getRealId();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _cont);
            OperatorConverter op_ = new OperatorConverter();
            op_.setSymbol(new ClassMethodId(foundClass_, id_));
            op_.setRootNumber(clMethImp_.getRootNumber());
            op_.setMemberNumber(clMethImp_.getMemberNumber());
            return op_;
        }
        ClassMethodIdReturn clId_ = getCustomOperatorOrMethod(_node, _op, _cont);
        if (clId_ != null) {
            String foundClass_ = clId_.getRealClass();
            MethodId id_ = clId_.getRealId();
            OperatorConverter op_ = new OperatorConverter();
            op_.setSymbol(new ClassMethodId(foundClass_, id_));
            op_.setRootNumber(clId_.getRootNumber());
            op_.setMemberNumber(clId_.getMemberNumber());
            return op_;
        }
        CustList<StringList> groups_ = new CustList<StringList>();
        if (StringList.quickEq(_op,"+")
                ||StringList.quickEq(_op,"-")) {
            StringList group_ = new StringList();
            group_.add(_cont.getStandards().getAliasPrimInteger());
            group_.add(_cont.getStandards().getAliasPrimLong());
            groups_.add(group_);
            group_ = new StringList();
            group_.add(_cont.getStandards().getAliasPrimFloat());
            group_.add(_cont.getStandards().getAliasPrimDouble());
            groups_.add(group_);
        } else if (StringList.quickEq(_op,"~")) {
            StringList group_ = new StringList();
            group_.add(_cont.getStandards().getAliasPrimBoolean());
            group_.add(_cont.getStandards().getAliasPrimInteger());
            group_.add(_cont.getStandards().getAliasPrimLong());
            groups_.add(group_);
        }
        for (StringList g: groups_) {
            CustList<CustList<MethodInfo>> lists_ = new CustList<CustList<MethodInfo>>();
            CustList<MethodInfo> list_ = new CustList<MethodInfo>();
            for (String p:g) {
                ParametersGroup p_ = new ParametersGroup();
                MethodInfo mloc_ = new MethodInfo();
                MethodId id_ = new MethodId(MethodAccessKind.STATIC,_op,new StringList(p));
                mloc_.setClassName(p);
                mloc_.setStatic(true);
                mloc_.setConstraints(id_);
                mloc_.setParameters(p_);
                mloc_.setReturnType(p);
                mloc_.format(true,_cont);
                list_.add(mloc_);
            }
            lists_.add(list_);
            ClassMethodIdReturn clMeth_ = getCustResult(_cont, false, true, -1, lists_, _op, "", _operand);
            if (clMeth_.isFoundMethod()) {
                CustList<OperationNode> chidren_ = _node.getChildrenNodes();
                CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
                for (OperationNode o: chidren_) {
                    firstArgs_.add(o.getResultClass());
                }
                _node.setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()), _cont));
                String foundClass_ = clMeth_.getRealClass();
                MethodId id_ = clMeth_.getRealId();
                MethodId realId_ = clMeth_.getRealId();
                InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _cont);
                OperatorConverter op_ = new OperatorConverter();
                op_.setSymbol(new ClassMethodId(foundClass_, id_));
                op_.setRootNumber(clMeth_.getRootNumber());
                op_.setMemberNumber(clMeth_.getMemberNumber());
                return op_;
            }
        }
        return null;
    }

    static ClassMethodIdReturn getIncrDecrOperatorOrMethod(MethodOperation _node,
                                                  ClassArgumentMatching _operand,
                                                  String _op, ContextEl _cont) {
        if (isNativeUnaryOperator(_operand,_op,_cont)) {
            return null;
        }
        CustList<CustList<MethodInfo>> listsUnary_ = new CustList<CustList<MethodInfo>>();
        CustList<MethodInfo> listUnary_ = new CustList<MethodInfo>();
        for (String n:_operand.getNames()) {
            fetchUnary(_cont, listUnary_,n);
        }
        listsUnary_.add(listUnary_);
        ClassMethodIdReturn clMethImp_ = getCustResult(_cont, false, true, -1, listsUnary_, _op, "", _operand);
        if (clMethImp_.isFoundMethod()) {
            CustList<OperationNode> chidren_ = _node.getChildrenNodes();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            for (OperationNode o: chidren_) {
                firstArgs_.add(o.getResultClass());
            }
            _node.setResultClass(voidToObject(new ClassArgumentMatching(clMethImp_.getReturnType()), _cont));
            MethodId realId_ = clMethImp_.getRealId();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _cont);
            return clMethImp_;
        }
        return getCustomIncrDecrOperatorOrMethod(_node,_operand,_op,_cont);
    }
    static OperatorConverter getBinaryOperatorOrMethod(MethodOperation _node,
                                                   ClassArgumentMatching _left, ClassArgumentMatching _right,
                                                   String _op, ContextEl _cont) {
        if (isNativeBinaryOperator(_left,_right,_op,_cont)) {
            return new OperatorConverter();
        }
        CustList<CustList<MethodInfo>> listsBinary_ = new CustList<CustList<MethodInfo>>();
        CustList<MethodInfo> listBinary_ = new CustList<MethodInfo>();
        ClassMethodId convert_ = null;
        int rootTest_ = -1;
        int memberTest_ = -1;
        if (StringList.quickEq(_op,"&&")) {
            CustList<MethodInfo> listTrue_ = new CustList<MethodInfo>();
            for (String n:_left.getNames()) {
                fetchFalse(_cont,listTrue_,n, null);
            }
            ClassMethodIdReturn clMethImp_ = getCustCastResult(_cont, listTrue_,  _left);
            if (clMethImp_.isFoundMethod()) {
                rootTest_ = clMethImp_.getRootNumber();
                memberTest_ = clMethImp_.getMemberNumber();
                convert_ = new ClassMethodId(clMethImp_.getId().getClassName(),clMethImp_.getRealId());
                for (String n:_left.getNames()) {
                    for (String o:_right.getNames()) {
                        fetchBinary(_cont, listBinary_,n,o);
                    }
                }
                listsBinary_.add(listBinary_);
            }
        } else if (StringList.quickEq(_op,"||")) {
            CustList<MethodInfo> listTrue_ = new CustList<MethodInfo>();
            for (String n:_left.getNames()) {
                fetchTrue(_cont,listTrue_,n, null);
            }
            ClassMethodIdReturn clMethImp_ = getCustCastResult(_cont, listTrue_,  _left);
            if (clMethImp_.isFoundMethod()) {
                rootTest_ = clMethImp_.getRootNumber();
                memberTest_ = clMethImp_.getMemberNumber();
                convert_ = new ClassMethodId(clMethImp_.getId().getClassName(),clMethImp_.getRealId());
                for (String n:_left.getNames()) {
                    for (String o:_right.getNames()) {
                        fetchBinary(_cont, listBinary_,n,o);
                    }
                }
                listsBinary_.add(listBinary_);
            }
        } else {
            for (String n:_left.getNames()) {
                for (String o:_right.getNames()) {
                    fetchBinary(_cont, listBinary_,n,o);
                }
            }
            listsBinary_.add(listBinary_);
        }
        ClassMethodIdReturn clMethImp_ = getCustResult(_cont, false, true, -1, listsBinary_, _op, "", _left,_right);
        if (clMethImp_.isFoundMethod()) {
            CustList<OperationNode> chidren_ = _node.getChildrenNodes();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            for (OperationNode o: chidren_) {
                firstArgs_.add(o.getResultClass());
            }
            _node.setResultClass(voidToObject(new ClassArgumentMatching(clMethImp_.getReturnType()), _cont));
            String foundClass_ = clMethImp_.getRealClass();
            MethodId id_ = clMethImp_.getRealId();
            MethodId realId_ = clMethImp_.getRealId();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _cont);
            OperatorConverter op_ = new OperatorConverter();
            op_.setSymbol(new ClassMethodId(foundClass_, id_));
            op_.setRootNumber(clMethImp_.getRootNumber());
            op_.setMemberNumber(clMethImp_.getMemberNumber());
            op_.setTest(convert_);
            op_.setRootNumberTest(rootTest_);
            op_.setMemberNumberTest(memberTest_);
            return op_;
        }
        if (!listsBinary_.isEmpty()) {
            ClassMethodIdReturn clId_ = getCustomOperatorOrMethod(_node, _op, _cont);
            if (clId_ != null) {
                OperatorConverter op_ = new OperatorConverter();
                String foundClass_ = clId_.getRealClass();
                MethodId id_ = clId_.getRealId();
                op_.setSymbol(new ClassMethodId(foundClass_,id_));
                op_.setRootNumber(clId_.getRootNumber());
                op_.setMemberNumber(clId_.getMemberNumber());
                op_.setTest(convert_);
                op_.setRootNumberTest(rootTest_);
                op_.setMemberNumberTest(memberTest_);
                return op_;
            }
        }
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        if (StringList.quickEq(_op,"+")
            ||StringList.quickEq(_op,"-")
            ||StringList.quickEq(_op,"*")
            ||StringList.quickEq(_op,"/")
            ||StringList.quickEq(_op,"%")) {
            CustList<ParamReturn> group_ = new CustList<ParamReturn>();
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimInteger(),_cont.getStandards().getAliasPrimInteger()));
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimLong(),_cont.getStandards().getAliasPrimLong()));
            groups_.add(group_);
            group_ = new CustList<ParamReturn>();
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimFloat(),_cont.getStandards().getAliasPrimFloat()));
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimDouble(),_cont.getStandards().getAliasPrimDouble()));
            groups_.add(group_);
        } else if (StringList.quickEq(_op,"<")
                ||StringList.quickEq(_op,"<=")
                ||StringList.quickEq(_op,">")
                ||StringList.quickEq(_op,">=")) {
            CustList<ParamReturn> group_ = new CustList<ParamReturn>();
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimInteger(),_cont.getStandards().getAliasPrimBoolean()));
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimLong(),_cont.getStandards().getAliasPrimBoolean()));
            groups_.add(group_);
            group_ = new CustList<ParamReturn>();
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimFloat(),_cont.getStandards().getAliasPrimBoolean()));
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimDouble(),_cont.getStandards().getAliasPrimBoolean()));
            groups_.add(group_);
        } else if (StringList.quickEq(_op,"&")
                ||StringList.quickEq(_op,"|")
                ||StringList.quickEq(_op,"^")) {
            CustList<ParamReturn> group_ = new CustList<ParamReturn>();
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimBoolean(),_cont.getStandards().getAliasPrimBoolean()));
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimInteger(),_cont.getStandards().getAliasPrimInteger()));
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimLong(),_cont.getStandards().getAliasPrimLong()));
            groups_.add(group_);
        } else if (StringList.quickEq(_op,"&&")
                ||StringList.quickEq(_op,"||")) {
            CustList<ParamReturn> group_ = new CustList<ParamReturn>();
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimBoolean(),_cont.getStandards().getAliasPrimBoolean()));
            groups_.add(group_);
        } else if (StringList.quickEq(_op,"<<")
                ||StringList.quickEq(_op,">>")
                ||StringList.quickEq(_op,"<<<")
                ||StringList.quickEq(_op,">>>")
                ||StringList.quickEq(_op,"<<<<")
                ||StringList.quickEq(_op,">>>>")) {
            CustList<ParamReturn> group_ = new CustList<ParamReturn>();
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimInteger(),_cont.getStandards().getAliasPrimInteger()));
            group_.add(new ParamReturn(_cont.getStandards().getAliasPrimLong(),_cont.getStandards().getAliasPrimLong()));
            groups_.add(group_);
        }
        for (CustList<ParamReturn> g: groups_) {
            CustList<CustList<MethodInfo>> lists_ = new CustList<CustList<MethodInfo>>();
            CustList<MethodInfo> list_ = new CustList<MethodInfo>();
            for (ParamReturn p:g) {
                ParametersGroup p_ = new ParametersGroup();
                MethodInfo mloc_ = new MethodInfo();
                String paramType_ = p.getParamType();
                MethodId id_ = new MethodId(MethodAccessKind.STATIC,_op,new StringList(paramType_,paramType_));
                mloc_.setClassName(paramType_);
                mloc_.setStatic(true);
                mloc_.setConstraints(id_);
                mloc_.setParameters(p_);
                mloc_.setReturnType(p.getReturnType());
                mloc_.format(true,_cont);
                list_.add(mloc_);
            }
            lists_.add(list_);
            ClassMethodIdReturn clMeth_ = getCustResult(_cont, false, true, -1, lists_, _op, "", _left, _right);
            if (clMeth_.isFoundMethod()) {
                CustList<OperationNode> chidren_ = _node.getChildrenNodes();
                CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
                for (OperationNode o: chidren_) {
                    firstArgs_.add(o.getResultClass());
                }
                _node.setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()), _cont));
                String foundClass_ = clMeth_.getRealClass();
                MethodId id_ = clMeth_.getRealId();
                MethodId realId_ = clMeth_.getRealId();
                InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _cont);
                OperatorConverter op_ = new OperatorConverter();
                op_.setSymbol(new ClassMethodId(foundClass_, id_));
                op_.setRootNumber(clMeth_.getRootNumber());
                op_.setMemberNumber(clMeth_.getMemberNumber());
                return op_;
            }
        }
        return new OperatorConverter();
    }
    private static ClassMethodIdReturn getCustomOperatorOrMethod(MethodOperation _node, String _op, ContextEl _cont) {
        StringList bounds_ = _cont.getAnalyzing().getTypesWithInnerOperators();
        CustList<OperationNode> chidren_ = _node.getChildrenNodes();
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: chidren_) {
            firstArgs_.add(o.getResultClass());
        }
        ClassMethodIdReturn clMeth_ = tryGetDeclaredCustMethod(_cont, -1, MethodAccessKind.STATIC,
                true, bounds_, _op, false, false, false, null,
                "",toArgArray(firstArgs_));
        if (clMeth_.isFoundMethod()) {
            _node.setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()), _cont));
            MethodId realId_ = clMeth_.getRealId();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _cont);
            return clMeth_;
        }
        //implicit use of operator key word
        ClassMethodIdReturn cust_ = getOperator(_cont, null, -1, true, _op, "", toArgArray(firstArgs_));
        if (cust_.isFoundMethod()) {
            _node.setResultClass(voidToObject(new ClassArgumentMatching(cust_.getReturnType()), _cont));
            MethodId realId_ = cust_.getRealId();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _cont);
            return cust_;
        }
        return null;
    }
    private static ClassMethodIdReturn getCustomIncrDecrOperatorOrMethod(MethodOperation _node, ClassArgumentMatching _arg, String _op, ContextEl _cont) {
        StringList bounds_ = _cont.getAnalyzing().getTypesWithInnerOperators();
        CustList<OperationNode> chidren_ = _node.getChildrenNodes();
        ClassMethodIdReturn clMeth_ = tryGetDeclaredCustIncrDecrMethod(_cont,
                bounds_, _op,
                _arg);
        if (clMeth_.isFoundMethod()) {
            _node.setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()), _cont));
            MethodId realId_ = clMeth_.getRealId();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, new CustList<ClassArgumentMatching>(_arg), _cont);
            return clMeth_;
        }
        ClassMethodIdReturn cust_ = getIncrDecrOperator(_cont, _op, _arg);
        if (cust_.isFoundMethod()) {
            _node.setResultClass(voidToObject(new ClassArgumentMatching(cust_.getReturnType()), _cont));
            MethodId realId_ = cust_.getRealId();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, new CustList<ClassArgumentMatching>(_arg), _cont);
            return cust_;
        }
        return null;
    }
    private static boolean isNativeUnaryOperator(ClassArgumentMatching _operand, String _op, ContextEl _cont) {
        if (StringList.quickEq(_op,"!")) {
            return _operand.isBoolType(_cont);
        }
        if (StringList.quickEq(_op,"~")) {
            int order_ = AnaTypeUtil.getIntOrderClass(_operand, _cont);
            return order_ != 0;
        }
        return PrimitiveTypeUtil.isPureNumberClass(_operand,_cont);
    }
    private static boolean isNativeBinaryOperator(ClassArgumentMatching _left, ClassArgumentMatching _right,String _op, ContextEl _cont) {
        if (StringList.quickEq(_op,"+")) {
            if (AnaTypeUtil.isIntOrderClass(_left,_right,_cont)) {
                return true;
            }
            if (AnaTypeUtil.isFloatOrderClass(_left,_right,_cont)) {
                return true;
            }
            if (_left.matchClass(_cont.getStandards().getAliasString())) {
                if (_right.matchClass(_cont.getStandards().getAliasNumber())) {
                    return true;
                }
                if (PrimitiveTypeUtil.isPureNumberClass(_right,_cont)) {
                    return true;
                }
                if (_right.isBoolType(_cont)) {
                    return true;
                }
                if (_right.matchClass(_cont.getStandards().getAliasString())) {
                    return true;
                }
                if (_right.matchClass(_cont.getStandards().getAliasStringBuilder())) {
                    return true;
                }
                return _right.matchClass(_cont.getStandards().getAliasObject());
            }
            if (_right.matchClass(_cont.getStandards().getAliasString())) {
                if (_left.matchClass(_cont.getStandards().getAliasNumber())) {
                    return true;
                }
                if (PrimitiveTypeUtil.isPureNumberClass(_left,_cont)) {
                    return true;
                }
                if (_left.isBoolType(_cont)) {
                    return true;
                }
                return _left.matchClass(_cont.getStandards().getAliasObject());
            }
            return false;
        }
        if (StringList.quickEq(_op,"-") || StringList.quickEq(_op,"*")
                ||StringList.quickEq(_op,"/") || StringList.quickEq(_op,"%")) {
            if (AnaTypeUtil.isIntOrderClass(_left,_right,_cont)) {
                return true;
            }
            return AnaTypeUtil.isFloatOrderClass(_left, _right, _cont);
        }
        if (StringList.quickEq(_op,"<") || StringList.quickEq(_op,">")
                ||StringList.quickEq(_op,"<=") || StringList.quickEq(_op,">=")) {
            if (AnaTypeUtil.isIntOrderClass(_left,_right,_cont)) {
                return true;
            }
            if (AnaTypeUtil.isFloatOrderClass(_left,_right,_cont)) {
                return true;
            }
            return _left.matchClass(_cont.getStandards().getAliasString())
                    &&_right.matchClass(_cont.getStandards().getAliasString());
        }
        if (StringList.quickEq(_op,"&") || StringList.quickEq(_op,"|")
                ||StringList.quickEq(_op,"^")) {
            if (AnaTypeUtil.isIntOrderClass(_left,_right,_cont)) {
                return true;
            }
            return _left.isBoolType(_cont)&&_right.isBoolType(_cont);
        }
        if (StringList.quickEq(_op,"&&") || StringList.quickEq(_op,"||")) {
            return _left.isBoolType(_cont)&&_right.isBoolType(_cont);
        }
        if (StringList.quickEq(_op,"<<") || StringList.quickEq(_op,">>")
                ||StringList.quickEq(_op,"<<<") || StringList.quickEq(_op,">>>")
                ||StringList.quickEq(_op,"<<<<") || StringList.quickEq(_op,">>>>")) {
            return AnaTypeUtil.isIntOrderClass(_left,_right,_cont);
        }
        if (AnaTypeUtil.isIntOrderClass(_left,_right,_cont)) {
            return true;
        }
        if (AnaTypeUtil.isFloatOrderClass(_left,_right,_cont)) {
            return true;
        }
        if (_left.matchClass(_cont.getStandards().getAliasNumber())
                &&_right.matchClass(_cont.getStandards().getAliasNumber())) {
            return true;
        }
        if (_left.isBoolType(_cont)&&_right.isBoolType(_cont)) {
            return true;
        }
        if (_left.matchClass(_cont.getStandards().getAliasString())
                &&_right.matchClass(_cont.getStandards().getAliasString())) {
            return true;
        }
        return _left.matchClass(_cont.getStandards().getAliasObject())
                && _right.matchClass(_cont.getStandards().getAliasObject());
    }

    private static ClassMethodIdReturn getIncrDecrOperator(ContextEl _cont, String _op, ClassArgumentMatching _argsClass) {
        //implicit use of operator key word
        CustList<MethodInfo> ops_ = getOperators(_cont, null);
        CustList<CustList<MethodInfo>> o_ = new CustList<CustList<MethodInfo>>();
        o_.add(ops_);
        return getCustIncrDecrResult(_cont, o_, _op, _argsClass);
    }
    static ClassMethodIdReturn getOperator(ContextEl _cont, ClassMethodId _cl, int _varargOnly,
                                           boolean _excVararg,
                                           String _op, String _param,ClassArgumentMatching... _argsClass) {
        CustList<MethodInfo> ops_ = getOperators(_cont, _cl);
        int varargOnly_ = _varargOnly;
        boolean uniq_ = uniq(_cl,_varargOnly);
        if (_cl != null) {
            varargOnly_ = -1;
        }
        CustList<CustList<MethodInfo>> o_ = new CustList<CustList<MethodInfo>>();
        o_.add(ops_);
        return getCustResult(_cont,uniq_, _excVararg,varargOnly_, o_, _op, _param,_argsClass);
    }

    static ClassMethodIdReturn getOperatorLambda(ContextEl _cont, ClassMethodId _cl, int _varargOnly,
                                                 String _op, ClassArgumentMatching... _argsClass) {
        CustList<MethodInfo> ops_ = getOperators(_cont, _cl);
        int varargOnly_ = _varargOnly;
        if (_cl != null) {
            varargOnly_ = -1;
        }
        CustList<CustList<MethodInfo>> o_ = new CustList<CustList<MethodInfo>>();
        o_.add(ops_);
        return getCustResultLambda(_cont, varargOnly_, o_, _op, _argsClass);
    }
    private static boolean uniq(Object _cl, int _varargOnly) {
        boolean uniq_ = false;
        if (_cl != null) {
            uniq_ = isUniqCtor(_varargOnly);
        }
        return uniq_;
    }
    static CustList<MethodInfo> getOperators(ContextEl _cont, ClassMethodId _cl){
        String objType_ = _cont.getStandards().getAliasObject();
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        for (EntryCust<OperatorBlock,ExecOperatorBlock> e: _cont.getAnalyzing().getMapOperators().entryList()) {
            OperatorBlock o = e.getKey();
            String ret_ = o.getImportedReturnType();
            MethodId id_ = o.getId();
            if (_cl != null) {
                if (!_cl.getConstraints().eq(id_)) {
                    continue;
                }
            }
            ParametersGroup p_ = new ParametersGroup();
            MethodInfo mloc_ = new MethodInfo();
            mloc_.setClassName(objType_);
            mloc_.setStatic(true);
            mloc_.setConstraints(id_);
            mloc_.setParameters(p_);
            mloc_.setReturnType(ret_);
            mloc_.setOriginalReturnType(ret_);
            mloc_.setMemberNumber(o.getNameNumber());
            mloc_.setFileName(o.getFile().getFileName());
            mloc_.format(true,_cont);
            methods_.add(mloc_);
        }
        return methods_;
    }
    private static CustList<MethodInfo>
    getDeclaredCustImplicitCast(ContextEl _conf,
                        String _fromClass, ClassMethodId _uniqueId, ClassArgumentMatching _arg) {
        String single_ = _arg.getSingleNameOrEmpty();
        return getDeclaredCustImplicitCast(_conf, _fromClass, _uniqueId, single_);
    }
    private static CustList<MethodInfo>
    getDeclaredCustCast(ContextEl _conf,
                        String _fromClass, ClassMethodId _uniqueId, ClassArgumentMatching _arg) {
        String single_ = _arg.getSingleNameOrEmpty();
        return getDeclaredCustCast(_conf, _fromClass, _uniqueId, single_);
    }

    private static CustList<MethodInfo> getDeclaredCustCast(ContextEl _conf, String _fromClass, ClassMethodId _uniqueId, String _single) {
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        String idFrom_ = StringExpUtil.getIdFromAllTypes(_single);
        String id_ = StringExpUtil.getIdFromAllTypes(_fromClass);
        StringMap<String> superTypesBaseAnc_ = new StringMap<String>();
        superTypesBaseAnc_.addEntry(idFrom_,idFrom_);
        StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
        superTypesBaseAncBis_.addEntry(id_,id_);
        CustList<MethodHeaderInfo> casts_ = _conf.getAnalyzing().getExplicitCastMethods().getVal(id_);
        CustList<MethodHeaderInfo> castsId_ = _conf.getAnalyzing().getExplicitIdCastMethods().getVal(id_);
        RootBlock tId_ = _conf.getAnalyzing().getAnaClassBody(id_);
        CustList<MethodHeaderInfo> castsFrom_ = _conf.getAnalyzing().getExplicitFromCastMethods().getVal(idFrom_);
        RootBlock tDi_ = _conf.getAnalyzing().getAnaClassBody(idFrom_);
        fetchCastMethods(tId_,_conf,  _uniqueId, glClass_, methods_, _fromClass,_fromClass, casts_, superTypesBaseAncBis_);
        fetchCastMethods(tId_,_conf,  _uniqueId, glClass_, methods_, _fromClass,_fromClass, castsId_, superTypesBaseAncBis_);
        fetchCastMethods(tDi_,_conf,  _uniqueId, glClass_, methods_, _fromClass,_single, castsFrom_, superTypesBaseAnc_);
        return methods_;
    }

    private static CustList<MethodInfo> getDeclaredCustImplicitCast(ContextEl _conf, String _fromClass, ClassMethodId _uniqueId, String _single) {
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        String idFrom_ = StringExpUtil.getIdFromAllTypes(_single);
        String id_ = StringExpUtil.getIdFromAllTypes(_fromClass);
        StringMap<String> superTypesBaseAnc_ = new StringMap<String>();
        superTypesBaseAnc_.addEntry(idFrom_,idFrom_);
        StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
        superTypesBaseAncBis_.addEntry(id_,id_);
        CustList<MethodHeaderInfo> casts_ = _conf.getAnalyzing().getImplicitCastMethods().getVal(id_);
        CustList<MethodHeaderInfo> castsId_ = _conf.getAnalyzing().getImplicitIdCastMethods().getVal(id_);
        RootBlock tId_ = _conf.getAnalyzing().getAnaClassBody(id_);
        CustList<MethodHeaderInfo> castsFrom_ = _conf.getAnalyzing().getImplicitFromCastMethods().getVal(idFrom_);
        RootBlock tDi_ = _conf.getAnalyzing().getAnaClassBody(idFrom_);
        fetchCastMethods(tId_,_conf,  _uniqueId, glClass_, methods_, _fromClass,_fromClass, casts_, superTypesBaseAncBis_);
        fetchCastMethods(tId_,_conf,  _uniqueId, glClass_, methods_, _fromClass,_fromClass, castsId_, superTypesBaseAncBis_);
        fetchCastMethods(tDi_,_conf,  _uniqueId, glClass_, methods_, _fromClass,_single, castsFrom_, superTypesBaseAnc_);
        return methods_;
    }


    private static CustList<MethodInfo> getDeclaredCustImplicitCast(ContextEl _conf, String _fromClass, String _single) {
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        fetchTo(_conf, glClass_, methods_, _fromClass,_fromClass);
        fetchFrom(_conf, glClass_, methods_, _fromClass,_single);
        return methods_;
    }

    private static void fetchFrom(ContextEl _conf, String glClass_, CustList<MethodInfo> methods_,  String _returnType,String _id) {
        if (!ExplicitOperation.customCast(_id)) {
            return;
        }
        String di_ = StringExpUtil.getIdFromAllTypes(_id);
        RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(di_);
        if (r_ == null) {
            return;
        }
        StringList allClasses_ = new StringList(r_.getGenericString());
        allClasses_.addAllElts(r_.getAllGenericSuperTypes());
        for (String s: allClasses_) {
            String formatted_ = AnaTemplates.quickFormat(r_,_id,s,_conf);
            String supId_ = StringExpUtil.getIdFromAllTypes(formatted_);
            StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
            superTypesBaseAncBis_.addEntry(supId_,supId_);
            CustList<MethodHeaderInfo> castsFrom_ = _conf.getAnalyzing().getImplicitFromCastMethods().getVal(supId_);
            RootBlock t_ = _conf.getAnalyzing().getAnaClassBody(supId_);
            fetchCastMethods(t_,_conf,  null, glClass_, methods_, _returnType,formatted_, castsFrom_, superTypesBaseAncBis_);
        }
    }

    private static void fetchTo(ContextEl _conf, String glClass_, CustList<MethodInfo> methods_,  String _returnType,String _id) {
        StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
        if (!ExplicitOperation.customCast(_id)) {
            return;
        }
        String di_ = StringExpUtil.getIdFromAllTypes(_id);
        superTypesBaseAncBis_.addEntry(di_,di_);
        CustList<MethodHeaderInfo> casts_ = _conf.getAnalyzing().getImplicitCastMethods().getVal(di_);
        CustList<MethodHeaderInfo> castsId_ = _conf.getAnalyzing().getImplicitIdCastMethods().getVal(di_);
        RootBlock t_ = _conf.getAnalyzing().getAnaClassBody(di_);
        fetchCastMethods(t_,_conf,  null, glClass_, methods_, _returnType,_id, casts_, superTypesBaseAncBis_);
        fetchCastMethods(t_,_conf,  null, glClass_, methods_, _returnType,_id, castsId_, superTypesBaseAncBis_);
    }

    private static void fetchBinary(ContextEl _conf, CustList<MethodInfo> methods_, String _first, String _second) {
        if (ExplicitOperation.customCast(_first)) {
            String di_ = StringExpUtil.getIdFromAllTypes(_first);
            RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(di_);
            if (r_ != null) {
                CustList<AnaFormattedRootBlock> allClasses_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(r_,r_.getGenericString()));
                allClasses_.addAllElts(r_.getAllGenericSuperTypesInfo());
                for (AnaFormattedRootBlock s: allClasses_) {
                    String formatted_ = AnaTemplates.quickFormat(r_,_first,s.getFormatted(),_conf);
                    String supId_ = StringExpUtil.getIdFromAllTypes(formatted_);
                    CustList<MethodHeaderInfo> binaryFirst_ = _conf.getAnalyzing().getBinaryFirst().getVal(supId_);
                    CustList<MethodHeaderInfo> binaryAll_ = _conf.getAnalyzing().getBinaryAll().getVal(supId_);
                    fetchImproveOperators(_conf, s.getRootBlock(),methods_,formatted_, binaryFirst_);
                    fetchImproveOperators(_conf, s.getRootBlock(),methods_,formatted_, binaryAll_);
                }
            }
        }
        if (ExplicitOperation.customCast(_second)) {
            String di_ = StringExpUtil.getIdFromAllTypes(_second);
            RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(di_);
            if (r_ != null) {
                CustList<AnaFormattedRootBlock> allClasses_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(r_,r_.getGenericString()));
                allClasses_.addAllElts(r_.getAllGenericSuperTypesInfo());
                for (AnaFormattedRootBlock s: allClasses_) {
                    String formatted_ = AnaTemplates.quickFormat(r_,_second,s.getFormatted(),_conf);
                    String supId_ = StringExpUtil.getIdFromAllTypes(formatted_);
                    CustList<MethodHeaderInfo> binarySecond_ = _conf.getAnalyzing().getBinarySecond().getVal(supId_);
                    CustList<MethodHeaderInfo> binaryAll_ = _conf.getAnalyzing().getBinaryAll().getVal(supId_);
                    fetchImproveOperators(_conf, s.getRootBlock(),methods_,formatted_, binarySecond_);
                    fetchImproveOperators(_conf, s.getRootBlock(),methods_,formatted_, binaryAll_);
                }
            }
        }
    }
    private static void fetchUnary(ContextEl _conf, CustList<MethodInfo> methods_, String _id) {
        if (!ExplicitOperation.customCast(_id)) {
            return;
        }
        String di_ = StringExpUtil.getIdFromAllTypes(_id);
        RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(di_);
        if (r_ == null) {
            return;
        }
        CustList<AnaFormattedRootBlock> allClasses_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(r_,r_.getGenericString()));
        allClasses_.addAllElts(r_.getAllGenericSuperTypesInfo());
        for (AnaFormattedRootBlock s: allClasses_) {
            String formatted_ = AnaTemplates.quickFormat(r_,_id,s.getFormatted(),_conf);
            String supId_ = StringExpUtil.getIdFromAllTypes(formatted_);
            CustList<MethodHeaderInfo> castsFrom_ = _conf.getAnalyzing().getUnary().getVal(supId_);
            fetchImproveOperators(_conf, s.getRootBlock(),methods_,formatted_, castsFrom_);
        }
    }
    public static ClassMethodIdReturn fetchTrueOperator(ContextEl _conf, ClassArgumentMatching _arg) {
        StringList names_ = _arg.getNames();
        return fetchTrueOperator(_conf, names_, _arg, null);
    }
    private static ClassMethodIdReturn fetchFalseOperator(ContextEl _conf, StringList _names, ClassArgumentMatching _arg, ClassMethodId _uniqueId) {
        CustList<MethodInfo> listTrue_ = new CustList<MethodInfo>();
        for (String n: _names) {
            fetchFalse(_conf,listTrue_,n, _uniqueId);
        }
        return getCustCastResult(_conf, listTrue_,  _arg);
    }
    private static ClassMethodIdReturn fetchTrueOperator(ContextEl _conf, StringList _names, ClassArgumentMatching _arg, ClassMethodId _uniqueId) {
        CustList<MethodInfo> listTrue_ = new CustList<MethodInfo>();
        for (String n: _names) {
            fetchTrue(_conf,listTrue_,n, _uniqueId);
        }
        return getCustCastResult(_conf, listTrue_,  _arg);
    }
    private static void fetchTrue(ContextEl _conf, CustList<MethodInfo> methods_, String _id, ClassMethodId _uniqueId) {
        if (!ExplicitOperation.customCast(_id)) {
            return;
        }
        String di_ = StringExpUtil.getIdFromAllTypes(_id);
        RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(di_);
        if (r_ == null) {
            return;
        }
        StringList allClasses_ = new StringList(r_.getGenericString());
        allClasses_.addAllElts(r_.getAllGenericSuperTypes());
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        for (String s: allClasses_) {
            String formatted_ = AnaTemplates.quickFormat(r_,_id,s,_conf);
            String supId_ = StringExpUtil.getIdFromAllTypes(formatted_);
            StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
            superTypesBaseAncBis_.addEntry(supId_,supId_);
            CustList<MethodHeaderInfo> castsFrom_ = _conf.getAnalyzing().getTrues().getVal(supId_);
            RootBlock t_ = _conf.getAnalyzing().getAnaClassBody(supId_);
            fetchCastMethods(t_,_conf, _uniqueId, glClass_, methods_, _conf.getStandards().getAliasPrimBoolean(),formatted_, castsFrom_, superTypesBaseAncBis_);
        }
    }
    private static void fetchFalse(ContextEl _conf, CustList<MethodInfo> methods_, String _id, ClassMethodId _uniqueId) {
        if (!ExplicitOperation.customCast(_id)) {
            return;
        }
        String di_ = StringExpUtil.getIdFromAllTypes(_id);
        RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(di_);
        if (r_ == null) {
            return;
        }
        StringList allClasses_ = new StringList(r_.getGenericString());
        allClasses_.addAllElts(r_.getAllGenericSuperTypes());
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        for (String s: allClasses_) {
            String formatted_ = AnaTemplates.quickFormat(r_,_id,s,_conf);
            String supId_ = StringExpUtil.getIdFromAllTypes(formatted_);
            StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
            superTypesBaseAncBis_.addEntry(supId_,supId_);
            CustList<MethodHeaderInfo> castsFrom_ = _conf.getAnalyzing().getFalses().getVal(supId_);
            RootBlock t_ = _conf.getAnalyzing().getAnaClassBody(supId_);
            fetchCastMethods(t_,_conf, _uniqueId, glClass_, methods_, _conf.getStandards().getAliasPrimBoolean(),formatted_, castsFrom_, superTypesBaseAncBis_);
        }
    }
    protected static CustList<CustList<MethodInfo>>
    getDeclaredCustMethodByType(ContextEl _conf, MethodAccessKind _staticContext, boolean _accessFromSuper,
                                boolean _superClass, StringList _fromClasses, String _name, boolean _import, ClassMethodIdAncestor _uniqueId) {
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        CustList<CustList<MethodInfo>> methods_;
        methods_ = new CustList<CustList<MethodInfo>>();
        fetchParamClassAncMethods(_conf,_fromClasses,_staticContext,_accessFromSuper,_superClass,_uniqueId,methods_);
        if (_import) {
            for (CustList<ImportedMethod> l: ResolvingImportTypes.lookupImportStaticMethods(_conf,glClass_, _name)) {
                CustList<MethodInfo> m_ = new CustList<MethodInfo>();
                for (ImportedMethod e:l) {
                    ClassMethodId m = e.getId();
                    String clName_ = m.getClassName();
                    MethodId id_ = m.getConstraints();
                    if (isCandidateMethod(_uniqueId,0, clName_, id_)) {
                        continue;
                    }
                    ParametersGroup p_ = new ParametersGroup();
                    MethodInfo mloc_ = new MethodInfo();
                    mloc_.setClassName(clName_);
                    mloc_.setStatic(true);
                    mloc_.setConstraints(id_);
                    mloc_.setParameters(p_);
                    mloc_.format(true,_conf);
                    mloc_.setReturnType(e.getReturnType());
                    mloc_.setOriginalReturnType(e.getReturnType());
                    mloc_.setFileName(e.getFileName());
                    mloc_.setRootNumber(e.getRootNumber());
                    mloc_.setMemberNumber(e.getMemberNumber());
                    mloc_.setStandardMethod(e.getStandardMethod());
                    m_.add(mloc_);
                }
                methods_.add(m_);
            }
        }
        return methods_;
    }

    protected static boolean isCandidateMethod(ClassMethodIdAncestor _uniqueId, int _ancestor,String _clName, MethodId _id) {
        if (_uniqueId != null) {
            if (_uniqueId.getAncestor() != _ancestor) {
                return true;
            }
            String className_ = _uniqueId.getClassMethodId().getClassName();
            if (!StringList.quickEq(StringExpUtil.getIdFromAllTypes(className_), StringExpUtil.getIdFromAllTypes(_clName))) {
                return true;
            }
            MethodId constraints_ = _uniqueId.getClassMethodId().getConstraints();
            return !constraints_.eq(_id);
        }
        return false;
    }


    public static void fetchParamClassAncMethods(ContextEl _conf, StringList _fromClasses, CustList<CustList<MethodInfo>> _methods) {
        fetchParamClassAncMethods(_conf,_fromClasses,MethodAccessKind.INSTANCE,false,true,null,_methods);
    }
    private static void fetchParamClassAncMethods(ContextEl _conf, StringList _fromClasses, MethodAccessKind _staticContext, boolean _accessFromSuper,
                                                  boolean _superClass, ClassMethodIdAncestor _uniqueId, CustList<CustList<MethodInfo>> _methods) {
        CustList<CustList<TypeInfo>> typeInfosGroups_ = typeLists(_conf,_fromClasses,_staticContext);
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        for (CustList<TypeInfo> g: typeInfosGroups_) {
            StringList baseTypes_ = new StringList();
            StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
            feedTypes(g, baseTypes_, superTypesBaseAncBis_);
            CustList<MethodInfo> methods_ = new CustList<MethodInfo>();
            for (TypeInfo t: g) {
                String f_ = t.getType();
                String cl_ = StringExpUtil.getIdFromAllTypes(f_);
                AnaGeneType root_ = _conf.getAnalyzing().getAnaGeneType(_conf, cl_);
                fetchParamClassMethods(_conf,_accessFromSuper,_superClass,t.getAncestor(),t.getScope(),_uniqueId,glClass_,methods_,f_, baseTypes_,superTypesBaseAncBis_, cl_, root_);
            }
            _methods.add(methods_);
        }
    }

    public static CustList<CustList<TypeInfo>> typeLists(ContextEl _conf, StringList _fromClasses, MethodAccessKind _staticContext) {
        CustList<TypeInfo> typeInfos_ = new CustList<TypeInfo>();
        IntTreeMap<CustList<TypeInfo>> typeInfosMap_ = new IntTreeMap<CustList<TypeInfo>>();
        for (String s: _fromClasses) {
            String baseCurName_ = StringExpUtil.getIdFromAllTypes(s);
            AnaGeneType root_ = _conf.getAnalyzing().getAnaGeneType(_conf,baseCurName_);
            if (root_ instanceof RootBlock) {
                String gene_ = root_.getGenericString();
                addToList(_conf,typeInfos_,_staticContext,root_,s,root_,gene_,0,true);
                for (AnaFormattedRootBlock m: ((RootBlock)root_).getAllGenericSuperTypesInfo()) {
                    RootBlock rootBlock_ = m.getRootBlock();
                    String formatted_ = m.getFormatted();
                    addToList(_conf,typeInfos_,_staticContext,root_,s, rootBlock_, formatted_,0,false);
                }
            }
            if (root_ instanceof StandardType) {
                String gene_ = root_.getGenericString();
                addToList(_conf,typeInfos_,_staticContext,root_,s,root_,gene_,0,true);
                for (String m : root_.getAllGenericSuperTypes()) {
                    StandardType sup_ = _conf.getStandards().getStandards().getVal(m);
                    addToList(_conf,typeInfos_,_staticContext,root_,s,sup_,m,0,false);
                }
            }

        }
        typeInfosMap_.put(0,typeInfos_);
        for (TypeInfo t: typeInfos_) {
            String f_ = t.getType();
            String cl_ = StringExpUtil.getIdFromAllTypes(f_);
            AnaGeneType root_ = _conf.getAnalyzing().getAnaGeneType(_conf,cl_);
            if (!(root_ instanceof RootBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) root_;
            int anc_ = 1;
            CustList<RootBlock> pars_ = r_.getAllParentTypes();
            int len_ = pars_.size();
            for (int i = 0; i < len_; i++) {
                typeInfosMap_.put(anc_,new CustList<TypeInfo>());
                anc_++;
            }
        }
        for (TypeInfo t: typeInfos_) {
            String f_ = t.getType();
            String cl_ = StringExpUtil.getIdFromAllTypes(f_);
            AnaGeneType root_ = _conf.getAnalyzing().getAnaGeneType(_conf,cl_);
            if (!(root_ instanceof RootBlock)) {
                continue;
            }
            RootBlock r_ = (RootBlock) root_;
            boolean add_ = !root_.isStaticType();
            int anc_ = 1;
            MethodAccessKind scope_ = _staticContext;
            for (RootBlock p: r_.getAllParentTypes()) {
                CustList<TypeInfo> typeInfosInt_ = typeInfosMap_.getVal(anc_);
                if (!add_) {
                    scope_ = MethodAccessKind.STATIC;
                }
                String gene_ = p.getGenericString();
                addToList(_conf,typeInfosInt_,scope_,root_,f_,root_,gene_,anc_,true);
                for (AnaFormattedRootBlock m: r_.getAllGenericSuperTypesInfo()) {
                    RootBlock rootBlock_ = m.getRootBlock();
                    String formatted_ = m.getFormatted();
                    addToList(_conf,typeInfosInt_,scope_,root_,f_,rootBlock_,formatted_,anc_,false);
                }
                if (p.isStaticType()) {
                    add_ = false;
                }
                anc_++;
            }
        }
        return typeInfosMap_.values();
    }
    private static void addToList(ContextEl _conf, CustList<TypeInfo> _list, MethodAccessKind _k, AnaGeneType _firstType, String _first, AnaGeneType _secondType,String _second, int _anc, boolean _base) {
        TypeInfo t_ = newTypeInfo(_conf, _k, _firstType,_first, _second, _anc);
        String f_ = t_.getType();
        for (TypeInfo t: _list) {
            if (StringList.quickEq(t.getType(), f_)) {
                return;
            }
        }
        t_.setBase(_base);
        t_.setSuperTypes(_secondType.getAllSuperTypes());
        _list.add(t_);
    }

    private static TypeInfo newTypeInfo(ContextEl _conf, MethodAccessKind _k, AnaGeneType _firstType, String _first, String _second, int _anc) {
        MethodAccessKind k_ = _k;
        String type_ = _second;
        if (AnaTemplates.correctNbParameters(_firstType,_first,_conf)) {
            type_ = AnaTemplates.format(_firstType,_first, _second, _conf);
        } else {
            k_ = MethodAccessKind.STATIC;
        }
        TypeInfo t_ = new TypeInfo();
        t_.setType(type_);
        t_.setAncestor(_anc);
        t_.setScope(k_);
        return t_;
    }

    private static void fetchCastMethods(RootBlock _type,ContextEl _conf, ClassMethodId _uniqueId, String _glClass, CustList<MethodInfo> _methods, String _returnType,String _cl, CustList<MethodHeaderInfo> _casts, StringMap<String> _superTypesBaseMap) {
        ClassMethodIdAncestor uniq_ = null;
        if (_uniqueId != null) {
            uniq_ = new ClassMethodIdAncestor(new ClassMethodId(StringExpUtil.getIdFromAllTypes(_uniqueId.getClassName()),_uniqueId.getConstraints()),0);
        }
        for (MethodHeaderInfo e: nullToEmpty(_casts)) {
            MethodInfo stMeth_ = fetchedParamCastMethod(_type,e,_returnType,_cl, _conf,uniq_,_glClass, _superTypesBaseMap);
            if (stMeth_ == null) {
                continue;
            }
            _methods.add(stMeth_);
        }
    }

    private static void fetchImproveOperators(ContextEl _conf, RootBlock _root, CustList<MethodInfo> _methods, String _cl, CustList<MethodHeaderInfo> _casts) {
        for (MethodHeaderInfo e: nullToEmpty(_casts)) {
            MethodInfo stMeth_ = fetchedParamImproveOperator(_root,e, _cl, _conf);
            _methods.add(stMeth_);
        }
    }
    private static CustList<MethodHeaderInfo> nullToEmpty(CustList<MethodHeaderInfo> _list) {
        if (_list == null) {
            return new CustList<MethodHeaderInfo>();
        }
        return _list;
    }

    private static void fetchParamClassMethods(ContextEl _conf, boolean _accessFromSuper, boolean _superClass, int _anc, MethodAccessKind _kind,
                                               ClassMethodIdAncestor _uniqueId, String _glClass, CustList<MethodInfo> _methods,
                                               String _cl, StringList _superTypesBase, StringMap<String> _superTypesBaseMap, String _fullName, AnaGeneType _g) {
        String genericString_ = _g.getGenericString();
        for (GeneCustStaticMethod e: ClassesUtil.getMethodBlocks(_g)) {
            MethodId id_ = e.getId();
            MethodAccessKind k_ = id_.getKind();
            if (_kind == MethodAccessKind.STATIC) {
                if (k_ != MethodAccessKind.STATIC) {
                    continue;
                }
            }
            if (_kind == MethodAccessKind.STATIC_CALL) {
                if (k_ != MethodAccessKind.STATIC) {
                    if (k_ != MethodAccessKind.STATIC_CALL) {
                        continue;
                    }
                }
            }
            if (filterMember(_accessFromSuper,_superClass,_superTypesBase, _fullName)) {
                continue;
            }
            MethodInfo stMeth_ = fetchedParamMethod(_g,e,genericString_,k_ == MethodAccessKind.STATIC,_conf, _uniqueId,_glClass,_anc, _cl,_superTypesBaseMap);
            if (stMeth_ == null) {
                continue;
            }
            _methods.add(stMeth_);
        }
        for (MethodInfo e: getPredefineStaticEnumMethods(_conf,genericString_,_anc)) {
            MethodId id_ = e.getConstraints();
            if (isCandidateMethod(_uniqueId,_anc, _fullName, id_)) {
                continue;
            }
            _methods.add(e);
        }
    }
    private static boolean filterMember(boolean _accessFromSuper, boolean _superClass,StringList _superTypesBase, String _fullName) {
        if (_accessFromSuper) {
            if (StringList.contains(_superTypesBase, _fullName)) {
                return true;
            }
        }
        if (!_superClass) {
            return !StringList.contains(_superTypesBase, _fullName);
        }
        return false;
    }

    private static MethodInfo fetchedParamMethod(AnaGeneType _r, GeneCustStaticMethod _m, String _s, boolean _keepParams,
                                                 ContextEl _conf, ClassMethodIdAncestor _uniqueId,
                                                 String _glClass, int _anc, String _f, StringMap<String> _superTypesBaseMap) {
        String base_ = StringExpUtil.getIdFromAllTypes(_s);
        MethodId id_ = _m.getId();
        if (isCandidateMethod(_uniqueId,_anc, base_, id_)) {
            return null;
        }
        String formattedClass_;
        if (!id_.canAccessParamTypes() || StringList.quickEq(base_, _conf.getStandards().getAliasFct())) {
            formattedClass_ = _s;
        } else {
            formattedClass_ = _f;
        }
        String outer_ = "";
        if (_r instanceof RootBlock) {
            outer_ = ((RootBlock)_r).getOuterFullName();
        }
        if (_m instanceof OverridableBlock) {
            OverridableBlock c = (OverridableBlock) _m;
            Accessed a_ = new Accessed(c.getAccess(), _r.getPackageName(), _r.getFullName(), outer_);
            if (cannotAccess(_conf,base_,a_,_glClass,_superTypesBaseMap)) {
                return null;
            }
        }
        return buildMethodInfo(_r,_m, _keepParams, _conf, _anc, formattedClass_);
    }

    private static MethodInfo fetchedParamCastMethod(RootBlock _type,MethodHeaderInfo _m, String _returnType, String _s,
                                                     ContextEl _conf, ClassMethodIdAncestor _uniqueId,
                                                     String _glClass, StringMap<String> _superTypesBaseMap) {
        String base_ = StringExpUtil.getIdFromAllTypes(_s);
        MethodId id_ = _m.getId();
        if (isCandidateMethod(_uniqueId, 0, base_, id_)) {
            return null;
        }
        Accessed a_ = new Accessed(_m.getAccess(), _type.getPackageName(), _type.getFullName(), _type.getOuterFullName());
        if (cannotAccess(_conf,base_, a_,_glClass,_superTypesBaseMap)) {
            return null;
        }
        return buildCastMethodInfo(_type,_m,_uniqueId, _conf, _returnType,_s);
    }

    private static MethodInfo fetchedParamImproveOperator(RootBlock _root,MethodHeaderInfo _m, String _s,
                                                          ContextEl _conf) {
        return buildImproveOperatorInfo(_root,_m, _conf, _s);
    }

    private static boolean cannotAccess(ContextEl _conf, String _base,AccessibleBlock _acc,
                                        String _glClass,  StringMap<String> _superTypesBaseMap) {
        String subType_ = _superTypesBaseMap.getVal(_base);
        if (!ContextUtil.canAccess(subType_,_acc, _conf)) {
            return true;
        }
        return !ContextUtil.canAccess(_glClass,_acc, _conf);
    }

    private static MethodInfo buildMethodInfo(AnaGeneType _r,GeneCustStaticMethod _m, boolean _keepParams, ContextEl _conf, int _anc, String _formattedClass) {
        String importedReturnType_ = _m.getImportedReturnType();
        String ret_ = importedReturnType_;
        ret_ = AnaTemplates.wildCardFormatReturn(_formattedClass, ret_, _conf);
        ParametersGroup p_ = new ParametersGroup();
        MethodId id_ = _m.getId();
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setOriginalReturnType(importedReturnType_);
        if (_m instanceof NamedFunctionBlock) {
            mloc_.setFileName(((Block)_m).getFile().getFileName());
            mloc_.setMemberNumber(((NamedFunctionBlock)_m).getNameNumber());
        }
        if (_m instanceof StandardMethod) {
            mloc_.setStandardMethod((StandardMethod)_m);
        }
        if (_r instanceof RootBlock) {
            mloc_.setRootNumber(((RootBlock)_r).getNumberAll());
        }
        mloc_.setClassName(_formattedClass);
        mloc_.setStaticMethod(id_.getKind());
        if (_m instanceof OverridableBlock) {
            mloc_.setAbstractMethod(((OverridableBlock)_m).isAbstractMethod());
            mloc_.setFinalMethod(((OverridableBlock)_m).isFinalMethod());
        }
        mloc_.setConstraints(id_);
        mloc_.setParameters(p_);
        mloc_.setReturnType(ret_);
        mloc_.setAncestor(_anc);
        mloc_.format(_keepParams,_conf);
        return mloc_;
    }

    private static MethodInfo buildCastMethodInfo(RootBlock _root,MethodHeaderInfo _m, ClassMethodIdAncestor _uniqueId, ContextEl _conf, String _returnType, String _formattedClass) {
        String importedReturnType_ = _m.getImportedReturnType();
        String ret_ = importedReturnType_;
        ret_ = AnaTemplates.wildCardFormatReturn(_formattedClass, ret_, _conf);
        ParametersGroup p_ = new ParametersGroup();
        MethodId id_ = _m.getId();
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setOriginalReturnType(importedReturnType_);
        mloc_.setFileName(_root.getFile().getFileName());
        mloc_.setMemberNumber(_m.getNameNumber());
        mloc_.setRootNumber(_root.getNumberAll());
        mloc_.setClassName(_formattedClass);
        mloc_.setStaticMethod(id_.getKind());
        mloc_.setConstraints(id_);
        mloc_.setParameters(p_);
        mloc_.setReturnType(ret_);
        mloc_.setAncestor(0);
        mloc_.format(false,_conf);
        if (_uniqueId == null) {
            Mapping map_ = new Mapping();
            map_.setArg(ret_);
            map_.setParam(_returnType);
            map_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
            if (!AnaTemplates.isCorrectOrNumbers(map_,_conf)) {
                return null;
            }
        }
        return mloc_;
    }

    private static MethodInfo buildImproveOperatorInfo(RootBlock _root,MethodHeaderInfo _m, ContextEl _conf, String _formattedClass) {
        String ret_ = _m.getImportedReturnType();
        ret_ = AnaTemplates.wildCardFormatReturn(_formattedClass, ret_, _conf);
        ParametersGroup p_ = new ParametersGroup();
        MethodId id_ = _m.getId();
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setClassName(_formattedClass);
        mloc_.setStaticMethod(id_.getKind());
        mloc_.setConstraints(id_);
        mloc_.setParameters(p_);
        mloc_.setReturnType(ret_);
        mloc_.setAncestor(0);
        mloc_.setRootNumber(_root.getNumberAll());
        mloc_.setMemberNumber(_m.getNameNumber());
        mloc_.format(false,_conf);
        return mloc_;
    }

    private static CustList<MethodInfo> getPredefineStaticEnumMethods(ContextEl _conf, String _className, int _ancestor) {
        CustList<MethodInfo> methods_;
        methods_ = new CustList<MethodInfo>();
        String idClass_ = StringExpUtil.getIdFromAllTypes(_className);
        RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(idClass_);
        if (!(r_ instanceof EnumBlock)) {
            return methods_;
        }
        String wildCardForm_ = r_.getWildCardString();
        String string_ = _conf.getStandards().getAliasString();
        String returnType_ = wildCardForm_;
        ParametersGroup p_ = new ParametersGroup();
        String valueOf_ = _conf.getStandards().getAliasEnumPredValueOf();
        MethodId realId_ = new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_));
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setClassName(idClass_);
        mloc_.setFileName(r_.getFile().getFileName());
        mloc_.setStatic(true);
        mloc_.setConstraints(realId_);
        mloc_.setParameters(p_);
        mloc_.format(true,_conf);
        mloc_.setReturnType(returnType_);
        mloc_.setOriginalReturnType(returnType_);
        mloc_.setAncestor(_ancestor);
        mloc_.setRootNumber(r_.getNumberAll());
        methods_.add(mloc_);
        p_ = new ParametersGroup();
        String values_ = _conf.getStandards().getAliasEnumValues();
        realId_ = new MethodId(MethodAccessKind.STATIC, values_, new StringList());
        mloc_ = new MethodInfo();
        mloc_.setClassName(idClass_);
        mloc_.setFileName(r_.getFile().getFileName());
        mloc_.setStatic(true);
        mloc_.setConstraints(realId_);
        mloc_.setParameters(p_);
        mloc_.format(true,_conf);
        returnType_ = StringExpUtil.getPrettyArrayType(returnType_);
        mloc_.setReturnType(returnType_);
        mloc_.setOriginalReturnType(returnType_);
        mloc_.setAncestor(_ancestor);
        mloc_.setRootNumber(r_.getNumberAll());
        methods_.add(mloc_);
        return methods_;
    }

    private static ClassMethodIdReturn getCustResultLambda(ContextEl _conf, int _varargOnly,
                                                           CustList<CustList<MethodInfo>> _methods,
                                                           String _name, ClassArgumentMatching... _argsClass) {
        CustList<CustList<MethodInfo>> signatures_ = new CustList<CustList<MethodInfo>>();
        for (CustList<MethodInfo> l: _methods) {
            CustList<MethodInfo> m_ = new CustList<MethodInfo>();
            for (MethodInfo e: l) {
                MethodId id_ = e.getConstraints();
                boolean varArg_ = id_.isVararg();
                if (_varargOnly > -1) {
                    if (!varArg_) {
                        continue;
                    }
                }
                if (!StringList.quickEq(id_.getName(), _name)) {
                    continue;
                }
                if (!isPossibleMethodLambda(_conf, e, _argsClass)) {
                    continue;
                }
                m_.add(e);
            }
            signatures_.add(m_);
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        Parametrable found_ = sortFct(signatures_, gr_);
        if (!(found_ instanceof MethodInfo)) {
            return new ClassMethodIdReturn(false);
        }
        MethodInfo m_ = (MethodInfo) found_;
        MethodId constraints_ = m_.getConstraints();
        String baseClassName_ = m_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = m_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(m_.getReturnType());
        res_.setOriginalReturnType(m_.getOriginalReturnType());
        res_.setFileName(m_.getFileName());
        res_.setMemberNumber(m_.getMemberNumber());
        res_.setRootNumber(m_.getRootNumber());
        res_.setStandardMethod(m_.getStandardMethod());
        res_.setAncestor(m_.getAncestor());
        res_.setAbstractMethod(m_.isAbstractMethod());
        res_.setStaticMethod(m_.isStatic());
        return res_;
    }
    private static ClassMethodIdReturn getCustResult(ContextEl _conf, boolean _unique,boolean _excludeVararg,int _varargOnly,
                                                     CustList<CustList<MethodInfo>> _methods,
            String _name, String _param,ClassArgumentMatching... _argsClass) {
        CustList<CustList<MethodInfo>> signatures_ = new CustList<CustList<MethodInfo>>();
        for (CustList<MethodInfo> l: _methods) {
            CustList<MethodInfo> m_ = new CustList<MethodInfo>();
            for (MethodInfo e: l) {
                MethodId id_ = e.getConstraints();
                boolean varArg_ = id_.isVararg();
                if (_varargOnly > -1) {
                    if (!varArg_) {
                        continue;
                    }
                }
                if (_excludeVararg) {
                    if (varArg_) {
                        continue;
                    }
                }
                if (!StringList.quickEq(id_.getName(), _name)) {
                    continue;
                }
                if (!isPossibleMethod(_conf, _unique, _varargOnly, e, _param,_argsClass)) {
                    continue;
                }
                m_.add(e);
            }
            signatures_.add(m_);
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        Parametrable found_ = sortFct(signatures_, gr_);
        if (!(found_ instanceof MethodInfo)) {
            return new ClassMethodIdReturn(false);
        }
        MethodInfo m_ = (MethodInfo) found_;
        int len_ = m_.getImplicits().size();
        for (int i = 0; i < len_; i++) {
            CustList<ImplicitInfos> implicitInfos_ = m_.getImplicits().get(i);
            for (ImplicitInfos j: implicitInfos_) {
                _argsClass[i].getImplicits().add(j.getIdMethod());
                _argsClass[i].setRootNumber(j.getRootNumber());
                _argsClass[i].setMemberNumber(j.getMemberNumber());
            }
        }
        MethodId constraints_ = m_.getConstraints();
        String baseClassName_ = m_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = m_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        if (m_.isVarArgWrap()) {
            res_.setVarArgToCall(true);
        }
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setStandardMethod(m_.getStandardMethod());
        res_.setRootNumber(m_.getRootNumber());
        res_.setMemberNumber(m_.getMemberNumber());
        res_.setReturnType(m_.getReturnType());
        res_.setAncestor(m_.getAncestor());
        res_.setAbstractMethod(m_.isAbstractMethod());
        res_.setStaticMethod(m_.isStatic());
        return res_;
    }
    private static ClassMethodIdReturn getCustIncrDecrResult(ContextEl _conf,
                                                             CustList<CustList<MethodInfo>> _methods,
                                                             String _name, ClassArgumentMatching _argsClass) {
        CustList<CustList<MethodInfo>> signatures_ = new CustList<CustList<MethodInfo>>();
        for (CustList<MethodInfo> l: _methods) {
            CustList<MethodInfo> m_ = new CustList<MethodInfo>();
            for (MethodInfo e: l) {
                MethodId id_ = e.getConstraints();
                boolean varArg_ = id_.isVararg();
                if (varArg_) {
                    continue;
                }
                if (!StringList.quickEq(id_.getName(), _name)) {
                    continue;
                }
                if (e.getGeneFormatted().getParametersTypesLength() != 1) {
                    continue;
                }
                if (!isPossibleIncrDecrMethod(_conf, e, _argsClass)) {
                    continue;
                }
                m_.add(e);
            }
            signatures_.add(m_);
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        Parametrable found_ = sortFct(signatures_, gr_);
        if (!(found_ instanceof MethodInfo)) {
            return new ClassMethodIdReturn(false);
        }
        MethodInfo m_ = (MethodInfo) found_;
        MethodId constraints_ = m_.getConstraints();
        String baseClassName_ = m_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = m_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(m_.getReturnType());
        res_.setAncestor(m_.getAncestor());
        res_.setStandardMethod(m_.getStandardMethod());
        res_.setRootNumber(m_.getRootNumber());
        res_.setMemberNumber(m_.getMemberNumber());
        res_.setAbstractMethod(m_.isAbstractMethod());
        res_.setStaticMethod(m_.isStatic());
        return res_;
    }
    private static boolean isPossibleMethodLambda(ContextEl _context, Parametrable _id,
                                                  ClassArgumentMatching... _argsClass) {
        int startOpt_ = _argsClass.length;
        boolean checkOnlyDem_ = true;
        int all_ = _id.getGeneFormatted().getParametersTypesLength();
        int last_ = all_-1;
        boolean vararg_ = _id.isVararg();
        if (!vararg_) {
            if (all_ != _argsClass.length) {
                return false;
            }
        } else {
            if (all_ > _argsClass.length + 1) {
                return false;
            }
            checkOnlyDem_ = false;
            startOpt_ = all_ - 1;
        }
        StringMap<StringList> mapCtr_ = _context.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        for (int i = CustList.FIRST_INDEX; i < startOpt_; i++) {
            String wc_ = _id.getGeneFormatted().getParametersType(i);
            wc_ = wrap(i,all_,vararg_,wc_);
            Mapping map_ = new Mapping();
            ClassArgumentMatching arg_ = _argsClass[i];
            map_.setArg(arg_);
            map_.getMapping().putAllMap(mapCtr_);
            map_.setParam(wc_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                return false;
            }
        }
        if (checkOnlyDem_) {
            _id.setInvocation(InvocationMethod.STRICT);
            return true;
        }
        if (all_ == _argsClass.length) {
            Mapping map_ = new Mapping();
            ClassArgumentMatching arg_ = _argsClass[last_];
            map_.setArg(arg_);
            map_.getMapping().putAllMap(mapCtr_);
            String wc_ = _id.getGeneFormatted().getParametersType(last_);
            if (wc_.isEmpty()) {
                return false;
            }
            String arr_ = StringExpUtil.getPrettyArrayType(wc_);
            map_.setParam(arr_);
            if (AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                _id.setInvocation(InvocationMethod.STRICT);
                return true;
            }
            map_.setParam(wc_);
            if (AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                _id.setInvocation(InvocationMethod.VARARG);
                return true;
            }
            return false;
        }
        int nbDem_ = _argsClass.length;
        Mapping map_ = new Mapping();
        map_.getMapping().putAllMap(mapCtr_);
        String wc_ = _id.getGeneFormatted().getParametersType(last_);
        map_.setParam(wc_);
        for (int i = startOpt_; i < nbDem_; i++) {
            ClassArgumentMatching a_ = _argsClass[i];
            map_.setArg(a_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                return false;
            }
        }
        _id.setInvocation(InvocationMethod.VARARG);
        return true;
    }
    private static boolean isPossibleMethod(ContextEl _context, boolean _unique, int _varargOnly, Parametrable _id,
                                            String _param,ClassArgumentMatching... _argsClass) {
        int startOpt_ = _argsClass.length;
        boolean checkOnlyDem_ = true;
        int all_ = _id.getGeneFormatted().getParametersTypesLength();
        int last_ = all_-1;
        boolean vararg_ = _id.isVararg();
        if (!vararg_) {
            if (all_ != _argsClass.length) {
                return false;
            }
        } else {
            if (all_ > _argsClass.length + 1) {
                return false;
            }
            if (_varargOnly != 0) {
                checkOnlyDem_ = false;
                startOpt_ = all_ - 1;
            }
            if (_varargOnly > 0) {
                if (startOpt_ != _varargOnly - 1) {
                    return false;
                }
            } else if (_varargOnly == 0) {
                if (all_ -1 != _argsClass.length) {
                    return false;
                }
            }
        }
        StringMap<StringList> mapCtr_ = _context.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        boolean allNotBoxUnbox_ = true;
        boolean implicit_ = false;
        for (int i = CustList.FIRST_INDEX; i < startOpt_; i++) {
            String wc_ = _id.getGeneFormatted().getParametersType(i);
			wc_ = wrap(i,all_,vararg_,wc_);
            CustList<ImplicitInfos> l_ = new CustList<ImplicitInfos>();
            _id.getImplicits().add(l_);
            if (_argsClass[i].isVariable()) {
                if (PrimitiveTypeUtil.isPrimitive(wc_,_context)) {
                    return false;
                }
                continue;
            }
            Mapping map_ = new Mapping();
            ClassArgumentMatching arg_ = _argsClass[i];
            map_.setArg(arg_);
            map_.getMapping().putAllMap(mapCtr_);
            if (wc_.isEmpty()) {
                return false;
            }
            map_.setParam(wc_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_context, wc_, arg_);
                if (res_.isFoundMethod()) {
                    implicit_ = true;
                    ImplicitInfos imp_ = new ImplicitInfos();
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    imp_.setIdMethod(cl_);
                    imp_.setRootNumber(res_.getRootNumber());
                    imp_.setMemberNumber(res_.getMemberNumber());
                    l_.add(imp_);
                    continue;
                }
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
        if (!match(_id,_param)) {
            return false;
        }
        if (checkOnlyDem_) {
            if (vararg_) {
                _id.setVarArgWrap(true);
            }
            setWideInvoke(_id, vararg_, allNotBoxUnbox_, implicit_);
            return true;
        }
        if (all_ == _argsClass.length) {
            Mapping map_ = new Mapping();
            ClassArgumentMatching arg_ = _argsClass[last_];
            map_.setArg(arg_);
            map_.getMapping().putAllMap(mapCtr_);
            String wc_ = _id.getGeneFormatted().getParametersType(last_);
            CustList<ImplicitInfos> l_ = new CustList<ImplicitInfos>();
            _id.getImplicits().add(l_);
            if (wc_.isEmpty()) {
                if (arg_.isVariable()) {
                    setWideInvoke(_id, true, allNotBoxUnbox_, implicit_);
                    return true;
                }
                return false;
            }
            String arr_ = StringExpUtil.getPrettyArrayType(wc_);
            map_.setParam(arr_);
            if (AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                setWideInvoke(_id, false, allNotBoxUnbox_, implicit_);
                if (_unique) {
                    map_.setParam(wc_);
                    if (AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                        setVarargOrImplicit(_id, implicit_);
                        _id.setVarArgWrap(true);
                    }
                }
                return true;
            }
            map_.setParam(wc_);
            if (AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                setVarargOrImplicit(_id, implicit_);
                _id.setVarArgWrap(true);
                return true;
            }
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_context, wc_, arg_);
            if (res_.isFoundMethod()) {
                _id.setInvocation(InvocationMethod.ALL);
                _id.setVarArgWrap(true);
                ImplicitInfos imp_ = new ImplicitInfos();
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                imp_.setIdMethod(cl_);
                imp_.setRootNumber(res_.getRootNumber());
                imp_.setMemberNumber(res_.getMemberNumber());
                l_.add(imp_);
                return true;
            }
            return false;
        }
        int nbDem_ = _argsClass.length;
        Mapping map_ = new Mapping();
        map_.getMapping().putAllMap(mapCtr_);
        String wc_ = _id.getGeneFormatted().getParametersType(last_);
        if (wc_.isEmpty()) {
            return false;
        }
        map_.setParam(wc_);
        for (int i = startOpt_; i < nbDem_; i++) {
            CustList<ImplicitInfos> l_ = new CustList<ImplicitInfos>();
            _id.getImplicits().add(l_);
            ClassArgumentMatching a_ = _argsClass[i];
            map_.setArg(a_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_context, wc_, a_);
                if (res_.isFoundMethod()) {
                    implicit_ = true;
                    ImplicitInfos imp_ = new ImplicitInfos();
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    imp_.setIdMethod(cl_);
                    imp_.setRootNumber(res_.getRootNumber());
                    imp_.setMemberNumber(res_.getMemberNumber());
                    l_.add(imp_);
                    continue;
                }
                return false;
            }
        }
        setVarargOrImplicit(_id, implicit_);
        _id.setVarArgWrap(true);
        return true;
    }
    private static boolean isPossibleIncrDecrMethod(ContextEl _context, Parametrable _id,
                                                    ClassArgumentMatching _argsClass) {
        StringMap<StringList> mapCtr_ = _context.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        String wc_ = _id.getGeneFormatted().getParametersType(0);
        Mapping map_ = new Mapping();
        map_.setArg(_argsClass);
        map_.getMapping().putAllMap(mapCtr_);
        map_.setParam(wc_);
        if (!AnaTemplates.isCorrectOrNumbers(map_, _context)) {
            return false;
        }
        map_.setArg(_id.getReturnType());
        if (!AnaTemplates.isCorrectOrNumbers(map_, _context)) {
            return false;
        }
        _id.setInvocation(InvocationMethod.STRICT);
        return true;

    }
    private static boolean match(Parametrable _id,
                                 String _param) {
        int all_ = _id.getGeneFormatted().getParametersTypesLength();
        int last_ = all_-1;
        if (last_ >= 0&&_id.isVararg()&&!_param.isEmpty()) {
            String wc_ = _id.getGeneFormatted().getParametersType(last_);
            return StringList.quickEq(wc_, _param);
        }
        return true;
    }
    private static void setWideInvoke(Parametrable _id, boolean vararg_, boolean allNotBoxUnbox_, boolean implicit_) {
        if (vararg_||implicit_) {
            setVarargOrImplicit(_id, implicit_);
        } else {
            if (allNotBoxUnbox_) {
                _id.setInvocation(InvocationMethod.STRICT);
            } else {
                _id.setInvocation(InvocationMethod.BOX_UNBOX);
            }
        }
    }

    private static void setVarargOrImplicit(Parametrable _id, boolean _implicit) {
        if (!_implicit) {
            _id.setInvocation(InvocationMethod.VARARG);
        } else {
            _id.setInvocation(InvocationMethod.ALL);
        }
    }

    private static ClassMethodIdReturn getCustCastResult(ContextEl _conf,
                                                         CustList<MethodInfo> _methods,
                                                         ClassArgumentMatching _argsClass) {
        CustList<MethodInfo> signatures_ = new CustList<MethodInfo>();
        for (MethodInfo e: _methods) {
            if (!isPossibleMethod(_conf, e, _argsClass)) {
                continue;
            }
            signatures_.add(e);
        }
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        CustList<CustList<MethodInfo>> c_ = new CustList<CustList<MethodInfo>>();
        c_.add(signatures_);
        Parametrable found_ = sortFct(c_, gr_);
        if (!(found_ instanceof MethodInfo)) {
            return new ClassMethodIdReturn(false);
        }
        MethodInfo m_ = (MethodInfo) found_;
        MethodId constraints_ = m_.getConstraints();
        String baseClassName_ = m_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = m_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(m_.getReturnType());
        res_.setOriginalReturnType(m_.getOriginalReturnType());
        res_.setFileName(m_.getFileName());
        res_.setMemberNumber(m_.getMemberNumber());
        res_.setRootNumber(m_.getRootNumber());
        res_.setStandardMethod(m_.getStandardMethod());
        res_.setAncestor(m_.getAncestor());
        res_.setAbstractMethod(m_.isAbstractMethod());
        res_.setStaticMethod(m_.isStatic());
        return res_;
    }

    private static boolean isPossibleMethod(ContextEl _context, MethodInfo _id,
                                            ClassArgumentMatching _argsClass) {
        StringList params_ = _id.getFoundFormatted().shiftFirst();
        int nbDem_ = params_.size();
        StringMap<StringList> mapCtr_ = _context.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        boolean allNotBoxUnbox_ = true;
        for (int i = CustList.FIRST_INDEX; i < nbDem_; i++) {
            String wc_ = wrap(i,params_.size(), false,params_.get(i));
            Mapping map_ = new Mapping();
            map_.setArg(_argsClass);
            map_.getMapping().putAllMap(mapCtr_);
            map_.setParam(wc_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _context)) {
                return false;
            }
            if (PrimitiveTypeUtil.isPrimitive(wc_, _context)) {
                if (!_argsClass.isPrimitive(_context)) {
                    allNotBoxUnbox_ = false;
                }
            } else {
                if (_argsClass.isPrimitive(_context)) {
                    allNotBoxUnbox_ = false;
                }
            }
        }
        if (allNotBoxUnbox_) {
            _id.setInvocation(InvocationMethod.STRICT);
        } else {
            _id.setInvocation(InvocationMethod.BOX_UNBOX);
        }
        return true;
    }

    private static Parametrable sortFct(CustList<CustList<MethodInfo>> _fct, ArgumentsGroup _context) {
        for (CustList<MethodInfo> l: _fct) {
            MethodInfo meth_ = getFoundMethod(l, _context);
            if (meth_ != null) {
                return meth_;
            }
            CustList<Parametrable> instances_ = new CustList<Parametrable>();
            CustList<Parametrable> staticsCall_ = new CustList<Parametrable>();
            CustList<Parametrable> statics_ = new CustList<Parametrable>();
            for (MethodInfo m : l) {
                MethodAccessKind kind_ = m.getConstraints().getKind();
                if (kind_ == MethodAccessKind.STATIC_CALL) {
                    staticsCall_.add(m);
                    continue;
                }
                if (kind_ == MethodAccessKind.STATIC) {
                    statics_.add(m);
                    continue;
                }
                instances_.add(m);
            }
            MethodInfo best_ = (MethodInfo) getBest(instances_, _context);
            if (best_ != null) {
                return best_;
            }
            best_ = (MethodInfo) getBest(staticsCall_, _context);
            if (best_ != null) {
                return best_;
            }
            best_ = (MethodInfo) getBest(statics_, _context);
            if (best_ != null) {
                return best_;
            }
        }
        return null;
    }
    private static Parametrable getBest(CustList<Parametrable> _fct, ArgumentsGroup _context) {
        int len_;
        if (!_fct.isEmpty()) {
            len_ = _fct.size();
            for (int i = CustList.SECOND_INDEX; i < len_; i++) {
                Parametrable pFirst_ = _fct.first();
                Parametrable pCurrent_ = _fct.get(i);
                int res_ = compare(_context, pFirst_, pCurrent_);
                if (res_ == CustList.SWAP_SORT) {
                    _fct.swapIndexes(CustList.FIRST_INDEX, i);
                }
            }
            if (!_fct.first().getParameters().isError()) {
                return _fct.first();
            }
        }
        return null;
    }
    public void cancelArgument() {
        if (resultClass.getUnwrapObject().isEmpty()) {
            return;
        }
        if (Argument.isNullValue(argument)) {
            argument = null;
        }
    }
    public void cancelArgumentString() {
        if (Argument.isNotDisplayableValue(argument)) {
            argument = null;
        }
    }
    private static ConstructorInfo sortCtors(CustList<ConstructorInfo> _fct, ArgumentsGroup _context) {
        ConstructorInfo ctor_ = getFoundConstructor(_fct, _context);
        if (ctor_ != null) {
            return ctor_;
        }
        CustList<Parametrable> instances_ = new CustList<Parametrable>();
        for (Parametrable m : _fct) {
            instances_.add(m);
        }
        return (ConstructorInfo) getBest(instances_,_context);
    }

    private static MethodInfo getFoundMethod(CustList<MethodInfo> _fct, ArgumentsGroup _context) {
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
            for (Parametrable m: _fct) {
                if (m.getInvocation() == InvocationMethod.VARARG) {
                    fct_.add(m);
                }
            }
            if (fct_.isEmpty()) {
                for (Parametrable m: _fct) {
                    fct_.add(m);
                }
            }
            allMax_ = getAllMaximalSpecificVariableArity(fct_, _context);
        }
        if (allMax_.isEmpty()) {
            return null;
        }
        MethodInfo first_ = (MethodInfo) allMax_.first();
        if (allMax_.size() == 1) {
            return first_;
        }
        CustList<Parametrable> allMaxInst_ = new CustList<Parametrable>();
        for (Parametrable m: allMax_) {
            if (((MethodInfo)m).getConstraints().getKind() == MethodAccessKind.INSTANCE) {
                allMaxInst_.add(m);
            }
        }
        if (allMaxInst_.isEmpty()) {
            return null;
        }
        MethodId id_ = first_.getFoundFormatted();
        int lenMax_ = allMaxInst_.size();
        boolean allOvEq_ = true;
        for (int i = 1; i < lenMax_; i++) {
            if (!((MethodInfo)allMaxInst_.get(i)).same(id_)) {
                allOvEq_ = false;
                break;
            }
        }
        CustList<Parametrable> nonAbs_ = new CustList<Parametrable>();
        CustList<Parametrable> abs_ = new CustList<Parametrable>();
        CustList<Parametrable> finals_ = new CustList<Parametrable>();
        if (allOvEq_) {
            for (Parametrable p: allMaxInst_) {
                MethodInfo m_ = (MethodInfo)p;
                if (!m_.isFinalMethod()) {
                    continue;
                }
                finals_.add(p);
            }
            if (finals_.size() == 1) {
                return (MethodInfo) finals_.first();
            }
            ContextEl context_ = _context.getContext();
            for (Parametrable p: allMaxInst_) {
                MethodInfo m_ = (MethodInfo)p;
                if (m_.isAbstractMethod()) {
                    continue;
                }
                String type_ = m_.getClassName();
                type_ = StringExpUtil.getIdFromAllTypes(type_);
                if (context_.getAnalyzing().getAnaClassBody(type_) instanceof InterfaceBlock) {
                    continue;
                }
                nonAbs_.add(p);
            }
            if (nonAbs_.size() == 1) {
                return (MethodInfo) nonAbs_.first();
            }
            nonAbs_.clear();
            for (Parametrable p: allMaxInst_) {
                MethodInfo m_ = (MethodInfo)p;
                if (m_.isAbstractMethod()) {
                    abs_.add(m_);
                    continue;
                }
                nonAbs_.add(p);
            }
            if (nonAbs_.size() == 1) {
                return (MethodInfo) nonAbs_.first();
            }
            StringMap<StringList> map_;
            map_ = _context.getMap();
            int lenAllMax_ = nonAbs_.size();
            for (int i = 0; i < lenAllMax_; i++) {
                MethodInfo curMi_ = (MethodInfo) nonAbs_.get(i);
                boolean spec_ = true;
                String curRet_ = curMi_.getReturnType();
                for (int j = 0; j < lenAllMax_; j++) {
                    if (i == j) {
                        continue;
                    }
                    MethodInfo otherMi_ = (MethodInfo) nonAbs_.get(j);
                    String otherRet_ = otherMi_.getReturnType();
                    if (StringList.quickEq(curRet_, otherRet_)) {
                        spec_ = false;
                        break;
                    }
                    if (!AnaTemplates.isReturnCorrect(otherRet_, curRet_, map_, context_)) {
                        spec_ = false;
                        break;
                    }
                }
                if (spec_) {
                    return curMi_;
                }
            }
            lenAllMax_ = abs_.size();
            for (int i = 0; i < lenAllMax_; i++) {
                MethodInfo curMi_ = (MethodInfo) abs_.get(i);
                boolean spec_ = true;
                String curRet_ = curMi_.getReturnType();
                for (int j = 0; j < lenAllMax_; j++) {
                    if (i == j) {
                        continue;
                    }
                    MethodInfo otherMi_ = (MethodInfo) abs_.get(j);
                    String otherRet_ = otherMi_.getReturnType();
                    if (!AnaTemplates.isReturnCorrect(otherRet_, curRet_, map_, context_)) {
                        spec_ = false;
                        break;
                    }
                }
                if (spec_) {
                    return curMi_;
                }
            }
        }
        return null;
    }
    private static ConstructorInfo getFoundConstructor(CustList<ConstructorInfo> _fct, ArgumentsGroup _context) {
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
            for (Parametrable m: _fct) {
                if (m.getInvocation() == InvocationMethod.VARARG) {
                    fct_.add(m);
                }
            }
            if (fct_.isEmpty()) {
                for (Parametrable m: _fct) {
                    fct_.add(m);
                }
            }
            allMax_ = getAllMaximalSpecificVariableArity(fct_, _context);
        }
        if (allMax_.size() == 1) {
            return (ConstructorInfo) allMax_.first();
        }
        return null;
    }
    private static CustList<Parametrable> getAllMaximalSpecificFixArity(CustList<Parametrable> _all, ArgumentsGroup _context) {
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
    private static CustList<Parametrable> getAllMaximalSpecificVariableArity(CustList<Parametrable> _all, ArgumentsGroup _context) {
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
    private static boolean isStrictMoreSpecificThanVariableArity(Parametrable _varOne, Parametrable _varTwo, ArgumentsGroup _context) {
        if (isNotMoreSpecificThanVariableArity(_varOne, _varTwo, _context)) {
            return false;
        }
        return isNotMoreSpecificThanVariableArity(_varTwo, _varOne, _context);
    }
    private static boolean isStrictMoreSpecificThanFixArity(Parametrable _fixOne, Parametrable _fixTwo, ArgumentsGroup _context) {
        if (isNotMoreSpecificThanFixArity(_fixOne, _fixTwo, _context)) {
            return false;
        }
        return isNotMoreSpecificThanFixArity(_fixTwo, _fixOne, _context);
    }
    private static boolean isNotMoreSpecificThanFixArity(Parametrable _one, Parametrable _two, ArgumentsGroup _context) {
        ContextEl context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        int len_ = _one.getGeneFormatted().getParametersTypesLength();
        boolean all_ = true;
        Identifiable idOne_ = _one.getGeneFormatted();
        Identifiable idTwo_ = _two.getGeneFormatted();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String wcOne_ = idOne_.getParametersType(i);
            String wcTwo_ = idTwo_.getParametersType(i);
			wcOne_ = wrap(i,len_,idOne_.isVararg(),wcOne_);
			wcTwo_ = wrap(i,len_,idTwo_.isVararg(),wcTwo_);
            if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                all_ = false;
                break;
            }
        }
        return !all_;
    }
    private static boolean isNotMoreSpecificThanVariableArity(Parametrable _one, Parametrable _two, ArgumentsGroup _context) {
        ContextEl context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        int lenOne_ = _one.getGeneFormatted().getParametersTypesLength();
        int lenTwo_ = _two.getGeneFormatted().getParametersTypesLength();
        int lastOne_ = lenOne_-1;
        int lastTwo_ = lenTwo_-1;
        boolean all_ = true;
        if (lenOne_ >= lenTwo_) {
            int pr_ = lenTwo_-1;
            for (int i = CustList.FIRST_INDEX; i < pr_; i++) {
                String wcOne_ = _one.getGeneFormatted().getParametersType(i);
                String wcTwo_ = _two.getGeneFormatted().getParametersType(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
            String wcTwo_ = _two.getGeneFormatted().getParametersType(lastTwo_);
            for (int i = pr_; i < lenOne_; i++) {
                String wcOne_ = _one.getGeneFormatted().getParametersType(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
        } else {
            int pr_ = lenOne_-1;
            for (int i = CustList.FIRST_INDEX; i < pr_; i++) {
                String wcOne_ = _one.getGeneFormatted().getParametersType(i);
                String wcTwo_ = _two.getGeneFormatted().getParametersType(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
            String wcOne_ = _one.getGeneFormatted().getParametersType(lastOne_);
            for (int i = pr_; i < lenTwo_; i++) {
                String wcTwo_ = _two.getGeneFormatted().getParametersType(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
        }
        return !all_;
    }
    private static int swapCasePreferred(String _paramFctOne, String _paramFctTwo, StringMap<StringList> _map, ContextEl _ana) {
        if (_paramFctOne.isEmpty()) {
            if (_paramFctTwo.isEmpty()) {
                return CustList.EQ_CMP;
            }
            return CustList.SWAP_SORT;
        }
        if (_paramFctTwo.isEmpty()) {
            return CustList.NO_SWAP_SORT;
        }
        if (StringList.quickEq(_paramFctOne,_paramFctTwo)) {
            return CustList.EQ_CMP;
        }
        Mapping map_ = new Mapping();
        map_.setMapping(_map);
        map_.setArg(_paramFctOne);
        map_.setParam(_paramFctTwo);
        if (AnaTemplates.isCorrectOrNumbers(map_, _ana)) {
            return CustList.NO_SWAP_SORT;
        }
        return CustList.SWAP_SORT;
    }

    private static String wrap(int _i, int _len, boolean _vararg, String _type){
		if (_type.isEmpty()){
			return _type;
		}
		if (_i + 1 == _len && _vararg) {
			return StringExpUtil.getPrettyArrayType(_type);
		}
		return _type;
    }
    private static int compare(ArgumentsGroup _context, Parametrable _o1, Parametrable _o2) {
        int len_ = _o1.getGeneFormatted().getParametersTypesLength();
        ContextEl context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        String glClassOne_ = _o1.getClassName();
        String glClassTwo_ = _o2.getClassName();
        if (len_ != _o2.getGeneFormatted().getParametersTypesLength()) {
            if (len_ < _o2.getGeneFormatted().getParametersTypesLength()) {
                return CustList.SWAP_SORT;
            }
            return CustList.NO_SWAP_SORT;
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String wcOne_ = _o1.getGeneFormatted().getParametersType(i);
            String wcTwo_ = _o2.getGeneFormatted().getParametersType(i);
            int res_ = checkPreferred(wcOne_, wcTwo_, map_, context_, _o1, _o2);
            if (res_ != CustList.EQ_CMP) {
                return res_;
            }
        }
        String baseTypeOne_ = StringExpUtil.getIdFromAllTypes(glClassOne_);
        String baseTypeTwo_ = StringExpUtil.getIdFromAllTypes(glClassTwo_);
        if (StringList.quickEq(baseTypeOne_, baseTypeTwo_)){
            if (_o1.sameParamsVararg(_o2)) {
                return CustList.NO_SWAP_SORT;
            }
        }
        if (!StringList.quickEq(_o2.getReturnType(), _o1.getReturnType())) {
            String p_ = _o1.getReturnType();
            String a_ = _o2.getReturnType();
            if (AnaTemplates.isReturnCorrect(p_, a_, map_, context_)) {
                return CustList.SWAP_SORT;
            }
            a_ = _o1.getReturnType();
            p_ = _o2.getReturnType();
            if (AnaTemplates.isReturnCorrect(p_, a_, map_, context_)) {
                return CustList.NO_SWAP_SORT;
            }
        } else if (StringList.quickEq(baseTypeOne_, baseTypeTwo_)){
            _o1.getParameters().setError(true);
            _o2.getParameters().setError(true);
            return CustList.NO_SWAP_SORT;
        }
        int res_ = AnaTypeUtil.cmpTypes(baseTypeOne_, baseTypeTwo_, context_);
        if (res_ != 0) {
            return res_;
        }
        //inherits types if static methods
        _o1.getParameters().setError(true);
        _o2.getParameters().setError(true);
        return CustList.NO_SWAP_SORT;
    }

    private static int checkPreferred(String _one, String _two, StringMap<StringList> _map, ContextEl _an, Parametrable _p1, Parametrable _p2) {
        int res_ = swapCasePreferred(_one, _two, _map, _an);
        if (res_ != CustList.EQ_CMP) {
            if (res_ == CustList.NO_SWAP_SORT) {
                return res_;
            }
            res_ = swapCasePreferred(_two, _one, _map, _an);
            if (res_ == CustList.NO_SWAP_SORT) {
                return CustList.SWAP_SORT;
            }
            _p1.getParameters().setError(true);
            _p2.getParameters().setError(true);
            return CustList.NO_SWAP_SORT;
        }
        return res_;
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

    public final void setSimpleArgumentAna(Argument _argument, ContextEl _conf) {
        setArgAna(this, _argument, _conf);
    }
    public static void setArgAna(OperationNode _op,Argument _argument, ContextEl _conf) {
        PossibleIntermediateDotted n_ = _op.getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
        String un_ = _op.getResultClass().getUnwrapObject();
        if (!un_.isEmpty()) {
            if (_argument.isNull()) {
                return;
            }
            _argument.setStruct(PrimitiveTypeUtil.unwrapObject(un_, _argument.getStruct(), _conf.getStandards()));
        }
        _op.setSimpleArgument(_argument);
    }

    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final void setResultClass(ClassArgumentMatching _resultClass) {
        resultClass = _resultClass;
    }

    public PossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(PossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    public final int getIndexBegin() {
        return operations.getDelimiter().getIndexBegin();
    }

    public StringList getErrs() {
        return errs;
    }

}
