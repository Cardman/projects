package code.maths.litteral;
import code.util.CustList;
import code.util.StringMap;


public abstract class QuickMbOperation extends PrimitiveBoolMbOperation implements ReductibleOperable{

    protected QuickMbOperation(int _index,
                               int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
        for (MbOperationNode o: chidren_) {
            if (o.getResultClass() != MathType.BOOLEAN) {
                _error.setIndex(o.getIndexInEl());
                _error.setError(true);
                return;
            }
        }
        setResultClass(chidren_.last().getResultClass());
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        MbArgument f_ = MbNumParsers.tryGet(this,0);
        boolean abs_ = f_.isBoolVal();
        if (abs_ == absorbingStruct()) {
            setArgument(f_);
            return;
        }
        MbArgument a_ = MbNumParsers.tryGet(this,1);
        setArgument(a_);
    }

    @Override
    public void tryCalculateNode(StringMap<String> _conf, ErrorStatus _error) {
        MbArgument f_ = MbNumParsers.tryGet(this,0);
        MbArgument s_ = MbNumParsers.tryGet(this,1);
        if (f_ == null) {
            return;
        }
        boolean v_ = f_.isBoolVal();
        if (v_ == absorbingStruct()) {
            setArgument(f_);
            return;
        }
        if (s_ == null) {
            return;
        }
        setArgument(s_);
    }

    public abstract boolean absorbingStruct();
}
