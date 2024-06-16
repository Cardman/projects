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
        indexAct = NumberUtil.parseInt(id_.toString());
        parts = partsStr(_info, index_, len_, NetAiki.AIKI_SEP_0);
    }

    public static CustList<String> partsStr(String _info, int _from, int _until, char _sep) {
        CustList<String> partsStr_ = new CustList<String>();
        StringBuilder part_ = new StringBuilder();
        int i_ = _from;
        while (i_ < _until) {
            char ch_ = _info.charAt(i_);
            if (ch_ == '\\') {
                i_++;
                part_.append('\\');
                part_.append(_info.charAt(i_));
                i_++;
            } else if (ch_ == _sep) {
                partsStr_.add(part_.toString());
                part_.delete(0,part_.length());
                i_++;
            } else {
                part_.append(ch_);
                i_++;
            }
        }
        partsStr_.add(part_.toString());
        return partsStr_;
    }

    public CustList<String> getParts() {
        return parts;
    }

    public int getIndexAct() {
        return indexAct;
    }
}
