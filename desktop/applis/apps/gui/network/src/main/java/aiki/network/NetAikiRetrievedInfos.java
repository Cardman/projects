package aiki.network;

import code.util.CustList;
import code.util.core.FirstSeparatorFind;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class NetAikiRetrievedInfos {
    private final int indexAct;
    private final CustList<String> parts;
    public NetAikiRetrievedInfos(String _info){
        int len_ = _info.length();
        FirstSeparatorFind cs_ = new FirstSeparatorFind(_info, NetAiki.AIKI_SEP_0);
        StringBuilder id_ = cs_.getId();
        int index_ = cs_.getIndex();
//        while (index_ < _info.length()) {
//            char ch_ = _info.charAt(index_);
//            if (ch_ == NetAiki.AIKI_SEP_0) {
//                index_++;
//                break;
//            }
//            id_.append(ch_);
//            index_++;
//        }
        if (!cs_.isFound()) {
            indexAct = -1;
            CustList<String> partsStr_ = new CustList<String>();
            partsStr_.add(_info);
            parts = partsStr_;
            return;
        }
        indexAct = NumberUtil.parseInt(id_.toString());
        parts = StringUtil.partsStr(_info, index_, len_, NetAiki.AIKI_SEP_0);
    }

    public CustList<String> getParts() {
        return parts;
    }

    public int getIndexAct() {
        return indexAct;
    }
}
