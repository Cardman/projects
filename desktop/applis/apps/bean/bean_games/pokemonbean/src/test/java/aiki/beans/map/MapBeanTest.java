package aiki.beans.map;

import aiki.util.Coords;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class MapBeanTest extends InitDbMap {
    @Test
    public void getPlaces() {
        assertSizeEq(10,callMapBeanPlacesGet());
    }
    @Test
    public void layers() {
        assertSizeEq(2,callMapBeanLayers(3));
    }
    @Test
    public void isMultiLayer1() {
        assertFalse(callMapBeanIsMultiLayer(0));
    }
    @Test
    public void isMultiLayer2() {
        assertTrue(callMapBeanIsMultiLayer(3));
    }
    @Test
    public void clickLevel1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapBeanClickLevel(3,1));
    }
    @Test
    public void clickLevel2() {
        Coords val_ = callMapBeanClickLevelId(3,1);
        assertEq(3,val_.getNumberPlace());
        assertEq(1,val_.getLevel().getLevelIndex());
    }
    @Test
    public void clickLevelZero1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callMapBeanClickLevelZero(0));
    }
    @Test
    public void clickLevelZero2() {
        Coords val_ = callMapBeanClickLevelZeroId(0);
        assertEq(0,val_.getNumberPlace());
        assertEq(0,val_.getLevel().getLevelIndex());
    }
}
