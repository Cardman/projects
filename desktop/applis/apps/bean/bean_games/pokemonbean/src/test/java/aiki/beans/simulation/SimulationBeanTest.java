package aiki.beans.simulation;

import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.facade.enums.SelectedBoolean;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import org.junit.Test;

public final class SimulationBeanTest extends InitDbSimulation {
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
        assertEq(1,elt(callSimulationBeanNumbers(2),0));
    }
    @Test
    public void getNumbers3() {
        assertEq(2,elt(callSimulationBeanNumbers(2),1));
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
        assertSizeEq(10,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkName("")));
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
        assertSizeEq(0,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkType("T_*",true)));
    }
    @Test
    public void getPokedexWholeWord2() {
        assertSizeEq(10,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkType("T_*",false)));
    }
    @Test
    public void getMiniImagePk() {
        assertEq("AAABAAAC",callSelectPokemonBeanGetMiniImage());
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
        assertSizeEq(3,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkHasEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexHasEvo2() {
        assertSizeEq(7,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkHasEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isEvoSelPk() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callSelectPokemonBeanIsEvoGet(pkTrainerSelectPk()));
    }
    @Test
    public void getPokedexIsEvo1() {
        assertSizeEq(3,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsEvo2() {
        assertSizeEq(7,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isLegPk() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callSelectPokemonBeanIsLegGet(pkTrainerSelectPk()));
    }
    @Test
    public void getPokedexIsLeg1() {
        assertSizeEq(2,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsLeg(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsLeg2() {
        assertSizeEq(8,callSelectPokemonBeanPokedexGet(pkTrainerSelectPkIsLeg(SelectedBoolean.NO.getBoolName())));
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
        assertSizeEq(4,callSelectItemBeanItemsGet(pkTrainerSelectItName("")));
    }
    @Test
    public void typePriceSelIt() {
        assertEq(NULL_REF,callSelectItemBeanTypedPriceGet(pkTrainerSelectItName()));
    }
    @Test
    public void getSelectedItemsPrice() {
        assertSizeEq(4,callSelectItemBeanItemsGet(pkTrainerSelectItPrice()));
    }
    @Test
    public void typeClassSelIt() {
        assertEq(NULL_REF,callSelectItemBeanTypedClassGet(pkTrainerSelectItName()));
    }
    @Test
    public void getSelectedItemsClass() {
        assertSizeEq(2,callSelectItemBeanItemsGet(pkTrainerSelectItCl(CI_BATTLE)));
    }
    @Test
    public void getSelectedIt() {
        assertEq(I_STONE_TR,callEditTrainerPokemonBeanGetTranslatedItem(pkTrainerSelectItName(I_STONE_TR)));
    }
    @Test
    public void getMiniImageIt() {
        assertEq("AAABBUTF",callSelectItemBeanGetMiniImage());
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
        assertFalse(callSelectLineMoveSelectedGet(elt(callEditTrainerPokemonBeanMovesGet(pkTrainer()),0)));
    }
    @Test
    public void getMovesPkTrainerElt2() {
        assertFalse(callSelectLineMoveSelectedGet(elt(callEditTrainerPokemonBeanMovesGet(pkTrainer(false)),0)));
    }
    @Test
    public void getMovesPkTrainerElt3() {
        assertTrue(callSelectLineMoveSelectedGet(elt(callEditTrainerPokemonBeanMovesGet(pkTrainer(true)),0)));
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
        assertEq("AAABAAAC",callSimulationBeanGetImageFoe(addPkTrainerChangeMoves(false),0));
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
        assertEq("AAABAAAC",callSimulationBeanGetImageAlly(addPkTrainerChangeMoves(true),0));
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
        assertEq(1,callPokemonTrainerDtoIndexGet(elt(callSimulationBeanFoeTeamGet(pkTrainerIndex()),1)));
    }
    @Test
    public void cancelAddPkTrainer() {
        assertSizeEq(0,callSimulationBeanFoeTeamGet(pkTrainerLevelCancelAdd()));
    }
    @Test
    public void selectFoePkInit() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editNoFoePk());
    }
    @Test
    public void selectFoePkAddedNoSelect() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editNoSelectedFoePk());
    }
    @Test
    public void selectFoePkAddedForeget() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editForgetSelectedFoePk());
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
        assertEq(0,callPokemonTrainerDtoIndexGet(elt(callSimulationBeanFoeTeamGet(pkTrainerFoeRemove()),0)));
    }
    @Test
    public void pkTrainerNameFoeAfterRemove() {
        assertEq(P_POK_01_TR,callSimulationBeanGetNameFoe(pkTrainerFoeRemove(),0));
    }
    @Test
    public void getSelectedFoePk() {
        assertEq(0,callSimulationBeanSelectedFoePkGet(formEditSelectedFoePk()));
    }
    @Test
    public void getSelectedFoeAction() {
        assertEq(TeamCrud.EDIT.getTeamCrudString(),callSimulationBeanSelectedFoeActionGet(formEditSelectedFoePk()));
    }
    @Test
    public void selectAllyPkInit() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editNoAllyPk());
    }
    @Test
    public void selectAllyPkAddedNoSelect() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editNoSelectedAllyPk());
    }
    @Test
    public void selectAllyPkAddedForeget() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editForgetSelectedAllyPk());
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
        assertEq(0,callPokemonTrainerDtoIndexGet(elt(callSimulationBeanAllyTeamGet(pkTrainerAllyRemove()),0)));
    }
    @Test
    public void pkTrainerNameAllyAfterRemove() {
        assertEq(P_POK_01_TR,callSimulationBeanGetNameAlly(pkTrainerAllyRemove(),0));
    }
    @Test
    public void getSelectedAllyPk() {
        assertEq(0,callSimulationBeanSelectedAllyPkGet(formEditSelectedAllyPk()));
    }
    @Test
    public void getSelectedAllyAction() {
        assertEq(TeamCrud.EDIT.getTeamCrudString(),callSimulationBeanSelectedAllyActionGet(formEditSelectedAllyPk()));
    }
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
        assertSizeEq(10,callAddPokemonBeanPokedexGet(pkPlayerSelectPkName("")));
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
        assertSizeEq(0,callAddPokemonBeanPokedexGet(pkPlayerSelectPkType("T_*",true)));
    }
    @Test
    public void getPokedexPkPlayerWholeWord2() {
        assertSizeEq(10,callAddPokemonBeanPokedexGet(pkPlayerSelectPkType("T_*",false)));
    }
    @Test
    public void getMiniImagePkPlayer() {
        assertEq("AAABAAAC",callAddPokemonBeanGetMiniImage());
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
        assertSizeEq(3,callAddPokemonBeanPokedexGet(pkPlayerSelectPkHasEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexPkPlayerHasEvo2() {
        assertSizeEq(7,callAddPokemonBeanPokedexGet(pkPlayerSelectPkHasEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isEvoSelPkPlayer() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callAddPokemonBeanIsEvoGet(pkPlayerSelectPk()));
    }
    @Test
    public void getPokedexIsEvoPkPlayer1() {
        assertSizeEq(3,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsEvo(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsEvoPkPlayer2() {
        assertSizeEq(7,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsEvo(SelectedBoolean.NO.getBoolName())));
    }
    @Test
    public void isLegPkPlayer() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(),callAddPokemonBeanIsLegGet(pkPlayerSelectPk()));
    }
    @Test
    public void getPokedexIsLegPkPlayer1() {
        assertSizeEq(2,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsLeg(SelectedBoolean.YES.getBoolName())));
    }
    @Test
    public void getPokedexIsLegPkPlayer2() {
        assertSizeEq(8,callAddPokemonBeanPokedexGet(pkPlayerSelectPkIsLeg(SelectedBoolean.NO.getBoolName())));
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
}
