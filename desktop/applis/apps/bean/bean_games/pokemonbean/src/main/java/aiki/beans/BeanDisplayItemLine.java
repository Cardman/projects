package aiki.beans;

import aiki.beans.facade.dto.*;

public final class BeanDisplayItemLine implements BeanDisplayElt<ItemLine> {
    @Override
    public int displayElt(CommonBean _rend, ItemLine _info) {
        _rend.initLine();
        _rend.addImg(_rend.getFacade().getData().getMiniItem(_info.getName().getKey()));
        _rend.feedParentsCts();
        _rend.formatMessageDirCts(_info.getName());
        _rend.formatMessageDirCts(Long.toString(_info.getPrice()));
        _rend.formatMessageDirCts(_info.getDescriptionClass());
        return 4;
    }
}
