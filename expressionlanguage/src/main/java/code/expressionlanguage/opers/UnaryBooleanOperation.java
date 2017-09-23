package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.NotBooleanException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.exceptions.NullObjectException;

public final class UnaryBooleanOperation extends PrimitiveBoolOperation {

    public UnaryBooleanOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 1) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        ClassArgumentMatching clMatch_;
        clMatch_ = chidren_.first().getResultClass();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        if (!clMatch_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
            if (!clMatch_.matchClass(Boolean.class)) {
                ClassArgumentMatching cl_ = chidren_.first().getResultClass();
                throw new NotBooleanException(String.valueOf(cl_)+RETURN_LINE+_conf.joinPages());
            }
        }
        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.PRIM_BOOLEAN));
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = _nodes.getVal(chidren_.first()).getArgument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        if (o_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NullObjectException*/

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        if (o_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
