package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.TwoStepsArgumentsPair;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecSemiAffectationOperation extends ExecAbstractUnaryOperation implements CallExecSimpleOperation {
    private ExecSettableElResult settable;
    private boolean post;
    private String oper;
    private ClassMethodId classMethodId;

    private int opOffset;
    public ExecSemiAffectationOperation(SemiAffectationOperation _s) {
        super(_s);
        post = _s.isPost();
        oper = _s.getOper();
        classMethodId = _s.getClassMethodId();
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
                leftArg_ = new Argument(ClassArgumentMatching.convert(_conf.getLastPage(),getResultClass(),NullStruct.NULL_VALUE,_conf));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes);
                return;
            }
        }
        if (classMethodId != null) {
            CustList<ExecOperationNode> chidren_ = new CustList<ExecOperationNode>();
            chidren_.add((ExecOperationNode) settable);
            CustList<Argument> arguments_ = new CustList<Argument>();
            arguments_.add(getArgument(_nodes,(ExecOperationNode) settable));
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            ExecInvokingOperation.checkParametersOperators(new DefaultExiting(_conf),_conf, classMethodId, Argument.createVoid(), firstArgs_);
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
        Argument stored_ = getArgument(_nodes,(ExecOperationNode) settable);
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _conf);
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

    @Override
    public ExecSettableElResult getSettable() {
        return settable;
    }

}
