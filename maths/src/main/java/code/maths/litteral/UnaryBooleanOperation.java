package code.maths.litteral;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class UnaryBooleanOperation extends PrimitiveBoolOperation {

    public UnaryBooleanOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.first().getResultClass()!= MathType.BOOLEAN) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(MathType.BOOLEAN);
    }
    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Object o_ = arg_.getObject();
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(b_);
        setArgument(a_);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
