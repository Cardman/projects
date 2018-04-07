package code.maths.litteral;
import code.maths.litteral.exceptions.BadMathExpressionException;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

final class MathUtil {

    private MathUtil() {
    }

    static Argument processEl(String _el, int _index, boolean _onlycheckSyntax, StringMap<String> _conf) {
        Delimiters d_ = MathResolver.checkSyntax(_el, _index);
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = MathResolver.getOperationsSequence(CustList.FIRST_INDEX, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(el_, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            throw new BadMathExpressionException(el_);
        }
        CustList<OperationNode> all_ = getSortedDescNodes(op_,_conf);
        analyze(all_, _conf);
        if (!_onlycheckSyntax) {
            calculate(all_, _conf);
            return op_.getArgument();
        }
        Argument a_ = new Argument();
        a_.setArgClass(op_.getResultClass());
        return a_;
    }

    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, StringMap<String> _context) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            c_ = getNext(c_, _root, list_, _context);
        }
        return list_;
    }

    public static OperationNode getNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes,StringMap<String> _context) {
        OperationNode next_ = createFirstChild(_current, _context);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        _sortedNodes.add(_current);
        next_ = createNextSibling(_current, _context);
        if (next_ != null) {
            next_.getParent().appendChild(next_);
            return next_;
        }
        next_ = _current.getParent();
        if (next_ == _root) {
            _sortedNodes.add(next_);
            return null;
        }
        if (next_ != null) {
            _sortedNodes.add(next_);
            OperationNode nextAfter_ = createNextSibling(next_, _context);
            while (nextAfter_ == null) {
                OperationNode par_ = next_.getParent();
                if (par_ == _root) {
                    _sortedNodes.add(par_);
                    break;
                }
                if (par_ == null) {
                    break;
                }
                _sortedNodes.add(par_);
                nextAfter_ = createNextSibling(par_, _context);
                next_ = par_;
            }
            if (nextAfter_ != null) {
                nextAfter_.getParent().appendChild(nextAfter_);
                return nextAfter_;
            }
        }
        return null;
    }
    private static OperationNode createFirstChild(OperationNode _block, StringMap<String> _context) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren() == null || block_.getChildren().isEmpty()) {
            return null;
        }
        String value_ = block_.getChildren().getValue(0);
        Delimiters d_ = block_.getOperations().getDelimiter();
        int curKey_ = block_.getChildren().getKey(0);
        d_.setChildOffest(curKey_);
        int offset_ = block_.getIndexInEl()+curKey_;
        OperationsSequence r_ = MathResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(value_, offset_, _context, CustList.FIRST_INDEX, block_, r_);
        if (op_ == null) {
            throw new BadMathExpressionException(value_);
        }
        return op_;
    }

    private static OperationNode createNextSibling(OperationNode _block, StringMap<String> _context) {
        MethodOperation p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        NatTreeMap<Integer,String> children_ = p_.getChildren();
        if (_block.getIndexChild() + 1 >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(_block.getIndexChild() + 1);
        Delimiters d_ = _block.getOperations().getDelimiter();
        int curKey_ = children_.getKey(_block.getIndexChild() + 1);
        d_.setChildOffest(curKey_);
        int offset_ = p_.getIndexInEl()+curKey_;
        OperationsSequence r_ = MathResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(value_, offset_, _context, _block.getIndexChild() + 1, p_, r_);
        if (op_ == null) {
            throw new BadMathExpressionException(value_);
        }
        return op_;
    }
    public static CustList<OperationNode> getDirectChildren(OperationNode _element) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        if (_element == null) {
            return list_;
        }
        OperationNode firstChild_ = _element.getFirstChild();
        OperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    static void analyze(CustList<OperationNode> _nodes, StringMap<String> _context) {
        for (OperationNode e: _nodes) {
            e.analyze(_nodes, _context);
        }
    }
    static void calculate(CustList<OperationNode> _nodes, StringMap<String> _context) {
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                e.calculate(_nodes, _context);
            }
        }
    }
}
