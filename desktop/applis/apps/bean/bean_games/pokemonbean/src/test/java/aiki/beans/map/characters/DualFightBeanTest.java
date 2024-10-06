package aiki.beans.map.characters;

import org.junit.Test;

public final class DualFightBeanTest extends InitDbCharacters {
    @Test
    public void getImage() {
        assertImgEq(IMG_DUAL,callDualFightBeanImageGet());
    }
    @Test
    public void getImageMini() {
        assertImgEq(IMG_DUAL1,callDualFightBeanImageMiniGet());
    }
    @Test
    public void getImageMiniSecond() {
        assertImgEq(IMG_DUAL2,callDualFightBeanImageMiniSecondGet());
    }
}
