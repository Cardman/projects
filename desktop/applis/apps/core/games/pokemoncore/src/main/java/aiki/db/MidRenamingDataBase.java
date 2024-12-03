package aiki.db;

public final class MidRenamingDataBase implements AbsRenamingDataBase {
    private final String oldName;
    private final String newName;

    public MidRenamingDataBase(String _o, String _n) {
        this.oldName = _o;
        this.newName = _n;
    }

    @Override
    public String rename(DataBase _db, String _exp) {
        return _db.renameMid(_exp,oldName,newName);
    }
}
