package aiki.beans;

import aiki.db.DataBase;

public abstract class AbsRedirectAdv extends AbsRedirect {

    protected AbsRedirectAdv(String _n) {
        super(_n);
    }

    public void manage(String _key,DataBase _data,StringMapObject _forms) {
        _forms.put(_key,getName());
    }

}
