package code.maths.litteral;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class EqOperation extends PrimitiveBoolOperation {

    private MathType eqType;
    public EqOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    Argument calculateEq(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.BOOLEAN);
        if (eqType == MathType.RATE) {
            a_.setObject(_a.getRateVal().eq(_b.getRateVal()));
        } else if (eqType == MathType.SET) {
            a_.setObject(_a.getListVal().eq(_b.getListVal()));
        } else {
            //first_ instanceof Boolean
            a_.setObject(_a.isBoolVal() == _b.isBoolVal());
        }
        return a_;
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 2) {
            _error.setError(true);
            _error.setIndex(getIndexInEl());
            return;
        }
        MathType first_ = chidren_.first().getResultClass();
        MathType second_ = chidren_.last().getResultClass();
        if (first_ == second_) {
            eqType = first_;
            setResultClass(MathType.BOOLEAN);
            return;
        }
        _error.setError(true);
        _error.setIndex(getIndexInEl());
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        Argument second_ = chidren_.last().getArgument();
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        if (StringUtil.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        Argument arg_ = calculateEq(first_, second_);
        if (complement_) {
            boolean b_ = arg_.isBoolVal();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setArgument(arg_);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

}
