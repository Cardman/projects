package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class EqOperation extends PrimitiveBoolOperation {

    public EqOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static Argument calculateEq(Argument _a, Argument _b, ContextEl _context) {
        Argument a_ = new Argument();
        a_.setObject(_a.getStruct().sameReference(_b.getStruct()));
        return a_;
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf);
    }

    void analyzeCommon(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        if (chidren_.size() != 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {

        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        Argument first_ = _nodes.getVal(opOne_).getArgument();
        Argument second_ = _nodes.getVal(opTwo_).getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_, _conf);
        if (complement_) {
            Boolean b_ = (Boolean) arg_.getObject();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_, _conf);
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
