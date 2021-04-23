package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
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

    static int processConverter(ContextEl _conf, Argument _right, ImplicitMethods _implicits, int _indexImplicit, StackCall _stackCall) {
        ExecTypeFunction c = _implicits.get(_indexImplicit);
        AbstractExiting ex_ = _conf.getExiting();
        CustList<Argument> args_ = new CustList<Argument>();
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addArg(Argument.getNullableValue(_right));
        if (ExecExplicitOperation.checkFormattedCustomOper(ex_,c, _implicits.getOwnerClass(), _conf,_right, _stackCall, l_)) {
            return _indexImplicit;
        }
        return _indexImplicit +1;
    }

    public void setParent(ExecMethodOperation _parent) {
        parent = _parent;
    }

    public final void setRelOffsetPossibleLastPage(int _offset, StackCall _stackCall) {
        _stackCall.setOffset(getIndexInEl()+_offset);
    }

    public final void setRelativeOffsetPossibleLastPage(StackCall _stackCall) {
        _stackCall.setOffset(getIndexInEl());
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, StackCall _stackCall) {
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
        if (content.getResultClass().isCheckOnlyNullPe() || unwrapObjectNb_ > -1) {
            if (last_.isNull()) {
                LgNames stds_ = _cont.getStandards();
                String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                setRelativeOffsetPossibleLastPage(_stackCall);
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, null_, _stackCall)));
                return;
            }
        }
        if (unwrapObjectNb_ > -1) {
            Argument arg_ = new Argument(NumParsers.unwrapObject(unwrapObjectNb_, last_.getStruct()));
            pair_.setArgument(arg_);
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
            if (StringUtil.quickEq(p_.getOper(),AbsBk.NULL_EQ)) {
                if (_value != NullStruct.NULL_VALUE) {
                    return par_.getOrder();
                }
            }
            if (StringUtil.quickEq(p_.getOper(),AbsBk.NULL_EQ_SHORT)) {
                if (_value != NullStruct.NULL_VALUE) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof ExecCompoundAffectationOperation) {
            ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation)par_;
            if (StringUtil.quickEq(p_.getOper(),AbsBk.AND_LOG_EQ)) {
                if (BooleanStruct.isFalse(_value)) {
                    return par_.getOrder();
                }
            }
            if (StringUtil.quickEq(p_.getOper(),AbsBk.AND_LOG_EQ_SHORT)) {
                if (BooleanStruct.isFalse(_value)) {
                    return par_.getOrder();
                }
            }
            if (StringUtil.quickEq(p_.getOper(),AbsBk.OR_LOG_EQ)) {
                if (BooleanStruct.isTrue(_value)) {
                    return par_.getOrder();
                }
            }
            if (StringUtil.quickEq(p_.getOper(),AbsBk.OR_LOG_EQ_SHORT)) {
                if (BooleanStruct.isTrue(_value)) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof ExecSafeDotOperation) {
            if (_value == NullStruct.NULL_VALUE) {
                ExecOperationNode last_ = ExecHelper.getLastNode(par_);
                boolean skip_ = !(last_ instanceof ExecAbstractLambdaOperation);
                if (skip_) {
                    ExecMethodOperation p_ = par_;
                    while (p_ != null) {
                        ExecOperationNode set_ = null;
                        if (p_ instanceof ExecCompoundAffectationOperation) {
                            set_ = ((ExecCompoundAffectationOperation)p_).getSettable();
                        }
                        if (p_ instanceof ExecAffectationOperation) {
                            set_ = ((ExecAffectationOperation)p_).getSettable();
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
        if (par_ instanceof ExecNullSafeOperation) {
            if (_value != NullStruct.NULL_VALUE) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ExecQuickOperation) {

            ExecQuickOperation q_ = (ExecQuickOperation) par_;
            if (q_.match(_value)) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ExecRefTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0) {
                if (BooleanStruct.isFalse(_value)) {
                    return ExecHelper.getOrder(_operation.getNextSibling()) + 1;
                }
            }
        }
        return _operation.getOrder() + 1;
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
        ImplicitMethods implicitsTest_ = pair_.getImplicitsTest();
        int indexImplicitTest_ = pair_.getIndexImplicitTest();
        Argument before_ = _argument;
        if (!implicitsTest_.isEmpty()) {
            if (implicitsTest_.isValidIndex(indexImplicitTest_)) {
                pair_.setArgumentBeforeTest(_argument);
                pair_.setIndexImplicitTest(processConverter(_conf,_argument,implicitsTest_,indexImplicitTest_, _stackCall));
                return;
            }
            if (!pair_.isCalcArgumentTest()) {
                pair_.setArgumentTest(BooleanStruct.isTrue(Argument.getNull(_argument.getStruct())));
                pair_.setCalcArgumentTest(true);
                before_ = Argument.getNullableValue(pair_.getArgumentBeforeTest());
            }
            ExecMethodOperation parent_ = getParent();
            if (parent_ == null||parent_ instanceof ExecRefTernaryOperation) {
                calcArg(_possiblePartial, _conf, _nodes, _argument, _stackCall);
                return;
            }
        } else {
            if (getNextSibling() != null) {
                ExecMethodOperation parent_ = getParent();
                if (parent_ instanceof ExecCompoundAffectationOperation) {
                    ExecCompoundAffectationOperation par_ = (ExecCompoundAffectationOperation) parent_;
                    if (StringUtil.quickEq(par_.getOper(), AbsBk.AND_LOG_EQ)){
                        if (!pair_.isCalcArgumentTest()) {
                            pair_.setArgumentTest(BooleanStruct.isFalse(Argument.getNull(_argument.getStruct())));
                            pair_.setCalcArgumentTest(true);
                        }
                    }
                    if (StringUtil.quickEq(par_.getOper(), AbsBk.AND_LOG_EQ_SHORT)){
                        if (!pair_.isCalcArgumentTest()) {
                            pair_.setArgumentTest(BooleanStruct.isFalse(Argument.getNull(_argument.getStruct())));
                            pair_.setCalcArgumentTest(true);
                        }
                    }
                    if (StringUtil.quickEq(par_.getOper(), AbsBk.OR_LOG_EQ)){
                        if (!pair_.isCalcArgumentTest()) {
                            pair_.setArgumentTest(BooleanStruct.isTrue(Argument.getNull(_argument.getStruct())));
                            pair_.setCalcArgumentTest(true);
                        }
                    }
                    if (StringUtil.quickEq(par_.getOper(), AbsBk.OR_LOG_EQ_SHORT)){
                        if (!pair_.isCalcArgumentTest()) {
                            pair_.setArgumentTest(BooleanStruct.isTrue(Argument.getNull(_argument.getStruct())));
                            pair_.setCalcArgumentTest(true);
                        }
                    }
                } else if (parent_ instanceof ExecAndOperation) {
                    if (!pair_.isCalcArgumentTest()) {
                        pair_.setArgumentTest(BooleanStruct.isFalse(Argument.getNull(_argument.getStruct())));
                        pair_.setCalcArgumentTest(true);
                    }
                } else if (parent_ instanceof ExecOrOperation) {
                    if (!pair_.isCalcArgumentTest()) {
                        pair_.setArgumentTest(BooleanStruct.isTrue(Argument.getNull(_argument.getStruct())));
                        pair_.setCalcArgumentTest(true);
                    }
                }
            }
        }
        if (pair_.isArgumentTest()) {
            calcArg(_possiblePartial, _conf, _nodes, before_, _stackCall);
            return;
        }
        ImplicitMethods implicits_ = pair_.getImplicits();
        int indexImplicit_ = pair_.getIndexImplicit();
        if (implicits_.isValidIndex(indexImplicit_)) {
            pair_.setIndexImplicit(processConverter(_conf,before_,implicits_,indexImplicit_, _stackCall));
            return;
        }
        Argument arg_ = before_;
        if (content.getResultClass().isConvertToString()){
            arg_ = processString(before_,_conf, _stackCall);
            if (_stackCall.getCallingState() != null) {
                return;
            }
        }
        calcArg(_possiblePartial, _conf, _nodes, arg_, _stackCall);
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
    public static Argument processRandCode(Argument _argument, ContextEl _conf, StackCall _stackCall) {
        StringMap<ExecTypeFunction> redir_ = _conf.getClasses().getRandCodeMethodsToCallBodies();
        return getOrRedirect(new RandCodeNativeFct(),_argument, _conf, _stackCall, redir_);
    }
    public static Argument processString(Argument _argument, ContextEl _conf, StackCall _stackCall) {
        StringMap<ExecTypeFunction> redir_ = _conf.getClasses().getToStringMethodsToCallBodies();
        return getOrRedirect(new StrNativeFct(),_argument, _conf, _stackCall, redir_);
    }

    private static Argument getOrRedirect(NativeFct _nat, Argument _argument, ContextEl _conf, StackCall _stackCall, StringMap<ExecTypeFunction> _redir) {
        Struct struct_ = Argument.getNullableValue(_argument).getStruct();
        String argClassName_ = struct_.getClassName(_conf);
        String idCl_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        ExecTypeFunction valBody_ = _redir.getVal(idCl_);
        String clCall_ = "";
        ExecTypeFunction p_ = new ExecTypeFunction(null,null);
        if (valBody_ != null) {
            ExecOverrideInfo polymorphMethod_ = ExecInvokingOperation.polymorph(_conf, struct_, valBody_);
            p_ = polymorphMethod_.getPair();
            clCall_ = ExecInherits.getOverridingFullTypeByBases(argClassName_,polymorphMethod_.getClassName(), _conf);
        }
        if (p_.getFct() == null) {
            return new Argument(_nat.compute(_argument, _conf));
        }
        Parameters parameters_ = new Parameters();
        Argument out_ = new Argument(struct_);
        _stackCall.setCallingState(new CustomFoundMethod(out_,clCall_, p_, parameters_));
        return out_;
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
