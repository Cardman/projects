package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.exceptions.NullObjectException;

public final class DotOperation extends MethodOperation {

    public DotOperation(String _el, int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

//    @Override
//    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
//        if (chidren_.size() < 2) {
//            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
//            throw new BadNumberValuesException(_conf.joinPages());
//        }
//        setResultClass(chidren_.last().getResultClass());
//    }

    @Override
    public void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    @Override
    public void analyzeRight(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    @Override
    public void analyzeSetting(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() < 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        setResultClass(chidren_.last().getResultClass());
    }

    /**@throws NullObjectException*/
//    @Override
//    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
//            ContextEl _conf, Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong();
//        OperationNode o_ = chidren_.last();
//        Argument a_ = _nodes.getVal(o_).getArgument();
//        setSimpleArgument(a_, _conf, _nodes);
//        return a_;
//    }

    @Override
    public Argument calculateLeft(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }
    
    @Override
    public Argument calculateRight(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    @Override
    public Argument calculateSetting(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode o_ = chidren_.last();
        Argument a_ = _nodes.getVal(o_).getArgument();
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NullObjectException*/
//    @Override
//    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf, Calculation _setting) {
//        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
//        setSimpleArgument(chidren_.last().getArgument(), _conf);
//    }

    @Override
    public void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    @Override
    public void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    @Override
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setSimpleArgument(chidren_.last().getArgument(), _conf);
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
//        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }
}
