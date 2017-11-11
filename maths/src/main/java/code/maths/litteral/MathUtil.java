package code.maths.litteral;
import code.util.CustList;
import code.util.StringMap;

final class MathUtil {

    private MathUtil() {
    }

    static Argument processEl(String _el, int _index, boolean _onlycheckSyntax, StringMap<String> _conf) {
        Delimiters d_ = MathResolver.checkSyntax(_el, _index);
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = MathResolver.getOperationsSequence(CustList.FIRST_INDEX, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(el_, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_);
        analyze(all_, _conf);
        if (!_onlycheckSyntax) {
            calculate(all_, _conf);
            return op_.getArgument();
        }
        Argument a_ = new Argument();
        a_.setArgClass(op_.getResultClass());
        return a_;
    }

    static CustList<OperationNode> getOperationNodes(OperationNode _root) {
        CustList<OperationNode> all_ = new CustList<OperationNode>();
        for (OperationNode s: getSortedDescNodes(_root)) {
            all_.add(s);
        }
        int order_ = 0;
        while (true) {
            CustList<OperationNode> next_ = new CustList<OperationNode>();
            for (OperationNode e: all_) {
                if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                    continue;
                }
                OperationNode cur_ = e;
                boolean tonumber_ = true;
                while (cur_ != null) {
                    int index_ = cur_.getIndexChild() - 1;
                    if (index_ >= CustList.FIRST_INDEX) {
                        CustList<OperationNode> sn_ = getDirectChildren(cur_.getParent());
                        OperationNode s_ = sn_.get(index_);
                        OperationNode prev_ = (OperationNode) s_;
                        if (prev_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                            tonumber_ = false;
                            break;
                        }
                    }
                    cur_ = cur_.getParent();
                }
                if (!tonumber_) {
                    continue;
                }
                CustList<OperationNode> list_ = getDirectChildren(e);
                if (!list_.isEmpty()) {
                    OperationNode op_ = (OperationNode) list_.last();
                    if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                        continue;
                    }
                }
                next_.add(e);
            }
            if (next_.isEmpty()) {
                break;
            }
            for (OperationNode o: next_) {
                o.setOrder(order_);
                order_++;
            }
        }
        all_.sortElts(new ComparatorOrder());
        return all_;
    }
    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        if (_root == null) {
            return list_;
        }
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static OperationNode getNext(OperationNode _current, OperationNode _root) {
        OperationNode n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getNextSibling();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getParent();
        if (n_ == _root) {
            return null;
        }
        if (n_ != null) {
            OperationNode next_ = n_.getNextSibling();
            while (next_ == null) {
                OperationNode par_ = n_.getParent();
                if (par_ == _root) {
                    break;
                }
                if (par_ == null) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ != null) {
                return next_;
            }
        }
        return null;
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
