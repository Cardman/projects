package aiki.beans;

import aiki.db.DataBase;
import code.util.CustList;

public final class RedirectSt extends AbsRedirectAdv {
    public RedirectSt(String _n) {
        super(_n);
    }

    @Override
    public CustList<String> keys(DataBase _data) {
        return _data.getStatus().getKeys();
    }

}
