package aiki.beans.simulation;

import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.fight.Fighter;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.StringList;
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
    public void selectFoePkAddedForget() {
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
    public void selectAllyPkAddedForget() {
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
        assertEq("AAABAAAD",callSimulationBeanGetImage(pkPlayerSelectPkNameAdded(P_POK_01_TR),0));
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
    @Test
    public void selectPlayerPkInit() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editNoPlayerPk());
    }
    @Test
    public void selectPlayerPkAddedNoSelect() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editNoSelectedPlayerPk());
    }
    @Test
    public void selectPlayerPkAddedForget() {
        assertEq(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,editForgetSelectedPlayerPk());
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
        assertEq(0,callPokemonPlayerDtoIndexGet(elt(callSimulationBeanTeamGet(pkPlayerRemove()),0)));
    }
    @Test
    public void pkPlayerNameAfterRemove() {
        assertEq(P_POK_01_TR,callSimulationBeanGetName(pkPlayerRemove(),0));
    }
    @Test
    public void getSelectedPkPlayerIndexe() {
        assertEq(0,callSimulationBeanSelectedPkGet(formEditSelectedPlayerPk()));
    }
    @Test
    public void getSelectedAction() {
        assertEq(TeamCrud.EDIT.getTeamCrudString(),callSimulationBeanSelectedActionGet(formEditSelectedPlayerPk()));
    }
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
        assertEq(I_BALL,first(elt(callEditPokemonBeanBallsGet(editEditSelectedPlayerPk()),0)));
    }
    @Test
    public void getBallsEditValue() {
        assertEq(I_BALL_TR,second(elt(callEditPokemonBeanBallsGet(editEditSelectedPlayerPk()),0)));
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
        assertEq(0,callEvLineEvGet(second(elt(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),0))));
    }
    @Test
    public void getEvEditAttackTr() {
        assertEq("ATTACK1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),1));
    }
    @Test
    public void getEvEditAttack() {
        assertEq(0,callEvLineEvGet(second(elt(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),1))));
    }
    @Test
    public void getEvEditDefenseTr() {
        assertEq("DEFENSE1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),2));
    }
    @Test
    public void getEvEditDefense() {
        assertEq(0,callEvLineEvGet(second(elt(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),2))));
    }
    @Test
    public void getEvEditSpecAttackTr() {
        assertEq("SPECIAL_ATTACK1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),3));
    }
    @Test
    public void getEvEditSpecAttack() {
        assertEq(0,callEvLineEvGet(second(elt(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),3))));
    }
    @Test
    public void getEvEditSpecDefenseTr() {
        assertEq("SPECIAL_DEFENSE1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),4));
    }
    @Test
    public void getEvEditSpecDefense() {
        assertEq(0,callEvLineEvGet(second(elt(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),4))));
    }
    @Test
    public void getEvEditSpeedTr() {
        assertEq("SPEED1",callEditPokemonBeanGetTranslatedStatistic(editEditSelectedPlayerPk(),5));
    }
    @Test
    public void getEvEditSpeed() {
        assertEq(0,callEvLineEvGet(second(elt(callEditPokemonBeanEvGet(editEditSelectedPlayerPk()),5))));
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
        assertEq(P_POK_03,first(elt(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelect()),0)));
    }
    @Test
    public void evoSelValue() {
        assertEq(P_POK_03_TR,second(elt(callSimulationBeanAvailableEvosGet(pkPlayerValidateEvosSelect()),0)));
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
        assertEq(0,first(elt(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void roundsFirstValue() {
        assertEq(0,second(elt(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void roundsSecondKey() {
        assertEq(1,first(elt(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void roundsSecondValue() {
        assertEq(1,second(elt(callSimulationBeanRoundGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void placesFights() {
        assertSizeEq(3,callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()));
    }
    @Test
    public void placesFightsFirstKey() {
        assertEq(Fighter.BACK,first(elt(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void placesFightsFirstValue() {
        assertEq(NULL_REF,second(elt(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),0)));
    }
    @Test
    public void placesFightsSecondKey() {
        assertEq(0,first(elt(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void placesFightsSecondValue() {
        assertEq("0",second(elt(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),1)));
    }
    @Test
    public void placesFightsThirdKey() {
        assertEq(1,first(elt(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),2)));
    }
    @Test
    public void placesFightsThirdValue() {
        assertEq("1",second(elt(callSimulationBeanPlacesFightGet(pkPlayerEvoThenFighters()),2)));
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
        assertEq(A_SIM_1,first(elt(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),0)));
    }
    @Test
    public void abilitiesFirstValue() {
        assertEq(A_SIM_1_TR,second(elt(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),0)));
    }
    @Test
    public void abilitiesSecondKey() {
        assertEq(A_SIM_2,first(elt(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),1)));
    }
    @Test
    public void abilitiesSecondValue() {
        assertEq(A_SIM_2_TR,second(elt(callSimulationBeanAbilitiesGet(pkPlayerEvoFightersSufficientFrontsFormMove(1)),1)));
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
    public void okMoveSets() {
        assertTrue(callSimulationBeanOkGet(pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersOk()));
    }
    @Test
    public void targetsFight() {
        assertSizeEq(2,callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)));
    }
    @Test
    public void targetsFightFirstKey() {
        assertEq(0,first(elt(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),0)));
    }
    @Test
    public void targetsFightFirstValue() {
        assertEq("0",second(elt(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),0)));
    }
    @Test
    public void targetsFightSecondKey() {
        assertEq(1,first(elt(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),1)));
    }
    @Test
    public void targetsFightSecondValue() {
        assertEq("1",second(elt(callSimulationBeanTargetFightGet(pkPlayerEvoFighterChoice(0,0)),1)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound1() {
        assertSizeEq(2,callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,0)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound1IndexFirst() {
        assertEq(0,callRadioLineMoveIndexGet(elt(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,0)),0)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound1IndexSecond() {
        assertEq(1,callRadioLineMoveIndexGet(elt(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,0)),1)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound2() {
        assertSizeEq(2,callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,1)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound2IndexFirst() {
        assertEq(0,callRadioLineMoveIndexGet(elt(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,1)),0)));
    }
    @Test
    public void moveSetFrontFighterChoiceRound2IndexSecond() {
        assertEq(1,callRadioLineMoveIndexGet(elt(callSimulationBeanMovesSetGet(pkPlayerEvoFighterChoice(0,1)),1)));
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
}
