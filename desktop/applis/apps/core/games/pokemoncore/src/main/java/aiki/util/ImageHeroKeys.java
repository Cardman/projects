package aiki.util;

import aiki.db.ImageArrayBaseSixtyFour;
import aiki.db.ImageHeroKey;

public final class ImageHeroKeys extends CommonMap<ImageHeroKey, ImageArrayBaseSixtyFour> {

    @Override
    protected ImageArrayBaseSixtyFour def() {
        return ImageArrayBaseSixtyFour.instance();
    }

    @Override
    protected boolean eq(ImageHeroKey _one, ImageHeroKey _two) {
        return _one.eq(_two);
    }
}
