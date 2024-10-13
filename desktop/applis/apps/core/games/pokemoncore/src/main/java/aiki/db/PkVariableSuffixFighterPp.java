package aiki.db;

public final class PkVariableSuffixFighterPp extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixFighterPp(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().fighterPp();
    }

    @Override
    public String value(String _v) {
        return getData().prefixFighterPp(_v);
    }
}
