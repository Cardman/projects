package aiki.db;

import code.util.*;

public final class IdRenamingDataBase implements AbsRenamingDataBase {
    private final StringList mids;
    private final String oldName;
    private final String newName;

    public IdRenamingDataBase(StringList _m, String _o, String _n) {
        this.mids = _m;
        this.oldName = _o;
        this.newName = _n;
    }

    @Override
    public String rename(DataBase _db, String _exp) {
        return _db.rename(_exp,mids,oldName,newName);
    }
}
