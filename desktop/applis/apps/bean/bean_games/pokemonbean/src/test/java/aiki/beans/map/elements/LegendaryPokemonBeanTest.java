package aiki.beans.map.elements;

import aiki.beans.map.InitDbLegPk;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class LegendaryPokemonBeanTest extends InitDbLegPk {
    @Test
    public void getImage1() {
        assertEq(one(IMG_18),callLegendaryPokemonBeanGetImage(0));
    }
    @Test
    public void getImage2() {
        assertEq(one(IMG_19),callLegendaryPokemonBeanGetImage(1));
    }
    @Test
    public void getName1() {
        assertEq(P_POK_18_TR,callLegendaryPokemonBeanGetName(0));
    }
    @Test
    public void getName2() {
        assertEq(P_POK_19_TR,callLegendaryPokemonBeanGetName(1));
    }
    @Test
    public void getWpLevel1() {
        assertEq(4,callWildPkGetLevel(callLegendaryPokemonBeanPokemonGet(0)));
    }
    @Test
    public void getWpLevel2() {
        assertEq(4,callWildPkGetLevel(callLegendaryPokemonBeanPokemonGet(1)));
    }
    @Test
    public void getLevel1() {
        assertEq(4,callLegendaryPokemonBeanGetLevel(0));
    }
    @Test
    public void getLevel2() {
        assertEq(4,callLegendaryPokemonBeanGetLevel(1));
    }
    @Test
    public void getAbility1() {
        assertEq(A_ABILITY2_TR,callLegendaryPokemonBeanGetAbility(0));
    }
    @Test
    public void getAbility2() {
        assertEq(A_ABILITY_TR,callLegendaryPokemonBeanGetAbility(1));
    }
    @Test
    public void getItem1() {
        assertEq(I_REPEL_TR,callLegendaryPokemonBeanGetItem(0));
    }
    @Test
    public void getItem2() {
        assertEq(I_SELLING_TR,callLegendaryPokemonBeanGetItem(1));
    }
    @Test
    public void getMovesAtLevel1() {
        assertSizeEq(2,callLegendaryPokemonBeanGetMovesAtLevel(0));
    }
    @Test
    public void getMovesAtLevel2() {
        assertSizeEq(2,callLegendaryPokemonBeanGetMovesAtLevel(1));
    }
    @Test
    public void getMove1() {
        assertEq(M_POK_00_TR,callLegendaryPokemonBeanGetMove(0,0));
    }
    @Test
    public void getMove2() {
        assertEq(M_POK_01_TR,callLegendaryPokemonBeanGetMove(0,1));
    }
    @Test
    public void getMove3() {
        assertEq(M_POK_02_TR,callLegendaryPokemonBeanGetMove(1,0));
    }
    @Test
    public void getMove4() {
        assertEq(M_POK_03_TR,callLegendaryPokemonBeanGetMove(1,1));
    }
    @Test
    public void getGender1() {
        assertEq(NO_G,callLegendaryPokemonBeanGetGender(0));
    }
    @Test
    public void getGender2() {
        assertEq(NO_G,callLegendaryPokemonBeanGetGender(1));
    }
    @Test
    public void clickName1() {
        assertEq(P_POK_18,callLegendaryPokemonBeanClickNameId(0));
    }
    @Test
    public void clickName2() {
        assertEq(P_POK_19,callLegendaryPokemonBeanClickNameId(1));
    }
    @Test
    public void clickMove1() {
        assertEq(M_POK_00,callLegendaryPokemonBeanClickMoveId(0,0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_POK_01,callLegendaryPokemonBeanClickMoveId(0,1));
    }
    @Test
    public void clickMove3() {
        assertEq(M_POK_02,callLegendaryPokemonBeanClickMoveId(1,0));
    }
    @Test
    public void clickMove4() {
        assertEq(M_POK_03,callLegendaryPokemonBeanClickMoveId(1,1));
    }@Test
    public void clickAbility1() {
        assertEq(A_ABILITY2,callLegendaryPokemonBeanClickAbilityId(0));
    }
    @Test
    public void clickAbility2() {
        assertEq(A_ABILITY,callLegendaryPokemonBeanClickAbilityId(1));
    }
    @Test
    public void clickItem1() {
        assertEq(I_REPEL,callLegendaryPokemonBeanClickItemId(0));
    }
    @Test
    public void clickItem2() {
        assertEq(I_SELLING,callLegendaryPokemonBeanClickItemId(1));
    }
    @Test
    public void clickItem3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_REPEL_HTML,callLegendaryPokemonBeanClickItem(0));
    }
    @Test
    public void clickItem4() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_SELLINGITEM_HTML,callLegendaryPokemonBeanClickItem(1));
    }
}
