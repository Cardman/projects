package aiki.beans.map.characters;

import org.junit.Test;

public final class DualFightBeanTest extends InitDbCharacters {
    @Test
    public void getImage() {
        assertEq(line(IMG_SINGLE,IMG_DUAL1),callDualFightBeanImageGet());
    }
    @Test
    public void getImageMini() {
        assertEq(one(IMG_DUAL1),callDualFightBeanImageMiniGet());
    }
    @Test
    public void getImageMiniSecond() {
        assertEq(one(IMG_DUAL2),callDualFightBeanImageMiniSecondGet());
    }
}
