package aiki.beans.map.characters;

import org.junit.Test;

public final class DualFightBeanTest extends InitDbCharacters {
    @Test
    public void getImage() {
        assertEq("AAACAAAWAAAX",callDualFightBeanImageGet());
    }
    @Test
    public void getImageMini() {
        assertEq("AAABAAAX",callDualFightBeanImageMiniGet());
    }
    @Test
    public void getImageMiniSecond() {
        assertEq("AAABAAAY",callDualFightBeanImageMiniSecondGet());
    }
}
