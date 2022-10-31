package aiki.beans;

import aiki.db.DataBase;

public abstract class AbsRedirectAdv extends AbsRedirect {

    protected AbsRedirectAdv(String _n, String _def) {
        super(_n,_def);
    }

    @Override
    public String manage(String _key, DataBase _data, StringMapObject _forms, String _target) {
        _forms.put(_key,getName());
        return _target;
    }

}
