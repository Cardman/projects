package aiki.comparators;

import aiki.db.DataBase;
import aiki.db.EquallablePkUtil;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.fight.InitializationDataBase;
import aiki.map.util.PlaceLevel;
import aiki.map.util.ScreenCoords;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.util.CustList;
import org.junit.Before;
import org.junit.Test;

public final class ComparatorsTest extends EquallablePkUtil {
    private DataBase data;
    @Before
    public void initDb() {
        data = InitializationDataBase.initDb();
    }
    @Test
    public void compareCoords1Test() {
        CustList<Coords> coords_ = new CustList<Coords>();
        coords_.add(newCoords(0,0,0,0));
        coords_.add(newCoords(1,0,0,0));
        coords_.sortElts(new ComparatorCoords(data));
        assertEq(newCoords(1,0,0,0),coords_.get(0));
        assertEq(newCoords(0,0,0,0),coords_.get(1));
    }
    @Test
    public void compareCoords2Test() {
        CustList<Coords> coords_ = new CustList<Coords>();
        coords_.add(newCoords(0,0,0,0));
        coords_.add(newCoords(0,0,0,1));
        coords_.sortElts(new ComparatorCoords(data));
        assertEq(newCoords(0,0,0,0),coords_.get(0));
        assertEq(newCoords(0,0,0,1),coords_.get(1));
    }
    @Test
    public void comparePlaceLevelTest() {
        CustList<PlaceLevel> coords_ = new CustList<PlaceLevel>();
        coords_.add(new PlaceLevel((short)0,(byte)0));
        coords_.add(new PlaceLevel((short)1,(byte)0));
        coords_.add(new PlaceLevel((short)0,(byte)1));
        coords_.add(new PlaceLevel((short)1,(byte)1));
        coords_.sortElts(new ComparatorPlaceLevel());
        assertEq(0,coords_.get(0).getPlace());
        assertEq(0,coords_.get(0).getLevel());
        assertEq(0,coords_.get(1).getPlace());
        assertEq(1,coords_.get(1).getLevel());
        assertEq(1,coords_.get(2).getPlace());
        assertEq(0,coords_.get(2).getLevel());
        assertEq(1,coords_.get(3).getPlace());
        assertEq(1,coords_.get(3).getLevel());
    }
    @Test
    public void compareScreenCoordsTest() {
        CustList<ScreenCoords> coords_ = new CustList<ScreenCoords>();
        coords_.add(new ScreenCoords((short)0,(byte)0));
        coords_.add(new ScreenCoords((short)1,(byte)0));
        coords_.add(new ScreenCoords((short)0,(byte)1));
        coords_.add(new ScreenCoords((short)1,(byte)1));
        coords_.sortElts(new ComparatorScreenCoords());
        assertEq(0,coords_.get(0).getXcoords());
        assertEq(0,coords_.get(0).getYcoords());
        assertEq(0,coords_.get(1).getXcoords());
        assertEq(1,coords_.get(1).getYcoords());
        assertEq(1,coords_.get(2).getXcoords());
        assertEq(0,coords_.get(2).getYcoords());
        assertEq(1,coords_.get(3).getXcoords());
        assertEq(1,coords_.get(3).getYcoords());
    }
    @Test
    public void compareTrainerPlaceNamesTest() {
        CustList<TrainerPlaceNames> coords_ = new CustList<TrainerPlaceNames>();
        coords_.add(new TrainerPlaceNames("0","0"));
        coords_.add(new TrainerPlaceNames("1","0"));
        coords_.add(new TrainerPlaceNames("0","1"));
        coords_.add(new TrainerPlaceNames("1","1"));
        coords_.sortElts(new ComparatorTrainerPlaceNames());
        assertEq("0",coords_.get(0).getTrainer());
        assertEq("0",coords_.get(0).getPlace());
        assertEq("0",coords_.get(1).getTrainer());
        assertEq("1",coords_.get(1).getPlace());
        assertEq("1",coords_.get(2).getTrainer());
        assertEq("0",coords_.get(2).getPlace());
        assertEq("1",coords_.get(3).getTrainer());
        assertEq("1",coords_.get(3).getPlace());
    }
    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Point newPoint(int _x, int _y) {
        return new Point((short)_x, (short)_y);
    }

}
