package code.maths.litteral;
import code.maths.litteralcom.*;
import code.util.*;
import code.util.core.*;

final class MathUtil {

    private MathUtil() {
    }

    static boolean usedId(String _el, String _prefix, StringList _mids, String _id) {
        MbDelimiters d_ = MathResolver.checkSyntax(_el, new ErrorStatus());
        for (MatVariableInfo v: d_.getVariables()) {
            if (contains(_mids, v.getName(), _prefix, _id)) {
                return true;
            }
        }
        for (StringList v: d_.getStringInfo()) {
            for (String e: v) {
                if (contains(_mids, e, _prefix, _id)) {
                    return true;
                }
                if (StringUtil.quickEq(e,_id)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean contains(StringList _mids, String _e, String _prefix, String _id) {
        for (String m: _mids) {
            if (StringUtil.quickEq(_e, _prefix +m+ _id)) {
                return true;
            }
        }
        return false;
    }

    static String rename(String _el, String _prefix, StringList _mids, String _id, String _target) {
        MbDelimiters d_ = MathResolver.checkSyntax(_el, new ErrorStatus());
        Ints dels_ = d_.getDelStringsChars();
        StringList parts_ = new StringList();
        StringBuilder part_ = new StringBuilder();
        int len_ = _el.length();
        for (int i = 0; i < len_; i++) {
            int index_ = dels_.indexOfNb(i);
            if (index_ < 0) {
                part_.append(_el.charAt(i));
                continue;
            }
            char cur_ = _el.charAt(i);
            if (index_ % 2 == 0) {
                parts_.add(part_.toString());
                part_.delete(0,part_.length());
                part_.append(cur_);
            } else {
                part_.append(cur_);
                parts_.add(part_.toString());
                part_.delete(0,part_.length());
            }
        }
        parts_.add(part_.toString());
        int nbParts_ = parts_.size();
        for (int i = 0; i < nbParts_; i++) {
            if (i % 2 == 0) {
                parts_.set(i, replaceWordsJoin(parts_.get(i), buildList(_prefix, _mids, _id, _target)));
            } else {
                parts_.set(i, replaceWordsSetJoin(parts_.get(i), build(_prefix, _mids, _id, _target)));
            }
        }
        return StringUtil.join(parts_, "");
    }

    static String replaceWordsJoin(String _str, StringMap<String> _map) {
        return StringUtil.join(replaceWords(_str, _map),"");
    }

    static String replaceWordsSetJoin(String _str, StringMap<String> _map) {
        return StringUtil.join(replaceWordsSet(_str, _map),"");
    }

    static StringList replaceWords(String _str, StringMap<String> _map) {
        StringList list_ = MathExpUtil.getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int size_ = list_.size();
        for (int i = 0; i < size_; i++) {
            String t_ = list_.get(i);
            int possibleNext_ = i + 1;
            if (_map.contains(t_) && (possibleNext_ == size_ || !list_.get(possibleNext_).trim().startsWith("("))) {
                newList_.add(_map.getVal(t_));
            } else {
                newList_.add(t_);
            }
        }
        return newList_;
    }

    static StringList replaceWordsSet(String _str, StringMap<String> _map) {
        StringList list_ = MathExpUtil.getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int size_ = list_.size();
        for (int i = 0; i < size_; i++) {
            String t_ = list_.get(i);
            if (_map.contains(t_)) {
                newList_.add(_map.getVal(t_));
            } else {
                newList_.add(t_);
            }
        }
        return newList_;
    }
    static StringMap<String> build(String _prefix, StringList _mids, String _id, String _target) {
        StringMap<String> replace_ = new StringMap<String>();
        replace_.addEntry(_id,_target);
        for (String m: _mids) {
            replace_.addEntry(_prefix+m+_id,_prefix+m+_target);
        }
        return replace_;
    }
    static StringMap<String> buildList(String _prefix, StringList _mids, String _id, String _target) {
        StringMap<String> replace_ = new StringMap<String>();
        for (String m: _mids) {
            replace_.addEntry(_prefix+m+_id,_prefix+m+_target);
        }
        return replace_;
    }
    static MbArgument processEl(String _el, boolean _onlycheckSyntax, StringMap<String> _conf) {
        ErrorStatus err_ = new ErrorStatus();
//        MbDelimiters d_ = MathResolver.checkSyntax(_el, err_);
//        if (err_.isError()) {
//            MbArgument arg_ = new MbArgument();
//            arg_.setArgClass(MathType.NOTHING);
//            arg_.setObject(err_);
//            return arg_;
//        }
//        MbOperationsSequence opTwo_ = MathResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_);
//        MbOperationNode op_ = MbOperationNode.createOperationNodeAndChild(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_);
//        if (op_ == null) {
//            MbArgument arg_ = new MbArgument();
//            arg_.setArgClass(MathType.NOTHING);
//            arg_.setObject(err_);
//            return arg_;
//        }
        CustList<MbOperationNode> all_ = getSortedDescNodes(_el, _conf,err_);
        if (all_.isEmpty()) {
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
            return MbArgument.ofNullable(all_.last().getArgument());
        }
        MbArgument a_ = new MbArgument();
        a_.setArgClass(all_.last().getResultClass());
        return a_;
    }

    public static CustList<MbOperationNode> getSortedDescNodes(String _el, StringMap<String> _context, ErrorStatus _error) {
        MbDelimiters d_ = MathResolver.checkSyntax(_el, _error);
        if (_error.isError()) {
            return new CustList<MbOperationNode>();
        }
        CustList<MbOperationNode> list_ = new CustList<MbOperationNode>();
        MbOperationsSequence opTwo_ = MathResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_);
        MbOperationNode op_ = MbOperationNode.createOperationNodeAndChild(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_);
        MbOperationNode c_ = op_;
        while (c_ != null) {
            c_ = getAnalyzedNext(c_, op_, list_, _context, _error, d_);
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
