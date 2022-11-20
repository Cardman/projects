package aiki.beans.simulation;

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
}
