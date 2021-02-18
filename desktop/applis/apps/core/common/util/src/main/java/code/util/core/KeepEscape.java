package code.util.core;

final class KeepEscape {
    private static final char ESCAPING_CHAR = '\\';
    private final int next;
    private final boolean keep;

    KeepEscape(int _next, boolean _keep) {
        next = _next;
        keep = _keep;
    }
    static KeepEscape keep(int _index,String _input, int _begin, char _meta) {
        int index_ = _index;
        int i_ = index_ - 1;
        int nbSl_ = 0;
        while (i_ >= _begin) {
            if (_input.charAt(i_) != ESCAPING_CHAR) {
                break;
            }
            nbSl_++;
            i_--;
        }
        if (nbSl_%2 == 0) {
            return new KeepEscape(index_,false);
        }
        int ind_ = _input.indexOf(_meta, index_+1);
        if (ind_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            index_ = IndexConstants.INDEX_NOT_FOUND_ELT;
            return new KeepEscape(index_,false);
        }
        index_ = ind_;
        return new KeepEscape(index_,true);
    }

    public int getNext() {
        return next;
    }

    public boolean isKeep() {
        return keep;
    }
}
