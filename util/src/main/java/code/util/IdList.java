package code.util;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class IdList<T> extends AbEqList<T> implements Equallable<IdList<T>> {

    public IdList() {
    }

    public IdList(T... _elements) {
        super(_elements);
    }

    public IdList(Listable<T> _c) {
        super(_c);
    }

    
    public IdList(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public IdList<T> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public IdList<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new IdList<T>();
        }
        return new IdList<T>(super.sub(_from, _to));
    }

    @Override
    public boolean eq(IdList<T> _g) {
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _g.get(i);
            if (e_ != f_) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOfObj(T _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }


}
