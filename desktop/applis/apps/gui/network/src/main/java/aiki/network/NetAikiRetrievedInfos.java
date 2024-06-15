package aiki.network;

import code.util.CustList;
import code.util.core.NumberUtil;

public final class NetAikiRetrievedInfos {
    private final int indexAct;
    private final CustList<String> parts;
    public NetAikiRetrievedInfos(String _info){
        int len_ = _info.length();
        StringBuilder id_ = new StringBuilder();
        int index_ = 0;
        while (index_ < _info.length()) {
            char ch_ = _info.charAt(index_);
            if (ch_ == NetAiki.AIKI_SEP_0) {
                index_++;
                break;
            }
            id_.append(ch_);
            index_++;
        }
        if (index_ >= _info.length()) {
            indexAct = -1;
            CustList<String> partsStr_ = new CustList<String>();
            partsStr_.add(_info);
            parts = partsStr_;
            return;
        }
        int indexClientAct_ = NumberUtil.parseInt(id_.toString());
        CustList<String> partsStr_ = new CustList<String>();
        StringBuilder part_ = new StringBuilder();
        int i_ = index_;
        while (i_ < len_) {
            char ch_ = _info.charAt(i_);
            if (ch_ == '\\') {
                i_++;
                part_.append(_info.charAt(i_));
                i_++;
            } else if (ch_ == NetAiki.AIKI_SEP_0) {
                partsStr_.add(part_.toString());
                part_.delete(0,part_.length());
                i_++;
            } else {
                part_.append(ch_);
                i_++;
            }
        }
        partsStr_.add(part_.toString());
        indexAct = indexClientAct_;
        parts = partsStr_;
    }

    public CustList<String> getParts() {
        return parts;
    }

    public int getIndexAct() {
        return indexAct;
    }
}
