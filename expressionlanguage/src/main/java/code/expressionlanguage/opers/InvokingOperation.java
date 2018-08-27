package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.Initializer;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundAnnotation;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.CustomReflectMethod;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ReflectingType;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class InvokingOperation extends MethodOperation implements PossibleIntermediateDotted {
    private ClassArgumentMatching previousResultClass;
    private boolean staticAccess;
    private boolean intermediate;

    private Argument previousArgument;

    public InvokingOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    CustList<ClassArgumentMatching> listClasses(CustList<OperationNode> _children, Analyzable _conf) {
        if (!_children.isEmpty() && _children.first() instanceof VarargOperation) {
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            CustList<ClassArgumentMatching> optArgs_ = new CustList<ClassArgumentMatching>();
            CustList<OperationNode> optArgsNodes_ = new CustList<OperationNode>();
            boolean opt_ = false;
            for (OperationNode o: _children) {
                if (o instanceof VarargOperation) {
                    continue;
                }
                if (o instanceof FirstOptOperation) {
                    opt_ = true;
                }
                if (opt_) {
                    optArgs_.add(o.getResultClass());
                    optArgsNodes_.add(o);
                } else {
                    firstArgs_.add(o.getResultClass());
                }
            }
            ClassArgumentMatching name_ = _children.first().getResultClass();
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            String glClass_ = _conf.getGlobalClass();
            if (glClass_ != null) {
                for (TypeVar t: Templates.getConstraints(glClass_, _conf)) {
                    map_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setParam(name_);
            for (OperationNode o: optArgsNodes_) {
                setRelativeOffsetPossibleAnalyzable(o.getIndexInEl(), _conf);
                ClassArgumentMatching argType_ = o.getResultClass();
                if (argType_.isVariable()) {
                    if (PrimitiveTypeUtil.isPrimitive(name_, _conf)) {
                        mapping_.setMapping(map_);
                        BadImplicitCast cast_ = new BadImplicitCast();
                        cast_.setMapping(mapping_);
                        cast_.setFileName(_conf.getCurrentFileName());
                        cast_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(cast_);
                    }
                    continue;
                }
                mapping_.setArg(argType_);
                mapping_.setMapping(map_);
                if (!Templates.isGenericCorrect(mapping_, _conf)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setRc(_conf.getCurrentLocation());
                    _conf.getClasses().addError(cast_);
                }
                if (PrimitiveTypeUtil.isPrimitive(name_, _conf)) {
                    o.getResultClass().setUnwrapObject(name_);
                }
            }
            name_ = PrimitiveTypeUtil.getPrettyArrayType(name_);
            ClassArgumentMatching clMatch_ = name_;
            firstArgs_.add(clMatch_);
            return firstArgs_;
        }
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getResultClass());
        }
        return firstArgs_;
    }

    static CustList<Argument> listArguments(CustList<OperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes, ExecutableCode _context) {
        if (!_children.isEmpty() && _children.first() instanceof VarargOperation) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            int i_ = CustList.FIRST_INDEX;
            for (OperationNode o: _children) {
                if (o instanceof VarargOperation) {
                    i_++;
                    continue;
                }
                if (o instanceof FirstOptOperation) {
                    opt_ = true;
                }
                Argument a_ = _nodes.get(i_);
                if (opt_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
                i_++;
            }
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getNames().first();
            g_ = _context.getOperationPageEl().formatVarType(g_, _context);
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
            Struct str_ = new ArrayStruct(array_,clArr_);
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                Argument chArg_ = optArgs_.get(i);
                ArrOperation.setCheckedElement(str_, i, chArg_, _context);
                if (_context.getException() != null) {
                    return firstArgs_;
                }
            }
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                Argument a_ = _nodes.get(i);
                if (i >= _natVararg) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
            }
            Argument argRem_ = new Argument();
            String g_ = _lastType;
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
            Struct str_ = new ArrayStruct(array_,clArr_);
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                Argument chArg_ = optArgs_.get(i);
                ArrOperation.setCheckedElement(str_, i, chArg_, _context);
                if (_context.getException() != null) {
                    return firstArgs_;
                }
            }
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>(_nodes);
        return firstArgs_;
    }
    static CustList<Argument> quickListArguments(CustList<OperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes, Analyzable _context) {
        if (!_children.isEmpty() && _children.first() instanceof VarargOperation) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            int i_ = CustList.FIRST_INDEX;
            for (OperationNode o: _children) {
                if (o instanceof VarargOperation) {
                    i_++;
                    continue;
                }
                if (o instanceof FirstOptOperation) {
                    opt_ = true;
                }
                Argument a_ = _nodes.get(i_);
                if (opt_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
                i_++;
            }
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getNames().first();
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                Argument chArg_ = optArgs_.get(i);
                if (!setCheckedElement(str_, i, chArg_, _context)) {
                    return null;
                }
            }
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                Argument a_ = _nodes.get(i);
                if (i >= _natVararg) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
            }
            Argument argRem_ = new Argument();
            String g_ = _lastType;
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                Argument chArg_ = optArgs_.get(i);
                if (!setCheckedElement(str_, i, chArg_, _context)) {
                    return null;
                }
            }
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>(_nodes);
        return firstArgs_;
    }
    static boolean setCheckedElement(ArrayStruct _array,Object _index, Argument _element, Analyzable _conf) {
        String base_ = PrimitiveTypeUtil.getQuickComponentType(_array.getClassName());
        if (PrimitiveTypeUtil.primitiveTypeNullObject(base_, _element.getStruct(), _conf.getStandards())) {
            return false;
        }
        return setElement(_array, _index, _element.getStruct(), _conf);
    }
    static boolean setElement(ArrayStruct _struct, Object _index, Struct _value, Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        if (_struct.isNull()) {
            return false;
        }
        if (_index == null) {
            return false;
        }
        String strClass_ = _struct.getClassName();
        String valClass_;
        if (_value.isArray()) {
            if (_value instanceof StdStruct) {
                valClass_ = ((StdStruct)_value).getClassName();
            } else {
                valClass_ = ((ArrayStruct)_value).getClassName();
            }
        } else {
            valClass_ = stds_.getSimpleStructClassName(_value.getInstance());
        }
        Struct[] instance_ = _struct.getInstance();
        int len_ = instance_.length;
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            return false;
        }
        if (!_value.isNull()) {
            String componentType_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
            String elementType_ = valClass_;
            Mapping mapping_ = new Mapping();
            mapping_.setArg(elementType_);
            mapping_.setParam(componentType_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                return false;
            }
        }
        Struct value_;
        if (_value instanceof NumberStruct || _value instanceof CharStruct) {
            String componentType_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(componentType_);
            value_ = PrimitiveTypeUtil.convertObject(cl_, _value, _conf);
        } else {
            value_ = _value;
        }
        instance_[index_] = value_;
        return true;
    }
    static StringList getBounds(String _cl, Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        String objectClassName_ = stds_.getAliasObject();
        StringList bounds_ = new StringList();
        if (_cl.startsWith(Templates.PREFIX_VAR_TYPE)) {
            String glClass_ = _conf.getGlobalClass();
            String curClassBase_ = Templates.getIdFromAllTypes(glClass_);
            GeneType gl_ = _conf.getClassBody(curClassBase_);
            StringMap<StringList> mapping_ = new StringMap<StringList>();
            for (TypeVar t: gl_.getParamTypesMapValues()) {
                mapping_.put(t.getName(), t.getConstraints());
            }
            bounds_.addAllElts(Mapping.getAllUpperBounds(mapping_, _cl.substring(1), objectClassName_));
        } else {
            bounds_.add(_cl);
        }
        return bounds_;
    }
    final boolean hasVoidPrevious(String _cl, Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        if (StringList.quickEq(_cl, stds_.getAliasVoid())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(_cl);
            mapping_.setParam(stds_.getAliasObject());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return true;
        }
        return false;
    }
    final boolean hasVoidArguments(CustList<OperationNode> _children,CustList<ClassArgumentMatching> _args, int _off, Analyzable _conf) {
        int indexChild_ = -1;
        LgNames stds_ = _conf.getStandards();
        boolean void_ = false;
        for (ClassArgumentMatching c:_args) {
            indexChild_++;
            if (c.matchVoid(_conf)) {
                void_ = true;
                if (indexChild_ < _children.size()) {
                    OperationNode op_ = _children.get(indexChild_);
                    op_.setRelativeOffsetPossibleAnalyzable(op_.getIndexInEl()+_off, _conf);
                } else {
                    setRelativeOffsetPossibleAnalyzable(getIndexInEl()+_off, _conf);
                }
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
        return void_;
    }
    static void unwrapArgsFct(CustList<OperationNode> _ch, MethodId _id, int _natvararg, String _lasttype, CustList<ClassArgumentMatching> _args, Analyzable _conf) {
        if (!_ch.isEmpty() && _ch.first() instanceof VarargOperation) {
            int i_ = CustList.FIRST_INDEX;
            for (OperationNode o: _ch) {
                if (o instanceof VarargOperation) {
                    i_++;
                    continue;
                }
                if (o instanceof FirstOptOperation) {
                    break;
                }
                String param_ = _id.getParametersTypes().get(i_-1);
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    o.getResultClass().setUnwrapObject(param_);
                }
                i_++;
            }
        } else if (_natvararg > -1) {
            int lenCh_ = _args.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _args.get(i);
                if (i >= _natvararg) {
                    if (PrimitiveTypeUtil.isPrimitive(_lasttype, _conf)) {
                        a_.setUnwrapObject(_lasttype);
                    }
                } else {
                    String param_ = _id.getParametersTypes().get(i);
                    if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                        a_.setUnwrapObject(param_);
                    }
                }
            }
        } else {
            int lenCh_ = _args.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _args.get(i);
                String param_ = _id.getParametersTypes().get(i);
                if (i + 1 == lenCh_ && _id.isVararg()) {
                    param_ = PrimitiveTypeUtil.getPrettyArrayType(param_);
                }
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    a_.setUnwrapObject(param_);
                }
            }
        }
    }
    final int lookOnlyForVarArg() {
        OperationNode first_ = getFirstChild();
        int from_ = 1;
        if (first_ == null) {
            return -1;
        }
        if (first_ instanceof StaticInitOperation) {
            from_++;
            first_ = first_.getNextSibling();
            if (first_ == null) {
                return -1;
            }
        }
        if (!(first_ instanceof VarargOperation)) {
            return -1;
        }
        CustList<OperationNode> ch_ = getChildrenNodes();
        int firstOpt_ = 0;
        int len_ = ch_.size();
        for (int i = from_; i < len_;i++) {
            if (ch_.get(i) instanceof FirstOptOperation) {
                firstOpt_ = i + 1 - from_;
                break;
            }
        }
        return firstOpt_;
    }
    @Override
    public final void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }

    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }
    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass) {
        setPreviousResultClass(_previousResultClass, false);
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
        staticAccess = _staticAccess;
    }

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public final boolean isStaticAccess() {
        return staticAccess;
    }

    public final void setStaticAccess(boolean _staticAccess) {
        staticAccess = _staticAccess;
    }
    abstract boolean isCallMethodCtor();
    public static boolean hasToExit(ExecutableCode _conf, String _className) {
        Classes classes_ = _conf.getClasses();
        if (classes_.isCustomType(_className)) {
            InitClassState res_ = classes_.getLocks().getState(_conf.getContextEl(), _className);
            if (res_ == InitClassState.NOT_YET) {
                _conf.getContextEl().setInitClass(new NotInitializedClass(_className));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(_className);
                _conf.setException(causing_);
                return true;
            }
        }
        return false;
    }
    public static Argument instancePrepare(ExecutableCode _conf, String _className, ConstructorId _constId, Argument _previous, CustList<Argument> _arguments) {
        return instancePrepare(_conf, _className, _constId, _previous, _arguments, "", -1, false);
    }
    public static Argument instancePrepare(ExecutableCode _conf, String _className, ConstructorId _constId, Argument _previous, CustList<Argument> _arguments, String _fieldName, int _blockIndex, boolean _format) {
        if (_conf.getException() != null) {
            Argument a_ = new Argument();
            return a_;
        }
        LgNames stds_ = _conf.getStandards();
        String idCl_ = Templates.getIdFromAllTypes(_className);
        GeneType g_ = _conf.getClassBody(idCl_);
        Argument needed_ = new Argument();
        if (g_ instanceof RootBlock) {
            RootBlock r_ = (RootBlock) g_;
            if (!r_.isStaticType()) {
                if (_previous.isNull()) {
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),stds_.getAliasNullPe()));
                    Argument a_ = new Argument();
                    return a_;
                }
                needed_.setStruct(_previous.getStruct());
            }
        }
        String base_ = Templates.getIdFromAllTypes(_className);
        if (!_conf.getClasses().isCustomType(base_)) {
            ResultErrorStd res_ = LgNames.newInstance(_conf.getContextEl(), _constId, Argument.toArgArray(_arguments));
            if (_conf.getException() != null) {
                Argument a_ = new Argument();
                return a_;
            }
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                Argument a_ = new Argument();
                return a_;
            }
            Argument arg_ = new Argument();
            arg_.setStruct(res_.getResult());
            return arg_;
        }
        String className_ = _className;
        PageEl page_ = _conf.getOperationPageEl();
        if (_format) {
            className_ = page_.formatVarType(className_, _conf);
        }
        StringList params_ = new StringList();
        int j_ = 0;
        for (String c: _constId.getParametersTypes()) {
            String class_ = c;
            class_ = Templates.format(className_, class_, _conf);
            if (j_ + 1 == _constId.getParametersTypes().size() && _constId.isVararg()) {
                class_ = PrimitiveTypeUtil.getPrettyArrayType(class_);
            }
            params_.add(class_);
            j_++;
        }
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: _arguments) {
            if (i_ < params_.size()) {
                Struct str_ = a.getStruct();
                if (!str_.isNull()) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(a.getObjectClassName(_conf.getContextEl()));
                    mapping_.setParam(params_.get(i_));
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        String cast_;
                        cast_ = stds_.getAliasCast();
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                        Argument a_ = new Argument();
                        return a_;
                    }
                } else if (PrimitiveTypeUtil.primitiveTypeNullObject(params_.get(i_), a.getStruct(), _conf)){
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),stds_.getAliasNullPe()));
                    Argument a_ = new Argument();
                    return a_;
                }
            }
            i_++;
        }
        _conf.getContextEl().setCallCtor(new CustomFoundConstructor(className_, _fieldName, _blockIndex,_constId, needed_, _arguments, InstancingStep.NEWING));
        return Argument.createVoid();
    }
    public static Argument instancePrepareAnnotation(ExecutableCode _conf, String _className, StringMap<String> _fieldNames,CustList<Argument> _arguments) {
        if (_conf.getException() != null) {
            Argument a_ = new Argument();
            return a_;
        }
        String className_ = _className;
        _conf.getContextEl().setCallAnnot(new CustomFoundAnnotation(className_, _fieldNames, _arguments));
        return Argument.createVoid();
    }
    public static ClassMethodId polymorph(ContextEl _conf, Struct _previous, ClassMethodId _classMethodId) {
        String classNameFound_ = _classMethodId.getClassName();
        classNameFound_ = Templates.getIdFromAllTypes(classNameFound_);
        String argClassName_ = _conf.getStandards().getStructClassName(_previous, _conf);
        argClassName_ = Templates.getGenericString(argClassName_, _conf);
        String base_ = Templates.getIdFromAllTypes(argClassName_);
        MethodId id_ = _classMethodId.getConstraints();
        MethodId methodId_;
        if (_conf.getMethodBodiesById(classNameFound_, id_).first().isFinalMethod()) {
            classNameFound_ = _classMethodId.getClassName();
            methodId_ = id_;
        } else {
            GeneType info_ = _conf.getClassBody(classNameFound_);
            StringMap<ClassMethodId> overriding_ = TypeUtil.getConcreteMethodsToCall(info_,id_, _conf.getContextEl());
            if (overriding_.contains(base_)) {
                ClassMethodId res_ = overriding_.getVal(base_);
                classNameFound_ = res_.getClassName();
                methodId_ = res_.getConstraints();
            } else {
                classNameFound_ = _classMethodId.getClassName();
                methodId_ = id_;
            }
        }
        return new ClassMethodId(classNameFound_, methodId_);
    }
    public static Argument callPrepare(ExecutableCode _conf, String _classNameFound, MethodId _methodId, Argument _previous, CustList<Argument> _firstArgs, int _possibleOffset) {
        StringList params_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        if (!_methodId.isStaticMethod()) {
            String className_ = stds_.getStructClassName(_previous.getStruct(), _conf.getContextEl());
            String classFormat_ = _classNameFound;
            classFormat_ = Templates.getFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_ == null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                Argument a_ = new Argument();
                return a_;
            }
            int i_ = 0;
            for (String c: _methodId.getParametersTypes()) {
                String c_ = c;
                c_ = Templates.format(classFormat_, c_, _conf);
                if (i_ + 1 == _methodId.getParametersTypes().size() && _methodId.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        } else {
            int i_ = 0;
            for (String c: _methodId.getParametersTypes()) {
                String c_ = c;
                if (i_ + 1 == _methodId.getParametersTypes().size() && _methodId.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        }
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: _firstArgs) {
            if (i_ < params_.size()) {
                Struct str_ = a.getStruct();
                if (!str_.isNull()) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(a.getObjectClassName(_conf.getContextEl()));
                    mapping_.setParam(params_.get(i_));
                    if (!Templates.isCorrect(mapping_, _conf)) {
                        if (_possibleOffset > -1) {
                            _conf.setOffset(_possibleOffset);
                        }
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                        Argument a_ = new Argument();
                        return a_;
                    }
                } else if (PrimitiveTypeUtil.primitiveTypeNullObject(params_.get(i_), a.getStruct(), _conf)){
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),stds_.getAliasNullPe()));
                    Argument a_ = new Argument();
                    return a_;
                }
            }
            i_++;
        }
        if (_conf.getException() != null) {
            return Argument.createVoid();
        }
        if (!StringList.isWord(_methodId.getName())) {
            _conf.getContextEl().setCallMethod(new CustomFoundMethod(_previous, _classNameFound, _methodId, _firstArgs));
            return Argument.createVoid();
        }
        Classes classes_ = _conf.getClasses();
        String aliasClass_ = stds_.getAliasClass();
        String aliasForName_ = stds_.getAliasForName();
        String aliasValueOf_ = stds_.getAliasEnumValueOf();
        String aliasEnumsValues_ = stds_.getAliasGetEnumConstants();
        String aliasDefaultInstance_ = stds_.getAliasDefaultInstance();
        String aliasInit_ = stds_.getAliasInit();
        String aliasAnnotated_ = stds_.getAliasAnnotated();
        String aliasAnnotation_ = stds_.getAliasAnnotation();
        String aliasGetAnnotations_ = stds_.getAliasGetAnnotations();
        String aliasGetAnnotationsParam_ = stds_.getAliasGetAnnotationsParameters();
        if (!_methodId.isStaticMethod()) {
            String clName_ = _previous.getObjectClassName(_conf.getContextEl());
            if (_previous.getStruct().isArray()) {
                //clone object
                Struct ret_ =_previous.getStruct();
                Argument a_ = new Argument();
                Object arr_ = ret_.getInstance();
                int len_ = LgNames.getLength(arr_);
                String clNameOut_ = ret_.getClassName(_conf);
                ArrayStruct copy_ = new ArrayStruct(new Struct[len_], clNameOut_);
                for (int i = 0; i < len_; i++) {
                    copy_.getInstance()[i] = LgNames.getElement(arr_, i, _conf.getContextEl());
                }
                a_.setStruct(copy_);
                return a_;
            }
            if (PrimitiveTypeUtil.canBeUseAsArgument(aliasAnnotation_, clName_, _conf)) {
                FieldableStruct f_ = (FieldableStruct) _previous.getStruct();
                Struct ret_ = f_.getStruct(new ClassField(clName_, _methodId.getName()));
                Argument a_ = new Argument();
                if (ret_ instanceof ArrayStruct) {
                    ArrayStruct orig_ = (ArrayStruct) ret_;
                    Struct[] arr_ = orig_.getInstance();
                    int len_ = arr_.length;
                    ArrayStruct copy_ = new ArrayStruct(new Struct[len_], orig_.getClassName());
                    for (int i = 0; i < len_; i++) {
                        copy_.getInstance()[i] = arr_[i];
                    }
                    a_.setStruct(copy_);
                } else {
                    a_.setStruct(ret_);
                }
                return a_;
            }
        }
        if (PrimitiveTypeUtil.canBeUseAsArgument(aliasAnnotated_, _classNameFound, _conf)) {
            if (StringList.quickEq(aliasGetAnnotations_, _methodId.getName())) {
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.ANNOTATION, _previous, _firstArgs));
                Argument a_ = new Argument();
                return a_;
            }
            if (StringList.quickEq(aliasGetAnnotationsParam_, _methodId.getName())) {
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.ANNOTATION_PARAM, _previous, _firstArgs));
                Argument a_ = new Argument();
                return a_;
            }
        }
        if (StringList.quickEq(aliasClass_, _classNameFound)) {
            if (StringList.quickEq(aliasValueOf_, _methodId.getName())) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _previous.getStruct();
                if (!cl_.isEnum()) {
                    Argument a_ = new Argument();
                    return a_;
                }
                Argument clArg_ = _firstArgs.first();
                String enumName_ = cl_.getName();
                enumName_ = Templates.getIdFromAllTypes(enumName_);
                return getEnumValue(enumName_, clArg_, _conf);
            }
            if (StringList.quickEq(aliasEnumsValues_, _methodId.getName())) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _previous.getStruct();
                if (!cl_.isEnum()) {
                    Argument a_ = new Argument();
                    return a_;
                }
                String enumName_ = cl_.getName();
                return getEnumValues(enumName_, _conf);
            }
            if (StringList.quickEq(aliasForName_, _methodId.getName())) {
                Argument clArg_ = _firstArgs.first();
                if (clArg_.isNull()) {
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),stds_.getAliasNullPe()));
                    Argument a_ = new Argument();
                    return a_;
                }
                String clDyn_ = (String) clArg_.getObject();
                if (StringList.quickEq(clDyn_.trim(), _conf.getStandards().getAliasVoid())) {
                    Argument a_ = new Argument();
                    a_.setStruct(_conf.getExtendedClassMetaInfo(clDyn_));
                    return a_;
                }
                Boolean init_ = (Boolean) _firstArgs.last().getObject();
                boolean gene_ = clDyn_.contains(Templates.TEMPLATE_BEGIN);
                if (!Templates.correctClassPartsDynamic(clDyn_, _conf, gene_)) {
                    _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),stds_.getAliasClassNotFoundError()));
                    Argument a_ = new Argument();
                    return a_;
                }
                if (init_) {
                    if (hasToExit(_conf, clDyn_)) {
                        return Argument.createVoid();
                    }
                }
                Argument a_ = new Argument();
                a_.setStruct(_conf.getExtendedClassMetaInfo(clDyn_));
                return a_;
            }
            if (StringList.quickEq(aliasDefaultInstance_, _methodId.getName())) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _previous.getStruct();
                String className_ = cl_.getName();
                ContextEl cont_ = _conf.getContextEl();
                String id_ = Templates.getIdFromAllTypes(className_);
                GeneType type_ = cont_.getClassBody(id_);
                if (type_.isAbstractType()) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    cont_.setException(new StdStruct(new CustomError(cont_.joinPages()),null_));
                    return Argument.createVoid();
                }
                if (!Templates.correctClassPartsDynamic(className_, cont_, true)) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    cont_.setException(new StdStruct(new CustomError(cont_.joinPages()),null_));
                    return Argument.createVoid();
                }
                if (InvokingOperation.hasToExit(_conf, className_)) {
                    return Argument.createVoid();
                }
                Initializer in_ = cont_.getInit();
                Struct struct_ = in_.processInit(cont_, NullStruct.NULL_VALUE, className_, EMPTY_STRING, 0);
                Argument a_ = new Argument();
                a_.setStruct(struct_);
                return a_;
            }
            if (StringList.quickEq(aliasInit_, _methodId.getName())) {
                ClassMetaInfo cl_ = (ClassMetaInfo) _previous.getStruct();
                String clDyn_ = cl_.getName();
                hasToExit(_conf, clDyn_);
                return Argument.createVoid();
            }
        }
        String aliasField_ = stds_.getAliasField();
        String aliasMethod_ = stds_.getAliasMethod();
        String aliasConstructor_ = stds_.getAliasConstructor();
        String aliasGetField_ = stds_.getAliasGetField();
        String aliasSetField_ = stds_.getAliasSetField();
        String aliasInvoke_ = stds_.getAliasInvoke();
        String aliasGetDefaultValue_ = stds_.getAliasGetDefaultValue();
        String aliasNewInstance_ = stds_.getAliasNewInstance();
        if (StringList.quickEq(aliasMethod_, _classNameFound)) {
            if (StringList.quickEq(aliasGetDefaultValue_, _methodId.getName())) {
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.DEFAULT_VALUE, _previous, _firstArgs));
                Argument a_ = new Argument();
                return a_;
            }
            if (StringList.quickEq(aliasInvoke_, _methodId.getName())) {
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.METHOD, _previous, _firstArgs));
                Argument a_ = new Argument();
                return a_;
            }
        }
        if (StringList.quickEq(aliasConstructor_, _classNameFound)) {
            if (StringList.quickEq(aliasNewInstance_, _methodId.getName())) {
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.CONSTRUCTOR, _previous, _firstArgs));
                Argument a_ = new Argument();
                return a_;
            }
        }
        if (StringList.quickEq(aliasField_, _classNameFound)) {
            if (StringList.quickEq(aliasGetField_, _methodId.getName())) {
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.GET_FIELD, _previous, _firstArgs));
                Argument a_ = new Argument();
                return a_;
            }
            if (StringList.quickEq(aliasSetField_, _methodId.getName())) {
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.SET_FIELD, _previous, _firstArgs));
                Argument a_ = new Argument();
                return a_;
            }
        }
        if (!classes_.isCustomType(_classNameFound)) {
            ClassMethodId dyn_ = new ClassMethodId(_classNameFound, _methodId);
            ResultErrorStd res_ = LgNames.invokeMethod(_conf.getContextEl(), dyn_, _previous.getStruct(), Argument.toArgArray(_firstArgs));
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                Argument a_ = new Argument();
                return a_;
            }
            Argument argRes_ = new Argument();
            argRes_.setStruct(res_.getResult());
            return argRes_;
        }
        _conf.getContextEl().setCallMethod(new CustomFoundMethod(_previous, _classNameFound, _methodId, _firstArgs));
        return Argument.createVoid();
    }

    public static Struct getElement(Struct _struct, Object _index, ExecutableCode _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        if (_struct.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return NullStruct.NULL_VALUE;
        }
        Object array_ = _struct.getInstance();
        int len_ = LgNames.getLength(array_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            return NullStruct.NULL_VALUE;
        }
        return LgNames.getElement(array_, index_, _conf.getContextEl());
    }
    public static void setElement(Struct _struct, Object _index, Struct _value, ExecutableCode _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        String store_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        store_ = stds_.getAliasStore();
        if (_struct.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        String strClass_ = stds_.getStructClassName(_struct, _conf.getContextEl());
        String valClass_ = stds_.getStructClassName(_value, _conf.getContextEl());
        Object instance_ = _struct.getInstance();
        int len_ = LgNames.getLength(instance_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            return;
        }
        String componentType_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
        if (!_value.isNull()) {
            String elementType_ = valClass_;
            Mapping mapping_ = new Mapping();
            mapping_.setArg(elementType_);
            mapping_.setParam(componentType_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(StringList.concat(componentType_,elementType_,_conf.joinPages())),store_));
                return;
            }
        } else if (PrimitiveTypeUtil.primitiveTypeNullObject(componentType_, _value, _conf)) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        Struct value_;
        if (_value instanceof NumberStruct || _value instanceof CharStruct) {
            ClassArgumentMatching cl_ = new ClassArgumentMatching(componentType_);
            value_ = PrimitiveTypeUtil.convertObject(cl_, _value, _conf);
        } else {
            value_ = _value;
        }
        LgNames.setElement(instance_, index_, value_, _conf.getContextEl());
    }
    public static Argument getEnumValues(String _class, ExecutableCode _conf) {
        String id_ = Templates.getIdFromAllTypes(_class);
        if (InvokingOperation.hasToExit(_conf, id_)) {
            return Argument.createVoid();
        }
        Classes classes_ = _conf.getClasses();
        CustList<Struct> enums_ = new CustList<Struct>();
        for (Block b: Classes.getDirectChildren(classes_.getClassBody(id_))) {
            if (!(b instanceof ElementBlock)) {
                continue;
            }
            ElementBlock b_ = (ElementBlock)b;
            String fieldName_ = b_.getFieldName();
            enums_.add(classes_.getStaticField(new ClassField(id_, fieldName_)));
        }
        Struct[] o_ = new Struct[enums_.size()];
        int i_ = CustList.FIRST_INDEX;
        for (Struct o: enums_) {
            o_[i_] = o;
            i_++;
        }
        String clArr_;
        if (StringList.quickEq(id_, _class)) {
            clArr_ = _class;
        } else {
            clArr_ = _conf.getStandards().getAliasEnum();
        }
        clArr_ = PrimitiveTypeUtil.getPrettyArrayType(clArr_);
        Argument argres_ = new Argument();
        argres_.setStruct(new ArrayStruct(o_,clArr_));
        return argres_;
    }
    public static Argument getEnumValue(String _class, Argument _name, ExecutableCode _conf) {
        if (InvokingOperation.hasToExit(_conf, _class)) {
            return Argument.createVoid();
        }
        if (_name.isNull()) {
            Argument argres_ = new Argument();
            return argres_;
        }
        Classes classes_ = _conf.getClasses();
        for (Block b: Classes.getDirectChildren(classes_.getClassBody(_class))) {
            if (!(b instanceof ElementBlock)) {
                continue;
            }
            ElementBlock b_ = (ElementBlock)b;
            String fieldName_ = b_.getFieldName();
            if (StringList.quickEq(fieldName_, (String) _name.getObject())) {
                Argument argres_ = new Argument();
                argres_.setStruct(classes_.getStaticField(new ClassField(_class, fieldName_)));
                return argres_;
            }
        }
        Argument argres_ = new Argument();
        return argres_;
    }
    public static Argument getField(FieldMetaInfo _meta, Argument _previous, ExecutableCode _conf) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = Templates.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        return getField(baseClass_, fieldName_, isStaticField_, _previous, _conf, -1);
    }
    public static Argument getField(String _className, String _fieldName, boolean _isStaticField, Argument _previous, ExecutableCode _conf, int _possibleOffset) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        Argument a_ = new Argument();
        if (_possibleOffset > -1) {
            _conf.setOffset(_possibleOffset);
        }

        Classes classes_ = _conf.getClasses();
        Argument arg_ = _previous;
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        if (_isStaticField) {
            if (InvokingOperation.hasToExit(_conf, _className)) {
                return Argument.createVoid();
            }
            if (classes_.isCustomType(_className)) {
                Struct struct_ = classes_.getStaticField(fieldId_);
                a_ = new Argument();
                a_.setStruct(struct_);
                return a_;
            }
            ResultErrorStd res_ = LgNames.getField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE);
            a_ = new Argument();
            if (res_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
            } else {
                a_.setStruct(res_.getResult());
            }
            return a_;
        }
        if (arg_.isNull()) {
            String npe_;
            npe_ = stds_.getAliasNullPe();
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),npe_));
            return Argument.createVoid();
        }
        String argClassName_ = arg_.getObjectClassName(_conf.getContextEl());
        String classNameFound_ = fieldId_.getClassName();
        String base_ = Templates.getIdFromAllTypes(argClassName_);
        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
            return arg_;
        }
        if (arg_.getStruct() instanceof FieldableStruct) {
            Struct struct_ = ((FieldableStruct) arg_.getStruct()).getStruct(fieldId_);
            a_ = new Argument();
            a_.setStruct(struct_);
            return a_;
        }
        Struct default_ = arg_.getStruct();
        ResultErrorStd res_ = LgNames.getField(_conf.getContextEl(), fieldId_, default_);
        a_ = new Argument();
        if (res_.getError() != null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
        } else {
            a_.setStruct(res_.getResult());
        }
        return a_;
    }
    public static Argument setField(FieldMetaInfo _meta, Argument _previous, Argument _right,ExecutableCode _conf) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = Templates.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        boolean isFinalField_ = _meta.isFinalField();
        String type_ = _meta.getType();
        return setField(baseClass_, fieldName_, isStaticField_, isFinalField_, true, type_, _previous, _right, _conf, -1);
    }
    public static Argument setField(String _className, String _fieldName, boolean _isStaticField, boolean _finalField, boolean _failIfFinal, String _returnType, Argument _previous, Argument _right,ExecutableCode _conf, int _possibleOffset) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        if (_possibleOffset > -1) {
            _conf.setOffset(_possibleOffset);
        }
        String fieldType_;
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        String className_ = _className;
        if (_isStaticField) {
            if (_finalField && _failIfFinal) {
                String npe_;
                npe_ = stds_.getAliasNullPe();
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),npe_));
                return Argument.createVoid();
            }
            if (InvokingOperation.hasToExit(_conf, className_)) {
                return Argument.createVoid();
            }
            fieldType_ = _returnType;
            Struct check_ = _right.getStruct();
            if (!check_.isNull()) {
                Mapping map_ = new Mapping();
                String rightClass_ = stds_.getStructClassName(check_, _conf.getContextEl());
                map_.setArg(rightClass_);
                map_.setParam(fieldType_);
                if (!Templates.isCorrect(map_, _conf)) {
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                    return Argument.createVoid();
                }
            } else if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldType_, check_, _conf)) {
                String npe_;
                npe_ = stds_.getAliasNullPe();
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),npe_));
                return Argument.createVoid();
            }
            ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
            if (_conf.getException() != null) {
                return _right;
            }
            if (_right.getStruct() instanceof NumberStruct || _right.getStruct() instanceof CharStruct) {
                _right.setStruct(PrimitiveTypeUtil.convertObject(cl_, _right.getStruct(), _conf));
            }
            if (classes_.isCustomType(className_)) {
                classes_.initializeStaticField(fieldId_, _right.getStruct());
                return _right;
            }
            ResultErrorStd result_;
            result_ = LgNames.setField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE, _right.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
                return _right;
            }
            return _right;
        }
        if (_previous.isNull()) {
            String npe_;
            npe_ = stds_.getAliasNullPe();
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),npe_));
            return Argument.createVoid();
        }
        String argClassName_ = _previous.getObjectClassName(_conf.getContextEl());
        String classNameFound_ = fieldId_.getClassName();
        String base_ = Templates.getIdFromAllTypes(argClassName_);
        classNameFound_ = fieldId_.getClassName();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameFound_, base_, _conf)) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
            return Argument.createVoid();
        }
        classNameFound_ = Templates.getIdFromAllTypes(classNameFound_);
        classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
        fieldType_ = _returnType;
        fieldType_ = Templates.format(classNameFound_, fieldType_, _conf);
        Struct check_ = _right.getStruct();
        if (!check_.isNull()) {
            Mapping map_ = new Mapping();
            String rightClass_ = stds_.getStructClassName(check_, _conf.getContextEl());
            map_.setArg(rightClass_);
            map_.setParam(fieldType_);
            if (!Templates.isCorrect(map_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,fieldType_,RETURN_LINE,_conf.joinPages())),cast_));
                return Argument.createVoid();
            }
        } else if (PrimitiveTypeUtil.primitiveTypeNullObject(fieldType_, check_, _conf)) {
            String npe_;
            npe_ = stds_.getAliasNullPe();
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),npe_));
            return Argument.createVoid();
        }
        ClassArgumentMatching cl_ = new ClassArgumentMatching(fieldType_);
        if (_conf.getException() != null) {
            return _right;
        }
        if (_right.getStruct() instanceof NumberStruct || _right.getStruct() instanceof CharStruct) {
            _right.setStruct(PrimitiveTypeUtil.convertObject(cl_, _right.getStruct(), _conf));
        }
        if (_previous.getStruct() instanceof FieldableStruct) {
            ((FieldableStruct) _previous.getStruct()).setStruct(fieldId_, _right.getStruct());
            return _right;
        }
        ResultErrorStd result_;
        result_ = LgNames.setField(_conf.getContextEl(), fieldId_, _previous.getStruct(), _right.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),result_.getError()));
            return _right;
        }
        return _right;
    }
}
