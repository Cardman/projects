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

    public EqSet(Listable<E> _c) {
        super(_c);
    }

    @CapacityInit
    public EqSet(CollCapacity _capacity) {
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

}
