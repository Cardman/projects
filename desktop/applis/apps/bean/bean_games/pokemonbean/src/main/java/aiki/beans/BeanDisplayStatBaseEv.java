package aiki.beans;

import aiki.beans.pokemon.*;

public final class BeanDisplayStatBaseEv implements BeanDisplayEltGrid<StringStatBaseEv> {

    @Override
    public int displayEltGrid(CommonBean _rend, StringStatBaseEv _info) {
        _rend.formatMessageDirCts(_info.getName().getTranslation());
        _rend.formatMessageDirCts(Long.toString(_info.getStat().getBase()));
        _rend.formatMessageDirCts(Long.toString(_info.getStat().getEv()));
        return 1;
    }
}
