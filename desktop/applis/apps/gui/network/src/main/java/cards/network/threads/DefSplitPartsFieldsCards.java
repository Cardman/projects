package cards.network.threads;

import code.util.CustList;

public final class DefSplitPartsFieldsCards implements IntSplitPartsFieldsCards {
    @Override
    public boolean split(char _ch, CustList<String> _parts) {
        return Net.SEP_0 == _ch;
    }
}
