package aiki.db;

public final class PrefixRenamingDataBase implements AbsRenamingDataBase {
    private final String newName;

    public PrefixRenamingDataBase(String _n) {
        this.newName = _n;
    }

    @Override
    public String rename(DataBase _db, String _exp) {
        return _db.renamePref(_exp,newName);
    }
}
