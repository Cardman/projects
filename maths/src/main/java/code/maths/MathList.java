package code.maths;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.ints.Displayable;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class MathList extends CustList<String> implements Equallable<MathList>, Displayable {

    private static final String EMPTY_STRING = "";
    private static final String SEP =  ";";
    private static final String ESCAPE =  "\\";

    public MathList() {
    }

    public MathList(Listable<String> _list) {
        super(_list);
    }

    public MathList(String... _strings) {
        super(_strings);
    }

    public static boolean eq(MathList _one, MathList _two) {
        StringList cone_ = new StringList(_one);
        StringList ctwo_ = new StringList(_two);
        cone_.sort();
        ctwo_.sort();
        cone_.removeDuplicates();
        ctwo_.removeDuplicates();
        return cone_.eq(ctwo_);
    }
    public void removeAllElements(MathList _c) {
        for (String s: _c) {
            if (containsObj(s)) {
                removeAllObj(s);
            }
        }
    }
    public void removeAllObj(String _obj) {
        while (containsObj(_obj)) {
            removeObj(_obj);
        }
    }
    public void removeObj(String _obj) {
        int index_ = indexOfObj(_obj);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        removeAt(index_);
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
        while (true) {
            if(i_ >= size()) {
                break;
            }
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

    public String escapedList() {
        StringBuilder str_ = new StringBuilder(String.valueOf(StringList.LEFT_BRACE));
        String end_ = String.valueOf(StringList.RIGHT_BRACE);
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put(SEP, StringList.concat(ESCAPE,SEP));
        rep_.put(ESCAPE, StringList.concat(ESCAPE,ESCAPE));
        rep_.put(end_, StringList.concat(ESCAPE,end_));
        for (String s: this) {
            str_.append(StringList.replaceMultiple(s, rep_));
            str_.append(SEP);
        }
        if (!isEmpty()) {
            str_.deleteCharAt(str_.length() - 1);
        }
        str_.append(end_);
        return str_.toString();
    }

    public String join(String _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(get(FIRST_INDEX));
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }

    public String join(char _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(get(FIRST_INDEX));
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }

    @Override
    public String display() {
        return StringList.concat(String.valueOf(StringList.LEFT_BRACE),join(SEP),String.valueOf(StringList.RIGHT_BRACE));
    }

    @Override
    public boolean eq(MathList _g) {
        StringList cone_ = new StringList(this);
        StringList ctwo_ = new StringList(_g);
        cone_.sort();
        ctwo_.sort();
        cone_.removeDuplicates();
        ctwo_.removeDuplicates();
        return cone_.eq(ctwo_);
    }

    public MathList subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public MathList sub(int _from, int _to) {
        if (_from > _to) {
            return new MathList();
        }
        return new MathList(super.sub(_from, _to));
    }
}
