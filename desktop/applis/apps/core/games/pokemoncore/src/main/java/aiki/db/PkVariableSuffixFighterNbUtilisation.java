package aiki.db;

public final class PkVariableSuffixFighterNbUtilisation extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixFighterNbUtilisation(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixFighterNbUtilisation(_v);
    }
}
