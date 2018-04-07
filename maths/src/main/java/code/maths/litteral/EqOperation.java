package code.maths.litteral;
import code.maths.MathList;
import code.maths.Rate;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class EqOperation extends PrimitiveBoolOperation {

    public EqOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateEq(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        Object first_ = _a.getObject();
        Object second_ = _b.getObject();
        if (first_ instanceof Rate) {
            a_.setObject(((Rate)first_).eq((Rate)second_));
        } else if (first_ instanceof MathList) {
            a_.setObject(((MathList)first_).eq((MathList)second_));
        } else {
            //first_ instanceof Boolean
            a_.setObject(((Boolean)first_).booleanValue() == ((Boolean)second_).booleanValue());
        }
        return a_;
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 2) {
            _error.setError(true);
            _error.setIndex(getIndexInEl());
            return;
        }
        MathType first_ = chidren_.first().getResultClass();
        MathType second_ = chidren_.last().getResultClass();
        if (first_ == second_) {
            setResultClass(MathType.BOOLEAN);
            return;
        }
        _error.setError(true);
        _error.setIndex(getIndexInEl());
    }

    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_);
        if (complement_) {
            Boolean b_ = (Boolean) arg_.getObject();
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
