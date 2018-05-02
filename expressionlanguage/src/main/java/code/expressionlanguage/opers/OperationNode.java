package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.StaticAccessFieldError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UndefinedConstructorError;
import code.expressionlanguage.methods.util.UndefinedMethodError;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.opers.util.ArgumentsGroup;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdResult;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorInfo;
import code.expressionlanguage.opers.util.ConstructorMetaInfo;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.Fcts;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.Identifiable;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodInfo;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.ParametersGroup;
import code.expressionlanguage.opers.util.Parametrable;
import code.expressionlanguage.opers.util.Parametrables;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
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
    protected static final char DOT_VAR = '.';
    protected static final char EXTERN_CLASS = '$';
    protected static final char INTERN_CLASS = '$';
    protected static final String SUPER_ACCESS = "super";
    protected static final String CURRENT = "this";
    protected static final String INSTANCE = "new";
    protected static final String STATIC_ACCESS = "static";
    protected static final String INSTANCEOF = "instanceof";
    protected static final String BOOLEAN = "bool";
    protected static final String CAST = "class";
    protected static final char MIN_ENCODE_DIGIT = '0';
    protected static final char MAX_ENCODE_DIGIT = '9';
    protected static final char MIN_ENCODE_LOW_LETTER = 'a';
    protected static final char MAX_ENCODE_LOW_LETTER = 'f';
    protected static final char MIN_ENCODE_UPP_LETTER = 'A';
    protected static final char MAX_ENCODE_UPP_LETTER = 'F';
    protected static final String CURRENT_INTANCE = "$this";
    protected static final String STATIC_CALL = "$$";
    protected static final String VAR_ARG = "vararg";
    protected static final String FIRST_OPT = "firstopt";

    protected static final String CLASS_CHOICE_PREF = "$classchoice$";

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
    protected static final String RETURN_TAB = "\n\t";

    protected static final String VARARG_SUFFIX = "...";

    private static final int QUICK_OP = 3;

    private MethodOperation parent;

    private OperationNode nextSibling;

    private Argument argument;

    private OperationsSequence operations;

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private final int indexChild;

    private boolean vararg;
    private boolean firstOptArg;

    private ClassArgumentMatching resultClass;

    private boolean staticBlock;

    private PossibleIntermediateDotted siblingSet;

    OperationNode(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        parent = _m;
        indexInEl = _indexInEl;
        operations = _op;
        indexChild = _indexChild;
    }

    public abstract void analyze(Analyzable _conf, String _fieldName);

    public abstract void calculate(CustList<OperationNode> _nodes, ContextEl _conf, String _op);

    public abstract Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op);


    public final void setRelativeOffsetPossibleLastPage(int _offset, Analyzable _cont) {
        _cont.setOffset(operations.getDelimiter().getIndexBegin()+_offset);
    }

    public static OperationNode createOperationNode(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        if (_op.getOperators().isEmpty()) {
            if (_op.getConstType() == ConstType.STATIC_ACCESS) {
                return new StaticAccessOperation(_index, _indexChild, _m, _op);
            }
            return new ConstantOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.FCT_OPER_PRIO) {
            String fctName_ = _op.getFctName().trim();
            if (fctName_.isEmpty()) {
                return new IdOperation(_index, _indexChild, _m, _op);
            }
            if (fctName_.startsWith(prefixFunction(INSTANCE))) {
                return new InstanceOperation(_index, _indexChild, _m, _op);
            }
            return new FctOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.ARR_OPER_PRIO) {
            return new ArrOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.DOT_PRIO) {
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
            return new SemiAffectationOperation(_index, _indexChild, _m, _op, false);
        }
        if (_op.getPriority() == ElResolver.MULT_PRIO) {
            return new MultOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.ADD_PRIO) {
            return new AddOperation(_index, _indexChild, _m, _op);
        }
        if (_op.getPriority() == ElResolver.CMP_PRIO) {
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
            return new AffectationOperation(_index, _indexChild, _m, _op);
        }
        return null;
    }

    final boolean isFirstChild() {
        return getIndexChild() == CustList.FIRST_INDEX;
    }

    public abstract boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes);

    public abstract boolean isCalculated();

    public abstract boolean isPossibleInitClass();

    public final boolean isSuperThis() {
        return isSuperConstructorCall() || isOtherConstructorClass();
    }

    public abstract boolean isSuperConstructorCall();

    public abstract boolean isOtherConstructorClass();

    public abstract ConstructorId getConstId();

    public abstract OperationNode getFirstChild();

    public final OperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(OperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }
    public static boolean okType(ContextEl _cont, String _className) {
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        String glClass_ = _cont.getLastPage().getGlobalClass();
        if (glClass_ != null) {
            for (TypeVar t: Templates.getConstraints(glClass_, _cont)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        return Templates.correctClassParts(_className, map_, _cont);
    }

    final boolean checkCorrect(Analyzable _cont, String _className,boolean _setOffset, int _offset) {
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        String glClass_ = _cont.getGlobalClass();
        if (!isStaticBlock()) {
            for (TypeVar t: Templates.getConstraints(glClass_, _cont)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        if (!Templates.correctClassParts(_className, map_, _cont)) {
            if (_setOffset) {
                setRelativeOffsetPossibleLastPage(_offset, _cont);
            }
            UnknownClassName unknown_ = new UnknownClassName();
            unknown_.setClassName(_className);
            unknown_.setFileName(_cont.getCurrentFileName());
            unknown_.setRc(_cont.getCurrentLocation());
            _cont.getClasses().getErrorsDet().add(unknown_);
            return false;
        }
        return true;
    }
    final boolean checkExistBase(Analyzable _cont, boolean _allowVarTypes, String _className,boolean _setOffset, int _offset) {
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        if (_allowVarTypes) {
            String glClass_ = _cont.getGlobalClass();
            for (TypeVar t: Templates.getConstraints(glClass_, _cont)) {
                map_.put(t.getName(), t.getConstraints());
            }
        }
        if (!Templates.existClassParts(_className, map_, _cont)) {
            if (_setOffset) {
                setRelativeOffsetPossibleLastPage(_offset, _cont);
            }
            UnknownClassName unknown_ = new UnknownClassName();
            unknown_.setClassName(_className);
            unknown_.setFileName(_cont.getCurrentFileName());
            unknown_.setRc(_cont.getCurrentLocation());
            _cont.getClasses().getErrorsDet().add(unknown_);
            return false;
        }
        return true;
    }
    static FieldResult getDeclaredCustField(Analyzable _cont, boolean _staticContext, ClassArgumentMatching _class, boolean _baseClass, boolean _superClass, String _name) {
        if (!_staticContext) {
            FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _baseClass, _superClass, _name);
            if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
                return resIns_;
            }
        }
        FieldResult resSt_ = getDeclaredCustFieldByContext(_cont, true, _class, _baseClass, _superClass, _name);
        if (resSt_.getStatus() == SearchingMemberStatus.UNIQ) {
            return resSt_;
        }
        //Errors
        FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _baseClass, _superClass, _name);
        if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
            StaticAccessFieldError access_ = new StaticAccessFieldError();
            access_.setClassName(_class.getName());
            access_.setId(_name);
            access_.setFileName(_cont.getCurrentFileName());
            access_.setRc(_cont.getCurrentLocation());
            _cont.getClasses().getErrorsDet().add(access_);
            return resIns_;
        }
        resSt_ = new FieldResult();
        resSt_.setStatus(SearchingMemberStatus.ZERO);
        return resSt_;
    }
    private static FieldResult getDeclaredCustFieldByContext(Analyzable _cont, boolean _static, ClassArgumentMatching _class, boolean _baseClass, boolean _superClass, String _name) {
        String clCurName_ = _class.getName();
        String base_ = StringList.getAllTypes(clCurName_).first();
        GeneType root_ = _cont.getClassBody(base_);
        StringList classeNames_ = new StringList();
        if (_baseClass) {
            classeNames_.add(root_.getFullName());
        }
        String objectType_ = _cont.getStandards().getAliasObject();
        if (_superClass) {
            classeNames_.addAllElts(root_.getAllSuperTypes());
        }
        EqList<ClassField> fields_ = new EqList<ClassField>();
        String glClass_ = _cont.getGlobalClass();
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = StringList.getAllTypes(glClass_).first();
        }
        for (String s: classeNames_) {
            if (StringList.quickEq(s, objectType_)) {
                continue;
            }
            ClassMetaInfo custClass_;
            custClass_ = _cont.getClassMetaInfo(s);
            for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
                if (!StringList.quickEq(e.getKey(), _name)) {
                    continue;
                }
                if (_static) {
                    if (!e.getValue().isStaticField()) {
                        continue;
                    }
                } else {
                    if (e.getValue().isStaticField()) {
                        continue;
                    }
                }
                if (!Classes.canAccessField(curClassBase_, s, _name, _cont)) {
                    continue;
                }
                fields_.add(new ClassField(s, _name));
            }
        }
        if (fields_.isEmpty()) {
            FieldResult r_ = new FieldResult();
            r_.setStatus(SearchingMemberStatus.ZERO);
            return r_;
        }
        String cl_ = fields_.first().getClassName();
        StringList subClasses_ = new StringList();
        for (ClassField c: fields_) {
            subClasses_.add(c.getClassName());
        }
        StringList subs_ = PrimitiveTypeUtil.getSubclasses(subClasses_, _cont);
        if (subs_.size() != CustList.ONE_ELEMENT) {
            FieldResult r_ = new FieldResult();
            r_.setStatus(SearchingMemberStatus.ZERO);
            return r_;
        }
        cl_ = subs_.first();
        String formatted_ = Templates.getFullTypeByBases(clCurName_, cl_, _cont);
        ClassMetaInfo custClass_;
        custClass_ = _cont.getClassMetaInfo(cl_);
        FieldMetaInfo field_ = custClass_.getFields().getVal(_name);
        FieldResult r_ = new FieldResult();
        String formattedType_ = field_.getType();
        String realType_ = formattedType_;
        formattedType_ = Templates.generalFormat(formatted_, formattedType_, _cont);
        FieldInfo f_ = new FieldInfo(_name, formatted_, formattedType_, realType_, _static, field_.isFinalField());
        r_.setId(f_);
        r_.setStatus(SearchingMemberStatus.UNIQ);
        return r_;
    }
    
    static ConstrustorIdVarArg getDeclaredCustConstructor(Analyzable _conf, int _varargOnly, ClassArgumentMatching _class,
    ClassArgumentMatching... _args) {
        ClassMetaInfo custClass_ = null;
        String clCurName_ = _class.getName();
        custClass_ = _conf.getClassMetaInfo(clCurName_);
        if (custClass_ == null) {
            return null;
        }
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
                _conf.getClasses().getErrorsDet().add(cast_);
            }
        }
        ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> constructors_;
        constructors_ = custClass_.getConstructors();
        if (constructors_.isEmpty()) {
            if (_args.length == 0) {
                ConstrustorIdVarArg out_;
                out_ = new ConstrustorIdVarArg();
                out_.setRealId(new ConstructorId(clCurName_, new EqList<ClassName>()));
                out_.setConstId(out_.getRealId());
                return out_;
            }
        }
        for (EntryCust<ConstructorId, ConstructorMetaInfo> e: constructors_.entryList()) {
            ConstructorId ctor_ = e.getKey();
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
            EqList<ClassName> classesNames_ = new EqList<ClassName>();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(new ClassName(c.getName(),false));
            }
            UndefinedConstructorError undefined_ = new UndefinedConstructorError();
            undefined_.setClassName(clCurName_);
            undefined_.setId(new ConstructorId(clCurName_, classesNames_));
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(undefined_);
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
            EqList<ClassName> classesNames_ = new EqList<ClassName>();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(new ClassName(c.getName(),false));
            }
            UndefinedConstructorError undefined_ = new UndefinedConstructorError();
            undefined_.setClassName(clCurName_);
            undefined_.setId(new ConstructorId(clCurName_, classesNames_));
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(undefined_);
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
        return out_;
    }

    static ClassMethodIdReturn getDeclaredCustMethod(Analyzable _conf, int _varargOnly,
    boolean _staticContext, StringList _classes, String _name,
    boolean _superClass, boolean _accessFromSuper, ClassArgumentMatching... _argsClass) {
        LgNames stds_ = _conf.getStandards();
        for (ClassArgumentMatching c:_argsClass) {
            if (c.matchVoid(_conf)) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(stds_.getAliasVoid());
                mapping_.setParam(stds_.getAliasObject());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(cast_);
            }
        }
        ObjectNotNullMap<ClassMethodId, MethodMetaInfo> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _staticContext,_varargOnly, _accessFromSuper, _superClass, _classes, _name, _argsClass);
        ClassMethodIdResult res_= getCustResult(_conf, _varargOnly, methods_, _name, _argsClass);
        if (res_.getStatus() == SearchingMemberStatus.UNIQ) {
            return toFoundMethod(_conf, res_);
        }
        //Error
        if (_conf.isAmbigous()) {
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getName());
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
            _conf.getClasses().getErrorsDet().add(undefined_);
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
            classesNames_.add(c.getName());
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
        _conf.getClasses().getErrorsDet().add(undefined_);
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
    private static ObjectNotNullMap<ClassMethodId, MethodMetaInfo>
    getDeclaredCustMethodByType(Analyzable _conf, boolean _staticContext, int _varargOnly, boolean _accessFromSuper,
        boolean _superClass, StringList _fromClasses, String _name, ClassArgumentMatching... _argsClass) {
        LgNames stds_ = _conf.getStandards();
        String glClass_ = _conf.getGlobalClass();
        CustList<GeneType> roots_ = new CustList<GeneType>();
        ObjectNotNullMap<ClassMethodId, MethodMetaInfo> methods_;
        methods_ = new ObjectNotNullMap<ClassMethodId, MethodMetaInfo>();
        StringList superTypes_ = new StringList();
        for (String s: _fromClasses) {
            String baseCurName_ = StringList.getAllTypes(s).first();
            superTypes_.add(baseCurName_);
            GeneType root_ = _conf.getClassBody(baseCurName_);
            roots_.add(root_);
            superTypes_.addAllElts(root_.getAllSuperTypes());
        }
        for (String t: superTypes_) {
            GeneType root_ = _conf.getClassBody(t);
            for (GeneMethod e: ContextEl.getMethodBlocks(root_)) {
                if (!Classes.canAccess(glClass_, e, _conf)) {
                    continue;
                }
                if (e.isStaticMethod()) {
                    MethodId id_ = e.getId();
                    String returnType_ = e.getReturnType(stds_);
                    MethodMetaInfo info_ = new MethodMetaInfo(t, id_, MethodModifier.STATIC, returnType_);
                    ClassMethodId clId_ = new ClassMethodId(t, id_);
                    methods_.put(clId_, info_);
                }
            }
        }
        if (!_staticContext){
            int indexType_ = 0;
            for (GeneType t: roots_) {
                String clCurName_ = _fromClasses.get(indexType_);
                for (EntryCust<MethodId, EqList<ClassMethodId>> e: t.getAllOverridingMethods().entryList()) {
                    for (ClassMethodId s: e.getValue()) {
                        String name_ = s.getClassName();
                        if (_accessFromSuper) {
                            String base_ = StringList.getAllTypes(name_).first();
                            if (StringList.quickEq(base_, t.getFullName())) {
                                continue;
                            }
                        }
                        String formattedClass_;
                        if (_superClass) {
                            formattedClass_ = Templates.getFullTypeByBases(clCurName_, name_, _conf);
                        } else {
                            String base_ = StringList.getAllTypes(name_).first();
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
                        String ret_ = sup_.getReturnType(stds_);
                        ret_ = Templates.generalFormat(formattedClass_, ret_, _conf);
                        MethodMetaInfo info_ = new MethodMetaInfo(formattedClass_, id_, MethodModifier.NORMAL, ret_);
                        ClassMethodId clId_ = new ClassMethodId(formattedClass_, id_);
                        methods_.put(clId_, info_);
                    }
                }
                indexType_++;
            }
        }
        return methods_;
    }
    private static ClassMethodIdResult getCustResult(Analyzable _conf, int _varargOnly,
            ObjectNotNullMap<ClassMethodId, MethodMetaInfo> _methods,
            String _name, ClassArgumentMatching... _argsClass) {
        CustList<ClassMethodId> possibleMethods_ = new CustList<ClassMethodId>();
        String glClass_ = _conf.getGlobalClass();
        for (EntryCust<ClassMethodId, MethodMetaInfo> e: _methods.entryList()) {
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
            ParametersGroup p_ = new ParametersGroup();
            MethodMetaInfo info_ = _methods.getVal(m);
            MethodId realId_ = info_.getRealId();
            for (String c: realId_.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            MethodInfo mloc_ = new MethodInfo();
            String formattedType_ = info_.getClassName();
            mloc_.setClassName(formattedType_);
            mloc_.setStatic(info_.getModifier() == MethodModifier.STATIC);
            mloc_.setConstraints(realId_);
            mloc_.setParameters(p_);
            mloc_.setReturnType(info_.getReturnType());
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
        MethodId realId_ = constraints_;
        String className_ = signatures_.first().getClassName();
        MethodMetaInfo info_ = _methods.getVal(new ClassMethodId(className_, realId_));
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
            if (info_.getModifier() == MethodModifier.STATIC) {
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
            map_.setArg(_argsClass[i].getName());
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
            map_.setArg(_argsClass[last_].getName());
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
            map_.setArg(_argsClass[i].getName());
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
        map_.setArg(_argsClass[last_].getName());
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
        String baseTypeOne_ = StringList.getAllTypes(glClassOne_).first();
        String baseTypeTwo_ = StringList.getAllTypes(glClassTwo_).first();
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

    final void setNextSiblingsArg(Argument _arg, ContextEl _cont) {
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

    final int processBooleanValues(Argument _arg, ContextEl _cont) {
        Object o_ = _arg.getObject();
        MethodOperation par_ = getParent();
        LgNames stds_ = _cont.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (o_ == null) {
            if (par_ instanceof QuickOperation) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                return -1;
            }
            boolean ternaryParent_ = false;
            if (par_ instanceof FctOperation) {
                FctOperation op_ = (FctOperation) par_;
                ternaryParent_ = op_.isTernary() && isFirstChild();
            }
            if (ternaryParent_) {
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                return -1;
            }
            return 0;
        }
        if (!(o_ instanceof Boolean)) {
            return 0;
        }
        if (!(par_ instanceof QuickOperation)) {
            boolean ternaryParent_ = false;
            if (par_ instanceof FctOperation) {
                FctOperation op_ = (FctOperation) par_;
                ternaryParent_ = op_.isTernary() && isFirstChild();
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

    public final Argument getArgument() {
        return argument;
    }

    public final void setSimpleArgument(Argument _argument) {
        argument = _argument;
    }

    public final void setArguments(Argument _argument) {
        argument = _argument;
    }

    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes) {
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getVal((OperationNode)n_).setPreviousArgument(_argument);
        }
        setNextSiblingsArg(_argument, _conf, _nodes);
    }

    public final void setSimpleArgument(Argument _argument, ContextEl _conf) {
        argument = _argument;
        PossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
        setNextSiblingsArg(_argument, _conf);
    }

    public final boolean isStaticBlock() {
        return staticBlock;
    }

    public final void setStaticBlock(boolean _staticBlock) {
        staticBlock = _staticBlock;
    }

    public final boolean isVoidArg(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        return StringList.quickEq(resultClass.getName(), stds_.getAliasVoid());
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
