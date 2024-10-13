package aiki.db;

public final class PkVariableSuffixCoeffEffBaseTypesCible extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixCoeffEffBaseTypesCible(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().coeffEffBaseTypesCible();
    }

    @Override
    public String value(String _v) {
        return getData().prefixCoeffEffBaseTypesCible(_v);
    }
}
