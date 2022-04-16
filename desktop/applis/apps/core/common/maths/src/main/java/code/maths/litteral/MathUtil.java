package code.maths.litteral;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

final class MathUtil {

    private MathUtil() {
    }

    static MbArgument processEl(String _el, boolean _onlycheckSyntax, StringMap<String> _conf) {
        ErrorStatus err_ = new ErrorStatus();
        MbDelimiters d_ = MathResolver.checkSyntax(_el, err_);
        if (err_.isError()) {
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.NOTHING);
            arg_.setObject(err_);
            return arg_;
        }
        MbOperationsSequence opTwo_ = MathResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_);
        MbOperationNode op_ = MbOperationNode.createOperationNodeAndChild(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.NOTHING);
            arg_.setObject(err_);
            return arg_;
        }
        CustList<MbOperationNode> all_ = getSortedDescNodes(op_,_conf,err_, d_);
        if (err_.isError()) {
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.NOTHING);
            arg_.setObject(err_);
            return arg_;
        }
        if (!_onlycheckSyntax) {
            calculate(all_, _conf, err_);
            if (err_.isError()) {
                MbArgument arg_ = new MbArgument();
                arg_.setArgClass(MathType.NOTHING);
                arg_.setObject(err_);
                return arg_;
            }
            return MbArgument.ofNullable(op_.getArgument());
        }
        MbArgument a_ = new MbArgument();
        a_.setArgClass(op_.getResultClass());
        return a_;
    }

    public static CustList<MbOperationNode> getSortedDescNodes(MbOperationNode _root, StringMap<String> _context, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> list_ = new CustList<MbOperationNode>();
        MbOperationNode c_ = _root;
        while (c_ != null) {
            c_ = getAnalyzedNext(c_, _root, list_, _context, _error, _del);
        }
        return list_;
    }

    public static MbOperationNode getAnalyzedNext(MbOperationNode _current, MbOperationNode _root, CustList<MbOperationNode> _sortedNodes, StringMap<String> _context, ErrorStatus _error, MbDelimiters _del) {
        MbOperationNode next_ = create(_error, _del, 0, _current);
        if (_error.isError()) {
            return null;
        }
        if (next_ != null) {
            ((MethodMbOperation) _current).appendChild(next_);
            return next_;
        }
        MbOperationNode current_ = _current;
        while (true) {
            current_.analyze(_context, _error, _del);
            if (_error.isError()) {
                return null;
            }
            current_.setOrder(_sortedNodes.size());
            tryReduce(_context, _error, current_);
            _sortedNodes.add(current_);
            next_ = create(_error, _del, current_.getIndexChild() + 1, current_.getParent());
            if (_error.isError()) {
                return null;
            }
            if (next_ != null) {
                next_.getParent().appendChild(next_);
                return next_;
            }
            MbOperationNode par_ = current_.getParent();
            if (par_ == _root) {
                return processRoot(_sortedNodes, _context, _error, par_, _del);
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }

    private static MbOperationNode processRoot(CustList<MbOperationNode> _sortedNodes, StringMap<String> _context, ErrorStatus _error, MbOperationNode _par, MbDelimiters _del) {
        _par.analyze(_context, _error, _del);
        if (!_error.isError()) {
            _par.setOrder(_sortedNodes.size());
            tryReduce(_context, _error, _par);
            _sortedNodes.add(_par);
        }
        return null;
    }

    private static void tryReduce(StringMap<String> _context, ErrorStatus _error, MbOperationNode _current) {
        if (_current instanceof ReductibleOperable) {
            ((ReductibleOperable) _current).tryCalculateNode(_context, _error);
        }
    }

    private static MbOperationNode create(ErrorStatus _error, MbDelimiters _delimiter, int _nextIndex, MbOperationNode _op) {
        if (!(_op instanceof MethodMbOperation)) {
            return null;
        }
        MethodMbOperation block_ = (MethodMbOperation) _op;
        StrTypes children_ = block_.getChildren();
        if (_nextIndex >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(_nextIndex);
        int curKey_ = children_.getKey(_nextIndex);
        int offset_ = block_.getIndexInEl()+curKey_;
        MbOperationsSequence r_ = MathResolver.getOperationsSequence(offset_, value_, _delimiter);
        MbOperationNode op_ = MbOperationNode.createOperationNodeAndChild(offset_, _nextIndex, block_, r_);
        if (op_ == null) {
            _error.setIndex(offset_);
            _error.setError(true);
            return null;
        }
        return op_;
    }

    static void calculate(CustList<MbOperationNode> _nodes, StringMap<String> _context, ErrorStatus _error) {
        int fr_ = 0;
        int len_ = _nodes.size();
        while (fr_ < len_) {
            MbOperationNode o = _nodes.get(fr_);
            MbArgument pre_ = o.getArgument();
            if (pre_ != null) {
                boolean st_ = pre_.isBoolVal();
                fr_ = MbOperationNode.getNextIndex(o, st_,fr_+1);
                continue;
            }
            o.calculate(_context, _error);
            if (_error.isError()) {
                return;
            }
            MbArgument res_ = MbArgument.ofNullable(o.getArgument());
            boolean st_ = res_.isBoolVal();
            fr_ = MbOperationNode.getNextIndex(o, st_,fr_+1);
        }
    }
}
