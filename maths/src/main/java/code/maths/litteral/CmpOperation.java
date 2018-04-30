package code.maths.litteral;
import code.maths.Rate;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class CmpOperation extends PrimitiveBoolOperation {

    public CmpOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateLower(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(Rate.strLower((Rate)_a.getObject(),(Rate) _b.getObject()));
        return a_;
    }

    static Argument calculateGreater(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(Rate.strGreater((Rate)_a.getObject(),(Rate) _b.getObject()));
        return a_;
    }
    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 2) {
            _error.setError(true);
            _error.setIndex(getIndexInEl());
            _error.setString("");
            return;
        }
        MathType first_ = chidren_.first().getResultClass();
        MathType second_ = chidren_.last().getResultClass();
        if (first_ == MathType.RATE && second_ == MathType.RATE) {
            setResultClass(MathType.BOOLEAN);
            return;
        }
        _error.setError(true);
        _error.setIndex(getIndexInEl());
        _error.setString("");
    }
    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        Argument arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(first_, second_);
        } else {
            arg_ = calculateGreater(first_, second_);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
            b_ = !b_;
            arg_.setObject(b_);
        }
        setArgument(arg_);
        setNextSiblingsArg(arg_);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
