package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.facade.simulation.enums.*;
import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.enums.*;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class SimulationBeanTest extends InitDbSimulation {
    @Test
    public void translatePk() {
        StringList tr_ = SimulationBean.translatePk(dbView(), new StringList(P_POK_01, P_POK_00));
        assertEq(2,tr_.size());
        assertEq(P_POK_00_TR,tr_.get(0));
        assertEq(P_POK_01_TR,tr_.get(1));
    }
    @Test
    public void isDiffState() {
        assertTrue(callSimulationBeanIsDiffState(dispSimu()));
    }
    @Test
    public void isDiffState2() {
        assertFalse(callSimulationBeanIsDiffState(validateDiff(2)));
    }
    @Test
    public void modifDiff() {
        assertEq(Rate.newRate("5/7"),integration());
    }
    @Test
    public void getNbTeams() {
        assertEq(2,callSimulationBeanNbTeamsGet(2));
    }
    @Test
    public void getNumbers1() {
        assertSizeEq(2,callSimulationBeanNumbers(2));
    }
    @Test
    public void getNumbers2() {
        assertEq(1,eltInt(callSimulationBeanNumbers(2),0));
    }
    @Test
    public void getNumbers3() {
        assertEq(2,eltInt(callSimulationBeanNumbers(2),1));
    }
    @Test
    public void getFreeTeams1() {
        assertFalse(callSimulationBeanFreeTeamsGet(0));
    }
    @Test
    public void getFreeTeams2() {
        assertTrue(callSimulationBeanFreeTeamsGet(2));
    }
    @Test
    public void getIndexTeam() {
        assertEq(1, callSimulationBeanIndexTeamGet(selectTeam(validateDiff(3),1)));
    }
    @Test
    public void selectedTeamNumber() {
        assertEq(2, callSimulationBeanSelectedTeamNumberGet(selectTeam(validateDiff(3),1)));
    }
    @Test
    public void getTranslatedName() {
        assertEq(P_POK_00_TR,callEditTrainerPokemonBeanGetTranslatedName(pkTrainer()));
    }
    @Test
    public void getBooleansSelPk1() {
        assertSizeEq(3,callSelectPokemonBeanBooleansGet(pkTrainerSelectPk()));
    }
    @Test
    public void genderBeginPkTrainer() {
        assertEq(Gender.NO_GENDER.getGenderName(),callEditTrainerPokemonBeanGenderGet(pkTrainer()));
    }
    @Test
    public void gendersListPkTrainer() {
        assertSizeEq(1,callEditTrainerPokemonBeanGendersGet(pkTrainer()));
    }
    @Test
    public void levelBegin() {
        assertEq(7,callEditTrainerPokemonBeanLevelGet(pkTrainer()));
    }
    @Test
    public void adding() {
        assertTrue(callEditTrainerPokemonBeanAddGet(pkTrainer()));
    }
    @Test
    public void ally() {
        assertTrue(callEditTrainerPokemonBeanAllyPkGet(pkTrainerSelectPkAllyInfo(true)));
    }
    @Test
    public void foe() {
        assertFalse(callEditTrainerPokemonBeanAllyPkGet(pkTrainerSelectPkAllyInfo(false)));
    }
    @Test
    public void typeNameSelPk() {
        assertEq(NULL_REF,callSelectPokemonBeanTypedNameGet(pkTrainerSelectPk()));
    }
    @Test
    public void getPokedex() {
        assertEq(10,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkName("")));
    }
    @Test
    public void getSelectedPk() {
        assertEq(P_POK_01_TR,callEditTrainerPokemonBeanGetTranslatedName(pkTrainerSelectPkName(P_POK_01_TR)));
    }
    @Test
    public void typeTypeSelPk() {
        assertEq(NULL_REF,callSelectPokemonBeanTypedTypeGet(pkTrainerSelectPk()));
    }
    @Test
    public void typeTypePkWholeWord1() {
        assertFalse(callSelectPokemonBeanWholeWordGet(pkTrainerSelectPkType("",false)));
    }
    @Test
    public void typeTypePkWholeWord2() {
        assertTrue(callSelectPokemonBeanWholeWordGet(pkTrainerSelectPkType("",true)));
    }
    @Test
    public void getPokedexWholeWord1() {
        assertEq(0,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkType("T_*",true)));
    }
    @Test
    public void getPokedexWholeWord2() {
        assertEq(10,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkType("T_*",false)));
    }
    @Test
    public void getMiniImagePk() {
        assertEq(one(IMG_00),callSelectPokemonBeanGetMiniImage());
    }
    @Test
    public void clickLinkPk1() {
        assertEq(P_POK_01_TR,callEditTrainerPokemonBeanGetTranslatedName(pkTrainerSelectPkRow(1)));
    }
    @Test
    public void hasEvoSelPk() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callSelectPokemonBeanHasEvoGet(pkTrainerSelectPk()));
    }
    @Test
    public void getPokedexHasEvo1() {
        assertEq(3,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkHasEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexHasEvo2() {
        assertEq(7,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkHasEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isEvoSelPk() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callSelectPokemonBeanIsEvoGet(pkTrainerSelectPk()));
    }
    @Test
    public void getPokedexIsEvo1() {
        assertEq(3,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsEvo2() {
        assertEq(7,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isLegPk() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callSelectPokemonBeanIsLegGet(pkTrainerSelectPk()));
    }
    @Test
    public void getPokedexIsLeg1() {
        assertEq(2,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsLeg(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsLeg2() {
        assertEq(8,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsLeg(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void cancelSelPk() {
        assertEq(P_POK_00_TR,callEditTrainerPokemonBeanGetTranslatedName(pkTrainerSelectPkCancel()));
    }
    @Test
    public void getTranslatedAbility() {
        assertEq(A_SIM_1_TR,callEditTrainerPokemonBeanGetTranslatedAbility(pkTrainer()));
    }
    @Test
    public void typeAbilitySelAb() {
        assertEq(NULL_REF,callSelectAbilityBeanTypedAbilityGet(pkTrainerSelectAb()));
    }
    @Test
    public void getSortedAbilities() {
        assertSizeEq(2,callSelectAbilityBeanSortedAbilitiesGet(pkTrainerSelectAb("")));
    }
    @Test
    public void getSelectedAb() {
        assertEq(A_SIM_2_TR,callEditTrainerPokemonBeanGetTranslatedAbility(pkTrainerSelectAb(A_SIM_2_TR)));
    }
    @Test
    public void getTrSortedAbility() {
        assertEq(A_SIM_2_TR,callSelectAbilityBeanGetTrAbility());
    }
    @Test
    public void clickLinkAb1() {
        assertEq(A_SIM_2_TR,callEditTrainerPokemonBeanGetTranslatedAbility(pkTrainerSelectAb(1)));
    }
    @Test
    public void cancelSelAb() {
        assertEq(A_SIM_1_TR,callEditTrainerPokemonBeanGetTranslatedAbility(pkTrainerSelectAbCancel()));
    }
    @Test
    public void getTranslatedItem() {
        assertEq(NULL_REF,callEditTrainerPokemonBeanGetTranslatedItem(pkTrainer()));
    }
    @Test
    public void typeNameSelIt() {
        assertEq(NULL_REF,callSelectItemBeanTypedNameGet(pkTrainerSelectItName()));
    }
    @Test
    public void getSelectedItemsName() {
        assertEq(4,callSelectItemBeanItemsGet(pkTrainerSelectItName("")));
    }
    @Test
    public void typePriceSelIt() {
        assertEq(NULL_REF,callSelectItemBeanTypedPriceGet(pkTrainerSelectItName()));
    }
    @Test
    public void getSelectedItemsPrice() {
        assertEq(4,callSelectItemBeanItemsGet(pkTrainerSelectItPrice()));
    }
    @Test
    public void typeClassSelIt() {
        assertEq(NULL_REF,callSelectItemBeanTypedClassGet(pkTrainerSelectItName()));
    }
    @Test
    public void getSelectedItemsClass() {
        assertEq(2,callSelectItemBeanItemsGet(pkTrainerSelectItCl(CI_BATTLE)));
    }
    @Test
    public void getSelectedIt() {
        assertEq(I_STONE_TR,callEditTrainerPokemonBeanGetTranslatedItem(pkTrainerSelectItName(I_STONE_TR)));
    }
    @Test
    public void getMiniImageIt() {
        assertEq(one(IMG_BALL),callSelectItemBeanGetMiniImage());
    }
    @Test
    public void clickLinkIt1() {
        assertEq(I_BALL_TR,callEditTrainerPokemonBeanGetTranslatedItem(pkTrainerSelectItName(0)));
    }
    @Test
    public void clickLinkIt2() {
        assertEq(I_MULT_EXP_TR,callEditTrainerPokemonBeanGetTranslatedItem(pkTrainerSelectItName(1)));
    }
    @Test
    public void cancelSelItRem() {
        assertEq(NULL_REF,callEditTrainerPokemonBeanGetTranslatedItem(pkTrainerSelectItCancelRem()));
    }
    @Test
    public void cancelSelIt() {
        assertEq(I_BALL_TR,callEditTrainerPokemonBeanGetTranslatedItem(pkTrainerSelectItCancel()));
    }
    @Test
    public void getMovesPkTrainer() {
        assertSizeEq(1, callEditTrainerPokemonBeanMovesGet(pkTrainer()));
    }
    @Test
    public void getMovesPkTrainerElt1() {
        assertFalse(callSelectLineMoveSelectedGet(eltSelectMove(callEditTrainerPokemonBeanMovesGet(pkTrainer()),0)));
    }
    @Test
    public void getMovesPkTrainerElt2() {
        assertFalse(callSelectLineMoveSelectedGet(eltSelectMove(callEditTrainerPokemonBeanMovesGet(pkTrainer(false)),0)));
    }
    @Test
    public void getMovesPkTrainerElt3() {
        assertTrue(callSelectLineMoveSelectedGet(eltSelectMove(callEditTrainerPokemonBeanMovesGet(pkTrainer(true)),0)));
    }
    @Test
    public void getCategoriesSelMoves1() {
        assertSizeEq(3,callEditPokemonMovesBeanCategoriesGet(pkTrainerSetMoves()));
    }
    @Test
    public void typeNameSetMoves() {
        assertEq(NULL_REF,callEditPokemonMovesBeanTypedNameGet(pkTrainerSetMoves()));
    }
    @Test
    public void getMovesSearchName() {
        assertSizeEq(5,callEditPokemonMovesBeanMovesGet(pkTrainerSetMovesName("")));
    }
    @Test
    public void getMovesPkTrainerAfter() {
        assertSizeEq(2, callEditTrainerPokemonBeanMovesGet(pkTrainerSetMovesNameAdd(M_POK_01_TR, 0)));
    }
    @Test
    public void getMovesPkTrainerAfterSec() {
        assertSizeEq(2, callEditTrainerPokemonBeanMovesGet(pkTrainerSetMovesNameAdd("M_POK_0*", 1)));
    }
    @Test
    public void typeTypeSetMoves() {
        assertEq(NULL_REF,callEditPokemonMovesBeanTypedTypeGet(pkTrainerSetMoves()));
    }
    @Test
    public void typeTypeMvWholeWord1() {
        assertFalse(callEditPokemonMovesBeanWholeWordGet(pkTrainerSetMovesType("",false)));
    }
    @Test
    public void typeTypeMvWholeWord2() {
        assertTrue(callEditPokemonMovesBeanWholeWordGet(pkTrainerSetMovesType("",true)));
    }
    @Test
    public void getMovesWholeWord1() {
        assertSizeEq(0,callEditPokemonMovesBeanMovesGet(pkTrainerSetMovesType("T_*",true)));
    }
    @Test
    public void getMovesWholeWord2() {
        assertSizeEq(5,callEditPokemonMovesBeanMovesGet(pkTrainerSetMovesType("T_*",false)));
    }
    @Test
    public void catSetMoves() {
        assertEq(NULL_REF,callEditPokemonMovesBeanCategoryGet(pkTrainerSetMoves()));
    }
    @Test
    public void getMovesSearchCat1() {
        assertSizeEq(2,callEditPokemonMovesBeanMovesGet(pkTrainerSetMovesCat(C_SIM_1)));
    }
    @Test
    public void getMovesSearchCat2() {
        assertSizeEq(3,callEditPokemonMovesBeanMovesGet(pkTrainerSetMovesCat(C_SIM_2)));
    }
    @Test
    public void getMovesIsPlayer1() {
        assertFalse(callEditPokemonMovesBeanPlayerGet(pkTrainerSetMoves()));
    }
    @Test
    public void getMovesPkTrainerCancel() {
        assertSizeEq(1, callEditTrainerPokemonBeanMovesGet(pkTrainerSetMovesCancel()));
    }
    @Test
    public void deleteMovesPkTrainer() {
        assertSizeEq(1, callEditTrainerPokemonBeanMovesGet(pkTrainerSetMovesRemove()));
    }
    @Test
    public void getFoeTeamChangeMovesPkTrainer() {
        assertSizeEq(1,callSimulationBeanFoeTeamGet(addPkTrainerChangeMoves(false)));
    }
    @Test
    public void getImageFoe() {
        assertEq(one(IMG_00),callSimulationBeanGetImageFoe(addPkTrainerChangeMoves(false),0));
    }
    @Test
    public void getNameFoe() {
        assertEq(P_POK_00_TR,callSimulationBeanGetNameFoe(addPkTrainerChangeMoves(false),0));
    }
    @Test
    public void getGenderFoe() {
        assertEq(NO_G,callSimulationBeanGetGenderFoe(addPkTrainerChangeMoves(false),0));
    }
    @Test
    public void getLevelFoe() {
        assertEq(7,callSimulationBeanGetLevelFoe(addPkTrainerChangeMoves(false),0));
    }
    @Test
    public void getAbilityFoe() {
        assertEq(A_SIM_1_TR,callSimulationBeanGetAbilityFoe(addPkTrainerChangeMoves(false),0));
    }
    @Test
    public void getItemFoe() {
        assertEq(I_BALL_TR,callSimulationBeanGetItemFoe(addPkTrainerChangeItem(false),0));
    }
    @Test
    public void getMovesFoe() {
        assertSizeEq(1,callSimulationBeanGetMovesFoe(addPkTrainerChangeMoves(false),0));
    }
    @Test
    public void getMovesFoeElt() {
        assertEq(M_POK_01_TR,elt(callSimulationBeanGetMovesFoe(addPkTrainerChangeMoves(false),0),0));
    }
    @Test
    public void getAllyTeamChangeMovesPkTrainer() {
        assertSizeEq(1,callSimulationBeanAllyTeamGet(addPkTrainerChangeMoves(true)));
    }
    @Test
    public void getImageAlly() {
        assertEq(one(IMG_00),callSimulationBeanGetImageAlly(addPkTrainerChangeMoves(true),0));
    }
    @Test
    public void getNameAlly() {
        assertEq(P_POK_00_TR,callSimulationBeanGetNameAlly(addPkTrainerChangeMoves(true),0));
    }
    @Test
    public void getGenderAlly() {
        assertEq(NO_G,callSimulationBeanGetGenderAlly(addPkTrainerChangeMoves(true),0));
    }
    @Test
    public void getLevelAlly() {
        assertEq(7,callSimulationBeanGetLevelAlly(addPkTrainerChangeMoves(true),0));
    }
    @Test
    public void getAbilityAlly() {
        assertEq(A_SIM_1_TR,callSimulationBeanGetAbilityAlly(addPkTrainerChangeMoves(true),0));
    }
    @Test
    public void getItemAlly() {
        assertEq(I_BALL_TR,callSimulationBeanGetItemAlly(addPkTrainerChangeItem(true),0));
    }
    @Test
    public void getMovesAlly() {
        assertSizeEq(1,callSimulationBeanGetMovesAlly(addPkTrainerChangeMoves(true),0));
    }
    @Test
    public void getMovesAllyElt() {
        assertEq(M_POK_01_TR,elt(callSimulationBeanGetMovesAlly(addPkTrainerChangeMoves(true),0),0));
    }
    @Test
    public void levelAfter() {
        assertEq(8,callSimulationBeanGetLevelFoe(pkTrainerLevel(8),0));
    }
    @Test
    public void levelTooMuch() {
        assertEq(255,callSimulationBeanGetLevelFoe(pkTrainerLevel(256),0));
    }
    @Test
    public void levelTooLow() {
        assertEq(1,callSimulationBeanGetLevelFoe(pkTrainerLevel(0),0));
    }
    @Test
    public void getMovesFoeRestore() {
        assertSizeEq(1,callSimulationBeanGetMovesFoe(pkTrainerLevelRestoreMoves(),0));
    }
    @Test
    public void getMovesFoeRestoreElt() {
        assertEq(M_POK_00_TR,elt(callSimulationBeanGetMovesFoe(pkTrainerLevelRestoreMoves(),0),0));
    }
    @Test
    public void pkTrainerIndexValue() {
        assertEq(1,callPokemonTrainerDtoIndexGet(eltPkTraDto(callSimulationBeanFoeTeamGet(pkTrainerIndex()),1)));
    }
    @Test
    public void cancelAddPkTrainer() {
        assertSizeEq(0,callSimulationBeanFoeTeamGet(pkTrainerLevelCancelAdd()));
    }
//    @Test
//    public void selectFoePkInitBad() {
//        assertTrue(callSimulationBeanErrorSelectedFoePkGet(editNoFoePkStateSelectZero()));
//        assertTrue(callSimulationBeanErrorSelectedFoePkGet(editNoFoePkStateSelectNo()));
//        assertFalse(callSimulationBeanErrorSelectedFoePkGet(editNoFoePkStateSelectZero(TeamCrud.EDIT)));
//        assertFalse(callSimulationBeanErrorSelectedFoePkGet(editNoFoePkStateSelectZero(TeamCrud.REMOVE)));
//    }
    @Test
    public void selectFoePkInit() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editNoFoePk());
    }
    @Test
    public void selectFoePkAddedNoSelect() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editNoSelectedFoePk());
    }
    @Test
    public void selectFoePkAddedForget() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editForgetSelectedFoePk());
    }
    @Test
    public void editingFoe() {
        assertFalse(callEditTrainerPokemonBeanAddGet(editEditSelectedFoePk()));
    }
    @Test
    public void getMovesFoeEditPkTrainer() {
        assertSizeEq(2,callSimulationBeanGetMovesFoe(editEditSelectedFoePkAddMove(),0));
    }
    @Test
    public void getMovesFoeEditPkTrainerFirst() {
        assertEq(M_POK_00_TR,elt(callSimulationBeanGetMovesFoe(editEditSelectedFoePkAddMove(),0),0));
    }
    @Test
    public void getMovesFoeEditPkTrainerSecond() {
        assertEq(M_POK_01_TR,elt(callSimulationBeanGetMovesFoe(editEditSelectedFoePkAddMove(),0),1));
    }
    @Test
    public void pkTrainerFoeIndexAfterRemove() {
        assertEq(0,callPokemonTrainerDtoIndexGet(eltPkTraDto(callSimulationBeanFoeTeamGet(pkTrainerFoeRemove()),0)));
    }
    @Test
    public void pkTrainerNameFoeAfterRemove() {
        assertEq(P_POK_01_TR,callSimulationBeanGetNameFoe(pkTrainerFoeRemove(),0));
    }
//    @Test
//    public void getSelectedFoePk() {
//        assertEq(0,callSimulationBeanSelectedFoePkGet(formEditSelectedFoePk()));
//    }
//    @Test
//    public void getSelectedFoeAction() {
//        assertEq(TeamCrud.EDIT.getTeamCrudString(),callSimulationBeanSelectedFoeActionGet(formEditSelectedFoePk()));
//    }
//    @Test
//    public void selectAllyPkInitBad() {
//        assertTrue(callSimulationBeanErrorSelectedAllyPkGet(editNoAllyPkStateSelectZero()));
//        assertTrue(callSimulationBeanErrorSelectedAllyPkGet(editNoAllyPkStateSelectNo()));
//        assertFalse(callSimulationBeanErrorSelectedAllyPkGet(editNoAllyPkStateSelectZero(TeamCrud.EDIT)));
//        assertFalse(callSimulationBeanErrorSelectedAllyPkGet(editNoAllyPkStateSelectZero(TeamCrud.REMOVE)));
//    }
    @Test
    public void selectAllyPkInit() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editNoAllyPk());
    }
    @Test
    public void selectAllyPkAddedNoSelect() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editNoSelectedAllyPk());
    }
    @Test
    public void selectAllyPkAddedForget() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editForgetSelectedAllyPk());
    }
    @Test
    public void editingAlly() {
        assertFalse(callEditTrainerPokemonBeanAddGet(editEditSelectedAllyPk()));
    }
    @Test
    public void getMovesAllyEditPkTrainer() {
        assertSizeEq(2,callSimulationBeanGetMovesAlly(editEditSelectedAllyPkAddMove(),0));
    }
    @Test
    public void getMovesAllyEditPkTrainerFirst() {
        assertEq(M_POK_00_TR,elt(callSimulationBeanGetMovesAlly(editEditSelectedAllyPkAddMove(),0),0));
    }
    @Test
    public void getMovesAllyEditPkTrainerSecond() {
        assertEq(M_POK_01_TR,elt(callSimulationBeanGetMovesAlly(editEditSelectedAllyPkAddMove(),0),1));
    }
    @Test
    public void pkTrainerAllyIndexAfterRemove() {
        assertEq(0,callPokemonTrainerDtoIndexGet(eltPkTraDto(callSimulationBeanAllyTeamGet(pkTrainerAllyRemove()),0)));
    }
    @Test
    public void pkTrainerNameAllyAfterRemove() {
        assertEq(P_POK_01_TR,callSimulationBeanGetNameAlly(pkTrainerAllyRemove(),0));
    }
//    @Test
//    public void getSelectedAllyPk() {
//        assertEq(0,callSimulationBeanSelectedAllyPkGet(formEditSelectedAllyPk()));
//    }
//    @Test
//    public void getSelectedAllyAction() {
//        assertEq(TeamCrud.EDIT.getTeamCrudString(),callSimulationBeanSelectedAllyActionGet(formEditSelectedAllyPk()));
//    }
    @Test
    public void defaultMult() {
        assertEq(1,callSimulationBeanMultiplicityGet(validateDiff(1)));
    }
    @Test
    public void defaultEnv() {
        assertEq(EnvironmentType.ROAD.getEnvName(),callSimulationBeanEnvironmentGet(validateDiff(1)));
    }
    @Test
    public void envs() {
        assertSizeEq(9,callSimulationBeanEnvironmentsGet(validateDiff(1)));
    }
    @Test
    public void noTeam() {
        assertFalse(callSimulationBeanOkGet(pkTrainerTwoTeamsNextKo()));
    }
    @Test
    public void sufficientPkInTeams() {
        assertTrue(callSimulationBeanOkGet(pkTrainerTwoTeamsNextOk()));
    }
    @Test
    public void sufficientPkInTeamsAdjMult() {
        assertTrue(callSimulationBeanOkGet(pkTrainerTwoTeamsNextAdjMult()));
    }
    @Test
    public void sufficientPkInTeamsAlly() {
        assertTrue(callSimulationBeanOkGet(pkTrainerTwoTeamsNextOkAlly()));
    }
    @Test
    public void getPkPlayerName() {
        assertEq(NULL_REF,callAddPokemonBeanNamePkGet(pkPlayer()));
    }
    @Test
    public void getBooleansSelPkPlayer1() {
        assertSizeEq(3,callAddPokemonBeanBooleansGet(pkPlayer()));
    }
    @Test
    public void genderBeginPkPlayer() {
        assertEq(Gender.NO_GENDER.getGenderName(),callAddPokemonBeanGenderGet(pkPlayer()));
    }
    @Test
    public void gendersListPkPlayer() {
        assertSizeEq(0,callAddPokemonBeanGendersGet(pkPlayer()));
    }
    @Test
    public void levelBeginPkPlayer() {
        assertEq(0,callAddPokemonBeanLevelGet(pkPlayer()));
    }
    @Test
    public void typeNameSelPkPlayer() {
        assertEq(NULL_REF,callAddPokemonBeanTypedNameGet(pkPlayerSelectPk()));
    }
    @Test
    public void getPokedexPkPlayer() {
        assertEq(10,callAddPokemonBeanPokedexGet(pkPlayerSelectPkName("")));
    }
    @Test
    public void getSelectedPkPlayer() {
        assertEq(P_POK_01,callAddPokemonBeanNamePkGet(pkPlayerSelectPkName(P_POK_01_TR)));
    }
    @Test
    public void typeTypeSelPkPlayer() {
        assertEq(NULL_REF,callAddPokemonBeanTypedTypeGet(pkPlayerSelectPk()));
    }
    @Test
    public void typeTypePkPlayerWholeWord1() {
        assertFalse(callAddPokemonBeanWholeWordGet(pkPlayerSelectPkType("",false)));
    }
    @Test
    public void typeTypePkPlayerWholeWord2() {
        assertTrue(callAddPokemonBeanWholeWordGet(pkPlayerSelectPkType("",true)));
    }
    @Test
    public void getPokedexPkPlayerWholeWord1() {
        assertEq(0,callAddPokemonBeanPokedexGet(pkPlayerSelectPkType("T_*",true)));
    }
    @Test
    public void getPokedexPkPlayerWholeWord2() {
        assertEq(10,callAddPokemonBeanPokedexGet(pkPlayerSelectPkType("T_*",false)));
    }
    @Test
    public void getMiniImagePkPlayer() {
        assertEq(one(IMG_00),callAddPokemonBeanGetMiniImage());
    }
    @Test
    public void clickLinkPkPlayer1() {
        assertEq(P_POK_01,callAddPokemonBeanNamePkGet(pkPlayerSelectPkRow(1)));
    }
    @Test
    public void hasEvoSelPkPlayer() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callAddPokemonBeanHasEvoGet(pkPlayerSelectPk()));
    }
    @Test
    public void getPokedexPkPlayerHasEvo1() {
        assertEq(3,callAddPokemonBeanPokedexGet(pkPlayerSelectPkHasEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexPkPlayerHasEvo2() {
        assertEq(7,callAddPokemonBeanPokedexGet(pkPlayerSelectPkHasEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isEvoSelPkPlayer() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callAddPokemonBeanIsEvoGet(pkPlayerSelectPk()));
    }
    @Test
    public void getPokedexIsEvoPkPlayer1() {
        assertEq(3,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsEvoPkPlayer2() {
        assertEq(7,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isLegPkPlayer() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callAddPokemonBeanIsLegGet(pkPlayerSelectPk()));
    }
    @Test
    public void getPokedexIsLegPkPlayer1() {
        assertEq(2,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsLeg(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsLegPkPlayer2() {
        assertEq(8,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsLeg(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void cancelSelPkPlayer() {
        assertSizeEq(0,callSimulationBeanTeamGet(pkPlayerSelectPkCancel()));
    }
    @Test
    public void getPkPlayerAbilities() {
        assertSizeEq(2,callAddPokemonBeanAbilitiesGet(pkPlayerSelectPkName(P_POK_01_TR)));
    }
    @Test
    public void getPkPlayerAbility() {
        assertEq(A_SIM_2,callAddPokemonBeanAbilityGet(pkPlayerSelectPkNameAbility(P_POK_01_TR)));
    }
    @Test
    public void getPkPlayerLevel() {
        assertEq(40,callAddPokemonBeanLevelGet(pkPlayerSelectPkNameAbility(P_POK_01_TR)));
    }
    @Test
    public void getPkPlayerGender() {
        assertEq(Gender.NO_GENDER.getGenderName(), callAddPokemonBeanGenderGet(pkPlayerSelectPkNameAbility(P_POK_01_TR)));
    }
    @Test
    public void addQuickPkPlayer() {
        assertEq(NULL_REF,callAddPokemonBeanTypedTypeGet(pkPlayerSelectPkNameQuickAdded()));
    }
    @Test
    public void addPkPlayer() {
        assertSizeEq(1,callSimulationBeanTeamGet(pkPlayerSelectPkNameAdded(P_POK_01_TR)));
    }
    @Test
    public void getImage() {
        assertEq(one(IMG_01),callSimulationBeanGetImage(pkPlayerSelectPkNameAdded(P_POK_01_TR),0));
    }
    @Test
    public void getName() {
        assertEq(P_POK_01_TR,callSimulationBeanGetName(pkPlayerSelectPkNameAdded(P_POK_01_TR),0));
    }
    @Test
    public void getGender() {
        assertEq(NO_G,callSimulationBeanGetGender(pkPlayerSelectPkNameAdded(P_POK_01_TR),0));
    }
    @Test
    public void getLevel() {
        assertEq(40,callSimulationBeanGetLevel(pkPlayerSelectPkNameAdded(P_POK_01_TR),0));
    }
    @Test
    public void getAbility() {
        assertEq(A_SIM_2_TR,callSimulationBeanGetAbility(pkPlayerSelectPkNameAdded(P_POK_01_TR),0));
    }
    @Test
    public void addPkPlayerTwice() {
        assertSizeEq(2,callSimulationBeanTeamGet(pkPlayerSelectPkNameTwice()));
    }
//    @Test
//    public void selectPkInitBad() {
//        assertTrue(callSimulationBeanErrorSelectedPkGet(editNoPkStateSelectZero()));
//        assertTrue(callSimulationBeanErrorSelectedPkGet(editNoPkStateSelectNo()));
//        assertFalse(callSimulationBeanErrorSelectedPkGet(editNoPkStateSelectZero(TeamCrud.EDIT)));
//        assertFalse(callSimulationBeanErrorSelectedPkGet(editNoPkStateSelectZero(TeamCrud.REMOVE)));
//    }

    @Test
    public void selectPlayerPkInit() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editNoPlayerPk());
    }
    @Test
    public void selectPlayerPkAddedNoSelect() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editNoSelectedPlayerPk());
    }
    @Test
    public void selectPlayerPkAddedForget() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,editForgetSelectedPlayerPk());
    }
    @Test
    public void namePkPlayerEdit() {
        assertEq(P_POK_00,callEditPokemonBeanNamePkGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void namePkPlayerEditTr() {
        assertEq(P_POK_00_TR,callEditPokemonBeanTranslateName(editEditSelectedPlayerPk()));
    }
    @Test
    public void chosenItem() {
        assertEq(I_BALL_TR,callEditPokemonBeanTranslateItem(editEditSelectedPlayerPkItem()));
    }
    @Test
    public void deleteMovesPkPlayer() {
        assertSizeEq(1, callEditPokemonBeanMovesGet(pkPlayerSetMovesRemove()));
    }
    @Test
    public void getItem() {
        assertEq(I_BALL_TR,callSimulationBeanGetItem(addPkPlayerChangeMoves(),0));
    }
    @Test
    public void getMovesEditPkPlayer() {
        assertSizeEq(1,callSimulationBeanGetMoves(addPkPlayerChangeMoves(),0));
    }
    @Test
    public void getMovesEditPkPlayerFirst() {
        assertEq(M_POK_01_TR,elt(callSimulationBeanGetMoves(addPkPlayerChangeMoves(),0),0));
    }
    @Test
    public void pkPlayerIndexAfterRemove() {
        assertEq(0,callPokemonPlayerDtoIndexGet(eltPkPlDto(callSimulationBeanTeamGet(pkPlayerRemove()),0)));
    }
    @Test
    public void pkPlayerNameAfterRemove() {
        assertEq(P_POK_01_TR,callSimulationBeanGetName(pkPlayerRemove(),0));
    }
//    @Test
//    public void getSelectedPkPlayerIndexe() {
//        assertEq(0,callSimulationBeanSelectedPkGet(formEditSelectedPlayerPk()));
//    }
//    @Test
//    public void getSelectedAction() {
//        assertEq(TeamCrud.EDIT.getTeamCrudString(),callSimulationBeanSelectedActionGet(formEditSelectedPlayerPk()));
//    }
    @Test
    public void allMovesPkPlayer() {
        assertSizeEq(5,callEditPokemonMovesBeanMovesGet(editEditSelectedPlayerPkListMoves("",false)));
    }
    @Test
    public void availableMovesPkPlayer() {
        assertSizeEq(2,callEditPokemonMovesBeanMovesGet(editEditSelectedPlayerPkListMoves("",true)));
    }
    @Test
    public void availableMovesPkPlayerNone() {
        assertSizeEq(0,callEditPokemonMovesBeanMovesGet(editEditSelectedPlayerPkListMoves(M_POK_01_TR,true)));
    }
    @Test
    public void availableMovesPkPlayerOne() {
        assertSizeEq(1,callEditPokemonMovesBeanMovesGet(editEditSelectedPlayerPkListMoves(M_POK_01_TR,false)));
    }
    @Test
    public void getAvailableMovesOnlyNo() {
        assertFalse(callEditPokemonMovesBeanAvailableMovesOnlyGet(editEditSelectedPlayerPkListMoves("",false)));
    }
    @Test
    public void getAvailableMovesOnlyYes() {
        assertTrue(callEditPokemonMovesBeanAvailableMovesOnlyGet(editEditSelectedPlayerPkListMoves("",true)));
    }
    @Test
    public void getAvailableMovesOnlyDef() {
        assertFalse(callEditPokemonMovesBeanAvailableMovesOnlyGet(editEditSelectedPlayerPkListMoves()));
    }
    @Test
    public void editPkPlayerMoves() {
        assertTrue(callEditPokemonMovesBeanPlayerGet(editEditSelectedPlayerPkListMoves()));
    }
    @Test
    public void editPkPlayerMovesCancel() {
        assertSizeEq(1,callEditPokemonBeanMovesGet(editEditSelectedPlayerPkListMovesCancel()));
    }
    @Test
    public void editPkPlayerClickLinkFirst() {
        assertEq(I_BALL_TR,callEditPokemonBeanTranslateItem(editEditSelectedPlayerPkItemPart(0)));
    }
    @Test
    public void editPkPlayerClickLinkSecond() {
        assertEq(I_MULT_EXP_TR,callEditPokemonBeanTranslateItem(editEditSelectedPlayerPkItemPart(1)));
    }
    @Test
    public void editPkPlayerRemoveItem() {
        assertEq(NULL_REF,callEditPokemonBeanTranslateItem(editEditSelectedPlayerPkItemCancelItem()));
    }
    @Test
    public void editPkPlayerKeepItem() {
        assertEq(I_BALL_TR,callEditPokemonBeanTranslateItem(editEditSelectedPlayerPkItemCancel()));
    }
    @Test
    public void getLevelEdit() {
        assertEq(4,callEditPokemonBeanLevelGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void getExpEdit() {
        assertEq(Rate.zero(),callEditPokemonBeanExperienceGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void getBallEdit() {
        assertEq(NULL_REF,callEditPokemonBeanBallGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void getBallsEdit() {
        assertSizeEq(1,callEditPokemonBeanBallsGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void getBallsEditKey() {
        assertEq(I_BALL,firstStrStr(eltStrStr(callEditPokemonBeanBallsGet(editEditSelectedPlayerPk()),0)));
    }
    @Test
    public void getBallsEditValue() {
        assertEq(I_BALL_TR,secondStrStr(eltStrStr(callEditPokemonBeanBallsGet(editEditSelectedPlayerPk()),0)));
    }
    @Test
    public void getHappinessEdit() {
        assertEq(0,callEditPokemonBeanHappinessGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void getRemainingHp() {
        assertEq(Rate.newRate("384/25"),callEditPokemonBeanRemainingHpGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void getEvEdit() {
        assertSizeEq(6,callEditPokemonBeanEvGet(editEditSelectedPlayerPk()));
    }
    @Test
    public void getEvEditHpTr() {
        assertEq("HP1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),0));
    }
    @Test
    public void getEvEditHp() {
        assertEq(0,callEvLineEvGet(secondStatisticEv(eltStatisticEv(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),0))));
    }
    @Test
    public void getEvEditAttackTr() {
        assertEq("ATTACK1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),1));
    }
    @Test
    public void getEvEditAttack() {
        assertEq(0,callEvLineEvGet(secondStatisticEv(eltStatisticEv(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),1))));
    }
    @Test
    public void getEvEditDefenseTr() {
        assertEq("DEFENSE1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),2));
    }
    @Test
    public void getEvEditDefense() {
        assertEq(0,callEvLineEvGet(secondStatisticEv(eltStatisticEv(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),2))));
    }
    @Test
    public void getEvEditSpecAttackTr() {
        assertEq("SPECIAL_ATTACK1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),3));
    }
    @Test
    public void getEvEditSpecAttack() {
        assertEq(0,callEvLineEvGet(secondStatisticEv(eltStatisticEv(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),3))));
    }
    @Test
    public void getEvEditSpecDefenseTr() {
        assertEq("SPECIAL_DEFENSE1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),4));
    }
    @Test
    public void getEvEditSpecDefense() {
        assertEq(0,callEvLineEvGet(secondStatisticEv(eltStatisticEv(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),4))));
    }
    @Test
    public void getEvEditSpeedTr() {
        assertEq("SPEED1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),5));
    }
    @Test
    public void getEvEditSpeed() {
        assertEq(0,callEvLineEvGet(secondStatisticEv(eltStatisticEv(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),5))));
    }
    @Test
    public void getHealEditOk() {
        assertTrue(callEditPokemonBeanHealGet(editEditSelectedPlayerPkHeal(true)));
    }
    @Test
    public void getHealEditKo() {
        assertFalse(callEditPokemonBeanHealGet(editEditSelectedPlayerPkHeal(false)));
    }
    @Test
    public void getNameFormHeal() {
        assertEq(P_POK_00_TR,callSimulationBeanGetName(editEditSelectedPlayerPkForm(true,Rate.one(),Rate.newRate("382/25")),0));
    }
    @Test
    public void getNameFormTooMuch() {
        assertEq(P_POK_00_TR,callSimulationBeanGetName(editEditSelectedPlayerPkForm(false,Rate.one(),Rate.newRate("386/25")),0));
    }
    @Test
    public void getNameFormZero() {
        assertEq(P_POK_00_TR,callSimulationBeanGetName(editEditSelectedPlayerPkForm(false,Rate.one(),Rate.zero()),0));
    }
    @Test
    public void getBadMovesCountEdit() {
        assertSizeEq(0,callEditPokemonBeanMovesGet(editEditSelectedPlayerPkFormNoMove()));
    }
    @Test
    public void cancelEditingPkPlayer() {
        assertSizeEq(1,callSimulationBeanGetMoves(editEditSelectedPlayerPkFormCancel(),0));
    }
    @Test
    public void koPlayerTeam() {
        assertFalse(callSimulationBeanOkGet(pkPlayerValidateEvosKo()));
    }
    @Test
    public void okPlayerTeam() {
        assertTrue(callSimulationBeanOkGet(pkPlayerValidateEvos()));
    }
    @Test
    public void selectedIndexFalse() {
        assertFalse(callSimulationBeanSelectedIndex(pkPlayerValidateEvosNoSelect()));
    }
    @Test
    public void selectedIndexTrue() {
        assertTrue(callSimulationBeanSelectedIndex(pkPlayerValidateEvosSelect()));
    }
    @Test
    public void noEvoSel() {
        assertSizeEq(0,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosNoSelect()));
    }
    @Test
    public void evoSel() {
        assertSizeEq(1,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelect()));
    }
    @Test
    public void evoSelKey() {
        assertEq(P_POK_03,firstStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelect()),0)));
    }
    @Test
    public void evoSelValue() {
        assertEq(P_POK_03_TR,secondStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelect()),0)));
    }
    @Test
    public void levelEvo() {
        assertEq(41,callSimulationBeanLevelEvoGet(pkPlayerValidateEvoValues()));
    }
    @Test
    public void chosenEvo() {
        assertEq(P_POK_03,callSimulationBeanChosenEvoGet(pkPlayerValidateEvoValues()));
    }
    @Test
    public void levelEvoForm() {
        assertEq(41,callSimulationBeanLevelEvoGet(pkPlayerValidateEvoValidate()));
    }
    @Test
    public void chosenEvoForm() {
        assertEq(P_POK_03,callSimulationBeanChosenEvoGet(pkPlayerValidateEvoValidate()));
    }
    @Test
    public void validateEvoNoRemainEvo() {
        assertSizeEq(0,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvoValidate()));
    }
    @Test
    public void cancelEvo() {
        assertSizeEq(1,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvoValidateThenCancel()));
    }
    @Test
    public void rounds() {
        assertSizeEq(2,callSimulationBeanRoundGet(pkPlayerEvoThenFighters()));
    }
    @Test
    public void roundsFirstKey() {
        assertEq(0,firstIntStrKey(eltIntStr(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void roundsFirstValue() {
        assertEq("0",secondIntStr(eltIntStr(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void roundsSecondKey() {
        assertEq(1,firstIntStrKey(eltIntStr(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void roundsSecondValue() {
        assertEq("1",secondIntStr(eltIntStr(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void placesFights() {
        assertSizeEq(3,callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()));
    }
    @Test
    public void placesFightsFirstKey() {
        assertEq(Fighter.BACK,firstIntStrKey(eltIntStr(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void placesFightsFirstValue() {
        assertEq(NULL_REF,secondIntStr(eltIntStr(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void placesFightsSecondKey() {
        assertEq(0,firstIntStrKey(eltIntStr(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void placesFightsSecondValue() {
        assertEq("0",secondIntStr(eltIntStr(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void placesFightsThirdKey() {
        assertEq(1,firstIntStrKey(eltIntStr(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),2)));
    }
    @Test
    public void placesFightsThirdValue() {
        assertEq("1",secondIntStr(eltIntStr(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),2)));
    }
    @Test
    public void round() {
        assertEq("0",callSimulationBeanSelectedRoundGet(pkPlayerEvoThenFighters()));
    }
    @Test
    public void placesFight() {
        assertEq(Long.toString(Fighter.BACK),callSimulationBeanPlaceFightGet(pkPlayerEvoThenFighters()));
    }
    @Test
    public void noSelectedFrontFighter() {
        assertEq(-1,callSimulationBeanSelectedPkGet(pkPlayerEvoFightersImmediateValid()));
    }
    @Test
    public void validateFighter() {
        assertEq(0,callSimulationBeanSelectedPkGet(pkPlayerEvoFightersFormValid()));
    }
    @Test
    public void koFrontFighters() {
        assertFalse(callSimulationBeanOkGet(pkPlayerEvoFightersWithoutFronts()));
    }
    @Test
    public void okFrontFighters() {
        assertTrue(callSimulationBeanOkGet(pkPlayerEvoFightersSufficientFronts()));
    }
    @Test
    public void isNotAvailMoves() {
        assertFalse(callSimulationBeanIsAvailableMoves(pkPlayerEvoFightersSufficientFronts()));
    }
    @Test
    public void noSelectedIndexMoves() {
        assertFalse(callSimulationBeanSelectedIndexForMoves(pkPlayerEvoFightersSufficientFronts()));
    }
    @Test
    public void selectedIndexMoves() {
        assertTrue(callSimulationBeanSelectedIndexForMoves(pkPlayerEvoFightersSufficientFrontsFormMove(0)));
    }
    @Test
    public void isNotAvailAbilities() {
        assertFalse(callSimulationBeanIsAvailableAbilities(pkPlayerEvoFightersSufficientFrontsFormMove(0)));
    }
    @Test
    public void isAvailAbilities() {
        assertTrue(callSimulationBeanIsAvailableAbilities(pkPlayerEvoFightersSufficientFrontsFormMove(1)));
    }
    @Test
    public void isAvailMoves() {
        assertTrue(callSimulationBeanIsAvailableMoves(pkPlayerEvoFightersSufficientFrontsFormMove(1)));
    }
    @Test
    public void noCurrentAbility() {
        assertEq(NULL_REF,callSimulationBeanCurrentAbilityGet(pkPlayerEvoFightersSufficientFrontsFormMove(0)));
    }
    @Test
    public void currentAbility() {
        assertEq(A_SIM_1,callSimulationBeanCurrentAbilityGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)));
    }
    @Test
    public void abilities() {
        assertSizeEq(2,callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)));
    }
    @Test
    public void abilitiesFirstKey() {
        assertEq(A_SIM_1,firstStrStr(eltStrStr(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),0)));
    }
    @Test
    public void abilitiesFirstValue() {
        assertEq(A_SIM_1_TR,secondStrStr(eltStrStr(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),0)));
    }
    @Test
    public void abilitiesSecondKey() {
        assertEq(A_SIM_2,firstStrStr(eltStrStr(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),1)));
    }
    @Test
    public void abilitiesSecondValue() {
        assertEq(A_SIM_2_TR,secondStrStr(eltStrStr(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),1)));
    }
    @Test
    public void keptMoves() {
        assertSizeEq(3,callSimulationBeanKeptMovesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)));
    }
    @Test
    public void validateMovesQuick() {
        assertSizeEq(3,callSimulationBeanKeptMovesGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(1, "")));
    }
    @Test
    public void validateMovesFull() {
        assertSizeEq(2,callSimulationBeanKeptMovesGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(1, A_SIM_2)));
    }
    @Test
    public void changeAbility() {
        assertEq(NULL_REF,callSimulationBeanCurrentAbilityGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(1, A_SIM_2)));
    }
    @Test
    public void validateMovesQuickChange() {
        assertSizeEq(2,callSimulationBeanKeptMovesGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(0, "")));
    }
    @Test
    public void validateMovesFullTwice() {
        assertSizeEq(2,callSimulationBeanKeptMovesGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuickTwice(1, A_SIM_2)));
    }
    @Test
    public void koMoveSets() {
        assertFalse(callSimulationBeanOkGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersKo()));
    }
    @Test
    public void cancelMoveSets() {
        assertTrue(callSimulationBeanOkGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersCancel()));
    }
    @Test
    public void okMoveSets() {
        assertTrue(callSimulationBeanOkGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersOk()));
    }
    @Test
    public void targetsFight() {
        assertSizeEq(2,callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)));
    }
    @Test
    public void targetsFightFirstKey() {
        assertEq(0,firstIntStrKey(eltIntStr(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),0)));
    }
    @Test
    public void targetsFightFirstValue() {
        assertEq("0",secondIntStr(eltIntStr(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),0)));
    }
    @Test
    public void targetsFightSecondKey() {
        assertEq(1,firstIntStrKey(eltIntStr(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),1)));
    }
    @Test
    public void targetsFightSecondValue() {
        assertEq("1",secondIntStr(eltIntStr(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),1)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound1() {
        assertSizeEq(2,callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,0)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound1IndexFirst() {
        assertEq(0,callRadioLineMoveIndexGet(eltRadioMove(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,0)),0)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound1IndexSecond() {
        assertEq(1,callRadioLineMoveIndexGet(eltRadioMove(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,0)),1)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound2() {
        assertSizeEq(2,callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,1)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound2IndexFirst() {
        assertEq(0,callRadioLineMoveIndexGet(eltRadioMove(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,1)),0)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound2IndexSecond() {
        assertEq(1,callRadioLineMoveIndexGet(eltRadioMove(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,1)),1)));
    }
    @Test
    public void allyChoiceSet1() {
        assertFalse(callSimulationBeanAllyChoiceGet(pkPlayerEvoFighterChoiceAfter(0,0,false,0,0)));
    }
    @Test
    public void allyChoiceSet2() {
        assertTrue(callSimulationBeanAllyChoiceGet(pkPlayerEvoFighterChoiceAfter(1,1,true,0,0)));
    }
    @Test
    public void targetChoice() {
        assertEq("1",callSimulationBeanTargetGet(pkPlayerEvoFighterChoiceAfter(0,0,true,0,1)));
    }
    @Test
    public void moveChoice() {
        assertEq(1,callSimulationBeanSelectedMoveGet(pkPlayerEvoFighterChoiceAfter(0,0,true,1,0)));
    }
    @Test
    public void moveChoiceNo() {
        assertEq(-1,callSimulationBeanSelectedMoveGet(pkPlayerEvoFighterChoiceAfter(0,0,true,-1,0)));
    }
    @Test
    public void getDisplayIfError1() {
        assertFalse(callSimulationBeanDisplayIfErrorGet(pkPlayerEvoFighterChoiceAfter(0,0,true,0,0)));
    }
    @Test
    public void getDisplayIfError2() {
        assertTrue(callSimulationBeanDisplayIfErrorGet(pkPlayerEvoFightersWithoutFronts()));
    }
    @Test
    public void simulateFightOk() {
        assertTrue(callSimulationBeanOkGet(pkPlayerFighterSimulate()));
    }
    @Test
    public void simulateFightEvosOk() {
        assertTrue(callSimulationBeanOkGet(pkPlayerEvoFighterSimulate()));
    }
    @Test
    public void simulateFightEvosKo() {
        assertFalse(callSimulationBeanOkGet(pkPlayerEvoFighterSimulateKo()));
    }
    @Test
    public void simulateFightEvosOkIssue() {
        assertFalse(callSimulationBeanIsIssue(pkPlayerEvoFighterSimulate()));
    }
    @Test
    public void simulateFightEvosKoIssue() {
        assertTrue(callSimulationBeanIsIssue(pkPlayerEvoFighterSimulateKo()));
    }
    @Test
    public void simulateFightEvosKosIssue() {
        assertTrue(callSimulationBeanIsIssue(pkPlayerEvoFighterSimulateKos()));
    }
    @Test
    public void skipEvosNoGuessSkip() {
        assertTrue(callSimulationBeanOkGet(pkPlayerFighterSkipEvosStateBadNbCount()));
    }
    @Test
    public void skipEvosEmpty() {
        assertFalse(callSimulationBeanOkGet(pkPlayerFighterSkipEvosStateEmpty()));
    }
    @Test
    public void oneFight() {
        assertFalse(callSimulationBeanIsFightAfter(pkPlayerFighterSimulateOneFight()));
    }
    @Test
    public void issueNoFight() {
        assertFalse(callSimulationBeanIsFightAfter(pkPlayerEvoFighterSimulateKos()));
    }
    @Test
    public void twoFights() {
        assertTrue(callSimulationBeanIsFightAfter(pkPlayerFighterSimulate()));
    }
    @Test
    public void getTeamAfterFight() {
        assertSizeEq(4,callSimulationBeanTeamAfterFightGet(pkPlayerFighterSimulateOneFight()));
    }
    @Test
    public void pokemonPlayerGetItem() {
        assertEq(I_BALL,callPokemonPlayerGetItem(eltPkPl(callSimulationBeanTeamAfterFightGet(pkPlayerFighterSimulateOneFight()),0)));
    }
    @Test
    public void pokemonPlayerGetHappiness() {
        assertEq(48,callPokemonPlayerGetHappiness(eltPkPl(callSimulationBeanTeamAfterFightGet(pkPlayerFighterSimulateOneFight()),0)));
    }
    @Test
    public void pokemonPlayergetWonExpSinceLastLevel() {
        assertEq(Rate.newRate("0"),callPokemonPlayerGetWonExpSinceLastLevel(eltPkPl(callSimulationBeanTeamAfterFightGet(pkPlayerFighterSimulateOneFight()),0)));
    }
    @Test
    public void getImageAfterFight() {
        assertEq(one(IMG_04),callSimulationBeanGetImageAfterFight(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void getNameAfterFight() {
        assertEq(P_POK_04_TR,callSimulationBeanGetNameAfterFight(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void getGenderAfterFight() {
        assertEq(NO_G,callSimulationBeanGetGenderAfterFight(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void getLevelAfterFight() {
        assertEq(65,callSimulationBeanGetLevelAfterFight(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void getAbilityAfterFight() {
        assertEq(A_SIM_1_TR,callSimulationBeanGetAbilityAfterFight(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void getItemAfterFight() {
        assertEq(I_BALL_TR,callSimulationBeanGetItemAfterFight(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void getMovesAfterFight() {
        assertSizeEq(2,callSimulationBeanGetMovesAfterFight(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void getMovesAfterFightFirst() {
        assertEq(M_POK_01_TR,elt(callSimulationBeanGetMovesAfterFight(pkPlayerFighterSimulateOneFight(),0),0));
    }
    @Test
    public void getMovesAfterFightSecond() {
        assertEq(M_POK_02_TR,elt(callSimulationBeanGetMovesAfterFight(pkPlayerFighterSimulateOneFight(),0),1));
    }
    @Test
    public void getRemainingLifeRate() {
        assertEq(LgInt.newLgInt("100"),callSimulationBeanGetRemainingLifeRate(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void numberNecessaryPointsForGrowingLevel() {
        assertEq(Rate.newRate("1"),callSimulationBeanNumberNecessaryPointsForGrowingLevel(pkPlayerFighterSimulateOneFight(),0));
    }
    @Test
    public void nextFight() {
        assertTrue(callSimulationBeanOkGet(pkPlayerFighterSimulate()));
    }
    @Test
    public void noSelectAfterFight() {
        assertEq(-1,callSimulationBeanSelectedPkGet(pkPlayerFighterSimulateAfterFight()));
    }
    @Test
    public void getEvolutionsAfterFight() {
        assertSizeEq(2,callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOne()));
    }
    @Test
    public void getEvolutionsAfterFightFirstKey() {
        assertEq(P_POK_04,firstStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),0)));
    }
    @Test
    public void getEvolutionsAfterFightFirstValue() {
        assertEq(P_POK_04_TR,secondStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),0)));
    }
    @Test
    public void getEvolutionsAfterFightSecondKey() {
        assertEq(P_POK_05,firstStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),1)));
    }
    @Test
    public void getEvolutionsAfterFightSecondValue() {
        assertEq(P_POK_05_TR,secondStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),1)));
    }
    @Test
    public void getKeptMovesAfterFight() {
        assertSizeEq(3,callSimulationBeanKeptMovesAfterFightGet(pkPlayerFighterSimulateAfterFightOne()));
    }
    @Test
    public void getAbilitiesAfterFight() {
        assertSizeEq(2,callSimulationBeanAbilitiesAfterFightGet(pkPlayerFighterSimulateAfterFightOne()));
    }
    @Test
    public void getAbilitiesAfterFightFirstKey() {
        assertEq(A_SIM_1,firstStrStr(eltStrStr(callSimulationBeanAbilitiesAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),0)));
    }
    @Test
    public void getAbilitiesAfterFightFirstValue() {
        assertEq(A_SIM_1_TR,secondStrStr(eltStrStr(callSimulationBeanAbilitiesAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),0)));
    }
    @Test
    public void getAbilitiesAfterFightSecondKey() {
        assertEq(A_SIM_2,firstStrStr(eltStrStr(callSimulationBeanAbilitiesAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),1)));
    }
    @Test
    public void getAbilitiesAfterFightSecondValue() {
        assertEq(A_SIM_2_TR,secondStrStr(eltStrStr(callSimulationBeanAbilitiesAfterFightGet(pkPlayerFighterSimulateAfterFightOne()),1)));
    }
    @Test
    public void getEvolutionAfterFight() {
        assertEq(P_POK_04,callSimulationBeanEvolutionAfterFightGet(pkPlayerFighterSimulateAfterFightOne()));
    }
    @Test
    public void getAbilityAfterFightCh() {
        assertEq(A_SIM_1,callSimulationBeanAbilityAfterFightGet(pkPlayerFighterSimulateAfterFightOne()));
    }
    @Test
    public void validateAfterFight() {
        assertTrue(callSimulationBeanOkGet(pkPlayerFighterSimulateAfterFightOneValidate()));
    }
    @Test
    public void cancelEvolutionAfterFight() {
        assertEq(P_POK_04,callSimulationBeanEvolutionAfterFightGet(pkPlayerFighterSimulateAfterFightCancelOne()));
    }
    @Test
    public void koFoes() {
        assertSizeEq(4,callSimulationBeanGetKoFoes(pkPlayerFighterSimulate()));
    }
    @Test
    public void notKoFoes() {
        assertSizeEq(0,callSimulationBeanGetNotKoFrontFoes(pkPlayerFighterSimulate()));
    }
    @Test
    public void notKo() {
        assertSizeEq(0,callSimulationBeanGetKoPlayers(pkPlayerFighterSimulate()));
    }
    @Test
    public void comment() {
        assertSizeEq(0,pkPlayerFighterSimulateComment());
    }
    @Test
    public void isEvolutionAfterFightStateNo() {
        assertFalse(callSimulationBeanIsEvolutionAfterFightState(pkPlayerFighterSimulateAfterFightCancel()));
    }
    @Test
    public void isEvolutionAfterFightStateYes() {
        assertTrue(callSimulationBeanIsEvolutionAfterFightState(pkPlayerFighterSimulateAfterFight()));
    }
    @Test
    public void isFightStateNo() {
        assertFalse(callSimulationBeanIsSimulationState(pkPlayerFighterSimulateAfterFightCancel2()));
    }
    @Test
    public void isFightStateYes() {
        assertTrue(callSimulationBeanIsSimulationState(pkPlayerFighterSimulateAfterFightCancel()));
    }
    @Test
    public void isMovesFightStateNo() {
        assertFalse(callSimulationBeanIsMovesFightState(pkPlayerFighterSimulateAfterFightCancel3()));
    }
    @Test
    public void isMovesFightStateYes() {
        assertTrue(callSimulationBeanIsMovesFightState(pkPlayerFighterSimulateAfterFightCancel2()));
    }
    @Test
    public void isMovesStateNo() {
        assertFalse(callSimulationBeanIsMovesState(pkPlayerFighterSimulateAfterFightCancel3()));
    }
    @Test
    public void isMovesStateYes() {
        assertTrue(callSimulationBeanIsMovesState(pkPlayerEvoFighterSimulateStMove()));
    }
    @Test
    public void isFrontStateNo() {
        assertFalse(callSimulationBeanIsFrontState(pkPlayerFighterSimulateAfterFightCancel2()));
    }
    @Test
    public void isFrontStateYes() {
        assertTrue(callSimulationBeanIsFrontState(pkPlayerFighterSimulateAfterFightCancel3()));
    }
    @Test
    public void isEvoStateNo() {
        assertFalse(callSimulationBeanIsEvolutionsState(pkPlayerFighterSimulateAfterFightCancel2()));
    }
    @Test
    public void isEvoStateYes() {
        assertTrue(callSimulationBeanIsEvolutionsState(pkPlayerEvoFighterSimulateStMoveCancel()));
    }
    @Test
    public void isTeamStateNo() {
        assertFalse(callSimulationBeanIsTeamState(pkPlayerFighterSimulateAfterFightCancel2()));
    }
    @Test
    public void isTeamStateYes() {
        assertTrue(callSimulationBeanIsTeamState(pkPlayerEvoFighterSimulateStMoveCancel2()));
    }
    @Test
    public void isFoeStateNo() {
        assertFalse(callSimulationBeanIsFoeState(pkPlayerFighterSimulate()));
    }
    @Test
    public void isFoeStateYes() {
        assertTrue(callSimulationBeanIsFoeState(validateDiff(2)));
    }
    @Test
    public void back() {
        assertTrue(callSimulationBeanIsDiffState(pkPlayerFighterSimulateAfterFightCancel4()));
    }
    @Test
    public void evoSelFirst() {
        assertSizeEq(1,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwo()));
    }
    @Test
    public void evoSelKeyFirst() {
        assertEq(P_POK_01,firstStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwo()),0)));
    }
    @Test
    public void evoSelValueFirst() {
        assertEq(P_POK_01_TR,secondStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwo()),0)));
    }
    @Test
    public void evoSelSecond() {
        assertSizeEq(1,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwoOnce()));
    }
    @Test
    public void evoSelKeySecond() {
        assertEq(P_POK_02,firstStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwoOnce()),0)));
    }
    @Test
    public void evoSelValueSecond() {
        assertEq(P_POK_02_TR,secondStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwoOnce()),0)));
    }
    @Test
    public void evoSelThird() {
        assertSizeEq(1,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwoTwice()));
    }
    @Test
    public void evoSelKeyThird() {
        assertEq(P_POK_03,firstStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwoTwice()),0)));
    }
    @Test
    public void evoSelValueThird() {
        assertEq(P_POK_03_TR,secondStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwoTwice()),0)));
    }
    @Test
    public void evoSelFourth() {
        assertSizeEq(0,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTwoThreeTimes()));
    }
    @Test
    public void evoSelTree() {
        assertSizeEq(2,callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTree()));
    }
    @Test
    public void evoSelTreeKeyFirst() {
        assertEq(P_POK_01,firstStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTree()),0)));
    }
    @Test
    public void evoSelTreeValueFirst() {
        assertEq(P_POK_01_TR,secondStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTree()),0)));
    }
    @Test
    public void evoSelTreeKeySecond() {
        assertEq(P_POK_02,firstStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTree()),1)));
    }
    @Test
    public void evoSelTreeValueSecond() {
        assertEq(P_POK_02_TR,secondStrStr(eltStrStr(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelectTree()),1)));
    }
    @Test
    public void getEvolutionsAfterFightTree() {
        assertSizeEq(3,callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOneLight()));
    }
    @Test
    public void getEvolutionsAfterFightTreeFirstKey() {
        assertEq(P_POK_00,firstStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOneLight()),0)));
    }
    @Test
    public void getEvolutionsAfterFightTreeFirstValue() {
        assertEq(P_POK_00_TR,secondStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOneLight()),0)));
    }
    @Test
    public void getEvolutionsAfterFightTreeSecondKey() {
        assertEq(P_POK_01,firstStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOneLight()),1)));
    }
    @Test
    public void getEvolutionsAfterFightTreeSecondValue() {
        assertEq(P_POK_01_TR,secondStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOneLight()),1)));
    }
    @Test
    public void getEvolutionsAfterFightTreeThirdKey() {
        assertEq(P_POK_02,firstStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOneLight()),2)));
    }
    @Test
    public void getEvolutionsAfterFightTreeThirdValue() {
        assertEq(P_POK_02_TR,secondStrStr(eltStrStr(callSimulationBeanEvolutionsAfterFightGet(pkPlayerFighterSimulateAfterFightOneLight()),2)));
    }
    @Test
    public void getPlaces() {
        assertSizeEq(4,callSimulationBeanPlacesGet(chooseTrainer()));
    }
    @Test
    public void getRealStepNumber() {
        assertEq(2,callSimulationBeanGetRealStepNumber(chooseTrainer()));
    }
    @Test
    public void layersSingle() {
        assertEq(1,callSimulationBeanLayers(chooseTrainer(),0));
    }
    @Test
    public void layersMult() {
        assertEq(2,callSimulationBeanLayers(chooseTrainer(),2));
    }
    @Test
    public void multLayersSingle() {
        assertFalse(callSimulationBeanIsMultiLayer(chooseTrainer(),0));
    }
    @Test
    public void multLayersMult() {
        assertTrue(callSimulationBeanIsMultiLayer(chooseTrainer(),2));
    }
    @Test
    public void getNoFight1() {
        assertEq(0,callSimulationLevelBeanNoFightGet(chooseTrainerLevelZero(0)));
    }
    @Test
    public void getNoFight2() {
        assertEq(0,callSimulationLevelBeanNoFightGet(chooseTrainerLevelZero(1)));
    }
    @Test
    public void getNoFight3() {
        assertEq(0,callSimulationLevelBeanNoFightGet(chooseTrainerLevel(2,0)));
    }
    @Test
    public void getNoFight4() {
        assertEq(0,callSimulationLevelBeanNoFightGet(chooseTrainerLevel(2,1)));
    }
    @Test
    public void getNoFight5() {
        assertEq(-1,callSimulationLevelBeanNoFightGet(chooseTrainerLevelZero(1,-1,1)));
    }
    @Test
    public void getNoFight6() {
        assertEq(-1,callSimulationLevelBeanNoFightGet(chooseTrainerLevelZero(0,-1,1)));
    }
    @Test
    public void getNoFight7() {
        assertEq(2,callSimulationLevelBeanNoFightGet(chooseTrainerLevel(1,2,0)));
    }
    @Test
    public void getNoFight8() {
        assertEq(2,callSimulationLevelBeanNoFightGet(chooseTrainerLevel(0,2,0)));
    }
    @Test
    public void getNoFight9() {
        assertEq(2,callSimulationLevelBeanNoFightGet(chooseTrainerLevel(1,2,4)));
    }
    @Test
    public void getTrainerNameNo() {
        assertEq(DataBase.EMPTY_STRING,callSimulationBeanGetTrainerName(chooseTrainer()));
    }
    @Test
    public void getTrainerNameLeagueOne() {
        assertEq(PL_4+" "+T_L_1+" 0",callSimulationBeanGetTrainerName(chooseTrainerLevel(3,0)));
    }
    @Test
    public void getTrainerNameLeagueTwo() {
        assertEq(PL_4+" "+T_L_2+" 0",callSimulationBeanGetTrainerName(chooseTrainerLevel(3,1)));
    }
    @Test
    public void getTrainerNameGymTr() {
        assertEq(PL_2+" "+G_L_1+" 0",callSimulationBeanGetTrainerName(chooseTrainerLevelZero(1,-1,0)));
    }
    @Test
    public void getTrainerNameGymLeader() {
        assertEq(PL_2+" "+G_L_1+" 0",callSimulationBeanGetTrainerName(chooseTrainerLevelZero(1,0,2)));
    }
    @Test
    public void getTrainerNameDual1() {
        assertEq(PL_3+" 0 "+D_T_1+" "+D_T_2+" 0",callSimulationBeanGetTrainerName(chooseTrainerLevel(0,-1,1)));
    }
    @Test
    public void getTrainerNameDual2() {
        assertEq(PL_3+" 0 "+D_T_1+" "+D_T_2+" 0",callSimulationBeanGetTrainerName(chooseTrainerLevel(0,-1,2)));
    }
    @Test
    public void getTrainerNameMult1() {
        assertEq(PL_3+" 1  0",callSimulationBeanGetTrainerName(chooseTrainerLevel(1,0,1)));
    }
    @Test
    public void getTrainerNameMult2() {
        assertEq(PL_3+" 1  1",callSimulationBeanGetTrainerName(chooseTrainerLevel(1,1,1)));
    }
    @Test
    public void getTrainerNameMult3() {
        assertEq(PL_3+" 1  1",callSimulationBeanGetTrainerName(chooseTrainerLevel(1,2,1)));
    }
    @Test
    public void okTrainer() {
        assertTrue(callSimulationBeanOkGet(chooseTrainerLevelDualValidate()));
    }
    @Test
    public void koTrainer() {
        assertFalse(callSimulationBeanOkGet(chooseTrainerLevelDualValidateKo()));
    }
    @Test
    public void realLeague() {
        assertTrue(callSimulationBeanOkGet(simuLeagueReal()));
    }
    @Test
    public void virtualLeague() {
        assertTrue(callSimulationBeanOkGet(simuLeagueVirtual()));
    }
    @Test
    public void realLeagueLevel1() {
        assertEq(95,callSimulationBeanGetLevelAfterFight(simuLeagueReal(),0));
    }
    @Test
    public void realLeagueLevel2() {
        assertEq(95,callSimulationBeanGetLevelAfterFight(simuLeagueReal(),1));
    }
    @Test
    public void virtualLeagueLevel1() {
        assertEq(95,callSimulationBeanGetLevelAfterFight(simuLeagueVirtual(),0));
    }
    @Test
    public void virtualLeagueLevel2() {
        assertEq(95,callSimulationBeanGetLevelAfterFight(simuLeagueVirtual(),1));
    }
    @Test
    public void lightReal1() {
        assertEq(71,callSimulationBeanGetLevelAfterFight(simuLeagueRealSec(),0));
    }
    @Test
    public void lightReal2() {
        assertEq(71,callSimulationBeanGetLevelAfterFight(simuLeagueRealSec(),1));
    }
    @Test
    public void simuSteps0() {
        assertSame(SimulationSteps.TEAM,editEditSelectedPlayerPkSimuStepsBack().getForms().getValSimStep(CommonBean.SIMU_CST_SIMULATION_STATE));
        assertEq(2,editEditSelectedPlayerPkSimuStepsNbFleeAttempt());
        assertEq(Fight.toFoeFighter(0).getTeam(),editEditSelectedPlayerPkSimuStepsIndexUserTeam().getTeam());
        assertEq(Fight.toFoeFighter(0).getPosition(),editEditSelectedPlayerPkSimuStepsIndexUserTeam().getPosition());
        assertSame(FightState.APPRENDRE_EVOLUER,editEditSelectedPlayerPkSimuStepsIndexFightState());
        assertEq(new LgInt(2),editEditSelectedPlayerPkSimuStepsNbRounds());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsWinningMoney());
        assertEq(1,editEditSelectedPlayerPkSimuStepsLostObjects(I_BALL).size());
        assertEq(I_BALL,editEditSelectedPlayerPkSimuStepsLostObjects(I_BALL).get(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsCaughtEvolutions(P_POK_00).size());
        assertEq(P_POK_00,editEditSelectedPlayerPkSimuStepsCaughtEvolutions(P_POK_00).get(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFirstPositPlayerFighters().size());
        assertEq(0,editEditSelectedPlayerPkSimuStepsFirstPositPlayerFighters().getKey(0));
        assertEq(Fighter.BACK,editEditSelectedPlayerPkSimuStepsFirstPositPlayerFighters().getValue(0));
        assertEq(4,editEditSelectedPlayerPkSimuStepsFirstPositFoeFighters().size());
        assertEq(0,editEditSelectedPlayerPkSimuStepsFirstPositFoeFighters().getKey(0));
        assertEq(Fighter.BACK,editEditSelectedPlayerPkSimuStepsFirstPositFoeFighters().getValue(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsUsedItemsWhileRound().size());
        assertEq(I_BALL,editEditSelectedPlayerPkSimuStepsUsedItemsWhileRound().getKey(0));
        assertEq(2,editEditSelectedPlayerPkSimuStepsUsedItemsWhileRound().getValue(0));
        assertEq(0,editEditSelectedPlayerPkSimuStepsUsedItemsWhileRoundRem().size());
        assertEq(1,editEditSelectedPlayerPkSimuStepsChoices().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsChoices().getKey(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsChoices().getValue(0).getKeptMoves().size());
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsChoices().getValue(0).getKeptMoves().get(0));
        assertEq(A_SIM_1,editEditSelectedPlayerPkSimuStepsChoices().getValue(0).getAbility());
        assertEq(P_POK_04,editEditSelectedPlayerPkSimuStepsChoices().getValue(0).getName());
        assertEq(0,editEditSelectedPlayerPkSimuStepsChoicesRem().size());
        assertEq(1,editEditSelectedPlayerPkSimuStepsAllyChoice().size());
        assertEq(M_POK_03,editEditSelectedPlayerPkSimuStepsAllyChoice().getKey(0).getMove());
        assertEq(0,editEditSelectedPlayerPkSimuStepsAllyChoice().getKey(0).getTarget().getTeam());
        assertEq(1,editEditSelectedPlayerPkSimuStepsAllyChoice().getKey(0).getTarget().getPosition());
        assertEq(M_POK_04,editEditSelectedPlayerPkSimuStepsAllyChoice().getValue(0).getMove());
        assertEq(1,editEditSelectedPlayerPkSimuStepsAllyChoice().getValue(0).getTarget().getTeam());
        assertEq(0,editEditSelectedPlayerPkSimuStepsAllyChoice().getValue(0).getTarget().getPosition());
        assertEq(0,editEditSelectedPlayerPkSimuStepsAllyChoiceRem().size());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMoves(false).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMoves(false).getVal(M_POK_07).getNbTurn());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMoves(false).getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMoves(true).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMoves(true).getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMoves(true).getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsStillEnabledMoves(false).size());
        assertEq(BoolVal.FALSE,editEditSelectedPlayerPkSimuStepsStillEnabledMoves(false).getVal(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsStillEnabledMoves(true).size());
        assertEq(BoolVal.TRUE,editEditSelectedPlayerPkSimuStepsStillEnabledMoves(true).getVal(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsPlayerFightersAgainstFoe().size());
        assertEq(1,editEditSelectedPlayerPkSimuStepsPlayerFightersAgainstFoe().getVal(0).size());
        assertEq(1,editEditSelectedPlayerPkSimuStepsPlayerFightersAgainstFoe().getVal(0).get(0));
        assertEq(2,editEditSelectedPlayerPkSimuStepsNbKoRoundTeam());
        assertEq(2,editEditSelectedPlayerPkSimuStepsNbKoPreviousRoundTeam());
        assertEq(1,editEditSelectedPlayerPkSimuStepsSuccessfulMovesRoundTeam(M_POK_07).size());
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsSuccessfulMovesRoundTeam(M_POK_07).get(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsNbUsesMovesTeam().size());
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsNbUsesMovesTeam().getKey(0));
        assertEq(2,editEditSelectedPlayerPkSimuStepsNbUsesMovesTeam().getValue(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsNbUsesMovesRoundTeam().size());
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsNbUsesMovesRoundTeam().getKey(0));
        assertEq(2,editEditSelectedPlayerPkSimuStepsNbUsesMovesRoundTeam().getValue(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeam(false).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeam(false).getVal(M_POK_07).getNbTurn());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMovesTeam(false).getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeam(true).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeam(true).getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMovesTeam(true).getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesWhileSendingFoeTeam(false).size());
        assertEq(BoolVal.FALSE,editEditSelectedPlayerPkSimuStepsEnabledMovesWhileSendingFoeTeam(false).getVal(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesWhileSendingFoeTeam(true).size());
        assertEq(BoolVal.TRUE,editEditSelectedPlayerPkSimuStepsEnabledMovesWhileSendingFoeTeam(true).getVal(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesWhileSendingFoeUsesTeam().size());
        assertEq(new LgInt(2),editEditSelectedPlayerPkSimuStepsEnabledMovesWhileSendingFoeUsesTeam().getVal(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(false).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(false).getVal(M_POK_07).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(false).getVal(M_POK_07).getValue(0).getNbRounds());
        assertEq(new Rate(3),editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(false).getVal(M_POK_07).getValue(0).getDamage());
        assertEq(0,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(false).getVal(M_POK_07).getValue(0).getTargetPosition().getTeam());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(false).getVal(M_POK_07).getValue(0).getTargetPosition().getPosition());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(false).getVal(M_POK_07).getValue(0).isIncrementing());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(true).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(true).getVal(M_POK_07).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(true).getVal(M_POK_07).getValue(0).getNbRounds());
        assertEq(new Rate(3),editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(true).getVal(M_POK_07).getValue(0).getDamage());
        assertEq(0,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(true).getVal(M_POK_07).getValue(0).getTargetPosition().getTeam());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(true).getVal(M_POK_07).getValue(0).getTargetPosition().getPosition());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamAnt(true).getVal(M_POK_07).getValue(0).isIncrementing());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, false).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, false).getVal(M_POK_07).size());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, false).getVal(M_POK_07).getValue(0).isFirstStacked());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, false).getVal(M_POK_07).getValue(0).isLastStacked());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, false).getVal(M_POK_07).getValue(0).getNbRounds());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, false).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, false).getVal(M_POK_07).size());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, false).getVal(M_POK_07).getValue(0).isFirstStacked());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, false).getVal(M_POK_07).getValue(0).isLastStacked());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, false).getVal(M_POK_07).getValue(0).getNbRounds());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, true).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, true).getVal(M_POK_07).size());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, true).getVal(M_POK_07).getValue(0).isFirstStacked());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, true).getVal(M_POK_07).getValue(0).isLastStacked());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(false, true).getVal(M_POK_07).getValue(0).getNbRounds());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, true).size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, true).getVal(M_POK_07).size());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, true).getVal(M_POK_07).getValue(0).isFirstStacked());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, true).getVal(M_POK_07).getValue(0).isLastStacked());
        assertEq(2,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamSou(true, true).getVal(M_POK_07).getValue(0).getNbRounds());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamCombo(false).size());
        assertEq(4,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamCombo(false).get(0).getCombo().getNbTurn());
        assertFalse(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamCombo(false).get(0).getCombo().isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamCombo(true).size());
        assertEq(4,editEditSelectedPlayerPkSimuStepsEnabledMovesTeamCombo(true).get(0).getCombo().getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsEnabledMovesTeamCombo(true).get(0).getCombo().isEnabled());
        assertEq(P_POK_00,editEditSelectedPlayerPkSimuStepsFighterName(P_POK_00));
        assertEq("",editEditSelectedPlayerPkSimuStepsFighterNickname(""));
        assertSame(Gender.NO_GENDER,editEditSelectedPlayerPkSimuStepsFighterGender(Gender.NO_GENDER));
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterWeight());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterHeight());
        assertEq(P_POK_00,editEditSelectedPlayerPkSimuStepsFighterCurrentName(P_POK_00));
        assertSame(Gender.NO_GENDER,editEditSelectedPlayerPkSimuStepsFighterCurrentGender(Gender.NO_GENDER));
        assertEq(I_BALL,editEditSelectedPlayerPkSimuStepsFighterLastUsedItem(I_BALL));
        assertEq(I_BALL,editEditSelectedPlayerPkSimuStepsFighterItem(I_BALL));
        assertEq(A_SIM_1,editEditSelectedPlayerPkSimuStepsFighterAbility(A_SIM_1));
        assertEq(A_SIM_1,editEditSelectedPlayerPkSimuStepsFighterCurrentAbility(A_SIM_1));
        assertEq(new LgInt(2),editEditSelectedPlayerPkSimuStepsFighterNbRounds());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterTypes(T_SIM_1).size());
        assertEq(T_SIM_1,editEditSelectedPlayerPkSimuStepsFighterTypes(T_SIM_1).get(0));
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterRemainingHp());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterClone());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterProtectedAgainstMoveTypes(T_SIM_1).size());
        assertEq(T_SIM_1,editEditSelectedPlayerPkSimuStepsFighterProtectedAgainstMoveTypes(T_SIM_1).get(0));
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterActed(false));
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterActed(true));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterGroundPlace());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterGroundPlaceSubst());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterWonExp());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterWonExpSinceLastLevel());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterLevel());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterHappiness());
        assertEq(I_BALL,editEditSelectedPlayerPkSimuStepsFighterUsedBallCatching(I_BALL));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterNbPrepaRound());
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterDisappeared(false));
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterDisappeared(true));
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterNeedingToRecharge(false));
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterNeedingToRecharge(true));
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsFighterLastSufferedMove(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterLastSufferedMoveTypes(T_SIM_1).size());
        assertEq(T_SIM_1,editEditSelectedPlayerPkSimuStepsFighterLastSufferedMoveTypes(T_SIM_1).get(0));
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsFighterLastUsedMove(M_POK_07));
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsFighterUsedMoveLastRound(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterAlreadyInvokedMovesRound(M_POK_07).size());
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsFighterAlreadyInvokedMovesRound(M_POK_07).get(0));
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsFighterLastSuccessfulMove(M_POK_07));
        assertEq(new LgInt(2),editEditSelectedPlayerPkSimuStepsFighterNbRepeatingSuccessfulMoves());
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterUsingItem(false));
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterUsingItem(true));
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterSuccessfulMove(false));
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterSuccessfulMove(true));
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterChanged(false));
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterChanged(true));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterMovesToBeLearnt(M_POK_07).size());
        assertEq(M_POK_07,editEditSelectedPlayerPkSimuStepsFighterMovesToBeLearnt(M_POK_07).get(0));
        assertSame(KindAction.MOVE,SimulationFighterForm.kindAction(editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)));
        assertEq(M_POK_07,((ActionMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getFirstChosenMove());
        assertEq(M_POK_06,((ActionMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getFinalChosenMove());
        assertEq(1,((ActionMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getSubstitute());
        assertEq(0,((ActionMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,TargetCoords.def().getTeam(),TargetCoords.def().getPosition(),I_BALL,1)).getChosenTargets().size());
        assertEq(1,((ActionMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getChosenTargets().size());
        assertEq(0,((ActionMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getChosenTargets().get(0).getTeam());
        assertEq(2,((ActionMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getChosenTargets().get(0).getPosition());
        assertSame(KindAction.HEAL_MOVE,SimulationFighterForm.kindAction(editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL_MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)));
        assertFalse(((ActionHealMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL_MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).isTeam());
        assertTrue(((ActionHealMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL_MOVE,M_POK_07,M_POK_06,true,0,2,I_BALL,1)).isTeam());
        assertEq(M_POK_07,((ActionHealMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL_MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getFirstChosenMove());
        assertEq(I_BALL,((ActionHealMove)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL_MOVE,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getChosenHealingItem());
        assertSame(KindAction.HEAL,SimulationFighterForm.kindAction(editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL,M_POK_07,M_POK_06,false,0,2,I_BALL,1)));
        assertFalse(((ActionSimpleHeal)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).isTeam());
        assertTrue(((ActionSimpleHeal)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL,M_POK_07,M_POK_06,true,0,2,I_BALL,1)).isTeam());
        assertEq(I_BALL,((ActionSimpleHeal)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.HEAL,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getChosenHealingItem());
        assertSame(KindAction.SWITCH,SimulationFighterForm.kindAction(editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.SWITCH,M_POK_07,M_POK_06,false,0,2,I_BALL,1)));
        assertEq(1,((ActionSwitch)editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.SWITCH,M_POK_07,M_POK_06,false,0,2,I_BALL,1)).getSubstitute());
        assertSame(KindAction.NO,SimulationFighterForm.kindAction(editEditSelectedPlayerPkSimuStepsFighterActionMove(KindAction.NO,M_POK_07,M_POK_06,false,0,2,I_BALL,1)));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterStatus().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterStatus().getValue(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterNbUsesMoves().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterNbUsesMoves().getValue(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesForAlly(false).size());
        assertEq(BoolVal.FALSE,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesForAlly(false).getVal(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesForAlly(true).size());
        assertEq(BoolVal.TRUE,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesForAlly(true).getVal(M_POK_07));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesProt().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesProt().getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterEnabledMovesProt().getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesConstChoices().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesConstChoices().getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterEnabledMovesConstChoices().getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesEndRound().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesEndRound().getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterEnabledMovesEndRound().getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesUnprot().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledMovesUnprot().getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterEnabledMovesUnprot().getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledChangingTypesMoves().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledChangingTypesMoves().getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterEnabledChangingTypesMoves().getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEnabledCounteringMoves().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledCounteringMoves().getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterEnabledCounteringMoves().getVal(M_POK_07).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterCopiedMoves().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterCopiedMoves().getVal(M_POK_07).getPp());
        assertEq(M_POK_06,editEditSelectedPlayerPkSimuStepsFighterCopiedMoves().getVal(M_POK_07).getMove());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledMoves().size());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEnabledMoves().getVal(M_POK_07).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterEnabledMoves().getVal(M_POK_07).isEnabled());
        assertEq(0,editEditSelectedPlayerPkSimuStepsFighterEnabledMoves().getVal(M_POK_08).getNbTurn());
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterEnabledMoves().getVal(M_POK_08).isEnabled());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterDamageRateInflictedByType().size());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterDamageRateInflictedByType().getVal(T_SIM_1));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterDamageRateSufferedByType().size());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterDamageRateSufferedByType().getVal(T_SIM_1));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterDamageSufferedCateg().size());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterDamageSufferedCateg().getVal(C_SIM_1));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterDamageSufferedCategRound().size());
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterDamageSufferedCategRound().getVal(C_SIM_1));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterEv().getVal(Statistic.SPEED));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterIv().getVal(Statistic.SPEED));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterStatisBoost().getVal(Statistic.SPEED));
        assertEq(new Rate(2),editEditSelectedPlayerPkSimuStepsFighterStatisBase().getVal(Statistic.SPEED));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterMoves().getVal(M_POK_00).getCurrent());
        assertEq(5,editEditSelectedPlayerPkSimuStepsFighterMoves().getVal(M_POK_00).getMax());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterMovesCurr().getVal(M_POK_00).getCurrent());
        assertEq(5,editEditSelectedPlayerPkSimuStepsFighterMovesCurr().getVal(M_POK_00).getMax());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterMovesAdd().getVal(M_POK_07).getCurrent());
        assertEq(5,editEditSelectedPlayerPkSimuStepsFighterMovesAdd().getVal(M_POK_07).getMax());
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterMovesRem().contains(M_POK_07));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterMovesCurrAdd().getVal(M_POK_07).getCurrent());
        assertEq(5,editEditSelectedPlayerPkSimuStepsFighterMovesCurrAdd().getVal(M_POK_07).getMax());
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterMovesCurrRem().contains(M_POK_07));
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterStatusRelat().getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))));
        assertEq(BoolVal.FALSE,editEditSelectedPlayerPkSimuStepsFighterIncrUserAccuracy(false).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))));
        assertEq(BoolVal.TRUE,editEditSelectedPlayerPkSimuStepsFighterIncrUserAccuracy(true).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))));
        assertEq(M_POK_06,editEditSelectedPlayerPkSimuStepsFighterTrackingMoves(false).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getMove());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterTrackingMoves(false).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getActivity().getNbTurn());
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterTrackingMoves(false).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getActivity().isEnabled());
        assertEq(M_POK_06,editEditSelectedPlayerPkSimuStepsFighterTrackingMoves(true).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getMove());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterTrackingMoves(true).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getActivity().getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterTrackingMoves(true).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getActivity().isEnabled());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterTrappingMoves(false).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getNbTurn());
        assertFalse(editEditSelectedPlayerPkSimuStepsFighterTrappingMoves(false).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).isEnabled());
        assertEq(2,editEditSelectedPlayerPkSimuStepsFighterTrappingMoves(true).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).getNbTurn());
        assertTrue(editEditSelectedPlayerPkSimuStepsFighterTrappingMoves(true).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).isEnabled());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterPrivateMoves(M_POK_06).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).size());
        assertEq(M_POK_06,editEditSelectedPlayerPkSimuStepsFighterPrivateMoves(M_POK_06).getVal(new MoveTeamPosition(M_POK_07,Fight.toFoeFighter(0))).get(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEvo().size());
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEvo().getVal(P_POK_02).getAbilities().size());
        assertEq(A_SIM_2,editEditSelectedPlayerPkSimuStepsFighterEvo().getVal(P_POK_02).getAbilities().get(0));
        assertEq(1,editEditSelectedPlayerPkSimuStepsFighterEvo().getVal(P_POK_02).getMoves().size());
        assertEq(M_POK_02,editEditSelectedPlayerPkSimuStepsFighterEvo().getVal(P_POK_02).getMoves().get(0));
        assertEq(0,editEditSelectedPlayerPkSimuStepsFighterEvoRem().size());
    }
    @Test
    public void simuSteps1() {
        assertEq("0",editEditSelectedPlayerPkSimuSteps().getSeed().tryRet());
    }
    @Test
    public void simuSteps2() {
        assertEq("0",editEditSelectedPlayerPkSimuStepsNext().getSeed().tryRet());
    }
    @Test
    public void simuSteps3() {
        assertEq("0",editEditSelectedPlayerPkSimuStepsCore().getSeed().tryRet());
    }
    @Test
    public void simuSteps4() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFirstPos1().getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsFirstPos2().getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsFirstPos3().getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsFirstPos4().getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsFirstPos5().getSeed().tryRet());
    }
    @Test
    public void simuSteps5() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsCoreTeam().getSeed().tryRet());
    }
    @Test
    public void simuSteps6() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsTeam1().getSeed().tryRet());
    }
    @Test
    public void simuSteps7() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsTeam2().getSeed().tryRet());
    }
    @Test
    public void simuSteps8() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsTeam3().getSeed().tryRet());
    }
    @Test
    public void simuSteps9() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsTeam4().getSeed().tryRet());
    }
    @Test
    public void simuSteps10() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsTeam5().getSeed().tryRet());
    }
    @Test
    public void simuSteps11() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsTeam6().getSeed().tryRet());
    }
    @Test
    public void simuSteps12() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsCoreFighter(KindAction.NO).getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsCoreFighter(KindAction.HEAL).getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsCoreFighter(KindAction.MOVE).getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsCoreFighter(KindAction.HEAL_MOVE).getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsCoreFighter(KindAction.SWITCH).getSeed().tryRet());
        assertEq("0", editEditSelectedPlayerPkSimuStepsCoreFighterCa().getSeed().tryRet());
    }
    @Test
    public void simuSteps13() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter1().getSeed().tryRet());
    }
    @Test
    public void simuSteps14() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter2().getSeed().tryRet());
    }
    @Test
    public void simuSteps15() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter3().getSeed().tryRet());
    }
    @Test
    public void simuSteps16() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter4().getSeed().tryRet());
    }
    @Test
    public void simuSteps17() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter5().getSeed().tryRet());
    }
    @Test
    public void simuSteps18() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter6().getSeed().tryRet());
    }
    @Test
    public void simuSteps19() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter7().getSeed().tryRet());
    }
    @Test
    public void simuSteps20() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter8().getSeed().tryRet());
    }
    @Test
    public void simuSteps21() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter9().getSeed().tryRet());
    }
    @Test
    public void simuSteps22() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter10().getSeed().tryRet());
    }
    @Test
    public void simuSteps23() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter11().getSeed().tryRet());
    }
    @Test
    public void simuSteps24() {
        assertEq("0", editEditSelectedPlayerPkSimuStepsFighter12().getSeed().tryRet());
    }
    @Test
    public void exit() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,quit());
    }
    @Test
    public void heart() {
        assertEq("",navigateDiffChange(callChange(beanDiffDis(EN),DifficultyWinPointsFight.FACILE.getWinName())));
    }
}
