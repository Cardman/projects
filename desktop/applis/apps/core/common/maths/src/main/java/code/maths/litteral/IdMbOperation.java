package code.maths.litteral;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;

public final class IdMbOperation extends MethodMbOperation {

    public IdMbOperation(int _index,
                         int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 1) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(chidren_.first().getResultClass());
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        setArgument(MbNumParsers.tryGet(this,0));
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }
}
