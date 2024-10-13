package aiki.db;

public final class PkVariableSuffixLanceurEffet extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixLanceurEffet(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().lanceurEffet();
    }

    @Override
    public String value(String _v) {
        return getData().prefixLanceurEffet(_v);
    }
}
