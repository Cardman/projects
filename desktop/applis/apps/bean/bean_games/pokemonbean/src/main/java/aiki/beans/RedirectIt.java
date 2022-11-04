package aiki.beans;

import aiki.beans.items.ItemsBean;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import code.util.CustList;

public final class RedirectIt extends AbsRedirectAdv {
    private final Item item;
    public RedirectIt(String _n, String _def, Item _db) {
        super(_n,_def);
        item = _db;
    }

    @Override
    public CustList<String> keys(DataBase _data) {
        return _data.getItems().getKeys();
    }

    @Override
    public String manage(String _key, DataBase _data, StringMapObject _forms, String _target) {
        super.manage(_key, _data, _forms, _target);
        return ItemsBean.switchItemPage(item);
    }
}
