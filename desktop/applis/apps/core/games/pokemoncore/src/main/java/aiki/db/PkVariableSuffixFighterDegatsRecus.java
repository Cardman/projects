package aiki.db;

public final class PkVariableSuffixFighterDegatsRecus extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixFighterDegatsRecus(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixFighterDegatsRecus(_v);
    }
}
