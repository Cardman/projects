package code.util.pagination;
import code.util.StringList;
import code.util.opers.CommonRegExpUtil;

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
        if (_searchMode == SearchingMode.REG_EXP) {
//            if (!RegExpUtil.matchRegExp(_string, _typedString)) {
//                return false;
//            }
            StringList matches_ = new StringList(_string);
            if (CommonRegExpUtil.filter(matches_, _typedString).isEmpty()) {
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

    protected static boolean match(Integer _min, Integer _max, Integer _number) {
        if (_min == null) {
            if (_max == null) {
                return true;
            }
            if (_max.compareTo(_number) < 0) {
                return false;
            }
            return true;
        }
        if (_max != null) {
            if (_max.compareTo(_number) < 0) {
                return false;
            }
        }
        if (_min.compareTo(_number) > 0) {
            return false;
        }
        return true;
    }

    protected static boolean match(Long _min, Long _max, Long _number) {
        if (_min == null) {
            if (_max == null) {
                return true;
            }
            if (_max.compareTo(_number) < 0) {
                return false;
            }
            return true;
        }
        if (_max != null) {
            if (_max.compareTo(_number) < 0) {
                return false;
            }
        }
        if (_min.compareTo(_number) > 0) {
            return false;
        }
        return true;
    }

    protected static boolean match(Short _min, Short _max, Short _number) {
        if (_min == null) {
            if (_max == null) {
                return true;
            }
            if (_max.compareTo(_number) < 0) {
                return false;
            }
            return true;
        }
        if (_max != null) {
            if (_max.compareTo(_number) < 0) {
                return false;
            }
        }
        if (_min.compareTo(_number) > 0) {
            return false;
        }
        return true;
    }

    protected static boolean match(Enum<?> _enum, Enum<?> _element) {
        if (_enum == null) {
            return true;
        }
        return _enum == _element;
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
                if (_typedString == null) {
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
        if (_searchMode == SearchingMode.REG_EXP) {
            if (CommonRegExpUtil.filter(_list,_typedString).isEmpty()) {
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
