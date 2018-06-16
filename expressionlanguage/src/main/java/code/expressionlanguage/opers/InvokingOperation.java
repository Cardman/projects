package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.CustomReflectMethod;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ReflectingType;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;
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
            String name_ = _children.first().getResultClass().getName();
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
                String argType_ = o.getResultClass().getName();
                if (argType_.isEmpty()) {
                    if (PrimitiveTypeUtil.isPrimitive(name_, _conf)) {
                        mapping_.setMapping(map_);
                        BadImplicitCast cast_ = new BadImplicitCast();
                        cast_.setMapping(mapping_);
                        cast_.setFileName(_conf.getCurrentFileName());
                        cast_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(cast_);
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
                    _conf.getClasses().getErrorsDet().add(cast_);
                }
                if (PrimitiveTypeUtil.isPrimitive(name_, _conf)) {
                    o.getResultClass().setUnwrapObject(name_);
                }
            }
            name_ = PrimitiveTypeUtil.getPrettyArrayType(name_);
            ClassArgumentMatching clMatch_ = new ClassArgumentMatching(name_);
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
            String g_ = _children.first().getResultClass().getName();
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
            String g_ = _children.first().getResultClass().getName();
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
            _conf.getClasses().getErrorsDet().add(cast_);
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
                _conf.getClasses().getErrorsDet().add(cast_);
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
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);

        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
    }

    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: children_) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            filter_.add(o);
        }
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        if (filter_.isEmpty()) {
            for (EntryCust<ClassField, AssignmentBefore> e: vars_.getFieldsBefore().getVal(this).entryList()) {
                AssignmentBefore b_ = e.getValue();
                fieldsAfter_.put(e.getKey(), b_.assignAfter(isBool_));
            }
            for (StringMap<AssignmentBefore> s: vars_.getVariablesBefore().getVal(this)) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore b_ = e.getValue();
                    sm_.put(e.getKey(), b_.assignAfter(isBool_));
                }
                variablesAfter_.add(sm_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            vars_.getVariables().put(this, variablesAfter_);
            return;
        }
        OperationNode last_ = filter_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
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
//      String null_;
//      null_ = stds_.getAliasNullPe();
        Argument needed_ = null;
//      if (_class != null && !Modifier.isStatic(_class.getModifiers())) {
//          Argument arg_ = _previous;
//          if (arg_.isNull()) {
//              throw new InvokeException(new StdStruct(new CustomError(_class.getName()+RETURN_LINE+_conf.joinPages()),null_));
//          }
//          needed_ = arg_;
//          _arguments.add(CustList.FIRST_INDEX, arg_);
//      }
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
        Classes classes_ = _conf.getClasses();
        String aliasClass_ = stds_.getAliasClass();
        String aliasForName_ = stds_.getAliasForName();
        if (StringList.quickEq(aliasClass_, _classNameFound)) {
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
                String base_ = Templates.getIdFromAllTypes(clDyn_);
                Boolean init_ = (Boolean) _firstArgs.last().getObject();
                if (base_.contains(Templates.TEMPLATE_BEGIN)) {
                    if (!Templates.correctClassParts(clDyn_, new StringMap<StringList>(), _conf)) {
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),stds_.getAliasClassNotFoundError()));
                        Argument a_ = new Argument();
                        return a_;
                    }
                } else {
                    if (!Templates.existClassParts(clDyn_, new StringMap<StringList>(), _conf)) {
                        _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),stds_.getAliasClassNotFoundError()));
                        Argument a_ = new Argument();
                        return a_;
                    }
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
        }
        String aliasMethod_ = stds_.getAliasMethod();
        String aliasConstructor_ = stds_.getAliasConstructor();
        String aliasInvoke_ = stds_.getAliasInvoke();
        String aliasNewInstance_ = stds_.getAliasNewInstance();
        if (StringList.quickEq(aliasMethod_, _classNameFound)) {
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
}
