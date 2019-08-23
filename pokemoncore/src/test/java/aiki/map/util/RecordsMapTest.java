package aiki.map.util;

import aiki.map.enums.Direction;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class RecordsMapTest {
    @Test
    public void test1() {
        MiniMapCoords m_ = MiniMapCoords.newMiniMapCoords("0 0");
        m_.display();
        assertEq(0,m_.getXcoords());
    }
    @Test
    public void test2() {
        PlaceInterConnect p_ = PlaceInterConnect.newPlaceInterConnect("0;UP");
        p_.display();
        assertEq(Direction.UP,p_.getDir());
    }
    @Test
    public void test3() {
        TileMiniMap t_ = new TileMiniMap();
        t_.setHeros(true);
        assertTrue(t_.isHeros());
    }
    @Test
    public void test4() {
        PlaceLevel t_ = new PlaceLevel();
        t_.setLevel((byte) 0);
        t_.setPlace((short) 0);
        t_.display();
        assertEq(0,t_.getLevel());
    }
}
