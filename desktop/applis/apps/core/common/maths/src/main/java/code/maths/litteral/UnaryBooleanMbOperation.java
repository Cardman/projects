package code.maths.litteral;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;

public final class UnaryBooleanMbOperation extends PrimitiveBoolMbOperation {

    public UnaryBooleanMbOperation(int _index,
                                   int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
        if (chidren_.first().getResultClass()!= MathType.BOOLEAN) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(MathType.BOOLEAN);
    }
    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        MbArgument arg_ = MbNumParsers.tryGet(this,0);
        boolean b_ = arg_.isBoolVal();
        b_ = !b_;
        MbArgument a_ = new MbArgument();
        a_.setArgClass(MathType.BOOLEAN);
        a_.setObject(b_);
        setArgument(a_);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }
}
