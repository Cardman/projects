package aiki.beans.simulation;

import aiki.facade.enums.SelectedBoolean;
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
        assertEq(P_POK_01_TR,callEditTrainerPokemonBeanGetTranslatedName(pkTrainerSelectPkName(1)));
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
        assertEq(P_POK_00_TR,callEditTrainerPokemonBeanGetTranslatedName(pkTrainerSelectCancel()));
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
}
