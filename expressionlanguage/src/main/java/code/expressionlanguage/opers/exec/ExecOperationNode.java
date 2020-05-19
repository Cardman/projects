package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.Delimiters;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.*;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public abstract class ExecOperationNode implements Operable {


    protected static final String ARR = "[";

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String PLUS = "+";

    protected static final String DIFF = "!=";

    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";

    private ExecMethodOperation parent;

    private ExecOperationNode nextSibling;

    private Argument argument;

    private int indexInEl;

    private int order;

    private final int indexChild;

    private ClassArgumentMatching resultClass;

    private ExecPossibleIntermediateDotted siblingSet;

    private int indexBegin;

    ExecOperationNode(Operable _oper) {
        indexInEl = _oper.getIndexInEl();
        indexBegin = _oper.getIndexBegin();
        indexChild = _oper.getIndexChild();
        resultClass = _oper.getResultClass();
        argument = _oper.getArgument();
        order = _oper.getOrder();
    }

    ExecOperationNode(int _indexChild, ClassArgumentMatching _res, int _order) {
        indexChild = _indexChild;
        resultClass = _res;
        order = _order;
    }

    public void setParent(ExecMethodOperation _parent) {
        parent = _parent;
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, ContextEl _cont) {
        _cont.setOffset(getIndexBegin()+_offset);
    }

    @Override
    public final int getIndexBegin() {
        return indexBegin;
    }
    public static Operable createExecOperationNode(OperationNode _anaNode) {
        if (_anaNode instanceof StaticInitOperation) {
            StaticInitOperation c_ = (StaticInitOperation) _anaNode;
            return new ExecStaticInitOperation(c_);
        }
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new ExecConstantOperation(c_);
        }
        if (_anaNode instanceof AnnotationInstanceOperation) {
            AnnotationInstanceOperation n_ = (AnnotationInstanceOperation) _anaNode;
            return new ExecAnnotationInstanceOperation(n_);
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor n_ = (InterfaceFctConstructor) _anaNode;
            return new ExecInterfaceFctConstructor(n_);
        }
        if (_anaNode instanceof InterfaceInvokingConstructor) {
            InterfaceInvokingConstructor n_ = (InterfaceInvokingConstructor) _anaNode;
            return new ExecInterfaceInvokingConstructor(n_);
        }
        if (_anaNode instanceof CurrentInvokingConstructor) {
            CurrentInvokingConstructor n_ = (CurrentInvokingConstructor) _anaNode;
            return new ExecCurrentInvokingConstructor(n_);
        }
        if (_anaNode instanceof SuperInvokingConstructor) {
            SuperInvokingConstructor n_ = (SuperInvokingConstructor) _anaNode;
            return new ExecSuperInvokingConstructor(n_);
        }
        if (_anaNode instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _anaNode;
            return new ExecCallDynMethodOperation(c_);
        }
        if (_anaNode instanceof InferArrayInstancing) {
            InferArrayInstancing i_ = (InferArrayInstancing) _anaNode;
            return new ExecArrayElementOperation(i_);
        }
        if (_anaNode instanceof ElementArrayInstancing) {
            ElementArrayInstancing e_ = (ElementArrayInstancing) _anaNode;
            return new ExecArrayElementOperation(e_);
        }
        if (_anaNode instanceof DimensionArrayInstancing) {
            DimensionArrayInstancing d_ = (DimensionArrayInstancing) _anaNode;
            return new ExecDimensionArrayInstancing(d_);
        }
        if (_anaNode instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _anaNode;
            return new ExecStandardInstancingOperation(s_);
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            if (a_.getClassMethodId() != null) {
                return new ExecCustArrOperation(a_);
            }
            return new ExecArrOperation(a_);
        }
        if (_anaNode instanceof DeclaringOperation) {
            DeclaringOperation d_ = (DeclaringOperation) _anaNode;
            return new ExecDeclaringOperation(d_);
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation d_ = (IdOperation) _anaNode;
            if (d_.isStandard()) {
                return new ExecIdOperation(d_);
            }
            return new ExecMultIdOperation(d_);
        }
        if (_anaNode instanceof EnumValueOfOperation) {
            EnumValueOfOperation d_ = (EnumValueOfOperation) _anaNode;
            return new ExecEnumValueOfOperation(d_);
        }
        if (_anaNode instanceof ValuesOperation) {
            ValuesOperation d_ = (ValuesOperation) _anaNode;
            return new ExecValuesOperation(d_);
        }
        if (_anaNode instanceof AbstractTernaryOperation) {
            AbstractTernaryOperation t_ = (AbstractTernaryOperation) _anaNode;
            return new ExecTernaryOperation(t_);
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            return new ExecChoiceFctOperation(c_);
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            return new ExecSuperFctOperation(s_);
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            return new ExecFctOperation(f_);
        }
        if (_anaNode instanceof FirstOptOperation) {
            FirstOptOperation f_ = (FirstOptOperation) _anaNode;
            return new ExecFirstOptOperation(f_);
        }
        if (_anaNode instanceof StaticAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new ExecStaticAccessOperation(f_);
        }
        if (_anaNode instanceof StaticCallAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new ExecStaticAccessOperation(f_);
        }
        if (_anaNode instanceof VarargOperation) {
            VarargOperation f_ = (VarargOperation) _anaNode;
            return new ExecVarargOperation(f_);
        }
        if (_anaNode instanceof DefaultValueOperation) {
            DefaultValueOperation f_ = (DefaultValueOperation) _anaNode;
            return new ExecDefaultValueOperation(f_);
        }
        if (_anaNode instanceof IdFctOperation) {
            IdFctOperation f_ = (IdFctOperation) _anaNode;
            return new ExecIdFctOperation(f_);
        }
        if (_anaNode instanceof LambdaOperation) {
            LambdaOperation f_ = (LambdaOperation) _anaNode;
            return new ExecLambdaOperation(f_);
        }
        if (_anaNode instanceof StaticInfoOperation) {
            StaticInfoOperation f_ = (StaticInfoOperation) _anaNode;
            return new ExecStaticInfoOperation(f_);
        }
        if (_anaNode instanceof ThisOperation) {
            ThisOperation f_ = (ThisOperation) _anaNode;
            return new ExecThisOperation(f_);
        }
        if (_anaNode instanceof ParentInstanceOperation) {
            ParentInstanceOperation f_ = (ParentInstanceOperation) _anaNode;
            return new ExecParentInstanceOperation(f_);
        }
        if (_anaNode instanceof ForwardOperation) {
            ForwardOperation f_ = (ForwardOperation) _anaNode;
            return new ExecForwardOperation(f_);
        }
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation s_ = (SettableAbstractFieldOperation) _anaNode;
            if (s_.getFieldId() == null) {
                OperationsSequence tmpOp_ = new OperationsSequence();
                tmpOp_.setDelimiter(new Delimiters());
                ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
                e_.setResultClass(s_.getResultClass());
                return new ExecErrorParentOperation(e_);
            }
            return new ExecSettableFieldOperation(s_);
        }
        if (_anaNode instanceof ArrayFieldOperation) {
            ArrayFieldOperation s_ = (ArrayFieldOperation) _anaNode;
            return new ExecArrayFieldOperation(s_);
        }
        if (_anaNode instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation m_ = (MutableLoopVariableOperation) _anaNode;
            return new ExecMutableLoopVariableOperation(m_);
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation m_ = (VariableOperation) _anaNode;
            return new ExecVariableOperation(m_);
        }
        if (_anaNode instanceof FinalVariableOperation) {
            FinalVariableOperation m_ = (FinalVariableOperation) _anaNode;
            return new ExecFinalVariableOperation(m_);
        }
        if (_anaNode instanceof ValueOperation) {
            ValueOperation m_ = (ValueOperation) _anaNode;
            return new ExecValueOperation(m_);
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new ExecDotOperation(m_);
        }
        if (_anaNode instanceof SafeDotOperation) {
            SafeDotOperation m_ = (SafeDotOperation) _anaNode;
            return new ExecSafeDotOperation(m_);
        }
        if (_anaNode instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation m_ = (ExplicitOperatorOperation) _anaNode;
            return new ExecExplicitOperatorOperation(m_);
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            return new ExecSemiAffectationOperation(m_);
        }
        if (_anaNode instanceof UnaryBooleanOperation) {
            UnaryBooleanOperation m_ = (UnaryBooleanOperation) _anaNode;
            return new ExecUnaryBooleanOperation(m_);
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (!n_.isOkNum()) {
                return new ExecErrorParentOperation(n_);
            }
            if (n_.getClassMethodId() != null) {
                return new ExecCustNumericOperation(n_);
            }
        }
        if (_anaNode instanceof UnaryBinOperation) {
            UnaryBinOperation m_ = (UnaryBinOperation) _anaNode;
            return new ExecUnaryBinOperation(m_);
        }
        if (_anaNode instanceof UnaryOperation) {
            UnaryOperation m_ = (UnaryOperation) _anaNode;
            return new ExecUnaryOperation(m_);
        }
        if (_anaNode instanceof CastOperation) {
            CastOperation m_ = (CastOperation) _anaNode;
            return new ExecCastOperation(m_);
        }
        if (_anaNode instanceof ExplicitOperation) {
            ExplicitOperation m_ = (ExplicitOperation) _anaNode;
            return new ExecExplicitOperation(m_);
        }
        if (_anaNode instanceof MultOperation) {
            MultOperation m_ = (MultOperation) _anaNode;
            return new ExecMultOperation(m_);
        }
        if (_anaNode instanceof AddOperation) {
            AddOperation m_ = (AddOperation) _anaNode;
            if (m_.isCatString()) {
                return new ExecCatOperation(m_);
            }
            return new ExecAddOperation(m_);
        }
        if (_anaNode instanceof ShiftLeftOperation) {
            ShiftLeftOperation m_ = (ShiftLeftOperation) _anaNode;
            return new ExecShiftLeftOperation(m_);
        }
        if (_anaNode instanceof ShiftRightOperation) {
            ShiftRightOperation m_ = (ShiftRightOperation) _anaNode;
            return new ExecShiftRightOperation(m_);
        }
        if (_anaNode instanceof BitShiftLeftOperation) {
            BitShiftLeftOperation m_ = (BitShiftLeftOperation) _anaNode;
            return new ExecBitShiftLeftOperation(m_);
        }
        if (_anaNode instanceof BitShiftRightOperation) {
            BitShiftRightOperation m_ = (BitShiftRightOperation) _anaNode;
            return new ExecBitShiftRightOperation(m_);
        }
        if (_anaNode instanceof RotateLeftOperation) {
            RotateLeftOperation m_ = (RotateLeftOperation) _anaNode;
            return new ExecRotateLeftOperation(m_);
        }
        if (_anaNode instanceof RotateRightOperation) {
            RotateRightOperation m_ = (RotateRightOperation) _anaNode;
            return new ExecRotateRightOperation(m_);
        }
        if (_anaNode instanceof CmpOperation) {
            CmpOperation c_ = (CmpOperation) _anaNode;
            if (!c_.isStringCompare()) {
                return new ExecNbCmpOperation(c_);
            }
            return new ExecStrCmpOperation(c_);
        }
        if (_anaNode instanceof InstanceOfOperation) {
            InstanceOfOperation c_ = (InstanceOfOperation) _anaNode;
            return new ExecInstanceOfOperation(c_);
        }
        if (_anaNode instanceof EqOperation) {
            EqOperation c_ = (EqOperation) _anaNode;
            return new ExecEqOperation(c_);
        }
        if (_anaNode instanceof BitAndOperation) {
            BitAndOperation c_ = (BitAndOperation) _anaNode;
            return new ExecBitAndOperation(c_);
        }
        if (_anaNode instanceof BitOrOperation) {
            BitOrOperation c_ = (BitOrOperation) _anaNode;
            return new ExecBitOrOperation(c_);
        }
        if (_anaNode instanceof BitXorOperation) {
            BitXorOperation c_ = (BitXorOperation) _anaNode;
            return new ExecBitXorOperation(c_);
        }
        if (_anaNode instanceof QuickOperation) {
            QuickOperation q_ = (QuickOperation) _anaNode;
            if (!q_.isOkNum()) {
                return new ExecErrorParentOperation(q_);
            }
        }
        if (_anaNode instanceof AndOperation) {
            AndOperation c_ = (AndOperation) _anaNode;
            return new ExecAndOperation(c_);
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            return new ExecOrOperation(c_);
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation c_ = (NullSafeOperation) _anaNode;
            return new ExecNullSafeOperation(c_);
        }
        if (_anaNode instanceof AssocationOperation) {
            AssocationOperation c_ = (AssocationOperation) _anaNode;
            return new ExecAssocationOperation(c_);
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _anaNode;
            return new ExecCompoundAffectationOperation(c_);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new ExecAffectationOperation(a_);
        }
        return new ExecErrorParentOperation(_anaNode);
    }

    public abstract ExecOperationNode getFirstChild();

    public final ExecOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(ExecOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    protected static Argument getPreviousArg(ExecPossibleIntermediateDotted _possible,IdMap<ExecOperationNode,ArgumentsPair> _nodes,  ContextEl _conf) {
        Argument previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, _possible);
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        return previous_;
    }
    protected static CustList<Argument> getArguments(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecMethodOperation _method) {
        CustList<Argument> a_ = new CustList<Argument>();
        for (ExecOperationNode o: _method.getChildrenNodes()) {
            a_.add(getArgument(_nodes, o));
        }
        return a_;
    }
    protected static Argument getArgument(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecOperationNode _node) {
        return getArgumentPair(_nodes,_node).getArgument();
    }
    protected static ArgumentsPair getArgumentPair(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecOperationNode _node) {
        return _nodes.getValue(_node.getOrder());
    }
    protected static Argument getPreviousArgument(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecPossibleIntermediateDotted _node) {
        return _nodes.getValue(_node.getOrder()).getPreviousArgument();
    }
    protected static CustList<ExecOperationNode> filterInvoking(CustList<ExecOperationNode> _list) {
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        for (ExecOperationNode o: _list) {
            if (o instanceof ExecStaticInitOperation) {
                continue;
            }
            out_.add(o);
        }
        return out_;
    }
    protected static CustList<Argument> filterInvoking(CustList<ExecOperationNode> _list, IdMap<ExecOperationNode,ArgumentsPair> _nodes) {
        CustList<Argument> out_ = new CustList<Argument>();
        for (ExecOperationNode o: _list) {
            if (o instanceof ExecStaticInitOperation) {
                continue;
            }
            out_.add(getArgument(_nodes,o));
        }
        return out_;
    }

    private void setNextSiblingsArg(Argument _arg, ContextEl _cont) {
        if (_cont.callsOrException()) {
            return;
        }
        String un_ = resultClass.getUnwrapObject();
        if (resultClass.isCheckOnlyNullPe() || !un_.isEmpty()) {
            if (_arg.isNull()) {
                LgNames stds_ = _cont.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _cont.setException(new ErrorStruct(_cont,null_));
                return;
            }
        }
        if (!un_.isEmpty()) {
            _arg.setStruct(PrimitiveTypeUtil.unwrapObject(un_, _arg.getStruct(), _cont.getStandards()));
        }
    }


    public final ExecMethodOperation getParent() {
        return parent;
    }
    public static int getNextIndex(ExecOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        ExecMethodOperation par_ = _operation.getParent();
        if (par_ instanceof ExecCompoundAffectationOperation) {
            ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation)par_;
            if (StringList.quickEq(p_.getOper(),"??=")) {
                if (_value != NullStruct.NULL_VALUE) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof ExecCompoundAffectationOperation) {
            ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation)par_;
            if (StringList.quickEq(p_.getOper(),"&&=")) {
                if (BooleanStruct.of(false).sameReference(_value)) {
                    return par_.getOrder();
                }
            }
            if (StringList.quickEq(p_.getOper(),"||=")) {
                if (BooleanStruct.of(true).sameReference(_value)) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof ExecSafeDotOperation) {
            if (_value == NullStruct.NULL_VALUE) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ExecNullSafeOperation) {
            if (_value != NullStruct.NULL_VALUE) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ExecQuickOperation) {
            ExecQuickOperation q_ = (ExecQuickOperation) par_;
            BooleanStruct bs_ = q_.absorbingStruct();
            if (bs_.sameReference(_value)) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ExecTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0) {
                BooleanStruct bs_ = BooleanStruct.of(false);
                if (bs_.sameReference(_value)) {
                    return _operation.getNextSibling().getOrder() + 1;
                }
            }
        }
        return _operation.getOrder() + 1;
    }

    @Override
    public final int getOrder() {
        return order;
    }

    public final void setOrder(int _order) {
        order = _order;
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

    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        setQuickConvertSimpleArgument(_argument, _conf, _nodes);
        setNextSiblingsArg(_argument, _conf);
    }

    protected final void setQuickNoConvertSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(false,_argument, _conf, _nodes);
    }
    protected final void setQuickConvertSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(true,_argument, _conf, _nodes);
    }
    protected final void setQuickSimpleArgument(boolean _convertToString,Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        if (_conf.callsOrException()) {
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        Argument arg_ = _argument;
        if (_convertToString && resultClass.isConvertToString()){
            pair_.setCalledToString(true);
            arg_ = processString(_argument,_conf);
            if (_conf.getCallingState() != null) {
                return;
            }
        }
        ExecPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getValue(n_.getOrder()).setPreviousArgument(arg_);
        }
        _nodes.getValue(getOrder()).setArgument(arg_);
        _conf.getCoverage().passBlockOperation(_conf, this,arg_,!_convertToString);
    }

    public static Argument processString(Argument _argument, ContextEl _conf) {
        Argument out_ = new Argument();
        out_.setStruct(_argument.getStruct());
        Struct struct_ = _argument.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            out_.setStruct(((DisplayableStruct)struct_).getDisplayedString(_conf));
        } else {
            String argClassName_ = _conf.getStandards().getStructClassName(struct_, _conf);
            ClassMethodIdReturn resDyn_ = OperationNode.tryGetDeclaredToString(_conf, argClassName_);
            ClassMethodId methodId_ = null;
            if (resDyn_.isFoundMethod()) {
                String foundClass_ = resDyn_.getRealClass();
                MethodId id_ = resDyn_.getRealId();
                methodId_ = new ClassMethodId(foundClass_, id_);
            }
            if (methodId_ == null) {
                out_.setStruct(_conf.getStandards().getStringOfObject(_conf,struct_));
            } else {
                ClassMethodId polymorph_ = ExecInvokingOperation.polymorph(_conf, struct_, methodId_);
                String className_ = polymorph_.getClassName();
                className_ = Templates.quickFormat(argClassName_,className_,_conf);
                MethodId ct_ = polymorph_.getConstraints();
                _conf.setCallingState(new CustomFoundMethod(out_,className_,ct_,new CustList<Argument>(),null));
            }
        }
        return out_;
    }
    @Override
    public final void setSimpleArgumentAna(Argument _argument, ContextEl _conf) {
        OperationNode.setArgAna(this, _argument, _conf);
    }

    @Override
    public PossibleIntermediateDottedOperable getSiblingSettable() {
        return getSiblingSet();
    }

    @Override
    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final ExecPossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(ExecPossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    @Override
    public ParentOperable getParentOperable() {
        return getParent();
    }
}
