package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.util.*;

public final class ImgRetrieverLinks implements ImgRetriever {
    @Override
    public StringMap<ImageArrayBaseSixtyFour> all(FacadeGame _fac) {
        return _fac.getData().getLinks();
    }
}
