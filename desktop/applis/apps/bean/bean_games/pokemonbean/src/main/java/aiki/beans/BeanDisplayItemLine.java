package aiki.beans;

import aiki.beans.facade.dto.*;
import code.util.core.*;

public final class BeanDisplayItemLine implements BeanDisplayEltGrid<ItemLine> {
    @Override
    public int displayEltGrid(CommonBean _rend, ItemLine _info) {
        _rend.initLine();
        _rend.addImg(_rend.getFacade().getData().getMiniItem(_info.getName().getKey()));
        _rend.feedParentsCts();
        _rend.formatMessageDirCts(_info.getName());
        _rend.formatMessageDirCts(Long.toString(_info.getPrice()));
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_info.getDescriptionClass()));
        return 4;
    }
}
