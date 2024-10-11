package aiki.beans.pokemon;

import aiki.db.MessagesDataBaseConstants;
import aiki.util.Coords;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class PokemonBeanTest extends InitDbPkOne {

    @Test
    public void getDisplayName() {
        assertEq(P_POK_00_TR,callPokemonBeanDisplayNameGet(0));
    }
    @Test
    public void isAppearing1() {
        assertTrue(callPokemonBeanIsAppearing(0,2,0));
    }
    @Test
    public void isAppearing2() {
        assertTrue(callPokemonBeanIsAppearing(8,2,0));
    }
    @Test
    public void isAppearing3() {
        assertTrue(callPokemonBeanIsAppearing(15,0,1));
    }
    @Test
    public void isAppearing4() {
        assertFalse(callPokemonBeanIsAppearing(1,0,1));
    }
    @Test
    public void isAppearingZero1() {
        assertTrue(callPokemonBeanIsAppearingZero(0,2));
    }
    @Test
    public void isAppearingZero2() {
        assertFalse(callPokemonBeanIsAppearingZero(15,2));
    }
    @Test
    public void isAppearingPlace1() {
        assertTrue(callPokemonBeanIsAppearingPlace(15,0));
    }
    @Test
    public void isAppearingPlace2() {
        assertFalse(callPokemonBeanIsAppearingPlace(1,0));
    }
    @Test
    public void isAppearingAnyWhere1() {
        assertTrue(callPokemonBeanIsAppearingAnyWhere(15));
    }
    @Test
    public void isAppearingAnyWhere2() {
        assertFalse(callPokemonBeanIsAppearingAnyWhere(2));
    }
    @Test
    public void layers() {
        assertSizeEq(2,callPokemonBeanLayers(0));
    }
    @Test
    public void isMultiLayer1() {
        assertTrue(callPokemonBeanIsMultiLayer(0));
    }
    @Test
    public void isMultiLayer2() {
        assertFalse(callPokemonBeanIsMultiLayer(1));
    }
    @Test
    public void isMultiLayer3() {
        assertFalse(callPokemonBeanIsMultiLayer(2));
    }
//    @Test
//    public void isFirstRow1() {
//        assertFalse(callPokemonBeanIsFirstRow(0));
//    }
//    @Test
//    public void isFirstRow2() {
//        assertFalse(callPokemonBeanIsFirstRow(1));
//    }
//    @Test
//    public void isFirstRow3() {
//        assertTrue(callPokemonBeanIsFirstRow(2));
//    }
//    @Test
//    public void isFirstRow4() {
//        assertFalse(callPokemonBeanIsFirstRow(3));
//    }
    @Test
    public void getMapWidth() {
        assertEq(2,callPokemonBeanGetMapWidth());
    }
    @Test
    public void getImages1() {
        assertSizeEq(4,callPokemonBeanImagesGet());
    }
    @Test
    public void getImages2() {
        assertEq(miniMap(IMG_0),second(elt(callPokemonBeanImagesGet(),0)));
    }
    @Test
    public void getImages3() {
        assertEq(miniMap(IMG_2),second(elt(callPokemonBeanImagesGet(),1)));
    }
    @Test
    public void getImages4() {
        assertEq(miniMap(IMG_1),second(elt(callPokemonBeanImagesGet(),2)));
    }
    @Test
    public void getImages5() {
        assertEq(miniMap(IMG_3),second(elt(callPokemonBeanImagesGet(),3)));
    }
    @Test
    public void getBackImage() {
        assertEq(back(IMG_B00),callPokemonBeanBackImageGet());
    }
    @Test
    public void getFrontImage() {
        assertEq(front(IMG_F00),callPokemonBeanFrontImageGet());
    }
    @Test
    public void getMiniMapImage1() {
        assertEq(miniMap(IMG_0),callPokemonBeanGetMiniMapImage(0));
    }
    @Test
    public void getMiniMapImage2() {
        assertEq(miniMap(IMG_2),callPokemonBeanGetMiniMapImage(1));
    }
    @Test
    public void getMiniMapImage3() {
        assertEq(new int[][]{new int[]{0,STEP},new int[]{IMG_1,16777215}},callPokemonBeanGetMiniMapImage(2));
    }
    @Test
    public void getMiniMapImage4() {
        assertEq(miniMap(IMG_3),callPokemonBeanGetMiniMapImage(3));
    }
    @Test
    public void getPlaces1() {
        assertSizeEq(3,callPokemonBeanPlacesGet());
    }
    @Test
    public void getPlaces2() {
        assertEq(2,callPlaceIndexIndexGet(elt(callPokemonBeanPlacesGet(),0)));
    }
    @Test
    public void getPlaces3() {
        assertEq(CAVE,callPlaceGetName(callPlaceIndexGetPlace(elt(callPokemonBeanPlacesGet(),0))));
    }
    @Test
    public void getPlaces4() {
        assertEq(1,callPlaceIndexIndexGet(elt(callPokemonBeanPlacesGet(),1)));
    }
    @Test
    public void getPlaces5() {
        assertEq(CITY,callPlaceGetName(callPlaceIndexGetPlace(elt(callPokemonBeanPlacesGet(),1))));
    }
    @Test
    public void getPlaces6() {
        assertEq(0,callPlaceIndexIndexGet(elt(callPokemonBeanPlacesGet(),2)));
    }
    @Test
    public void getPlaces7() {
        assertEq(ROAD,callPlaceGetName(callPlaceIndexGetPlace(elt(callPokemonBeanPlacesGet(),2))));
    }
    @Test
    public void getPlaceName1() {
        assertEq(NULL_REF,callPokemonBeanGetPlaceName(0));
    }
    @Test
    public void getPlaceName2() {
        assertEq(CITY,callPokemonBeanGetPlaceName(1));
    }
    @Test
    public void getPlaceName3() {
        assertEq(ROAD,callPokemonBeanGetPlaceName(2));
    }
    @Test
    public void getPlaceName4() {
        assertEq(CAVE,callPokemonBeanGetPlaceName(3));
    }
    @Test
    public void getHeight() {
        assertEq(Rate.one(),callPokemonBeanHeightGet());
    }
    @Test
    public void getWeight() {
        assertEq(Rate.one(),callPokemonBeanWeightGet());
    }
    @Test
    public void roundHeight() {
        assertEq("1.0"+Rate.POWER+"0",callPokemonBeanRoundHeight());
    }
    @Test
    public void roundWeight() {
        assertEq("1.0"+Rate.POWER+"0",callPokemonBeanRoundWeight());
    }
    @Test
    public void getHatchingSteps() {
        assertEq(LgInt.one(),callPokemonBeanHatchingStepsGet());
    }
    @Test
    public void getCatchingRate() {
        assertEq(1,callPokemonBeanCatchingRateGet());
    }
    @Test
    public void getExpRate() {
        assertEq(1,callPokemonBeanExpRateGet());
    }
    @Test
    public void getExpEvo() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callPokemonBeanExpEvoGet());
    }
    @Test
    public void getEvoBase() {
        assertEq(P_POK_00_TR,callPokemonBeanEvoBaseGet());
    }
    @Test
    public void clickBase1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callPokemonBeanClickBase());
    }
    @Test
    public void clickBase2() {
        assertEq(P_POK_00,callPokemonBeanClickBaseId());
    }
    @Test
    public void getStatistics1() {
        assertSizeEq(6,callPokemonBeanStatisticsGet());
    }
    @Test
    public void getStatistics2() {
        assertEq(ST_HP_TR,elt(callPokemonBeanStatisticsGet(),0));
    }
    @Test
    public void getStatistics3() {
        assertEq(ST_ATT_TR,elt(callPokemonBeanStatisticsGet(),1));
    }
    @Test
    public void getStatistics4() {
        assertEq(ST_DEF_TR,elt(callPokemonBeanStatisticsGet(),2));
    }
    @Test
    public void getStatistics5() {
        assertEq(ST_ATT_SPE_TR,elt(callPokemonBeanStatisticsGet(),3));
    }
    @Test
    public void getStatistics6() {
        assertEq(ST_DEF_SPE_TR,elt(callPokemonBeanStatisticsGet(),4));
    }
    @Test
    public void getStatistics7() {
        assertEq(ST_SPEED_TR,elt(callPokemonBeanStatisticsGet(),5));
    }
    @Test
    public void getBase() {
        assertEq(1,callPokemonBeanGetBase());
    }
    @Test
    public void getEv() {
        assertEq(1,callPokemonBeanGetEv());
    }
    @Test
    public void getPossibleGenders1() {
        assertSizeEq(1,callPokemonBeanPossibleGendersGet());
    }
    @Test
    public void getPossibleGenders2() {
        assertEq(NO_G,elt(callPokemonBeanPossibleGendersGet(),0));
    }
    @Test
    public void getTypes1() {
        assertSizeEq(1,callPokemonBeanTypesGet());
    }
    @Test
    public void getTypes2() {
        assertEq(T_TYPE1_TR,elt(callPokemonBeanTypesGet(),0));
    }
    @Test
    public void getEvolutions1() {
        assertSizeEq(1,callPokemonBeanEvolutionsGet());
    }
    @Test
    public void getEvolutions2() {
        assertEq(P_POK_01,elt(callPokemonBeanEvolutionsGet(),0));
    }
    @Test
    public void getMapVars1() {
        assertSizeEq(1,callPokemonBeanMapVarsGet());
    }
    @Test
    public void getMapVars2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callPokemonBeanMapVarsGet(),0)));
    }
    @Test
    public void getMapVars3() {
        assertEq(TIME,second(elt(callPokemonBeanMapVarsGet(),0)));
    }
    @Test
    public void getAbilities1() {
        assertSizeEq(1,callPokemonBeanAbilitiesGet());
    }
    @Test
    public void getAbilities2() {
        assertEq(A_ABILITY,elt(callPokemonBeanAbilitiesGet(),0));
    }
    @Test
    public void getTrAbility() {
        assertEq(A_ABILITY_TR,callPokemonBeanGetTrAbility());
    }
    @Test
    public void clickAbility1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callPokemonBeanClickAbility());
    }
    @Test
    public void clickAbility2() {
        assertEq(A_ABILITY,callPokemonBeanClickAbilityId());
    }
    @Test
    public void getMoveTutors1() {
        assertSizeEq(1,callPokemonBeanMoveTutorsGet());
    }
    @Test
    public void getMoveTutors2() {
        assertEq(M_DAM,elt(callPokemonBeanMoveTutorsGet(),0));
    }
    @Test
    public void getMoveTutor() {
        assertEq(M_DAM_TR,callPokemonBeanGetMoveTutor());
    }
    @Test
    public void clickMoveTutors1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonBeanClickMoveTutors());
    }
    @Test
    public void clickMoveTutors2() {
        assertEq(M_DAM,callPokemonBeanClickMoveTutorsId());
    }
    @Test
    public void getEggGroupsPk1() {
        assertSizeEq(10,callPokemonBeanEggGroupsPkGet(0));
    }
    @Test
    public void getEggGroupsPk2() {
        assertSizeEq(10,callPokemonBeanEggGroupsPkGet(1));
    }
    @Test
    public void getEggGroupsPk3() {
        assertSizeEq(10,callPokemonBeanEggGroupsPkGet(2));
    }
    @Test
    public void getEggGroupsPk4() {
        assertSizeEq(11,callPokemonBeanEggGroupsPkGet(3));
    }
    @Test
    public void getEggGroupsPk5() {
        assertSizeEq(11,callPokemonBeanEggGroupsPkGet(4));
    }
    @Test
    public void getEggGroupsPk6() {
        assertSizeEq(11,callPokemonBeanEggGroupsPkGet(5));
    }
    @Test
    public void getEggGroupsPk7() {
        assertSizeEq(11,callPokemonBeanEggGroupsPkGet(6));
    }
    @Test
    public void getEggGroupsPk8() {
        assertSizeEq(11,callPokemonBeanEggGroupsPkGet(7));
    }
    @Test
    public void getEggGroupsPk9() {
        assertSizeEq(5,callPokemonBeanEggGroupsPkGet(8));
    }
    @Test
    public void getEggGroupsPk10() {
        assertSizeEq(5,callPokemonBeanEggGroupsPkGet(9));
    }
    @Test
    public void getEggGroupsPk11() {
        assertSizeEq(5,callPokemonBeanEggGroupsPkGet(10));
    }
    @Test
    public void getEggGroupsPk12() {
        assertSizeEq(5,callPokemonBeanEggGroupsPkGet(11));
    }
    @Test
    public void getEggGroupsPk13() {
        assertSizeEq(10,callPokemonBeanEggGroupsPkGet(12));
    }
    @Test
    public void getEggGroupsPk14() {
        assertSizeEq(10,callPokemonBeanEggGroupsPkGet(13));
    }
    @Test
    public void getEggGroupsPk15() {
        assertSizeEq(5,callPokemonBeanEggGroupsPkGet(14));
    }
    @Test
    public void getEggGroupsPk16() {
        assertSizeEq(11,callPokemonBeanEggGroupsPkGet(15));
    }
    @Test
    public void getEggGroupsPk17() {
        assertEq(P_POK_08,elt(callPokemonBeanEggGroupsPkGet(8),0));
    }
    @Test
    public void getEggGroupsPk18() {
        assertEq(P_POK_09,elt(callPokemonBeanEggGroupsPkGet(8),1));
    }
    @Test
    public void getEggGroupsPk19() {
        assertEq(P_POK_10,elt(callPokemonBeanEggGroupsPkGet(8),2));
    }
    @Test
    public void getEggGroupsPk20() {
        assertEq(P_POK_11,elt(callPokemonBeanEggGroupsPkGet(8),3));
    }
    @Test
    public void getEggGroupsPk21() {
        assertEq(P_POK_14,elt(callPokemonBeanEggGroupsPkGet(8),4));
    }
    @Test
    public void getEggPk() {
        assertEq(P_POK_09_TR,callPokemonBeanGetEggPk(8,1));
    }
    @Test
    public void clickEggPk1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callPokemonBeanClickEggPk(8,1));
    }
    @Test
    public void clickEggPk2() {
        assertEq(P_POK_09,callPokemonBeanClickEggPkId(8,1));
    }
    @Test
    public void getTechnicalMoves1() {
        assertSizeEq(1,callPokemonBeanTechnicalMovesGet());
    }
    @Test
    public void getTechnicalMoves2() {
        assertEq(1,first(elt(callPokemonBeanTechnicalMovesGet(),0)));
    }
    @Test
    public void getTechnicalMoves3() {
        assertEq(M_DAM_TR,second(elt(callPokemonBeanTechnicalMovesGet(),0)));
    }
    @Test
    public void clickTechnicalMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonBeanClickTechnicalMove());
    }
    @Test
    public void clickTechnicalMove2() {
        assertEq(M_DAM,callPokemonBeanClickTechnicalMoveId());
    }
    @Test
    public void getHiddenMoves1() {
        assertSizeEq(1,callPokemonBeanHiddenMovesGet());
    }
    @Test
    public void getHiddenMoves2() {
        assertEq(1,first(elt(callPokemonBeanHiddenMovesGet(),0)));
    }
    @Test
    public void getHiddenMoves3() {
        assertEq(M_STA_TR,second(elt(callPokemonBeanHiddenMovesGet(),0)));
    }
    @Test
    public void clickHiddenMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonBeanClickHiddenMove());
    }
    @Test
    public void clickHiddenMove2() {
        assertEq(M_STA,callPokemonBeanClickHiddenMoveId());
    }
    @Test
    public void getLevMoves1() {
        assertSizeEq(2,callPokemonBeanLevMovesGet());
    }
    @Test
    public void getLevMoves2() {
        assertEq(1,callLevelMoveGetLevel(elt(callPokemonBeanLevMovesGet(),0)));
    }
    @Test
    public void getLevMoves3() {
        assertEq(M_DAM_TR,callLevelMoveGetMove(elt(callPokemonBeanLevMovesGet(),0)));
    }
    @Test
    public void getLevMoves4() {
        assertEq(3,callLevelMoveGetLevel(elt(callPokemonBeanLevMovesGet(),1)));
    }
    @Test
    public void getLevMoves5() {
        assertEq(M_STA_TR,callLevelMoveGetMove(elt(callPokemonBeanLevMovesGet(),1)));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonBeanClickMove(0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_DAM,callPokemonBeanClickMoveId(0));
    }
    @Test
    public void clickMove3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callPokemonBeanClickMove(1));
    }
    @Test
    public void clickMove4() {
        assertEq(M_STA,callPokemonBeanClickMoveId(1));
    }

    @Test
    public void getLevel() {
        assertEq(5,callEvolutionLevelBeanLevelGet());
    }
    @Test
    public void getGender1() {
        assertEq(NO_G,callEvolutionLevelGenderBeanGenderGet());
    }
    @Test
    public void getDisplayBase() {
        assertEq(P_POK_01_TR,callEvolutionBeanDisplayBaseGet());
    }
    @Test
    public void getStone() {
        assertEq(I_STONE_TR,callEvolutionStoneBeanStoneGet());
    }
    @Test
    public void clickStone1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_STONE_HTML,callEvolutionStoneBeanClickStone());
    }
    @Test
    public void clickStone2() {
        assertEq(I_STONE,callEvolutionStoneBeanClickStoneId());
    }
    @Test
    public void getGender2() {
        assertEq(NO_G,callEvolutionStoneGenderBeanGenderGet());
    }
    @Test
    public void getMove() {
        assertEq(M_DAM_TR,callEvolutionMoveBeanMoveGet());
    }
    @Test
    public void clickMoveEvo1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEvolutionMoveBeanClickMove());
    }
    @Test
    public void clickMoveEvo2() {
        assertEq(M_DAM,callEvolutionMoveBeanClickMoveId());
    }
    @Test
    public void getType() {
        assertEq(T_TYPE1_TR,callEvolutionMoveTypeBeanTypeGet());
    }
    @Test
    public void getOther() {
        assertEq(P_POK_11_TR,callEvolutionTeamBeanOtherGet());
    }
    @Test
    public void clickTeam1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callEvolutionTeamBeanClickTeam());
    }
    @Test
    public void clickTeam2() {
        assertEq(P_POK_11,callEvolutionTeamBeanClickTeamId());
    }
    @Test
    public void getItem() {
        assertEq(I_HOLD_TR,callEvolutionItemBeanItemGet());
    }
    @Test
    public void clickItem1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_EVO_ITEM_HTML,callEvolutionItemBeanClickItem());
    }
    @Test
    public void clickItem2() {
        assertEq(I_HOLD,callEvolutionItemBeanClickItemId());
    }
    @Test
    public void getDisplayNameEvo() {
        assertEq(P_POK_13_TR,callEvolutionBeanDisplayNameGet());
    }
    @Test
    public void clickEvo1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callEvolutionBeanClickEvo());
    }
    @Test
    public void clickEvo2() {
        assertEq(P_POK_13,callEvolutionBeanClickEvoId());
    }
    @Test
    public void getMin() {
        assertEq(128,callEvolutionHappinessBeanMinGet());
    }
    @Test
    public void clickLevel1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callPokemonBeanClickLevel());
    }
    @Test
    public void clickLevel2() {
        Coords val_ = callPokemonBeanClickLevelId();
        assertEq(2,val_.getNumberPlace());
        assertEq(1,val_.getLevel().getLevelIndex());
    }
    @Test
    public void clickLevelZero1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,callPokemonBeanClickLevelZero());
    }
    @Test
    public void clickLevelZero2() {
        Coords val_ = callPokemonBeanClickLevelZeroId();
        assertEq(0,val_.getNumberPlace());
        assertEq(0,val_.getLevel().getLevelIndex());
    }
    @Test
    public void clickPokedex() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML,callPokemonBeanClickPokedex());
    }
}
