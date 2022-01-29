package code.expressionlanguage.analyze.types;

import code.util.IdList;

public final class IdTypeList<T> {
    private final IdList<T> list = new IdList<T>();
    public void add(T _elt) {
        list.add(_elt);
    }
    public void add(int _i,T _elt) {
        list.add(_i,_elt);
    }

    public IdList<T> ls() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public T first() {
        return list.first();
    }

    public boolean onlyOneElt() {
        if (isEmpty()) {
            return false;
        }
        T geneType_ = first();
        int size_ = list.size();
        for (int i = 1; i < size_; i++) {
            if (list.get(i) != geneType_) {
                return false;
            }
        }
        return true;
    }
}
