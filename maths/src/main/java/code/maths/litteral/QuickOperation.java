package code.maths.litteral;
import code.util.CustList;
import code.util.StringMap;


public abstract class QuickOperation extends PrimitiveBoolOperation implements ReductibleOperable{

    public QuickOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        for (OperationNode o: chidren_) {
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
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode first_ = chidren_.first();
        Argument f_ = first_.getArgument();
        Object abs_ = f_.getObject();
        if (abs_ == (Boolean)absorbingStruct()) {
            setArgument(f_);
            return;
        }
        OperationNode last_ = chidren_.last();
        Argument a_ = last_.getArgument();
        setArgument(a_);
    }

    @Override
    public void tryCalculateNode(StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode first_ = chidren_.first();
        OperationNode last_ = chidren_.last();
        Argument f_ = first_.getArgument();
        Argument s_ = last_.getArgument();
        if (f_ == null) {
            return;
        }
        Object v_ = f_.getObject();
        if (v_ == (Boolean)absorbingStruct()) {
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
