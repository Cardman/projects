package code.maths.litteral;
import code.maths.litteral.exceptions.NotBooleanException;
import code.util.CustList;
import code.util.StringMap;


public abstract class QuickOperation extends PrimitiveBoolOperation {

    public QuickOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        for (OperationNode o: chidren_) {
            if (o.getResultClass() != MathType.BOOLEAN) {
                throw new NotBooleanException(String.valueOf(o.getIndexInEl()));
            }
        }
        setResultClass(chidren_.last().getResultClass());
    }

    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        setArgument(chidren_.last().getArgument());
        setNextSiblingsArg(chidren_.last().getArgument());
    }
}
