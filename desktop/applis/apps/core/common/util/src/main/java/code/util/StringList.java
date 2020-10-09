package code.util;
import code.util.comparators.NaturalComparator;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.Displayable;
import code.util.ints.Listable;

public final class StringList extends AbEqList<String> implements Displayable {

    public StringList() {
    }

    public StringList(Listable<String> _list) {
        super(_list);
    }

    public StringList(String... _strings) {
        super(_strings);
    }

    
    public StringList(CollCapacity _capacity) {
        super(_capacity);
    }

    public void sort() {
        sortElts(new NaturalComparator());
    }

    public void replace(String _old, String _new) {
        int size_ = size();
        for (int i = IndexConstants.FIRST_INDEX; i < size_; i++) {
            if (StringUtil.quickEq(_old, get(i))) {
                set(i, _new);
            }
        }
    }

    public void replaceInStrings(String _replaced, String _replacing) {
        //setModified();
        int size_ = size();
        for (int i = IndexConstants.FIRST_INDEX; i<size_; i++) {
            set(i, StringUtil.replace(get(i), _replaced, _replacing));
        }
    }

    public void replaceBackSlashesInStrings() {
        //setModified();
        int size_ = size();
        for (int i = IndexConstants.FIRST_INDEX; i<size_; i++) {
            set(i, StringUtil.replaceBackSlash(get(i)));
        }
    }

    public Ints indexesOfString(String _string) {
        Ints list_ = new Ints();
        int size_ = size();
        for (int i = IndexConstants.FIRST_INDEX; i<size_; i++) {
            if (StringUtil.quickEq(get(i),_string)) {
                list_.add(i);
            }
        }
        return list_;
    }

    public StringList filterByMultiWords(String _exp) {
        StringList list_ = new StringList();
        for (String s: this) {
            if (s == null) {
                continue;
            }
            if (!StringUtil.match(s, _exp)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public StringList filterBeginsWith(String _regExp) {
        StringList list_ = new StringList();
        for (String s: this) {
            if (!s.startsWith(_regExp)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public StringList filterEndsWith(String _regExp) {
        StringList list_ = new StringList();
        for (String s: this) {
            if (!s.endsWith(_regExp)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    public boolean containsAllObj(CustList<String> _list) {
        for (String e: _list) {
            if (!StringUtil.contains(this, e)) {
                return false;
            }
        }
        return true;
    }

    public void removeAllString(String _obj) {
        StringUtil.removeAllObj(this,_obj);
    }

    public void removeString(String _string) {
        StringUtil.removeObj(this, _string);
    }

    @Override
    public boolean match(String _one, String _two) {
        return StringUtil.quickEq(_one,_two);
    }

    public boolean onlyOneElt() {
        if (isEmpty()) {
            return false;
        }
        String e_ = first();
        int s_ = size();
        for (int i = IndexConstants.SECOND_INDEX; i < s_; i++) {
            if (!StringUtil.quickEq(get(i),e_)) {
                return false;
            }
        }
        return true;
    }

    public boolean disjoint(CustList<String> _list) {
        for (String s: _list) {
            if (StringUtil.contains(this,s)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append("[");
        str_.append(StringUtil.join(this, ","));
        str_.append("]");
        return str_.toString();
    }
}
