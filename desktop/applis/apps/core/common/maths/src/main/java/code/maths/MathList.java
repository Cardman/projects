package code.maths;
import code.util.CustList;
import code.util.comparators.NaturalComparator;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
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
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < size()) {
            String v_ = get(i_);
            if (StringUtil.quickEq(v_, _obj)) {
                remove(i_);
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
      return indexOfObj(_obj) != IndexConstants.INDEX_NOT_FOUND_ELT;
  }
    public void removeDuplicates() {
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < size()) {
            String e_ = get(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
                remove(next_);
                rem_ = true;
                next_ = indexOfObj(e_, i_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }
    public int indexOfObj(String _obj) {
        return indexOfObj(_obj, IndexConstants.FIRST_INDEX);
    }
    public int indexOfObj(String _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            String e_ = get(i);
            if (StringUtil.quickEq(_element, e_)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public boolean eq(MathList _g) {
        return StringUtil.equalsSet(this,_g);
    }
}
