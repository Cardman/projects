package aiki.db;

public final class PkVariableSuffixCoeffEffBaseTypesLanceur extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixCoeffEffBaseTypesLanceur(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().coeffEffBaseTypesLanceur();
    }

    @Override
    public String value(String _v) {
        return getData().prefixCoeffEffBaseTypesLanceur(_v);
    }
}
