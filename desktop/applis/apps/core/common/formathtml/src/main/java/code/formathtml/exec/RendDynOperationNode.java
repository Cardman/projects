package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AnnotationBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.variables.ArgumentsPair;

import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public abstract class RendDynOperationNode {

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String PLUS = "+";

    protected static final String DIFF = "!=";

    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";

    private RendMethodOperation parent;

    private RendDynOperationNode nextSibling;

    private Argument argument;

    private int indexInEl;

    private int order;

    private final int indexChild;

    private ClassArgumentMatching resultClass;

    private RendPossibleIntermediateDotted siblingSet;

    private int indexBegin;

    private ImplicitMethods implicits = new ImplicitMethods();
    private ImplicitMethods implicitsTest = new ImplicitMethods();
    RendDynOperationNode(OperationNode _oper) {
        indexInEl = _oper.getIndexInEl();
        indexBegin = _oper.getIndexBegin();
        indexChild = _oper.getIndexChild();
        resultClass = _oper.getResultClass();
        argument = _oper.getArgument();
        order = _oper.getOrder();
    }

    RendDynOperationNode(int _indexChild, ClassArgumentMatching _res, int _order) {
        indexChild = _indexChild;
        resultClass = _res;
        order = _order;
    }
    public void setParent(RendMethodOperation _parent) {
        parent = _parent;
    }
    protected static Argument processCall(RendCallable _node, RendDynOperationNode _method,
                                          Argument _previous, CustList<Argument> _arguments,
                                          Configuration _conf, Argument _right) {
        Argument argres_ = _node.getArgument(_previous, _arguments, _conf, _right);
        argres_ = init(argres_,_node,_previous,_arguments,_conf,_right);
        argres_ = init(argres_,_node,_previous,_arguments,_conf,_right);
        return _method.processCall(_conf,argres_);
    }
    private static Argument init(Argument _before,
                         RendCallable _node,
                         Argument _previous, CustList<Argument> _arguments,
                         Configuration _conf, Argument _right) {
        CallingState state_ = _conf.getContext().getCallingState();
        Argument before_ = _before;
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
            if (_conf.getContext().hasException()) {
                return Argument.createVoid();
            }
            before_ = _node.getArgument(_previous, _arguments, _conf, _right);
        }
        return before_;
    }
    private Argument processCall(Configuration _conf, Argument _res) {
        CallingState callingState_ = _conf.getContext().getCallingState();
        Argument res_;
        if (callingState_ instanceof CustomFoundConstructor) {
            CustomFoundConstructor ctor_ = (CustomFoundConstructor)callingState_;
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(),ctor_.getType(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContext());
        } else if (callingState_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) callingState_;
            res_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), _conf.getContext(),method_.getRight());
        } else if (callingState_ instanceof CustomReflectMethod) {
            CustomReflectMethod ref_ = (CustomReflectMethod) callingState_;
            res_ = ProcessMethod.reflectArgument(ref_.getGl(), ref_.getArguments(), _conf.getContext(), ref_.getReflect(), ref_.isLambda());
        } else if (callingState_ instanceof CustomFoundCast) {
            CustomFoundCast cast_ = (CustomFoundCast) callingState_;
            res_ = ProcessMethod.castArgument(cast_.getClassName(),cast_.getId(), cast_.getArguments(), _conf.getContext());
        } else {
            res_ = _res;
        }
        return res_;
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, Configuration _cont) {
        _cont.setOpOffset(indexBegin+_offset);
    }

    public static RendDynOperationNode createExecOperationNode(OperationNode _anaNode, ContextEl _cont) {
        if (_anaNode instanceof InternGlobalOperation) {
            InternGlobalOperation m_ = (InternGlobalOperation) _anaNode;
            return new RendInternGlobalOperation(m_);
        }
        if (_anaNode instanceof StaticInitOperation) {
            StaticInitOperation c_ = (StaticInitOperation) _anaNode;
            return new RendStaticInitOperation(c_);
        }
        if (_anaNode instanceof ErrorPartOperation) {
            ErrorPartOperation c_ = (ErrorPartOperation) _anaNode;
            return new RendErrorParentOperation(c_);
        }
        if (_anaNode instanceof ForwardOperation) {
            ForwardOperation c_ = (ForwardOperation) _anaNode;
            return new RendForwardOperation(c_);
        }
        if (_anaNode instanceof ConstantOperation) {
            ConstantOperation c_ = (ConstantOperation) _anaNode;
            return new RendConstantOperation(c_);
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            if (f_.isClonedMethod()) {
                return new RendCloneOperation(f_);
            }
        }
        if (_anaNode instanceof InvokingOperation && _anaNode instanceof AbstractCallFctOperation) {
            InvokingOperation i_ = (InvokingOperation) _anaNode;
            AbstractCallFctOperation a_ = (AbstractCallFctOperation) _anaNode;
            ClassMethodId classMethodId_ = a_.getClassMethodId();
            if (classMethodId_ != null) {
                if (a_.getStandardMethod() != null) {
                    return new RendStdFctOperation(i_,a_);
                }
                String className_ = classMethodId_.getClassName();
                className_ = StringExpUtil.getIdFromAllTypes(className_);
                RootBlock classBody_ = _cont.getAnalyzing().getAnaClassBody(className_);
                if (classBody_ instanceof AnnotationBlock) {
                    return new RendAnnotationMethodOperation(i_,a_);
                }
            }
        }
        if (_anaNode instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _anaNode;
            return new RendCallDynMethodOperation(c_);
        }
        if (_anaNode instanceof InferArrayInstancing) {
            InferArrayInstancing i_ = (InferArrayInstancing) _anaNode;
            return new RendArrayElementOperation(i_);
        }
        if (_anaNode instanceof ElementArrayInstancing) {
            ElementArrayInstancing e_ = (ElementArrayInstancing) _anaNode;
            return new RendArrayElementOperation(e_);
        }
        if (_anaNode instanceof DimensionArrayInstancing) {
            DimensionArrayInstancing d_ = (DimensionArrayInstancing) _anaNode;
            return new RendDimensionArrayInstancing(d_);
        }
        if (_anaNode instanceof StandardInstancingOperation) {
            StandardInstancingOperation s_ = (StandardInstancingOperation) _anaNode;
            ExecNamedFunctionBlock ctor_ = ExecOperationNode.fetchFunction(_cont, s_.getRootNumber(), s_.getMemberNumber());
            ExecRootBlock rootBlock_ = ExecOperationNode.fetchType(_cont, s_.getRootNumber());
            if (rootBlock_ == null) {
                return new RendDirectStandardInstancingOperation(s_);
            }
            return new RendStandardInstancingOperation(s_,rootBlock_,ctor_);
        }
        if (_anaNode instanceof InterfaceFctConstructor) {
            InterfaceFctConstructor s_ = (InterfaceFctConstructor) _anaNode;
            return new RendInterfaceFctConstructor(s_,_cont);
        }
        if (_anaNode instanceof ArrOperation) {
            ArrOperation a_ = (ArrOperation) _anaNode;
            ExecRootBlock ex_ = ExecOperationNode.fetchType(_cont,a_.getRootNumber());
            ExecNamedFunctionBlock get_ = ExecOperationNode.fetchFunction(a_.getRootNumber(), a_.getMemberNumber(), _cont);
            ExecNamedFunctionBlock set_ = ExecOperationNode.fetchFunction(a_.getRootNumber(), a_.getMemberNumberSet(), _cont);
            if (a_.getClassMethodId() != null) {
                return new RendCustArrOperation(a_,get_,set_,ex_);
            }
            return new RendArrOperation(a_);
        }
        if (_anaNode instanceof DeclaringOperation) {
            DeclaringOperation d_ = (DeclaringOperation) _anaNode;
            return new RendDeclaringOperation(d_);
        }
        if (_anaNode instanceof IdOperation) {
            IdOperation d_ = (IdOperation) _anaNode;
            if (d_.isStandard()) {
                return new RendIdOperation(d_);
            }
            return new RendMultIdOperation(d_);
        }
        if (_anaNode instanceof EnumValueOfOperation) {
            EnumValueOfOperation d_ = (EnumValueOfOperation) _anaNode;
            return new RendEnumValueOfOperation(d_,_cont);
        }
        if (_anaNode instanceof ValuesOperation) {
            ValuesOperation d_ = (ValuesOperation) _anaNode;
            return new RendValuesOperation(d_,_cont);
        }
        if (_anaNode instanceof AbstractTernaryOperation) {
            AbstractTernaryOperation t_ = (AbstractTernaryOperation) _anaNode;
            return new RendTernaryOperation(t_);
        }
        if (_anaNode instanceof ChoiceFctOperation) {
            ChoiceFctOperation c_ = (ChoiceFctOperation) _anaNode;
            if (c_.isTrueFalse()) {
                return new RendExplicitOperation(c_,ExecOperationNode.fetchFunction(c_.getRootNumber(),c_.getMemberNumber(),_cont));
            }
            ExecRootBlock ex_ = ExecOperationNode.fetchType(_cont,c_.getRootNumber());
            if (ex_ != null) {
                ExecNamedFunctionBlock fct_ = ExecOperationNode.fetchFunction(c_, _cont);
                return new RendChoiceFctOperation(c_,fct_);
            }
        }
        if (_anaNode instanceof SuperFctOperation) {
            SuperFctOperation s_ = (SuperFctOperation) _anaNode;
            if (s_.isTrueFalse()) {
                return new RendExplicitOperation(s_,ExecOperationNode.fetchFunction(s_.getRootNumber(),s_.getMemberNumber(),_cont));
            }
            ExecRootBlock ex_ = ExecOperationNode.fetchType(_cont,s_.getRootNumber());
            if (ex_ != null) {
                ExecNamedFunctionBlock fct_ = ExecOperationNode.fetchFunction(s_, _cont);
                return new RendSuperFctOperation(s_,fct_);
            }
        }
        if (_anaNode instanceof FctOperation) {
            FctOperation f_ = (FctOperation) _anaNode;
            if (f_.isTrueFalse()) {
                return new RendExplicitOperation(f_,ExecOperationNode.fetchFunction(f_.getRootNumber(),f_.getMemberNumber(),_cont));
            }
            ExecNamedFunctionBlock fct_ = ExecOperationNode.fetchFunction(f_, _cont);
            ExecRootBlock ex_ = ExecOperationNode.fetchType(_cont,f_.getRootNumber());
            if (ex_ != null) {
                return new RendFctOperation(f_,fct_,ex_);
            }
        }
        if (_anaNode instanceof FirstOptOperation) {
            FirstOptOperation f_ = (FirstOptOperation) _anaNode;
            return new RendFirstOptOperation(f_);
        }
        if (_anaNode instanceof StaticAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new RendStaticAccessOperation(f_);
        }
        if (_anaNode instanceof StaticCallAccessOperation) {
            LeafOperation f_ = (LeafOperation) _anaNode;
            return new RendStaticAccessOperation(f_);
        }
        if (_anaNode instanceof VarargOperation) {
            VarargOperation f_ = (VarargOperation) _anaNode;
            return new RendVarargOperation(f_);
        }
        if (_anaNode instanceof IdFctOperation) {
            IdFctOperation f_ = (IdFctOperation) _anaNode;
            return new RendIdFctOperation(f_);
        }
        if (_anaNode instanceof LambdaOperation) {
            LambdaOperation f_ = (LambdaOperation) _anaNode;
            return new RendLambdaOperation(f_,_cont);
        }
        if (_anaNode instanceof StaticInfoOperation) {
            StaticInfoOperation f_ = (StaticInfoOperation) _anaNode;
            return new RendStaticInfoOperation(f_);
        }
        if (_anaNode instanceof DefaultValueOperation) {
            DefaultValueOperation f_ = (DefaultValueOperation) _anaNode;
            return new RendDefaultValueOperation(f_);
        }
        if (_anaNode instanceof DefaultOperation) {
            DefaultOperation f_ = (DefaultOperation) _anaNode;
            return new RendDefaultOperation(f_);
        }
        if (_anaNode instanceof ThisOperation) {
            ThisOperation f_ = (ThisOperation) _anaNode;
            return new RendThisOperation(f_);
        }
        if (_anaNode instanceof ParentInstanceOperation) {
            ParentInstanceOperation f_ = (ParentInstanceOperation) _anaNode;
            return new RendParentInstanceOperation(f_);
        }
        if (_anaNode instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation s_ = (SettableAbstractFieldOperation) _anaNode;
            if (s_.getFieldId() == null) {
                return new RendErrorParentOperation(_anaNode);
            }
            return new RendSettableFieldOperation(s_);
        }
        if (_anaNode instanceof ArrayFieldOperation) {
            ArrayFieldOperation s_ = (ArrayFieldOperation) _anaNode;
            return new RendArrayFieldOperation(s_);
        }
        if (_anaNode instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation m_ = (MutableLoopVariableOperation) _anaNode;
            return new RendMutableLoopVariableOperation(m_);
        }
        if (_anaNode instanceof VariableOperation) {
            VariableOperation m_ = (VariableOperation) _anaNode;
            return new RendVariableOperation(m_);
        }
        if (_anaNode instanceof FinalVariableOperation) {
            FinalVariableOperation m_ = (FinalVariableOperation) _anaNode;
            return new RendFinalVariableOperation(m_);
        }
        if (_anaNode instanceof DotOperation) {
            DotOperation m_ = (DotOperation) _anaNode;
            return new RendDotOperation(m_);
        }
        if (_anaNode instanceof SafeDotOperation) {
            SafeDotOperation m_ = (SafeDotOperation) _anaNode;
            return new RendSafeDotOperation(m_);
        }
        if (_anaNode instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation m_ = (ExplicitOperatorOperation) _anaNode;
            return new RendExplicitOperatorOperation(m_,_cont);
        }
        if (_anaNode instanceof SemiAffectationOperation) {
            SemiAffectationOperation m_ = (SemiAffectationOperation) _anaNode;
            return new RendSemiAffectationOperation(m_,_cont);
        }
        if (_anaNode instanceof UnaryBooleanOperation) {
            UnaryBooleanOperation m_ = (UnaryBooleanOperation) _anaNode;
            return new RendUnaryBooleanOperation(m_);
        }
        if (_anaNode instanceof UnaryBinOperation) {
            UnaryBinOperation m_ = (UnaryBinOperation) _anaNode;
            return new RendUnaryBinOperation(m_);
        }
        if (_anaNode instanceof SymbolOperation) {
            SymbolOperation n_ = (SymbolOperation) _anaNode;
            if (!n_.isOkNum()) {
                return new RendErrorParentOperation(_anaNode);
            }
            if (n_.getClassMethodId() != null) {
                return new RendCustNumericOperation(n_,_anaNode,ExecOperationNode.fetchFunction(_cont,n_.getRootNumber(),n_.getMemberNumber()));
            }
        }
        if (_anaNode instanceof UnaryOperation) {
            UnaryOperation m_ = (UnaryOperation) _anaNode;
            return new RendUnaryOperation(m_);
        }
        if (_anaNode instanceof CastOperation) {
            CastOperation m_ = (CastOperation) _anaNode;
            return new RendCastOperation(m_);
        }
        if (_anaNode instanceof ExplicitOperation) {
            ExplicitOperation m_ = (ExplicitOperation) _anaNode;
            return new RendExplicitOperation(m_,ExecOperationNode.fetchFunction(m_.getRootNumber(),m_.getMemberNumber(),_cont));
        }
        if (_anaNode instanceof ImplicitOperation) {
            ImplicitOperation m_ = (ImplicitOperation) _anaNode;
            return new RendImplicitOperation(m_,ExecOperationNode.fetchFunction(m_.getRootNumber(),m_.getMemberNumber(),_cont));
        }
        if (_anaNode instanceof MultOperation) {
            MultOperation m_ = (MultOperation) _anaNode;
            return new RendMultOperation(m_);
        }
        if (_anaNode instanceof AddOperation) {
            AddOperation m_ = (AddOperation) _anaNode;
            return new RendAddOperation(m_);
        }
        if (_anaNode instanceof ShiftLeftOperation) {
            ShiftLeftOperation m_ = (ShiftLeftOperation) _anaNode;
            return new RendShiftLeftOperation(m_);
        }
        if (_anaNode instanceof ShiftRightOperation) {
            ShiftRightOperation m_ = (ShiftRightOperation) _anaNode;
            return new RendShiftRightOperation(m_);
        }
        if (_anaNode instanceof BitShiftLeftOperation) {
            BitShiftLeftOperation m_ = (BitShiftLeftOperation) _anaNode;
            return new RendBitShiftLeftOperation(m_);
        }
        if (_anaNode instanceof BitShiftRightOperation) {
            BitShiftRightOperation m_ = (BitShiftRightOperation) _anaNode;
            return new RendBitShiftRightOperation(m_);
        }
        if (_anaNode instanceof RotateLeftOperation) {
            RotateLeftOperation m_ = (RotateLeftOperation) _anaNode;
            return new RendRotateLeftOperation(m_);
        }
        if (_anaNode instanceof RotateRightOperation) {
            RotateRightOperation m_ = (RotateRightOperation) _anaNode;
            return new RendRotateRightOperation(m_);
        }
        if (_anaNode instanceof CmpOperation) {
            CmpOperation c_ = (CmpOperation) _anaNode;
            return new RendAbstractCmpOperation(c_);
        }
        if (_anaNode instanceof InstanceOfOperation) {
            InstanceOfOperation c_ = (InstanceOfOperation) _anaNode;
            return new RendInstanceOfOperation(c_);
        }
        if (_anaNode instanceof EqOperation) {
            EqOperation c_ = (EqOperation) _anaNode;
            return new RendEqOperation(c_);
        }
        if (_anaNode instanceof BitAndOperation) {
            BitAndOperation c_ = (BitAndOperation) _anaNode;
            return new RendBitAndOperation(c_);
        }
        if (_anaNode instanceof BitOrOperation) {
            BitOrOperation c_ = (BitOrOperation) _anaNode;
            return new RendBitOrOperation(c_);
        }
        if (_anaNode instanceof BitXorOperation) {
            BitXorOperation c_ = (BitXorOperation) _anaNode;
            return new RendBitXorOperation(c_);
        }
        if (_anaNode instanceof NullSafeOperation) {
            NullSafeOperation n_ = (NullSafeOperation) _anaNode;
            return new RendNullSafeOperation(n_);
        }
        if (_anaNode instanceof QuickOperation) {
            QuickOperation q_ = (QuickOperation) _anaNode;
            if (!q_.isOkNum()) {
                return new RendErrorParentOperation(q_);
            }
        }
        if (_anaNode instanceof AndOperation) {
            AndOperation c_ = (AndOperation) _anaNode;
            return new RendAndOperation(c_,_cont);
        }
        if (_anaNode instanceof OrOperation) {
            OrOperation c_ = (OrOperation) _anaNode;
            return new RendOrOperation(c_,_cont);
        }
        if (_anaNode instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _anaNode;
            return new RendCompoundAffectationOperation(c_,_cont);
        }
        if (_anaNode instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _anaNode;
            return new RendAffectationOperation(a_);
        }
        return new RendErrorParentOperation(_anaNode);
    }

    public final RendDynOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(RendDynOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    static CustList<RendDynOperationNode> filterInvoking(CustList<RendDynOperationNode> _list) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
        for (RendDynOperationNode o: _list) {
            if (o instanceof RendStaticInitOperation) {
                continue;
            }
            out_.add(o);
        }
        return out_;
    }

    private void setNextSiblingsArg(Argument _arg, Configuration _cont) {
        if (_cont.getContext().hasException()) {
            return;
        }
        String un_ = resultClass.getUnwrapObject();
        if (resultClass.isCheckOnlyNullPe() || !un_.isEmpty()) {
            if (_arg.isNull()) {
                LgNames stds_ = _cont.getStandards();
                String null_;
                null_ = stds_.getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _cont.setException(new ErrorStruct(_cont.getContext(),null_));
                return;
            }
        }
        if (!un_.isEmpty()) {
            _arg.setStruct(PrimitiveTypeUtil.unwrapObject(un_, _arg.getStruct(), _cont.getStandards()));
        }
    }

    protected static Argument getPreviousArg(RendPossibleIntermediateDotted _possible, IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, _possible);
        } else {
            previous_ = _conf.getLastPage().getGlobalArgument();
        }
        return previous_;
    }
    protected static CustList<Argument> getArguments(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendMethodOperation _method) {
        CustList<Argument> a_ = new CustList<Argument>();
        for (RendDynOperationNode o: _method.getChildrenNodes()) {
            a_.add(getArgument(_nodes, o));
        }
        return a_;
    }
    protected static Argument getArgument(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return getArgumentPair(_nodes,_node).getArgument();
    }
    protected static ArgumentsPair getArgumentPair(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return _nodes.getValue(_node.getOrder());
    }
    protected static Argument getPreviousArgument(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendPossibleIntermediateDotted _node) {
        return _nodes.getValue(_node.getOrder()).getPreviousArgument();
    }

    public final RendMethodOperation getParent() {
        return parent;
    }
    public static int getNextIndex(RendDynOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        RendMethodOperation par_ = _operation.getParent();
        if (par_ instanceof RendCompoundAffectationOperation) {
            RendCompoundAffectationOperation p_ = (RendCompoundAffectationOperation)par_;
            if (StringList.quickEq(p_.getOper(),"??=")) {
                if (_value != NullStruct.NULL_VALUE) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof RendCompoundAffectationOperation) {
            RendCompoundAffectationOperation p_ = (RendCompoundAffectationOperation)par_;
            if (StringList.quickEq(p_.getOper(),"&&=")) {
                if (BooleanStruct.isFalse(_value)) {
                    return par_.getOrder();
                }
            }
            if (StringList.quickEq(p_.getOper(),"||=")) {
                if (BooleanStruct.isTrue(_value)) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof RendSafeDotOperation) {
            if (_value == NullStruct.NULL_VALUE) {
                RendDynOperationNode last_ = par_.getChildrenNodes().last();
                boolean skip_ = true;
                if (last_ instanceof RendLambdaOperation) {
                    skip_ = false;
                }
                if (skip_) {
                    RendMethodOperation p_ = par_;
                    while (p_ != null) {
                        RendSettableElResult set_ = null;
                        if (p_ instanceof RendCompoundAffectationOperation) {
                            set_ = ((RendCompoundAffectationOperation)p_).getSettable();
                        }
                        if (p_ instanceof RendAffectationOperation) {
                            set_ = ((RendAffectationOperation)p_).getSettable();
                        }
                        if (set_ == last_) {
                            return p_.getOrder();
                        }
                        p_ = p_.getParent();
                    }
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof RendNullSafeOperation) {
            if (_value != NullStruct.NULL_VALUE) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof RendQuickOperation) {
            RendQuickOperation q_ = (RendQuickOperation) par_;
            if (q_.match(_value)) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof RendTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0) {
                if (BooleanStruct.isFalse(_value)) {
                    return _operation.getNextSibling().getOrder() + 1;
                }
            }
        }
        return _operation.getOrder() + 1;
    }

    public final int getOrder() {
        return order;
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


    public final void setSimpleArgument(Argument _argument, Configuration _conf, IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        setQuickConvertSimpleArgument(_argument, _conf, _nodes);
        setNextSiblingsArg(_argument, _conf);
    }

    protected final void setQuickNoConvertSimpleArgument(Argument _argument, Configuration _conf, IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(_argument,_conf,_nodes);
    }
    protected final void setQuickConvertSimpleArgument(Argument _argument, Configuration _conf, IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(_argument,_conf,_nodes);
    }
    private void setQuickSimpleArgument(Argument _argument, Configuration _conf, IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        if (_conf.getContext().hasException()) {
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = pair_.getImplicits();
        Argument out_ = _argument;
        ImplicitMethods implicitsTest_ = pair_.getImplicitsTest();
        if (!implicitsTest_.isEmpty()) {
            Argument res_ = tryConvert(implicitsTest_.get(0),implicitsTest_.getOwnerClass(), out_, _conf);
            if (res_ == null) {
                return;
            }
            Struct nRes_ = Argument.getNull(res_.getStruct());
            pair_.setArgumentTest(BooleanStruct.isTrue(nRes_));
            RendMethodOperation parent_ = getParent();
            if (parent_ == null || parent_ instanceof RendTernaryOperation) {
                calcArg(_nodes,new Argument(nRes_));
                return;
            }
        } else {
            if (getNextSibling() != null) {
                RendMethodOperation parent_ = getParent();
                if (parent_ instanceof RendCompoundAffectationOperation) {
                    RendCompoundAffectationOperation par_ = (RendCompoundAffectationOperation) parent_;
                    if (StringList.quickEq(par_.getOper(), "&&=")){
                        pair_.setArgumentTest(BooleanStruct.isFalse(Argument.getNull(_argument.getStruct())));
                    }
                    if (StringList.quickEq(par_.getOper(), "||=")){
                        pair_.setArgumentTest(BooleanStruct.isTrue(Argument.getNull(_argument.getStruct())));
                    }
                } else if (parent_ instanceof RendAndOperation) {
                    pair_.setArgumentTest(BooleanStruct.isFalse(Argument.getNull(_argument.getStruct())));
                } else if (parent_ instanceof RendOrOperation) {
                    pair_.setArgumentTest(BooleanStruct.isTrue(Argument.getNull(_argument.getStruct())));
                }
            }
        }
        if (pair_.isArgumentTest()) {
            calcArg(_nodes,out_);
            return;
        }
        int s_ = implicits_.size();
        for (int i = 0; i < s_; i++) {
            ExecNamedFunctionBlock c = implicits_.get(i);
            Argument res_ = tryConvert(c, implicits_.getOwnerClass(), out_, _conf);
            if (res_ == null) {
                return;
            }
            out_ = res_;
        }
        if (resultClass.isConvertToString()){
            out_ = processString(_argument,_conf);
            ContextEl ctx_ = _conf.getContext();
            if (ctx_.hasException()) {
                return;
            }
        }
        calcArg(_nodes, out_);
    }

    private void calcArg(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument out_) {
        RendPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getValue(n_.getOrder()).setPreviousArgument(out_);
        }
        _nodes.getValue(getOrder()).setArgument(out_);
    }

    static Argument tryConvert(ExecNamedFunctionBlock c, String _owner, Argument _argument, Configuration _conf) {
        CustList<Argument> args_ = new CustList<Argument>(_argument);
        PageEl last_ = _conf.getPageEl();
        String cl_ = _owner;
        String paramNameOwner_ = last_.formatVarType(cl_, _conf.getContext());
        if (_conf.hasToExit(paramNameOwner_)) {
            CallingState state_ = _conf.getContext().getCallingState();
            if (state_ instanceof NotInitializedClass) {
                NotInitializedClass statusInit_ = (NotInitializedClass) state_;
                ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
            }
        }
        if (!_conf.getContext().hasException()) {
            ExecTemplates.okArgsSet(c,true, paramNameOwner_,args_, _conf.getContext(), null);
        }
        if (_conf.getContext().hasException()) {
            return null;
        }
        CustomFoundCast c_ = new CustomFoundCast(paramNameOwner_, c, args_);
        _conf.getContext().setCallingState(c_);
        Argument out_ = ProcessMethod.castArgument(c_.getClassName(),c_.getId(), c_.getArguments(), _conf.getContext());
        if (_conf.getContext().hasException()) {
            return null;
        }
        return out_;
    }
    public static Argument processString(Argument _argument, Configuration _conf) {
        Argument out_ = new Argument(_argument.getStruct());
        ContextEl ctx_ = _conf.getContext();
        out_ = ExecOperationNode.processString(out_, ctx_);
        CallingState state_ = ctx_.getCallingState();
        boolean convert_ = false;
        if (state_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) state_;
            out_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), ctx_,method_.getRight());
            convert_ = true;
        }
        if (ctx_.hasException()) {
            return Argument.createVoid();
        }
        if (convert_) {
            Argument outConv_ = new Argument();
            outConv_.setStruct(ExecCatOperation.getDisplayable(out_,_conf.getContext()).getDisplayedString(ctx_));
            out_ = outConv_;
        }
        return out_;
    }
    public final ClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public final RendPossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(RendPossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    public abstract RendDynOperationNode getFirstChild();

    public void setOrder(int _order) {
        order = _order;
    }

    public ImplicitMethods getImplicits() {
        return implicits;
    }

    public ImplicitMethods getImplicitsTest() {
        return implicitsTest;
    }

}
