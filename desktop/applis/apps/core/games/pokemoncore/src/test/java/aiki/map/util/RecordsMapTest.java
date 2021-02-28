package aiki.map.util;

import aiki.db.EquallablePkUtil;
import aiki.fight.util.*;
import aiki.map.Condition;
import aiki.map.enums.Direction;
import aiki.util.*;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import org.junit.Test;

public final class RecordsMapTest extends EquallablePkUtil {
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
    @Test
    public void test5() {
        Condition c_ = new Condition(new CollCapacity(1));
        c_.add(new Coords(""));
        c_.removeAllObj(new Coords("0"));
        assertEq(1,c_.size());
    }
    @Test
    public void test6() {
        StatisticPokemons c_ = new StatisticPokemons();
        c_.getVal(new StatisticPokemon(";"));
        assertTrue(c_.isEmpty());
        c_.addEntry(new StatisticPokemon("SPEED;"), (byte) 0);
        assertNotNull(c_.getKeys());
        assertTrue(!c_.isEmpty());
    }
    @Test
    public void test7() {
        WeatherTypes c_ = new WeatherTypes();
        c_.addEntry(new WeatherType("SPEED;"), Rate.zero());
        assertNotNull(c_.getVal(new WeatherType("")));
        assertTrue(!c_.isEmpty());
    }
    @Test
    public void test8() {
        CustList<TypesDuo> typesDuos_ = new CustList<TypesDuo>();
        typesDuos_.add(new TypesDuo("SPEED;"));
        assertFalse(TypesDuos.contains(typesDuos_,new TypesDuo(";")));
        TypesDuos c_ = new TypesDuos();
        assertNotNull(c_.getVal(new TypesDuo(";")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test9() {
        StatisticTypeRate c_ = new StatisticTypeRate();
        assertNotNull(c_.getVal(new StatisticType(";")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test10() {
        StatisticTypeByte c_ = new StatisticTypeByte();
        assertNotNull(c_.getVal(new StatisticType(";")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test11() {
        StatisticCategoryRate c_ = new StatisticCategoryRate();
        assertNotNull(c_.getVal(new StatisticCategory(";")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test12() {
        StatisticCategoryByte c_ = new StatisticCategoryByte();
        assertNotNull(c_.getVal(new StatisticCategory(";")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test13() {
        StatisticStatusList c_ = new StatisticStatusList();
        assertEq(0,c_.getVal(new StatisticStatus(";")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test14() {
        CategoryMults c_ = new CategoryMults();
        assertNotNull(c_.getVal(new CategoryMult(";")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test15() {
        CoordsLists c_ = new CoordsLists();
        assertNotNull(c_.getVal(new Coords("0")));
        c_.removeKey(new Coords("0"));
        c_.put(new Coords("0"),new Condition());
        c_.put(new Coords("0"),new Condition());
        assertEq(1,c_.size());
        assertTrue(!c_.isEmpty());
    }
    @Test
    public void test16() {
        LevelPoints c_ = new LevelPoints();
        assertNotNull(c_.getVal(new LevelPoint("")));
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test17() {
        PointsString c_ = new PointsString();
        c_.removeKey(new Point());
        assertEq(0,c_.size());
        assertTrue(c_.isEmpty());
    }
    @Test
    public void test18() {
        MiniMapCoordsList c_ = new MiniMapCoordsList();
        assertNotNull(c_.getVal(MiniMapCoords.newMiniMapCoords("")));
    }
    @Test
    public void test19() {
        PlaceInterConnects c_ = new PlaceInterConnects();
        assertNotNull(c_.getVal(new PlaceInterConnect("")));
    }
    @Test
    public void test20() {
        PointsString c_ = new PointsString();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test21() {
        PointsShort c_ = new PointsShort();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test22() {
        PointsWildPk c_ = new PointsWildPk();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test23() {
        PointsBuildingArea c_ = new PointsBuildingArea();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test24() {
        PointsLink c_ = new PointsLink();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test25() {
        PointsArr c_ = new PointsArr();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test26() {
        PointsPoint c_ = new PointsPoint();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test27() {
        PointsDims c_ = new PointsDims();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test28() {
        PointsDualFight c_ = new PointsDualFight();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test29() {
        PointsPerson c_ = new PointsPerson();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test30() {
        PointsBuilding c_ = new PointsBuilding();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test31() {
        PointsBlock c_ = new PointsBlock();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test32() {
        PointsGymTrainer c_ = new PointsGymTrainer();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test33() {
        PointsCharacterInRoadCave c_ = new PointsCharacterInRoadCave();
        assertNotNull(c_.getVal(new Point()));
    }
    @Test
    public void test34() {
        PlaceInterConnects c_ = new PlaceInterConnects();
        c_.put(new PlaceInterConnect(""),new Coords(""));
        c_.put(new PlaceInterConnect(""),new Coords(""));
        assertEq(1,c_.size());
    }
}
