package aiki.beans.map.characters;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class PokemonTeamBeanTest extends InitDbCharacters {
    @Test
    public void getTeam1() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetTempTrainer());
    }
    @Test
    public void getTeam2() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetGymTrainer());
    }
    @Test
    public void getTeam3() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetGymLeader());
    }
    @Test
    public void getTeam4() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetTrainerLeague(0));
    }
    @Test
    public void getTeam5() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetTrainerLeague(1));
    }
    @Test
    public void getTeam6() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetMulti(0));
    }
    @Test
    public void getTeam7() {
        assertSizeEq(2,callPokemonTeamBeanTeamGetMulti(1));
    }
    @Test
    public void getImage1() {
        assertEq(one(IMG_16),callPokemonTeamBeanGetImageGymTrainer(0));
    }
    @Test
    public void getImage2() {
        assertEq(one(IMG_17),callPokemonTeamBeanGetImageGymTrainer(1));
    }
    @Test
    public void getImage3() {
        assertEq(one(IMG_14),callPokemonTeamBeanGetImageGymLeader(0));
    }
    @Test
    public void getImage4() {
        assertEq(one(IMG_15),callPokemonTeamBeanGetImageGymLeader(1));
    }
    @Test
    public void getImage5() {
        assertEq(one(IMG_14),callPokemonTeamBeanGetImageTempTrainer(0));
    }
    @Test
    public void getImage6() {
        assertEq(one(IMG_15),callPokemonTeamBeanGetImageTempTrainer(1));
    }
    @Test
    public void getImage7() {
        assertEq(one(IMG_12),callPokemonTeamBeanGetImageTrainerLeague(0,0));
    }
    @Test
    public void getImage8() {
        assertEq(one(IMG_13),callPokemonTeamBeanGetImageTrainerLeague(0,1));
    }
    @Test
    public void getImage9() {
        assertEq(one(IMG_20),callPokemonTeamBeanGetImageTrainerLeague(1,0));
    }
    @Test
    public void getImage10() {
        assertEq(one(IMG_21),callPokemonTeamBeanGetImageTrainerLeague(1,1));
    }
    @Test
    public void getImage11() {
        assertEq(one(IMG_08),callPokemonTeamBeanGetImageMulti(0,0));
    }
    @Test
    public void getImage12() {
        assertEq(one(IMG_09),callPokemonTeamBeanGetImageMulti(0,1));
    }
    @Test
    public void getImage13() {
        assertEq(one(IMG_10),callPokemonTeamBeanGetImageMulti(1,0));
    }
    @Test
    public void getImage14() {
        assertEq(one(IMG_11),callPokemonTeamBeanGetImageMulti(1,1));
    }
    @Test
    public void getGender() {
        assertEq(NO_G,callPokemonTeamBeanGetGender());
    }
    @Test
    public void getReward1() {
        assertEq(20,callPokemonTeamBeanRewardGet(0));
    }
    @Test
    public void getReward2() {
        assertEq(15,callPokemonTeamBeanRewardGet(1));
    }
    @Test
    public void getReward3() {
        assertEq(25,callPokemonTeamBeanRewardGet());
    }
    @Test
    public void getMultiplicity1() {
        assertEq(2, callPokemonTeamBeanMultiplicityGetMult());
    }
    @Test
    public void getMultiplicity2() {
        assertEq(1,callPokemonTeamBeanMultiplicityGet());
    }
    @Test
    public void getName1() {
        assertEq(P_POK_08_TR,callPokemonTeamBeanGetName(0,0));
    }
    @Test
    public void getName2() {
        assertEq(P_POK_09_TR,callPokemonTeamBeanGetName(0,1));
    }
    @Test
    public void getName3() {
        assertEq(P_POK_10_TR,callPokemonTeamBeanGetName(1,0));
    }
    @Test
    public void getName4() {
        assertEq(P_POK_11_TR,callPokemonTeamBeanGetName(1,1));
    }
    @Test
    public void getAbility1() {
        assertEq(A_ABILITY_TR,callPokemonTeamBeanGetAbility(0,0));
    }
    @Test
    public void getAbility2() {
        assertEq(A_ABILITY2_TR,callPokemonTeamBeanGetAbility(0,1));
    }
    @Test
    public void getAbility3() {
        assertEq(A_ABILITY2_TR,callPokemonTeamBeanGetAbility(1,0));
    }
    @Test
    public void getAbility4() {
        assertEq(A_ABILITY_TR,callPokemonTeamBeanGetAbility(1,1));
    }
    @Test
    public void getItem1() {
        assertEq(I_BALL_TR,callPokemonTeamBeanGetItem(0,0));
    }
    @Test
    public void getItem2() {
        assertEq(I_BERRY_TR,callPokemonTeamBeanGetItem(0,1));
    }
    @Test
    public void getItem3() {
        assertEq(NULL_REF,callPokemonTeamBeanGetItem(1,0));
    }
    @Test
    public void getItem4() {
        assertEq(NULL_REF,callPokemonTeamBeanGetItem(1,1));
    }
    @Test
    public void getMove1() {
        assertEq(M_POK_04_TR,callPokemonTeamBeanGetMove(0,0,0));
    }
    @Test
    public void getMove2() {
        assertEq(M_POK_05_TR,callPokemonTeamBeanGetMove(0,0,1));
    }
    @Test
    public void getMove3() {
        assertEq(M_POK_06_TR,callPokemonTeamBeanGetMove(0,1,0));
    }
    @Test
    public void getMove4() {
        assertEq(M_POK_07_TR,callPokemonTeamBeanGetMove(0,1,1));
    }
    @Test
    public void getMove5() {
        assertEq(M_POK_04_TR,callPokemonTeamBeanGetMove(1,0,0));
    }
    @Test
    public void getMove6() {
        assertEq(M_POK_05_TR,callPokemonTeamBeanGetMove(1,0,1));
    }
    @Test
    public void getMove7() {
        assertEq(M_POK_06_TR,callPokemonTeamBeanGetMove(1,1,0));
    }
    @Test
    public void getMove8() {
        assertEq(M_POK_07_TR,callPokemonTeamBeanGetMove(1,1,1));
    }
    @Test
    public void clickName1() {
        assertEq(P_POK_08,callPokemonTeamBeanClickNameId(0,0));
    }
    @Test
    public void clickName2() {
        assertEq(P_POK_09,callPokemonTeamBeanClickNameId(0,1));
    }
    @Test
    public void clickName3() {
        assertEq(P_POK_10,callPokemonTeamBeanClickNameId(1,0));
    }
    @Test
    public void clickName4() {
        assertEq(P_POK_11,callPokemonTeamBeanClickNameId(1,1));
    }
    @Test
    public void clickName5() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callPokemonTeamBeanClickName(0,0));
    }
    @Test
    public void clickName6() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callPokemonTeamBeanClickName(0,1));
    }
    @Test
    public void clickName7() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callPokemonTeamBeanClickName(1,0));
    }
    @Test
    public void clickName8() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callPokemonTeamBeanClickName(1,1));
    }
    @Test
    public void clickAbility1() {
        assertEq(A_ABILITY,callPokemonTeamBeanClickAbilityId(0,0));
    }
    @Test
    public void clickAbility2() {
        assertEq(A_ABILITY2,callPokemonTeamBeanClickAbilityId(0,1));
    }
    @Test
    public void clickAbility3() {
        assertEq(A_ABILITY2,callPokemonTeamBeanClickAbilityId(1,0));
    }
    @Test
    public void clickAbility4() {
        assertEq(A_ABILITY,callPokemonTeamBeanClickAbilityId(1,1));
    }
    @Test
    public void clickAbility5() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callPokemonTeamBeanClickAbility(0,0));
    }
    @Test
    public void clickAbility6() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callPokemonTeamBeanClickAbility(0,1));
    }
    @Test
    public void clickAbility7() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callPokemonTeamBeanClickAbility(1,0));
    }
    @Test
    public void clickAbility8() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callPokemonTeamBeanClickAbility(1,1));
    }
    @Test
    public void clickItem1() {
        assertEq(I_BALL,callPokemonTeamBeanClickItemId(0,0));
    }
    @Test
    public void clickItem2() {
        assertEq(I_BERRY,callPokemonTeamBeanClickItemId(0,1));
    }
    @Test
    public void clickItem3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,callPokemonTeamBeanClickItem(0,0));
    }
    @Test
    public void clickItem4() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML,callPokemonTeamBeanClickItem(0,1));
    }
    @Test
    public void clickMove1() {
        assertEq(M_POK_04,callPokemonTeamBeanClickMoveId(0,0,0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_POK_05,callPokemonTeamBeanClickMoveId(0,0,1));
    }
    @Test
    public void clickMove3() {
        assertEq(M_POK_06,callPokemonTeamBeanClickMoveId(0,1,0));
    }
    @Test
    public void clickMove4() {
        assertEq(M_POK_07,callPokemonTeamBeanClickMoveId(0,1,1));
    }
    @Test
    public void clickMove5() {
        assertEq(M_POK_04,callPokemonTeamBeanClickMoveId(1,0,0));
    }
    @Test
    public void clickMove6() {
        assertEq(M_POK_05,callPokemonTeamBeanClickMoveId(1,0,1));
    }
    @Test
    public void clickMove7() {
        assertEq(M_POK_06,callPokemonTeamBeanClickMoveId(1,1,0));
    }
    @Test
    public void clickMove8() {
        assertEq(M_POK_07,callPokemonTeamBeanClickMoveId(1,1,1));
    }
    @Test
    public void clickMove9() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(0,0,0));
    }
    @Test
    public void clickMove10() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(0,0,1));
    }
    @Test
    public void clickMove11() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(0,1,0));
    }
    @Test
    public void clickMove12() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(0,1,1));
    }
    @Test
    public void clickMove13() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(1,0,0));
    }
    @Test
    public void clickMove14() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(1,0,1));
    }
    @Test
    public void clickMove15() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(1,1,0));
    }
    @Test
    public void clickMove16() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonTeamBeanClickMove(1,1,1));
    }
}
