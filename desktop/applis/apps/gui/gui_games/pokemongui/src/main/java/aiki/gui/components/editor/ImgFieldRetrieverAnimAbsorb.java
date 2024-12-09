package aiki.gui.components.editor;

import aiki.db.ImageArrayBaseSixtyFour;
import aiki.facade.FacadeGame;

public final class ImgFieldRetrieverAnimAbsorb implements ImgFieldRetriever {
    @Override
    public ImageArrayBaseSixtyFour get(FacadeGame _fac) {
        return _fac.getData().getAnimAbsorb();
    }

    @Override
    public void set(FacadeGame _fac, ImageArrayBaseSixtyFour _i) {
        _fac.getData().setAnimAbsorb(_i);
    }
}
