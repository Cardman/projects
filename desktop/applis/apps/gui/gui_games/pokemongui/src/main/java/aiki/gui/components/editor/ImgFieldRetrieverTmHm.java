package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;

public final class ImgFieldRetrieverTmHm implements ImgFieldRetriever {
    @Override
    public ImageArrayBaseSixtyFour get(FacadeGame _fac) {
        return _fac.getData().getImageTmHm();
    }

    @Override
    public void set(FacadeGame _fac, ImageArrayBaseSixtyFour _i) {
        _fac.getData().setImageTmHm(_i);
    }
}
