package code.expressionlanguage.exec.util;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;

public final class ImplicitMethods {
    private final CustList<ExecTypeFunction> converter = new CustList<ExecTypeFunction>();
    private ExecFormattedRootBlock ownerClass;

    public CustList<ExecTypeFunction> getConverter() {
        return converter;
    }

    public ExecFormattedRootBlock getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(ExecFormattedRootBlock _ownerClass) {
        this.ownerClass = _ownerClass;
    }

    public static boolean isValidIndex(ImplicitMethods _impl,int _indexImplicit) {
        return _impl!=null&&_impl.isValidIndex(_indexImplicit);
    }

    public boolean isValidIndex(int _indexImplicit) {
        return converter.isValidIndex(_indexImplicit);
    }

    public ExecTypeFunction get(int _indexImplicit) {
        return converter.get(_indexImplicit);
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return converter.size();
    }
}
