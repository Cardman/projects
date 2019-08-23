package cards.president;
import static cards.president.EquallablePresidentUtil.assertEq;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class RulesPresidentTest {
    @Test
    public void isValidRules_defaultRules1Test(){
        RulesPresident rules_ = new RulesPresident(3);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
        assertEq(18,rules_.getNbMaxCardsPerPlayer());
    }
    @Test
    public void isValidRules_defaultRules2Test(){
        RulesPresident rules_ = new RulesPresident(4);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
        assertEq(13,rules_.getNbMaxCardsPerPlayer());
    }
    @Test
    public void isValidRules_defaultRules3Test(){
        RulesPresident rules_ = new RulesPresident(5);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules4Test(){
        RulesPresident rules_ = new RulesPresident(6);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules5Test(){
        RulesPresident rules_ = new RulesPresident(7);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules6Test(){
        RulesPresident rules_ = new RulesPresident(8);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules7Test(){
        RulesPresident rules_ = new RulesPresident(9);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules8Test(){
        RulesPresident rules_ = new RulesPresident(10);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules9Test(){
        RulesPresident rules_ = new RulesPresident(11);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules10Test(){
        RulesPresident rules_ = new RulesPresident(12);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules11Test(){
        RulesPresident rules_ = new RulesPresident(13);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
        assertEq(1,RulesPresident.getNbMinStacks(13));
    }
    @Test
    public void isValidRules_defaultRules12Test(){
        RulesPresident rules_ = new RulesPresident(14);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
        assertEq(2,RulesPresident.getNbMinStacks(14));
    }
    @Test
    public void isValidRules_defaultRules13Test(){
        RulesPresident rules_ = new RulesPresident(15);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void isValidRules_defaultRules14Test(){
        RulesPresident rules_ = new RulesPresident(16);
        assertTrue(rules_.isValidRules());
        assertTrue(rules_.getNbMaxStacks() >= rules_.getNbMinStacks());
    }
    @Test
    public void copyTest() {
        RulesPresident rules_ = new RulesPresident();
        RulesPresident rulesTwo_ = new RulesPresident(rules_);
        rulesTwo_.setNbDeals(4);
        rulesTwo_.setMixedCards(rules_.getMixedCards());
        assertEq(0,rules_.getNbDeals());
        assertEq(1,RulesPresident.getNbMinStacks(4));
        assertEq(2,RulesPresident.getNbMaxStacks(4));
        assertEq(19,RulesPresident.getNbMaxStacksPlayers());
        assertEq(3,RulesPresident.getNbMinPlayers());
        assertEq(32,RulesPresident.getNbMaxPlayers());
        assertNotSame(rules_,rulesTwo_);
    }
}