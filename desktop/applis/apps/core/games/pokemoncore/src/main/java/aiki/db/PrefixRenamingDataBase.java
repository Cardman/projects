package aiki.db;

public final class PrefixRenamingDataBase implements AbsRenamingDataBase {
    private final String oldName;
    private final String newName;

    public PrefixRenamingDataBase(String _o, String _n) {
        this.oldName = _o;
        this.newName = _n;
    }

    @Override
    public String rename(DataBase _db, String _exp) {
        return _db.renamePref(_exp, oldName,newName);
    }
}
