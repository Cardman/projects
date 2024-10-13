package aiki.db;

public final class PkVariableSuffixLanceurPp extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixLanceurPp(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().lanceurPp();
    }

    @Override
    public String value(String _v) {
        return getData().prefixLanceurPp(_v);
    }
}
