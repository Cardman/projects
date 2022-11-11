package aiki.beans.pokemon;

import aiki.game.fight.Fight;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class PokemonBeanTest extends InitDbPkOne {
    @Test
    public void getDisplayName() {
        assertEq(P_POK_00_TR,callPokemonBeanDisplayNameGet(0));
    }
    @Test
    public void isAppearing1() {
        assertTrue(callPokemonBeanIsAppearing(0,2,0));
    }
    @Test
    public void isAppearing2() {
        assertTrue(callPokemonBeanIsAppearing(8,2,0));
    }
    @Test
    public void isAppearing3() {
        assertTrue(callPokemonBeanIsAppearing(15,0,1));
    }
    @Test
    public void isAppearing4() {
        assertFalse(callPokemonBeanIsAppearing(1,0,1));
    }
    @Test
    public void isAppearingZero1() {
        assertTrue(callPokemonBeanIsAppearingZero(0,2));
    }
    @Test
    public void isAppearingZero2() {
        assertFalse(callPokemonBeanIsAppearingZero(15,2));
    }
    @Test
    public void isAppearingPlace1() {
        assertTrue(callPokemonBeanIsAppearingPlace(15,0));
    }
    @Test
    public void isAppearingPlace2() {
        assertFalse(callPokemonBeanIsAppearingPlace(1,0));
    }
    @Test
    public void isAppearingAnyWhere1() {
        assertTrue(callPokemonBeanIsAppearingAnyWhere(15));
    }
    @Test
    public void isAppearingAnyWhere2() {
        assertFalse(callPokemonBeanIsAppearingAnyWhere(2));
    }
    @Test
    public void layers() {
        assertSizeEq(2,callPokemonBeanLayers(0));
    }
    @Test
    public void isMultiLayer1() {
        assertTrue(callPokemonBeanIsMultiLayer(0));
    }
    @Test
    public void isMultiLayer2() {
        assertFalse(callPokemonBeanIsMultiLayer(1));
    }
    @Test
    public void isMultiLayer3() {
        assertFalse(callPokemonBeanIsMultiLayer(2));
    }
    @Test
    public void isFirstRow1() {
        assertFalse(callPokemonBeanIsFirstRow(0));
    }
    @Test
    public void isFirstRow2() {
        assertFalse(callPokemonBeanIsFirstRow(1));
    }
    @Test
    public void isFirstRow3() {
        assertTrue(callPokemonBeanIsFirstRow(2));
    }
    @Test
    public void isFirstRow4() {
        assertFalse(callPokemonBeanIsFirstRow(3));
    }
    @Test
    public void getMapWidth() {
        assertEq(2,callPokemonBeanGetMapWidth());
    }
    @Test
    public void getImages1() {
        assertSizeEq(4,callPokemonBeanImagesGet());
    }
    @Test
    public void getImages2() {
        assertEq("AAACXXXXCAAADAAA////",second(elt(callPokemonBeanImagesGet(),0)));
    }
    @Test
    public void getImages3() {
        assertEq("AAACXXXXCAAAFAAA////",second(elt(callPokemonBeanImagesGet(),1)));
    }
    @Test
    public void getImages4() {
        assertEq("AAACXXXXCAAAEAAA////",second(elt(callPokemonBeanImagesGet(),2)));
    }
    @Test
    public void getImages5() {
        assertEq("AAACXXXXCAAAGAAA////",second(elt(callPokemonBeanImagesGet(),3)));
    }
    @Test
    public void getBackImage() {
        assertEq("AAACAAAADAAA////////",callPokemonBeanBackImageGet());
    }
    @Test
    public void getFrontImage() {
        assertEq("AAACAAAAEAAA////////",callPokemonBeanFrontImageGet());
    }
    @Test
    public void getMiniMapImage1() {
        assertEq("AAACXXXXCAAADAAA////",callPokemonBeanGetMiniMapImage(0));
    }
    @Test
    public void getMiniMapImage2() {
        assertEq("AAACXXXXCAAAFAAA////",callPokemonBeanGetMiniMapImage(1));
    }
    @Test
    public void getMiniMapImage3() {
        assertEq("AAACAAAABAAAEAAA////",callPokemonBeanGetMiniMapImage(2));
    }
    @Test
    public void getMiniMapImage4() {
        assertEq("AAACXXXXCAAAGAAA////",callPokemonBeanGetMiniMapImage(3));
    }
    @Test
    public void getPlaces1() {
        assertSizeEq(3,callPokemonBeanPlacesGet());
    }
    @Test
    public void getPlaces2() {
        assertEq(2,callPlaceIndexIndexGet(elt(callPokemonBeanPlacesGet(),0)));
    }
    @Test
    public void getPlaces3() {
        assertEq(CAVE,callPlaceGetName(callPlaceIndexGetPlace(elt(callPokemonBeanPlacesGet(),0))));
    }
    @Test
    public void getPlaces4() {
        assertEq(1,callPlaceIndexIndexGet(elt(callPokemonBeanPlacesGet(),1)));
    }
    @Test
    public void getPlaces5() {
        assertEq(CITY,callPlaceGetName(callPlaceIndexGetPlace(elt(callPokemonBeanPlacesGet(),1))));
    }
    @Test
    public void getPlaces6() {
        assertEq(0,callPlaceIndexIndexGet(elt(callPokemonBeanPlacesGet(),2)));
    }
    @Test
    public void getPlaces7() {
        assertEq(ROAD,callPlaceGetName(callPlaceIndexGetPlace(elt(callPokemonBeanPlacesGet(),2))));
    }
    @Test
    public void getPlaceName1() {
        assertEq(NULL_REF,callPokemonBeanGetPlaceName(0));
    }
    @Test
    public void getPlaceName2() {
        assertEq(CITY,callPokemonBeanGetPlaceName(1));
    }
    @Test
    public void getPlaceName3() {
        assertEq(ROAD,callPokemonBeanGetPlaceName(2));
    }
    @Test
    public void getPlaceName4() {
        assertEq(CAVE,callPokemonBeanGetPlaceName(3));
    }
    @Test
    public void getHeight() {
        assertEq(Rate.one(),callPokemonBeanHeightGet());
    }
    @Test
    public void getWeight() {
        assertEq(Rate.one(),callPokemonBeanWeightGet());
    }
    @Test
    public void roundHeight() {
        assertEq("1.0E0",callPokemonBeanRoundHeight());
    }
    @Test
    public void roundWeight() {
        assertEq("1.0E0",callPokemonBeanRoundWeight());
    }
    @Test
    public void getHatchingSteps() {
        assertEq(LgInt.one(),callPokemonBeanHatchingStepsGet());
    }
    @Test
    public void getCatchingRate() {
        assertEq(1,callPokemonBeanCatchingRateGet());
    }
    @Test
    public void getExpRate() {
        assertEq(1,callPokemonBeanExpRateGet());
    }
    @Test
    public void getExpEvo() {
        assertEq(Fight.TEMPS_TOUR,callPokemonBeanExpEvoGet());
    }
    @Test
    public void getEvoBase() {
        assertEq(P_POK_00_TR,callPokemonBeanEvoBaseGet());
    }
    @Test
    public void clickBase1() {
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,callPokemonBeanClickBase());
    }
    @Test
    public void clickBase2() {
        assertEq(P_POK_00,callPokemonBeanClickBaseId());
    }
}
