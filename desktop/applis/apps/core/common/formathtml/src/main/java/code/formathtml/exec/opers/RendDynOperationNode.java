package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.variables.ArgumentsPair;

import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public abstract class RendDynOperationNode {

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String PLUS = "+";

    protected static final String DIFF = "!=";

    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";

    private RendMethodOperation parent;

    private RendDynOperationNode nextSibling;

    private ExecOperationContent content;

    private RendPossibleIntermediateDotted siblingSet;

    private ImplicitMethods implicits = new ImplicitMethods();
    private ImplicitMethods implicitsTest = new ImplicitMethods();

    RendDynOperationNode(ExecOperationContent _content) {
        content = _content;
    }
    public void setParent(RendMethodOperation _parent) {
        parent = _parent;
    }

    protected static Argument processCall(Argument _res, ContextEl _context) {
        CallingState callingState_ = _context.getCallingState();
        Argument res_;
        if (callingState_ instanceof CustomFoundConstructor) {
            CustomFoundConstructor ctor_ = (CustomFoundConstructor)callingState_;
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(),ctor_.getPair(), ctor_.getCurrentObject(), ctor_.getArguments(), _context);
        } else if (callingState_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) callingState_;
            res_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(),method_.getPair(), method_.getArguments(), _context);
        } else if (callingState_ instanceof AbstractReflectElement) {
            AbstractReflectElement ref_ = (AbstractReflectElement) callingState_;
            res_ = ProcessMethod.reflectArgument(_context,ref_);
        } else if (callingState_ instanceof CustomFoundCast) {
            CustomFoundCast cast_ = (CustomFoundCast) callingState_;
            res_ = ProcessMethod.castArgument(cast_.getClassName(),cast_.getPair(), cast_.getArguments(), _context);
        } else {
            res_ = _res;
        }
        return res_;
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, Configuration _cont) {
        _cont.setOpOffset(content.getIndexBegin() +_offset);
    }

    public int getIndexBegin() {
        return content.getIndexBegin();
    }

    public final RendDynOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(RendDynOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    private void setNextSiblingsArg(Argument _arg, Configuration _cont, ContextEl _context) {
        if (_context.callsOrException()) {
            return;
        }
        byte unwrapObjectNb_ = content.getResultClass().getUnwrapObjectNb();
        if (content.getResultClass().isCheckOnlyNullPe() || unwrapObjectNb_ > -1) {
            if (_arg.isNull()) {
                LgNames stds_ = _context.getStandards();
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_)));
                return;
            }
        }
        if (unwrapObjectNb_ > -1) {
            _arg.setStruct(NumParsers.unwrapObject(unwrapObjectNb_, _arg.getStruct()));
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
        return Argument.getNullableValue(getArgumentPair(_nodes,_node).getArgument());
    }
    protected static ArgumentsPair getArgumentPair(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendDynOperationNode _node) {
        return _nodes.getValue(_node.getOrder());
    }
    protected static Argument getPreviousArgument(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, RendPossibleIntermediateDotted _node) {
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
            if (StringUtil.quickEq(p_.getOper(),"??=")) {
                if (_value != NullStruct.NULL_VALUE) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof RendCompoundAffectationOperation) {
            RendCompoundAffectationOperation p_ = (RendCompoundAffectationOperation)par_;
            if (StringUtil.quickEq(p_.getOper(),"&&=")) {
                if (BooleanStruct.isFalse(_value)) {
                    return par_.getOrder();
                }
            }
            if (StringUtil.quickEq(p_.getOper(),"||=")) {
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
                        RendDynOperationNode set_ = null;
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


    public final void setSimpleArgument(Argument _argument, Configuration _conf, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context) {
        setQuickConvertSimpleArgument(_argument, _nodes, _context);
        setNextSiblingsArg(_argument, _conf, _context);
    }

    protected final void setQuickNoConvertSimpleArgument(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context) {
        setQuickSimpleArgument(_argument, _nodes, _context);
    }
    protected final void setQuickConvertSimpleArgument(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context) {
        setQuickSimpleArgument(_argument, _nodes, _context);
    }
    private void setQuickSimpleArgument(Argument _argument, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context) {
        if (_context.callsOrException()) {
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = pair_.getImplicits();
        Argument out_ = _argument;
        ImplicitMethods implicitsTest_ = pair_.getImplicitsTest();
        if (!implicitsTest_.isEmpty()) {
            Argument res_ = tryConvert(implicitsTest_.get(0),implicitsTest_.getOwnerClass(), out_, _context);
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
                    if (StringUtil.quickEq(par_.getOper(), "&&=")){
                        pair_.setArgumentTest(BooleanStruct.isFalse(Argument.getNull(_argument.getStruct())));
                    }
                    if (StringUtil.quickEq(par_.getOper(), "||=")){
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
            ExecTypeFunction c = implicits_.get(i);
            Argument res_ = tryConvert(c, implicits_.getOwnerClass(), out_, _context);
            if (res_ == null) {
                return;
            }
            out_ = res_;
        }
        if (content.getResultClass().isConvertToString()){
            out_ = processString(_argument, _context);
            if (_context.callsOrException()) {
                return;
            }
        }
        calcArg(_nodes, out_);
    }

    private void calcArg(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _out) {
        RendPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getValue(n_.getOrder()).setPreviousArgument(_out);
        }
        _nodes.getValue(getOrder()).setArgument(_out);
    }

    static Argument tryConvert(ExecTypeFunction _c, String _owner, Argument _argument, ContextEl _context) {
        CustList<Argument> args_ = new CustList<Argument>(Argument.getNullableValue(_argument));
        Parameters parameters_ = new Parameters();
        if (!_context.callsOrException()) {
            parameters_ = ExecTemplates.okArgsSet(_c.getType(), _c.getFct(), _owner,null, args_, _context, null, true);
        }
        if (_context.callsOrException()) {
            return null;
        }
        Argument out_ = ProcessMethod.castArgument(_owner,_c, parameters_, _context);
        if (_context.callsOrException()) {
            return null;
        }
        return out_;
    }
    public static Argument processString(Argument _argument, ContextEl _context) {
        Argument out_ = new Argument(_argument.getStruct());
        out_ = ExecOperationNode.processString(out_, _context);
        CallingState state_ = _context.getCallingState();
        boolean convert_ = false;
        if (state_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) state_;
            out_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(),method_.getPair(), method_.getArguments(), _context);
            convert_ = true;
        }
        if (_context.callsOrException()) {
            return Argument.createVoid();
        }
        if (convert_) {
            out_ = new Argument(ExecCatOperation.getDisplayable(out_, _context));
        }
        return out_;
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
