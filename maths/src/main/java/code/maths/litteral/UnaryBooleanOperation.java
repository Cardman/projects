package code.maths.litteral;
import code.maths.litteral.exceptions.NotBooleanException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class UnaryBooleanOperation extends PrimitiveBoolOperation {

    public UnaryBooleanOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        if (chidren_.first().getResultClass()!= MathType.BOOLEAN) {
            throw new NotBooleanException(String.valueOf(getIndexInEl()));
        }
        setResultClass(MathType.BOOLEAN);
    }
    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        Argument arg_ = chidren_.first().getArgument();
        Object o_ = arg_.getObject();
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(b_);
        setArgument(a_);
        setNextSiblingsArg(a_);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
