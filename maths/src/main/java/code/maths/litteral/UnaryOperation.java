package code.maths.litteral;
import code.maths.Rate;
import code.maths.litteral.exceptions.NotNumberException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class UnaryOperation extends PrimitiveBoolOperation {

    public UnaryOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.first().getResultClass() != MathType.RATE) {
            throw new NotNumberException(String.valueOf(getIndexInEl()));
        }
        setResultClass(MathType.RATE);
    }

    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument a_ = new Argument();
        Object o_ = arg_.getObject();
        a_.setArgClass(MathType.RATE);
        a_.setObject(((Rate)o_).opposNb());
        setArgument(a_);
        return;
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
