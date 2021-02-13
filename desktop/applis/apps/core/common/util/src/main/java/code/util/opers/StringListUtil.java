package code.util.opers;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class StringListUtil {

    private static final char LF = '\r';
    private static final char LR = '\n';
    private static final String EMPTY_STRING = "";

    private StringListUtil() {
    }

    public static CustList<StringList> commonLinesFiles(String _s, String _t) {
        CustList<StringList> results_ = new CustList<StringList>();
        StringList s_ = StringUtil.splitChars(_s,LR,LF);
        StringList t_ = StringUtil.splitChars(_t,LR,LF);
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
                if (StringUtil.quickEq(_s.get(i), _t.get(j))) {
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
            if (!StringUtil.quickEq(_s.get(i_), _t.get(j_))) {
                break;
            }
            firstOne_.add(_s.get(i_));
            i_++;
            j_++;
        }
        return lastMatch(_s, _t, firstOne_, firstTwo_);
    }

    private static StringList lastMatch(StringList _s, StringList _t, StringList _firstOne, StringList _firstTwo) {
        boolean found_;
        int j_;
        int i_;
        i_ = 0;
        j_ = 0;
        int k_ = 0;
        found_=false;
        while(true) {
            for (int i = 0; i < _s.size(); i++) {
                if (StringUtil.quickEq(_s.get(i), _t.get(k_))) {
                    i_ = i;
                    j_ = k_;
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                break;
            }
            k_++;
        }
        return endSub(_s, _t, _firstOne, _firstTwo, i_, j_);
    }

    private static StringList endSub(StringList _s, StringList _t, StringList _firstOne, StringList _firstTwo, int _i, int _j) {
        int i_ = _i;
        int j_ = _j;
        while (i_ < _s.size() && j_ < _t.size()) {
            if (!StringUtil.quickEq(_s.get(i_), _t.get(j_))) {
                break;
            }
            _firstTwo.add(_s.get(i_));
            i_++;
            j_++;
        }
        int lastIndexOne_ = Math.max(lastIndexOf(_s, _firstOne), lastIndexOf(_t,_firstOne));
        int lastIndexTwo_ = Math.max(lastIndexOf(_s,_firstTwo), lastIndexOf(_t,_firstTwo));
        if (lastIndexOne_ > lastIndexTwo_) {
            return _firstTwo;
        }
        return _firstOne;
    }
    private static int indexOf(StringList _list, StringList _sub) {
        return _list.indexesOfString(_sub.first()).first();
    }
    private static int lastIndexOf(StringList _list, StringList _sub) {
        return _list.indexesOfString(_sub.first()).last();
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
        return lastCommon(_s, _t, firstOne_, firstTwo_);
    }

    private static String lastCommon(String _s, String _t, StringBuilder _firstOne, StringBuilder _firstTwo) {
        int j_;
        int i_;
        boolean found_;
        i_ = 0;
        j_ = 0;
        found_ = false;
        int k_ = 0;
        while(true) {
            for (int i = 0; i < _s.length(); i++) {
                if (_s.charAt(i) == _t.charAt(k_)) {
                    i_ = i;
                    j_ = k_;
                    found_ = true;
                    break;
                }
            }
            if (found_) {
                break;
            }
            k_++;
        }
        while (i_ < _s.length() && j_ < _t.length()) {
            if (_s.charAt(i_) != _t.charAt(j_)) {
                break;
            }
            _firstTwo.append(_s.charAt(i_));
            i_++;
            j_++;
        }
        int lastIndexOne_ = Math.max(_s.lastIndexOf(_firstOne.toString()), _t.lastIndexOf(_firstOne.toString()));
        int lastIndexTwo_ = Math.max(_s.lastIndexOf(_firstTwo.toString()), _t.lastIndexOf(_firstTwo.toString()));
        if (lastIndexOne_ > lastIndexTwo_) {
            return _firstTwo.toString();
        }
        return _firstOne.toString();
    }
}
