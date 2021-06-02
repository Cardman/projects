package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.common.AbstractLookingSuperTypes;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.CustList;

public final class ExecLookingSuperTypes extends AbstractLookingSuperTypes {
    private final CustList<ExecFormattedRootBlock> list;
    public ExecLookingSuperTypes(CustList<ExecFormattedRootBlock> _list) {
        list = _list;
    }
    @Override
    protected int sizeType() {
        return list.size();
    }

    @Override
    protected String getType(int _index) {
        return list.get(_index).getFormatted();
    }
}
