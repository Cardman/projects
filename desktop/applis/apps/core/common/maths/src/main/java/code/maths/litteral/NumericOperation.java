package code.maths.litteral;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.StringMap;

public abstract class NumericOperation extends MethodOperation {

    public NumericOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static Argument calculateSum(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.plus((Rate) _a.getObject(), (Rate) _b.getObject()));
        return a_;
    }
    static Argument calculateDiff(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.minus((Rate) _a.getObject(), (Rate) _b.getObject()));
        return a_;
    }
    static Argument calculateMult(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.multiply((Rate) _a.getObject(), (Rate) _b.getObject()));
        return a_;
    }
    static Argument calculateDiv(Argument _a, Argument _b, int _offset, ErrorStatus _error) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        Rate den_ = (Rate)_b.getObject();
        if (den_.isZero()) {
            _error.setError(true);
            _error.setIndex(_offset);
            a_.setArgClass(MathType.NOTHING);
            a_.setObject(_error);
            return a_;
        }
        a_.setObject(Rate.divide((Rate) _a.getObject(), den_));
        return a_;
    }

    static MathType getResultClass(MathType _a, MathType _b) {
        return MathType.RATE;
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        MathType a_ = chidren_.first().getResultClass();
        MathType r_;
        int i_ = CustList.SECOND_INDEX;
        for (EntryCust<Integer, String> e: getOperations().getOperators().entryList()) {
            MathType c_ = chidren_.get(i_).getResultClass();
            r_ = analyzeOper(a_, e.getValue(), c_, chidren_.get(i_).getIndexInEl(), _error);
            if (_error.isError()) {
                return;
            }
            a_ = r_;
            i_++;
        }
        setResultClass(a_);
    }

    abstract MathType analyzeOper(MathType _a, String _op, MathType _b, int _offset, ErrorStatus _error);
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, int _offset, ErrorStatus _error);

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument r_;
        int i_ = CustList.SECOND_INDEX;
        for (EntryCust<Integer, String> e: getOperations().getOperators().entryList()) {
            Argument c_ = chidren_.get(i_).getArgument();
            r_ = calculateOper(a_, e.getValue(), c_, chidren_.get(i_).getIndexInEl(), _error);
            if (_error.isError()) {
                return;
            }
            a_ = r_;
            i_++;
        }
        setArgument(a_);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
