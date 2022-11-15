package aiki.beans.map;

import aiki.beans.items.AikiBeansItemsStd;
import aiki.beans.map.elements.AikiBeansMapElementsStd;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.util.Coords;
import org.junit.Test;

public final class MapLevelBeanTest extends InitDbLevelMap {
    @Test
    public void getTiles() {
        assertSizeEq(9,callMapLevelBeanTilesGet(0));
    }
    @Test
    public void getRoad1() {
        assertFalse(callMapLevelBeanRoadGet(0));
    }
    @Test
    public void getRoad2() {
        assertTrue(callMapLevelBeanRoadGet(2));
    }
    @Test
    public void clickForeGround1() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(0,4));
    }
    @Test
    public void clickForeGround2() {
        Coords c_ = callMapLevelBeanClickForeGroundId(0, 4);
        assertEq(newCoords(0,0,1,1,1,1),c_);
    }
    @Test
    public void clickForeGround3() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(1,4));
    }
    @Test
    public void clickForeGround4() {
        Coords c_ = callMapLevelBeanClickForeGroundId(1, 4);
        assertEq(newCoords(1,0,1,1,1,1),c_);
    }
    @Test
    public void getGym1() {
        assertFalse(callMapLevelBeanGymGet(1,4));
    }
    @Test
    public void getGym2() {
        assertTrue(callMapLevelBeanGymGet(0,4));
    }
    @Test
    public void getPokemonCenter1() {
        assertFalse(callMapLevelBeanPokemonCenterGet(0,4));
    }
    @Test
    public void getPokemonCenter2() {
        assertTrue(callMapLevelBeanPokemonCenterGet(1,4));
    }
    @Test
    public void getOutside1() {
        assertFalse(callMapLevelBeanOutsideGet(0,4));
    }
    @Test
    public void getOutside2() {
        assertTrue(callMapLevelBeanOutsideGet(0));
    }
    @Test
    public void getPossibleMultiLayer1() {
        assertFalse(callMapLevelBeanPossibleMultiLayerGet(0));
    }
    @Test
    public void getPossibleMultiLayer2() {
        assertTrue(callMapLevelBeanPossibleMultiLayerGet(3,0));
    }
    @Test
    public void getPossibleMultiLayer3() {
        assertTrue(callMapLevelBeanPossibleMultiLayerGet(8,0));
    }
    @Test
    public void clickForeGround5() {
        assertEq("",callMapLevelBeanClickForeGround(1,3));
    }
    @Test
    public void clickForeGround6() {
        assertEq("",callMapLevelBeanClickForeGround(2,3));
    }
    @Test
    public void clickForeGround7() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML,callMapLevelBeanClickForeGround(3,0,4));
    }
    @Test
    public void clickForeGround8() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML,callMapLevelBeanClickForeGround(3,0,1));
    }
    @Test
    public void clickForeGround9() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML,callMapLevelBeanClickForeGround(3,0,2));
    }
    @Test
    public void clickForeGround10() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML,callMapLevelBeanClickForeGround(3,1,1));
    }
    @Test
    public void clickForeGround11() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_DEALER_HTML,callMapLevelBeanClickForeGround(3,1,4));
    }
    @Test
    public void clickForeGround12() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callMapLevelBeanClickForeGround(3,1,5));
    }
    @Test
    public void clickForeGround13() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callMapLevelBeanClickForeGround(3,1,8));
    }
    @Test
    public void clickForeGround14() {
        assertEq(AikiBeansItemsStd.WEB_HTML_ITEMS_BOOST_HTML,callMapLevelBeanClickForeGround(3,1,2));
    }
    @Test
    public void clickForeGround15() {
        assertEq("",callMapLevelBeanClickForeGroundTwice(1,4,4));
    }
    @Test
    public void clickForeGround17() {
        assertEq("",callMapLevelBeanClickForeGroundTwice(1,4,2));
    }
    @Test
    public void clickForeGround18() {
        assertEq("",callMapLevelBeanClickForeGroundTwice(1,4,0));
    }
    @Test
    public void clickForeGround19() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_SELLER_HTML,callMapLevelBeanClickForeGroundTwice(1,4,5));
    }
    @Test
    public void clickForeGround20() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_SELLER_HTML,callMapLevelBeanClickForeGroundTwice(1,4,8));
    }
    @Test
    public void clickForeGround21() {
        assertEq("",callMapLevelBeanClickForeGroundTwice(0,4,3));
    }
    @Test
    public void clickForeGround22() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickForeGroundTwice(0,4,0));
    }
    @Test
    public void clickForeGround23() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickForeGroundTwice(0,4,1));
    }
    @Test
    public void clickForeGround24() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGroundTwice(0,4,2));
    }
    @Test
    public void clickForeGround25() {
        assertEq("",callMapLevelBeanClickForeGround(8,0,3));
    }
    @Test
    public void clickForeGround26() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickForeGround(8,0,4));
    }
    @Test
    public void clickForeGround27() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickForeGround(8,1,4));
    }
    @Test
    public void clickForeGround28() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(8,0,7));
    }
    @Test
    public void clickForeGround29() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(8,1,7));
    }
    @Test
    public void clickForeGround30() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(3,1,0));
    }
    @Test
    public void clickForeGround31() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(3,0,3));
    }
    @Test
    public void clickForeGround32() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(2,0,1));
    }
    @Test
    public void clickForeGround33() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickForeGround(3,0,6));
    }
    @Test
    public void getDirs1() {
        assertSizeEq(1,callMapLevelBeanDirsGet(2,0));
    }
    @Test
    public void getDirs2() {
        assertSizeEq(1,callMapLevelBeanDirsGet(2,3));
    }
    @Test
    public void getDirs3() {
        assertSizeEq(1,callMapLevelBeanDirsGet(6,0));
    }
    @Test
    public void getDirs4() {
        assertSizeEq(1,callMapLevelBeanDirsGet(7,0));
    }
    @Test
    public void isUp1() {
        assertTrue(callMapLevelBeanIsUp(2,0,0));
    }
    @Test
    public void isUp2() {
        assertFalse(callMapLevelBeanIsUp(2,3,0));
    }
    @Test
    public void idDown1() {
        assertTrue(callMapLevelBeanIsDown(2,3,0));
    }
    @Test
    public void idDown2() {
        assertFalse(callMapLevelBeanIsDown(2,0,0));
    }
    @Test
    public void isLeft1() {
        assertTrue(callMapLevelBeanIsLeft(6,0,0));
    }
    @Test
    public void isLeft2() {
        assertFalse(callMapLevelBeanIsLeft(7,0,0));
    }
    @Test
    public void isRight1() {
        assertTrue(callMapLevelBeanIsRight(7,0,0));
    }
    @Test
    public void isRight2() {
        assertFalse(callMapLevelBeanIsRight(6,0,0));
    }
    @Test
    public void clickTileOnMap1() {
        assertEq("",callMapLevelBeanClickTileOnMap(4,8));
    }
    @Test
    public void clickTileOnMap2() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_AREA_HTML,callMapLevelBeanClickTileOnMap(6,3));
    }
    @Test
    public void clickTileOnMap3() {
        assertEq("",callMapLevelBeanClickTileOnMap(9,3));
    }
    @Test
    public void clickTileOnMap4() {
        assertEq(AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(5,0));
    }
}
