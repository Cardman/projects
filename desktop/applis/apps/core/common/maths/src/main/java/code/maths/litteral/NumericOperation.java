package code.maths.litteral;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class NumericOperation extends MethodOperation {

    protected NumericOperation(int _index,
                               int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static Argument calculateSum(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.plus(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }
    static Argument calculateDiff(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.minus(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }
    static Argument calculateMult(Argument _a, Argument _b) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.multiply(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }
    static Argument calculateDiv(Argument _a, Argument _b, int _offset, ErrorStatus _error) {
        Argument a_ = new Argument();
        a_.setArgClass(MathType.RATE);
        Rate den_ = _b.getRateVal();
        if (den_.isZero()) {
            _error.setError(true);
            _error.setIndex(_offset);
            a_.setArgClass(MathType.NOTHING);
            a_.setObject(_error);
            return a_;
        }
        a_.setObject(Rate.divide(_a.getRateVal(), den_));
        return a_;
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        MathType a_ = chidren_.first().getResultClass();
        MathType r_;
        int i_ = IndexConstants.SECOND_INDEX;
        for (IndexStrPart e: getOperations().getOperators().getValues()) {
            MathType c_ = chidren_.get(i_).getResultClass();
            r_ = analyzeOper(a_, e.getPart(), c_, chidren_.get(i_).getIndexInEl(), _error);
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
        int i_ = IndexConstants.SECOND_INDEX;
        for (IndexStrPart e: getOperations().getOperators().getValues()) {
            Argument c_ = chidren_.get(i_).getArgument();
            r_ = calculateOper(a_, e.getPart(), c_, chidren_.get(i_).getIndexInEl(), _error);
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
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }
}
