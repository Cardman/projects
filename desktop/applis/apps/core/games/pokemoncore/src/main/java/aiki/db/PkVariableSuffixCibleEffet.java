package aiki.db;

public final class PkVariableSuffixCibleEffet extends AbsVariableSuffixImpl implements AbsVariableSuffix {
    public PkVariableSuffixCibleEffet(DataBase _d) {
        super(_d);
    }

    @Override
    public String value() {
        return getData().cibleEffet();
    }

    @Override
    public String value(String _v) {
        return getData().prefixCibleEffet(_v);
    }
}
