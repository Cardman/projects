package aiki.beans.help;

import org.junit.Test;

public final class GeneralHelpBeanTest extends InitDbGeneralHelp {
    @Test
    public void firstGeneralHelpHasItem1() {
        assertFalse(callGeneralHelpBeanFirstPokemonHasNoItem());
    }
    @Test
    public void firstGeneralHelpHasItem2() {
        assertTrue(callGeneralHelpBeanFirstPokemonHasItem());
    }
    @Test
    public void getTmPrice1() {
        assertEq("1",callGeneralHelpBeanGetTmPrice(0));
    }
    @Test
    public void getTmPrice2() {
        assertEq(NULL_REF,callGeneralHelpBeanGetTmPrice(1));
    }
    @Test
    public void getMapWidth() {
        assertEq(2,callGeneralHelpBeanGetMapWidth());
    }
    @Test
    public void getMiniMap1() {
        assertSizeEq(4,callGeneralHelpBeanMiniMapGet());
    }
    @Test
    public void getMiniMap2() {
        assertEq("AAACXXXXCAAADAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),0)));
    }
    @Test
    public void getMiniMap3() {
        assertEq("AAACXXXXCAAAFAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),1)));
    }
    @Test
    public void getMiniMap4() {
        assertEq("AAACXXXXCAAAEAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),2)));
    }
    @Test
    public void getMiniMap5() {
        assertEq("AAACXXXXCAAAGAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),3)));
    }
    @Test
    public void getMiniMapImage1() {
        assertEq("AAACXXXXCAAADAAA////",callGeneralHelpBeanGetMiniMapImage(0));
    }
    @Test
    public void getMiniMapImage2() {
        assertEq("AAACXXXXCAAAFAAA////",callGeneralHelpBeanGetMiniMapImage(1));
    }
    @Test
    public void getMiniMapImage3() {
        assertEq("AAACXXXXCAAAEAAA////",callGeneralHelpBeanGetMiniMapImage(2));
    }
    @Test
    public void getMiniMapImage4() {
        assertEq("AAACXXXXCAAAGAAA////",callGeneralHelpBeanGetMiniMapImage(3));
    }
    @Test
    public void getPlaceName1() {
        assertEq(NULL_REF,callGeneralHelpBeanGetPlaceName(0));
    }
    @Test
    public void getPlaceName2() {
        assertEq(CITY,callGeneralHelpBeanGetPlaceName(1));
    }
    @Test
    public void getPlaceName3() {
        assertEq(ROAD,callGeneralHelpBeanGetPlaceName(2));
    }
    @Test
    public void getPlaceName4() {
        assertEq(CAVE,callGeneralHelpBeanGetPlaceName(3));
    }
    @Test
    public void getBegin() {
        assertEq(ROAD,callGeneralHelpBeanBeginGet());
    }
    @Test
    public void getUnlockedCity() {
        assertEq("AAACXXXXCAAAHAAA////",callGeneralHelpBeanUnlockedCityGet());
    }
}
