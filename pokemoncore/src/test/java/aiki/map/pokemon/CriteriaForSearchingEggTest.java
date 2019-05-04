package aiki.map.pokemon;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.facade.enums.SearchingMode;


public class CriteriaForSearchingEggTest extends InitializationDataBase {

    @Test
    public void matchName1Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        assertTrue(criteria_.matchName(NULL_REF));
    }

    @Test
    public void matchName2Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        assertTrue(criteria_.matchName("NAME"));
    }

    @Test
    public void matchName3Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfName("NA");
        assertTrue(!criteria_.matchName("NAME"));
    }

    @Test
    public void matchName4Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfName("NAME");
        assertTrue(criteria_.matchName("NAME"));
    }

    @Test
    public void matchSteps1Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        assertTrue(criteria_.matchSteps(0));
    }

    @Test
    public void matchSteps2Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        assertTrue(criteria_.matchSteps(2));
    }

    @Test
    public void matchSteps3Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        criteria_.setMinSteps(3);
        criteria_.setMaxSteps(5);
        assertTrue(!criteria_.matchSteps(6));
    }

    @Test
    public void matchSteps4Test() {
        CriteriaForSearchingEgg criteria_ = new CriteriaForSearchingEgg();
        criteria_.setMinSteps(3);
        criteria_.setMaxSteps(5);
        assertTrue(criteria_.matchSteps(4));
    }
}
