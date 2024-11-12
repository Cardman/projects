package aiki.util;

import aiki.db.ImageArrayBaseSixtyFour;
import aiki.db.ImageHeroKey;
import code.util.*;

public final class ImageHeroKeys extends AbsBasicMap<ImageHeroKey, ImageArrayBaseSixtyFour> {

    @Override
    protected ImageArrayBaseSixtyFour def() {
        return ImageArrayBaseSixtyFour.instance();
    }

    @Override
    protected boolean matchKeys(ImageHeroKey _one, ImageHeroKey _two) {
        return _one.eq(_two);
    }
}
