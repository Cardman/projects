package aiki.beans;

import aiki.beans.facade.dto.*;
import code.scripts.pages.aiki.*;
import code.util.core.*;

public final class BeanDisplayMoveLine implements BeanDisplayEltGrid<MoveLine> {
    @Override
    public int displayEltGrid(CommonBean _rend, MoveLine _info) {
        _rend.formatMessageDirCts(_info.getTranslatedKey());
        _rend.formatMessageDirCts(Long.toString(_info.getPp()));
        _rend.formatMessageDirCts(StringUtil.join(_info.getTypes()," - "));
        _rend.formatMessageDirCts(_info.getCategory());
        if (_info.isDamageMove()) {
            _rend.formatMessageCts(MessagesPkBean.MOVES, MessagesDataMovesMoves.M_P_71_DAMAGING);
        } else {
            _rend.formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_STATUS);
        }
        if (!_info.isDamageMove()) {
            _rend.formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_STATUS_INDIRECT);
        } else if (_info.isDirect()) {
            _rend.formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_DAMAGING_DIRECT);
        } else {
            _rend.formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_DAMAGING_INDIRECT);
        }
        _rend.formatMessageDirCts(Long.toString(_info.getPriority()));
        _rend.formatMessageDirCts(_info.getAccuracy());
        _rend.formatMessageDirCts(_info.getPower());
        return 9;
    }
}
