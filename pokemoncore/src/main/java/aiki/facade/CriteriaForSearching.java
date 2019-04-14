package aiki.facade;
import code.util.Numbers;
import code.util.StringList;
import code.util.pagination.SearchingMode;
import code.util.pagination.SelectedBoolean;

public abstract class CriteriaForSearching {

    protected static boolean match(SearchingMode _searchMode, String _typedString, String _string) {
        if (_typedString == null) {
            return true;
        }
        if (_string == null) {
            return false;
        }
        if (_searchMode == SearchingMode.WHOLE_STRING) {
            if (!StringList.quickEq(_string,_typedString)) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.SUBSTRING) {
            if (!_string.contains(_typedString)) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.MATCH_SPACE) {
            if (!StringList.matchSpace(_string, _typedString)) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.BEGIN) {
            if (!StringList.startsWith(_string, _typedString)) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.END) {
            if (!StringList.endsWith(_string, _typedString)) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.META_CHARACTER) {
            if (!StringList.match(_string, _typedString)) {
                return false;
            }
        }
        return true;
    }

    protected static boolean match(Number _min, Number _max, Number _number) {
        if (_min == null) {
            if (_max == null) {
                return true;
            }
            if (Numbers.compare(_max,_number) < 0) {
                return false;
            }
            return true;
        }
        if (_max != null) {
            if (Numbers.compare(_max,_number) < 0) {
                return false;
            }
        }
        if (Numbers.compare(_min,_number) > 0) {
            return false;
        }
        return true;
    }

    protected static boolean match(SelectedBoolean _selectedBoolean, boolean _boolean) {
        if (_selectedBoolean == SelectedBoolean.YES_AND_NO) {
            return true;
        }
        if (_selectedBoolean.isSelected() && _boolean) {
            return true;
        }
        if (!_selectedBoolean.isSelected() && !_boolean) {
            return true;
        }
        return false;
    }

    protected static boolean match(SearchingMode _searchMode, String _typedString, StringList _list) {
        if (_typedString == null || _typedString.isEmpty()) {
            return true;
        }
        if (_searchMode == SearchingMode.WHOLE_STRING) {
            boolean contained_ = false;
            for (String s: _list) {
                if (s == null) {
                    continue;
                }
                if (StringList.quickEq(s,_typedString)) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.SUBSTRING) {
            boolean contained_ = false;
            for (String s: _list) {
                if (s == null) {
                    continue;
                }
                if (s.contains(_typedString)) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.BEGIN) {
            boolean contained_ = false;
            for (String s: _list) {
                if (s == null) {
                    continue;
                }
                if (StringList.startsWith(s, _typedString)) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.END) {
            boolean contained_ = false;
            for (String s: _list) {
                if (s == null) {
                    continue;
                }
                if (StringList.endsWith(s, _typedString)) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.MATCH_SPACE) {
            boolean contained_ = false;
            for (String s: _list) {
                if (s == null) {
                    continue;
                }
                if (StringList.matchSpace(s, _typedString)) {
                    contained_ = true;
                    break;
                }
            }
            if (!contained_) {
                return false;
            }
        }
        if (_searchMode == SearchingMode.META_CHARACTER) {
            if (_list.filterByMultiWords(_typedString).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
