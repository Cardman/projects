package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.AbstractLookingSuperTypes;
import code.util.CustList;

public final class AnaLookingSuperTypes extends AbstractLookingSuperTypes {
    private final CustList<AnaFormattedRootBlock> list;
    public AnaLookingSuperTypes(CustList<AnaFormattedRootBlock> _list) {
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
