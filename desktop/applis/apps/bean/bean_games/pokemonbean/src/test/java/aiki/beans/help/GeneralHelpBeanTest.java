package aiki.beans.help;

import aiki.beans.abilities.AikiBeansAbilitiesStd;
import aiki.beans.items.AikiBeansItemsStd;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.pokemon.AikiBeansPokemonStd;
import code.maths.Rate;
import org.junit.Test;

public final class GeneralHelpBeanTest extends InitDbGeneralHelp {
    @Test
    public void firstGeneralHelpHasItem1() {
        assertFalse(callGeneralHelpBeanFirstPokemonHasNoItem());
    }
    @Test
    public void firstGeneralHelpHasItem2() {
        assertTrue(callGeneralHelpBeanFirstPokemonHasItem());
    }
    @Test
    public void getTmPrice1() {
        assertEq("1",callGeneralHelpBeanGetTmPrice(0));
    }
    @Test
    public void getTmPrice2() {
        assertEq(NULL_REF,callGeneralHelpBeanGetTmPrice(1));
    }
    @Test
    public void getMapWidth() {
        assertEq(2,callGeneralHelpBeanGetMapWidth());
    }
    @Test
    public void getMiniMap1() {
        assertSizeEq(4,callGeneralHelpBeanMiniMapGet());
    }
    @Test
    public void getMiniMap2() {
        assertEq("AAACXXXXCAAADAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),0)));
    }
    @Test
    public void getMiniMap3() {
        assertEq("AAACXXXXCAAAFAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),1)));
    }
    @Test
    public void getMiniMap4() {
        assertEq("AAACXXXXCAAAEAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),2)));
    }
    @Test
    public void getMiniMap5() {
        assertEq("AAACXXXXCAAAGAAA////",second(elt(callGeneralHelpBeanMiniMapGet(),3)));
    }
    @Test
    public void getMiniMapImage1() {
        assertEq("AAACXXXXCAAADAAA////",callGeneralHelpBeanGetMiniMapImage(0));
    }
    @Test
    public void getMiniMapImage2() {
        assertEq("AAACXXXXCAAAFAAA////",callGeneralHelpBeanGetMiniMapImage(1));
    }
    @Test
    public void getMiniMapImage3() {
        assertEq("AAACXXXXCAAAEAAA////",callGeneralHelpBeanGetMiniMapImage(2));
    }
    @Test
    public void getMiniMapImage4() {
        assertEq("AAACXXXXCAAAGAAA////",callGeneralHelpBeanGetMiniMapImage(3));
    }
    @Test
    public void getPlaceName1() {
        assertEq(NULL_REF,callGeneralHelpBeanGetPlaceName(0));
    }
    @Test
    public void getPlaceName2() {
        assertEq(CITY,callGeneralHelpBeanGetPlaceName(1));
    }
    @Test
    public void getPlaceName3() {
        assertEq(ROAD,callGeneralHelpBeanGetPlaceName(2));
    }
    @Test
    public void getPlaceName4() {
        assertEq(CAVE,callGeneralHelpBeanGetPlaceName(3));
    }
    @Test
    public void getBegin() {
        assertEq(ROAD,callGeneralHelpBeanBeginGet());
    }
    @Test
    public void getUnlockedCity() {
        assertEq("AAACXXXXCAAAHAAA////",callGeneralHelpBeanUnlockedCityGet());
    }
    @Test
    public void getPkName() {
        assertEq(P_POK_00_TR,callGeneralHelpBeanGetName());
    }
    @Test
    public void clickPkName1() {
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,callGeneralHelpBeanClickName());
    }
    @Test
    public void clickPkName2() {
        assertEq(P_POK_00,callGeneralHelpBeanClickNameId());
    }
    @Test
    public void getPkItem() {
        assertEq(I_BALL_TR,callGeneralHelpBeanGetItem());
    }
    @Test
    public void clickPkItem1() {
        assertEq(AikiBeansItemsStd.WEB_HTML_ITEMS_BALL_HTML,callGeneralHelpBeanClickItem());
    }
    @Test
    public void clickPkItem2() {
        assertEq(I_BALL,callGeneralHelpBeanClickItemId());
    }
    @Test
    public void getPkAbility() {
        assertEq(A_ABILITY_TR,callGeneralHelpBeanGetAbility());
    }
    @Test
    public void clickPkAbility1() {
        assertEq(AikiBeansAbilitiesStd.WEB_HTML_ABILITY_DATA_HTML,callGeneralHelpBeanClickAbility());
    }
    @Test
    public void clickPkAbility2() {
        assertEq(A_ABILITY,callGeneralHelpBeanClickAbilityId());
    }
    @Test
    public void getMovesAtLevelFirstPk() {
        assertSizeEq(1,callGeneralHelpBeanGetMovesAtLevel());
    }
    @Test
    public void getPkMove() {
        assertEq(M_POK_00_TR,callGeneralHelpBeanGetMove());
    }
    @Test
    public void clickPkMove1() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callGeneralHelpBeanClickMove());
    }
    @Test
    public void clickPkMove2() {
        assertEq(M_POK_00,callGeneralHelpBeanClickMoveId());
    }
    @Test
    public void getPkLevel() {
        assertEq(4,callGeneralHelpBeanGetLevel());
    }
    @Test
    public void getPkGender() {
        assertEq(NO_G,callGeneralHelpBeanGetGender());
    }
    @Test
    public void getPkImage() {
        assertEq("AAABAAAA",callGeneralHelpBeanGetImage());
    }
    @Test
    public void getPokemonDefaultEggGroup() {
        assertSizeEq(1,callGeneralHelpBeanPokemonDefaultEggGroupGet());
    }
    @Test
    public void getName() {
        assertEq(P_POK_01_TR,callGeneralHelpBeanGetTrPokemon());
    }
    @Test
    public void clickName1() {
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,callGeneralHelpBeanClickPokemon());
    }
    @Test
    public void clickName2() {
        assertEq(P_POK_01,callGeneralHelpBeanClickPokemonId());
    }
    @Test
    public void getTm() {
        assertSizeEq(2,callGeneralHelpBeanTmGet());
    }
    @Test
    public void getTrTm1() {
        assertEq(M_POK_02_TR,callGeneralHelpBeanGetTrTm(0));
    }
    @Test
    public void getTrTm2() {
        assertEq(M_POK_03_TR,callGeneralHelpBeanGetTrTm(1));
    }
    @Test
    public void clickTm1() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callGeneralHelpBeanClickTm(0));
    }
    @Test
    public void clickTm2() {
        assertEq(M_POK_02,callGeneralHelpBeanClickTmId(0));
    }
    @Test
    public void clickTm3() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callGeneralHelpBeanClickTm(1));
    }
    @Test
    public void clickTm4() {
        assertEq(M_POK_03,callGeneralHelpBeanClickTmId(1));
    }
    @Test
    public void getHm() {
        assertSizeEq(1,callGeneralHelpBeanHmGet());
    }
    @Test
    public void getTrHm() {
        assertEq(M_POK_00_TR,callGeneralHelpBeanGetTrHm());
    }
    @Test
    public void clickHm1() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callGeneralHelpBeanClickHm());
    }
    @Test
    public void clickHm2() {
        assertEq(M_POK_00,callGeneralHelpBeanClickHmId());
    }
    @Test
    public void getTypes() {
        assertSizeEq(1,callGeneralHelpBeanTypesGet());
    }
    @Test
    public void getTrType() {
        assertEq(T_TYPE1_TR,callGeneralHelpBeanGetTrType());
    }
    @Test
    public void getImageType() {
        assertEq("AAACXXXXCAAAIAAA////",callGeneralHelpBeanGetImageType());
    }
    @Test
    public void getColorType() {
        assertEq("AAABBQYH",callGeneralHelpBeanGetColorType());
    }
    @Test
    public void getNbMaxMoves() {
        assertEq(4,callGeneralHelpBeanNbMaxMovesGet());
    }
    @Test
    public void getMaxEv() {
        assertEq(15,callGeneralHelpBeanMaxEvGet());
    }
    @Test
    public void getMaxIv() {
        assertEq(16,callGeneralHelpBeanMaxIvGet());
    }
    @Test
    public void getMaxLevel() {
        assertEq(64,callGeneralHelpBeanMaxLevelGet());
    }
    @Test
    public void getMinLevel() {
        assertEq(1,callGeneralHelpBeanMinLevelGet());
    }
    @Test
    public void getHappinessMax() {
        assertEq(128,callGeneralHelpBeanHappinessMaxGet());
    }
    @Test
    public void getNbMaxTeam() {
        assertEq(8,callGeneralHelpBeanNbMaxTeamGet());
    }
    @Test
    public void getMaxPp() {
        assertEq(48,callGeneralHelpBeanMaxPpGet());
    }
    @Test
    public void getNbNecStepsIncrHappiness() {
        assertEq(2,callGeneralHelpBeanNbNecStepsIncrHappinessGet());
    }
    @Test
    public void getNbMaxStepsSameEvoBase() {
        assertEq(3,callGeneralHelpBeanNbMaxStepsSameEvoBaseGet());
    }
    @Test
    public void getNbMaxSteps() {
        assertEq(6,callGeneralHelpBeanNbMaxStepsGet());
    }
    @Test
    public void getDefaultMoney() {
        assertEq(new Rate(1024),callGeneralHelpBeanDefaultMoneyGet());
    }
}
