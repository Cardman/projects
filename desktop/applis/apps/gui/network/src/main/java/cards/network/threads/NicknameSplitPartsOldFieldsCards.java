package cards.network.threads;

import code.util.CustList;

public final class NicknameSplitPartsOldFieldsCards implements IntSplitPartsFieldsCards {
    @Override
    public boolean split(char _ch, CustList<String> _parts) {
        return Net.SEP_0 == _ch && _parts.size() < 2;
    }
}
