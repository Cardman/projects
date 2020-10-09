package code.util;

import code.util.core.IndexConstants;

public final class CharList extends CustList<Character> {

    public CharList() {
    }

    public CharList(char... _elements) {
        super(new CollCapacity(_elements.length));
        for (char c : _elements) {
            add(c);
        }
    }

    public static char[] wrapCharArray(char... _chars) {
        return _chars;
    }

    public boolean containsChar(char _char) {
        return containsObj(_char);
    }

    public int indexOfObj(char _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            char e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public boolean containsObj(char _k) {
        return indexOfObj(_k, IndexConstants.FIRST_INDEX) > -1;
    }
}
