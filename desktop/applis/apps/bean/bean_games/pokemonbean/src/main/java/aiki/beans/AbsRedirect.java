package aiki.beans;

import aiki.db.DataBase;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbsRedirect {
    private final String name;
    private final String def;

    protected AbsRedirect(String _n, String _d) {
        this.name = _n;
        this.def = _d;
    }

    public static String tryRedirect(CommonBean _common, AbsRedirect _red, String _key, String _target) {
        return tryRedirect(_red, _key, _target, _common.getDataBase(), _common.getForms());
    }

    public static String tryRedirect(AbsRedirect _red, String _key, String _target, DataBase _data, StringMapObject _forms) {
        if (StringUtil.contains(_red.keys(_data), _red.getName())) {
            return _red.manage(_key, _data, _forms,_target);
        }
        return _red.def;
    }

    public abstract CustList<String> keys(DataBase _data);
    public abstract String manage(String _key, DataBase _data, StringMapObject _forms, String _target);

    public String getName() {
        return name;
    }
}
