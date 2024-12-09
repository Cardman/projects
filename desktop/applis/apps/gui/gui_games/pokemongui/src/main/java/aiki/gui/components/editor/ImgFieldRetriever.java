package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;

public interface ImgFieldRetriever {
    ImageArrayBaseSixtyFour get(FacadeGame _fac);
    void set(FacadeGame _fac, ImageArrayBaseSixtyFour _i);
}
