package aiki.facade;

import code.util.AbsComparerTreeMap;
import code.maths.LgInt;
import code.util.ints.Comparing;

public final class ItemsBuySellMap extends AbsComparerTreeMap<String, LgInt> {
    public ItemsBuySellMap(Comparing<String> _cmp) {
        super(_cmp);
    }
}
