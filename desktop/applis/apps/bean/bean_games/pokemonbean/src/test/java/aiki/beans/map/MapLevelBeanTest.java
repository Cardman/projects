package aiki.beans.map;

import aiki.util.Coords;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class MapLevelBeanTest extends InitDbLevelMap {
    @Test
    public void getTiles() {
        assertSizeEq(25,callMapLevelBeanTilesGet(0));
    }
    @Test
    public void getMapWidth() {
        assertEq(5,callMapLevelBeanGetMapWidth(0));
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
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(0,12));
    }
    @Test
    public void clickForeGround2() {
        Coords c_ = callMapLevelBeanClickTileOnMapId(0, 12);
        assertEq(newCoords(0,0,1,1,1,1),c_);
    }
    @Test
    public void clickForeGround3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(1,12));
    }
    @Test
    public void clickForeGround4() {
        Coords c_ = callMapLevelBeanClickTileOnMapId(1, 12);
        assertEq(newCoords(1,0,1,1,1,1),c_);
    }
    @Test
    public void getGym1() {
        assertFalse(callMapLevelBeanGymGet(1,12));
    }
    @Test
    public void getGym2() {
        assertTrue(callMapLevelBeanGymGet(0,12));
    }
    @Test
    public void getPokemonCenter1() {
        assertFalse(callMapLevelBeanPokemonCenterGet(0,12));
    }
    @Test
    public void getPokemonCenter2() {
        assertTrue(callMapLevelBeanPokemonCenterGet(1,12));
    }
    @Test
    public void getOutside1() {
        assertFalse(callMapLevelBeanOutsideGet(0,12));
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
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(1,11));
    }
    @Test
    public void clickForeGround6() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(2,10));
    }
    @Test
    public void clickForeGround7() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML,callMapLevelBeanClickTileOnMap(3,0,12));
    }
    @Test
    public void clickForeGround8() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML,callMapLevelBeanClickTileOnMap(3,0,7));
    }
    @Test
    public void clickForeGround9() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML,callMapLevelBeanClickTileOnMap(3,0,8));
    }
    @Test
    public void clickForeGround10() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML,callMapLevelBeanClickTileOnMap(3,1,7));
    }
    @Test
    public void clickForeGround11() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DEALER_HTML,callMapLevelBeanClickTileOnMap(3,1,12));
    }
    @Test
    public void clickForeGround12() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callMapLevelBeanClickTileOnMap(3,1,13));
    }
    @Test
    public void clickForeGround13() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callMapLevelBeanClickTileOnMap(3,1,18));
    }
    @Test
    public void clickForeGround14() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BOOST_HTML,callMapLevelBeanClickTileOnMap(3,1,8));
    }
    @Test
    public void clickForeGroundId12() {
        assertEq(M_POK_00,callMapLevelBeanClickTileOnMapMv(3,1,13));
    }
    @Test
    public void clickForeGroundId13() {
        assertEq(M_POK_01,callMapLevelBeanClickTileOnMapMv(3,1,18));
    }
    @Test
    public void clickForeGroundId14() {
        assertEq(I_BOOST,callMapLevelBeanClickTileOnMapIt(3,1,8));
    }
    @Test
    public void clickForeGround15() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMapTwice(1,12,12));
    }
    @Test
    public void clickForeGround17() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMapTwice(1,12,8));
    }
    @Test
    public void clickForeGround18() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMapTwice(1,12,6));
    }
    @Test
    public void clickForeGround19() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML,callMapLevelBeanClickTileOnMapTwice(1,12,13));
    }
    @Test
    public void clickForeGround20() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML,callMapLevelBeanClickTileOnMapTwice(1,12,18));
    }
    @Test
    public void clickForeGround21() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMapTwice(0,12,13));
    }
    @Test
    public void clickForeGround22() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickTileOnMapTwice(0,12,4));
    }
    @Test
    public void clickForeGround23() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickTileOnMapTwice(0,12,7));
    }
    @Test
    public void clickForeGround24() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMapTwice(0,12,10));
    }
    @Test
    public void clickForeGround25() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(8,0,11));
    }
    @Test
    public void clickForeGround26() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickTileOnMap(8,0,12));
    }
    @Test
    public void clickForeGround27() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickTileOnMap(8,1,12));
    }
    @Test
    public void clickForeGround28() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(8,0,17));
    }
    @Test
    public void clickForeGround29() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(8,1,17));
    }
    @Test
    public void clickForeGround30() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(3,1,6));
    }
    @Test
    public void clickForeGround31() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(3,0,11));
    }
    @Test
    public void clickForeGround32() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(2,0,6));
    }
    @Test
    public void clickForeGround33() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(3,0,16));
    }
//    @Test
//    public void getDirs1() {
//        assertSizeEq(1,callMapLevelBeanDirsGet(2,0));
//    }
//    @Test
//    public void getDirs2() {
//        assertSizeEq(1,callMapLevelBeanDirsGet(2,3));
//    }
//    @Test
//    public void getDirs3() {
//        assertSizeEq(1,callMapLevelBeanDirsGet(6,0));
//    }
//    @Test
//    public void getDirs4() {
//        assertSizeEq(1,callMapLevelBeanDirsGet(7,0));
//    }
//    @Test
//    public void isUp1() {
//        assertTrue(callMapLevelBeanIsUp(2,0,0));
//    }
//    @Test
//    public void isUp2() {
//        assertFalse(callMapLevelBeanIsUp(2,3,0));
//    }
//    @Test
//    public void idDown1() {
//        assertTrue(callMapLevelBeanIsDown(2,3,0));
//    }
//    @Test
//    public void idDown2() {
//        assertFalse(callMapLevelBeanIsDown(2,0,0));
//    }
//    @Test
//    public void isLeft1() {
//        assertTrue(callMapLevelBeanIsLeft(6,0,0));
//    }
//    @Test
//    public void isLeft2() {
//        assertFalse(callMapLevelBeanIsLeft(7,0,0));
//    }
//    @Test
//    public void isRight1() {
//        assertTrue(callMapLevelBeanIsRight(7,0,0));
//    }
//    @Test
//    public void isRight2() {
//        assertFalse(callMapLevelBeanIsRight(6,0,0));
//    }
    @Test
    public void clickTileOnMap1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(4,18));
    }
    @Test
    public void clickTileOnMap2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML,callMapLevelBeanClickTileOnMap(6,10));
    }
    @Test
    public void clickTileOnMap3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(9,10));
    }
    @Test
    public void clickTileOnMap4() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(5,6));
    }
    @Test
    public void clickTileOnMap8() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(8,18));
    }
    @Test
    public void clickTileOnMap9() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,callMapLevelBeanClickTileOnMap(8,12));
    }
    @Test
    public void clickTileOnMap10() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(5,9));
    }
    @Test
    public void clickTileOnMap11() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickTileOnMap(5,8));
    }
    @Test
    public void getWildPokemonAreas() {
        assertSizeEq(2,callMapLevelBeanAreas(2));
    }
    @Test
    public void getNeighbours1() {
        assertSizeEq(2,callMapLevelBeanNeighboursGet(2));
    }
    @Test
    public void getNeighbours2() {
        assertEq(0,first(elt(callMapLevelBeanNeighboursGet(2),0)));
    }
    @Test
    public void getNeighbours3() {
        assertEq(PL_1,second(elt(callMapLevelBeanNeighboursGet(2),0)));
    }
    @Test
    public void getNeighbours4() {
        assertEq(1,first(elt(callMapLevelBeanNeighboursGet(2),1)));
    }
    @Test
    public void getNeighbours5() {
        assertEq(PL_2,second(elt(callMapLevelBeanNeighboursGet(2),1)));
    }
    @Test
    public void getLevelIndex() {
        assertEq(1,callMapLevelBeanLevelIndexGet(3,1));
    }
    @Test
    public void getPlaceName() {
        assertEq(PL_4,callMapLevelBeanPlaceNameGet(3,1));
    }
    @Test
    public void clickAreaOnMap1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickAreaOnMap(2,10));
    }
    @Test
    public void clickAreaOnMap2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML,callMapLevelBeanClickAreaOnMap(2,5));
    }
    @Test
    public void clickArea() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML,callMapLevelBeanClickArea(2,0));
    }
    @Test
    public void clickNeighbour() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapLevelBeanClickNeighbour(2,0));
    }
    @Test
    public void getWhiteTiles() {
        assertSizeEq(25,callMapLevelBeanWhiteTilesGet(0));
    }
    @Test
    public void isStorage1() {
        assertFalse(callMapLevelBeanIsStorage(0,12,12));
    }
    @Test
    public void isStorage2() {
        assertFalse(callMapLevelBeanIsStorage(0,13));
    }
    @Test
    public void isStorage3() {
        assertFalse(callMapLevelBeanIsStorage(2,10));
    }
    @Test
    public void isStorage4() {
        assertFalse(callMapLevelBeanIsStorage(1,12,6));
    }
    @Test
    public void isStorage5() {
        assertTrue(callMapLevelBeanIsStorage(1,12,7));
    }
    @Test
    public void isMoveTutors1() {
        assertFalse(callMapLevelBeanIsMoveTutors(0,12,12));
    }
    @Test
    public void isMoveTutors2() {
        assertFalse(callMapLevelBeanIsMoveTutors(1,12,7));
    }
    @Test
    public void isMoveTutors3() {
        assertFalse(callMapLevelBeanIsMoveTutors(1,12,6));
    }
    @Test
    public void isMoveTutors4() {
        assertFalse(callMapLevelBeanIsMoveTutors(1,12,13));
    }
    @Test
    public void isMoveTutors5() {
        assertFalse(callMapLevelBeanIsMoveTutors(1,12,18));
    }
    @Test
    public void isMoveTutors6() {
        assertTrue(callMapLevelBeanIsMoveTutors(1,12,8));
    }
    @Test
    public void isHealer1() {
        assertFalse(callMapLevelBeanIsHealer(0,12,12));
    }
    @Test
    public void isHealer2() {
        assertFalse(callMapLevelBeanIsHealer(1,12,7));
    }
    @Test
    public void isHealer3() {
        assertFalse(callMapLevelBeanIsHealer(1,12,8));
    }
    @Test
    public void isHealer4() {
        assertTrue(callMapLevelBeanIsHealer(1,12,6));
    }
    @Test
    public void isHost1() {
        assertFalse(callMapLevelBeanIsHost(1,12,6));
    }
    @Test
    public void isHost2() {
        assertTrue(callMapLevelBeanIsHost(1,12,11));
    }
    @Test
    public void isFossile1() {
        assertFalse(callMapLevelBeanIsFossile(1,12,11));
    }
    @Test
    public void isFossile2() {
        assertTrue(callMapLevelBeanIsFossile(1,12,16));
    }
    @Test
    public void withoutTitle1() {
        assertTrue(callMapLevelBeanWithoutTitle(1,12,12));
    }
    @Test
    public void withoutTitle2() {
        assertFalse(callMapLevelBeanWithoutTitle(1,12,7));
    }
    @Test
    public void withoutTitle3() {
        assertFalse(callMapLevelBeanWithoutTitle(1,12,8));
    }
    @Test
    public void withoutTitle4() {
        assertFalse(callMapLevelBeanWithoutTitle(1,12,6));
    }
    @Test
    public void withoutTitle5() {
        assertFalse(callMapLevelBeanWithoutTitle(1,12,11));
    }
    @Test
    public void withoutTitle6() {
        assertFalse(callMapLevelBeanWithoutTitle(1,12,16));
    }
    @Test
    public void isAccessibleByBeatingSomeTrainers1() {
        assertFalse(callMapLevelBeanIsAccessibleByBeatingSomeTrainers(3,0,15));
    }
    @Test
    public void isAccessibleByBeatingSomeTrainers2() {
        assertTrue(callMapLevelBeanIsAccessibleByBeatingSomeTrainers(3,0,16));
    }
}
