package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.TwoStepsArgumentsPair;
import code.expressionlanguage.analyze.opers.SemiAffectationOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecSemiAffectationOperation extends ExecAbstractUnaryOperation implements CallExecSimpleOperation {
    private ExecSettableElResult settable;
    private boolean post;
    private String oper;
    private MethodAccessKind kind;
    private String className;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converterFrom;
    private ImplicitMethods converterTo;

    private int opOffset;
    public ExecSemiAffectationOperation(SemiAffectationOperation _s, ContextEl _context) {
        super(_s);
        post = _s.isPost();
        oper = _s.getOper();
        kind = getKind(_s.getClassMethodId());
        className = getType(_s.getClassMethodId());
        named = fetchFunctionOp(_s.getRootNumber(),_s.getMemberNumber(), _context.getAnalyzing());
        rootBlock = fetchType(_s.getRootNumber(), _context.getAnalyzing());
        converterFrom = fetchImplicits(_context,_s.getConverterFrom(),_s.getRootNumberFrom(),_s.getMemberNumberFrom());
        converterTo = fetchImplicits(_context,_s.getConverterTo(),_s.getRootNumberTo(),_s.getMemberNumberTo());
        opOffset = _s.getOpOffset();
    }

    public void setup() {
        settable = ExecAffectationOperation.tryGetSettable(this);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        if (((ExecOperationNode) settable).getParent() instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = ((ExecOperationNode) settable).getParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                ArgumentsPair pairBefore_ = getArgumentPair(_nodes,this);
                pairBefore_.setEndCalculate(true);
                pairBefore_.setIndexImplicitSemiFrom(-1);
                pairBefore_.setIndexImplicitSemiTo(-1);
                if (pairBefore_ instanceof TwoStepsArgumentsPair) {
                    ((TwoStepsArgumentsPair)pairBefore_).setCalledIndexer(true);
                }
                leftArg_ = new Argument(ClassArgumentMatching.convert(_conf.getLastPage(),getResultClass(),NullStruct.NULL_VALUE,_conf));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        if (named != null) {
            CustList<ExecOperationNode> list_ = getChildrenNodes();
            ExecOperationNode left_ = list_.first();
            CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
            chidren_.add(left_);
            CustList<Argument> arguments_ = new CustList<Argument>();
            Argument leftArg_ = getArgument(_nodes,left_);
            arguments_.add(leftArg_);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            ExecInvokingOperation.checkParametersOperators(new DefaultExiting(_conf),_conf, rootBlock,named, firstArgs_, className, kind);
            return;
        }
        ArgumentsPair pairBefore_ = getArgumentPair(_nodes,this);
        ImplicitMethods implicits_ = pairBefore_.getImplicitsSemiFrom();
        int indexImplicit_ = pairBefore_.getIndexImplicitSemiFrom();
        if (implicits_.isValidIndex(indexImplicit_)) {
            CustList<ExecOperationNode> list_ = getChildrenNodes();
            ExecOperationNode left_ = list_.first();
            Argument leftArg_ = getArgument(_nodes,left_);
            Struct store_;
            store_ = leftArg_.getStruct();
            Argument l_ = new Argument(store_);
            pairBefore_.setIndexImplicitSemiFrom(ExecOperationNode.processConverter(_conf,l_, implicits_,indexImplicit_));
            return;
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _conf);
        Argument arg_ = settable.calculateSemiSetting(_nodes, _conf, oper, post);
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        pair_.setEndCalculate(true);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    @Override
    public void endCalculate(ContextEl _conf,
                             IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _conf);
        ImplicitMethods implicits_ = pair_.getImplicitsSemiFrom();
        int indexImplicit_ = pair_.getIndexImplicitSemiFrom();
        if (implicits_.isValidIndex(indexImplicit_)) {
            CustList<ExecOperationNode> list_ = getChildrenNodes();
            ExecOperationNode left_ = list_.first();
            Argument leftArg_ = getArgument(_nodes,left_);
            Struct store_;
            store_ = leftArg_.getStruct();
            Argument l_ = new Argument(store_);
            pair_.setIndexImplicitSemiFrom(ExecOperationNode.processConverter(_conf,l_, implicits_,indexImplicit_));
            return;
        }
        implicits_ = pair_.getImplicitsSemiTo();
        indexImplicit_ = pair_.getIndexImplicitSemiTo();
        if (implicits_.isValidIndex(indexImplicit_)) {
            String tres_ = implicits_.get(indexImplicit_).getImportedParametersTypes().first();
            ClassArgumentMatching cl_ = new ClassArgumentMatching(tres_);
            Argument res_;
            res_ = ExecNumericOperation.calculateIncrDecr(_right, _conf, oper, cl_);
            pair_.setIndexImplicitSemiTo(ExecOperationNode.processConverter(_conf,res_, implicits_,indexImplicit_));
            return;
        }
        Argument stored_ = getArgument(_nodes,(ExecOperationNode) settable);
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = settable.endCalculate(_conf, _nodes, post, stored_, _right);
            setSimpleArgument(arg_, _conf, _nodes);
            return;
        }
        if (pair_ instanceof TwoStepsArgumentsPair) {
            TwoStepsArgumentsPair s_ = (TwoStepsArgumentsPair) pair_;
            Argument out_;
            if (!s_.isCalledIndexer()) {
                s_.setCalledIndexer(true);
                out_ = ExecSemiAffectationOperation.getPrePost(post, stored_, _right);
            } else {
                out_ = _right;
            }
            setSimpleArgument(out_, _conf, _nodes);
            return;
        }
        setSimpleArgument(_right, _conf, _nodes);
    }

    static Argument getPrePost(boolean _post, Argument _stored,Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

    public ExecSettableElResult getSettable() {
        return settable;
    }

    public ImplicitMethods getConverterFrom() {
        return converterFrom;
    }

    public ImplicitMethods getConverterTo() {
        return converterTo;
    }
}
