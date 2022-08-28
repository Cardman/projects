package aiki.facade;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class CriteriaForSearching {

    protected CriteriaForSearching() {
    }
    protected static boolean match(SearchingMode _searchMode, String _typedString, String _string) {
        if (_typedString == null) {
            return true;
        }
        if (_string == null) {
            return false;
        }
        if (_searchMode == SearchingMode.WHOLE_STRING && !StringUtil.quickEq(_string, _typedString)) {
            return false;
        }
        if (_searchMode == SearchingMode.SUBSTRING && !_string.contains(_typedString)) {
            return false;
        }
        if (_searchMode == SearchingMode.MATCH_SPACE && !StringUtil.matchSpace(_string, _typedString)) {
            return false;
        }
        if (_searchMode == SearchingMode.BEGIN && !StringUtil.startsWith(_string, _typedString)) {
            return false;
        }
        if (_searchMode == SearchingMode.END && !StringUtil.endsWith(_string, _typedString)) {
            return false;
        }
        if (_searchMode == SearchingMode.META_CHARACTER) {
            return StringUtil.match(_string, _typedString);
        }
        return true;
    }

    protected static boolean match(Long _min, Long _max, long _number) {
        if (_min == null) {
            if (_max == null) {
                return true;
            }
            return NumberUtil.compareLg(_max, _number) >= 0;
        }
        if (_max != null && NumberUtil.compareLg(_max, _number) < 0) {
            return false;
        }
        return NumberUtil.compareLg(_min, _number) <= 0;
    }

    public static boolean match(SelectedBoolean _selectedBoolean, boolean _boolean) {
        if (_selectedBoolean == SelectedBoolean.YES_AND_NO) {
            return true;
        }
        if (_selectedBoolean == SelectedBoolean.YES && _boolean) {
            return true;
        }
        return _selectedBoolean == SelectedBoolean.NO && !_boolean;
    }

    protected static boolean match(SearchingMode _searchMode, String _typedString, StringList _list) {
        if (_typedString == null || _typedString.isEmpty()) {
            return true;
        }
        if (_searchMode == SearchingMode.WHOLE_STRING && !wholeString(_typedString, _list)) {
            return false;
        }
        if (_searchMode == SearchingMode.SUBSTRING && !substring(_typedString, _list)) {
            return false;
        }
        if (_searchMode == SearchingMode.BEGIN && !begin(_typedString, _list)) {
            return false;
        }
        if (_searchMode == SearchingMode.END && !end(_typedString, _list)) {
            return false;
        }
        if (_searchMode == SearchingMode.MATCH_SPACE && !space(_typedString, _list)) {
            return false;
        }
        return _searchMode != SearchingMode.META_CHARACTER || !_list.filterByMultiWords(_typedString).isEmpty();
    }

    private static boolean space(String _typedString, StringList _list) {
        boolean contained_ = false;
        for (String s: _list) {
            if (s != null && StringUtil.matchSpace(s, _typedString)) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

    private static boolean end(String _typedString, StringList _list) {
        boolean contained_ = false;
        for (String s: _list) {
            if (s != null && StringUtil.endsWith(s, _typedString)) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

    private static boolean begin(String _typedString, StringList _list) {
        boolean contained_ = false;
        for (String s: _list) {
            if (s != null && StringUtil.startsWith(s, _typedString)) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

    private static boolean substring(String _typedString, StringList _list) {
        boolean contained_ = false;
        for (String s: _list) {
            if (s != null && s.contains(_typedString)) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }

    private static boolean wholeString(String _typedString, StringList _list) {
        boolean contained_ = false;
        for (String s: _list) {
            if (s != null && StringUtil.quickEq(s, _typedString)) {
                contained_ = true;
                break;
            }
        }
        return contained_;
    }
}
