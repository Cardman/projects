package aiki.beans.map.characters;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class AllyBeanTest extends InitDbCharacters {
    @Test
    public void getTeam1() {
        assertSizeEq(2,callAllyBeanTeamGet());
    }
    @Test
    public void getTeam2() {
        assertEq(18,callPkTrainerGetLevel(elt(callAllyBeanTeamGet(),0)));
    }
    @Test
    public void getTeam3() {
        assertEq(I_HEAL_HP_STATUS,callPkTrainerGetItem(elt(callAllyBeanTeamGet(),0)));
    }
    @Test
    public void getTeam4() {
        assertSizeEq(2,callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),0)));
    }
    @Test
    public void getTeam5() {
        assertEq(M_POK_04,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),0)),0));
    }
    @Test
    public void getTeam6() {
        assertEq(M_POK_05,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),0)),1));
    }
    @Test
    public void getTeam7() {
        assertEq(19,callPkTrainerGetLevel(elt(callAllyBeanTeamGet(),1)));
    }
    @Test
    public void getTeam8() {
        assertEq(I_HEAL_PP,callPkTrainerGetItem(elt(callAllyBeanTeamGet(),1)));
    }
    @Test
    public void getTeam9() {
        assertSizeEq(2,callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),1)));
    }
    @Test
    public void getTeam10() {
        assertEq(M_POK_06,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),1)),0));
    }
    @Test
    public void getTeam11() {
        assertEq(M_POK_07,elt(callPkTrainerGetMoves(elt(callAllyBeanTeamGet(),1)),1));
    }
    @Test
    public void getImage1() {
        assertEq(one(IMG_16),callAllyBeanGetImage(0));
    }
    @Test
    public void getImage2() {
        assertEq(one(IMG_17),callAllyBeanGetImage(1));
    }
    @Test
    public void getName1() {
        assertEq(P_POK_16_TR,callAllyBeanGetName(0));
    }
    @Test
    public void getName2() {
        assertEq(P_POK_17_TR,callAllyBeanGetName(1));
    }
    @Test
    public void getAbility1() {
        assertEq(A_ABILITY_TR,callAllyBeanGetAbility(0));
    }
    @Test
    public void getAbility2() {
        assertEq(A_ABILITY2_TR,callAllyBeanGetAbility(1));
    }
    @Test
    public void getItem1() {
        assertEq(I_HEAL_HP_STATUS_TR,callAllyBeanGetItem(0));
    }
    @Test
    public void getItem2() {
        assertEq(I_HEAL_PP_TR,callAllyBeanGetItem(1));
    }
    @Test
    public void getMove1() {
        assertEq(M_POK_04_TR,callAllyBeanGetMove(0,0));
    }
    @Test
    public void getMove2() {
        assertEq(M_POK_05_TR,callAllyBeanGetMove(0,1));
    }
    @Test
    public void getMove3() {
        assertEq(M_POK_06_TR,callAllyBeanGetMove(1,0));
    }
    @Test
    public void getMove4() {
        assertEq(M_POK_07_TR,callAllyBeanGetMove(1,1));
    }
    @Test
    public void getGender() {
        assertEq(NO_G,callAllyBeanGetGender());
    }
    @Test
    public void clickName1() {
        assertEq(P_POK_16,callAllyBeanClickNameId(0));
    }
    @Test
    public void clickName2() {
        assertEq(P_POK_17,callAllyBeanClickNameId(1));
    }
    @Test
    public void clickAbility1() {
        assertEq(A_ABILITY,callAllyBeanClickAbilityId(0));
    }
    @Test
    public void clickAbility2() {
        assertEq(A_ABILITY2,callAllyBeanClickAbilityId(1));
    }
    @Test
    public void clickItem1() {
        assertEq(I_HEAL_HP_STATUS,callAllyBeanClickItemId(0));
    }
    @Test
    public void clickItem2() {
        assertEq(I_HEAL_PP,callAllyBeanClickItemId(1));
    }
    @Test
    public void clickMove1() {
        assertEq(M_POK_04,callAllyBeanClickMoveId(0,0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_POK_05,callAllyBeanClickMoveId(0,1));
    }
    @Test
    public void clickMove3() {
        assertEq(M_POK_06,callAllyBeanClickMoveId(1,0));
    }
    @Test
    public void clickMove4() {
        assertEq(M_POK_07,callAllyBeanClickMoveId(1,1));
    }
    @Test
    public void clickName3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callAllyBeanClickName(0));
    }
    @Test
    public void clickName4() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callAllyBeanClickName(1));
    }
    @Test
    public void clickAbility3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callAllyBeanClickAbility(0));
    }
    @Test
    public void clickAbility4() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callAllyBeanClickAbility(1));
    }
    @Test
    public void clickItem3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGHPSTATUS_HTML,callAllyBeanClickItem(0));
    }
    @Test
    public void clickItem4() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGPP_HTML,callAllyBeanClickItem(1));
    }
    @Test
    public void clickMove5() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAllyBeanClickMove(0,0));
    }
    @Test
    public void clickMove6() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAllyBeanClickMove(0,1));
    }
    @Test
    public void clickMove7() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAllyBeanClickMove(1,0));
    }
    @Test
    public void clickMove8() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callAllyBeanClickMove(1,1));
    }
}
