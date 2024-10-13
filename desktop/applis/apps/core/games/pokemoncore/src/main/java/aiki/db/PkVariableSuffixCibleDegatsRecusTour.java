package aiki.db;

public final class PkVariableSuffixCibleDegatsRecusTour extends AbsVariableSuffixImpl implements AbsVariableSuffixArg {
    public PkVariableSuffixCibleDegatsRecusTour(DataBase _d) {
        super(_d);
    }

    @Override
    public String value(String _v) {
        return getData().prefixCibleDegatsRecusTour(_v);
    }
}
