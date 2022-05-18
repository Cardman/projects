package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public abstract class ExecOperationNode {

    protected static final String ARR = "[";

    protected static final String MULT = "*";

    protected static final String DIV = "/";

    protected static final String PLUS = "+";

    protected static final String DIFF = "!=";

    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";

    private ExecMethodOperation parent;

    private final ExecOperationContent content;

    private ExecPossibleIntermediateDotted siblingSet;

    private final ImplicitMethods implicits = new ImplicitMethods();
    private final ImplicitMethods implicitsTest = new ImplicitMethods();

    ExecOperationNode(ExecOperationContent _content) {
        content = _content;
    }

    ExecOperationNode(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        this(new ExecOperationContent(_indexChild,_res,_order));
    }

    public void setParent(ExecMethodOperation _parent) {
        parent = _parent;
    }

    public final void setRelOffsetPossibleLastPage(int _offset, StackCall _stackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+_offset,_stackCall);
    }

    public final void setRelativeOffsetPossibleLastPage(StackCall _stackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl(),_stackCall);
    }

    public static void setRelativeOffsetPossibleLastPage(int _offset, StackCall _stackCall) {
        _stackCall.setOffset(_offset);
    }

    public abstract ExecOperationNode getFirstChild();

    public final ExecOperationNode getNextSibling() {
        return ExecHelper.getNextNode(this);
    }

    protected Argument getPreviousArg(ExecPossibleIntermediateDotted _possible, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        Argument previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, this);
        } else {
            previous_ = _stackCall.getLastPage().getGlobalArgument();
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
        return Argument.getNullableValue(ExecHelper.getArgumentPair(_nodes,_node).getArgument());
    }
    protected static Argument getFirstArgument(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecMethodOperation _node) {
        return Argument.getNullableValue(ExecHelper.getArgumentPair(_nodes, ExecHelper.getFirstNode(_node)).getArgument());
    }
    protected static Argument getLastArgument(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecMethodOperation _node) {
        CustList<ExecOperationNode> childrenNodes_ = _node.getChildrenNodes();
        return Argument.getNullableValue(ExecHelper.getArgumentPair(_nodes, ExecHelper.getNode(childrenNodes_,childrenNodes_.size()-1)).getArgument());
    }
    protected static Argument getPreviousArgument(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecOperationNode _node) {
        return Argument.getNullableValue(ExecHelper.getArgumentPair(_nodes,_node).getPreviousArgument());
    }

    private void setNextSiblingsArg(ContextEl _cont, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        if (_cont.callsOrException(_stackCall)) {
            return;
        }
        byte unwrapObjectNb_ = content.getResultClass().getUnwrapObjectNb();
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        Argument last_ = Argument.getNullableValue(pair_.getArgument());
        if ((content.getResultClass().isCheckOnlyNullPe() || unwrapObjectNb_ > -1) && last_.isNull()) {
            LgNames stds_ = _cont.getStandards();
            String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
            setRelativeOffsetPossibleLastPage(_stackCall);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, null_, _stackCall)));
            return;
        }
        if (unwrapObjectNb_ > -1) {
            Argument arg_ = new Argument(NumParsers.unwrapObject(unwrapObjectNb_, last_.getStruct()));
            pair_.setArgument(arg_);
        }
    }


    public final ExecMethodOperation getParent() {
        return parent;
    }

    public static boolean orEq(CompoundedOperator _p) {
        return shortEq(_p,"||", AbsBk.OR_LOG_EQ, AbsBk.OR_LOG_EQ_SHORT);
    }

    public static boolean andEq(CompoundedOperator _p) {
        return shortEq(_p, "&&", AbsBk.AND_LOG_EQ, AbsBk.AND_LOG_EQ_SHORT);
    }

    public static boolean nullEq(CompoundedOperator _p) {
        return shortEq(_p,"??", AbsBk.NULL_EQ, AbsBk.NULL_EQ_SHORT);
    }

    private static boolean shortEq(CompoundedOperator _compound, String _simple, String _slow, String _quick) {
        return StringUtil.quickEq(_compound.getOper(), _simple) || StringUtil.quickEq(_compound.getOper(), _slow) || StringUtil.quickEq(_compound.getOper(), _quick);
    }

    public final int getOrder() {
        return content.getOrder();
    }

    public final void setOrder(int _order) {
        content.setOrder(_order);
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

    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        setQuickConvertSimpleArgument(_argument, _conf, _nodes, _stackCall);
        setNextSiblingsArg(_conf, _nodes, _stackCall);
    }

    public final void setConstantSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        setQuickSimpleArgument(false,_argument, _conf, _nodes, _stackCall);
        setNextSiblingsArg(_conf, _nodes, _stackCall);
    }

    protected final void setQuickNoConvertSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        setQuickSimpleArgument(false,_argument, _conf, _nodes, _stackCall);
    }
    protected final void setQuickConvertSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        setQuickSimpleArgument(true,_argument, _conf, _nodes, _stackCall);
    }
    protected final void setQuickSimpleArgument(boolean _possiblePartial, Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        pair_.argumentImpl(_argument);
        int indexImplicitTest_ = pair_.getIndexImplicitTest();
        Argument before_;
        if (!implicitsTest.isEmpty()) {
            if (implicitsTest.isValidIndex(indexImplicitTest_)) {
                pair_.setArgumentBeforeTest(_argument);
                pair_.setIndexImplicitTest(ParamCheckerUtil.processConverter(_conf,_argument,implicitsTest,indexImplicitTest_, _stackCall));
                return;
            }
            before_ = pair_.argument(_argument);
            pair_.argumentTest(BooleanStruct.isTrue(_argument.getStruct()));
            ExecMethodOperation parent_ = getParent();
            if (isTestContext(parent_)) {
                calcArg(_possiblePartial, _conf, _nodes, _argument, _stackCall);
                return;
            }
        } else {
            before_ = _argument;
            if (getNextSibling() != null) {
                ExecMethodOperation parent_ = getParent();
                if (parent_ instanceof CompoundedOperator) {
                    CompoundedOperator par_ = (CompoundedOperator) parent_;
                    testpair(_argument, pair_, par_);
//                } else if (parent_ instanceof ExecQuickOperation) {
//                    pair_.argumentTest(((ExecQuickOperation)parent_).match(Argument.getNull(_argument.getStruct())));
                }
            }
        }
        if (pair_.isArgumentTest()) {
            calcArg(_possiblePartial, _conf, _nodes, before_, _stackCall);
            return;
        }
        defCalcArg(_possiblePartial, _conf, _nodes, _stackCall, pair_, before_);
    }

    private void defCalcArg(boolean _possiblePartial, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall, ArgumentsPair _pair, Argument _before) {
        int indexImplicit_ = _pair.getIndexImplicit();
        if (implicits.isValidIndex(indexImplicit_)) {
            _pair.setIndexImplicit(ParamCheckerUtil.processConverter(_conf, _before,implicits,indexImplicit_, _stackCall));
            return;
        }
        Argument arg_ = _before;
        if (content.getResultClass().isConvertToString()){
            arg_ = IndirectCalledFctUtil.processString(_before, _conf, _stackCall);
            if (_stackCall.getCallingState() != null) {
                return;
            }
        }
        calcArg(_possiblePartial, _conf, _nodes, arg_, _stackCall);
    }

    private static boolean isTestContext(ExecMethodOperation _parent) {
        return _parent == null || _parent instanceof ExecRefTernaryOperation;
    }

    public static void testpair(Argument _argument, ArgumentsPair _pair, CompoundedOperator _par) {
        if (andEq(_par)) {
            _pair.argumentTest(BooleanStruct.isFalse(_argument.getStruct()));
        }
        if (orEq(_par)) {
            _pair.argumentTest(BooleanStruct.isTrue(_argument.getStruct()));
        }
        if (nullEq(_par)) {
            _pair.argumentTest(!_argument.isNull());
        }
    }

    private void calcArg(boolean _possiblePartial, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _arg, StackCall _stackCall) {
        ExecPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ instanceof ExecOperationNode) {
            ExecHelper.getArgumentPair(_nodes,(ExecOperationNode)n_).setPreviousArgument(_arg);
        }
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        pair_.setArgument(_arg);
        _conf.getCoverage().passBlockOperation(this, !_possiblePartial, pair_, _stackCall);
    }

    public final ExecClassArgumentMatching getResultClass() {
        return content.getResultClass();
    }

    public final ExecPossibleIntermediateDotted getSiblingSet() {
        return siblingSet;
    }

    public final void setSiblingSet(ExecPossibleIntermediateDotted _siblingSet) {
        siblingSet = _siblingSet;
    }

    public ImplicitMethods getImplicits() {
        return implicits;
    }

    public ImplicitMethods getImplicitsTest() {
        return implicitsTest;
    }
}
