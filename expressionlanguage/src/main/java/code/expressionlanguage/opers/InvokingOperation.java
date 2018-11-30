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
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundAnnotation;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.CustomReflectMethod;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ReflectingType;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LambdaConstructorStruct;
import code.expressionlanguage.structs.LambdaFieldStruct;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Numbers;
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
            if (o instanceof IdFctOperation) {
                continue;
            }
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
                if (_context.getContextEl().hasExceptionOrFailInit()) {
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
                if (_children.get(i) instanceof IdFctOperation) {
                    continue;
                }
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
                if (_context.getContextEl().hasExceptionOrFailInit()) {
                    return firstArgs_;
                }
            }
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
            if (_children.get(i) instanceof IdFctOperation) {
                continue;
            }
            Argument a_ = _nodes.get(i);
            firstArgs_.add(a_);
        }
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
    private static boolean setElement(ArrayStruct _struct, Object _index, Struct _value, Analyzable _conf) {
        if (_struct.isNull()) {
            return false;
        }
        if (_index == null) {
            return false;
        }
        String strClass_ = _struct.getClassName();
        String valClass_ = _value.getClassName(_conf.getContextEl());
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
        if (_value instanceof NumberStruct) {
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
    static boolean hasVoidPrevious(String _cl, Analyzable _conf) {
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
    final ClassMethodId lookOnlyForId() {
        OperationNode first_ = getFirstChild();
        if (first_ == null) {
            return null;
        }
        if (first_ instanceof StaticInitOperation) {
            first_ = first_.getNextSibling();
            if (first_ == null) {
                return null;
            }
        }
        if (!(first_ instanceof IdFctOperation)) {
            return null;
        }
        return ((IdFctOperation)first_).getMethod();
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
    abstract boolean isCallMethodCtor(Analyzable _an);
    public static boolean hasToExit(ExecutableCode _conf, String _className) {
        Classes classes_ = _conf.getClasses();
        String idClass_ = Templates.getIdFromAllTypes(_className);
        ContextEl cont_ = _conf.getContextEl();
        if (!cont_.isEmptyPages()) {
            String curClass_ = cont_.getLastPage().getGlobalClass();
            if (curClass_ != null) {
                curClass_ = Templates.getIdFromAllTypes(curClass_);
                if (StringList.quickEq(curClass_, idClass_)) {
                    return false;
                }
            }
        }
        if (classes_.isCustomType(_className)) {
            InitClassState res_ = classes_.getLocks().getState(_conf.getContextEl(), _className);
            if (res_ == InitClassState.NOT_YET) {
                _conf.getContextEl().failInitEnums();
                _conf.getContextEl().setInitClass(new NotInitializedClass(_className));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                _conf.getContextEl().failInitEnums();
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
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            Argument a_ = new Argument();
            return a_;
        }
        LgNames stds_ = _conf.getStandards();
        String idCl_ = Templates.getIdFromAllTypes(_className);
        GeneType g_ = _conf.getClassBody(idCl_);
        Argument needed_ = new Argument();
        if (g_ instanceof RootBlock) {
            RootBlock r_ = (RootBlock) g_;
            if (!r_.withoutInstance()) {
                //From analyze
                StringList parts_ = Templates.getAllInnerTypes(_className);
                String param_ = parts_.sub(0, parts_.size()-1).join("..");
                if (_previous.isNull()) {
                    String npe_;
                    npe_ = stds_.getAliasNullPe();
                    _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),npe_));
                    Argument a_ = new Argument();
                    return a_;
                }
                String arg_ = _previous.getObjectClassName(_conf.getContextEl());
                if (!Templates.isCorrectExecute(arg_, param_, _conf)) {
                    String cast_;
                    cast_ = stds_.getAliasCast();
                    _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                needed_.setStruct(_previous.getStruct());
            }
        }
        String base_ = Templates.getIdFromAllTypes(_className);
        if (!_conf.getClasses().isCustomType(base_)) {
            ResultErrorStd res_ = LgNames.newInstance(_conf.getContextEl(), _constId, Argument.toArgArray(_arguments));
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
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
            class_ = Templates.quickFormat(className_, class_, _conf);
            if (j_ + 1 == _constId.getParametersTypes().size() && _constId.isVararg()) {
                class_ = PrimitiveTypeUtil.getPrettyArrayType(class_);
            }
            params_.add(class_);
            j_++;
        }
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: _arguments) {
            if (i_ < params_.size()) {
                String param_ = params_.get(i_);
                if (!Templates.checkObject(param_, a, _conf)) {
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
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
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
            if (!Templates.isCorrectExecute(className_, _classNameFound, _conf)) {
                _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),cast_));
                Argument a_ = new Argument();
                return a_;
            }
            if (!(_previous.getStruct() instanceof ArrayStruct)) {
                classFormat_ = Templates.getFullTypeByBases(className_, classFormat_, _conf);
            }
            int i_ = 0;
            for (String c: _methodId.getParametersTypes()) {
                String c_ = c;
                c_ = Templates.quickFormat(classFormat_, c_, _conf);
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
                if (_possibleOffset > -1) {
                    _conf.setOffset(_possibleOffset);
                }
                String param_ = params_.get(i_);
                if (!Templates.checkObject(param_, a, _conf)) {
                    Argument a_ = new Argument();
                    return a_;
                }
            }
            i_++;
        }
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
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
            Struct prev_ =_previous.getStruct();
            if (prev_ instanceof ArrayStruct) {
                //clone object
                Argument a_ = new Argument();
                ArrayStruct arr_ = (ArrayStruct) prev_;
                Struct[] str_ = arr_.getInstance();
                int len_ = str_.length;
                String clNameOut_ = arr_.getClassName();
                ArrayStruct copy_ = new ArrayStruct(new Struct[len_], clNameOut_);
                for (int i = 0; i < len_; i++) {
                    copy_.getInstance()[i] = str_[i];
                }
                _conf.getContextEl().addSensibleElementsFromClonedArray(arr_, copy_);
                a_.setStruct(copy_);
                return a_;
            }
            if (PrimitiveTypeUtil.canBeUseAsArgument(false, aliasAnnotation_, clName_, _conf)) {
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
        if (PrimitiveTypeUtil.canBeUseAsArgument(false, aliasAnnotated_, _classNameFound, _conf)) {
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
                RootBlock r_ = classes_.getClassBody(enumName_);
                StringList allElements_ = new StringList();
                for (Block e: Classes.getDirectChildren(r_)) {
                    if (e instanceof ElementBlock) {
                        String type_ = ((ElementBlock)e).getImportedClassName();
                        allElements_.add(type_);
                    }
                }
                allElements_.removeDuplicates();
                String className_;
                if (allElements_.size() == 1) {
                    className_ = allElements_.first();
                } else {
                    className_ = r_.getWildCardString();
                }
                return getEnumValues(className_, _conf);
            }
            if (StringList.quickEq(aliasForName_, _methodId.getName())) {
                Argument clArg_ = _firstArgs.first();
                if (clArg_.isNull()) {
                    _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),stds_.getAliasNullPe()));
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
                String res_ = Templates.correctClassPartsDynamic(clDyn_, _conf, gene_, false);
                if (res_.isEmpty()) {
                    _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),stds_.getAliasClassNotFoundError()));
                    Argument a_ = new Argument();
                    return a_;
                }
                if (init_) {
                    if (hasToExit(_conf, res_)) {
                        return Argument.createVoid();
                    }
                }
                Argument a_ = new Argument();
                a_.setStruct(_conf.getExtendedClassMetaInfo(res_));
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
                    cont_.setException(new ErrorStruct(new CustomError(cont_.joinPages()),null_));
                    return Argument.createVoid();
                }
                String res_ = Templates.correctClassPartsDynamic(className_, _conf, true, true);
                if (res_.isEmpty()) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    cont_.setException(new ErrorStruct(new CustomError(cont_.joinPages()),null_));
                    return Argument.createVoid();
                }
                className_ = res_;
                String first_ = className_;
                CustList<GeneType> need_ = new CustList<GeneType>();
                if (type_ instanceof RootBlock) {
                    CustList<RootBlock> needRoot_;
                    needRoot_ = ((RootBlock)type_).getSelfAndParentTypes();
                    first_ = needRoot_.first().getFullName();
                    for (RootBlock r: needRoot_) {
                        need_.add(r);
                    }
                    if (type_.withoutInstance() && hasToExit(_conf, first_)) {
                        return Argument.createVoid();
                    }
                } else {
                    need_.add(type_);
                }
                if (!_firstArgs.isEmpty()) {
                    Struct par_ = _firstArgs.first().getStruct();
                    if (type_.withoutInstance()) {
                        par_ = NullStruct.NULL_VALUE;
                    } else {
                        if (par_.isNull()) {
                            String null_;
                            null_ = stds_.getAliasNullPe();
                            cont_.setException(new ErrorStruct(new CustomError(cont_.joinPages()),null_));
                            return Argument.createVoid();
                        }
                        String argCl_ = par_.getClassName(cont_);
                        //From analyze
                        StringList inners_ = Templates.getAllInnerTypes(className_);
                        String param_ = inners_.mid(0, inners_.size() - 1).join("..");
                        if (!Templates.isCorrectExecute(argCl_, param_, cont_)) {
                            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),cast_));
                            return Argument.createVoid();
                        }
                    }
                    Initializer in_ = cont_.getInit();
                    String genStr_ = type_.getGenericString();
                    String form_ = Templates.quickFormat(className_, genStr_, cont_);
                    par_ = in_.processInit(cont_, par_, form_, EMPTY_STRING, 0);
                    Argument a_ = new Argument();
                    a_.setStruct(par_);
                    return a_;
                }
                Struct parent_ = NullStruct.NULL_VALUE;
                Initializer in_ = cont_.getInit();
                for (GeneType r: need_) {
                    String genStr_ = r.getGenericString();
                    String form_ = Templates.quickFormat(className_, genStr_, cont_);
                    parent_ = in_.processInit(cont_, parent_, form_, EMPTY_STRING, 0);
                }
                Argument a_ = new Argument();
                a_.setStruct(parent_);
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
        String aliasFct_ = stds_.getAliasFct();
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
        if (StringList.quickEq(aliasFct_, _classNameFound)) {
            Argument instance_ = _firstArgs.first();
            Struct inst_ = instance_.getStruct();
            if (!(inst_ instanceof ArrayStruct)) {
                _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),stds_.getAliasNullPe()));
                Argument a_ = new Argument();
                return a_;
            }
            ArrayStruct arr_ = (ArrayStruct) inst_;
            Struct[] real_ = arr_.getInstance();
            CustList<Argument> ar_ = new CustList<Argument>();
            int len_ = real_.length;
            for (int i = 0; i < len_; i++) {
                Struct str_ = real_[i];
                ar_.add(new Argument(str_));
            }
            return prepareCallDyn(_previous, ar_, _conf);
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
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                Argument a_ = new Argument();
                return a_;
            }
            Argument argRes_ = new Argument();
            argRes_.setStruct(res_.getResult());
            return argRes_;
        }
        ContextEl context_ = _conf.getContextEl();
        CustList<MethodBlock> methods_ = Classes.getMethodBodiesById(context_, _classNameFound, _methodId);
        if (methods_.isEmpty()) {
            //static enum methods
            String values_ = context_.getStandards().getAliasValues();
            if (StringList.quickEq(_methodId.getName(), values_)) {
                EnumBlock e_ = (EnumBlock) classes_.getClassBody(_classNameFound);
                StringList allElements_ = new StringList();
                for (Block e: Classes.getDirectChildren(e_)) {
                    if (e instanceof ElementBlock) {
                        String type_ = ((ElementBlock)e).getImportedClassName();
                        allElements_.add(type_);
                    }
                }
                allElements_.removeDuplicates();
                String className_;
                if (allElements_.size() == 1) {
                    className_ = allElements_.first();
                } else {
                    className_ = e_.getWildCardString();
                }
                return getEnumValues(className_, _conf);
            }
            Argument arg_ = _firstArgs.first();
            return getEnumValue(_classNameFound, arg_, _conf);
        }
        String enum_ = context_.getStandards().getAliasEnum();
        if (StringList.quickEq(enum_, _classNameFound)) {
            String name_ = context_.getStandards().getAliasName();
            EnumerableStruct en_ = (EnumerableStruct) _previous.getStruct();
            if (StringList.quickEq(_methodId.getName(), name_)) {
                return new Argument(new StringStruct(en_.getName()));
            }
            return new Argument(new IntStruct(en_.getOrdinal()));
        }
        context_.setCallMethod(new CustomFoundMethod(_previous, _classNameFound, _methodId, _firstArgs));
        return Argument.createVoid();
    }

    public static Argument prepareCallDyn(Argument _previous, CustList<Argument> _values, ExecutableCode _conf) {
        Struct ls_ = _previous.getStruct();
        String typeFct_ = ls_.getClassName(_conf);
        StringList parts_ = Templates.getAllTypes(typeFct_);
        StringList paramsFct_ = parts_.mid(1, parts_.size() - 2);
        LgNames lgNames_ = _conf.getStandards();
        int valuesSize_ = _values.size();
        if (valuesSize_ != paramsFct_.size()) {
            String null_;
            null_ = lgNames_.getAliasNullPe();
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),null_));
            Argument a_ = new Argument();
            return a_;
        }
        for (int i = 0; i < valuesSize_; i++) {
            Argument arg_ = _values.get(i);
            String param_ = paramsFct_.get(i);
            if (!Templates.checkObject(param_, arg_, _conf)) {
                Argument a_ = new Argument();
                return a_;
            }
        }
        if (ls_ instanceof LambdaConstructorStruct) {
            Argument result_ = new Argument();
            LambdaConstructorStruct l_ = (LambdaConstructorStruct) ls_;
            String forId_ = l_.getFormClassName();
            if (forId_.startsWith(ARR)) {
                Numbers<Integer> dims_ = new Numbers<Integer>();
                String size_;
                size_ = lgNames_.getAliasBadSize();
                for (Argument a: _values) {
                    int dim_ = ((NumberStruct)a.getStruct()).getInstance().intValue();
                    if (dim_ < 0) {
                        _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),size_));
                        return result_;
                    }
                    dims_.add(dim_);
                }
                String c_ = forId_.substring(ARR.length());
                if (StringList.quickEq(c_, _conf.getStandards().getAliasVoid())) {
                    _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),lgNames_.getAliasClassNotFoundError()));
                    return result_;
                }
                result_.setStruct(PrimitiveTypeUtil.newCustomArray(c_, dims_, _conf));
                return result_;
            }
            ConstructorId cid_ = l_.getFid();
            ConstructorMetaInfo c_ = new ConstructorMetaInfo(forId_, AccessEnum.PUBLIC, cid_, forId_, cid_, forId_, forId_);
            Argument pr_ = new Argument();
            pr_.setStruct(c_);
            Argument instance_ = l_.getInstanceCall();
            String obj_ = _conf.getStandards().getAliasObject();
            obj_ = PrimitiveTypeUtil.getPrettyArrayType(obj_);
            if (!l_.isShiftInstance()) {
                ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()],obj_);
                int i_ = 0;
                for (Argument v: _values) {
                    arr_.getInstance()[i_] = v.getStruct();
                    i_++;
                }
                CustList<Argument> nList_ = new CustList<Argument>();
                nList_.add(new Argument(arr_));
                _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.CONSTRUCTOR, pr_, nList_));
                Argument a_ = new Argument();
                return a_;
            }
            ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()+1],obj_);
            int i_ = 1;
            arr_.getInstance()[0] = instance_.getStruct();
            for (Argument v: _values) {
                arr_.getInstance()[i_] = v.getStruct();
                i_++;
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            nList_.add(new Argument(arr_));
            _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.CONSTRUCTOR, pr_, nList_));
            Argument a_ = new Argument();
            return a_;
        }
        if (ls_ instanceof LambdaFieldStruct) {
            LambdaFieldStruct l_ =  (LambdaFieldStruct) ls_;
            boolean aff_ = l_.isAffect();
            ClassField idField_ = l_.getFid();
            FieldInfo fi_ = _conf.getFieldInfo(idField_);
            boolean static_ = fi_.isStaticField();
            int nbAncestors_ = l_.getAncestor();
            boolean final_ = fi_.isFinalField();
            String name_ = idField_.getFieldName();
            String clName_ = idField_.getClassName();
            String retField_ = l_.getReturnFieldType();
            FieldMetaInfo f_ = new FieldMetaInfo(clName_, name_, retField_, static_, final_, false, AccessEnum.PUBLIC);
            Argument pr_ = new Argument();
            pr_.setStruct(f_);
            ReflectingType type_;
            if (aff_) {
                type_ = ReflectingType.SET_FIELD;
            } else {
                type_ = ReflectingType.GET_FIELD;
            }
            Argument instance_ = l_.getInstanceCall();
            String obj_ = _conf.getStandards().getAliasObject();
            obj_ = PrimitiveTypeUtil.getPrettyArrayType(obj_);
            CustList<Argument> nList_ = new CustList<Argument>();
            Argument realInstance_;
            if (static_) {
                realInstance_ = new Argument();
            } else if (!l_.isShiftInstance()) {
                realInstance_ = instance_;
            } else {
                realInstance_ = _values.first();
            }
            if (!static_) {
                Struct value_ = realInstance_.getStruct();
                realInstance_.setStruct(PrimitiveTypeUtil.getParent(nbAncestors_, clName_, value_, _conf));
                if (_conf.getContextEl().hasExceptionOrFailInit()) {
                    return new Argument();
                }
            }
            nList_.add(realInstance_);
            if (aff_) {
                nList_.add(_values.last());
            }
            _conf.getContextEl().setReflectMethod(new CustomReflectMethod(type_, pr_, nList_));
            Argument a_ = new Argument();
            return a_;
        }
        LambdaMethodStruct l_ =  (LambdaMethodStruct) ls_;
        int nbAncestors_ = l_.getAncestor();
        String id_ = Templates.getIdFromAllTypes(l_.getFormClassName());
        MethodId fid_ = l_.getFid();
        MethodModifier met_;
        boolean static_ = fid_.isStaticMethod();
        if (l_.isAbstractMethod()) {
            met_ = MethodModifier.ABSTRACT;
        } else if (static_) {
            met_ = MethodModifier.STATIC;
        } else {
            met_ = MethodModifier.NORMAL;
        }
        MethodMetaInfo m_ = new MethodMetaInfo(AccessEnum.PUBLIC, id_, fid_, met_, "", fid_, "", "");
        m_.setPolymorph(l_.isPolymorph());
        Argument pr_ = new Argument();
        pr_.setStruct(m_);
        Argument instance_ = l_.getInstanceCall();
        String obj_ = _conf.getStandards().getAliasObject();
        obj_ = PrimitiveTypeUtil.getPrettyArrayType(obj_);
        if (!l_.isShiftInstance()) {
            ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()],obj_);
            int i_ = 0;
            for (Argument v: _values) {
                arr_.getInstance()[i_] = v.getStruct();
                i_++;
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            if (!static_) {
                Struct value_ = instance_.getStruct();
                instance_.setStruct(PrimitiveTypeUtil.getParent(nbAncestors_, id_, value_, _conf));
                if (_conf.getContextEl().hasExceptionOrFailInit()) {
                    return new Argument();
                }
            }
            nList_.add(instance_);
            nList_.add(new Argument(arr_));
            _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.METHOD, pr_, nList_));
            Argument a_ = new Argument();
            return a_;
        }
        if (!StringList.isWord(fid_.getName())) {
            ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()+1],obj_);
            int i_ = 1;
            arr_.getInstance()[0] = instance_.getStruct();
            for (Argument v: _values) {
                arr_.getInstance()[i_] = v.getStruct();
                i_++;
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            nList_.add(new Argument(arr_));
            _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.METHOD, pr_, nList_));
            Argument a_ = new Argument();
            return a_;
        }
        ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()-1],obj_);
        int i_ = 0;
        for (Argument v: _values.mid(1, _values.size() - 1)) {
            arr_.getInstance()[i_] = v.getStruct();
            i_++;
        }
        CustList<Argument> nList_ = new CustList<Argument>();
        Argument firstValue_ = _values.first();
        Struct value_ = firstValue_.getStruct();
        firstValue_.setStruct(PrimitiveTypeUtil.getParent(nbAncestors_, id_, value_, _conf));
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return new Argument();
        }
        nList_.add(firstValue_);
        nList_.add(new Argument(arr_));
        _conf.getContextEl().setReflectMethod(new CustomReflectMethod(ReflectingType.METHOD, pr_, nList_));
        Argument a_ = new Argument();
        return a_;
    }
    public static Struct getElement(Struct _struct, Object _index, ExecutableCode _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        if (!(_struct instanceof ArrayStruct)) {
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),null_));
            return NullStruct.NULL_VALUE;
        }
        ArrayStruct a_ = (ArrayStruct) _struct;
        Struct[] array_ = a_.getInstance();
        int len_ = array_.length;
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            _conf.setException(new ErrorStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            return NullStruct.NULL_VALUE;
        }
        Struct elt_ = array_[index_];
        _conf.getContextEl().addSensibleField(_struct, elt_);
        return elt_;
    }
    public static void setElement(Struct _struct, Object _index, Struct _value, ExecutableCode _conf, boolean _convert) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        if (!(_struct instanceof ArrayStruct)) {
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        String strClass_ = stds_.getStructClassName(_struct, _conf.getContextEl());
        ArrayStruct a_ = (ArrayStruct) _struct;
        Struct[] arr_ = a_.getInstance();
        int len_ = arr_.length;
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            _conf.setException(new ErrorStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            return;
        }
        String componentType_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
        Argument arg_ = new Argument(_value);
        if (!Templates.checkElt(componentType_, arg_, _convert, _conf)) {
            return;
        }
        if (_conf.getContextEl().isContainedSensibleFields(_struct)) {
            _conf.getContextEl().failInitEnums();
            return;
        }
        arr_[index_] = _value;
    }
    public static Argument getEnumValues(String _class, ExecutableCode _conf) {
        String id_ = Templates.getIdFromAllTypes(_class);
        if (InvokingOperation.hasToExit(_conf, id_)) {
            return Argument.createVoid();
        }
        Classes classes_ = _conf.getClasses();
        ContextEl c_ = _conf.getContextEl();
        CustList<Struct> enums_ = new CustList<Struct>();
        for (Block b: Classes.getDirectChildren(classes_.getClassBody(id_))) {
            if (!(b instanceof ElementBlock)) {
                continue;
            }
            ElementBlock b_ = (ElementBlock)b;
            String fieldName_ = b_.getUniqueFieldName();
            Struct str_ = classes_.getStaticField(new ClassField(id_, fieldName_),c_);
            _conf.getContextEl().addSensibleField(id_, str_);
            enums_.add(str_);
        }
        Struct[] o_ = new Struct[enums_.size()];
        int i_ = CustList.FIRST_INDEX;
        for (Struct o: enums_) {
            o_[i_] = o;
            i_++;
        }
        String clArr_ = _class;
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
        ContextEl c_ = _conf.getContextEl();
        Classes classes_ = _conf.getClasses();
        for (Block b: Classes.getDirectChildren(classes_.getClassBody(_class))) {
            if (!(b instanceof ElementBlock)) {
                continue;
            }
            ElementBlock b_ = (ElementBlock)b;
            String fieldName_ = b_.getUniqueFieldName();
            if (StringList.quickEq(fieldName_, (String) _name.getObject())) {
                Argument argres_ = new Argument();
                Struct str_ = classes_.getStaticField(new ClassField(_class, fieldName_),c_);
                _conf.getContextEl().addSensibleField(_class, str_);
                argres_.setStruct(str_);
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
                ContextEl c_ = _conf.getContextEl();
                Struct struct_ = classes_.getStaticField(fieldId_,c_);
                _conf.getContextEl().addSensibleField(fieldId_.getClassName(), struct_);
                a_ = new Argument();
                a_.setStruct(struct_);
                return a_;
            }
            ResultErrorStd res_ = LgNames.getField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE);
            a_ = new Argument();
            if (res_.getError() != null) {
                _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),res_.getError()));
            } else {
                a_.setStruct(res_.getResult());
            }
            return a_;
        }
        if (arg_.isNull()) {
            String npe_;
            npe_ = stds_.getAliasNullPe();
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),npe_));
            return Argument.createVoid();
        }
        String argClassName_ = arg_.getObjectClassName(_conf.getContextEl());
        String base_ = Templates.getIdFromAllTypes(argClassName_);
        if (!PrimitiveTypeUtil.canBeUseAsArgument(false, _className, base_, _conf)) {
            _conf.setException(new ErrorStruct(new CustomError(StringList.concat(base_,RETURN_LINE,_className,RETURN_LINE,_conf.joinPages())),cast_));
            return arg_;
        }
        if (arg_.getStruct() instanceof FieldableStruct) {
            Struct struct_ = ((FieldableStruct) arg_.getStruct()).getStruct(fieldId_);
            _conf.getContextEl().addSensibleField(arg_.getStruct(), struct_);
            a_ = new Argument();
            a_.setStruct(struct_);
            return a_;
        }
        Struct default_ = arg_.getStruct();
        ResultErrorStd res_ = LgNames.getField(_conf.getContextEl(), fieldId_, default_);
        a_ = new Argument();
        if (res_.getError() != null) {
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),res_.getError()));
        } else {
            a_.setStruct(res_.getResult());
        }
        return a_;
    }
    public static Argument setField(FieldMetaInfo _meta, Argument _previous, Argument _right,ExecutableCode _conf, boolean _convert) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = Templates.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        boolean isFinalField_ = _meta.isFinalField();
        String type_ = _meta.getType();
        return setField(baseClass_, fieldName_, isStaticField_, isFinalField_, true, type_, _previous, _right, _conf, -1, _convert);
    }
    public static Argument setField(String _className, String _fieldName, boolean _isStaticField, boolean _finalField, boolean _failIfFinal, String _returnType, Argument _previous, Argument _right,ExecutableCode _conf, int _possibleOffset, boolean _convert) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        if (_possibleOffset > -1) {
            _conf.setOffset(_possibleOffset);
        }
        String fieldType_;
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        if (_isStaticField) {
            if (_finalField && _failIfFinal) {
                String npe_;
                npe_ = stds_.getAliasNullPe();
                _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),npe_));
                return Argument.createVoid();
            }
            if (InvokingOperation.hasToExit(_conf, _className)) {
                return Argument.createVoid();
            }
            fieldType_ = _returnType;
            if (!Templates.checkObject(fieldType_, _right, _convert, _conf)) {
                return Argument.createVoid();
            }
            if (classes_.isCustomType(_className)) {
                if (_conf.getContextEl().isSensibleField(fieldId_.getClassName())) {
                    _conf.getContextEl().failInitEnums();
                    return _right;
                }
                classes_.initializeStaticField(fieldId_, _right.getStruct());
                return _right;
            }
            ResultErrorStd result_;
            result_ = LgNames.setField(_conf.getContextEl(), fieldId_, NullStruct.NULL_VALUE, _right.getStruct());
            if (result_.getError() != null) {
                _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),result_.getError()));
                return _right;
            }
            return _right;
        }
        if (_previous.isNull()) {
            String npe_;
            npe_ = stds_.getAliasNullPe();
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),npe_));
            return Argument.createVoid();
        }
        String argClassName_ = _previous.getObjectClassName(_conf.getContextEl());
        String base_ = Templates.getIdFromAllTypes(argClassName_);
        String classNameFound_ = _className;
        if (!PrimitiveTypeUtil.canBeUseAsArgument(false, classNameFound_, base_, _conf)) {
            _conf.setException(new ErrorStruct(new CustomError(StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE,_conf.joinPages())),cast_));
            return Argument.createVoid();
        }
        classNameFound_ = Templates.getFullTypeByBases(argClassName_, classNameFound_, _conf);
        fieldType_ = _returnType;
        fieldType_ = Templates.quickFormat(classNameFound_, fieldType_, _conf);
        if (!Templates.checkObject(fieldType_, _right, _convert, _conf)) {
            return Argument.createVoid();
        }
        if (_previous.getStruct() instanceof FieldableStruct) {
            if (_conf.getContextEl().isContainedSensibleFields(_previous.getStruct())) {
                _conf.getContextEl().failInitEnums();
                return _right;
            }
            ((FieldableStruct) _previous.getStruct()).setStruct(fieldId_, _right.getStruct());
            return _right;
        }
        ResultErrorStd result_;
        result_ = LgNames.setField(_conf.getContextEl(), fieldId_, _previous.getStruct(), _right.getStruct());
        if (result_.getError() != null) {
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),result_.getError()));
            return _right;
        }
        return _right;
    }
}
