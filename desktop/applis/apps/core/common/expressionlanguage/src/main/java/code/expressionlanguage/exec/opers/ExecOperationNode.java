package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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
        ExecFormattedRootBlock formatted_ = StackCall.formatVarType(_stackCall, _implicits.getOwnerClass());
        return processConverter(_conf, _right, _implicits, _indexImplicit, _stackCall, formatted_);
    }

    static int processConverter(ContextEl _conf, Argument _right, ImplicitMethods _implicits, int _indexImplicit, StackCall _stackCall, ExecFormattedRootBlock _formatted) {
        ExecTypeFunction c = _implicits.get(_indexImplicit);
        AbstractExiting ex_ = _conf.getExiting();
        ArgumentListCall l_ = new ArgumentListCall(Argument.getNullableValue(_right));
        if (ExecExplicitOperation.checkCustomOper(ex_, c, _formatted, _conf, _right, _stackCall, l_)) {
            return _indexImplicit;
        }
        return _indexImplicit +1;
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
    protected static CustList<Argument> getArguments(CustList<ExecOperationInfo> _nodes) {
        CustList<Argument> a_ = new CustList<Argument>();
        for (ExecOperationInfo o: _nodes) {
            a_.add(o.getPair().getArgument());
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
    public static int getNextIndex(ExecOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        ExecMethodOperation par_ = _operation.getParent();
        if (par_ instanceof ExecCompoundAffectationOperation) {
            ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation)par_;
            if (ancSettable(p_,_operation) != null&&shEq(_value, p_)) {
                return par_.getOrder();
            }
        }
        if (safeDotShort(_value, par_)) {
            ExecOperationNode last_ = ExecHelper.getLastNode(par_);
            if (!(last_ instanceof ExecAbstractLambdaOperation)) {
                return shortCutNul(par_, last_, par_.getOrder());
            }
        }
        if (nulSafeShort(_value, par_)) {
            return par_.getOrder();
        }
        if (valueShort(_value, par_)) {
            return par_.getOrder();
        }
        if (par_ instanceof ExecRefTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0 && BooleanStruct.isFalse(_value)) {
                return ExecHelper.getOrder(_operation.getNextSibling()) + 1;
            }
        }
        return _operation.getOrder() + 1;
    }

    public static ExecOperationNode ancSettable(ExecAbstractAffectOperation _compo, ExecOperationNode _operation) {
        ExecOperationNode cur_ = _compo.getSettable();
        while (cur_ != null) {
            if (cur_ == _operation) {
                return cur_;
            }
            cur_ = cur_.getParent();
        }
        return null;
    }

    private static boolean valueShort(Struct _value, ExecMethodOperation _par) {
        return _par instanceof ExecQuickOperation && ((ExecQuickOperation) _par).match(_value);
    }

    private static boolean nulSafeShort(Struct _value, ExecMethodOperation _par) {
        return _par instanceof ExecNullSafeOperation && _value != NullStruct.NULL_VALUE;
    }

    private static boolean safeDotShort(Struct _value, ExecMethodOperation _par) {
        return _par instanceof ExecSafeDotOperation && _value == NullStruct.NULL_VALUE;
    }

    private static int shortCutNul(ExecMethodOperation _par, ExecOperationNode _last, int _order) {
        ExecMethodOperation p_ = _par;
        while (p_ != null) {
            ExecOperationNode set_ = null;
            if (p_ instanceof ExecAbstractAffectOperation) {
                set_ = ((ExecAbstractAffectOperation) p_).getSettable();
            }
            if (set_ == _last) {
                return p_.getOrder();
            }
            p_ = p_.getParent();
        }
        return _order;
    }

    public static boolean shEq(Struct _value, CompoundedOperator _p) {
        return nullEq(_value, _p) || andEq(_value, _p) || orEq(_value, _p);
    }

    private static boolean orEq(Struct _value, CompoundedOperator _p) {
        return orEq(_p) && BooleanStruct.isTrue(_value);
    }

    public static boolean orEq(CompoundedOperator _p) {
        return shortEq(_p, AbsBk.OR_LOG_EQ, AbsBk.OR_LOG_EQ_SHORT);
    }

    private static boolean andEq(Struct _value, CompoundedOperator _p) {
        return andEq(_p) && BooleanStruct.isFalse(_value);
    }

    public static boolean andEq(CompoundedOperator _p) {
        return shortEq(_p, AbsBk.AND_LOG_EQ, AbsBk.AND_LOG_EQ_SHORT);
    }

    private static boolean nullEq(Struct _value, CompoundedOperator _par) {
        return nullEq(_par) && _value != NullStruct.NULL_VALUE;
    }

    private static boolean nullEq(CompoundedOperator _par) {
        return shortEq(_par, AbsBk.NULL_EQ, AbsBk.NULL_EQ_SHORT);
    }

    private static boolean shortEq(CompoundedOperator _compound, String _slow, String _quick) {
        return StringUtil.quickEq(_compound.getOper(), _slow) || StringUtil.quickEq(_compound.getOper(), _quick);
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
        int indexImplicitTest_ = pair_.getIndexImplicitTest();
        Argument before_;
        if (!implicitsTest.isEmpty()) {
            if (implicitsTest.isValidIndex(indexImplicitTest_)) {
                pair_.setArgumentBeforeTest(_argument);
                pair_.setIndexImplicitTest(processConverter(_conf,_argument,implicitsTest,indexImplicitTest_, _stackCall));
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
                } else if (parent_ instanceof ExecQuickOperation) {
                    pair_.argumentTest(((ExecQuickOperation)parent_).match(Argument.getNull(_argument.getStruct())));
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
            _pair.setIndexImplicit(processConverter(_conf, _before,implicits,indexImplicit_, _stackCall));
            return;
        }
        Argument arg_ = _before;
        if (content.getResultClass().isConvertToString()){
            arg_ = processString(_before, _conf, _stackCall);
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
        ExecFormattedRootBlock clCall_ = ExecFormattedRootBlock.defValue();
        ExecTypeFunction p_ = new ExecTypeFunction((ExecRootBlock) null,null);
        if (valBody_ != null) {
            ExecOverrideInfo polymorphMethod_ = ExecInvokingOperation.polymorph(_conf, struct_, valBody_);
            p_ = polymorphMethod_.getPair();
            clCall_ = ExecFormattedRootBlock.getFullObject(argClassName_, polymorphMethod_.getClassName(), _conf);
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
