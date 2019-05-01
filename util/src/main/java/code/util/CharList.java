package code.util;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class CharList extends CustList<Character> implements Equallable<CharList> {

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
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public boolean eq(CharList _g) {
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            Character e_ = get(i);
            Character f_ = _g.get(i);
            if (e_.charValue() != f_.charValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean containsObj(char _k) {
        return indexOfObj(_k, FIRST_INDEX) > -1;
    }
}
