package aiki.map.pokemon;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingPp;
import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;
import code.util.pagination.SearchingMode;


public class CriteriaForSearchingItemTest extends InitializationDataBase {

    @Test
    public void matchName1Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchName(NULL_REF));
    }

    @Test
    public void matchName2Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchName("NAME"));
    }

    @Test
    public void matchName3Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfName("NA");
        assertTrue(!criteria_.matchName("NAME"));
    }

    @Test
    public void matchName4Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfName("NAME");
        assertTrue(criteria_.matchName("NAME"));
    }

    @Test
    public void matchPrice1Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchPrice(0));
    }

    @Test
    public void matchPrice2Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchPrice(2));
    }

    @Test
    public void matchPrice3Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setMinPrice(3);
        criteria_.setMaxPrice(5);
        assertTrue(!criteria_.matchPrice(6));
    }

    @Test
    public void matchPrice4Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setMinPrice(3);
        criteria_.setMaxPrice(5);
        assertTrue(criteria_.matchPrice(4));
    }

    @Test
    public void matchNumber1Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchNumber(new LgInt("0")));
    }

    @Test
    public void matchNumber2Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchNumber(new LgInt("2")));
    }

    @Test
    public void matchNumber3Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setMinNumber(new LgInt("3"));
        criteria_.setMaxNumber(new LgInt("5"));
        assertTrue(!criteria_.matchNumber(new LgInt("6")));
    }

    @Test
    public void matchNumber4Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setMinNumber(new LgInt("3"));
        criteria_.setMaxNumber(new LgInt("5"));
        assertTrue(criteria_.matchNumber(new LgInt("4")));
    }

    @Test
    public void matchClass1Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchClass(_data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchClass2Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Berry.ITEM);
        assertTrue(criteria_.matchClass(_data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchClass3Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Ball.ITEM);
        assertTrue(!criteria_.matchClass(_data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchClass4Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(HealingPp.ITEM);
        assertTrue(criteria_.matchClass(_data_.getItem(HUILE)));
    }
}
