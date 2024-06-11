package cards.network.threads;

import code.util.CustList;
import code.util.core.NumberUtil;

public final class NetRetrievedInfos {
    private final int indexAct;
    private final CustList<String> parts;
    public NetRetrievedInfos(CustList<IntSplitPartsFieldsCards> _window, String _info){
        int len_ = _info.length();
        StringBuilder id_ = new StringBuilder();
        int index_ = 0;
        while (true) {
            char ch_ = _info.charAt(index_);
            if (ch_ == Net.SEP_0) {
                index_++;
                break;
            }
            id_.append(ch_);
            index_++;
        }
        int indexClientAct_;
        int find_;
        if (id_.length() == 0) {
            find_ = 1;
            indexClientAct_ = 0;
        } else if (id_.charAt(0) == '-') {
            find_ = 2;
            indexClientAct_ = 1;
        } else {
            find_ = 0;
            indexClientAct_ = NumberUtil.parseInt(id_.toString())+2;
        }
        CustList<String> parts_ = new CustList<String>();
        StringBuilder part_ = new StringBuilder();
        int[] pass_ = new int[]{0};
        for (int i = index_; i < len_; i++) {
            char ch_ = _info.charAt(i);
            if (_window.get(find_).split(ch_, pass_)) {
                parts_.add(part_.toString());
                part_.delete(0,part_.length());
            } else {
                part_.append(ch_);
            }
        }
        parts_.add(part_.toString());
        indexAct = indexClientAct_;
        parts = parts_;
    }

    public CustList<String> getParts() {
        return parts;
    }

    public int getIndexAct() {
        return indexAct;
    }
}
