package code.maths.litteral;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class UnaryOperation extends PrimitiveBoolOperation {

    public UnaryOperation(int _index,
                          int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
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
        Rate o_ = arg_.getRateVal();
        a_.setArgClass(MathType.RATE);
        if (StringUtil.quickEq(getOperations().getOperators().firstValue().trim(), UNARY_MINUS)) {
            a_.setObject(o_.opposNb());
        } else {
            a_.setObject(o_);
        }
        setArgument(a_);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }
}
