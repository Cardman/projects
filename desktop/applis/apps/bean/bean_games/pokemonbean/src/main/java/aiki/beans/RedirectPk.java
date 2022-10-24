package aiki.beans;

import aiki.db.DataBase;
import code.util.CustList;

public final class RedirectPk extends AbsRedirectAdv {
    public RedirectPk(String _n) {
        super(_n);
    }

    @Override
    public CustList<String> keys(DataBase _data) {
        return _data.getPokedex().getKeys();
    }

}
