package code.util;

import code.util.ints.Listable;

public final class Ints extends Numbers<Integer> {

    public Ints() {
    }

    public Ints(Listable<Integer> _list) {
        super(_list);
    }

    public Ints(Integer... _array) {
        super(_array);
    }


    public Ints(CollCapacity _capacity) {
        super(_capacity);
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
            Ints nbs_ = new Ints(i);
            e_.add(nbs_);
        }
        for (int i = 1; i < sdims_; i++) {
            CustList<Ints> newIndexes_;
            newIndexes_ = new CustList<Ints>();
            int d_ = get(i);
            for (Ints p: e_) {
                for (int j = 0; j < d_; j++) {
                    Ints n_;
                    n_ = new Ints(p);
                    n_.add(j);
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
