package aiki.db;

public final class PkVariableSuffixLanceurDegatsRecusTour extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixLanceurDegatsRecusTour(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixLanceurDegatsRecusTour(_v);
    }
}
