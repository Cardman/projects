package cards.network.threads;

import code.util.CustList;
import code.util.core.FirstSeparatorFind;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.IntSplitPartsFields;

public final class NetRetrievedInfos {
    private final int indexAct;
    private final CustList<String> parts;
    public NetRetrievedInfos(CustList<IntSplitPartsFields> _window, String _info){
        FirstSeparatorFind cs_ = new FirstSeparatorFind(_info, Net.SEP_0);
        StringBuilder id_ = cs_.getId();
        int index_ = cs_.getIndex();
//        StringBuilder id_ = new StringBuilder();
//        int index_ = 0;
//        while (index_ < _info.length()) {
//            char ch_ = _info.charAt(index_);
//            if (ch_ == Net.SEP_0) {
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
        indexAct = indexClientAct_;
        parts = StringUtil.partsStrQuick(_window,_info,index_,_info.length(),find_,cs_);
    }

    public CustList<String> getParts() {
        return parts;
    }

    public int getIndexAct() {
        return indexAct;
    }
}
