package aiki.db;

public abstract class AbsVariableSuffixImpl {
    private final DataBase data;

    protected AbsVariableSuffixImpl(DataBase _d) {
        this.data = _d;
    }

    public DataBase getData() {
        return data;
    }
}
