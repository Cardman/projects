package aiki.beans;

import aiki.beans.facade.dto.*;
import aiki.beans.simulation.*;
import code.util.core.*;

public final class BeanDisplayItemLineSimu implements BeanDisplayEltGrid<ItemLine> {
    private final SelectItemBean select;

    public BeanDisplayItemLineSimu(SelectItemBean _s) {
        this.select = _s;
    }
    @Override
    public int displayEltGrid(CommonBean _rend, ItemLine _info) {
        _rend.initLine();
        _rend.addImg(_rend.getFacade().getData().getMiniItem(_info.getName().getKey()));
        _rend.feedParentsCts();
        _rend.getBuilder().formatMessageDirCts(_info.getName().getTranslation(),new SelectItemBeanClickLink(select,_info.getName()));
        _rend.formatMessageDirCts(Long.toString(_info.getPrice()));
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(_info.getDescriptionClass()));
        return 4;
    }
}
