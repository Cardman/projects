package aiki.db;

public final class PkVariableSuffixCibleDegatsRecus extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixCibleDegatsRecus(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixCibleDegatsRecus(_v);
    }
}
