package aiki.util;

import aiki.db.ImageHeroKey;

public final class ImageHeroKeys extends CommonMap<ImageHeroKey, int[][]> {

    @Override
    protected int[][] def() {
        return new int[0][0];
    }

    @Override
    protected boolean eq(ImageHeroKey _one, ImageHeroKey _two) {
        return _one.eq(_two);
    }
}
