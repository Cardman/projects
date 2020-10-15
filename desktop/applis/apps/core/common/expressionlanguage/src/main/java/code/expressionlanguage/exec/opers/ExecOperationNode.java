package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.util.PolymorphMethod;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
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

    private ExecOperationNode nextSibling;

    private ExecOperationContent content;

    private ExecPossibleIntermediateDotted siblingSet;

    private ImplicitMethods implicits = new ImplicitMethods();
    private ImplicitMethods implicitsTest = new ImplicitMethods();

    ExecOperationNode(ExecOperationContent _content) {
        content = _content;
    }

    ExecOperationNode(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        this(new ExecOperationContent(_indexChild,_res,_order));
    }

    static int processConverter(ContextEl _conf, Argument _right, ImplicitMethods _implicits, int _indexImplicit) {
        ExecNamedFunctionBlock c = _implicits.get(_indexImplicit);
        AbstractExiting ex_ = _conf.getExiting();
        CustList<Argument> args_ = new CustList<Argument>(_right);
        if (ExecExplicitOperation.checkFormattedCustomOper(ex_,_implicits.getRootBlock(), c, args_, _implicits.getOwnerClass(), _conf,_right)) {
            return _indexImplicit;
        }
        return _indexImplicit +1;
    }

    public void setParent(ExecMethodOperation _parent) {
        parent = _parent;
    }

    public final void setRelativeOffsetPossibleLastPage(int _offset, ContextEl _cont) {
        _cont.setOffset(getIndexBegin()+_offset);
    }

    public final int getIndexBegin() {
        return content.getIndexBegin();
    }

    public abstract ExecOperationNode getFirstChild();

    public final ExecOperationNode getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(ExecOperationNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    protected Argument getPreviousArg(ExecPossibleIntermediateDotted _possible,IdMap<ExecOperationNode,ArgumentsPair> _nodes,  ContextEl _conf) {
        Argument previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument(_nodes, this);
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
        return Argument.getNullableValue(getArgumentPair(_nodes,_node).getArgument());
    }
    protected static ArgumentsPair getArgumentPair(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecOperationNode _node) {
        return _nodes.getValue(_node.getOrder());
    }
    protected static Argument getPreviousArgument(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecOperationNode _node) {
        return Argument.getNullableValue(_nodes.getValue(_node.getOrder()).getPreviousArgument());
    }

    private void setNextSiblingsArg(ContextEl _cont, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        if (_cont.callsOrException()) {
            return;
        }
        byte unwrapObjectNb_ = content.getResultClass().getUnwrapObjectNb();
        Argument last_ = Argument.getNullableValue(_nodes.getValue(getOrder()).getArgument());
        if (content.getResultClass().isCheckOnlyNullPe() || unwrapObjectNb_ > -1) {
            if (last_.isNull()) {
                LgNames stds_ = _cont.getStandards();
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                setRelativeOffsetPossibleLastPage(getIndexInEl(), _cont);
                _cont.setCallingState(new ErrorStruct(_cont,null_));
                return;
            }
        }
        if (unwrapObjectNb_ > -1) {
            Argument arg_ = new Argument(NumParsers.unwrapObject(unwrapObjectNb_, last_.getStruct()));
            _nodes.getValue(getOrder()).setArgument(arg_);
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
            if (StringUtil.quickEq(p_.getOper(),"??=")) {
                if (_value != NullStruct.NULL_VALUE) {
                    return par_.getOrder();
                }
            }
        }
        if (par_ instanceof ExecCompoundAffectationOperation) {
            ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation)par_;
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
        if (par_ instanceof ExecSafeDotOperation) {
            if (_value == NullStruct.NULL_VALUE) {
                ExecOperationNode last_ = par_.getChildrenNodes().last();
                boolean skip_ = true;
                if (last_ instanceof ExecAbstractLambdaOperation) {
                    skip_ = false;
                }
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
        if (par_ instanceof ExecTernaryOperation) {
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

    public final void setSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        setQuickConvertSimpleArgument(_argument, _conf, _nodes);
        setNextSiblingsArg(_conf, _nodes);
    }

    public final void setConstantSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(false,_argument, _conf, _nodes);
        setNextSiblingsArg(_conf, _nodes);
    }

    protected final void setQuickNoConvertSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(false,_argument, _conf, _nodes);
    }
    protected final void setQuickConvertSimpleArgument(Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        setQuickSimpleArgument(true,_argument, _conf, _nodes);
    }
    protected final void setQuickSimpleArgument(boolean _possiblePartial,Argument _argument, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        if (_conf.callsOrException()) {
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        ImplicitMethods implicitsTest_ = pair_.getImplicitsTest();
        int indexImplicitTest_ = pair_.getIndexImplicitTest();
        Argument before_ = _argument;
        if (!implicitsTest_.isEmpty()) {
            if (implicitsTest_.isValidIndex(indexImplicitTest_)) {
                pair_.setArgumentBeforeTest(_argument);
                pair_.setIndexImplicitTest(processConverter(_conf,_argument,implicitsTest_,indexImplicitTest_));
                return;
            }
            if (!pair_.isCalcArgumentTest()) {
                pair_.setArgumentTest(BooleanStruct.isTrue(Argument.getNull(_argument.getStruct())));
                pair_.setCalcArgumentTest(true);
                before_ = Argument.getNullableValue(pair_.getArgumentBeforeTest());
            }
            ExecMethodOperation parent_ = getParent();
            if (parent_ == null||parent_ instanceof ExecTernaryOperation) {
                calcArg(_possiblePartial, _conf, _nodes, _argument);
                return;
            }
        } else {
            if (getNextSibling() != null) {
                ExecMethodOperation parent_ = getParent();
                if (parent_ instanceof ExecCompoundAffectationOperation) {
                    ExecCompoundAffectationOperation par_ = (ExecCompoundAffectationOperation) parent_;
                    if (StringUtil.quickEq(par_.getOper(), "&&=")){
                        if (!pair_.isCalcArgumentTest()) {
                            pair_.setArgumentTest(BooleanStruct.isFalse(Argument.getNull(_argument.getStruct())));
                            pair_.setCalcArgumentTest(true);
                        }
                    }
                    if (StringUtil.quickEq(par_.getOper(), "||=")){
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
            calcArg(_possiblePartial, _conf, _nodes, before_);
            return;
        }
        ImplicitMethods implicits_ = pair_.getImplicits();
        int indexImplicit_ = pair_.getIndexImplicit();
        if (implicits_.isValidIndex(indexImplicit_)) {
            pair_.setIndexImplicit(processConverter(_conf,before_,implicits_,indexImplicit_));
            return;
        }
        Argument arg_ = before_;
        if (content.getResultClass().isConvertToString()){
            arg_ = processString(before_,_conf);
            if (_conf.getCallingState() != null) {
                return;
            }
        }
        calcArg(_possiblePartial, _conf, _nodes, arg_);
    }

    private void calcArg(boolean _possiblePartial, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _arg) {
        ExecPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ instanceof ExecOperationNode) {
            _nodes.getValue(((ExecOperationNode)n_).getOrder()).setPreviousArgument(_arg);
        }
        ArgumentsPair pair_ = _nodes.getValue(getOrder());
        pair_.setArgument(_arg);
        _conf.getCoverage().passBlockOperation(_conf, this, !_possiblePartial, pair_);
    }
    public static Argument processString(Argument _argument, ContextEl _conf) {
        Struct struct_ = Argument.getNullableValue(_argument).getStruct();
        String argClassName_ = struct_.getClassName(_conf);
        String idCl_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        PolymorphMethod valBody_ = _conf.getClasses().getToStringMethodsToCallBodies().getVal(idCl_);
        String clCall_ = "";
        ExecNamedFunctionBlock methodCallBody_ = null;
        ExecRootBlock type_ = null;
        if (valBody_ != null) {
            ExecOverrideInfo polymorphMethod_ = ExecInvokingOperation.polymorph(_conf, struct_, valBody_.getRootBlock(), valBody_.getNamed());
            methodCallBody_ = polymorphMethod_.getOverridableBlock();
            clCall_ = polymorphMethod_.getClassName();
            type_ = polymorphMethod_.getRootBlock();
        } else {
            argClassName_ = " ";
        }
        clCall_ = ExecTemplates.getOverridingFullTypeByBases(argClassName_,clCall_,_conf);
        if (methodCallBody_ == null) {
            return new Argument(ExecCatOperation.getDisplayable(_argument,_conf));
        }
        Parameters parameters_ = new Parameters();
        Argument out_ = new Argument(struct_);
        _conf.setCallingState(new CustomFoundMethod(out_,clCall_,type_,methodCallBody_,parameters_));
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
