package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.Identifiable;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
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
        previousResultClass = new ClassArgumentMatching(EMPTY_STRING);
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
            map_ = _conf.getCurrentConstraints();
            Mapping mapping_ = new Mapping();
            mapping_.setParam(name_);
            for (OperationNode o: optArgsNodes_) {
                setRelativeOffsetPossibleAnalyzable(o.getIndexInEl(), _conf);
                ClassArgumentMatching argType_ = o.getResultClass();
                mapping_.setArg(argType_);
                mapping_.setMapping(map_);
                if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(cast_);
                }
                if (PrimitiveTypeUtil.isPrimitive(name_, _conf)) {
                    o.getResultClass().setUnwrapObject(name_);
                    o.cancelArgument();
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

    static CustList<OperationNode> filterOperands(CustList<OperationNode> _children) {
        CustList<OperationNode> firstArgs_ = new CustList<OperationNode>();
        for (OperationNode o: _children) {
            if (o instanceof IdFctOperation) {
                continue;
            }
            firstArgs_.add(o);
        }
        return firstArgs_;
    }

    public static CustList<Argument> quickListArguments(CustList<Operable> _children, int _natVararg, String _lastType, CustList<Argument> _nodes, Analyzable _context) {
        if (!_children.isEmpty() && _children.first() instanceof VarargOperable) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            int i_ = CustList.FIRST_INDEX;
            for (Operable o: _children) {
                if (o instanceof VarargOperable) {
                    i_++;
                    continue;
                }
                if (o instanceof FirstOptOperable) {
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
            Templates.setElements(optArgs_,str_);
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
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            Templates.setElements(optArgs_,str_);
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        return new CustList<Argument>(_nodes);
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
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
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
                OperationNode op_ = _children.get(indexChild_);
                op_.setRelativeOffsetPossibleAnalyzable(op_.getIndexInEl()+_off, _conf);
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
        return void_;
    }
    static void unwrapArgsFct(CustList<OperationNode> _ch, Identifiable _id, int _natvararg, String _lasttype, CustList<ClassArgumentMatching> _args, Analyzable _conf) {
        boolean filter_ = true;
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
                    o.cancelArgument();
                }
                i_++;
            }
            filter_ = false;
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
        if (filter_) {
            for (OperationNode o: filterOperands(_ch)) {
                o.cancelArgument();
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

    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
        staticAccess = _staticAccess;
    }

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

}
