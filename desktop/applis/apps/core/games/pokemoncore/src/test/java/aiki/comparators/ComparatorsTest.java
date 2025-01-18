package aiki.comparators;

import aiki.db.DataBase;
import aiki.db.EquallablePkUtil;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.fight.InitializationDataBase;
import aiki.map.util.PlaceLevel;
import aiki.map.util.ScreenCoords;
import aiki.util.*;
import code.util.CustList;
import org.junit.Test;

public final class ComparatorsTest extends EquallablePkUtil {

    @Test
    public void compareCoords1Test() {
        DataBase data_ = InitializationDataBase.initDb();
        CustList<Coords> coords_ = new CustList<Coords>();
        coords_.add(newCoords(0,0,0,0));
        coords_.add(newCoords(1,0,0,0));
        coords_.sortElts(new ComparatorCoords(data_));
        assertEq(newCoords(1,0,0,0),coords_.get(0));
        assertEq(newCoords(0,0,0,0),coords_.get(1));
    }
    @Test
    public void compareCoords2Test() {
        DataBase data_ = InitializationDataBase.initDb();
        CustList<Coords> coords_ = new CustList<Coords>();
        coords_.add(newCoords(0,0,0,0));
        coords_.add(newCoords(0,0,0,1));
        coords_.sortElts(new ComparatorCoords(data_));
        assertEq(newCoords(0,0,0,0),coords_.get(0));
        assertEq(newCoords(0,0,0,1),coords_.get(1));
    }
    @Test
    public void comparePlaceLevelTest() {
        CustList<PlaceLevel> coords_ = new CustList<PlaceLevel>();
        coords_.add(new PlaceLevel(0,0));
        coords_.add(new PlaceLevel(1,0));
        coords_.add(new PlaceLevel(0,1));
        coords_.add(new PlaceLevel(1,1));
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
        coords_.add(new ScreenCoords(0,0));
        coords_.add(new ScreenCoords(1,0));
        coords_.add(new ScreenCoords(0,1));
        coords_.add(new ScreenCoords(1,1));
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

}
