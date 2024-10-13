package aiki.db;

public final class PkVariableSuffixCibleNbUtilisation extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixCibleNbUtilisation(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixCibleNbUtilisation(_v);
    }
}
