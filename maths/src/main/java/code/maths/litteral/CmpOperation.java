package code.maths.litteral;
import code.maths.Rate;
import code.maths.litteral.exceptions.NotComparableException;
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
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        MathType first_ = chidren_.first().getResultClass();
        MathType second_ = chidren_.last().getResultClass();
        if (first_ == MathType.RATE && second_ == MathType.RATE) {
            setResultClass(MathType.BOOLEAN);
            return;
        }
        throw new NotComparableException(String.valueOf(getIndexInEl()));
    }
    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
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
