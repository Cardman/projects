package code.maths.litteral;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class CmpMbOperation extends PrimitiveBoolMbOperation {

    public CmpMbOperation(int _index,
                          int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static MbArgument calculateLower(MbArgument _a, MbArgument _b) {
        MbArgument a_ = new MbArgument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(Rate.strLower(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }

    static MbArgument calculateGreater(MbArgument _a, MbArgument _b) {
        MbArgument a_ = new MbArgument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(Rate.strGreater(_a.getRateVal(), _b.getRateVal()));
        return a_;
    }
    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
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
        MbArgument first_ = MbNumParsers.tryGet(this,0);
        MbArgument second_ = MbNumParsers.tryGet(this,1);
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringUtil.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else {
            if (StringUtil.quickEq(op_, GREATER_EQ)) {
                complement_ = true;
                useOp_ = LOWER;
            }
        }
        MbArgument arg_;
        if (StringUtil.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(first_, second_);
        } else {
            arg_ = calculateGreater(first_, second_);
        }
        boolean b_ = arg_.isBoolVal();
        if (complement_) {
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
