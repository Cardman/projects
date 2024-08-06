package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.fight.items.*;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;
import aiki.facade.enums.SearchingMode;


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
        criteria_.setMinPrice(3L);
        criteria_.setMaxPrice(5L);
        assertTrue(!criteria_.matchPrice(6));
    }

    @Test
    public void matchPrice4Test() {
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setMinPrice(3L);
        criteria_.setMaxPrice(5L);
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
        DataBase data_ = initDb();
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        assertTrue(criteria_.matchClass(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchClass2Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Item.BERRY);
        assertTrue(criteria_.matchClass(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchClass3Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Item.BALL);
        assertTrue(!criteria_.matchClass(data_.getItem(BAIE_ORAN)));
    }

    @Test
    public void matchClass4Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Item.HEALING_PP);
        assertTrue(criteria_.matchClass(data_.getItem(HUILE)));
    }

    @Test
    public void matchClass5Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Item.HEALING_ITEM);
        assertTrue(criteria_.matchClass(data_.getItem(REVEIL)));
    }

    @Test
    public void matchClass6Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Item.HEALING_ITEM);
        assertTrue(criteria_.matchClass(data_.getItem(PETIT_RAPPEL)));
    }

    @Test
    public void matchClass7Test() {
        DataBase data_ = initDb();
        CriteriaForSearchingItem criteria_ = new CriteriaForSearchingItem();
        criteria_.setSelectedClass(Item.HEALING_ITEM);
        assertTrue(!criteria_.matchClass(data_.getItem(POKE_BALL)));
    }
}
