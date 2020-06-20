package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodIdAncestor;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InvokingOperation extends MethodOperation implements PossibleIntermediateDotted {
    private ClassArgumentMatching previousResultClass;
    private MethodAccessKind staticAccess;
    private boolean intermediate;

    private Argument previousArgument;

    public InvokingOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        previousResultClass = new ClassArgumentMatching(EMPTY_STRING);
    }

    @Override
    final void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    static String getVarargParam(CustList<OperationNode> _children) {
        if (!_children.isEmpty() && _children.first() instanceof VarargOperation) {
            return ((VarargOperation)_children.first()).getClassName();
        }
        return "";
    }
    static CustList<ClassArgumentMatching> listClasses(CustList<OperationNode> _children) {
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: _children) {
            if (o instanceof VarargOperation
                    || o instanceof IdFctOperation) {
                continue;
            }
            firstArgs_.add(o.getResultClass());
        }
        return firstArgs_;
    }

    private static CustList<OperationNode> filterOperands(CustList<OperationNode> _children) {
        CustList<OperationNode> firstArgs_ = new CustList<OperationNode>();
        for (OperationNode o: _children) {
            if (o instanceof IdFctOperation
                    || o instanceof VarargOperation) {
                continue;
            }
            firstArgs_.add(o);
        }
        return firstArgs_;
    }

    public static CustList<Argument> quickListArguments(CustList<OperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            int natVarArg_ = _natVararg;
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                if (_children.get(i) instanceof IdFctOperation
                        || _children.get(i) instanceof VarargOperation) {
                    natVarArg_++;
                    continue;
                }
                Argument a_ = _nodes.get(i);
                if (i >= natVarArg_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
            }
            Argument argRem_ = new Argument();
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            ExecTemplates.setElements(optArgs_,str_);
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
            if (_children.get(i) instanceof IdFctOperation
                    || _children.get(i) instanceof VarargOperation) {
                continue;
            }
            Argument a_ = _nodes.get(i);
            firstArgs_.add(a_);
        }
        return firstArgs_;
    }
    static StringList getBounds(String _cl, ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String objectClassName_ = stds_.getAliasObject();
        StringList bounds_ = new StringList();
        if (_cl.startsWith(Templates.PREFIX_VAR_TYPE)) {
            String glClass_ = _conf.getAnalyzing().getGlobalClass();
            String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
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

    static void unwrapArgsFct(CustList<OperationNode> _ch, Identifiable _id, int _natvararg, String _lasttype, CustList<ClassArgumentMatching> _args, ContextEl _conf) {
        if (_natvararg > -1) {
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
                    param_ = StringExpUtil.getPrettyArrayType(param_);
                }
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    a_.setUnwrapObject(param_);
                }
            }
        }
        for (OperationNode o: filterOperands(_ch)) {
            o.cancelArgument();
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
        if (!(first_ instanceof VarargOperation)&&!(first_ instanceof IdFctOperation)) {
            return -1;
        }
        CustList<OperationNode> ch_ = getChildrenNodes();
        int firstOpt_ = 0;
        boolean found_ = false;
        int len_ = ch_.size();
        for (int i = from_; i < len_;i++) {
            if (ch_.get(i) instanceof FirstOptOperation) {
                firstOpt_ = i + 1 - from_;
                found_ = true;
                break;
            }
        }
        if (!found_ && first_ instanceof IdFctOperation) {
            return -1;
        }
        return firstOpt_;
    }
    final ClassMethodIdAncestor lookOnlyForId() {
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
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
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

    public final MethodAccessKind isStaticAccess() {
        return staticAccess;
    }

    public final void setStaticAccess(MethodAccessKind _staticAccess) {
        staticAccess = _staticAccess;
    }

}
