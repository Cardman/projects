package code.util.opers;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;

public final class StringListUtil {

    private static final char LF = '\r';
    private static final char LR = '\n';
    private static final String EMPTY_STRING = "";

    private StringListUtil() {
    }

    public static EqList<StringList> commonLinesFiles(String _s, String _t) {
        EqList<StringList> results_ = new EqList<StringList>();
        StringList s_ = StringList.splitChars(_s,LR,LF);
        StringList t_ = StringList.splitChars(_t,LR,LF);
        while (true) {
            StringList c_ = firstCommonLinesBlock(s_, t_);
            if (c_.isEmpty()) {
                break;
            }
            results_.add(c_);
            int index_;
            index_ = indexOf(s_, c_);
            int one_ = index_ + c_.size();
            index_ = indexOf(t_, c_);
            int two_ = index_ + c_.size();
            s_ = new StringList(s_.mid(one_));
            t_ = new StringList(t_.mid(two_));
        }
        return results_;
    }

    public static StringList commonSubstrings(String _s, String _t) {
        StringList results_ = new StringList();
        String s_ = _s;
        String t_ = _t;
        while (true) {
            String c_ = firstCommonSubstring(s_, t_);
            if (c_.isEmpty()) {
                break;
            }
            results_.add(c_);
            int one_ = s_.indexOf(c_) + c_.length();
            int two_ = t_.indexOf(c_) + c_.length();
            s_ = s_.substring(one_);
            t_ = t_.substring(two_);
        }
        return results_;
    }
    public static StringList firstCommonLinesBlock(StringList _s, StringList _t) {
        StringList firstOne_ = new StringList();
        StringList firstTwo_ = new StringList();
        int i_ = 0;
        int j_ = 0;
        boolean found_ = false;
        for (int i = 0; i < _s.size(); i++) {
            for (int j = 0; j < _t.size(); j++) {
                if (StringList.quickEq(_s.get(i), _t.get(j))) {
                    i_ = i;
                    j_ = j;
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                break;
            }
        }
        if (!found_) {
            return new StringList();
        }
        while (i_ < _s.size() && j_ < _t.size()) {
            if (!StringList.quickEq(_s.get(i_), _t.get(j_))) {
                break;
            }
            firstOne_.add(_s.get(i_));
            i_++;
            j_++;
        }
        i_ = 0;
        j_ = 0;
        found_ = false;
        for (int j = 0; j < _t.size(); j++) {
            for (int i = 0; i < _s.size(); i++) {
                if (StringList.quickEq(_s.get(i), _t.get(j))) {
                    i_ = i;
                    j_ = j;
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                break;
            }
        }
        while (i_ < _s.size() && j_ < _t.size()) {
            if (!StringList.quickEq(_s.get(i_), _t.get(j_))) {
                break;
            }
            firstTwo_.add(_s.get(i_));
            i_++;
            j_++;
        }
        int lastIndexOne_ = Math.max(lastIndexOf(_s, firstOne_), lastIndexOf(_t,firstOne_));
        int lastIndexTwo_ = Math.max(lastIndexOf(_s,firstTwo_), lastIndexOf(_t,firstTwo_));
        if (lastIndexOne_ > lastIndexTwo_) {
            return firstTwo_;
        }
        return firstOne_;
    }
    private static int indexOf(StringList _list, StringList _sub) {
        int len_ = _list.size();
        for (int i_: _list.indexesOfString(_sub.first())) {
            int i = i_;
            int in_ = CustList.FIRST_INDEX;
            boolean ok_ = true;
            while (in_ < _sub.size() && i < len_) {
                if (!StringList.quickEq(_list.get(i), _sub.get(in_))) {
                    ok_ = false;
                    break;
                }
                in_++;
                i++;
            }
            if (ok_) {
                return i_;
            }
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
    private static int lastIndexOf(StringList _list, StringList _sub) {
        int len_ = _list.size();
        for (int i_: _list.indexesOfString(_sub.first()).getReverse()) {
            int i = i_;
            int in_ = CustList.FIRST_INDEX;
            boolean ok_ = true;
            while (in_ < _sub.size() && i < len_) {
                if (!StringList.quickEq(_list.get(i), _sub.get(in_))) {
                    ok_ = false;
                    break;
                }
                in_++;
                i++;
            }
            if (ok_) {
                return i_;
            }
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
    public static String firstCommonSubstring(String _s, String _t) {
        StringBuilder firstOne_ = new StringBuilder();
        StringBuilder firstTwo_ = new StringBuilder();
        int i_ = 0;
        int j_ = 0;
        boolean found_ = false;
        for (int i = 0; i < _s.length(); i++) {
            for (int j = 0; j < _t.length(); j++) {
                if (_s.charAt(i) == _t.charAt(j)) {
                    i_ = i;
                    j_ = j;
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                break;
            }
        }
        if (!found_) {
            return EMPTY_STRING;
        }
        while (i_ < _s.length() && j_ < _t.length()) {
            if (_s.charAt(i_) != _t.charAt(j_)) {
                break;
            }
            firstOne_.append(_s.charAt(i_));
            i_++;
            j_++;
        }
        i_ = 0;
        j_ = 0;
        found_ = false;
        for (int j = 0; j < _t.length(); j++) {
            for (int i = 0; i < _s.length(); i++) {
                if (_s.charAt(i) == _t.charAt(j)) {
                    i_ = i;
                    j_ = j;
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                break;
            }
        }
        while (i_ < _s.length() && j_ < _t.length()) {
            if (_s.charAt(i_) != _t.charAt(j_)) {
                break;
            }
            firstTwo_.append(_s.charAt(i_));
            i_++;
            j_++;
        }
        int lastIndexOne_ = Math.max(_s.lastIndexOf(firstOne_.toString()), _t.lastIndexOf(firstOne_.toString()));
        int lastIndexTwo_ = Math.max(_s.lastIndexOf(firstTwo_.toString()), _t.lastIndexOf(firstTwo_.toString()));
        if (lastIndexOne_ > lastIndexTwo_) {
            return firstTwo_.toString();
        }
        return firstOne_.toString();
    }
}
