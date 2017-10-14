package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.NatTreeMap;

public abstract class MethodOperation extends OperationNode {

    private boolean initializedFirstChild;

    private OperationNode firstChild;

    private NatTreeMap<Integer,String> children;

    public MethodOperation(int _index, ContextEl _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
        children = new NatTreeMap<Integer,String>();
        calculateChildren();
    }

    final CustList<OperationNode> getChildrenNodes() {
        CustList<OperationNode> chidren_ = new CustList<OperationNode>();
        for (OperationNode o: ElUtil.getDirectChildren(this)) {
            chidren_.add((OperationNode)o);
        }
        return chidren_;
    }

    abstract void calculateChildren();

    @Override
    final boolean isFirstChild() {
        return getIndexChild() == CustList.FIRST_INDEX;
    }
    @Override
    public final OperationNode getFirstChild() {
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
        OperationsSequence r_ = ElResolver.getOperationsSequence(getIndexInEl(), value_, getConf(), d_);
        firstChild = createOperationNode(getIndexInEl()+curKey_, getConf(), CustList.FIRST_INDEX, this, r_);
        return firstChild;
    }

    public final NatTreeMap<Integer, String> getChildren() {
        return children;
    }
}
