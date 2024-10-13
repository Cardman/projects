package aiki.db;

public final class PkVariableSuffixLanceurNbUtilisation extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixLanceurNbUtilisation(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixLanceurNbUtilisation(_v);
    }
}
