package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class CharList extends AbEqList<Character> implements Equallable<CharList> {

    public CharList() {
    }

    public CharList(Character... _elements) {
        super(_elements);
    }
    
    public CharList(Listable<? extends Character> _c) {
        super(_c);
    }

    @CapacityInit
    private CharList(int _capacity) {
        super(_capacity);
    }

//    public CharList(Iterable<? extends Character> _c) {
//        super(_c);
//    }

    public boolean containsChar(char _char) {
        return containsObj(_char);
    }

    @Override
    public int indexOfObj(Character _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            Character e_ = get(i);
            if (e_ == null) {
                continue;
            }
            if (e_.charValue() == _element.charValue()) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public Numbers<Integer> indexesOfObj(Character _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            Character e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (e_.charValue() == _element.charValue()) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }

    @Override
    public boolean eq(CharList _g) {
        if (_g == null) {
            return false;
        }
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            Character e_ = get(i);
            Character f_ = _g.get(i);
            if (e_ == null) {
                if (f_ != null) {
                    return false;
                }
                continue;
            }
            if (e_.charValue() != f_.charValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CharList subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public CharList sub(int _from, int _to) {
        if (_from > _to) {
            return new CharList();
        }
        return new CharList(super.sub(_from, _to));
    }
}
