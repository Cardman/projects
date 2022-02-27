package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.util.NativeFct;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendNativeFct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendDynOperationNode {

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String PLUS = "+";

    protected static final String DIFF = "!=";

    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";

    private RendMethodOperation parent;

    private final ExecOperationContent content;

    private RendPossibleIntermediateDotted siblingSet;

    private final ImplicitMethods implicits = new ImplicitMethods();
    private final ImplicitMethods implicitsTest = new ImplicitMethods();

    RendDynOperationNode(ExecOperationContent _content) {
        content = _content;
    }
    public void setParent(RendMethodOperation _parent) {
        parent = _parent;
    }

    public static ArgumentWrapper processCall(Argument _res, ContextEl _context, RendStackCall _stackCall) {
        StackCall stackCall_ = _stackCall.getStackCall();
        CallingState callingState_ = stackCall_.getCallingState();
        ArgumentWrapper res_;
        if (callingState_ != null) {
            res_ = ProcessMethod.calculate(callingState_, _context, stackCall_);
        } else {
            res_ = new ArgumentWrapper(_res,null);
        }
        return res_;
    }

    public static void setRelativeOffsetPossibleLastPage(int _offset, RendStackCall _rendStackCall) {
        _rendStackCall.setOpOffset(_offset);
    }

    public final void setRelativeOffsetPossibleLastPage(RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl(),_rendStackCall);
    }

    public final void setRelOffsetPossibleLastPage(int _offset,RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+_offset,_rendStackCall);
    }

    public final RendDynOperationNode getNextSibling() {
        return getNextNode(this);
    }

    private void setNextSiblingsArg(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStackCall) {
        if (_context.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Argument last_ = Argument.getNullableValue(pair_.getArgument());
        byte unwrapObjectNb_ = content.getResultClass().getUnwrapObjectNb();
        if ((content.getResultClass().isCheckOnlyNullPe() || unwrapObjectNb_ > -1) && last_.isNull()) {
            LgNames stds_ = _context.getStandards();
            String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
            setRelativeOffsetPossibleLastPage(_rendStackCall);
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _rendStackCall.getStackCall())));
            return;
        }
        if (unwrapObjectNb_ > -1) {
            Argument arg_ = new Argument(NumParsers.unwrapObject(unwrapObjectNb_, last_.getStruct()));
            pair_.setArgument(arg_);
        }
    }

    protected Argument getPreviousArg(RendPossibleIntermediateDotted _possible, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, RendStackCall _rendStackCall) {
        Argument previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, this);
        } else {
            previous_ = _rendStackCall.getLastPage().getGlobalArgument();
        }
        return previous_;
    }
    public static CustList<Argument> getArguments(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendMethodOperation _method) {
        CustList<Argument> a_ = new CustList<Argument>();
        for (RendDynOperationNode o: _method.getChildrenNodes()) {
            a_.add(getArgument(_nodes, o));
        }
        return a_;
    }
    protected static Argument getArgument(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return Argument.getNullableValue(getArgumentPair(_nodes,_node).getArgument());
    }
    protected static Argument getArgumentBeforeImpl(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return Argument.getNullableValue(getArgumentPair(_nodes,_node).getArgumentBeforeImpl());
    }
    protected static Argument getPreviousArgument(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return Argument.getNullableValue(_nodes.getValue(_node.getOrder()).getPreviousArgument());
    }

    public final RendMethodOperation getParent() {
        return parent;
    }
    public static int getNextIndex(RendDynOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        RendMethodOperation par_ = _operation.getParent();
        if (par_ instanceof RendCompoundAffectationOperation) {
            RendCompoundAffectationOperation p_ = (RendCompoundAffectationOperation)par_;
            if (ancSettable(p_,_operation) != null && ExecOperationNode.shEq(_value, p_)) {
                return par_.getOrder();
            }
        }
        if (ExecOperationNode.safeDotShort(_value,index_,par_ instanceof RendSafeDotOperation)) {
            RendDynOperationNode last_ = par_.getChildrenNodes().last();
            if (!(last_ instanceof RendAbstractLambdaOperation)) {
                return shortCutNul(par_, last_, par_.getOrder());
            }
        }
        if (nulSafeShort(_value, par_)) {
            return par_.getOrder();
        }
        if (valueShort(_value, par_)) {
            return par_.getOrder();
        }
        if (par_ instanceof RendRefTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0 && BooleanStruct.isFalse(_value)) {
                return getOrder(_operation.getNextSibling()) + 1;
            }
        }
        return _operation.getOrder() + 1;
    }

    public static RendDynOperationNode ancSettable(RendAbstractAffectOperation _compo, RendDynOperationNode _operation) {
        RendDynOperationNode cur_ = _compo.getSettable();
        while (cur_ != null) {
            if (cur_ == _operation) {
                return cur_;
            }
            cur_ = cur_.getParent();
        }
        return null;
    }
    private static boolean valueShort(Struct _value, RendMethodOperation _par) {
        return _par instanceof RendQuickOperation && ((RendQuickOperation) _par).match(_value);
    }

    private static boolean nulSafeShort(Struct _value, RendMethodOperation _par) {
        return _par instanceof RendNullSafeOperation && _value != NullStruct.NULL_VALUE;
    }

    private static int shortCutNul(RendMethodOperation _par, RendDynOperationNode _last, int _order) {
        RendMethodOperation p_ = _par;
        while (p_ != null) {
            RendDynOperationNode set_ = null;
            if (p_ instanceof RendAbstractAffectOperation) {
                set_ = ((RendAbstractAffectOperation) p_).getSettable();
            }
            if (set_ == _last) {
                return p_.getOrder();
            }
            p_ = p_.getParent();
        }
        return _order;
    }

    public final int getOrder() {
        return content.getOrder();
    }

    public final int getIndexInEl() {
        return content.getIndexInEl();
    }

    public final int getIndexChild() {
        return content.getIndexChild();
    }

    public final Argument getArgument() {
        return content.getArgument();
    }


    public final void setSimpleArgument(ArgumentWrapper _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStackCall) {
        AbstractWrapper wrapper_ = _argument.getWrapper();
        if (wrapper_ != null) {
            getArgumentPair(_nodes,this).setWrapper(wrapper_);
        }
        setQuickConvertSimpleArgument(_argument.getValue(), _nodes, _context, _rendStackCall);
        setNextSiblingsArg(_nodes, _context, _rendStackCall);
    }

    public final void setSimpleArgument(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStackCall) {
        setQuickConvertSimpleArgument(_argument, _nodes, _context, _rendStackCall);
        setNextSiblingsArg(_nodes, _context, _rendStackCall);
    }

    protected final void setQuickNoConvertSimpleArgument(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setQuickSimpleArgument(_argument, _nodes, _context, _rendStack);
    }
    protected final void setQuickConvertSimpleArgument(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setQuickSimpleArgument(_argument, _nodes, _context, _rendStack);
    }
    private void setQuickSimpleArgument(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        if (_context.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        if (!implicitsTest.isEmpty()) {
            Argument res_ = tryConvert(implicitsTest.get(0),implicitsTest.getOwnerClass(), _argument, _context, _rendStack);
            if (res_ == null) {
                return;
            }
            Struct nRes_ = Argument.getNull(res_.getStruct());
            pair_.argumentTest(BooleanStruct.isTrue(nRes_));
            RendMethodOperation parent_ = getParent();
            if (isTestContext(parent_)) {
                calcArg(_nodes,new Argument(nRes_));
                return;
            }
        } else {
            if (getNextSibling() != null) {
                RendMethodOperation parent_ = getParent();
                if (parent_ instanceof CompoundedOperator) {
                    CompoundedOperator par_ = (CompoundedOperator) parent_;
                    ExecOperationNode.testpair(_argument,pair_,par_);
                } else if (parent_ instanceof RendQuickOperation) {
                    pair_.argumentTest(((RendQuickOperation)parent_).match(Argument.getNull(_argument.getStruct())));
                }
            }
        }
        if (pair_.isArgumentTest()) {
            calcArg(_nodes, _argument);
            return;
        }
        defCalcArg(_argument, _nodes, _context, _rendStack, _argument);
    }

    private void defCalcArg(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack, Argument _out) {
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        pair_.argumentImpl(_out);
        Argument out_ = _out;
        int s_ = implicits.size();
        for (int i = 0; i < s_; i++) {
            ExecTypeFunction c = implicits.get(i);
            Argument res_ = tryConvert(c, implicits.getOwnerClass(), out_, _context, _rendStack);
            if (res_ == null) {
                return;
            }
            out_ = res_;
        }
        if (content.getResultClass().isConvertToString()){
            out_ = processString(_argument, _context, _rendStack);
            if (_context.callsOrException(_rendStack.getStackCall())) {
                return;
            }
        }
        calcArg(_nodes, out_);
    }

    private static boolean isTestContext(RendMethodOperation _parent) {
        return _parent == null || _parent instanceof RendRefTernaryOperation;
    }

    private void calcArg(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _out) {
        RendPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getValue(n_.getOrder()).setPreviousArgument(_out);
        }
        _nodes.getValue(getOrder()).setArgument(_out);
    }

    static Argument tryConvert(ExecTypeFunction _c, ExecFormattedRootBlock _owner, Argument _argument, ContextEl _context, RendStackCall _rend) {
        ExecFormattedRootBlock format_ = StackCall.formatVarType(_rend,_owner);
        CustList<Argument> args_ = new CustList<Argument>(Argument.getNullableValue(_argument));
        Parameters parameters_ = new Parameters();
        if (!_context.callsOrException(_rend.getStackCall())) {
            ArgumentListCall l_ = new ArgumentListCall(args_);
            parameters_ = ExecTemplates.okArgsSet(_c.getFct(), format_,null, l_, _context, _rend.getStackCall());
        }
        if (_context.callsOrException(_rend.getStackCall())) {
            return null;
        }
        Argument out_ = ProcessMethod.calculate(new CustomFoundMethod(Argument.createVoid(), format_, _c, parameters_), _context, _rend.getStackCall()).getValue();
        if (_context.callsOrException(_rend.getStackCall())) {
            return null;
        }
        return out_;
    }

    public static Argument processString(Argument _argument, ContextEl _context, RendStackCall _stackCall) {
        RendNativeFct nat_ = new RendNativeFct();
        Argument out_ = new Argument(_argument.getStruct());
        out_ = ExecOperationNode.processString(out_, _context, _stackCall.getStackCall());
        return result(nat_,_stackCall, _context, out_);
    }

    public static Argument processRandCode(Argument _argument, ContextEl _context, RendStackCall _stackCall) {
        RendNativeFct nat_ = new RendNativeFct();
        Argument out_ = new Argument(_argument.getStruct());
        out_ = ExecOperationNode.processRandCode(out_, _context, _stackCall.getStackCall());
        return result(nat_,_stackCall,_context, out_);
    }

    private static Argument result(NativeFct _nat,RendStackCall _st, ContextEl _context, Argument _out) {
        boolean convert_ = _st.getStackCall().getCallingState() instanceof CustomFoundMethod;
        Argument out_ = calculateArgument(_st,_out,_context);
        if (_context.callsOrException(_st.getStackCall())) {
            return Argument.createVoid();
        }
        if (convert_) {
            out_ = new Argument(_nat.compute(out_, _context));
        }
        return out_;
    }
    public static Argument calculateArgument(RendStackCall _stackCall,Argument _def, ContextEl _ct) {
        CallingState state_ = _stackCall.getStackCall().getCallingState();
        if (state_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) state_;
            return ProcessMethod.calculate(method_, _ct, _stackCall.getStackCall()).getValue();
        }
        return _def;
    }
    public static RendMethodOperation getParentOrNull(RendDynOperationNode _node) {
        if (_node == null) {
            return null;
        }
        return _node.getParent();
    }
    public static RendDynOperationNode getMainNode(RendDynOperationNode _node) {
        RendMethodOperation parent_ = _node.getParent();
        return getFirstNode(parent_);
    }

    public static RendDynOperationNode getFirstNode(RendMethodOperation _parent) {
        if (_parent == null) {
            return null;
        }
        return getNode(_parent.getChildrenNodes(),0);
    }

    public static RendDynOperationNode getLastNode(RendMethodOperation _parent) {
        CustList<RendDynOperationNode> childrenNodes_ = _parent.getChildrenNodes();
        return getNode(childrenNodes_,childrenNodes_.size()-1);
    }
    public static RendDynOperationNode getNextNode(RendDynOperationNode _node) {
        RendMethodOperation par_ = _node.getParent();
        if (par_ == null) {
            return null;
        }
        return getNode(par_.getChildrenNodes(),_node.getIndexChild()+1);
    }
    public static ArgumentsPair getArgumentPair(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        int order_ = getOrder(_node);
        return getArgumentPair(_nodes, order_);
    }

    public static ArgumentsPair getArgumentPair(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, int _order) {
        if (!_nodes.isValidIndex(_order)) {
            ArgumentsPair pair_ = new ArgumentsPair();
            pair_.setArgument(Argument.createVoid());
            return pair_;
        }
        return _nodes.getValue(_order);
    }

    public static int getOrder(RendDynOperationNode _node) {
        if (_node == null) {
            return 0;
        }
        return _node.getOrder();
    }
    public static RendDynOperationNode getNode(CustList<RendDynOperationNode> _nodes, int _index) {
        if (_nodes.isValidIndex(_index)) {
            return _nodes.get(_index);
        }
        return null;
    }
    public final ExecClassArgumentMatching getResultClass() {
        return content.getResultClass();
    }

    public final RendPossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(RendPossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    public abstract RendDynOperationNode getFirstChild();

    public void setOrder(int _order) {
        content.setOrder(_order);
    }

    public ImplicitMethods getImplicits() {
        return implicits;
    }

    public ImplicitMethods getImplicitsTest() {
        return implicitsTest;
    }

}
