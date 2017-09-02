package code.maths;
import code.util.AbEqList;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class MathList extends AbEqList<String> implements Equallable<MathList> {

    static final char SEPARATOR_SET_CHAR = ';';

    private static final String EMPTY_STRING = "";
    private static final char ESCAPING_CHAR = '\\';
    private static final String SEP =  EMPTY_STRING+SEPARATOR_SET_CHAR;
    private static final String ESCAPE =  EMPTY_STRING+ESCAPING_CHAR;

    private static final String LEFT_BRACE_SET=EMPTY_STRING+StringList.LEFT_BRACE;

    private static final String RIGHT_BRACE_SET= EMPTY_STRING+StringList.RIGHT_BRACE;

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
                //_list.containsObj(s)
                removeAllObj(s);
            }
        }
    }
    public void sort() {
        //setModified();
        sortElts(new NaturalComparator<String>());
    }
//    public void removeDuplicates() {
//        //setModified();
//        int i_ = FIRST_INDEX;
//        while (true) {
//            if(i_ >= size()) {
//                break;
//            }
//            int j_ = i_ + 1;
//            while (true) {
//                if (j_ >= size()) {
//                    break;
//                }
//                if (get(i_) == null) {
//                    if (get(j_) == null) {
//                        removeAt(j_);
//                    } else {
//                        j_++;
//                    }
//                    continue;
//                }
//                if (StringList.eq(get(i_),get(j_))) {
//                    removeAt(j_);
//                } else {
//                    j_++;
//                }
//            }
//            i_++;
//        }
//    }
    public MathList intersectStr(MathList _list) {
        MathList list_ = new MathList();
        for (String s: _list) {
            if (containsObj(s)) {
                //_list.containsObj(s)
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
    @Override
    public int indexOfObj(String _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            String e_ = get(i);
            if (e_ == null) {
                continue;
            }
            if (StringList.quickEq(_element, e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
//    @Override
//    public Numbers<Integer> indexesOfObj(String _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            String e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (StringList.eq(_element, e_)) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
//    @Override
//    public int indexOfObj(String _element) {
//        if (_element == null) {
//            int i_ = FIRST_INDEX;
//            for (String e:this) {
//                if (e == null) {
//                    return i_;
//                }
//                i_++;
//            }
//            return INDEX_NOT_FOUND_ELT;
//        }
//        int i_ = FIRST_INDEX;
//        for (String e:this) {
//            if (e == null) {
//                i_++;
//                continue;
//            }
//            if (StringList.eq(_element, e)) {
//                return i_;
//            }
//            i_++;
//        }
//        return INDEX_NOT_FOUND_ELT;
//    }
    public String escapedList() {
        StringBuilder str_ = new StringBuilder(LEFT_BRACE_SET);
        StringMap<String> rep_ = new StringMap<String>();
        rep_.put(SEP, ESCAPE+SEP);
        rep_.put(ESCAPE, ESCAPE+ESCAPE);
        rep_.put(RIGHT_BRACE_SET, ESCAPE+RIGHT_BRACE_SET);
        for (String s: this) {
            str_.append(StringList.replaceMultiple(s, rep_));
            str_.append(SEP);
        }
        if (!isEmpty()) {
            str_.deleteCharAt(str_.length() - 1);
        }
        str_.append(RIGHT_BRACE_SET);
        return str_.toString();
    }
    @Override
    public String toString() {
        return LEFT_BRACE_SET+join(SEPARATOR_SET_CHAR)+RIGHT_BRACE_SET;
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

    @Override
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
