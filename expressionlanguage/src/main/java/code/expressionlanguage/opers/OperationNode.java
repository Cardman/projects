package code.expressionlanguage.opers;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ConstType;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.exec.PossibleIntermediateDottedOperable;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.*;

public abstract class OperationNode implements Operable {

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

    private MethodOperation parent;

    private OperationNode nextSibling;

    private Argument argument;

    private OperationsSequence operations;

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

    public abstract void analyze(Analyzable _conf);

    public final void tryAnalyzeAssignmentAfter(Analyzable _conf) {
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

    public static OperationNode createOperationNode(int _index,
                                                    int _indexChild, MethodOperation _m, OperationsSequence _op, Analyzable _an) {
        OperationNode res_ = createOperationNodeBis(_index, _indexChild, _m, _op, _an);
        if (_m instanceof DotOperation&&_m.getFirstChild() != null && !(res_ instanceof PossibleIntermediateDotted)) {
            return new ErrorPartOperation(_index, _indexChild, _m, _op);
        }
        return res_;
    }
    public static OperationNode createOperationNodeBis(int _index,
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
                FunctionBlock fct_ = _an.getAnalyzing().getCurrentFct();
                if (fct_ instanceof OverridableBlock) {
                    OverridableBlock indexer_ = (OverridableBlock) fct_;
                    if (indexer_.getKind() == MethodKind.SET_INDEX) {
                        String keyWordValue_ = keyWords_.getKeyWordValue();
                        if (StringList.quickEq(keyWordValue_, str_)) {
                            return new ValueOperation(_index, _indexChild, _m, _op);
                        }
                    }
                }
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.CATCH_VAR) {
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.LOOP_INDEX) {
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.LOOP_VAR) {
                LoopVariable locVar_ = _an.getMutableLoopVar(str_);
                if (locVar_ != null) {
                    return new MutableLoopVariableOperation(_index, _indexChild, _m, _op, locVar_.getClassName());
                }
                return new FinalVariableOperation(_index, _indexChild, _m, _op);
            }
            if (ct_ == ConstType.LOC_VAR) {
                return new VariableOperation(_index, _indexChild, _m, _op);
            }
            if (_m instanceof DotOperation) {
                OperationNode ch_ = _m.getFirstChild();
                if (ch_ != null) {
                    if (ch_.getResultClass().isArray()) {
                        return new ArrayFieldOperation(_index, _indexChild, _m, _op);
                    }
                    return new StandardFieldOperation(_index, _indexChild, _m, _op);
                }
            }
            LoopVariable mutVar_ = _an.getMutableLoopVar(str_);
            if (mutVar_ != null) {
                return new MutableLoopVariableOperation(_index, _indexChild, _m, _op, mutVar_.getClassName());
            }
            LocalVariable locVar_ = _an.getLocalVar(str_);
            if (locVar_ != null) {
                return new VariableOperation(_index, _indexChild, _m, _op);
            }
            return new StandardFieldOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.DECL_PRIO) {
            return new DeclaringOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO) {
            if (_an.isAnnotAnalysis(_m,_op)) {
                return new AnnotationInstanceOperation(_index, _indexChild, _m, _op);
            }
            String fctName_ = _op.getFctName().trim();
            if (fctName_.startsWith(AROBASE)) {
                return new AnnotationInstanceOperation(_index, _indexChild, _m, _op);
            }
        }
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO && _op.isCallDbArray()) {
            String fctName_ = _op.getFctName().trim();
            if (_m instanceof DotOperation) {
                OperationNode ch_ = _m.getFirstChild();
                if (ch_ != null) {
                    String type_ = ch_.getResultClass().getSingleNameOrEmpty();
                    if (!type_.isEmpty()) {
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
                String op_ = _op.getOperators().firstValue();
                if (StringList.quickEq(op_,"{")) {
                    return new ElementArrayInstancing(_index, _indexChild, _m, _op);
                }
                if (StringList.quickEq(op_,"[")) {
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
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO && !_op.getValues().isEmpty()) {
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
            if (ContextEl.startsWithKeyWord(value_, _an.getKeyWords().getKeyWordExplicit())) {
                return new ExplicitOperation(_index, _indexChild, _m, _op);
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

    static void checkClassAccess(Analyzable _conf, String _glClass, String _classStr) {
        Classes classes_ = _conf.getClasses();
        RootBlock r_ = classes_.getClassBody(_classStr);
        String curClassBase_ = Templates.getIdFromAllTypes(_glClass);
        if (!Classes.canAccess(curClassBase_, r_, _conf)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getCurrentFileName());
            //className len
            badAccess_.buildError(_conf.getContextEl().getAnalysisMessages().getInaccessibleType(),
                    _classStr,
                    curClassBase_);
            _conf.addError(badAccess_);
        }
    }
    final boolean isFirstChild() {
        MethodOperation par_ = getParent();
        if (par_ == null) {
            return true;
        }
        return isFirstChildInParent();
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

    static FieldResult getDeclaredCustField(Analyzable _cont, MethodAccessKind _staticContext, ClassArgumentMatching _class,
                                            boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        FieldResult fr_ = resolveDeclaredCustField(_cont, _staticContext != MethodAccessKind.INSTANCE,
                _class, _baseClass, _superClass, _name, _import, _aff);
        if (fr_.getStatus() == SearchingMemberStatus.UNIQ) {
            return fr_;
        }
        FoundErrorInterpret access_ = new FoundErrorInterpret();
        access_.setFileName(_cont.getCurrentFileName());
        access_.setIndexFile(_cont.getCurrentLocationIndex());
        //_name len
        access_.buildError(_cont.getContextEl().getAnalysisMessages().getUndefinedAccessibleField(),
                _name,
                StringList.join(_class.getNames(),"&"));
        _cont.addError(access_);
        FieldResult res_ = new FieldResult();
        res_.setStatus(SearchingMemberStatus.ZERO);
        return res_;
    }

    public static FieldResult resolveDeclaredCustField(Analyzable _cont, boolean _staticContext, ClassArgumentMatching _class,
                                                       boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        if (!_staticContext) {
            FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _baseClass, _superClass, _name, _import, _aff);
            if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
                return resIns_;
            }
        }
        return getDeclaredCustFieldByContext(_cont, true, _class, _baseClass, _superClass, _name, _import,_aff);
    }
    private static FieldResult getDeclaredCustFieldByContext(Analyzable _cont, boolean _static, ClassArgumentMatching _class,
                                                             boolean _baseClass, boolean _superClass, String _name, boolean _import, boolean _aff) {
        ObjectMap<ClassField,FieldResult> ancestors_ = new ObjectMap<ClassField,FieldResult>();
        String glClass_ = _cont.getGlobalClass();
        String curClassBase_ = Templates.getIdFromAllTypes(glClass_);
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
            for (TypeInfo t: g) {
                if (t.isBase()) {
                    String id_ = Templates.getIdFromAllTypes(t.getType());
                    baseTypes_.add(id_);
                    superTypesBaseAncBis_.put(id_, id_);
                    for (String m: t.getSuperTypes()) {
                        superTypesBaseAncBis_.put(m, id_);
                    }
                }
            }
            for (TypeInfo t: g) {
                String f_ = t.getType();
                String cl_ = Templates.getIdFromAllTypes(f_);
                GeneType root_ = _cont.getClassBody(cl_);
                fetchFieldsType(_cont,!_baseClass,_superClass,t.getAncestor(),t.getScope() == MethodAccessKind.STATIC,_aff,_name,glClass_,ancestors_,f_,root_,baseTypes_,superTypesBaseAncBis_);
                maxAnc_ = Math.max(maxAnc_, t.getAncestor());
            }
        }
        int max_ = maxAnc_;
        for (int i = 0; i <= maxAnc_; i++) {
            StringList subClasses_ = new StringList();
            for (EntryCust<ClassField,FieldResult> e: ancestors_.entryList()) {
                if (e.getValue().getAnc() != i) {
                    continue;
                }
                subClasses_.add(e.getKey().getClassName());
            }
            StringList subs_ = PrimitiveTypeUtil.getSubclasses(subClasses_, _cont);
            if (subs_.onlyOneElt()) {
                String cl_ = subs_.first();
                ClassField id_ = new ClassField(cl_, _name);
                return ancestors_.getVal(id_);
            }
        }
        if (_import) {
            Block curBlock_ = _cont.getCurrentBlock();
            int maxLoc_ = maxAnc_ + 1;
            for (EntryCust<ClassField, ImportedField> e: _cont.lookupImportStaticFields(curClassBase_, _name, curBlock_).entryList()) {
                ImportedField v_ = e.getValue();
                max_ = Math.max(max_, v_.getImported() +maxAnc_);
                FieldResult res_ = new FieldResult();
                FieldInfo fi_ = _cont.getFieldInfo(e.getKey());
                String realType_ = fi_.getType();
                boolean finalField_ = fi_.isFinalField();
                String formatted_ = e.getKey().getClassName();
                FieldInfo if_ = FieldInfo.newFieldInfo(_name, formatted_, realType_, _static, finalField_, _cont, _aff,v_.getReturnType());
                res_.setId(if_);
                res_.setAnc(v_.getImported() +maxAnc_);
                res_.setStatus(SearchingMemberStatus.UNIQ);
                ancestors_.add(e.getKey(),res_);
            }
            for (int i = maxLoc_; i <= max_; i++) {
                StringList subClasses_ = new StringList();
                for (EntryCust<ClassField,FieldResult> e: ancestors_.entryList()) {
                    if (e.getValue().getAnc() != i) {
                        continue;
                    }
                    subClasses_.add(e.getKey().getClassName());
                }
                StringList subs_ = PrimitiveTypeUtil.getSubclasses(subClasses_, _cont);
                if (subs_.size() == CustList.ONE_ELEMENT) {
                    String cl_ = subs_.first();
                    ClassField id_ = new ClassField(cl_, _name);
                    return ancestors_.getVal(id_);
                }
            }
        }
        FieldResult r_ = new FieldResult();
        r_.setStatus(SearchingMemberStatus.ZERO);
        return r_;
    }

    private static void fetchFieldsType(Analyzable _conf, boolean _accessFromSuper, boolean _superClass, int _anc, boolean _static,
                                        boolean _aff,
                                        String _name, String _glClass, ObjectMap<ClassField, FieldResult> _ancestors,
                                        String _cl, GeneType _root, StringList _superTypesBase, StringMap<String> _superTypesBaseMap) {
        String fullName_ = _root.getFullName();
        String genericString_ = _root.getGenericString();
        String id_ = Templates.getIdFromAllTypes(fullName_);
        ClassField candidate_ = new ClassField(id_, _name);
        FieldInfo fi_ = _conf.getFieldInfo(candidate_);
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
        String subType_ = _superTypesBaseMap.getVal(fullName_);
        GeneField e_ = fi_.getGeneField();
        if (e_ instanceof AccessibleBlock) {
            if (!Classes.canAccess(subType_, (AccessibleBlock)e_, _conf)) {
                return;
            }
            if (!Classes.canAccess(_glClass, (AccessibleBlock)e_, _conf)) {
                return;
            }
        }
        if (_accessFromSuper) {
            if (StringList.contains(_superTypesBase, fullName_)) {
                return;
            }
        }
        if (!_superClass) {
            if (!StringList.contains(_superTypesBase, fullName_)) {
                return;
            }
        }
        String formatted_;
        if (staticField_) {
            formatted_ = _cl;
        } else {
            formatted_ = Templates.quickFormat(_cl,genericString_,_conf);
        }
        String realType_ = fi_.getType();
        boolean finalField_ = fi_.isFinalField();
        FieldInfo if_ = FieldInfo.newFieldInfo(_name, formatted_, realType_, staticField_, finalField_, _conf, _aff, e_);
        if (if_ == null) {
            return;
        }
        FieldResult res_ = new FieldResult();
        res_.setId(if_);
        res_.setAnc(_anc);
        res_.setStatus(SearchingMemberStatus.UNIQ);
        _ancestors.add(candidate_, res_);
    }
    static ConstrustorIdVarArg getDeclaredCustConstructor(Analyzable _conf, int _varargOnly, ClassArgumentMatching _class,
            GeneType _type,
            ConstructorId _uniqueId, ClassArgumentMatching... _args) {
        String clCurName_ = _class.getName();
        LgNames stds_ = _conf.getStandards();
        String glClass_ = _conf.getGlobalClass();
        int varargOnly_ = _varargOnly;
        boolean uniq_ = false;
        if (_uniqueId != null) {
            if (varargOnly_ > -1) {
                uniq_ = true;
            }
            varargOnly_ = -1;
        }
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid(_conf)) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(stds_.getAliasVoid());
                mapping_.setParam(stds_.getAliasObject());
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                //key word len
                cast_.buildError(_conf.getContextEl().getAnalysisMessages().getVoidType(),
                        stds_.getAliasVoid());
                _conf.addError(cast_);
            }
        }
        CustList<GeneConstructor> constructors_ = Classes.getConstructorBodies(_type);
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
            if (varargOnly_ > -1) {
                if (!varArg_) {
                    continue;
                }
            }
            if (_uniqueId != null) {
                if (!_uniqueId.eq(ctor_)) {
                    continue;
                }
            }
            if (e instanceof AccessibleBlock) {
                if (!Classes.canAccess(glClass_, (AccessibleBlock)e, _conf)) {
                    continue;
                }
            }
            ParametersGroup pg_ = new ParametersGroup();
            for (String c: ctor_.getParametersTypes()) {
                pg_.add(new ClassMatching(c));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            mloc_.setConstraints(ctor_);
            mloc_.setParameters(pg_);
            mloc_.setClassName(clCurName_);
            mloc_.format(_conf);
            if (!isPossibleMethod(_conf, uniq_, varargOnly_, mloc_, _args)) {
                continue;
            }
            signatures_.add(mloc_);
        }
        StringMap<StringList> map_;
        map_ = _conf.getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        ConstructorInfo cInfo_ = sortCtors(signatures_, gr_);
        if (cInfo_ == null) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(StringList.join(c.getNames(), "&"));
            }
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setIndexFile(_conf.getCurrentLocationIndex());
            //key word len
            undefined_.buildError(_conf.getContextEl().getAnalysisMessages().getUndefinedCtor(),
                    new ConstructorId(clCurName_, classesNames_, false).getSignature(_conf));
            _conf.addError(undefined_);
            ConstrustorIdVarArg out_;
            out_ = new ConstrustorIdVarArg();
            return out_;
        }
        ConstructorId ctor_ = cInfo_.getConstraints();
        ConstrustorIdVarArg out_;
        out_ = new ConstrustorIdVarArg();
        if (varargOnly_ == -1 && cInfo_.isVarArgWrap()) {
            out_.setVarArgToCall(true);
        }
        out_.setRealId(ctor_);
        out_.setConstId(cInfo_.getFormatted());
        return out_;
    }

    void checkNull(Argument _arg, Analyzable _conf) {
        if (Argument.isNullValue(_arg)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            //leaf or header parent or first operator
            static_.buildError(_conf.getContextEl().getAnalysisMessages().getNullValue(),
                    _conf.getStandards().getAliasNullPe());
            _conf.addError(static_);
        }
    }
    static ClassMethodIdReturn getDeclaredCustMethod(Analyzable _conf, int _varargOnly,
    MethodAccessKind _staticContext, StringList _classes, String _name,
    boolean _superClass, boolean _accessFromSuper, boolean _import, ClassMethodId _uniqueId, ClassArgumentMatching... _argsClass) {
        ClassMethodIdReturn res_ = tryGetDeclaredCustMethod(_conf, _varargOnly, _staticContext, _classes, _name, _superClass, _accessFromSuper, _import, _uniqueId, _argsClass);
        if (res_.isFoundMethod()) {
            return res_;
        }
        ClassMethodIdReturn return_ = new ClassMethodIdReturn(false);
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringList.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_conf.getCurrentFileName());
        undefined_.setIndexFile(_conf.getCurrentLocationIndex());
        //_name len
        undefined_.buildError(_conf.getContextEl().getAnalysisMessages().getUndefinedMethod(),
                new MethodId(_staticContext, _name, classesNames_).getSignature(_conf));
        _conf.addError(undefined_);
        return_.setId(new ClassMethodId(_classes.first(), new MethodId(_staticContext, _name, classesNames_)));
        return_.setRealId(new MethodId(_staticContext, _name, classesNames_));
        return_.setRealClass(_classes.first());
        return_.setReturnType(_conf.getStandards().getAliasObject());
        return return_;
    }

    public static ClassMethodIdReturn tryGetDeclaredToString(Analyzable _conf, String _class) {
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectMap<ClassMethodId, MethodInfo>();
        String baseCurName_ = Templates.getIdFromAllTypes(_class);
        RootBlock root_ = _conf.getClasses().getClassBody(baseCurName_);
        if (root_ != null) {
            fetchToStringMethods(_conf,root_,baseCurName_,methods_);
        }
        return getCustResultExec(_conf, methods_);
    }

    private static ClassMethodIdReturn getCustResultExec(Analyzable _conf,
                                                         ObjectMap<ClassMethodId, MethodInfo> _methods) {
        MethodInfo found_ = getFoundMethodExec(_methods.values(), _conf);
        if (found_ == null) {
            return new ClassMethodIdReturn(false);
        }
        MethodId constraints_ = found_.getConstraints();
        String baseClassName_ = found_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = found_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(found_.getReturnType());
        res_.setAncestor(found_.getAncestor());
        res_.setAbstractMethod(found_.isAbstractMethod());
        res_.setStaticMethod(found_.isStatic());
        return res_;
    }

    private static MethodInfo getFoundMethodExec(CustList<MethodInfo> _fct, Analyzable _context) {
        CustList<MethodInfo> nonAbs_ = new CustList<MethodInfo>();
        CustList<MethodInfo> finals_ = new CustList<MethodInfo>();
        for (MethodInfo p: _fct) {
            if (!p.isFinalMethod()) {
                continue;
            }
            finals_.add(p);
        }
        if (finals_.size() == 1) {
            return finals_.first();
        }
        for (MethodInfo p: _fct) {
            if (p.isAbstractMethod()) {
                continue;
            }
            String type_ = p.getClassName();
            if (_context.getClassBody(type_) instanceof GeneInterface) {
                continue;
            }
            nonAbs_.add(p);
        }
        if (nonAbs_.size() == 1) {
            return nonAbs_.first();
        }
        nonAbs_.clear();
        for (MethodInfo p: _fct) {
            if (p.isAbstractMethod()) {
                continue;
            }
            nonAbs_.add(p);
        }
        if (nonAbs_.size() == 1) {
            return nonAbs_.first();
        }
        StringList sub_ = new StringList();
        StringMap<MethodInfo> meths_ = new StringMap<MethodInfo>();
        for (MethodInfo p: _fct) {
            String cl_ = p.getClassName();
            meths_.addEntry(cl_,p);
            sub_.add(cl_);
        }
        sub_ = PrimitiveTypeUtil.getSubclasses(sub_,_context);
        if (!sub_.onlyOneElt()) {
            return null;
        }
        return meths_.getVal(sub_.first());
    }

    protected static ClassMethodIdReturn tryGetDeclaredCustMethod(Analyzable _conf, int _varargOnly,
                                                                  MethodAccessKind _staticContext,
                                                                  StringList _classes, String _name,
                                                                  boolean _superClass, boolean _accessFromSuper,
                                                                  boolean _import, ClassMethodId _uniqueId,
                                                                  ClassArgumentMatching[] _argsClass) {
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _staticContext, _accessFromSuper, _superClass, _classes, _name, _import, _uniqueId);
        int varargOnly_ = _varargOnly;
        boolean uniq_ = false;
        if (_uniqueId != null) {
            if (varargOnly_ > -1) {
                uniq_ = true;
            }
            varargOnly_ = -1;
        }
        return getCustResult(_conf,uniq_, varargOnly_, methods_, _name, _argsClass);
    }
    protected static ClassMethodIdReturn tryGetDeclaredCast(Analyzable _conf, String _classes, String _name, ClassMethodId _uniqueId, ClassArgumentMatching[] _argsClass) {
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = getDeclaredCustCast(_conf, _classes, _uniqueId);
        return getCustResult(_conf,false, -1, methods_, _name, _argsClass);
    }

    static ClassMethodIdReturn getOperator(Analyzable _cont, String _op, ClassArgumentMatching... _argsClass) {
        ObjectMap<ClassMethodId, MethodInfo> ops_ = getOperators(_cont);
        return getCustResult(_cont,false, -1, ops_, _op, _argsClass);
    }
    static ObjectMap<ClassMethodId, MethodInfo> getOperators(Analyzable _cont){
        String objType_ = _cont.getStandards().getAliasObject();
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectMap<ClassMethodId, MethodInfo>();
        for (OperatorBlock o: _cont.getClasses().getOperators()) {
            String ret_ = o.getImportedReturnType();
            MethodId id_ = o.getId();
            ParametersGroup p_ = new ParametersGroup();
            for (String c: id_.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            MethodInfo mloc_ = new MethodInfo();
            mloc_.setClassName(objType_);
            mloc_.setStatic(true);
            mloc_.setConstraints(id_);
            mloc_.setParameters(p_);
            mloc_.setReturnType(ret_);
            mloc_.format(true,_cont);
            ClassMethodId clId_ = new ClassMethodId(objType_, id_);
            methods_.put(clId_, mloc_);
        }
        return methods_;
    }
    private static ObjectMap<ClassMethodId, MethodInfo>
    getDeclaredCustCast(Analyzable _conf,
                        String _fromClass, ClassMethodId _uniqueId) {
        String glClass_ = _conf.getGlobalClass();
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectMap<ClassMethodId, MethodInfo>();
        String id_ = Templates.getIdFromAllTypes(_fromClass);
        GeneType root_ = _conf.getClassBody(id_);
        StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
        superTypesBaseAncBis_.addEntry(id_,id_);
        fetchCastMethods(_conf,  _uniqueId, glClass_, methods_, _fromClass, root_, superTypesBaseAncBis_);
        return methods_;
    }

    private static ObjectMap<ClassMethodId, MethodInfo>
    getDeclaredCustMethodByType(Analyzable _conf, MethodAccessKind _staticContext, boolean _accessFromSuper,
                                boolean _superClass, StringList _fromClasses, String _name, boolean _import, ClassMethodId _uniqueId) {
        String glClass_ = _conf.getGlobalClass();
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectMap<ClassMethodId, MethodInfo>();
        fetchParamClassAncMethods(_conf,_fromClasses,_staticContext,_accessFromSuper,_superClass,_uniqueId,methods_);
        if (_import) {
            for (EntryCust<ClassMethodId, ImportedMethod> e: _conf.lookupImportStaticMethods(glClass_, _name, _conf.getCurrentBlock()).entryList()) {
                ClassMethodId m = e.getKey();
                String clName_ = m.getClassName();
                MethodId id_ = m.getConstraints();
                if (isCandidateMethod(_uniqueId, clName_, id_)) {
                    continue;
                }
                ParametersGroup p_ = new ParametersGroup();
                for (String c: id_.getParametersTypes()) {
                    p_.add(new ClassMatching(c));
                }
                MethodInfo mloc_ = new MethodInfo();
                mloc_.setImported(e.getValue().getImported());
                mloc_.setClassName(clName_);
                mloc_.setStatic(true);
                mloc_.setConstraints(id_);
                mloc_.setParameters(p_);
                mloc_.format(true,_conf);
                mloc_.setReturnType(e.getValue().getReturnType());
                methods_.add(m, mloc_);
            }
        }
        return methods_;
    }

    private static boolean isCandidateMethod(ClassMethodId _uniqueId, String _clName, MethodId _id) {
        if (_uniqueId != null) {
            if (!StringList.quickEq(_uniqueId.getClassName(), _clName)) {
                return true;
            }
            if (!_uniqueId.getConstraints().eq(_id)) {
                return true;
            }
        }
        return false;
    }


    public static void fetchParamClassAncMethods(Analyzable _conf, StringList _fromClasses, ObjectMap<ClassMethodId, MethodInfo> _methods) {
        fetchParamClassAncMethods(_conf,_fromClasses,MethodAccessKind.INSTANCE,false,true,null,_methods);
    }
    private static void fetchParamClassAncMethods(Analyzable _conf, StringList _fromClasses, MethodAccessKind _staticContext, boolean _accessFromSuper,
                                                  boolean _superClass, ClassMethodId _uniqueId, ObjectMap<ClassMethodId, MethodInfo> _methods) {
        CustList<CustList<TypeInfo>> typeInfosGroups_ = typeLists(_conf,_fromClasses,_staticContext);
        String glClass_ = _conf.getGlobalClass();
        for (CustList<TypeInfo> g: typeInfosGroups_) {
            StringList baseTypes_ = new StringList();
            StringMap<String> superTypesBaseAncBis_ = new StringMap<String>();
            for (TypeInfo t: g) {
                if (t.isBase()) {
                    String id_ = Templates.getIdFromAllTypes(t.getType());
                    baseTypes_.add(id_);
                    superTypesBaseAncBis_.put(id_, id_);
                    for (String m: t.getSuperTypes()) {
                        superTypesBaseAncBis_.put(m, id_);
                    }
                }
            }
            for (TypeInfo t: g) {
                String f_ = t.getType();
                String cl_ = Templates.getIdFromAllTypes(f_);
                GeneType root_ = _conf.getClassBody(cl_);
                fetchParamClassMethods(_conf,_accessFromSuper,_superClass,t.getAncestor(),t.getScope(),_uniqueId,glClass_,_methods,f_,root_,baseTypes_,superTypesBaseAncBis_);
            }
        }
    }

    public static CustList<CustList<TypeInfo>> typeLists(Analyzable _conf, StringList _fromClasses, MethodAccessKind _staticContext) {
        CustList<TypeInfo> typeInfos_ = new CustList<TypeInfo>();
        IntTreeMap<CustList<TypeInfo>> typeInfosMap_ = new IntTreeMap<CustList<TypeInfo>>();
        for (String s: _fromClasses) {
            String baseCurName_ = Templates.getIdFromAllTypes(s);
            GeneType root_ = _conf.getClassBody(baseCurName_);
            if (root_ == null) {
                continue;
            }
            String gene_ = root_.getGenericString();
            addToList(_conf,typeInfos_,_staticContext,s,gene_,0,true);
            for (String m : root_.getAllGenericSuperTypes()) {
                addToList(_conf,typeInfos_,_staticContext,s,m,0,false);
            }
        }
        typeInfosMap_.put(0,typeInfos_);
        for (TypeInfo t: typeInfos_) {
            String f_ = t.getType();
            String cl_ = Templates.getIdFromAllTypes(f_);
            GeneType root_ = _conf.getClassBody(cl_);
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
            String cl_ = Templates.getIdFromAllTypes(f_);
            GeneType root_ = _conf.getClassBody(cl_);
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
                addToList(_conf,typeInfosInt_,scope_,f_,gene_,anc_,true);
                for (String m : p.getAllGenericSuperTypes()) {
                    addToList(_conf,typeInfosInt_,scope_,f_,m,anc_,false);
                }
                if (p.isStaticType()) {
                    add_ = false;
                }
                anc_++;
            }
        }
        return typeInfosMap_.values();
    }
    private static void addToList(Analyzable _conf, CustList<TypeInfo> _list, MethodAccessKind _k, String _first, String _second, int _anc, boolean _base) {
        TypeInfo t_ = newTypeInfo(_conf, _k, _first, _second, _anc);
        String f_ = t_.getType();
        String id_ = Templates.getIdFromAllTypes(f_);
        for (TypeInfo t: _list) {
            if (StringList.quickEq(t.getType(), f_)) {
                return;
            }
        }
        GeneType info_ = _conf.getClassBody(id_);
        t_.setBase(_base);
        t_.setSuperTypes(info_.getAllSuperTypes());
        _list.add(t_);
    }

    private static TypeInfo newTypeInfo(Analyzable _conf, MethodAccessKind _k, String _first, String _second, int _anc) {
        MethodAccessKind k_ = _k;
        String type_ = _second;
        if (Templates.correctNbParameters(_first,_conf)) {
            String f_ = Templates.format(_first, _second, _conf);
            if (f_ == null) {
                k_ = MethodAccessKind.STATIC;
            } else {
                type_ = f_;
            }
        } else {
            k_ = MethodAccessKind.STATIC;
        }
        TypeInfo t_ = new TypeInfo();
        t_.setType(type_);
        t_.setAncestor(_anc);
        t_.setScope(k_);
        return t_;
    }

    private static void fetchCastMethods(Analyzable _conf, ClassMethodId _uniqueId, String _glClass, ObjectMap<ClassMethodId, MethodInfo> _methods, String _cl, GeneType _root, StringMap<String> _superTypesBaseMap) {
        ClassMethodId uniq_ = null;
        if (_uniqueId != null) {
            uniq_ = new ClassMethodId(Templates.getIdFromAllTypes(_uniqueId.getClassName()),_uniqueId.getConstraints());
        }
        for (GeneMethod e: ContextEl.getMethodBlocks(_root)) {
            MethodId id_ = e.getId();
            if (!e.isStaticMethod()) {
                continue;
            }
            ClassMethodId cId_ = new ClassMethodId(_cl, id_);
            MethodInfo stMeth_ = fetchedParamMethod(e,cId_,false,_conf,uniq_,_glClass,0,_cl,_superTypesBaseMap);
            if (stMeth_ == null) {
                continue;
            }
            stMeth_.setReturnType(e.getImportedReturnType());
            _methods.add(cId_, stMeth_);
        }
    }

    private static void fetchParamClassMethods(Analyzable _conf, boolean _accessFromSuper, boolean _superClass, int _anc, MethodAccessKind _kind,
                                               ClassMethodId _uniqueId, String _glClass, ObjectMap<ClassMethodId, MethodInfo> _methods,
                                               String _cl, GeneType _root, StringList _superTypesBase, StringMap<String> _superTypesBaseMap) {
        String fullName_ = _root.getFullName();
        String genericString_ = _root.getGenericString();
        for (GeneMethod e: ContextEl.getMethodBlocks(_root)) {
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
            if (_accessFromSuper) {
                if (StringList.contains(_superTypesBase, fullName_)) {
                    continue;
                }
            }
            if (!_superClass) {
                if (!StringList.contains(_superTypesBase, fullName_)) {
                    continue;
                }
            }
            ClassMethodId cId_ = new ClassMethodId(genericString_, id_);
            MethodInfo stMeth_ = fetchedParamMethod(e,cId_,k_ == MethodAccessKind.STATIC,_conf, _uniqueId,_glClass,_anc, _cl,_superTypesBaseMap);
            if (stMeth_ == null) {
                continue;
            }
            ClassMethodId clId_ = new ClassMethodId(fullName_, id_);
            _methods.add(clId_, stMeth_);
        }
        for (EntryCust<ClassMethodId,MethodInfo> e: getPredefineStaticEnumMethods(_conf,genericString_,_anc).entryList()) {
            ClassMethodId cId_ = e.getKey();
            MethodId id_ = cId_.getConstraints();
            if (isCandidateMethod(_uniqueId, fullName_, id_)) {
                continue;
            }
            ClassMethodId clId_ = new ClassMethodId(fullName_, id_);
            _methods.add(clId_, e.getValue());
        }
    }
    private static void fetchToStringMethods(Analyzable _conf, RootBlock _root,String _cl, ObjectMap<ClassMethodId, MethodInfo> _methods) {
        StringList geneSuperTypes_ = new StringList();
        geneSuperTypes_.add(_cl);
        geneSuperTypes_.addAllElts(_root.getAllSuperTypes());
        for (String t: geneSuperTypes_) {
            RootBlock root_ = _conf.getClasses().getClassBody(t);
            if (root_ == null) {
                continue;
            }
            String fullName_ = root_.getFullName();
            for (GeneMethod e: ContextEl.getMethodBlocks(root_)) {
                MethodId id_ = e.getId();
                if (id_.isStaticMethod()) {
                    continue;
                }
                if (!StringList.quickEq(id_.getName(),_conf.getKeyWords().getKeyWordToString())) {
                    continue;
                }
                if (!id_.getParametersTypes().isEmpty()) {
                    continue;
                }
                ClassMethodId clId_ = new ClassMethodId(fullName_, id_);
                _methods.add(clId_, buildMethodInfo(e,false,_conf,0,id_,t));
            }
        }
    }

    private static MethodInfo fetchedParamMethod(GeneMethod _m,ClassMethodId _s,boolean _keepParams,
                                                 Analyzable _conf, ClassMethodId _uniqueId,
                                                 String _glClass, int _anc, String _f, StringMap<String> _superTypesBaseMap) {
        String name_ = _s.getClassName();
        String base_ = Templates.getIdFromAllTypes(name_);
        MethodId id_ = _s.getConstraints();
        if (isCandidateMethod(_uniqueId, base_, id_)) {
            return null;
        }
        String formattedClass_;
        if (!id_.canAccessParamTypes() || StringList.quickEq(base_, _conf.getStandards().getAliasFct())) {
            formattedClass_ = name_;
        } else {
            formattedClass_ = _f;
        }
        if (_m instanceof AccessibleBlock) {
            String subType_ = _superTypesBaseMap.getVal(base_);
            if (!Classes.canAccess(subType_, (AccessibleBlock)_m, _conf)) {
                return null;
            }
            if (!Classes.canAccess(_glClass, (AccessibleBlock)_m, _conf)) {
                return null;
            }
        }
        return buildMethodInfo(_m, _keepParams, _conf, _anc, id_, formattedClass_);
    }

    private static MethodInfo buildMethodInfo(GeneMethod _m, boolean _keepParams, Analyzable _conf, int _anc, MethodId _id, String _formattedClass) {
        String ret_ = _m.getImportedReturnType();
        ret_ = Templates.wildCardFormatReturn(_formattedClass, ret_, _conf);
        ParametersGroup p_ = new ParametersGroup();
        for (String c: _id.getParametersTypes()) {
            p_.add(new ClassMatching(c));
        }
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setClassName(_formattedClass);
        mloc_.setStaticMethod(_m.getId().getKind());
        if (_m instanceof GeneCustMethod) {
            mloc_.setAbstractMethod(((GeneCustMethod)_m).isAbstractMethod());
            mloc_.setFinalMethod(((GeneCustMethod)_m).isFinalMethod());
        }
        mloc_.setConstraints(_id);
        mloc_.setParameters(p_);
        mloc_.setReturnType(ret_);
        mloc_.setAncestor(_anc);
        mloc_.format(_keepParams,_conf);
        return mloc_;
    }

    private static ObjectMap<ClassMethodId, MethodInfo> getPredefineStaticEnumMethods(Analyzable _conf, String _className, int _ancestor) {
        ObjectMap<ClassMethodId, MethodInfo> methods_;
        methods_ = new ObjectMap<ClassMethodId, MethodInfo>();
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
        MethodId realId_ = new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_));
        for (String c: realId_.getParametersTypes()) {
            p_.add(new ClassMatching(c));
        }
        MethodInfo mloc_ = new MethodInfo();
        mloc_.setClassName(idClass_);
        mloc_.setStatic(true);
        mloc_.setConstraints(realId_);
        mloc_.setParameters(p_);
        mloc_.format(true,_conf);
        mloc_.setReturnType(returnType_);
        mloc_.setAncestor(_ancestor);
        ClassMethodId clId_ = new ClassMethodId(idClass_, realId_);
        methods_.add(clId_, mloc_);
        p_ = new ParametersGroup();
        String values_ = _conf.getStandards().getAliasEnumValues();
        realId_ = new MethodId(MethodAccessKind.STATIC, values_, new StringList());
        mloc_ = new MethodInfo();
        mloc_.setClassName(idClass_);
        mloc_.setStatic(true);
        mloc_.setConstraints(realId_);
        mloc_.setParameters(p_);
        mloc_.format(true,_conf);
        returnType_ = PrimitiveTypeUtil.getPrettyArrayType(returnType_);
        mloc_.setReturnType(returnType_);
        mloc_.setAncestor(_ancestor);
        clId_ = new ClassMethodId(idClass_, realId_);
        methods_.add(clId_, mloc_);
        return methods_;
    }
    private static ClassMethodIdReturn getCustResult(Analyzable _conf, boolean _unique,int _varargOnly,
                                                     ObjectMap<ClassMethodId, MethodInfo> _methods,
            String _name, ClassArgumentMatching... _argsClass) {
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
            MethodInfo mi_ = e.getValue();
            if (!isPossibleMethod(_conf, _unique, _varargOnly, mi_, _argsClass)) {
                continue;
            }
            signatures_.add(mi_);
        }
        StringMap<StringList> map_;
        map_ = _conf.getCurrentConstraints();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_);
        MethodInfo found_ = sortFct(signatures_, gr_);
        if (found_ == null) {
            return new ClassMethodIdReturn(false);
        }
        MethodId constraints_ = found_.getConstraints();
        String baseClassName_ = found_.getClassName();
        ClassMethodIdReturn res_ = new ClassMethodIdReturn(true);
        MethodId id_ = found_.getFoundFormatted();
        res_.setId(new ClassMethodId(baseClassName_, id_));
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

    private static boolean isPossibleMethod(Analyzable _context, boolean _unique, int _varargOnly, Parametrable _id,
                                            ClassArgumentMatching... _argsClass) {
        int startOpt_ = _argsClass.length;
        boolean checkOnlyDem_ = true;
        StringList params_ = _id.getGeneFormatted().getParametersTypes();
        int nbDem_ = params_.size();
        int last_ = params_.getLastIndex();
        boolean vararg_ = _id.isVararg();
        if (!vararg_) {
            if (params_.size() != _argsClass.length) {
                return false;
            }
        } else {
            if (params_.size() > _argsClass.length + 1) {
                return false;
            }
            if (_varargOnly != 0) {
                checkOnlyDem_ = false;
                nbDem_--;
                startOpt_ = params_.size() - 1;
            }
            if (_varargOnly > 0) {
                if (startOpt_ != _varargOnly - 1) {
                    return false;
                }
            } else if (_varargOnly == 0) {
                if (params_.size() != _argsClass.length) {
                    return false;
                }
            }
        }
        StringMap<StringList> mapCtr_ = _context.getCurrentConstraints();
        boolean allNotBoxUnbox_ = true;
        for (int i = CustList.FIRST_INDEX; i < nbDem_; i++) {
            String wc_ = params_.get(i);
			wc_ = wrap(i,params_.size(),vararg_,wc_);
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
            }
            if (allNotBoxUnbox_) {
                _id.setInvocation(InvocationMethod.STRICT);
            } else if (!vararg_) {
                _id.setInvocation(InvocationMethod.BOX_UNBOX);
            } else {
                _id.setInvocation(InvocationMethod.ALL);
            }
            return true;
        }
        if (params_.size() == _argsClass.length) {
            Mapping map_ = new Mapping();
            ClassArgumentMatching arg_ = _argsClass[last_];
            map_.setArg(arg_);
            map_.getMapping().putAllMap(mapCtr_);
            String wc_ = params_.last();
            if (wc_.isEmpty()) {
                if (arg_.isVariable()) {
                    setInvoke(_id, allNotBoxUnbox_);
                    return true;
                }
                return false;
            }
            String arr_ = PrimitiveTypeUtil.getPrettyArrayType(wc_);
            map_.setParam(arr_);
            if (Templates.isCorrectOrNumbers(map_, _context)) {
                setInvoke(_id, allNotBoxUnbox_);
                if (_unique) {
                    map_.setParam(wc_);
                    if (Templates.isCorrectOrNumbers(map_, _context)) {
                        _id.setInvocation(InvocationMethod.ALL);
                        _id.setVarArgWrap(true);
                    }
                }
                return true;
            }
            map_.setParam(wc_);
            if (Templates.isCorrectOrNumbers(map_, _context)) {
                _id.setInvocation(InvocationMethod.ALL);
                _id.setVarArgWrap(true);
                return true;
            }
            return false;
        }
        nbDem_ = _argsClass.length;
        Mapping map_ = new Mapping();
        map_.getMapping().putAllMap(mapCtr_);
        String wc_ = params_.last();
        if (wc_.isEmpty()) {
            return false;
        }
        map_.setParam(wc_);
        for (int i = startOpt_; i < nbDem_; i++) {
            map_.setArg(_argsClass[i]);
            if (!Templates.isCorrectOrNumbers(map_, _context)) {
                return false;
            }
        }
        _id.setInvocation(InvocationMethod.ALL);
        _id.setVarArgWrap(true);
        return true;
    }

    private static void setInvoke(Parametrable _id, boolean _allNotBoxUnbox) {
        if (_allNotBoxUnbox) {
            _id.setInvocation(InvocationMethod.STRICT);
        } else {
            _id.setInvocation(InvocationMethod.BOX_UNBOX);
        }
    }

    private static MethodInfo sortFct(CustList<MethodInfo> _fct, ArgumentsGroup _context) {
        MethodInfo meth_ = getFoundMethod(_fct, _context);
        if (meth_ != null) {
            return meth_;
        }
        CustList<Parametrable> instances_ = new CustList<Parametrable>();
        CustList<Parametrable> staticsCall_ = new CustList<Parametrable>();
        CustList<Parametrable> statics_ = new CustList<Parametrable>();
        for (MethodInfo m : _fct) {
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
        return (MethodInfo) getBest(statics_, _context);
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
                fct_.add(m);
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
            Analyzable context_ = _context.getContext();
            for (Parametrable p: allMaxInst_) {
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
                    if (!Templates.isReturnCorrect(otherRet_, curRet_, map_, context_)) {
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
                    if (!Templates.isReturnCorrect(otherRet_, curRet_, map_, context_)) {
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
                fct_.add(m);
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
    private static boolean isStrictMoreSpecificThanVariableArity(Parametrable _one, Parametrable _two, ArgumentsGroup _context) {
        if (!isMoreSpecificThanVariableArity(_one, _two, _context)) {
            return false;
        }
        return !isMoreSpecificThanVariableArity(_two, _one, _context);
    }
    private static boolean isStrictMoreSpecificThanFixArity(Parametrable _one, Parametrable _two, ArgumentsGroup _context) {
        if (!isMoreSpecificThanFixArity(_one, _two, _context)) {
            return false;
        }
        return !isMoreSpecificThanFixArity(_two, _one, _context);
    }
    private static boolean isMoreSpecificThanFixArity(Parametrable _one, Parametrable _two, ArgumentsGroup _context) {
        int cmp_ = compareImportAncestor(_one, _two);
        if (cmp_ == CustList.SWAP_SORT) {
            return false;
        }
        if (cmp_ == CustList.NO_SWAP_SORT) {
            return true;
        }
        Analyzable context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        int len_ = _one.getParameters().size();
        boolean all_ = true;
        Identifiable idOne_ = _one.getGeneFormatted();
        Identifiable idTwo_ = _two.getGeneFormatted();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String wcOne_ = idOne_.getParametersTypes().get(i);
            String wcTwo_ = idTwo_.getParametersTypes().get(i);
			wcOne_ = wrap(i,len_,idOne_.isVararg(),wcOne_);
			wcTwo_ = wrap(i,len_,idTwo_.isVararg(),wcTwo_);
            if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                all_ = false;
                break;
            }
        }
        return all_;
    }
    private static boolean isMoreSpecificThanVariableArity(Parametrable _one, Parametrable _two, ArgumentsGroup _context) {
        int cmp_ = compareImportAncestor(_one, _two);
        if (cmp_ == CustList.SWAP_SORT) {
            return false;
        }
        if (cmp_ == CustList.NO_SWAP_SORT) {
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
                String wcOne_ = _one.getGeneFormatted().getParametersTypes().get(i);
                String wcTwo_ = _two.getGeneFormatted().getParametersTypes().get(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
            String wcTwo_ = _two.getGeneFormatted().getParametersTypes().last();
            for (int i = pr_; i < lenOne_; i++) {
                String wcOne_ = _one.getGeneFormatted().getParametersTypes().get(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
        } else {
            int pr_ = lenOne_-1;
            for (int i = CustList.FIRST_INDEX; i < pr_; i++) {
                String wcOne_ = _one.getGeneFormatted().getParametersTypes().get(i);
                String wcTwo_ = _two.getGeneFormatted().getParametersTypes().get(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
            String wcOne_ = _one.getGeneFormatted().getParametersTypes().last();
            for (int i = pr_; i < lenTwo_; i++) {
                String wcTwo_ = _two.getGeneFormatted().getParametersTypes().get(i);
                if (swapCasePreferred(wcOne_, wcTwo_, map_, context_) == CustList.SWAP_SORT) {
                    all_ = false;
                    break;
                }
            }
        }
        return all_;
    }
    private static int swapCasePreferred(String _paramFctOne, String _paramFctTwo, StringMap<StringList> _map, Analyzable _ana) {
        ClassMatching one_;
        ClassMatching two_;
        if (_paramFctOne.isEmpty()) {
            if (_paramFctTwo.isEmpty()) {
                return CustList.EQ_CMP;
            } else {
                return CustList.SWAP_SORT;
            }
        }
        if (_paramFctTwo.isEmpty()) {
            return CustList.NO_SWAP_SORT;
        }
        one_ = new ClassMatching(_paramFctOne);
        two_ = new ClassMatching(_paramFctTwo);
        if (one_.matchClass(two_)) {
            return CustList.EQ_CMP;
        }
        if (two_.isAssignableFrom(one_, _map, _ana)) {
            return CustList.NO_SWAP_SORT;
        }
        return CustList.SWAP_SORT;
    }

    private static String wrap(int _i, int _len, boolean _vararg, String _type){
		if (_type.isEmpty()){
			return _type;
		}
		if (_i + 1 == _len && _vararg) {
			return PrimitiveTypeUtil.getPrettyArrayType(_type);
		}
		return _type;
    }
    private static int compare(ArgumentsGroup _context, Parametrable _o1, Parametrable _o2) {
        int len_ = _o1.getParameters().size();
        Analyzable context_ = _context.getContext();
        StringMap<StringList> map_;
        map_ = _context.getMap();
        String glClassOne_ = _o1.getClassName();
        String glClassTwo_ = _o2.getClassName();
        if (len_ != _o2.getParameters().size()) {
            if (len_ < _o2.getParameters().size()) {
                return CustList.SWAP_SORT;
            }
            return CustList.NO_SWAP_SORT;
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String wcOne_ = _o1.getGeneFormatted().getParametersTypes().get(i);
            String wcTwo_ = _o2.getGeneFormatted().getParametersTypes().get(i);
            int res_ = checkPreferred(wcOne_, wcTwo_, map_, context_, _o1, _o2);
            if (res_ != CustList.EQ_CMP) {
                return res_;
            }
        }
        String baseTypeOne_ = Templates.getIdFromAllTypes(glClassOne_);
        String baseTypeTwo_ = Templates.getIdFromAllTypes(glClassTwo_);
        if (!StringList.quickEq(_o2.getReturnType(), _o1.getReturnType())) {
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
        } else if (StringList.quickEq(baseTypeOne_, baseTypeTwo_)){
            _o1.getParameters().setError(true);
            _o2.getParameters().setError(true);
            return CustList.NO_SWAP_SORT;
        }
        int res_ = PrimitiveTypeUtil.cmpTypes(baseTypeOne_, baseTypeTwo_, context_);
        if (res_ != 0) {
            return res_;
        }
        //inherits types if static methods
        _o1.getParameters().setError(true);
        _o2.getParameters().setError(true);
        return CustList.NO_SWAP_SORT;
    }

    private static int compareImportAncestor(Parametrable _o1, Parametrable _o2) {
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
        return CustList.EQ_CMP;
    }
    private static int checkPreferred(String _one, String _two, StringMap<StringList> _map, Analyzable _an, Parametrable _p1, Parametrable _p2) {
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

    @Override
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

    @Override
    public final int getIndexInEl() {
        return indexInEl;
    }

    @Override
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
        setArgAna(this, _argument, _conf);
    }
    public static void setArgAna(Operable _op,Argument _argument, Analyzable _conf) {
        PossibleIntermediateDottedOperable n_ = _op.getSiblingSettable();
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

    @Override
    public PossibleIntermediateDottedOperable getSiblingSettable() {
        return getSiblingSet();
    }

    @Override
    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final void setResultClass(ClassArgumentMatching _resultClass) {
        resultClass = _resultClass;
    }

    public final void setStaticResultClass(ClassArgumentMatching _resultClass) {
        resultClass = _resultClass;
    }

    public PossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(PossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    @Override
    public final int getIndexBegin() {
        return operations.getDelimiter().getIndexBegin();
    }

    @Override
    public ParentOperable getParentOperable() {
        return getParent();
    }

}
