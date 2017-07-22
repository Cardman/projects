package code.util;
import code.util.annot.CapacityInit;
import code.util.annot.RwXml;
import code.util.ints.Equallable;
import code.util.ints.Listable;
import code.util.ints.Settable;

@RwXml
public class EqSet<E extends Equallable<E>> extends AbEqList<E> implements Settable<E> {

//    private final List<E> elements;

    public EqSet() {
    }

    public EqSet(Listable<? extends E> _c) {
        super(_c);
    }

    @CapacityInit
    private EqSet(int _capacity) {
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
    public EqSet<E> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public EqSet<E> sub(int _from, int _to) {
        if (_from > _to) {
            return new EqSet<E>();
        }
        return new EqSet<E>(super.sub(_from, _to));
    }
    @Override
    public int indexOfObj(E _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            E e_ = get(i);
            if (e_ == null) {
                continue;
            }
            if (_element.eq(e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }
//    @Override
//    public Numbers<Integer> indexesOfObj(E _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            E e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (_element.eq(e_)) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
//    @Override
//    public int indexOfObj(E _element) {
//        if (_element == null) {
//            int i_ = FIRST_INDEX;
//            for (E e:this) {
//                if (e == null) {
//                    return i_;
//                }
//                i_++;
//            }
//            return INDEX_NOT_FOUND_ELT;
//        }
//        int i_ = FIRST_INDEX;
//        for (E e:this) {
//            if (e == null) {
//                i_++;
//                continue;
//            }
//            if (_element.eq(e)) {
//                return i_;
//            }
//            i_++;
//        }
//        return INDEX_NOT_FOUND_ELT;
//    }
//    @Override
//    public boolean remove(Object _o) {
//        return elements.remove(_o);
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> _c) {
//        return elements.containsAll(_c);
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends E> _c) {
//        boolean added_ = false;
//        for (E e: _c) {
//            boolean locAdd_ = add(e);
//            if (locAdd_) {
//                added_ = true;
//            }
//        }
//        return added_;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> _c) {
//        return elements.retainAll(_c);
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> _c) {
//        return elements.removeAll(_c);
//    }
//
//    @Override
//    public void clear() {
//        elements.clear();
//    }

}
