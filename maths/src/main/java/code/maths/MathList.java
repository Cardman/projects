package code.maths;
import code.util.CustList;
import code.util.StringList;
import code.util.comparators.NaturalComparator;
import code.util.ints.Listable;

public final class MathList extends CustList<String> {

    public MathList() {
    }

    public MathList(Listable<String> _list) {
        super(_list);
    }

    public void removeAllElements(MathList _c) {
        for (String s: _c) {
            if (containsObj(s)) {
                removeAllObj(s);
            }
        }
    }
    private void removeAllObj(String _obj) {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            String v_ = get(i_);
            if (StringList.quickEq(v_, _obj)) {
                removeAt(i_);
            } else {
                i_++;
            }
        }
    }
    public void sort() {
        sortElts(new NaturalComparator());
    }
    public MathList intersectStr(MathList _list) {
        MathList list_ = new MathList();
        for (String s: _list) {
            if (containsObj(s)) {
                list_.add(s);
            }
        }
        return list_;
    }
    public boolean containsAllObj(MathList _list) {
        for (String e: _list) {
            if (!containsObj(e)) {
                return false;
            }
        }
        return true;
    }
    public boolean containsObj(String _obj) {
      return indexOfObj(_obj) != INDEX_NOT_FOUND_ELT;
  }
    public void removeDuplicates() {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            String e_ = get(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != INDEX_NOT_FOUND_ELT) {
                removeAt(next_);
                rem_ = true;
                next_ = indexOfObj(e_, next_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }
    public int indexOfObj(String _obj) {
        return indexOfObj(_obj,FIRST_INDEX);
    }
    public int indexOfObj(String _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            String e_ = get(i);
            if (StringList.quickEq(_element, e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    public boolean eq(MathList _g) {
        StringList cone_ = new StringList(this);
        StringList ctwo_ = new StringList(_g);
        cone_.sort();
        ctwo_.sort();
        cone_.removeDuplicates();
        ctwo_.removeDuplicates();
        return cone_.eq(ctwo_);
    }
}
