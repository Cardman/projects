package code.maths.litteral;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

public abstract class MethodOperation extends OperationNode {

    private boolean initializedFirstChild;

    private OperationNode firstChild;

    private NatTreeMap<Integer,String> children;

    public MethodOperation(String _el, int _index, StringMap<String> _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
        children = new NatTreeMap<Integer,String>();
        calculateChildren();
    }

    CustList<OperationNode> getChildrenAmong(CustList<OperationNode> _nodes, boolean _analayze) {
        CustList<OperationNode> chidren_ = new CustList<OperationNode>();
        for (OperationNode o: _nodes) {
            if (o.getParent() == this) {
//                if (_analayze && o.getResultClass() == null) {
//                    throw new AnalyzingException();
//                } else if (!_analayze && o.getArgument() == null) {
//                    throw new EvalutationException();
//                }
                chidren_.add(o);
            }
        }
        return chidren_;
    }

    abstract void calculateChildren();

//    @Override
//    boolean isFirstLeaf() {
//        if (getFirstChild() != null) {
//            return false;
//        }
//        return getIndexChild() == CustList.FIRST_INDEX;
//    }
//
//    @Override
//    boolean isRealLeaf() {
//        return false;
//    }

    @Override
    boolean isFirstChild() {
        return getIndexChild() == CustList.FIRST_INDEX;
    }

    @Override
    public OperationNode getFirstChild() {
        if (initializedFirstChild) {
            return firstChild;
        }
        initializedFirstChild = true;
        if (children == null || children.isEmpty()) {
            return null;
        }
        String value_ = children.getValue(0);
        Delimiters d_ = getOperations().getDelimiter();
        int curKey_ = children.getKey(0);
        d_.setChildOffest(curKey_);
        OperationsSequence r_ = MathResolver.getOperationsSequence(getIndexInEl(), value_, getConf(), d_);
//        if (r_.getOperators().isEmpty()) {
//            firstChild = new ConstantOperation(value_, children.getKey(0), getImportingPage(), this, r_, CustList.FIRST_INDEX);
//            return firstChild;
//        }
//        if (r_.getPriority() == ExpressionLanguageResolver.FCT_OPER_PRIO) {
//            firstChild = new FctOperation(value_, children.getKey(0), getImportingPage(), CustList.FIRST_INDEX, this, r_);
//            return firstChild;
//        }
//        if (r_.getPriority() == ExpressionLanguageResolver.ARR_OPER_PRIO) {
//            firstChild = new ArrOperation(value_, children.getKey(0), getImportingPage(), CustList.FIRST_INDEX, this, r_);
//            return firstChild;
//        }
        firstChild = createOperationNode(value_, getIndexInEl()+curKey_, getConf(), CustList.FIRST_INDEX, this, r_);
        return firstChild;
    }

    public final NatTreeMap<Integer, String> getChildren() {
        return children;
    }
}
