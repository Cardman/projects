package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.*;
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

    public static ArgumentWrapper processCall(Struct _res, ContextEl _context, RendStackCall _stackCall) {
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
        Struct last_ = ArgumentListCall.getNull(pair_.getArgument());
        byte unwrapObjectNb_ = content.getResultClass().getUnwrapObjectNb();
        if ((content.getResultClass().isCheckOnlyNullPe() || unwrapObjectNb_ > -1) && last_ == NullStruct.NULL_VALUE) {
            LgNames stds_ = _context.getStandards();
            String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
            setRelativeOffsetPossibleLastPage(_rendStackCall);
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _rendStackCall.getStackCall())));
            return;
        }
        if (unwrapObjectNb_ > -1) {
            pair_.setArgument(NumParsers.unwrapObject(unwrapObjectNb_, last_));
        }
    }

    public Struct getPreviousArg(RendPossibleIntermediateDotted _possible, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, RendStackCall _rendStackCall) {
        Struct previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, this);
        } else {
            previous_ = _rendStackCall.getLastPage().getGlobalStruct();
        }
        return previous_;
    }
    public static CustList<Struct> getArguments(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendMethodOperation _method) {
        CustList<Struct> a_ = new CustList<Struct>();
        for (RendDynOperationNode o: _method.getChildrenNodes()) {
            a_.add(getArgument(_nodes, o));
        }
        return a_;
    }
    protected static Struct getArgument(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return ArgumentListCall.getNull(getArgumentPair(_nodes,_node).getArgument());
    }

    protected static Struct getPreviousArgument(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return ArgumentListCall.getNull(_nodes.getValue(_node.getOrder()).getPreviousArgument());
    }

    public final RendMethodOperation getParent() {
        return parent;
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

    public final Struct getArgument() {
        return content.getArgument();
    }


    public final void setSimpleArgument(ArgumentWrapper _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStackCall) {
        setWrapper(_nodes, _argument);
        setQuickConvertSimpleArgument(_argument.getValue(), _nodes, _context, _rendStackCall);
        setNextSiblingsArg(_nodes, _context, _rendStackCall);
    }

    protected void setWrapper(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ArgumentWrapper _wrapper) {
        AbstractWrapper wr_ = _wrapper.getWrapper();
        if (wr_ != null) {
            getArgumentPair(_nodes,this).setWrapper(wr_);
        }
    }

    public final void setSimpleArgument(Struct _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStackCall) {
        setQuickConvertSimpleArgument(_argument, _nodes, _context, _rendStackCall);
        setNextSiblingsArg(_nodes, _context, _rendStackCall);
    }

    protected final void setQuickNoConvertSimpleArgument(Struct _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setQuickSimpleArgument(_argument, _nodes, _context, _rendStack);
    }
    protected final void setQuickConvertSimpleArgument(Struct _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setQuickSimpleArgument(_argument, _nodes, _context, _rendStack);
    }
    private void setQuickSimpleArgument(Struct _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        if (_context.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        pair_.argumentImpl(_argument);
        if (!implicitsTest.isEmpty()) {
            Struct res_ = tryConvert(implicitsTest, _argument, _context, _rendStack);
            if (res_ == null) {
                return;
            }
            Struct nRes_ = ArgumentListCall.getNull(res_);
            pair_.argumentTest(BooleanStruct.isTrue(nRes_));
            RendMethodOperation parent_ = getParent();
            if (isTestContext(parent_)) {
                calcArg(_nodes,nRes_);
                return;
            }
        } else {
            if (getNextSibling() != null) {
                RendMethodOperation parent_ = getParent();
                if (parent_ instanceof CompoundedOperator) {
                    CompoundedOperator par_ = (CompoundedOperator) parent_;
                    ExecOperationNode.testpair(_argument,pair_,par_);
                }
            }
        }
        if (pair_.isArgumentTest()) {
            calcArg(_nodes, _argument);
            return;
        }
        defCalcArg(_argument, _nodes, _context, _rendStack, _argument);
    }

    private void defCalcArg(Struct _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack, Struct _out) {
        Struct out_ = _out;
        if (!implicits.isEmpty()) {
            Struct res_ = tryConvert(implicits, out_, _context, _rendStack);
            if (res_ == null) {
                return;
            }
            out_ = res_;
        }
        if (content.getResultClass().isConvertToString()){
            Struct res_ = processString(_argument, _context, _rendStack);
            if (res_ == null) {
                return;
            }
            out_ = res_;
        }
        calcArg(_nodes, out_);
    }

    private static boolean isTestContext(RendMethodOperation _parent) {
        return _parent == null || _parent instanceof RendRefTernaryOperation;
    }

    protected void calcArg(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Struct _out) {
        RendPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getValue(n_.getOrder()).setPreviousArgument(_out);
        }
        _nodes.getValue(getOrder()).setArgument(_out);
    }

    static Struct tryConvert(ImplicitMethods _i, Struct _argument, ContextEl _context, RendStackCall _rend) {
        ExecTypeFunction c_ = _i.get(0);
        ExecFormattedRootBlock format_ = StackCall.formatVarType(_rend, _i.getOwnerClass());
        if (_context.callsOrException(_rend.getStackCall())) {
            return null;
        }
        ExecTemplates.wrapAndCall(new ExecOverrideInfo(format_,c_), NullStruct.NULL_VALUE,_context,_rend.getStackCall(),new ArgumentListCall(ArgumentListCall.getNull(_argument)));
        ArgumentWrapper res_ = tryGetValue(_context, _rend, null);
        if (res_ == null) {
            return null;
        }
        return res_.getValue();
    }

    public static Struct processString(Struct _argument, ContextEl _context, RendStackCall _stackCall) {
        RendNativeFct nat_ = new RendNativeFct();
        Struct out_ = _argument;
        out_ = IndirectCalledFctUtil.processString(out_, _context, _stackCall.getStackCall());
        return result(nat_,_stackCall, _context, out_);
    }

    public static Struct processRandCode(Struct _argument, ContextEl _context, RendStackCall _stackCall) {
        RendNativeFct nat_ = new RendNativeFct();
        Struct out_ = _argument;
        out_ = IndirectCalledFctUtil.processRandCode(out_, _context, _stackCall.getStackCall());
        return result(nat_,_stackCall,_context, out_);
    }

    private static Struct result(NativeFct _nat,RendStackCall _st, ContextEl _context, Struct _out) {
        boolean convert_ = _st.getStackCall().getCallingState() instanceof CustomFoundMethod;
        ArgumentWrapper aw_ = tryGetValue(_context, _st, new ArgumentWrapper(_out));
        Struct out_ = null;
        if (aw_ != null) {
            if (convert_) {
                out_ = _nat.compute(aw_.getValue(), _context);
            } else {
                out_ = aw_.getValue();
            }
        }
        if (aw_ == null) {
            return null;
        }
        return ArgumentListCall.getNull(out_);
    }
    public static ArgumentWrapper tryGetValue(ContextEl _context, RendStackCall _stackCall, ArgumentWrapper _def) {
        StackCall stackCall_ = _stackCall.getStackCall();
        CallingState callingState_ = stackCall_.getCallingState();
        ArgumentWrapper res_;
        if (callingState_ != null) {
            res_ = ProcessMethod.calculate(callingState_, _context, stackCall_);
        } else {
            return _def;
        }
        if (_context.callsOrException(stackCall_)) {
            return null;
        }
        return res_;
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
            pair_.setArgument(NullStruct.NULL_VALUE);
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

    public final RendPossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(RendPossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    public ImplicitMethods getImplicits() {
        return implicits;
    }

    public ImplicitMethods getImplicitsTest() {
        return implicitsTest;
    }

}
