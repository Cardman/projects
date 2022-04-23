package code.maths.litteral;

import code.maths.Rate;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class UnaryMbOperation extends PrimitiveBoolMbOperation {

    public UnaryMbOperation(int _index,
                            int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
        if (chidren_.first().getResultClass() != MathType.RATE) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(MathType.RATE);
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        MbArgument arg_ = MbNumParsers.tryGet(this,0);
        MbArgument a_ = new MbArgument();
        Rate o_ = arg_.getRateVal();
        a_.setArgClass(MathType.RATE);
        if (StringUtil.quickEq(getOpers().firstValue().trim(), UNARY_MINUS)) {
            a_.setObject(o_.opposNb());
        } else {
            a_.setObject(o_);
        }
        setArgument(a_);
    }

}
