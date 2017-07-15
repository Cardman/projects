package aiki.map.pokemon;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import code.util.StringList;
import code.util.pagination.SearchingMode;
import code.util.pagination.SelectedBoolean;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.CriteriaForSearchingMove;

@SuppressWarnings("static-method")
public class CriteriaForSearchingMoveTest extends InitializationDataBase {

    @BeforeClass
    public static void initDataBase() {
        InitializationDataBase.initDataBase();
    }

    @Test
    public void matchName1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setContentOfName("ROLLOUT");
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        assertTrue(criteria_.matchName("ROLLOUT"));
    }

    @Test
    public void matchTypes1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setContentOfType("WATER");
        criteria_.setSearchModeType(SearchingMode.WHOLE_STRING);
        assertTrue(criteria_.matchTypes(new StringList("WATER","FIRE","GRASS")));
    }

    @Test
    public void matchPrice1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setMinPrice(0);
        criteria_.setMaxPrice(2);
        assertTrue(criteria_.matchPrice(1));
    }

    @Test
    public void matchPp1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setMinPp(0);
        criteria_.setMaxPp(2);
        assertTrue(criteria_.matchPp(1));
    }

    @Test
    public void matchPrio1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setMinPrio(0);
        criteria_.setMaxPrio(2);
        assertTrue(criteria_.matchPrio(1));
    }

    @Test
    public void matchDisappearingRound1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setDisappearingRound(SelectedBoolean.YES);
        assertTrue(criteria_.matchDisappearingRound(true));
    }

    @Test
    public void matchConstUserChoice1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setConstUserChoice(SelectedBoolean.NO);
        assertTrue(criteria_.matchConstUserChoice(false));
    }

    @Test
    public void matchRechargeRound1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setRechargeRound(SelectedBoolean.NO);
        assertTrue(!criteria_.matchRechargeRound(true));
    }

    @Test
    public void matchTechnicalMove1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setTechnicalMove(SelectedBoolean.YES);
        assertTrue(!criteria_.matchTechnicalMove(false));
    }

    @Test
    public void matchSwitchType1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setSwitchType(SwitchType.LANCEUR);
        assertTrue(criteria_.matchSwitchType(SwitchType.LANCEUR));
    }

    @Test
    public void matchTargetChoice1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setTargetChoice(TargetChoice.TOUS_ADV);
        assertTrue(!criteria_.matchTargetChoice(TargetChoice.ANY_FOE));
    }

    @Test
    public void matchClass1Test() {
        CriteriaForSearchingMove criteria_;
        criteria_ = new CriteriaForSearchingMove();
        criteria_.setSelectedClass(DamagingMoveData.MOVE);
        assertTrue(criteria_.matchClass(_data_.getMove(BATAILLE)));
        assertTrue(!criteria_.matchClass(_data_.getMove(RELAIS)));
        criteria_.setSelectedClass(StatusMoveData.MOVE);
        assertTrue(!criteria_.matchClass(_data_.getMove(BATAILLE)));
        assertTrue(criteria_.matchClass(_data_.getMove(RELAIS)));
    }
}
