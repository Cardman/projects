package aiki.beans.solution;

import org.junit.Test;

public final class SolutionBeanTest extends InitDbSolution {
    @Test
    public void getSteps1() {
        assertSizeEq(5, callSolutionBeanStepsGet());
    }
    @Test
    public void getSteps2() {
        assertSizeEq(5, callSolutionBeanStepsTwiceGet());
    }
    @Test
    public void getNames1() {
        assertSizeEq(1, callStepDtoGetNames(elt(callSolutionBeanStepsGet(),0)));
    }
    @Test
    public void getNames2() {
        assertSizeEq(1, callStepDtoGetNames(elt(callSolutionBeanStepsGet(),1)));
    }
    @Test
    public void getNames3() {
        assertSizeEq(1, callStepDtoGetNames(elt(callSolutionBeanStepsGet(),2)));
    }
    @Test
    public void getNames4() {
        assertSizeEq(0, callStepDtoGetNames(elt(callSolutionBeanStepsGet(),3)));
    }
    @Test
    public void getNames5() {
        assertSizeEq(0, callStepDtoGetNames(elt(callSolutionBeanStepsGet(),4)));
    }
    @Test
    public void getTrainer1() {
        assertEq(G_L, callPlaceTrainerDtoTrainerGet(elt(callStepDtoGetNames(elt(callSolutionBeanStepsGet(),0)),0)));
    }
    @Test
    public void getTrainer2() {
        assertEq(D_T_1+" "+D_T_2, callPlaceTrainerDtoTrainerGet(elt(callStepDtoGetNames(elt(callSolutionBeanStepsGet(),1)),0)));
    }
    @Test
    public void getTrainer3() {
        assertEq(T_L, callPlaceTrainerDtoTrainerGet(elt(callStepDtoGetNames(elt(callSolutionBeanStepsGet(),2)),0)));
    }
    @Test
    public void getPlace1() {
        assertEq(CITY2, callPlaceTrainerDtoPlaceGet(elt(callStepDtoGetNames(elt(callSolutionBeanStepsGet(),0)),0)));
    }
    @Test
    public void getPlace2() {
        assertEq(ROAD2, callPlaceTrainerDtoPlaceGet(elt(callStepDtoGetNames(elt(callSolutionBeanStepsGet(),1)),0)));
    }
    @Test
    public void gePlace3() {
        assertEq(LEAGUE, callPlaceTrainerDtoPlaceGet(elt(callStepDtoGetNames(elt(callSolutionBeanStepsGet(),2)),0)));
    }
    @Test
    public void getPokemon1() {
        assertSizeEq(1, callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),0)));
    }
    @Test
    public void getPokemon2() {
        assertSizeEq(1, callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),1)));
    }
    @Test
    public void getPokemon3() {
        assertSizeEq(0, callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),2)));
    }
    @Test
    public void getPokemon4() {
        assertSizeEq(2, callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)));
    }
    @Test
    public void getPokemon5() {
        assertSizeEq(0, callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),4)));
    }
    @Test
    public void getPokemonSize1() {
        assertSizeEq(1, second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),0)),0)));
    }
    @Test
    public void getPokemonSize2() {
        assertSizeEq(1, second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),1)),0)));
    }
    @Test
    public void getPokemonSize3() {
        assertSizeEq(1, second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),0)));
    }
    @Test
    public void getPokemonSize4() {
        assertSizeEq(1, second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),1)));
    }
    @Test
    public void getImage1() {
        assertEq(one(IMG_00), callWildPokemonDtoImageGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),0)),0)),0)));
    }
    @Test
    public void getImage2() {
        assertEq(one(IMG_02), callWildPokemonDtoImageGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),1)),0)),0)));
    }
    @Test
    public void getImage3() {
        assertEq(one(IMG_04), callWildPokemonDtoImageGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),0)),0)));
    }
    @Test
    public void getImage4() {
        assertEq(one(IMG_05), callWildPokemonDtoImageGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),1)),0)));
    }
    @Test
    public void getName1() {
        assertEq(P_POK_00_TR, callWildPokemonDtoNameGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),0)),0)),0)));
    }
    @Test
    public void getName2() {
        assertEq(P_POK_02_TR, callWildPokemonDtoNameGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),1)),0)),0)));
    }
    @Test
    public void getName3() {
        assertEq(P_POK_04_TR, callWildPokemonDtoNameGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),0)),0)));
    }
    @Test
    public void getName4() {
        assertEq(P_POK_05_TR, callWildPokemonDtoNameGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),1)),0)));
    }
    @Test
    public void getGender1() {
        assertEq(NO_G, callWildPokemonDtoGenderGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),0)),0)),0)));
    }
    @Test
    public void getGender2() {
        assertEq(NO_G, callWildPokemonDtoGenderGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),1)),0)),0)));
    }
    @Test
    public void getGender3() {
        assertEq(NO_G, callWildPokemonDtoGenderGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),0)),0)));
    }
    @Test
    public void getGender4() {
        assertEq(NO_G, callWildPokemonDtoGenderGet(elt(second(elt(callStepDtoGetPokemon(elt(callSolutionBeanStepsGet(),3)),1)),0)));
    }
    @Test
    public void getPlaceWp1() {
        assertEq(ROAD1, callSolutionBeanGetPlace(0,0));
    }
    @Test
    public void getPlaceWp2() {
        assertEq(ROAD2, callSolutionBeanGetPlace(1,0));
    }
    @Test
    public void getPlaceWp3() {
        assertEq(CAVE+" "+0, callSolutionBeanGetPlace(3,0));
    }
    @Test
    public void getPlaceWp4() {
        assertEq(CAVE+" "+1, callSolutionBeanGetPlace(3,1));
    }
}
