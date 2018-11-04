package code.expressionlanguage.options;

import code.util.CustList;
import code.util.ints.Comparing;

public final class StartsWithComparing implements Comparing<String> {

    @Override
    public int compare(String _one, String _two) {
        if (_one.startsWith(_two)) {
            return CustList.NO_SWAP_SORT;
        }
        if (_two.startsWith(_one)) {
            return CustList.SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }

}
