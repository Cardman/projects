package aiki.db;

public final class PkVariableSuffixLanceurDegatsRecus extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixLanceurDegatsRecus(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixLanceurDegatsRecus(_v);
    }
}
