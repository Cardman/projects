package aiki.db;

public final class PkVariableSuffixCoeffEffBaseTypesCombattantEntrant extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixCoeffEffBaseTypesCombattantEntrant(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().coeffEffBaseTypesCombattantEntrant();
    }

    @Override
    public String value(String _v) {
        return getData().prefixCoeffEffBaseTypesCombattantEntrant(_v);
    }
}
