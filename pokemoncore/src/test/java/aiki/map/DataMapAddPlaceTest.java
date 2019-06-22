package aiki.map;
import static aiki.db.EquallablePkUtil.assertEq;

import org.junit.Test;

import aiki.map.places.City;
import aiki.map.places.Place;
import code.util.CustList;
import code.util.*;


public class DataMapAddPlaceTest {

    @Test
    public void indexOfAddedPlace1Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setPlaces(new ShortMap<Place>());
        assertEq(CustList.FIRST_INDEX, dataMap_.indexOfAddedPlace());
    }

    @Test
    public void indexOfAddedPlace2Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setPlaces(new ShortMap<Place>());
        dataMap_.getPlaces().put((short) 0, new City());
        assertEq(1, dataMap_.indexOfAddedPlace());
    }

    @Test
    public void indexOfAddedPlace3Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setPlaces(new ShortMap<Place>());
        dataMap_.getPlaces().put((short) 0, new City());
        dataMap_.getPlaces().put((short) 2, new City());
        assertEq(1, dataMap_.indexOfAddedPlace());
    }

    @Test
    public void indexOfAddedPlace4Test() {
        DataMap dataMap_ = new DataMap();
        dataMap_.setPlaces(new ShortMap<Place>());
        dataMap_.getPlaces().put((short) 2, new City());
        assertEq(0, dataMap_.indexOfAddedPlace());
    }
}
