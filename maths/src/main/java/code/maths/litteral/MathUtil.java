package code.maths.litteral;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

final class MathUtil {

    private MathUtil() {
    }

    static Argument processEl(String _el, int _index, boolean _onlycheckSyntax, StringMap<String> _conf) {
        ErrorStatus err_ = new ErrorStatus();
        Delimiters d_ = MathResolver.checkSyntax(_el, _index, err_);
        if (err_.isError()) {
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.NOTHING);
            arg_.setObject(err_);
            return arg_;
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = MathResolver.getOperationsSequence(CustList.FIRST_INDEX, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(el_, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.NOTHING);
            arg_.setObject(err_);
            return arg_;
        }
        CustList<OperationNode> all_ = getSortedDescNodes(op_,_conf,err_);
        if (err_.isError()) {
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.NOTHING);
            arg_.setObject(err_);
            op_.setArgument(arg_);
        }
        if (!_onlycheckSyntax) {
            calculate(all_, _conf, err_);
            if (err_.isError()) {
                Argument arg_ = new Argument();
                arg_.setArgClass(MathType.NOTHING);
                arg_.setObject(err_);
                op_.setArgument(arg_);
            }
            return op_.getArgument();
        }
        Argument a_ = new Argument();
        a_.setArgClass(op_.getResultClass());
        return a_;
    }

    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, StringMap<String> _context, ErrorStatus _error) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            if (_error.isError()) {
                return list_;
            }
            c_ = getAnalyzedNext(c_, _root, list_, _context, _error);
        }
        return list_;
    }

    public static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes,StringMap<String> _context, ErrorStatus _error) {
        OperationNode next_ = createFirstChild(_current, _context, _error);
        if (_error.isError()) {
            return null;
        }
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            current_.analyze(_context, _error);
            if (_error.isError()) {
                return null;
            }
            _sortedNodes.add(current_);
            next_ = createNextSibling(current_, _context, _error);
            if (next_ != null) {
                next_.getParent().appendChild(next_);
                return next_;
            }
            OperationNode par_ = current_.getParent();
            if (par_ == _root) {
                par_.analyze(_context, _error);
                if (_error.isError()) {
                    return null;
                }
                _sortedNodes.add(par_);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }
    private static OperationNode createFirstChild(OperationNode _block, StringMap<String> _context, ErrorStatus _error) {
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
            _error.setIndex(offset_);
            _error.setError(true);
            return null;
        }
        return op_;
    }

    private static OperationNode createNextSibling(OperationNode _block, StringMap<String> _context, ErrorStatus _error) {
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
            _error.setIndex(offset_);
            _error.setError(true);
            return null;
        }
        return op_;
    }
    public static CustList<OperationNode> getDirectChildren(OperationNode _element) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode firstChild_ = _element.getFirstChild();
        OperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    static void calculate(CustList<OperationNode> _nodes, StringMap<String> _context, ErrorStatus _error) {
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                e.calculate(_context, _error);
                if (_error.isError()) {
                    return;
                }
            }
        }
    }
}
