package cards.consts;

import code.util.CustList;
import code.util.core.IndexConstants;

public final class LeadingCards<T> {
    private final CustList<T> list = new CustList<T>();

    public void leading(CustList<T> _union, CustList<T> _current, CustList<T> _played, CustList<T> _full) {
        int nbPlayedOrOwnedCards_ = Math.min(_union.size(),_full.size());
        for (byte c = IndexConstants.FIRST_INDEX; c < nbPlayedOrOwnedCards_; c++) {
            if (_union.get(c)!=
                    _full.get(c)) {
                break;
            }
            if (contient(_current,_union.get(c))) {
                list.add(_union.get(c));
            }
        }
        int nbLeadingCards_ = list.size();
        if (nbLeadingCards_ + _current.size() + _played.size() >= _full
                .size()) {
            for (T carte_ : _current) {
                if (!contient(list,carte_)) {
                    list.add(carte_);
                }
            }
        }
    }
    private boolean contient(CustList<T> _ls, T _card) {
        for (T t: _ls) {
            if (t == _card) {
                return true;
            }
        }
        return false;
    }

    public CustList<T> getList() {
        return list;
    }
}
