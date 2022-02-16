package code.formathtml.common;

import code.util.EntryCust;
import code.util.IntTreeMap;

public final class RendBlockUtil {
    private RendBlockUtil() {
    }

    public static int retrieve(int _offset, IntTreeMap<Integer> _escapedChar) {
        int delta_ = getDelta(_offset, _escapedChar);
        return _offset + delta_;
    }

    private static int getDelta(int _offset, IntTreeMap< Integer> _allEsc) {
        int delta_ = 0;
        int nbIndexes_ = getIndexesCount(_allEsc, _offset);
        for (int i = 0; i < nbIndexes_; i++) {
            delta_ += _allEsc.getValue(i);
        }
        return delta_;
    }

    private static int getIndexesCount(IntTreeMap< Integer> _t, int _offset) {
        int delta_ = 0;
        int count_ = 0;
        for (EntryCust<Integer, Integer> e: _t.entryList()) {
            if (e.getKey() - delta_ >= _offset) {
                return count_;
            }
            delta_ += e.getValue();
            count_++;
        }
        return count_;
    }
}
