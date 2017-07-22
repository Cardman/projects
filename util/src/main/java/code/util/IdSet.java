package code.util;
import code.util.annot.CapacityInit;
import code.util.annot.RwXml;
import code.util.ints.Listable;
import code.util.ints.Settable;

@RwXml
public final class IdSet<E> extends AbEqList<E> implements Settable<E> {

//    private final IdList<E> elements = new IdList<E>();

    public IdSet() {
    }

    public IdSet(Listable<? extends E> _c) {
        super(_c);
    }

    @CapacityInit
    private IdSet(int _capacity) {
        super(_capacity);
    }

    @Override
    public boolean addEl(E _e) {
        if (containsObj(_e)) {
            return false;
        }
        super.add(_e);
        return true;
    }

    @Override
    public IdSet<E> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public IdSet<E> sub(int _from, int _to) {
        if (_from > _to) {
            return new IdSet<E>();
        }
        return new IdSet<E>(super.sub(_from, _to));
    }
    @Override
    public int indexOfObj(E _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            E e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public Numbers<Integer> indexesOfObj(E _element) {
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            E e_ = get(i);
//            if (_element == e_) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
//
//    @Override
//    public int indexOfObj(E _element) {
//        int len_ = size();
//        for (int i = FIRST_INDEX; i < len_; i++) {
//            if (get(i) != _element) {
//                continue;
//            }
//            return i;
//        }
//        return INDEX_NOT_FOUND_ELT;
//    }

}
