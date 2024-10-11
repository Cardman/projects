package aiki.beans.map.elements;

import aiki.beans.map.InitDbArea;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class AreaBeanTest extends InitDbArea {
    @Test
    public void getAvgNbSteps1() {
        assertEq(5,callAreaApparitionGetAvgNbSteps(0));
    }
    @Test
    public void getAvgNbSteps2() {
        assertEq(10,callAreaApparitionGetAvgNbSteps(1));
    }
    @Test
    public void getWildPokemon1() {
        assertSizeEq(2,callAreaApparitionGetWildPokemon(0));
    }
    @Test
    public void getWildPokemon2() {
        assertSizeEq(2,callAreaApparitionGetWildPokemon(1));
    }
    @Test
    public void getWildPokemonFishing1() {
        assertSizeEq(2,callAreaApparitionGetWildPokemonFishing(0));
    }
    @Test
    public void getWildPokemonFishing2() {
        assertSizeEq(2,callAreaApparitionGetWildPokemonFishing(1));
    }
    @Test
    public void getWpLevel1() {
        assertEq(4,callWildPkGetLevel(elt(callAreaApparitionGetWildPokemon(0),0)));
    }
    @Test
    public void getWpLevel2() {
        assertEq(4,callWildPkGetLevel(elt(callAreaApparitionGetWildPokemon(1),0)));
    }
    @Test
    public void getWpItem1() {
        assertEq(I_FOSSIL,callWildPkGetItem(elt(callAreaApparitionGetWildPokemon(0),0)));
    }
    @Test
    public void getWpItem2() {
        assertEq(I_HEAL_STATUS,callWildPkGetItem(elt(callAreaApparitionGetWildPokemon(1),1)));
    }
    @Test
    public void getWpItem3() {
        assertEq(I_EVO_ITEM,callWildPkGetItem(elt(callAreaApparitionGetWildPokemonFishing(0),0)));
    }
    @Test
    public void getWpItem4() {
        assertEq(I_EVO_STONE,callWildPkGetItem(elt(callAreaApparitionGetWildPokemonFishing(1),1)));
    }
    @Test
    public void getImage1() {
        assertEq(one(IMG_00),callAreaBeanGetImage(0,0));
    }
    @Test
    public void getImage2() {
        assertEq(one(IMG_01),callAreaBeanGetImage(0,1));
    }
    @Test
    public void getImage3() {
        assertEq(one(IMG_04),callAreaBeanGetImage(1,0));
    }
    @Test
    public void getImage4() {
        assertEq(one(IMG_05),callAreaBeanGetImage(1,1));
    }
    @Test
    public void getImageFishing1() {
        assertEq(one(IMG_02),callAreaBeanGetImageFishing(0,0));
    }
    @Test
    public void getImageFishing2() {
        assertEq(one(IMG_03),callAreaBeanGetImageFishing(0,1));
    }
    @Test
    public void getImageFishing3() {
        assertEq(one(IMG_06),callAreaBeanGetImageFishing(1,0));
    }
    @Test
    public void getImageFishing4() {
        assertEq(one(IMG_07),callAreaBeanGetImageFishing(1,1));
    }
    @Test
    public void getName1() {
        assertEq(P_POK_00_TR,callAreaBeanGetName(0,0));
    }
    @Test
    public void getName2() {
        assertEq(P_POK_01_TR,callAreaBeanGetName(0,1));
    }
    @Test
    public void getName3() {
        assertEq(P_POK_04_TR,callAreaBeanGetName(1,0));
    }
    @Test
    public void getName4() {
        assertEq(P_POK_05_TR,callAreaBeanGetName(1,1));
    }
    @Test
    public void getNameFishing1() {
        assertEq(P_POK_02_TR,callAreaBeanGetNameFishing(0,0));
    }
    @Test
    public void getNameFishing2() {
        assertEq(P_POK_03_TR,callAreaBeanGetNameFishing(0,1));
    }
    @Test
    public void getNameFishing3() {
        assertEq(P_POK_06_TR,callAreaBeanGetNameFishing(1,0));
    }
    @Test
    public void getNameFishing4() {
        assertEq(P_POK_07_TR,callAreaBeanGetNameFishing(1,1));
    }
    @Test
    public void getItem1() {
        assertEq(I_FOSSIL_TR,callAreaBeanGetItem(0,0));
    }
    @Test
    public void getItem2() {
        assertEq(I_HEAL_STATUS_TR,callAreaBeanGetItem(0,1));
    }
    @Test
    public void getItem3() {
        assertEq(I_FOSSIL_TR,callAreaBeanGetItem(1,0));
    }
    @Test
    public void getItem4() {
        assertEq(I_HEAL_STATUS_TR,callAreaBeanGetItem(1,1));
    }
    @Test
    public void getItemFishing1() {
        assertEq(I_EVO_ITEM_TR,callAreaBeanGetItemFishing(0,0));
    }
    @Test
    public void getItemFishing2() {
        assertEq(I_EVO_STONE_TR,callAreaBeanGetItemFishing(0,1));
    }
    @Test
    public void getItemFishing3() {
        assertEq(I_EVO_ITEM_TR,callAreaBeanGetItemFishing(1,0));
    }
    @Test
    public void getItemFishing4() {
        assertEq(I_EVO_STONE_TR,callAreaBeanGetItemFishing(1,1));
    }
    @Test
    public void getAbility1() {
        assertEq(A_ABILITY_TR,callAreaBeanGetAbility(0,0));
    }
    @Test
    public void getAbility2() {
        assertEq(A_ABILITY2_TR,callAreaBeanGetAbility(0,1));
    }
    @Test
    public void getAbility3() {
        assertEq(A_ABILITY_TR,callAreaBeanGetAbility(1,0));
    }
    @Test
    public void getAbility4() {
        assertEq(A_ABILITY2_TR,callAreaBeanGetAbility(1,1));
    }
    @Test
    public void getAbilityFishing1() {
        assertEq(A_ABILITY_TR,callAreaBeanGetAbilityFishing(0,0));
    }
    @Test
    public void getAbilityFishing2() {
        assertEq(A_ABILITY2_TR,callAreaBeanGetAbilityFishing(0,1));
    }
    @Test
    public void getAbilityFishing3() {
        assertEq(A_ABILITY_TR,callAreaBeanGetAbilityFishing(1,0));
    }
    @Test
    public void getAbilityFishing4() {
        assertEq(A_ABILITY2_TR,callAreaBeanGetAbilityFishing(1,1));
    }
    @Test
    public void getGender1() {
        assertEq(NO_G,callAreaBeanGetGender(0,0));
    }
    @Test
    public void getGender2() {
        assertEq(NO_G,callAreaBeanGetGender(0,1));
    }
    @Test
    public void getGender3() {
        assertEq(NO_G,callAreaBeanGetGender(1,0));
    }
    @Test
    public void getGender4() {
        assertEq(NO_G,callAreaBeanGetGender(1,1));
    }
    @Test
    public void getGenderFishing1() {
        assertEq(NO_G,callAreaBeanGetGenderFishing(0,0));
    }
    @Test
    public void getGenderFishing2() {
        assertEq(NO_G,callAreaBeanGetGenderFishing(0,1));
    }
    @Test
    public void getGenderFishing3() {
        assertEq(NO_G,callAreaBeanGetGenderFishing(1,0));
    }
    @Test
    public void getGenderFishing4() {
        assertEq(NO_G,callAreaBeanGetGenderFishing(1,1));
    }

    @Test
    public void getMovesAtLevel1() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevel(0,0));
    }
    @Test
    public void getMovesAtLevel2() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevel(0,1));
    }
    @Test
    public void getMovesAtLevel3() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevel(1,0));
    }
    @Test
    public void getMovesAtLevel4() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevel(1,1));
    }
    @Test
    public void getMovesAtLevelFishing1() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevelFishing(0,0));
    }
    @Test
    public void getMovesAtLevelFishing2() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevelFishing(0,1));
    }
    @Test
    public void getMovesAtLevelFishing3() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevelFishing(1,0));
    }
    @Test
    public void getMovesAtLevelFishing4() {
        assertSizeEq(2,callAreaBeanGetMovesAtLevelFishing(1,1));
    }
    @Test
    public void getMove1() {
        assertEq(M_POK_00_TR,callAreaBeanGetMove(0,0,0));
    }
    @Test
    public void getMove2() {
        assertEq(M_POK_01_TR,callAreaBeanGetMove(0,0,1));
    }
    @Test
    public void getMove3() {
        assertEq(M_POK_02_TR,callAreaBeanGetMove(0,1,0));
    }
    @Test
    public void getMove4() {
        assertEq(M_POK_03_TR,callAreaBeanGetMove(0,1,1));
    }
    @Test
    public void getMove5() {
        assertEq(M_POK_00_TR,callAreaBeanGetMove(1,0,0));
    }
    @Test
    public void getMove6() {
        assertEq(M_POK_01_TR,callAreaBeanGetMove(1,0,1));
    }
    @Test
    public void getMove7() {
        assertEq(M_POK_02_TR,callAreaBeanGetMove(1,1,0));
    }
    @Test
    public void getMove8() {
        assertEq(M_POK_03_TR,callAreaBeanGetMove(1,1,1));
    }
    @Test
    public void getMoveFishing1() {
        assertEq(M_POK_00_TR,callAreaBeanGetMoveFishing(0,0,0));
    }
    @Test
    public void getMoveFishing2() {
        assertEq(M_POK_01_TR,callAreaBeanGetMoveFishing(0,0,1));
    }
    @Test
    public void getMoveFishing3() {
        assertEq(M_POK_02_TR,callAreaBeanGetMoveFishing(0,1,0));
    }
    @Test
    public void getMoveFishing4() {
        assertEq(M_POK_03_TR,callAreaBeanGetMoveFishing(0,1,1));
    }
    @Test
    public void getMoveFishing5() {
        assertEq(M_POK_00_TR,callAreaBeanGetMoveFishing(1,0,0));
    }
    @Test
    public void getMoveFishing6() {
        assertEq(M_POK_01_TR,callAreaBeanGetMoveFishing(1,0,1));
    }
    @Test
    public void getMoveFishing7() {
        assertEq(M_POK_02_TR,callAreaBeanGetMoveFishing(1,1,0));
    }
    @Test
    public void getMoveFishing8() {
        assertEq(M_POK_03_TR,callAreaBeanGetMoveFishing(1,1,1));
    }
    @Test
    public void clickName1() {
        assertEq(P_POK_00,callAreaBeanClickNameId(0,0));
    }
    @Test
    public void clickName2() {
        assertEq(P_POK_01,callAreaBeanClickNameId(0,1));
    }
    @Test
    public void clickName3() {
        assertEq(P_POK_04,callAreaBeanClickNameId(1,0));
    }
    @Test
    public void clickName4() {
        assertEq(P_POK_05,callAreaBeanClickNameId(1,1));
    }
    @Test
    public void clickNameFishing1() {
        assertEq(P_POK_02,callAreaBeanClickNameFishingId(0,0));
    }
    @Test
    public void clickNameFishing2() {
        assertEq(P_POK_03,callAreaBeanClickNameFishingId(0,1));
    }
    @Test
    public void clickNameFishing3() {
        assertEq(P_POK_06,callAreaBeanClickNameFishingId(1,0));
    }
    @Test
    public void clickNameFishing4() {
        assertEq(P_POK_07,callAreaBeanClickNameFishingId(1,1));
    }
    @Test
    public void clickMove1() {
        assertEq(M_POK_00,callAreaBeanClickMoveId(0,0,0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_POK_01,callAreaBeanClickMoveId(0,0,1));
    }
    @Test
    public void clickMove3() {
        assertEq(M_POK_02,callAreaBeanClickMoveId(0,1,0));
    }
    @Test
    public void clickMove4() {
        assertEq(M_POK_03,callAreaBeanClickMoveId(0,1,1));
    }
    @Test
    public void clickMove5() {
        assertEq(M_POK_00,callAreaBeanClickMoveId(1,0,0));
    }
    @Test
    public void clickMove6() {
        assertEq(M_POK_01,callAreaBeanClickMoveId(1,0,1));
    }
    @Test
    public void clickMove7() {
        assertEq(M_POK_02,callAreaBeanClickMoveId(1,1,0));
    }
    @Test
    public void clickMove8() {
        assertEq(M_POK_03,callAreaBeanClickMoveId(1,1,1));
    }
    @Test
    public void clickMoveFishing1() {
        assertEq(M_POK_00,callAreaBeanClickMoveFishingId(0,0,0));
    }
    @Test
    public void clickMoveFishing2() {
        assertEq(M_POK_01,callAreaBeanClickMoveFishingId(0,0,1));
    }
    @Test
    public void clickMoveFishing3() {
        assertEq(M_POK_02,callAreaBeanClickMoveFishingId(0,1,0));
    }
    @Test
    public void clickMoveFishing4() {
        assertEq(M_POK_03,callAreaBeanClickMoveFishingId(0,1,1));
    }
    @Test
    public void clickMoveFishing5() {
        assertEq(M_POK_00,callAreaBeanClickMoveFishingId(1,0,0));
    }
    @Test
    public void clickMoveFishing6() {
        assertEq(M_POK_01,callAreaBeanClickMoveFishingId(1,0,1));
    }
    @Test
    public void clickMoveFishing7() {
        assertEq(M_POK_02,callAreaBeanClickMoveFishingId(1,1,0));
    }
    @Test
    public void clickMoveFishing8() {
        assertEq(M_POK_03,callAreaBeanClickMoveFishingId(1,1,1));
    }
    @Test
    public void clickAbility1() {
        assertEq(A_ABILITY,callAreaBeanClickAbilityId(0,0));
    }
    @Test
    public void clickAbility2() {
        assertEq(A_ABILITY2,callAreaBeanClickAbilityId(0,1));
    }
    @Test
    public void clickAbility3() {
        assertEq(A_ABILITY,callAreaBeanClickAbilityId(1,0));
    }
    @Test
    public void clickAbility4() {
        assertEq(A_ABILITY2,callAreaBeanClickAbilityId(1,1));
    }
    @Test
    public void clickAbilityFishing1() {
        assertEq(A_ABILITY,callAreaBeanClickAbilityFishingId(0,0));
    }
    @Test
    public void clickAbilityFishing2() {
        assertEq(A_ABILITY2,callAreaBeanClickAbilityFishingId(0,1));
    }
    @Test
    public void clickAbilityFishing3() {
        assertEq(A_ABILITY,callAreaBeanClickAbilityFishingId(1,0));
    }
    @Test
    public void clickAbilityFishing4() {
        assertEq(A_ABILITY2,callAreaBeanClickAbilityFishingId(1,1));
    }
    @Test
    public void clickItem1() {
        assertEq(I_FOSSIL,callAreaBeanClickItemId(0,0));
    }
    @Test
    public void clickItem2() {
        assertEq(I_HEAL_STATUS,callAreaBeanClickItemId(0,1));
    }
    @Test
    public void clickItem3() {
        assertEq(I_FOSSIL,callAreaBeanClickItemId(1,0));
    }
    @Test
    public void clickItem4() {
        assertEq(I_HEAL_STATUS,callAreaBeanClickItemId(1,1));
    }
    @Test
    public void clickItemFishing1() {
        assertEq(I_EVO_ITEM,callAreaBeanClickItemFishingId(0,0));
    }
    @Test
    public void clickItemFishing2() {
        assertEq(I_EVO_STONE,callAreaBeanClickItemFishingId(0,1));
    }
    @Test
    public void clickItemFishing3() {
        assertEq(I_EVO_ITEM,callAreaBeanClickItemFishingId(1,0));
    }
    @Test
    public void clickItemFishing4() {
        assertEq(I_EVO_STONE,callAreaBeanClickItemFishingId(1,1));
    }
    @Test
    public void clickItem5() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML,callAreaBeanClickItem(0,0));
    }
    @Test
    public void clickItem6() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML,callAreaBeanClickItem(0,1));
    }
    @Test
    public void clickItem7() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_FOSSIL_HTML,callAreaBeanClickItem(1,0));
    }
    @Test
    public void clickItem8() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML,callAreaBeanClickItem(1,1));
    }
    @Test
    public void clickItemFishing5() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML,callAreaBeanClickItemFishing(0,0));
    }
    @Test
    public void clickItemFishing6() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML,callAreaBeanClickItemFishing(0,1));
    }
    @Test
    public void clickItemFishing7() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML,callAreaBeanClickItemFishing(1,0));
    }
    @Test
    public void clickItemFishing8() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML,callAreaBeanClickItemFishing(1,1));
    }
}
