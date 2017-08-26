package code.maths.litteral;
import code.util.CustList;
import code.util.EnumList;
import code.util.StringMap;

public abstract class InvokingOperation extends MethodOperation {

    public InvokingOperation(String _el, int _index,
            StringMap<String> _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static EnumList<MathType> listClasses(CustList<OperationNode> _children) {
        EnumList<MathType> firstArgs_ = new EnumList<MathType>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getResultClass());
        }
        return firstArgs_;
    }

    static CustList<Argument> listArguments(CustList<OperationNode> _children) {
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getArgument());
        }
        return firstArgs_;
    }
}
