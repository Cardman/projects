package aiki.db;

public final class PkVariableSuffixCiblePp extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixCiblePp(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().ciblePp();
    }

    @Override
    public String value(String _v) {
        return getData().prefixCiblePp(_v);
    }
}
