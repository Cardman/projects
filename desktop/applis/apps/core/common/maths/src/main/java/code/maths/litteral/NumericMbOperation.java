package code.maths.litteral;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class NumericMbOperation extends MethodMbOperation {

    protected NumericMbOperation(int _index,
                                 int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static MbArgument calculateSum(MbArgument _a, MbArgument _b) {
        MbArgument a_ = new MbArgument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.plus(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }
    static MbArgument calculateDiff(MbArgument _a, MbArgument _b) {
        MbArgument a_ = new MbArgument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.minus(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }
    static MbArgument calculateMult(MbArgument _a, MbArgument _b) {
        MbArgument a_ = new MbArgument();
        a_.setArgClass(MathType.RATE);
        a_.setObject(Rate.multiply(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }
    static MbArgument calculateDiv(MbArgument _a, MbArgument _b, int _offset, ErrorStatus _error) {
        MbArgument a_ = new MbArgument();
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
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
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
    abstract MbArgument calculateOper(MbArgument _a, String _op, MbArgument _b, int _offset, ErrorStatus _error);

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
        MbArgument a_ = chidren_.first().getArgument();
        MbArgument r_;
        int i_ = IndexConstants.SECOND_INDEX;
        for (IndexStrPart e: getOperations().getOperators().getValues()) {
            MbArgument c_ = chidren_.get(i_).getArgument();
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
