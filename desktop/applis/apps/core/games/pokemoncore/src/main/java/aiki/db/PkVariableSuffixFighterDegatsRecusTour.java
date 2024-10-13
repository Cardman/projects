package aiki.db;

public final class PkVariableSuffixFighterDegatsRecusTour extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixFighterDegatsRecusTour(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixFighterDegatsRecusTour(_v);
    }
}
