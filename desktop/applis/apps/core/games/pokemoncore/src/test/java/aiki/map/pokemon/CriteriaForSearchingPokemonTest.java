package aiki.map.pokemon;

import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;


public class CriteriaForSearchingPokemonTest extends InitializationDataBase {

    @Test
    public void matchItem1Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchItem(NULL_REF));
    }

    @Test
    public void matchItem2Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchItem("OBJECT"));
    }

    @Test
    public void matchItem3Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setWithItem(SelectedBoolean.YES);
        assertTrue(!criteria_.matchItem(NULL_REF));
    }

    @Test
    public void matchItem4Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setWithItem(SelectedBoolean.NO);
        assertTrue(criteria_.matchItem(NULL_REF));
    }

    @Test
    public void matchItem5Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeItem(SearchingMode.SUBSTRING);
        criteria_.setWithItem(SelectedBoolean.YES);
        assertTrue(criteria_.matchItem("OBJECT"));
    }

    @Test
    public void matchItem6Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeItem(SearchingMode.SUBSTRING);
        criteria_.setContentOfItem("OBJ");
        criteria_.setWithItem(SelectedBoolean.YES);
        assertTrue(criteria_.matchItem("OBJECT"));
    }

    @Test
    public void matchItem7Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeItem(SearchingMode.SUBSTRING);
        criteria_.setWithItem(SelectedBoolean.NO);
        assertTrue(!criteria_.matchItem("OBJECT"));
    }

    @Test
    public void matchName1Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchName(NULL_REF));
    }

    @Test
    public void matchName2Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchName("NAME"));
    }

    @Test
    public void matchName3Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfName("NA");
        assertTrue(!criteria_.matchName("NAME"));
    }

    @Test
    public void matchName4Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfName("NAME");
        assertTrue(criteria_.matchName("NAME"));
    }

    @Test
    public void matchAbility1Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchAbility(NULL_REF));
    }

    @Test
    public void matchAbility2Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchAbility("ABILITY"));
    }

    @Test
    public void matchAbility3Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeAbility(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfAbility("BIL");
        assertTrue(!criteria_.matchAbility("ABILITY"));
    }

    @Test
    public void matchAbility4Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeName(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfName("ABILITY");
        assertTrue(criteria_.matchAbility("ABILITY"));
    }

    @Test
    public void matchLevel1Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchLevel(0));
    }

    @Test
    public void matchLevel2Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchLevel(2));
    }

    @Test
    public void matchLevel3Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setMinLevel(3L);
        criteria_.setMaxLevel(5L);
        assertTrue(!criteria_.matchLevel(6));
    }

    @Test
    public void matchLevel4Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setMinLevel(3L);
        criteria_.setMaxLevel(5L);
        assertTrue(criteria_.matchLevel(4));
    }

    @Test
    public void matchGender1Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchGender(null));
    }

    @Test
    public void matchGender2Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchGender(Gender.FEMALE));
    }

    @Test
    public void matchGender3Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchGender(Gender.MALE));
    }

    @Test
    public void matchGender4Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchGender(Gender.NO_GENDER));
    }

    @Test
    public void matchGender5Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setGender(Gender.NO_GENDER);
        assertTrue(!criteria_.matchGender(Gender.FEMALE));
    }

    @Test
    public void matchGender6Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setGender(Gender.NO_GENDER);
        assertTrue(!criteria_.matchGender(Gender.MALE));
    }

    @Test
    public void matchGender7Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setGender(Gender.NO_GENDER);
        assertTrue(criteria_.matchGender(Gender.NO_GENDER));
    }

    @Test
    public void matchMoves1Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeMove(SearchingMode.WHOLE_STRING);
        assertTrue(criteria_.matchMoves(new StringList()));
    }

    @Test
    public void matchMoves2Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeMove(SearchingMode.WHOLE_STRING);
        assertTrue(criteria_.matchMoves(new StringList("STRING")));
    }

    @Test
    public void matchMoves3Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeMove(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfMove("STRING");
        assertTrue(criteria_.matchMoves(new StringList("STRING")));
    }

    @Test
    public void matchMoves4Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeMove(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfMove("STR");
        assertTrue(!criteria_.matchMoves(new StringList("STRING")));
    }

    @Test
    public void matchMoves5Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setSearchModeMove(SearchingMode.WHOLE_STRING);
        criteria_.setContentOfMove("STR");
        assertTrue(criteria_.matchMoves(new StringList("STRING","STR")));
    }

    @Test
    public void matchNbPossEvos1Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchNbPossEvos(0));
    }

    @Test
    public void matchNbPossEvos2Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        assertTrue(criteria_.matchNbPossEvos(2));
    }

    @Test
    public void matchNbPossEvos3Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setMinNbPossEvols(3L);
        criteria_.setMaxNbPossEvols(5L);
        assertTrue(!criteria_.matchNbPossEvos(6));
    }

    @Test
    public void matchNbPossEvos4Test() {
        CriteriaForSearchingPokemon criteria_ = new CriteriaForSearchingPokemon();
        criteria_.setMinNbPossEvols(3L);
        criteria_.setMaxNbPossEvols(5L);
        assertTrue(criteria_.matchNbPossEvos(4));
    }
}
