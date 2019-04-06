package code.maths.litteral;
import code.maths.Rate;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class UnaryOperation extends PrimitiveBoolOperation {

    public UnaryOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.first().getResultClass() != MathType.RATE) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(MathType.RATE);
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument a_ = new Argument();
        Object o_ = arg_.getObject();
        a_.setArgClass(MathType.RATE);
        int key_ = getOperations().getOperators().firstKey();
        if (StringList.quickEq(getOperations().getOperators().getVal(key_).trim(), UNARY_MINUS)) {
            a_.setObject(((Rate)o_).opposNb());
        } else {
            a_.setObject(o_);
        }
        setArgument(a_);
        return;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
