package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class EqOperation extends PrimitiveBoolOperation {

    private String oper;
    public EqOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = _op.getOperators().values().first();
    }

    static Argument calculateEq(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setObject(_a.getStruct().sameReference(_b.getStruct()));
        return a_;
    }

    @Override
    public void analyze(Analyzable _conf) {
        if (StringList.quickEq(oper.trim(), NEG_BOOL)) {
            UnexpectedOperationAffect badEl_ = new UnexpectedOperationAffect();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
        }
        LgNames stds_ = _conf.getStandards();
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }
    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {

        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        Argument first_ = _nodes.getVal(opOne_).getArgument();
        Argument second_ = _nodes.getVal(opTwo_).getArgument();
        boolean complement_ = false;
        String op_ = oper.trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_);
        if (complement_) {
            Boolean b_ = (Boolean) arg_.getObject();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_);
        if (complement_) {
            Boolean b_ = (Boolean) arg_.getObject();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_);
        if (complement_) {
            Boolean b_ = (Boolean) arg_.getObject();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf);
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
