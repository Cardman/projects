package aiki.db;

public final class PkVariableSuffixCoeffEffBaseTypesFighter extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixCoeffEffBaseTypesFighter(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().coeffEffBaseTypesFighter();
    }

    @Override
    public String value(String _v) {
        return getData().prefixCoeffEffBaseTypesFighter(_v);
    }
}
