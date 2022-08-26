package code.util;

import code.util.ints.Listable;

public final class Ints extends Numbers<Integer> {

    public Ints() {
    }

    public Ints(Listable<Integer> _list) {
        super(_list);
    }


    public Ints(CollCapacity _capacity) {
        super(_capacity);
    }

    public static Ints newList(int... _values) {
        Ints sh_ = new Ints(new CollCapacity(_values.length));
        for (int b: _values) {
            sh_.add(b);
        }
        return sh_;
    }

    public int[] toArrInt() {
        int size_ = size();
        int[] sh_ = new int[size_];
        for (int i = 0; i < size_; i++) {
            sh_[i] = (int) getLong(i);
        }
        return sh_;
    }

    public static Ints singleOrEmpty(int _element) {
        if (_element < 0) {
            return new Ints();
        }
        return Ints.newList(_element);
    }

    public CustList<Ints> getAllIndexes() {
        CustList<Ints> e_;
        e_ = new CustList<Ints>();
        if (isEmpty()) {
            return e_;
        }
        int f_ = first();
        int sdims_ = size();
        for (int i = 0; i < f_; i++) {
            Ints nbs_ = Ints.newList(i);
            e_.add(nbs_);
        }
        for (int d = 1; d < sdims_; d++) {
            CustList<Ints> newIndexes_;
            newIndexes_ = new CustList<Ints>();
            int d_ = get(d);
            for (Ints p: e_) {
                for (int i = 0; i < d_; i++) {
                    Ints n_;
                    n_ = new Ints(p);
                    n_.add(i);
                    newIndexes_.add(n_);
                }
            }
            e_ = newIndexes_;
        }
        return e_;
    }
    @Override
    long getLong(int _index) {
        return get(_index);
    }
}
