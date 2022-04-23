package code.maths.litteral;

import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class EqMbOperation extends PrimitiveBoolMbOperation {

    private MathType eqType;
    public EqMbOperation(int _index,
                         int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    MbArgument calculateEq(MbArgument _a, MbArgument _b) {
        MbArgument a_ = new MbArgument();
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
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
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
        MbArgument first_ = MbNumParsers.tryGet(this,0);
        MbArgument second_ = MbNumParsers.tryGet(this,1);
        boolean complement_ = false;
        String op_ = getOpers().values().first().trim();
        if (StringUtil.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        MbArgument arg_ = calculateEq(first_, second_);
        if (complement_) {
            boolean b_ = arg_.isBoolVal();
            b_ = !b_;
            arg_.setObject(b_);
        }
        setArgument(arg_);
    }


}
