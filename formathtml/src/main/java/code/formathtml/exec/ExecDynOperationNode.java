package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractTernaryOperation;
import code.expressionlanguage.opers.AddOperation;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.AndOperation;
import code.expressionlanguage.opers.AnnotationInstanceOperation;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.opers.ArrayFieldOperation;
import code.expressionlanguage.opers.AssocationOperation;
import code.expressionlanguage.opers.BitAndOperation;
import code.expressionlanguage.opers.BitOrOperation;
import code.expressionlanguage.opers.BitShiftLeftOperation;
import code.expressionlanguage.opers.BitShiftRightOperation;
import code.expressionlanguage.opers.BitXorOperation;
import code.expressionlanguage.opers.CallDynMethodOperation;
import code.expressionlanguage.opers.CastOperation;
import code.expressionlanguage.opers.ChoiceFctOperation;
import code.expressionlanguage.opers.CmpOperation;
import code.expressionlanguage.opers.CompoundAffectationOperation;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.CurrentInvokingConstructor;
import code.expressionlanguage.opers.DeclaringOperation;
import code.expressionlanguage.opers.DimensionArrayInstancing;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.ElementArrayInstancing;
import code.expressionlanguage.opers.EnumValueOfOperation;
import code.expressionlanguage.opers.EqOperation;
import code.expressionlanguage.opers.ErrorPartOperation;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.FinalVariableOperation;
import code.expressionlanguage.opers.FirstOptOperation;
import code.expressionlanguage.opers.IdFctOperation;
import code.expressionlanguage.opers.IdOperation;
import code.expressionlanguage.opers.InferArrayInstancing;
import code.expressionlanguage.opers.InstanceOfOperation;
import code.expressionlanguage.opers.InterfaceInvokingConstructor;
import code.expressionlanguage.opers.LambdaOperation;
import code.expressionlanguage.opers.MultOperation;
import code.expressionlanguage.opers.MutableLoopVariableOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.OrOperation;
import code.expressionlanguage.opers.RotateLeftOperation;
import code.expressionlanguage.opers.RotateRightOperation;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.ShiftLeftOperation;
import code.expressionlanguage.opers.ShiftRightOperation;
import code.expressionlanguage.opers.StandardInstancingOperation;
import code.expressionlanguage.opers.StaticAccessOperation;
import code.expressionlanguage.opers.StaticInfoOperation;
import code.expressionlanguage.opers.StaticInitOperation;
import code.expressionlanguage.opers.SuperFctOperation;
import code.expressionlanguage.opers.SuperInvokingConstructor;
import code.expressionlanguage.opers.ThisOperation;
import code.expressionlanguage.opers.UnaryBinOperation;
import code.expressionlanguage.opers.UnaryBooleanOperation;
import code.expressionlanguage.opers.UnaryOperation;
import code.expressionlanguage.opers.ValuesOperation;
import code.expressionlanguage.opers.VarargOperation;
import code.expressionlanguage.opers.VariableOperation;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecDynOperationNode implements Operable {


    protected static final String ARR = "[";

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String PLUS = "+";

    protected static final String LOWER_EQ = "<=";

    protected static final String LOWER = "<";

    protected static final String GREATER_EQ = ">=";

    protected static final String GREATER = ">";

    protected static final String DIFF = "!=";

    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";

    private static final int QUICK_OP = 3;

    private ExecMethodOperation parent;

    private ExecDynOperationNode nextSibling;

    private Argument argument;

    private int indexInEl;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private final int indexChild;

    private ClassArgumentMatching resultClass;

    private boolean staticBlock;

    private ExecPossibleIntermediateDotted siblingSet;

    private int indexBegin;

    ExecDynOperationNode(OperationNode _oper) {
        indexInEl = _oper.getIndexInEl();
        indexBegin = _oper.getIndexBegin();
        indexChild = _oper.getIndexChild();
        resultClass = _oper.getResultClass();
        staticBlock = _oper.isStaticBlock();
        argument = _oper.getArgument();
        order = _oper.getOrder();
    }

    public void setArgument(Argument _argument) {
        argument = _argument;
    }

    public void setIndexInEl(int _indexInEl) {
        indexInEl = _indexInEl;
    }

    public void setIndexBegin(int _indexBegin) {
        indexBegin = _indexBegin;
    }

    public void setParent(ExecMethodOperation _parent) {
        parent = _parent;
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, ExecutableCode _cont) {
        _cont.setOffset(indexBegin+_offset);
    }

    public final int getIndexBegin() {
        return indexBegin;
    }
    public static Operable createExecOperationNode(OperationNode _anaNode,Analyzable _conf) {
        if (_anaNode instanceof InternGlobalOperation) {
            InternGlobalOperation m_ = (InternGlobalOperation) _anaNode;
            return new ExecInternGlobalOperation(m_);
        }
        if (_anaNode instanceof StaticInitOperation) {
            StaticInitOperation c_ = (StaticInitOperation) _anaNode;
            return new ExecStaticInitOperation(c_);
        }
        if (_anaNode instanceof ErrorPartOperation) {
            ErrorPartOperation c_ = (ErrorPartOperation) _anaNode;
            return new ExecErrorPartOperation(c_);
        }
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new ExecConstantOperation(c_);
        }
        if (_anaNode instanceof AnnotationInstanceOperation) {
            AnnotationInstanceOperation n_ = (AnnotationInstanceOperation) _anaNode;
            return new ExecAnnotationInstanceOperation(n_);
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
            return new ExecInferArrayInstancing(i_);
        }
        if (_anaNode instanceof ElementArrayInstancing) {
            ElementArrayInstancing e_ = (ElementArrayInstancing) _anaNode;
            return new ExecElementArrayInstancing(e_);
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
            return new ExecArrOperation(a_);
        }
        if (_anaNode instanceof DeclaringOperation) {
            DeclaringOperation d_ = (DeclaringOperation) _anaNode;
            return new ExecDeclaringOperation(d_);
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation d_ = (IdOperation) _anaNode;
            return new ExecIdOperation(d_);
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
            StaticAccessOperation f_ = (StaticAccessOperation) _anaNode;
            return new ExecStaticAccessOperation(f_);
        }
        if (_anaNode instanceof VarargOperation) {
            VarargOperation f_ = (VarargOperation) _anaNode;
            return new ExecVarargOperation(f_);
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
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation s_ = (SettableAbstractFieldOperation) _anaNode;
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
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new ExecDotOperation(m_);
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            return new ExecSemiAffectationOperation(m_);
        }
        if (_anaNode instanceof UnaryBooleanOperation) {
            UnaryBooleanOperation m_ = (UnaryBooleanOperation) _anaNode;
            return new ExecUnaryBooleanOperation(m_);
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
        if (_anaNode instanceof MultOperation) {
            MultOperation m_ = (MultOperation) _anaNode;
            return new ExecMultOperation(m_);
        }
        if (_anaNode instanceof AddOperation) {
            AddOperation m_ = (AddOperation) _anaNode;
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
            if (!c_.isOkNum()) {
                ErrorPartOperation err_ = new ErrorPartOperation(c_.getIndexInEl(), c_.getIndexChild(), c_.getParent(), c_.getOperations());
                return new ExecErrorPartOperation(err_);
            }
            return new ExecAbstractCmpOperation(c_);
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
        if (_anaNode instanceof AndOperation) {
            AndOperation c_ = (AndOperation) _anaNode;
            return new ExecAndOperation(c_);
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            return new ExecOrOperation(c_);
        }
        if (_anaNode instanceof AssocationOperation) {
            AssocationOperation c_ = (AssocationOperation) _anaNode;
            return new ExecAssocationOperation(c_);
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _anaNode;
            return new ExecCompoundAffectationOperation(c_);
        }
        AffectationOperation a_ = (AffectationOperation) _anaNode;
        return new ExecAffectationOperation(a_);
    }

    final boolean isFirstChild() {
        ExecMethodOperation par_ = getParent();
        if (par_ == null) {
            return true;
        }
        if (par_.getFirstChild() instanceof ExecStaticInitOperation) {
            return getIndexChild() == CustList.SECOND_INDEX;
        }
        return getIndexChild() == CustList.FIRST_INDEX;
    }

    public abstract ExecDynOperationNode getFirstChild();

    public final ExecDynOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(ExecDynOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    protected static Argument getPreviousArg(ExecPossibleIntermediateDotted _possible,IdMap<ExecDynOperationNode,ArgumentsPair> _nodes,  ContextEl _conf) {
        Argument previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, _possible);
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        return previous_;
    }
    public static CustList<Argument> getArguments(IdMap<ExecDynOperationNode,ArgumentsPair> _nodes, ExecMethodOperation _method) {
        CustList<Argument> a_ = new CustList<Argument>();
        for (ExecDynOperationNode o: _method.getChildrenNodes()) {
            a_.add(getArgument(_nodes, o));
        }
        return a_;
    }
    public static Argument getArgument(IdMap<ExecDynOperationNode,ArgumentsPair> _nodes, ExecDynOperationNode _node) {
        return _nodes.getValue(_node.getOrder()).getArgument();
    }
    public static Argument getPreviousArgument(IdMap<ExecDynOperationNode,ArgumentsPair> _nodes, ExecPossibleIntermediateDotted _node) {
        return _nodes.getValue(_node.getOrder()).getPreviousArgument();
    }
    public static CustList<ExecDynOperationNode> filterInvoking(CustList<ExecDynOperationNode> _list) {
        CustList<ExecDynOperationNode> out_ = new CustList<ExecDynOperationNode>();
        for (ExecDynOperationNode o: _list) {
            if (o instanceof ExecStaticInitOperation) {
                continue;
            }
            out_.add(o);
        }
        return out_;
    }
    public static CustList<Argument> filterInvoking(CustList<ExecDynOperationNode> _list, IdMap<ExecDynOperationNode,ArgumentsPair> _nodes) {
        CustList<Argument> out_ = new CustList<Argument>();
        for (ExecDynOperationNode o: _list) {
            if (o instanceof ExecStaticInitOperation) {
                continue;
            }
            out_.add(getArgument(_nodes,o));
        }
        return out_;
    }
    final void setNextSiblingsArg(Argument _arg, ExecutableCode _cont) {
        if (_cont.getContextEl().hasException()) {
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
        int res_ = processBooleanValues(_arg, _cont);
        if (res_ <= 0) {
            return;
        }
        ExecMethodOperation par_ = getParent();
        BooleanStruct o_ = (BooleanStruct) _arg.getStruct();
        Boolean b_ = o_.getInstance();
        if (res_ != QUICK_OP) {
            CustList<ExecDynOperationNode> l_ = par_.getChildrenNodes();
            ExecDynOperationNode opElt_ = l_.get(res_);
            opElt_.setSimpleArgument(_arg);
            return;
        }
        ExecQuickOperation q_ = (ExecQuickOperation) par_;
        if (b_ == q_.absorbingValue()) {
            CustList<ExecDynOperationNode> opers_ = new CustList<ExecDynOperationNode>();
            for (ExecDynOperationNode s: par_.getChildrenNodes()) {
                opers_.add(s);
            }
            int len_ = opers_.size();
            for (int i = getIndexChild() + 1; i < len_; i++) {
                opers_.get(i).setSimpleArgument(_arg);
            }
        }
    }

    private void setNextSiblingsArg(Argument _arg, ContextEl _cont, IdMap<ExecDynOperationNode, ArgumentsPair> _nodes) {
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

    final int processBooleanValues(Argument _arg, ExecutableCode _cont) {
        Struct o_ = _arg.getStruct();
        ExecMethodOperation par_ = getParent();
        if (!(o_ instanceof BooleanStruct)) {
            return 0;
        }
        if (!(par_ instanceof ExecQuickOperation)) {
            boolean ternaryParent_ = false;
            if (par_ instanceof ExecTernaryOperation) {
                ternaryParent_ = isFirstChild();
            }
            if (!ternaryParent_) {
                return 0;
            }
            Boolean b_ = ((BooleanStruct) o_).getInstance();
            if (b_) {
                return 2;
            }
            return 1;
        }
        return QUICK_OP;
    }

    public final ExecMethodOperation getParent() {
        return parent;
    }
    public static int getNextIndex(ExecDynOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        ExecMethodOperation par_ = _operation.getParent();
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
                BooleanStruct bs_ = new BooleanStruct(false);
                if (bs_.sameReference(_value)) {
                    return _operation.getNextSibling().getOrder() + 1;
                }
            }
        }
        return _operation.getOrder() + 1;
    }
    public static boolean isDeclaringField(ExecSettableElResult _var, Analyzable _an) {
        Block bl_ = _an.getCurrentBlock();
        if (!(bl_ instanceof FieldBlock)) {
            return false;
        }
        if (!(_var instanceof ExecSettableFieldOperation)) {
            return false;
        }
        return isDeclaringVariable((ExecSettableFieldOperation) _var);
    }

    private static boolean isDeclaringVariable(ExecLeafOperation _var) {
        ExecMethodOperation par_ = _var.getParent();
        if (par_ == null) {
            return true;
        }
        if (par_ instanceof ExecDeclaringOperation) {
            return true;
        }
        if (par_ instanceof ExecAffectationOperation) {
            if (par_.getParent() == null) {
                if (_var == par_.getFirstChild()) {
                    return true;
                }
            }
            if (par_.getParent() instanceof ExecDeclaringOperation) {
                if (_var == par_.getFirstChild()) {
                    return true;
                }
            }
        }
        return false;
    }
    public final int getOrder() {
        return order;
    }

    public final void setOrder(int _order) {
        order = _order;
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

    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecDynOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(_argument, _conf, _nodes);
        setNextSiblingsArg(_argument, _conf, _nodes);
    }

    public final void setQuickSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecDynOperationNode, ArgumentsPair> _nodes) {
        if (_conf.callsOrException()) {
            return;
        }
        ExecPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getVal((ExecDynOperationNode)n_).setPreviousArgument(_argument);
        }
        _nodes.getVal(this).setArgument(_argument);
    }

    public final void setSimpleArgument(Argument _argument, ExecutableCode _conf) {
        setQuickSimpleArgument(_argument, _conf);
        setNextSiblingsArg(_argument, _conf);
    }

    public final void setQuickSimpleArgument(Argument _argument, ExecutableCode _conf) {
        argument = _argument;
        ExecPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            n_.setPreviousArgument(_argument);
        }
    }

    @Override
    public final void setSimpleArgumentAna(Argument _argument, Analyzable _conf) {
        ExecPossibleIntermediateDotted n_ = getSiblingSet();
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

    public final ExecPossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(ExecPossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }
}
