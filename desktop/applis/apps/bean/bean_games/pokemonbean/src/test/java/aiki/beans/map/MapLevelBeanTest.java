package aiki.beans.map;

import aiki.beans.map.elements.AikiBeansMapElementsStd;
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
}
