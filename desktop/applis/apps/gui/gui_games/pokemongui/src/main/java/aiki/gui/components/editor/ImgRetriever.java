package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.util.*;

public interface ImgRetriever {
    StringMap<ImageArrayBaseSixtyFour> all(FacadeGame _fac);
}
